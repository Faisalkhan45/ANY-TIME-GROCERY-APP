package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class ByteBufferBitmapImageDecoderResourceDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
    private final BitmapImageDecoderResourceDecoder wrapped = new BitmapImageDecoderResourceDecoder();

    public boolean handles(ByteBuffer source, Options options) throws IOException {
        return true;
    }

    public Resource<Bitmap> decode(ByteBuffer buffer, int width, int height, Options options) throws IOException {
        return this.wrapped.decode(ImageDecoder.createSource(buffer), width, height, options);
    }
}
