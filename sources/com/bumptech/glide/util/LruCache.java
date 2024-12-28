package com.bumptech.glide.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<T, Y> {
    private final Map<T, Entry<Y>> cache = new LinkedHashMap(100, 0.75f, true);
    private long currentSize;
    private final long initialMaxSize;
    private long maxSize;

    public LruCache(long size) {
        this.initialMaxSize = size;
        this.maxSize = size;
    }

    public synchronized void setSizeMultiplier(float multiplier) {
        if (multiplier >= 0.0f) {
            this.maxSize = (long) Math.round(((float) this.initialMaxSize) * multiplier);
            evict();
        } else {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
    }

    /* access modifiers changed from: protected */
    public int getSize(Y y) {
        return 1;
    }

    /* access modifiers changed from: protected */
    public synchronized int getCount() {
        return this.cache.size();
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(T t, Y y) {
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized long getCurrentSize() {
        return this.currentSize;
    }

    public synchronized boolean contains(T key) {
        return this.cache.containsKey(key);
    }

    public synchronized Y get(T key) {
        Entry<Y> entry;
        entry = this.cache.get(key);
        return entry != null ? entry.value : null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized Y put(T r8, Y r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            int r0 = r7.getSize(r9)     // Catch:{ all -> 0x004b }
            long r1 = (long) r0     // Catch:{ all -> 0x004b }
            long r3 = r7.maxSize     // Catch:{ all -> 0x004b }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r2 = 0
            if (r1 < 0) goto L_0x0012
            r7.onItemEvicted(r8, r9)     // Catch:{ all -> 0x004b }
            monitor-exit(r7)
            return r2
        L_0x0012:
            if (r9 == 0) goto L_0x001a
            long r3 = r7.currentSize     // Catch:{ all -> 0x004b }
            long r5 = (long) r0     // Catch:{ all -> 0x004b }
            long r3 = r3 + r5
            r7.currentSize = r3     // Catch:{ all -> 0x004b }
        L_0x001a:
            java.util.Map<T, com.bumptech.glide.util.LruCache$Entry<Y>> r1 = r7.cache     // Catch:{ all -> 0x004b }
            if (r9 != 0) goto L_0x0020
            r3 = r2
            goto L_0x0025
        L_0x0020:
            com.bumptech.glide.util.LruCache$Entry r3 = new com.bumptech.glide.util.LruCache$Entry     // Catch:{ all -> 0x004b }
            r3.<init>(r9, r0)     // Catch:{ all -> 0x004b }
        L_0x0025:
            java.lang.Object r1 = r1.put(r8, r3)     // Catch:{ all -> 0x004b }
            com.bumptech.glide.util.LruCache$Entry r1 = (com.bumptech.glide.util.LruCache.Entry) r1     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0042
            long r3 = r7.currentSize     // Catch:{ all -> 0x004b }
            int r5 = r1.size     // Catch:{ all -> 0x004b }
            long r5 = (long) r5     // Catch:{ all -> 0x004b }
            long r3 = r3 - r5
            r7.currentSize = r3     // Catch:{ all -> 0x004b }
            Y r3 = r1.value     // Catch:{ all -> 0x004b }
            boolean r3 = r3.equals(r9)     // Catch:{ all -> 0x004b }
            if (r3 != 0) goto L_0x0042
            Y r3 = r1.value     // Catch:{ all -> 0x004b }
            r7.onItemEvicted(r8, r3)     // Catch:{ all -> 0x004b }
        L_0x0042:
            r7.evict()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0049
            Y r2 = r1.value     // Catch:{ all -> 0x004b }
        L_0x0049:
            monitor-exit(r7)
            return r2
        L_0x004b:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.util.LruCache.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public synchronized Y remove(T key) {
        Entry<Y> entry = this.cache.remove(key);
        if (entry == null) {
            return null;
        }
        this.currentSize -= (long) entry.size;
        return entry.value;
    }

    public void clearMemory() {
        trimToSize(0);
    }

    /* access modifiers changed from: protected */
    public synchronized void trimToSize(long size) {
        while (this.currentSize > size) {
            Iterator<Map.Entry<T, Entry<Y>>> cacheIterator = this.cache.entrySet().iterator();
            Map.Entry<T, Entry<Y>> last = cacheIterator.next();
            Entry<Y> toRemove = last.getValue();
            this.currentSize -= (long) toRemove.size;
            T key = last.getKey();
            cacheIterator.remove();
            onItemEvicted(key, toRemove.value);
        }
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    static final class Entry<Y> {
        final int size;
        final Y value;

        Entry(Y value2, int size2) {
            this.value = value2;
            this.size = size2;
        }
    }
}
