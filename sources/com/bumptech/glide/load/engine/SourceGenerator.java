package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

class SourceGenerator implements DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {
    private static final String TAG = "SourceGenerator";
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private volatile Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private volatile int loadDataListIndex;
    private volatile DataCacheKey originalKey;
    private volatile DataCacheGenerator sourceCacheGenerator;

    SourceGenerator(DecodeHelper<?> helper2, DataFetcherGenerator.FetcherReadyCallback cb2) {
        this.helper = helper2;
        this.cb = cb2;
    }

    public boolean startNext() {
        if (this.dataToCache != null) {
            Object data = this.dataToCache;
            this.dataToCache = null;
            try {
                if (!cacheData(data)) {
                    return true;
                }
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to properly rewind or write data to cache", e);
                }
            }
        }
        if (this.sourceCacheGenerator != null && this.sourceCacheGenerator.startNext()) {
            return true;
        }
        this.sourceCacheGenerator = null;
        this.loadData = null;
        boolean started = false;
        while (!started && hasNextModelLoader()) {
            List<ModelLoader.LoadData<?>> loadData2 = this.helper.getLoadData();
            int i = this.loadDataListIndex;
            this.loadDataListIndex = i + 1;
            this.loadData = loadData2.get(i);
            if (this.loadData != null && (this.helper.getDiskCacheStrategy().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.hasLoadPath(this.loadData.fetcher.getDataClass()))) {
                started = true;
                startNextLoad(this.loadData);
            }
        }
        return started;
    }

    private void startNextLoad(final ModelLoader.LoadData<?> toStart) {
        this.loadData.fetcher.loadData(this.helper.getPriority(), new DataFetcher.DataCallback<Object>() {
            public void onDataReady(Object data) {
                if (SourceGenerator.this.isCurrentRequest(toStart)) {
                    SourceGenerator.this.onDataReadyInternal(toStart, data);
                }
            }

            public void onLoadFailed(Exception e) {
                if (SourceGenerator.this.isCurrentRequest(toStart)) {
                    SourceGenerator.this.onLoadFailedInternal(toStart, e);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean isCurrentRequest(ModelLoader.LoadData<?> requestLoadData) {
        ModelLoader.LoadData<?> currentLoadData = this.loadData;
        return currentLoadData != null && currentLoadData == requestLoadData;
    }

    private boolean hasNextModelLoader() {
        return this.loadDataListIndex < this.helper.getLoadData().size();
    }

    private boolean cacheData(Object dataToCache2) throws IOException {
        Object obj = dataToCache2;
        long startTime = LogTime.getLogTime();
        try {
            DataRewinder<T> rewinder = this.helper.getRewinder(obj);
            Object data = rewinder.rewindAndGet();
            Encoder<X> sourceEncoder = this.helper.getSourceEncoder(data);
            DataCacheWriter<Object> writer = new DataCacheWriter<>(sourceEncoder, data, this.helper.getOptions());
            DataCacheKey newOriginalKey = new DataCacheKey(this.loadData.sourceKey, this.helper.getSignature());
            DiskCache diskCache = this.helper.getDiskCache();
            diskCache.put(newOriginalKey, writer);
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Finished encoding source to cache, key: " + newOriginalKey + ", data: " + obj + ", encoder: " + sourceEncoder + ", duration: " + LogTime.getElapsedMillis(startTime));
            }
            if (diskCache.get(newOriginalKey) != null) {
                this.originalKey = newOriginalKey;
                this.sourceCacheGenerator = new DataCacheGenerator(Collections.singletonList(this.loadData.sourceKey), this.helper, this);
                return true;
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Attempt to write: " + this.originalKey + ", data: " + obj + " to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly...");
            }
            this.cb.onDataFetcherReady(this.loadData.sourceKey, rewinder.rewindAndGet(), this.loadData.fetcher, this.loadData.fetcher.getDataSource(), this.loadData.sourceKey);
            if (1 == 0) {
                this.loadData.fetcher.cleanup();
            }
            return false;
        } finally {
            if (0 == 0) {
                this.loadData.fetcher.cleanup();
            }
        }
    }

    public void cancel() {
        ModelLoader.LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void onDataReadyInternal(ModelLoader.LoadData<?> loadData2, Object data) {
        DiskCacheStrategy diskCacheStrategy = this.helper.getDiskCacheStrategy();
        if (data == null || !diskCacheStrategy.isDataCacheable(loadData2.fetcher.getDataSource())) {
            this.cb.onDataFetcherReady(loadData2.sourceKey, data, loadData2.fetcher, loadData2.fetcher.getDataSource(), this.originalKey);
            return;
        }
        this.dataToCache = data;
        this.cb.reschedule();
    }

    /* access modifiers changed from: package-private */
    public void onLoadFailedInternal(ModelLoader.LoadData<?> loadData2, Exception e) {
        this.cb.onDataFetcherFailed(this.originalKey, e, loadData2.fetcher, loadData2.fetcher.getDataSource());
    }

    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    public void onDataFetcherReady(Key sourceKey, Object data, DataFetcher<?> fetcher, DataSource dataSource, Key attemptedKey) {
        this.cb.onDataFetcherReady(sourceKey, data, fetcher, this.loadData.fetcher.getDataSource(), sourceKey);
    }

    public void onDataFetcherFailed(Key sourceKey, Exception e, DataFetcher<?> fetcher, DataSource dataSource) {
        this.cb.onDataFetcherFailed(sourceKey, e, fetcher, this.loadData.fetcher.getDataSource());
    }
}
