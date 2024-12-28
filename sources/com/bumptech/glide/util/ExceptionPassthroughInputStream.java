package com.bumptech.glide.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public final class ExceptionPassthroughInputStream extends InputStream {
    private static final Queue<ExceptionPassthroughInputStream> POOL = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    public static ExceptionPassthroughInputStream obtain(InputStream toWrap) {
        ExceptionPassthroughInputStream result;
        Queue<ExceptionPassthroughInputStream> queue = POOL;
        synchronized (queue) {
            result = queue.poll();
        }
        if (result == null) {
            result = new ExceptionPassthroughInputStream();
        }
        result.setInputStream(toWrap);
        return result;
    }

    static void clearQueue() {
        synchronized (POOL) {
            while (true) {
                Queue<ExceptionPassthroughInputStream> queue = POOL;
                if (!queue.isEmpty()) {
                    queue.remove();
                }
            }
        }
    }

    ExceptionPassthroughInputStream() {
    }

    /* access modifiers changed from: package-private */
    public void setInputStream(InputStream toWrap) {
        this.wrapped = toWrap;
    }

    public int available() throws IOException {
        return this.wrapped.available();
    }

    public void close() throws IOException {
        this.wrapped.close();
    }

    public void mark(int readLimit) {
        this.wrapped.mark(readLimit);
    }

    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    public int read() throws IOException {
        try {
            return this.wrapped.read();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    public int read(byte[] buffer) throws IOException {
        try {
            return this.wrapped.read(buffer);
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        try {
            return this.wrapped.read(buffer, byteOffset, byteCount);
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    public synchronized void reset() throws IOException {
        this.wrapped.reset();
    }

    public long skip(long byteCount) throws IOException {
        try {
            return this.wrapped.skip(byteCount);
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    public IOException getException() {
        return this.exception;
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        Queue<ExceptionPassthroughInputStream> queue = POOL;
        synchronized (queue) {
            queue.offer(this);
        }
    }
}
