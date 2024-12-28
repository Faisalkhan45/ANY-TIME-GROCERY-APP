package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import com.bumptech.glide.util.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

interface ImageReader {
    Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException;

    int getImageOrientation() throws IOException;

    ImageHeaderParser.ImageType getImageType() throws IOException;

    void stopGrowingBuffers();

    public static final class ByteArrayReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final byte[] bytes;
        private final List<ImageHeaderParser> parsers;

        ByteArrayReader(byte[] bytes2, List<ImageHeaderParser> parsers2, ArrayPool byteArrayPool2) {
            this.bytes = bytes2;
            this.parsers = parsers2;
            this.byteArrayPool = byteArrayPool2;
        }

        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            byte[] bArr = this.bytes;
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, ByteBuffer.wrap(this.bytes));
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, ByteBuffer.wrap(this.bytes), this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
        }
    }

    public static final class FileReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final File file;
        private final List<ImageHeaderParser> parsers;

        FileReader(File file2, List<ImageHeaderParser> parsers2, ArrayPool byteArrayPool2) {
            this.file = file2;
            this.parsers = parsers2;
            this.byteArrayPool = byteArrayPool2;
        }

        public Bitmap decodeBitmap(BitmapFactory.Options options) throws FileNotFoundException {
            InputStream is = null;
            try {
                InputStream is2 = new RecyclableBufferedInputStream(new FileInputStream(this.file), this.byteArrayPool);
                Bitmap decodeStream = BitmapFactory.decodeStream(is2, (Rect) null, options);
                try {
                    is2.close();
                } catch (IOException e) {
                }
                return decodeStream;
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            InputStream is = null;
            try {
                InputStream is2 = new RecyclableBufferedInputStream(new FileInputStream(this.file), this.byteArrayPool);
                ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(this.parsers, is2, this.byteArrayPool);
                try {
                    is2.close();
                } catch (IOException e) {
                }
                return type;
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        }

        public int getImageOrientation() throws IOException {
            InputStream is = null;
            try {
                InputStream is2 = new RecyclableBufferedInputStream(new FileInputStream(this.file), this.byteArrayPool);
                int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, is2, this.byteArrayPool);
                try {
                    is2.close();
                } catch (IOException e) {
                }
                return orientation;
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        }

        public void stopGrowingBuffers() {
        }
    }

    public static final class ByteBufferReader implements ImageReader {
        private final ByteBuffer buffer;
        private final ArrayPool byteArrayPool;
        private final List<ImageHeaderParser> parsers;

        ByteBufferReader(ByteBuffer buffer2, List<ImageHeaderParser> parsers2, ArrayPool byteArrayPool2) {
            this.buffer = buffer2;
            this.parsers = parsers2;
            this.byteArrayPool = byteArrayPool2;
        }

        public Bitmap decodeBitmap(BitmapFactory.Options options) {
            return BitmapFactory.decodeStream(stream(), (Rect) null, options);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, ByteBufferUtil.rewind(this.buffer));
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, ByteBufferUtil.rewind(this.buffer), this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
        }

        private InputStream stream() {
            return ByteBufferUtil.toStream(ByteBufferUtil.rewind(this.buffer));
        }
    }

    public static final class InputStreamImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final InputStreamRewinder dataRewinder;
        private final List<ImageHeaderParser> parsers;

        InputStreamImageReader(InputStream is, List<ImageHeaderParser> parsers2, ArrayPool byteArrayPool2) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(byteArrayPool2);
            this.parsers = (List) Preconditions.checkNotNull(parsers2);
            this.dataRewinder = new InputStreamRewinder(is, byteArrayPool2);
        }

        public Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeStream(this.dataRewinder.rewindAndGet(), (Rect) null, options);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder.rewindAndGet(), this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
            this.dataRewinder.fixMarkLimits();
        }
    }

    public static final class ParcelFileDescriptorImageReader implements ImageReader {
        private final ArrayPool byteArrayPool;
        private final ParcelFileDescriptorRewinder dataRewinder;
        private final List<ImageHeaderParser> parsers;

        ParcelFileDescriptorImageReader(ParcelFileDescriptor parcelFileDescriptor, List<ImageHeaderParser> parsers2, ArrayPool byteArrayPool2) {
            this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(byteArrayPool2);
            this.parsers = (List) Preconditions.checkNotNull(parsers2);
            this.dataRewinder = new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        public Bitmap decodeBitmap(BitmapFactory.Options options) throws IOException {
            return BitmapFactory.decodeFileDescriptor(this.dataRewinder.rewindAndGet().getFileDescriptor(), (Rect) null, options);
        }

        public ImageHeaderParser.ImageType getImageType() throws IOException {
            return ImageHeaderParserUtils.getType(this.parsers, this.dataRewinder, this.byteArrayPool);
        }

        public int getImageOrientation() throws IOException {
            return ImageHeaderParserUtils.getOrientation(this.parsers, this.dataRewinder, this.byteArrayPool);
        }

        public void stopGrowingBuffers() {
        }
    }
}
