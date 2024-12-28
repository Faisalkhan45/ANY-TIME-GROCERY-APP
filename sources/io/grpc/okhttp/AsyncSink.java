package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

final class AsyncSink implements Sink {
    /* access modifiers changed from: private */
    public final Buffer buffer = new Buffer();
    private boolean closed = false;
    private boolean controlFramesExceeded;
    private int controlFramesInWrite;
    /* access modifiers changed from: private */
    public boolean flushEnqueued = false;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final int maxQueuedControlFrames;
    /* access modifiers changed from: private */
    public int queuedControlFrames;
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public Sink sink;
    /* access modifiers changed from: private */
    @Nullable
    public Socket socket;
    /* access modifiers changed from: private */
    public final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    /* access modifiers changed from: private */
    public boolean writeEnqueued = false;

    static /* synthetic */ int access$420(AsyncSink x0, int x1) {
        int i = x0.queuedControlFrames - x1;
        x0.queuedControlFrames = i;
        return i;
    }

    static /* synthetic */ int access$908(AsyncSink x0) {
        int i = x0.controlFramesInWrite;
        x0.controlFramesInWrite = i + 1;
        return i;
    }

    private AsyncSink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler, int maxQueuedControlFrames2) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(executor, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(exceptionHandler, "exceptionHandler");
        this.maxQueuedControlFrames = maxQueuedControlFrames2;
    }

    static AsyncSink sink(SerializingExecutor executor, ExceptionHandlingFrameWriter.TransportExceptionHandler exceptionHandler, int maxQueuedControlFrames2) {
        return new AsyncSink(executor, exceptionHandler, maxQueuedControlFrames2);
    }

    /* access modifiers changed from: package-private */
    public void becomeConnected(Sink sink2, Socket socket2) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink2, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "socket");
    }

    /* access modifiers changed from: package-private */
    public FrameWriter limitControlFramesWriter(FrameWriter delegate) {
        return new LimitControlFramesWriter(delegate);
    }

    /* Debug info: failed to restart local var, previous not found, register: 7 */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        if (r0 == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r7.socket.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r7.serializingExecutor.execute(new io.grpc.okhttp.AsyncSink.AnonymousClass1(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        io.perfmark.PerfMark.stopTask("AsyncSink.write");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(okio.Buffer r8, long r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "source"
            com.google.common.base.Preconditions.checkNotNull(r8, r0)
            boolean r0 = r7.closed
            if (r0 != 0) goto L_0x007c
            java.lang.String r0 = "AsyncSink.write"
            io.perfmark.PerfMark.startTask(r0)
            r0 = 0
            java.lang.Object r1 = r7.lock     // Catch:{ all -> 0x0075 }
            monitor-enter(r1)     // Catch:{ all -> 0x0075 }
            okio.Buffer r2 = r7.buffer     // Catch:{ all -> 0x0072 }
            r2.write((okio.Buffer) r8, (long) r9)     // Catch:{ all -> 0x0072 }
            int r2 = r7.queuedControlFrames     // Catch:{ all -> 0x0072 }
            int r3 = r7.controlFramesInWrite     // Catch:{ all -> 0x0072 }
            int r2 = r2 + r3
            r7.queuedControlFrames = r2     // Catch:{ all -> 0x0072 }
            r3 = 0
            r7.controlFramesInWrite = r3     // Catch:{ all -> 0x0072 }
            boolean r3 = r7.controlFramesExceeded     // Catch:{ all -> 0x0072 }
            r4 = 1
            if (r3 != 0) goto L_0x002e
            int r3 = r7.maxQueuedControlFrames     // Catch:{ all -> 0x0072 }
            if (r2 <= r3) goto L_0x002e
            r7.controlFramesExceeded = r4     // Catch:{ all -> 0x0072 }
            r0 = 1
            goto L_0x0045
        L_0x002e:
            boolean r2 = r7.writeEnqueued     // Catch:{ all -> 0x0072 }
            if (r2 != 0) goto L_0x006b
            boolean r2 = r7.flushEnqueued     // Catch:{ all -> 0x0072 }
            if (r2 != 0) goto L_0x006b
            okio.Buffer r2 = r7.buffer     // Catch:{ all -> 0x0072 }
            long r2 = r2.completeSegmentByteCount()     // Catch:{ all -> 0x0072 }
            r5 = 0
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x0043
            goto L_0x006b
        L_0x0043:
            r7.writeEnqueued = r4     // Catch:{ all -> 0x0072 }
        L_0x0045:
            monitor-exit(r1)     // Catch:{ all -> 0x0072 }
            if (r0 == 0) goto L_0x005a
            java.net.Socket r1 = r7.socket     // Catch:{ IOException -> 0x004e }
            r1.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0054
        L_0x004e:
            r1 = move-exception
            io.grpc.okhttp.ExceptionHandlingFrameWriter$TransportExceptionHandler r2 = r7.transportExceptionHandler     // Catch:{ all -> 0x0075 }
            r2.onException(r1)     // Catch:{ all -> 0x0075 }
        L_0x0054:
            java.lang.String r1 = "AsyncSink.write"
            io.perfmark.PerfMark.stopTask(r1)
            return
        L_0x005a:
            io.grpc.internal.SerializingExecutor r1 = r7.serializingExecutor     // Catch:{ all -> 0x0075 }
            io.grpc.okhttp.AsyncSink$1 r2 = new io.grpc.okhttp.AsyncSink$1     // Catch:{ all -> 0x0075 }
            r2.<init>()     // Catch:{ all -> 0x0075 }
            r1.execute(r2)     // Catch:{ all -> 0x0075 }
            java.lang.String r0 = "AsyncSink.write"
            io.perfmark.PerfMark.stopTask(r0)
            return
        L_0x006b:
            monitor-exit(r1)     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = "AsyncSink.write"
            io.perfmark.PerfMark.stopTask(r1)
            return
        L_0x0072:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0072 }
            throw r2     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r0 = move-exception
            java.lang.String r1 = "AsyncSink.write"
            io.perfmark.PerfMark.stopTask(r1)
            throw r0
        L_0x007c:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "closed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.AsyncSink.write(okio.Buffer, long):void");
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public void flush() throws IOException {
        if (!this.closed) {
            PerfMark.startTask("AsyncSink.flush");
            try {
                synchronized (this.lock) {
                    if (this.flushEnqueued) {
                        PerfMark.stopTask("AsyncSink.flush");
                        return;
                    }
                    this.flushEnqueued = true;
                    this.serializingExecutor.execute(new WriteRunnable() {
                        final Link link = PerfMark.linkOut();

                        /* Debug info: failed to restart local var, previous not found, register: 5 */
                        public void doRun() throws IOException {
                            PerfMark.startTask("WriteRunnable.runFlush");
                            PerfMark.linkIn(this.link);
                            Buffer buf = new Buffer();
                            try {
                                synchronized (AsyncSink.this.lock) {
                                    buf.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                                    boolean unused = AsyncSink.this.flushEnqueued = false;
                                }
                                AsyncSink.this.sink.write(buf, buf.size());
                                AsyncSink.this.sink.flush();
                                PerfMark.stopTask("WriteRunnable.runFlush");
                            } catch (Throwable th) {
                                PerfMark.stopTask("WriteRunnable.runFlush");
                                throw th;
                            }
                        }
                    });
                    PerfMark.stopTask("AsyncSink.flush");
                }
            } catch (Throwable th) {
                PerfMark.stopTask("AsyncSink.flush");
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        if (AsyncSink.this.sink != null && AsyncSink.this.buffer.size() > 0) {
                            AsyncSink.this.sink.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                        }
                    } catch (IOException e) {
                        AsyncSink.this.transportExceptionHandler.onException(e);
                    }
                    AsyncSink.this.buffer.close();
                    try {
                        if (AsyncSink.this.sink != null) {
                            AsyncSink.this.sink.close();
                        }
                    } catch (IOException e2) {
                        AsyncSink.this.transportExceptionHandler.onException(e2);
                    }
                    try {
                        if (AsyncSink.this.socket != null) {
                            AsyncSink.this.socket.close();
                        }
                    } catch (IOException e3) {
                        AsyncSink.this.transportExceptionHandler.onException(e3);
                    }
                }
            });
        }
    }

    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public final void run() {
            try {
                if (AsyncSink.this.sink != null) {
                    doRun();
                    return;
                }
                throw new IOException("Unable to perform write due to unavailable sink.");
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }

    private class LimitControlFramesWriter extends ForwardingFrameWriter {
        public LimitControlFramesWriter(FrameWriter delegate) {
            super(delegate);
        }

        public void ackSettings(Settings peerSettings) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.ackSettings(peerSettings);
        }

        public void rstStream(int streamId, ErrorCode errorCode) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.rstStream(streamId, errorCode);
        }

        public void ping(boolean ack, int payload1, int payload2) throws IOException {
            if (ack) {
                AsyncSink.access$908(AsyncSink.this);
            }
            super.ping(ack, payload1, payload2);
        }
    }
}
