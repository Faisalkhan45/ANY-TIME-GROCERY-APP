package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.util.List;

class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private ResourceCacheKey currentKey;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int resourceClassIndex = -1;
    private int sourceIdIndex;
    private Key sourceKey;

    ResourceCacheGenerator(DecodeHelper<?> helper2, DataFetcherGenerator.FetcherReadyCallback cb2) {
        this.helper = helper2;
        this.cb = cb2;
    }

    /* Debug info: failed to restart local var, previous not found, register: 15 */
    public boolean startNext() {
        GlideTrace.beginSection("ResourceCacheGenerator.startNext");
        try {
            List<Key> sourceIds = this.helper.getCacheKeys();
            if (sourceIds.isEmpty()) {
                return false;
            }
            List<Class<?>> resourceClasses = this.helper.getRegisteredResourceClasses();
            if (!resourceClasses.isEmpty()) {
                while (true) {
                    if (this.modelLoaders != null) {
                        if (hasNextModelLoader()) {
                            this.loadData = null;
                            boolean started = false;
                            while (!started && hasNextModelLoader()) {
                                List<ModelLoader<File, ?>> list = this.modelLoaders;
                                int i = this.modelLoaderIndex;
                                this.modelLoaderIndex = i + 1;
                                this.loadData = list.get(i).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                                if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                                    started = true;
                                    this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                                }
                            }
                            GlideTrace.endSection();
                            return started;
                        }
                    }
                    int i2 = this.resourceClassIndex + 1;
                    this.resourceClassIndex = i2;
                    if (i2 >= resourceClasses.size()) {
                        int i3 = this.sourceIdIndex + 1;
                        this.sourceIdIndex = i3;
                        if (i3 >= sourceIds.size()) {
                            GlideTrace.endSection();
                            return false;
                        }
                        this.resourceClassIndex = 0;
                    }
                    Key sourceId = sourceIds.get(this.sourceIdIndex);
                    Class<?> resourceClass = resourceClasses.get(this.resourceClassIndex);
                    Key key = sourceId;
                    this.currentKey = new ResourceCacheKey(this.helper.getArrayPool(), key, this.helper.getSignature(), this.helper.getWidth(), this.helper.getHeight(), this.helper.getTransformation(resourceClass), resourceClass, this.helper.getOptions());
                    File file = this.helper.getDiskCache().get(this.currentKey);
                    this.cacheFile = file;
                    if (file != null) {
                        this.sourceKey = sourceId;
                        this.modelLoaders = this.helper.getModelLoaders(file);
                        this.modelLoaderIndex = 0;
                    }
                }
            } else if (File.class.equals(this.helper.getTranscodeClass())) {
                GlideTrace.endSection();
                return false;
            } else {
                throw new IllegalStateException("Failed to find any load path from " + this.helper.getModelClass() + " to " + this.helper.getTranscodeClass());
            }
        } finally {
            GlideTrace.endSection();
        }
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    public void cancel() {
        ModelLoader.LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    public void onDataReady(Object data) {
        this.cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    public void onLoadFailed(Exception e) {
        this.cb.onDataFetcherFailed(this.currentKey, e, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }
}
