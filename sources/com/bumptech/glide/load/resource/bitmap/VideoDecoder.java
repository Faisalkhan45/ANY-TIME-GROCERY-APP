package com.bumptech.glide.load.resource.bitmap;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    public static final long DEFAULT_FRAME = -1;
    static final int DEFAULT_FRAME_OPTION = 2;
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        public void update(byte[] keyBytes, Integer value, MessageDigest messageDigest) {
            if (value != null) {
                messageDigest.update(keyBytes);
                synchronized (this.buffer) {
                    this.buffer.position(0);
                    messageDigest.update(this.buffer.putInt(value.intValue()).array());
                }
            }
        }
    });
    private static final List<String> PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX = Collections.unmodifiableList(Arrays.asList(new String[]{"TP1A", "TD1A.220804.031"}));
    private static final String TAG = "VideoDecoder";
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        public void update(byte[] keyBytes, Long value, MessageDigest messageDigest) {
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(value.longValue()).array());
            }
        }
    });
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaMetadataRetrieverInitializer<T> initializer;

    interface MediaMetadataRetrieverInitializer<T> {
        void initialize(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new AssetFileDescriptorInitializer());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ParcelFileDescriptorInitializer());
    }

    public static ResourceDecoder<ByteBuffer, Bitmap> byteBuffer(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ByteBufferInitializer());
    }

    VideoDecoder(BitmapPool bitmapPool2, MediaMetadataRetrieverInitializer<T> initializer2) {
        this(bitmapPool2, initializer2, DEFAULT_FACTORY);
    }

    VideoDecoder(BitmapPool bitmapPool2, MediaMetadataRetrieverInitializer<T> initializer2, MediaMetadataRetrieverFactory factory2) {
        this.bitmapPool = bitmapPool2;
        this.initializer = initializer2;
        this.factory = factory2;
    }

    public boolean handles(T t, Options options) {
        return true;
    }

    public Resource<Bitmap> decode(T resource, int outWidth, int outHeight, Options options) throws IOException {
        Integer frameOption;
        DownsampleStrategy downsampleStrategy;
        int i;
        Options options2 = options;
        long frameTimeMicros = ((Long) options2.get(TARGET_FRAME)).longValue();
        if (frameTimeMicros >= 0 || frameTimeMicros == -1) {
            Integer frameOption2 = (Integer) options2.get(FRAME_OPTION);
            if (frameOption2 == null) {
                frameOption = 2;
            } else {
                frameOption = frameOption2;
            }
            DownsampleStrategy downsampleStrategy2 = (DownsampleStrategy) options2.get(DownsampleStrategy.OPTION);
            if (downsampleStrategy2 == null) {
                downsampleStrategy = DownsampleStrategy.DEFAULT;
            } else {
                downsampleStrategy = downsampleStrategy2;
            }
            MediaMetadataRetriever mediaMetadataRetriever = this.factory.build();
            try {
                this.initializer.initialize(mediaMetadataRetriever, resource);
                Bitmap result = decodeFrame(mediaMetadataRetriever, frameTimeMicros, frameOption.intValue(), outWidth, outHeight, downsampleStrategy);
                if (i < 29) {
                    mediaMetadataRetriever.release();
                }
                return BitmapResource.obtain(result, this.bitmapPool);
            } finally {
                if (Build.VERSION.SDK_INT >= 29) {
                    mediaMetadataRetriever.close();
                } else {
                    mediaMetadataRetriever.release();
                }
            }
        } else {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + frameTimeMicros);
        }
    }

    private static Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        Bitmap result = null;
        if (!(Build.VERSION.SDK_INT < 27 || outWidth == Integer.MIN_VALUE || outHeight == Integer.MIN_VALUE || strategy == DownsampleStrategy.NONE)) {
            result = decodeScaledFrame(mediaMetadataRetriever, frameTimeMicros, frameOption, outWidth, outHeight, strategy);
        }
        if (result == null) {
            result = decodeOriginalFrame(mediaMetadataRetriever, frameTimeMicros, frameOption);
        }
        Bitmap result2 = correctHdr180DegVideoFrameOrientation(mediaMetadataRetriever, result);
        if (result2 != null) {
            return result2;
        }
        throw new VideoDecoderException();
    }

    private static Bitmap correctHdr180DegVideoFrameOrientation(MediaMetadataRetriever mediaMetadataRetriever, Bitmap frame) {
        if (!isHdr180RotationFixRequired()) {
            return frame;
        }
        boolean requiresHdr180RotationFix = false;
        try {
            if (isHDR(mediaMetadataRetriever)) {
                requiresHdr180RotationFix = Math.abs(Integer.parseInt(mediaMetadataRetriever.extractMetadata(24))) == 180;
            }
        } catch (NumberFormatException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception trying to extract HDR transfer function or rotation");
            }
        }
        if (!requiresHdr180RotationFix) {
            return frame;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Applying HDR 180 deg thumbnail correction");
        }
        Matrix rotationMatrix = new Matrix();
        rotationMatrix.postRotate(180.0f, ((float) frame.getWidth()) / 2.0f, ((float) frame.getHeight()) / 2.0f);
        return Bitmap.createBitmap(frame, 0, 0, frame.getWidth(), frame.getHeight(), rotationMatrix, true);
    }

    private static boolean isHDR(MediaMetadataRetriever mediaMetadataRetriever) throws NumberFormatException {
        String colorTransferString = mediaMetadataRetriever.extractMetadata(36);
        String colorStandardString = mediaMetadataRetriever.extractMetadata(35);
        int colorTransfer = Integer.parseInt(colorTransferString);
        return (colorTransfer == 7 || colorTransfer == 6) && Integer.parseInt(colorStandardString) == 6;
    }

    static boolean isHdr180RotationFixRequired() {
        if (!Build.MODEL.startsWith("Pixel") || Build.VERSION.SDK_INT != 33) {
            return Build.VERSION.SDK_INT >= 30 && Build.VERSION.SDK_INT < 33;
        }
        return isTBuildRequiringRotationFix();
    }

    private static boolean isTBuildRequiringRotationFix() {
        for (String buildId : PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX) {
            if (Build.ID.startsWith(buildId)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeScaledFrame(android.media.MediaMetadataRetriever r14, long r15, int r17, int r18, int r19, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r20) {
        /*
            r7 = r14
            r0 = 18
            java.lang.String r0 = r14.extractMetadata(r0)     // Catch:{ all -> 0x0052 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x0052 }
            r1 = 19
            java.lang.String r1 = r14.extractMetadata(r1)     // Catch:{ all -> 0x0052 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x0052 }
            r2 = 24
            java.lang.String r2 = r14.extractMetadata(r2)     // Catch:{ all -> 0x0052 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x0052 }
            r8 = r2
            r2 = 90
            if (r8 == r2) goto L_0x002b
            r2 = 270(0x10e, float:3.78E-43)
            if (r8 != r2) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r9 = r1
            goto L_0x002f
        L_0x002b:
            r2 = r0
            r0 = r1
            r1 = r2
            r9 = r1
        L_0x002f:
            r10 = r18
            r11 = r19
            r12 = r20
            float r1 = r12.getScaleFactor(r0, r9, r10, r11)     // Catch:{ all -> 0x0050 }
            r13 = r1
            float r1 = (float) r0     // Catch:{ all -> 0x0050 }
            float r1 = r1 * r13
            int r5 = java.lang.Math.round(r1)     // Catch:{ all -> 0x0050 }
            float r1 = (float) r9     // Catch:{ all -> 0x0050 }
            float r1 = r1 * r13
            int r6 = java.lang.Math.round(r1)     // Catch:{ all -> 0x0050 }
            r1 = r14
            r2 = r15
            r4 = r17
            android.graphics.Bitmap r1 = r1.getScaledFrameAtTime(r2, r4, r5, r6)     // Catch:{ all -> 0x0050 }
            return r1
        L_0x0050:
            r0 = move-exception
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r10 = r18
            r11 = r19
            r12 = r20
        L_0x0059:
            r1 = 3
            java.lang.String r2 = "VideoDecoder"
            boolean r1 = android.util.Log.isLoggable(r2, r1)
            if (r1 == 0) goto L_0x0067
            java.lang.String r1 = "Exception trying to decode a scaled frame on oreo+, falling back to a fullsize frame"
            android.util.Log.d(r2, r1, r0)
        L_0x0067:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.VideoDecoder.decodeScaledFrame(android.media.MediaMetadataRetriever, long, int, int, int, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy):android.graphics.Bitmap");
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption) {
        return mediaMetadataRetriever.getFrameAtTime(frameTimeMicros, frameOption);
    }

    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    private static final class AssetFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        public void initialize(MediaMetadataRetriever retriever, AssetFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
        }
    }

    static final class ParcelFileDescriptorInitializer implements MediaMetadataRetrieverInitializer<ParcelFileDescriptor> {
        ParcelFileDescriptorInitializer() {
        }

        public void initialize(MediaMetadataRetriever retriever, ParcelFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor());
        }
    }

    static final class ByteBufferInitializer implements MediaMetadataRetrieverInitializer<ByteBuffer> {
        ByteBufferInitializer() {
        }

        public void initialize(MediaMetadataRetriever retriever, final ByteBuffer data) {
            retriever.setDataSource(new MediaDataSource() {
                public int readAt(long position, byte[] buffer, int offset, int size) {
                    if (position >= ((long) data.limit())) {
                        return -1;
                    }
                    data.position((int) position);
                    int numBytesRead = Math.min(size, data.remaining());
                    data.get(buffer, offset, numBytesRead);
                    return numBytesRead;
                }

                public long getSize() {
                    return (long) data.limit();
                }

                public void close() {
                }
            });
        }
    }

    private static final class VideoDecoderException extends RuntimeException {
        private static final long serialVersionUID = -2556382523004027815L;

        VideoDecoderException() {
            super("MediaMetadataRetriever failed to retrieve a frame without throwing, check the adb logs for .*MetadataRetriever.* prior to this exception for details");
        }
    }
}
