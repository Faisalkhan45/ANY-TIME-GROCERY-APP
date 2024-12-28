package com.bumptech.glide;

import android.content.Context;
import androidx.tracing.Trace;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.util.GlideSuppliers;
import java.util.List;

final class RegistryFactory {
    private RegistryFactory() {
    }

    static GlideSuppliers.GlideSupplier<Registry> lazilyCreateAndInitializeRegistry(final Glide glide, final List<GlideModule> manifestModules, final AppGlideModule annotationGeneratedModule) {
        return new GlideSuppliers.GlideSupplier<Registry>() {
            private boolean isInitializingOrInitialized;

            public Registry get() {
                if (!this.isInitializingOrInitialized) {
                    this.isInitializingOrInitialized = true;
                    Trace.beginSection("Glide registry");
                    try {
                        return RegistryFactory.createAndInitRegistry(Glide.this, manifestModules, annotationGeneratedModule);
                    } finally {
                        Trace.endSection();
                    }
                } else {
                    throw new IllegalStateException("Recursive Registry initialization! In your AppGlideModule and LibraryGlideModules, Make sure you're using the provided Registry rather calling glide.getRegistry()!");
                }
            }
        };
    }

    static Registry createAndInitRegistry(Glide glide, List<GlideModule> manifestModules, AppGlideModule annotationGeneratedModule) {
        BitmapPool bitmapPool = glide.getBitmapPool();
        ArrayPool arrayPool = glide.getArrayPool();
        Context context = glide.getGlideContext().getApplicationContext();
        GlideExperiments experiments = glide.getGlideContext().getExperiments();
        Registry registry = new Registry();
        initializeDefaults(context, registry, bitmapPool, arrayPool, experiments);
        initializeModules(context, glide, registry, manifestModules, annotationGeneratedModule);
        return registry;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x03c7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void initializeDefaults(android.content.Context r27, com.bumptech.glide.Registry r28, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r29, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r30, com.bumptech.glide.GlideExperiments r31) {
        /*
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            java.lang.Class<byte[]> r4 = byte[].class
            com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser r5 = new com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser
            r5.<init>()
            r1.register((com.bumptech.glide.load.ImageHeaderParser) r5)
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 27
            if (r5 < r6) goto L_0x0020
            com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser r5 = new com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser
            r5.<init>()
            r1.register((com.bumptech.glide.load.ImageHeaderParser) r5)
        L_0x0020:
            android.content.res.Resources r5 = r27.getResources()
            java.util.List r6 = r28.getImageHeaderParsers()
            com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder r7 = new com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder
            r7.<init>(r0, r6, r2, r3)
            com.bumptech.glide.load.ResourceDecoder r8 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel(r29)
            com.bumptech.glide.load.resource.bitmap.Downsampler r9 = new com.bumptech.glide.load.resource.bitmap.Downsampler
            java.util.List r10 = r28.getImageHeaderParsers()
            android.util.DisplayMetrics r11 = r5.getDisplayMetrics()
            r9.<init>(r10, r11, r2, r3)
            int r10 = android.os.Build.VERSION.SDK_INT
            r11 = 28
            if (r10 < r11) goto L_0x005a
            java.lang.Class<com.bumptech.glide.GlideBuilder$EnableImageDecoderForBitmaps> r10 = com.bumptech.glide.GlideBuilder.EnableImageDecoderForBitmaps.class
            r12 = r31
            boolean r10 = r12.isEnabled(r10)
            if (r10 == 0) goto L_0x005c
            com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder r10 = new com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder
            r10.<init>()
            com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder r13 = new com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder
            r13.<init>()
            goto L_0x0067
        L_0x005a:
            r12 = r31
        L_0x005c:
            com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder r10 = new com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder
            r10.<init>(r9)
            r13 = r10
            com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder r10 = new com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder
            r10.<init>(r9, r3)
        L_0x0067:
            int r14 = android.os.Build.VERSION.SDK_INT
            java.lang.String r15 = "Animation"
            if (r14 < r11) goto L_0x0083
            java.lang.Class<java.io.InputStream> r11 = java.io.InputStream.class
            java.lang.Class<android.graphics.drawable.Drawable> r14 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.ResourceDecoder r12 = com.bumptech.glide.load.resource.drawable.AnimatedWebpDecoder.streamDecoder(r6, r3)
            r1.append(r15, r11, r14, r12)
            java.lang.Class<java.nio.ByteBuffer> r11 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.Drawable> r12 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.ResourceDecoder r14 = com.bumptech.glide.load.resource.drawable.AnimatedWebpDecoder.byteBufferDecoder(r6, r3)
            r1.append(r15, r11, r12, r14)
        L_0x0083:
            com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder r11 = new com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder
            r11.<init>(r0)
            com.bumptech.glide.load.model.ResourceLoader$StreamFactory r12 = new com.bumptech.glide.load.model.ResourceLoader$StreamFactory
            r12.<init>(r5)
            com.bumptech.glide.load.model.ResourceLoader$UriFactory r14 = new com.bumptech.glide.load.model.ResourceLoader$UriFactory
            r14.<init>(r5)
            r16 = r4
            com.bumptech.glide.load.model.ResourceLoader$FileDescriptorFactory r4 = new com.bumptech.glide.load.model.ResourceLoader$FileDescriptorFactory
            r4.<init>(r5)
            com.bumptech.glide.load.model.ResourceLoader$AssetFileDescriptorFactory r0 = new com.bumptech.glide.load.model.ResourceLoader$AssetFileDescriptorFactory
            r0.<init>(r5)
            r17 = r0
            com.bumptech.glide.load.resource.bitmap.BitmapEncoder r0 = new com.bumptech.glide.load.resource.bitmap.BitmapEncoder
            r0.<init>(r3)
            com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder r18 = new com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder
            r18.<init>()
            r19 = r18
            com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder r18 = new com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder
            r18.<init>()
            r20 = r18
            r18 = r14
            android.content.ContentResolver r14 = r27.getContentResolver()
            r21 = r14
            java.lang.Class<java.nio.ByteBuffer> r14 = java.nio.ByteBuffer.class
            r22 = r4
            com.bumptech.glide.load.model.ByteBufferEncoder r4 = new com.bumptech.glide.load.model.ByteBufferEncoder
            r4.<init>()
            com.bumptech.glide.Registry r4 = r1.append(r14, r4)
            java.lang.Class<java.io.InputStream> r14 = java.io.InputStream.class
            r23 = r12
            com.bumptech.glide.load.model.StreamEncoder r12 = new com.bumptech.glide.load.model.StreamEncoder
            r12.<init>(r3)
            com.bumptech.glide.Registry r4 = r4.append(r14, r12)
            java.lang.Class<java.nio.ByteBuffer> r12 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            r24 = r11
            java.lang.String r11 = "Bitmap"
            com.bumptech.glide.Registry r4 = r4.append(r11, r12, r14, r13)
            java.lang.Class<java.io.InputStream> r12 = java.io.InputStream.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            r4.append(r11, r12, r14, r10)
            boolean r4 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.isSupported()
            if (r4 == 0) goto L_0x00fa
            java.lang.Class<android.os.ParcelFileDescriptor> r4 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r12 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder r14 = new com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder
            r14.<init>(r9)
            r1.append(r11, r4, r12, r14)
        L_0x00fa:
            java.lang.Class<android.os.ParcelFileDescriptor> r4 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r12 = android.graphics.Bitmap.class
            com.bumptech.glide.Registry r4 = r1.append(r11, r4, r12, r8)
            java.lang.Class<android.content.res.AssetFileDescriptor> r12 = android.content.res.AssetFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            r25 = r9
            com.bumptech.glide.load.ResourceDecoder r9 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.asset(r29)
            com.bumptech.glide.Registry r4 = r4.append(r11, r12, r14, r9)
            java.lang.Class<android.graphics.Bitmap> r9 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.Bitmap> r12 = android.graphics.Bitmap.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r14 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r4 = r4.append(r9, r12, r14)
            java.lang.Class<android.graphics.Bitmap> r9 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.Bitmap> r12 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder r14 = new com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder
            r14.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r11, r9, r12, r14)
            java.lang.Class<android.graphics.Bitmap> r9 = android.graphics.Bitmap.class
            com.bumptech.glide.Registry r4 = r4.append(r9, r0)
            java.lang.Class<java.nio.ByteBuffer> r9 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r12 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r14 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r14.<init>((android.content.res.Resources) r5, r13)
            r26 = r13
            java.lang.String r13 = "BitmapDrawable"
            com.bumptech.glide.Registry r4 = r4.append(r13, r9, r12, r14)
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r12 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r14 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r14.<init>((android.content.res.Resources) r5, r10)
            com.bumptech.glide.Registry r4 = r4.append(r13, r9, r12, r14)
            java.lang.Class<android.os.ParcelFileDescriptor> r9 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r12 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r14 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r14.<init>((android.content.res.Resources) r5, r8)
            com.bumptech.glide.Registry r4 = r4.append(r13, r9, r12, r14)
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r9 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder r12 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder
            r12.<init>(r2, r0)
            com.bumptech.glide.Registry r4 = r4.append(r9, r12)
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r12 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.StreamGifDecoder r13 = new com.bumptech.glide.load.resource.gif.StreamGifDecoder
            r13.<init>(r6, r7, r3)
            com.bumptech.glide.Registry r4 = r4.append(r15, r9, r12, r13)
            java.lang.Class<java.nio.ByteBuffer> r9 = java.nio.ByteBuffer.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r12 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.Registry r4 = r4.append(r15, r9, r12, r7)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r9 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.GifDrawableEncoder r12 = new com.bumptech.glide.load.resource.gif.GifDrawableEncoder
            r12.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r9, r12)
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r9 = com.bumptech.glide.gifdecoder.GifDecoder.class
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r12 = com.bumptech.glide.gifdecoder.GifDecoder.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r13 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r4 = r4.append(r9, r12, r13)
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r9 = com.bumptech.glide.gifdecoder.GifDecoder.class
            java.lang.Class<android.graphics.Bitmap> r12 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder r13 = new com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder
            r13.<init>(r2)
            com.bumptech.glide.Registry r4 = r4.append(r11, r9, r12, r13)
            java.lang.Class<android.net.Uri> r9 = android.net.Uri.class
            java.lang.Class<android.graphics.drawable.Drawable> r11 = android.graphics.drawable.Drawable.class
            r12 = r24
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r12)
            java.lang.Class<android.net.Uri> r9 = android.net.Uri.class
            java.lang.Class<android.graphics.Bitmap> r11 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder r13 = new com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder
            r13.<init>(r12, r2)
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            com.bumptech.glide.load.resource.bytes.ByteBufferRewinder$Factory r9 = new com.bumptech.glide.load.resource.bytes.ByteBufferRewinder$Factory
            r9.<init>()
            com.bumptech.glide.Registry r4 = r4.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r9)
            java.lang.Class<java.io.File> r9 = java.io.File.class
            java.lang.Class<java.nio.ByteBuffer> r11 = java.nio.ByteBuffer.class
            com.bumptech.glide.load.model.ByteBufferFileLoader$Factory r13 = new com.bumptech.glide.load.model.ByteBufferFileLoader$Factory
            r13.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            java.lang.Class<java.io.File> r9 = java.io.File.class
            java.lang.Class<java.io.InputStream> r11 = java.io.InputStream.class
            com.bumptech.glide.load.model.FileLoader$StreamFactory r13 = new com.bumptech.glide.load.model.FileLoader$StreamFactory
            r13.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            java.lang.Class<java.io.File> r9 = java.io.File.class
            java.lang.Class<java.io.File> r11 = java.io.File.class
            com.bumptech.glide.load.resource.file.FileDecoder r13 = new com.bumptech.glide.load.resource.file.FileDecoder
            r13.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            java.lang.Class<java.io.File> r9 = java.io.File.class
            java.lang.Class<android.os.ParcelFileDescriptor> r11 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.FileLoader$FileDescriptorFactory r13 = new com.bumptech.glide.load.model.FileLoader$FileDescriptorFactory
            r13.<init>()
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            java.lang.Class<java.io.File> r9 = java.io.File.class
            java.lang.Class<java.io.File> r11 = java.io.File.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r13 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r4 = r4.append(r9, r11, r13)
            com.bumptech.glide.load.data.InputStreamRewinder$Factory r9 = new com.bumptech.glide.load.data.InputStreamRewinder$Factory
            r9.<init>(r3)
            r4.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r9)
            boolean r4 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.isSupported()
            if (r4 == 0) goto L_0x0214
            com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$Factory r4 = new com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$Factory
            r4.<init>()
            r1.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r4)
        L_0x0214:
            java.lang.Class r4 = java.lang.Integer.TYPE
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            r11 = r23
            com.bumptech.glide.Registry r4 = r1.append(r4, r9, r11)
            java.lang.Class r9 = java.lang.Integer.TYPE
            java.lang.Class<android.os.ParcelFileDescriptor> r13 = android.os.ParcelFileDescriptor.class
            r14 = r22
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r14)
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            java.lang.Class<java.io.InputStream> r13 = java.io.InputStream.class
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r11)
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            java.lang.Class<android.os.ParcelFileDescriptor> r13 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r14)
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            java.lang.Class<android.net.Uri> r13 = android.net.Uri.class
            r15 = r18
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r15)
            java.lang.Class r9 = java.lang.Integer.TYPE
            java.lang.Class<android.content.res.AssetFileDescriptor> r13 = android.content.res.AssetFileDescriptor.class
            r18 = r0
            r0 = r17
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r0)
            java.lang.Class<java.lang.Integer> r9 = java.lang.Integer.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r13 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r0)
            java.lang.Class r9 = java.lang.Integer.TYPE
            java.lang.Class<android.net.Uri> r13 = android.net.Uri.class
            com.bumptech.glide.Registry r4 = r4.append(r9, r13, r15)
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            java.lang.Class<java.io.InputStream> r13 = java.io.InputStream.class
            com.bumptech.glide.load.model.DataUrlLoader$StreamFactory r0 = new com.bumptech.glide.load.model.DataUrlLoader$StreamFactory
            r0.<init>()
            com.bumptech.glide.Registry r0 = r4.append(r9, r13, r0)
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            com.bumptech.glide.load.model.DataUrlLoader$StreamFactory r13 = new com.bumptech.glide.load.model.DataUrlLoader$StreamFactory
            r13.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r4, r9, r13)
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            com.bumptech.glide.load.model.StringLoader$StreamFactory r13 = new com.bumptech.glide.load.model.StringLoader$StreamFactory
            r13.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r4, r9, r13)
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.Class<android.os.ParcelFileDescriptor> r9 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.StringLoader$FileDescriptorFactory r13 = new com.bumptech.glide.load.model.StringLoader$FileDescriptorFactory
            r13.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r4, r9, r13)
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r9 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.StringLoader$AssetFileDescriptorFactory r13 = new com.bumptech.glide.load.model.StringLoader$AssetFileDescriptorFactory
            r13.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r4, r9, r13)
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r9 = java.io.InputStream.class
            com.bumptech.glide.load.model.AssetUriLoader$StreamFactory r13 = new com.bumptech.glide.load.model.AssetUriLoader$StreamFactory
            android.content.res.AssetManager r3 = r27.getAssets()
            r13.<init>(r3)
            com.bumptech.glide.Registry r0 = r0.append(r4, r9, r13)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r4 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.AssetUriLoader$FileDescriptorFactory r9 = new com.bumptech.glide.load.model.AssetUriLoader$FileDescriptorFactory
            android.content.res.AssetManager r13 = r27.getAssets()
            r9.<init>(r13)
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r9)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader$Factory r9 = new com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader$Factory
            r13 = r27
            r9.<init>(r13)
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r9)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader$Factory r9 = new com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader$Factory
            r9.<init>(r13)
            r0.append(r3, r4, r9)
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 29
            if (r0 < r3) goto L_0x02fa
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$InputStreamFactory r4 = new com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$InputStreamFactory
            r4.<init>(r13)
            r1.append(r0, r3, r4)
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<android.os.ParcelFileDescriptor> r3 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$FileDescriptorFactory r4 = new com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$FileDescriptorFactory
            r4.<init>(r13)
            r1.append(r0, r3, r4)
        L_0x02fa:
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.UriLoader$StreamFactory r4 = new com.bumptech.glide.load.model.UriLoader$StreamFactory
            r9 = r21
            r4.<init>(r9)
            com.bumptech.glide.Registry r0 = r1.append(r0, r3, r4)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.os.ParcelFileDescriptor> r4 = android.os.ParcelFileDescriptor.class
            r21 = r6
            com.bumptech.glide.load.model.UriLoader$FileDescriptorFactory r6 = new com.bumptech.glide.load.model.UriLoader$FileDescriptorFactory
            r6.<init>(r9)
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r4 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.UriLoader$AssetFileDescriptorFactory r6 = new com.bumptech.glide.load.model.UriLoader$AssetFileDescriptorFactory
            r6.<init>(r9)
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            com.bumptech.glide.load.model.UrlUriLoader$StreamFactory r6 = new com.bumptech.glide.load.model.UrlUriLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<java.net.URL> r3 = java.net.URL.class
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.UrlLoader$StreamFactory r6 = new com.bumptech.glide.load.model.stream.UrlLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.File> r4 = java.io.File.class
            com.bumptech.glide.load.model.MediaStoreFileLoader$Factory r6 = new com.bumptech.glide.load.model.MediaStoreFileLoader$Factory
            r6.<init>(r13)
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<com.bumptech.glide.load.model.GlideUrl> r3 = com.bumptech.glide.load.model.GlideUrl.class
            java.lang.Class<java.io.InputStream> r4 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.HttpGlideUrlLoader$Factory r6 = new com.bumptech.glide.load.model.stream.HttpGlideUrlLoader$Factory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r6)
            java.lang.Class<java.nio.ByteBuffer> r3 = java.nio.ByteBuffer.class
            com.bumptech.glide.load.model.ByteArrayLoader$ByteBufferFactory r4 = new com.bumptech.glide.load.model.ByteArrayLoader$ByteBufferFactory
            r4.<init>()
            r6 = r16
            com.bumptech.glide.Registry r0 = r0.append(r6, r3, r4)
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.ByteArrayLoader$StreamFactory r4 = new com.bumptech.glide.load.model.ByteArrayLoader$StreamFactory
            r4.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r6, r3, r4)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r16 = r7
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r7)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r7)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder r7 = new com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r4, r7)
            java.lang.Class<android.graphics.Bitmap> r3 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r4 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder r7 = new com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder
            r7.<init>((android.content.res.Resources) r5)
            com.bumptech.glide.Registry r0 = r0.register(r3, r4, r7)
            java.lang.Class<android.graphics.Bitmap> r3 = android.graphics.Bitmap.class
            r4 = r19
            com.bumptech.glide.Registry r0 = r0.register(r3, r6, r4)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder r7 = new com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder
            r19 = r8
            r8 = r20
            r7.<init>(r2, r4, r8)
            com.bumptech.glide.Registry r0 = r0.register(r3, r6, r7)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r3 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r0.register(r3, r6, r8)
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r0 < r3) goto L_0x03df
            com.bumptech.glide.load.ResourceDecoder r0 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.byteBuffer(r29)
            java.lang.Class<java.nio.ByteBuffer> r3 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.Bitmap> r6 = android.graphics.Bitmap.class
            r1.append(r3, r6, r0)
            java.lang.Class<java.nio.ByteBuffer> r3 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r6 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r7 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r7.<init>((android.content.res.Resources) r5, r0)
            r1.append(r3, r6, r7)
        L_0x03df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RegistryFactory.initializeDefaults(android.content.Context, com.bumptech.glide.Registry, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool, com.bumptech.glide.GlideExperiments):void");
    }

    private static void initializeModules(Context context, Glide glide, Registry registry, List<GlideModule> manifestModules, AppGlideModule annotationGeneratedModule) {
        for (GlideModule module : manifestModules) {
            try {
                module.registerComponents(context, glide, registry);
            } catch (AbstractMethodError e) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + module.getClass().getName(), e);
            }
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.registerComponents(context, glide, registry);
        }
    }
}
