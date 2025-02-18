package okhttp3.internal.http;

import androidx.browser.trusted.sharing.ShareTarget;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.StreamAllocation;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private volatile StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient client2, boolean forWebSocket2) {
        this.client = client2;
        this.forWebSocket = forWebSocket2;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation2 = this.streamAllocation;
        if (streamAllocation2 != null) {
            streamAllocation2.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object callStackTrace2) {
        this.callStackTrace = callStackTrace2;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    /* Debug info: failed to restart local var, previous not found, register: 19 */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0158  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r20) throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            okhttp3.Request r0 = r20.request()
            r2 = r20
            okhttp3.internal.http.RealInterceptorChain r2 = (okhttp3.internal.http.RealInterceptorChain) r2
            okhttp3.Call r9 = r2.call()
            okhttp3.EventListener r10 = r2.eventListener()
            okhttp3.internal.connection.StreamAllocation r11 = new okhttp3.internal.connection.StreamAllocation
            okhttp3.OkHttpClient r3 = r1.client
            okhttp3.ConnectionPool r4 = r3.connectionPool()
            okhttp3.HttpUrl r3 = r0.url()
            okhttp3.Address r5 = r1.createAddress(r3)
            java.lang.Object r8 = r1.callStackTrace
            r3 = r11
            r6 = r9
            r7 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            r1.streamAllocation = r3
            r4 = 0
            r5 = 0
            r11 = r0
            r12 = r3
            r13 = r5
        L_0x0031:
            boolean r0 = r1.canceled
            if (r0 != 0) goto L_0x015f
            r3 = 1
            r5 = 0
            r6 = 0
            okhttp3.Response r0 = r2.proceed(r11, r12, r6, r6)     // Catch:{ RouteException -> 0x0134, IOException -> 0x0118, all -> 0x0114 }
            r14 = 0
            if (r14 == 0) goto L_0x0045
            r12.streamFailed(r6)
            r12.release()
        L_0x0045:
            if (r13 == 0) goto L_0x005f
            okhttp3.Response$Builder r3 = r0.newBuilder()
            okhttp3.Response$Builder r5 = r13.newBuilder()
            okhttp3.Response$Builder r5 = r5.body(r6)
            okhttp3.Response r5 = r5.build()
            okhttp3.Response$Builder r3 = r3.priorResponse(r5)
            okhttp3.Response r0 = r3.build()
        L_0x005f:
            okhttp3.Route r3 = r12.route()
            okhttp3.Request r15 = r1.followUpRequest(r0, r3)
            if (r15 != 0) goto L_0x0071
            boolean r3 = r1.forWebSocket
            if (r3 != 0) goto L_0x0070
            r12.release()
        L_0x0070:
            return r0
        L_0x0071:
            okhttp3.ResponseBody r3 = r0.body()
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
            int r8 = r4 + 1
            r3 = 20
            if (r8 > r3) goto L_0x00f5
            okhttp3.RequestBody r3 = r15.body()
            boolean r3 = r3 instanceof okhttp3.internal.http.UnrepeatableRequestBody
            if (r3 != 0) goto L_0x00e6
            okhttp3.HttpUrl r3 = r15.url()
            boolean r3 = r1.sameConnection(r0, r3)
            if (r3 != 0) goto L_0x00b7
            r12.release()
            okhttp3.internal.connection.StreamAllocation r16 = new okhttp3.internal.connection.StreamAllocation
            okhttp3.OkHttpClient r3 = r1.client
            okhttp3.ConnectionPool r4 = r3.connectionPool()
            okhttp3.HttpUrl r3 = r15.url()
            okhttp3.Address r5 = r1.createAddress(r3)
            java.lang.Object r7 = r1.callStackTrace
            r3 = r16
            r6 = r9
            r17 = r7
            r7 = r10
            r18 = r2
            r2 = r8
            r8 = r17
            r3.<init>(r4, r5, r6, r7, r8)
            r1.streamAllocation = r3
            r12 = r3
            goto L_0x00c0
        L_0x00b7:
            r18 = r2
            r2 = r8
            okhttp3.internal.http.HttpCodec r3 = r12.codec()
            if (r3 != 0) goto L_0x00c7
        L_0x00c0:
            r11 = r15
            r13 = r0
            r4 = r2
            r2 = r18
            goto L_0x0031
        L_0x00c7:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Closing the body of "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r5 = " didn't close its backing stream. Bad interceptor?"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x00e6:
            r12.release()
            java.net.HttpRetryException r3 = new java.net.HttpRetryException
            int r4 = r0.code()
            java.lang.String r5 = "Cannot retry streamed HTTP body"
            r3.<init>(r5, r4)
            throw r3
        L_0x00f5:
            r18 = r2
            r2 = r8
            r12.release()
            java.net.ProtocolException r3 = new java.net.ProtocolException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Too many follow-up requests: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x0114:
            r0 = move-exception
            r18 = r2
            goto L_0x0156
        L_0x0118:
            r0 = move-exception
            r18 = r2
            r2 = r0
            r0 = r2
            boolean r2 = r0 instanceof okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x0155 }
            if (r2 != 0) goto L_0x0122
            r5 = 1
        L_0x0122:
            r2 = r5
            boolean r5 = r1.recover(r0, r12, r2, r11)     // Catch:{ all -> 0x0155 }
            if (r5 == 0) goto L_0x0133
            r3 = 0
            if (r3 == 0) goto L_0x014c
            r12.streamFailed(r6)
            r12.release()
            goto L_0x014c
        L_0x0133:
            throw r0     // Catch:{ all -> 0x0155 }
        L_0x0134:
            r0 = move-exception
            r18 = r2
            r2 = r0
            r0 = r2
            java.io.IOException r2 = r0.getLastConnectException()     // Catch:{ all -> 0x0155 }
            boolean r2 = r1.recover(r2, r12, r5, r11)     // Catch:{ all -> 0x0155 }
            if (r2 == 0) goto L_0x0150
            r2 = 0
            if (r2 == 0) goto L_0x014c
            r12.streamFailed(r6)
            r12.release()
        L_0x014c:
            r2 = r18
            goto L_0x0031
        L_0x0150:
            java.io.IOException r2 = r0.getLastConnectException()     // Catch:{ all -> 0x0155 }
            throw r2     // Catch:{ all -> 0x0155 }
        L_0x0155:
            r0 = move-exception
        L_0x0156:
            if (r3 == 0) goto L_0x015e
            r12.streamFailed(r6)
            r12.release()
        L_0x015e:
            throw r0
        L_0x015f:
            r18 = r2
            r12.release()
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Canceled"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private boolean recover(IOException e, StreamAllocation streamAllocation2, boolean requestSendStarted, Request userRequest) {
        streamAllocation2.streamFailed(e);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((!requestSendStarted || !(userRequest.body() instanceof UnrepeatableRequestBody)) && isRecoverable(e, requestSendStarted) && streamAllocation2.hasMoreRoutes()) {
            return true;
        }
        return false;
    }

    private boolean isRecoverable(IOException e, boolean requestSendStarted) {
        if (e instanceof ProtocolException) {
            return false;
        }
        if (e instanceof InterruptedIOException) {
            if (!(e instanceof SocketTimeoutException) || requestSendStarted) {
                return false;
            }
            return true;
        } else if ((!(e instanceof SSLHandshakeException) || !(e.getCause() instanceof CertificateException)) && !(e instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    private Request followUpRequest(Response userResponse, Route route) throws IOException {
        String location;
        HttpUrl url;
        Proxy selectedProxy;
        if (userResponse != null) {
            int responseCode = userResponse.code();
            String method = userResponse.request().method();
            RequestBody requestBody = null;
            switch (responseCode) {
                case NOTICE_VALUE:
                case 301:
                case 302:
                case 303:
                    break;
                case 307:
                case 308:
                    if (!method.equals(ShareTarget.METHOD_GET) && !method.equals("HEAD")) {
                        return null;
                    }
                case TypedValues.CycleType.TYPE_CURVE_FIT /*401*/:
                    return this.client.authenticator().authenticate(route, userResponse);
                case 407:
                    if (route != null) {
                        selectedProxy = route.proxy();
                    } else {
                        selectedProxy = this.client.proxy();
                    }
                    if (selectedProxy.type() == Proxy.Type.HTTP) {
                        return this.client.proxyAuthenticator().authenticate(route, userResponse);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                case 408:
                    if (!this.client.retryOnConnectionFailure() || (userResponse.request().body() instanceof UnrepeatableRequestBody)) {
                        return null;
                    }
                    if ((userResponse.priorResponse() == null || userResponse.priorResponse().code() != 408) && retryAfter(userResponse, 0) <= 0) {
                        return userResponse.request();
                    }
                    return null;
                case TypedValues.PositionType.TYPE_PERCENT_WIDTH /*503*/:
                    if ((userResponse.priorResponse() == null || userResponse.priorResponse().code() != 503) && retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
                        return userResponse.request();
                    }
                    return null;
                default:
                    return null;
            }
            if (!this.client.followRedirects() || (location = userResponse.header(HttpHeaders.LOCATION)) == null || (url = userResponse.request().url().resolve(location)) == null) {
                return null;
            }
            if (!url.scheme().equals(userResponse.request().url().scheme()) && !this.client.followSslRedirects()) {
                return null;
            }
            Request.Builder requestBuilder = userResponse.request().newBuilder();
            if (HttpMethod.permitsRequestBody(method)) {
                boolean maintainBody = HttpMethod.redirectsWithBody(method);
                if (HttpMethod.redirectsToGet(method)) {
                    requestBuilder.method(ShareTarget.METHOD_GET, (RequestBody) null);
                } else {
                    if (maintainBody) {
                        requestBody = userResponse.request().body();
                    }
                    requestBuilder.method(method, requestBody);
                }
                if (!maintainBody) {
                    requestBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
                    requestBuilder.removeHeader(HttpHeaders.CONTENT_LENGTH);
                    requestBuilder.removeHeader(HttpHeaders.CONTENT_TYPE);
                }
            }
            if (!sameConnection(userResponse, url)) {
                requestBuilder.removeHeader(HttpHeaders.AUTHORIZATION);
            }
            return requestBuilder.url(url).build();
        }
        throw new IllegalStateException();
    }

    private int retryAfter(Response userResponse, int defaultDelay) {
        String header = userResponse.header(HttpHeaders.RETRY_AFTER);
        if (header == null) {
            return defaultDelay;
        }
        if (header.matches("\\d+")) {
            return Integer.valueOf(header).intValue();
        }
        return Integer.MAX_VALUE;
    }

    private boolean sameConnection(Response response, HttpUrl followUp) {
        HttpUrl url = response.request().url();
        return url.host().equals(followUp.host()) && url.port() == followUp.port() && url.scheme().equals(followUp.scheme());
    }
}
