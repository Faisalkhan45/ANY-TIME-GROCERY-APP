package okhttp3.internal.ws;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public RealWebSocket(Request request, WebSocketListener listener2, Random random2, long pingIntervalMillis2) {
        if (ShareTarget.METHOD_GET.equals(request.method())) {
            this.originalRequest = request;
            this.listener = listener2;
            this.random = random2;
            this.pingIntervalMillis = pingIntervalMillis2;
            byte[] nonce = new byte[16];
            random2.nextBytes(nonce);
            this.key = ByteString.of(nonce).base64();
            this.writerRunnable = new Runnable() {
                public void run() {
                    do {
                        try {
                        } catch (IOException e) {
                            RealWebSocket.this.failWebSocket(e, (Response) null);
                            return;
                        }
                    } while (RealWebSocket.this.writeOneFrame());
                }
            };
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    public Request request() {
        return this.originalRequest;
    }

    public synchronized long queueSize() {
        return this.queueSize;
    }

    public void cancel() {
        this.call.cancel();
    }

    public void connect(OkHttpClient client) {
        OkHttpClient client2 = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request request = this.originalRequest.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header(HttpHeaders.SEC_WEBSOCKET_KEY, this.key).header(HttpHeaders.SEC_WEBSOCKET_VERSION, "13").build();
        Call newWebSocketCall = Internal.instance.newWebSocketCall(client2, request);
        this.call = newWebSocketCall;
        newWebSocketCall.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + request.url().redact(), streams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, (Response) null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly((Closeable) response);
                }
            }

            public void onFailure(Call call, IOException e) {
                RealWebSocket.this.failWebSocket(e, (Response) null);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() == 101) {
            String headerConnection = response.header(HttpHeaders.CONNECTION);
            if (HttpHeaders.UPGRADE.equalsIgnoreCase(headerConnection)) {
                String headerUpgrade = response.header(HttpHeaders.UPGRADE);
                if ("websocket".equalsIgnoreCase(headerUpgrade)) {
                    String headerAccept = response.header(HttpHeaders.SEC_WEBSOCKET_ACCEPT);
                    String acceptExpected = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
                    if (!acceptExpected.equals(headerAccept)) {
                        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + "'");
                    }
                    return;
                }
                throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + "'");
            }
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + "'");
        }
        throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
    }

    public void initReaderAndWriter(String name, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(name, false));
            this.executor = scheduledThreadPoolExecutor;
            if (this.pingIntervalMillis != 0) {
                PingRunnable pingRunnable = new PingRunnable();
                long j = this.pingIntervalMillis;
                scheduledThreadPoolExecutor.scheduleAtFixedRate(pingRunnable, j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void awaitTermination(int timeout, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination((long) timeout, timeUnit);
    }

    /* access modifiers changed from: package-private */
    public void tearDown() throws InterruptedException {
        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    public synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public void onReadMessage(String text) throws IOException {
        this.listener.onMessage((WebSocket) this, text);
    }

    public void onReadMessage(ByteString bytes) throws IOException {
        this.listener.onMessage((WebSocket) this, bytes);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.failed     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0022
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0024 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0012
            goto L_0x0022
        L_0x0012:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0024 }
            r0.add(r2)     // Catch:{ all -> 0x0024 }
            r1.runWriter()     // Catch:{ all -> 0x0024 }
            int r0 = r1.receivedPingCount     // Catch:{ all -> 0x0024 }
            int r0 = r0 + 1
            r1.receivedPingCount = r0     // Catch:{ all -> 0x0024 }
            monitor-exit(r1)
            return
        L_0x0022:
            monitor-exit(r1)
            return
        L_0x0024:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString buffer) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public void onReadClose(int code, String reason) {
        if (code != -1) {
            Streams toClose = null;
            synchronized (this) {
                if (this.receivedCloseCode == -1) {
                    this.receivedCloseCode = code;
                    this.receivedCloseReason = reason;
                    if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                        toClose = this.streams;
                        this.streams = null;
                        ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                        if (scheduledFuture != null) {
                            scheduledFuture.cancel(false);
                        }
                        this.executor.shutdown();
                    }
                } else {
                    throw new IllegalStateException("already closed");
                }
            }
            try {
                this.listener.onClosing(this, code, reason);
                if (toClose != null) {
                    this.listener.onClosed(this, code, reason);
                }
            } finally {
                Util.closeQuietly((Closeable) toClose);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean send(String text) {
        if (text != null) {
            return send(ByteString.encodeUtf8(text), 1);
        }
        throw new NullPointerException("text == null");
    }

    public boolean send(ByteString bytes) {
        if (bytes != null) {
            return send(bytes, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean send(okio.ByteString r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.failed     // Catch:{ all -> 0x003e }
            r1 = 0
            if (r0 != 0) goto L_0x003c
            boolean r0 = r6.enqueuedClose     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000b
            goto L_0x003c
        L_0x000b:
            long r2 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r0 = r7.size()     // Catch:{ all -> 0x003e }
            long r4 = (long) r0     // Catch:{ all -> 0x003e }
            long r2 = r2 + r4
            r4 = 16777216(0x1000000, double:8.289046E-317)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0022
            r0 = 1001(0x3e9, float:1.403E-42)
            r2 = 0
            r6.close(r0, r2)     // Catch:{ all -> 0x003e }
            monitor-exit(r6)
            return r1
        L_0x0022:
            long r0 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r2 = r7.size()     // Catch:{ all -> 0x003e }
            long r2 = (long) r2     // Catch:{ all -> 0x003e }
            long r0 = r0 + r2
            r6.queueSize = r0     // Catch:{ all -> 0x003e }
            java.util.ArrayDeque<java.lang.Object> r0 = r6.messageAndCloseQueue     // Catch:{ all -> 0x003e }
            okhttp3.internal.ws.RealWebSocket$Message r1 = new okhttp3.internal.ws.RealWebSocket$Message     // Catch:{ all -> 0x003e }
            r1.<init>(r8, r7)     // Catch:{ all -> 0x003e }
            r0.add(r1)     // Catch:{ all -> 0x003e }
            r6.runWriter()     // Catch:{ all -> 0x003e }
            r0 = 1
            monitor-exit(r6)
            return r0
        L_0x003c:
            monitor-exit(r6)
            return r1
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.send(okio.ByteString, int):boolean");
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean pong(ByteString payload) {
        if (!this.failed) {
            if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                this.pongQueue.add(payload);
                runWriter();
                return true;
            }
        }
        return false;
    }

    public boolean close(int code, String reason) {
        return close(code, reason, 60000);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean close(int code, String reason, long cancelAfterCloseMillis) {
        WebSocketProtocol.validateCloseCode(code);
        ByteString reasonBytes = null;
        if (reason != null) {
            reasonBytes = ByteString.encodeUtf8(reason);
            if (((long) reasonBytes.size()) > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + reason);
            }
        }
        if (!this.failed) {
            if (!this.enqueuedClose) {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
                runWriter();
                return true;
            }
        }
        return false;
    }

    private void runWriter() {
        if (Thread.holdsLock(this)) {
            ScheduledExecutorService scheduledExecutorService = this.executor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.execute(this.writerRunnable);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* Debug info: failed to restart local var, previous not found, register: 12 */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: okhttp3.internal.ws.RealWebSocket$Close} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: okhttp3.internal.ws.RealWebSocket$Message} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: okhttp3.internal.ws.RealWebSocket$Close} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: okhttp3.internal.ws.RealWebSocket$Message} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: okhttp3.internal.ws.RealWebSocket$Close} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r6 == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.writePong(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        if ((r0 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
        r5 = r0.data;
        r7 = okio.Okio.buffer(r4.newMessageSink(r0.formatOpcode, (long) r5.size()));
        r7.write(r5);
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
        monitor-enter(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r12.queueSize -= (long) r5.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0084, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
        if ((r0 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008d, code lost:
        r5 = (okhttp3.internal.ws.RealWebSocket.Close) r0;
        r4.writeClose(r5.code, r5.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0097, code lost:
        if (r3 == null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0099, code lost:
        r12.listener.onClosed(r12, r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a3, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a9, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00aa, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ab, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ae, code lost:
        throw r5;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeOneFrame() throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            r1 = -1
            r2 = 0
            r3 = 0
            monitor-enter(r12)
            boolean r4 = r12.failed     // Catch:{ all -> 0x00af }
            r5 = 0
            if (r4 == 0) goto L_0x000c
            monitor-exit(r12)     // Catch:{ all -> 0x00af }
            return r5
        L_0x000c:
            okhttp3.internal.ws.WebSocketWriter r4 = r12.writer     // Catch:{ all -> 0x00af }
            java.util.ArrayDeque<okio.ByteString> r6 = r12.pongQueue     // Catch:{ all -> 0x00af }
            java.lang.Object r6 = r6.poll()     // Catch:{ all -> 0x00af }
            okio.ByteString r6 = (okio.ByteString) r6     // Catch:{ all -> 0x00af }
            if (r6 != 0) goto L_0x0051
            java.util.ArrayDeque<java.lang.Object> r7 = r12.messageAndCloseQueue     // Catch:{ all -> 0x00af }
            java.lang.Object r7 = r7.poll()     // Catch:{ all -> 0x00af }
            r0 = r7
            boolean r7 = r0 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x004d
            int r5 = r12.receivedCloseCode     // Catch:{ all -> 0x00af }
            r1 = r5
            java.lang.String r5 = r12.receivedCloseReason     // Catch:{ all -> 0x00af }
            r2 = r5
            r5 = -1
            if (r1 == r5) goto L_0x0038
            okhttp3.internal.ws.RealWebSocket$Streams r5 = r12.streams     // Catch:{ all -> 0x00af }
            r3 = r5
            r5 = 0
            r12.streams = r5     // Catch:{ all -> 0x00af }
            java.util.concurrent.ScheduledExecutorService r5 = r12.executor     // Catch:{ all -> 0x00af }
            r5.shutdown()     // Catch:{ all -> 0x00af }
            goto L_0x0051
        L_0x0038:
            java.util.concurrent.ScheduledExecutorService r5 = r12.executor     // Catch:{ all -> 0x00af }
            okhttp3.internal.ws.RealWebSocket$CancelRunnable r7 = new okhttp3.internal.ws.RealWebSocket$CancelRunnable     // Catch:{ all -> 0x00af }
            r7.<init>()     // Catch:{ all -> 0x00af }
            r8 = r0
            okhttp3.internal.ws.RealWebSocket$Close r8 = (okhttp3.internal.ws.RealWebSocket.Close) r8     // Catch:{ all -> 0x00af }
            long r8 = r8.cancelAfterCloseMillis     // Catch:{ all -> 0x00af }
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00af }
            java.util.concurrent.ScheduledFuture r5 = r5.schedule(r7, r8, r10)     // Catch:{ all -> 0x00af }
            r12.cancelFuture = r5     // Catch:{ all -> 0x00af }
            goto L_0x0051
        L_0x004d:
            if (r0 != 0) goto L_0x0051
            monitor-exit(r12)     // Catch:{ all -> 0x00af }
            return r5
        L_0x0051:
            monitor-exit(r12)     // Catch:{ all -> 0x00af }
            if (r6 == 0) goto L_0x0058
            r4.writePong(r6)     // Catch:{ all -> 0x00aa }
            goto L_0x009f
        L_0x0058:
            boolean r5 = r0 instanceof okhttp3.internal.ws.RealWebSocket.Message     // Catch:{ all -> 0x00aa }
            if (r5 == 0) goto L_0x0089
            r5 = r0
            okhttp3.internal.ws.RealWebSocket$Message r5 = (okhttp3.internal.ws.RealWebSocket.Message) r5     // Catch:{ all -> 0x00aa }
            okio.ByteString r5 = r5.data     // Catch:{ all -> 0x00aa }
            r7 = r0
            okhttp3.internal.ws.RealWebSocket$Message r7 = (okhttp3.internal.ws.RealWebSocket.Message) r7     // Catch:{ all -> 0x00aa }
            int r7 = r7.formatOpcode     // Catch:{ all -> 0x00aa }
            int r8 = r5.size()     // Catch:{ all -> 0x00aa }
            long r8 = (long) r8     // Catch:{ all -> 0x00aa }
            okio.Sink r7 = r4.newMessageSink(r7, r8)     // Catch:{ all -> 0x00aa }
            okio.BufferedSink r7 = okio.Okio.buffer((okio.Sink) r7)     // Catch:{ all -> 0x00aa }
            r7.write((okio.ByteString) r5)     // Catch:{ all -> 0x00aa }
            r7.close()     // Catch:{ all -> 0x00aa }
            monitor-enter(r12)     // Catch:{ all -> 0x00aa }
            long r8 = r12.queueSize     // Catch:{ all -> 0x0086 }
            int r10 = r5.size()     // Catch:{ all -> 0x0086 }
            long r10 = (long) r10     // Catch:{ all -> 0x0086 }
            long r8 = r8 - r10
            r12.queueSize = r8     // Catch:{ all -> 0x0086 }
            monitor-exit(r12)     // Catch:{ all -> 0x0086 }
            goto L_0x009f
        L_0x0086:
            r8 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0086 }
            throw r8     // Catch:{ all -> 0x00aa }
        L_0x0089:
            boolean r5 = r0 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x00aa }
            if (r5 == 0) goto L_0x00a4
            r5 = r0
            okhttp3.internal.ws.RealWebSocket$Close r5 = (okhttp3.internal.ws.RealWebSocket.Close) r5     // Catch:{ all -> 0x00aa }
            int r7 = r5.code     // Catch:{ all -> 0x00aa }
            okio.ByteString r8 = r5.reason     // Catch:{ all -> 0x00aa }
            r4.writeClose(r7, r8)     // Catch:{ all -> 0x00aa }
            if (r3 == 0) goto L_0x009e
            okhttp3.WebSocketListener r7 = r12.listener     // Catch:{ all -> 0x00aa }
            r7.onClosed(r12, r1, r2)     // Catch:{ all -> 0x00aa }
        L_0x009e:
        L_0x009f:
            r5 = 1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
            return r5
        L_0x00a4:
            java.lang.AssertionError r5 = new java.lang.AssertionError     // Catch:{ all -> 0x00aa }
            r5.<init>()     // Catch:{ all -> 0x00aa }
            throw r5     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r5 = move-exception
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
            throw r5
        L_0x00af:
            r4 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00af }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame():boolean");
    }

    private final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r1 == -1) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        failWebSocket(new java.net.SocketTimeoutException("sent ping but didn't receive pong within " + r7.pingIntervalMillis + "ms (after " + (r1 - 1) + " successful ping/pongs)"), (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        failWebSocket(r2, (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePingFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.failed     // Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)     // Catch:{ all -> 0x0059 }
            return
        L_0x0007:
            okhttp3.internal.ws.WebSocketWriter r0 = r7.writer     // Catch:{ all -> 0x0059 }
            boolean r1 = r7.awaitingPong     // Catch:{ all -> 0x0059 }
            r2 = -1
            if (r1 == 0) goto L_0x0011
            int r1 = r7.sentPingCount     // Catch:{ all -> 0x0059 }
            goto L_0x0012
        L_0x0011:
            r1 = r2
        L_0x0012:
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x0059 }
            r4 = 1
            int r3 = r3 + r4
            r7.sentPingCount = r3     // Catch:{ all -> 0x0059 }
            r7.awaitingPong = r4     // Catch:{ all -> 0x0059 }
            monitor-exit(r7)     // Catch:{ all -> 0x0059 }
            r3 = 0
            if (r1 == r2) goto L_0x004e
            java.net.SocketTimeoutException r2 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            java.lang.StringBuilder r4 = r4.append(r5)
            long r5 = r7.pingIntervalMillis
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = "ms (after "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r1 + -1
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " successful ping/pongs)"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.<init>(r4)
            r7.failWebSocket(r2, r3)
            return
        L_0x004e:
            okio.ByteString r2 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x0054 }
            r0.writePing(r2)     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r2 = move-exception
            r7.failWebSocket(r2, r3)
        L_0x0058:
            return
        L_0x0059:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0059 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writePingFrame():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.listener.onFailure(r3, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failWebSocket(java.lang.Exception r4, @javax.annotation.Nullable okhttp3.Response r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.failed     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x002e }
            return
        L_0x0007:
            r0 = 1
            r3.failed = r0     // Catch:{ all -> 0x002e }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r3.streams     // Catch:{ all -> 0x002e }
            r1 = 0
            r3.streams = r1     // Catch:{ all -> 0x002e }
            java.util.concurrent.ScheduledFuture<?> r1 = r3.cancelFuture     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0017
            r2 = 0
            r1.cancel(r2)     // Catch:{ all -> 0x002e }
        L_0x0017:
            java.util.concurrent.ScheduledExecutorService r1 = r3.executor     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x001e
            r1.shutdown()     // Catch:{ all -> 0x002e }
        L_0x001e:
            monitor-exit(r3)     // Catch:{ all -> 0x002e }
            okhttp3.WebSocketListener r1 = r3.listener     // Catch:{ all -> 0x0029 }
            r1.onFailure(r3, r4, r5)     // Catch:{ all -> 0x0029 }
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            return
        L_0x0029:
            r1 = move-exception
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
            throw r1
        L_0x002e:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }

    static final class Message {
        final ByteString data;
        final int formatOpcode;

        Message(int formatOpcode2, ByteString data2) {
            this.formatOpcode = formatOpcode2;
            this.data = data2;
        }
    }

    static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;

        Close(int code2, ByteString reason2, long cancelAfterCloseMillis2) {
            this.code = code2;
            this.reason = reason2;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis2;
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean client2, BufferedSource source2, BufferedSink sink2) {
            this.client = client2;
            this.source = source2;
            this.sink = sink2;
        }
    }

    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }
}
