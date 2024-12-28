package com.bumptech.glide.load.data;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import java.io.IOException;

public class FileDescriptorAssetPathFetcher extends AssetPathFetcher<AssetFileDescriptor> {
    public FileDescriptorAssetPathFetcher(AssetManager assetManager, String assetPath) {
        super(assetManager, assetPath);
    }

    /* access modifiers changed from: protected */
    public AssetFileDescriptor loadResource(AssetManager assetManager, String path) throws IOException {
        return assetManager.openFd(path);
    }

    /* access modifiers changed from: protected */
    public void close(AssetFileDescriptor data) throws IOException {
        data.close();
    }

    public Class<AssetFileDescriptor> getDataClass() {
        return AssetFileDescriptor.class;
    }
}
