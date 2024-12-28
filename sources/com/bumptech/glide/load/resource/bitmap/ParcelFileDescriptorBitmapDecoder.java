package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

public final class ParcelFileDescriptorBitmapDecoder implements ResourceDecoder<ParcelFileDescriptor, Bitmap> {
    private static final int MAXIMUM_FILE_BYTE_SIZE_FOR_FILE_DESCRIPTOR_DECODER = 536870912;
    private final Downsampler downsampler;

    public ParcelFileDescriptorBitmapDecoder(Downsampler downsampler2) {
        this.downsampler = downsampler2;
    }

    public boolean handles(ParcelFileDescriptor source, Options options) {
        return isSafeToTryDecoding(source) && this.downsampler.handles(source);
    }

    private boolean isSafeToTryDecoding(ParcelFileDescriptor source) {
        if (("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER)) && source.getStatSize() > 536870912) {
            return false;
        }
        return true;
    }

    public Resource<Bitmap> decode(ParcelFileDescriptor source, int width, int height, Options options) throws IOException {
        return this.downsampler.decode(source, width, height, options);
    }
}
