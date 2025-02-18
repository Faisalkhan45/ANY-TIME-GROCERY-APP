package okhttp3.internal.cache;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor implements Interceptor {
    final InternalCache cache;

    public CacheInterceptor(InternalCache cache2) {
        this.cache = cache2;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response cacheCandidate;
        InternalCache internalCache = this.cache;
        if (internalCache != null) {
            cacheCandidate = internalCache.get(chain.request());
        } else {
            cacheCandidate = null;
        }
        CacheStrategy strategy = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), cacheCandidate).get();
        Request networkRequest = strategy.networkRequest;
        Response cacheResponse = strategy.cacheResponse;
        InternalCache internalCache2 = this.cache;
        if (internalCache2 != null) {
            internalCache2.trackResponse(strategy);
        }
        if (cacheCandidate != null && cacheResponse == null) {
            Util.closeQuietly((Closeable) cacheCandidate.body());
        }
        if (networkRequest == null && cacheResponse == null) {
            return new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(TypedValues.PositionType.TYPE_PERCENT_HEIGHT).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (networkRequest == null) {
            return cacheResponse.newBuilder().cacheResponse(stripBody(cacheResponse)).build();
        }
        Response networkResponse = null;
        try {
            networkResponse = chain.proceed(networkRequest);
            if (cacheResponse != null) {
                if (networkResponse.code() == 304) {
                    Response response = cacheResponse.newBuilder().headers(combine(cacheResponse.headers(), networkResponse.headers())).sentRequestAtMillis(networkResponse.sentRequestAtMillis()).receivedResponseAtMillis(networkResponse.receivedResponseAtMillis()).cacheResponse(stripBody(cacheResponse)).networkResponse(stripBody(networkResponse)).build();
                    networkResponse.body().close();
                    this.cache.trackConditionalCacheHit();
                    this.cache.update(cacheResponse, response);
                    return response;
                }
                Util.closeQuietly((Closeable) cacheResponse.body());
            }
            Response response2 = networkResponse.newBuilder().cacheResponse(stripBody(cacheResponse)).networkResponse(stripBody(networkResponse)).build();
            if (this.cache != null) {
                if (HttpHeaders.hasBody(response2) && CacheStrategy.isCacheable(response2, networkRequest)) {
                    return cacheWritingResponse(this.cache.put(response2), response2);
                }
                if (HttpMethod.invalidatesCache(networkRequest.method())) {
                    try {
                        this.cache.remove(networkRequest);
                    } catch (IOException e) {
                    }
                }
            }
            return response2;
        } finally {
            if (networkResponse == null && cacheCandidate != null) {
                Util.closeQuietly((Closeable) cacheCandidate.body());
            }
        }
    }

    private static Response stripBody(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }
        return response.newBuilder().body((ResponseBody) null).build();
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        Sink cacheBodyUnbuffered;
        if (cacheRequest == null || (cacheBodyUnbuffered = cacheRequest.body()) == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        Source cacheWritingSource = new Source() {
            boolean cacheRequestClosed;

            public long read(Buffer sink, long byteCount) throws IOException {
                try {
                    long bytesRead = source.read(sink, byteCount);
                    if (bytesRead == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            cacheBody.close();
                        }
                        return -1;
                    }
                    sink.copyTo(cacheBody.buffer(), sink.size() - bytesRead, bytesRead);
                    cacheBody.emitCompleteSegments();
                    return bytesRead;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        };
        return response.newBuilder().body(new RealResponseBody(response.header(com.google.common.net.HttpHeaders.CONTENT_TYPE), response.body().contentLength(), Okio.buffer(cacheWritingSource))).build();
    }

    private static Headers combine(Headers cachedHeaders, Headers networkHeaders) {
        Headers.Builder result = new Headers.Builder();
        int size = cachedHeaders.size();
        for (int i = 0; i < size; i++) {
            String fieldName = cachedHeaders.name(i);
            String value = cachedHeaders.value(i);
            if ((!com.google.common.net.HttpHeaders.WARNING.equalsIgnoreCase(fieldName) || !value.startsWith("1")) && (isContentSpecificHeader(fieldName) || !isEndToEnd(fieldName) || networkHeaders.get(fieldName) == null)) {
                Internal.instance.addLenient(result, fieldName, value);
            }
        }
        int size2 = networkHeaders.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String fieldName2 = networkHeaders.name(i2);
            if (!isContentSpecificHeader(fieldName2) && isEndToEnd(fieldName2)) {
                Internal.instance.addLenient(result, fieldName2, networkHeaders.value(i2));
            }
        }
        return result.build();
    }

    static boolean isEndToEnd(String fieldName) {
        return !com.google.common.net.HttpHeaders.CONNECTION.equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.KEEP_ALIVE.equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.PROXY_AUTHENTICATE.equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.PROXY_AUTHORIZATION.equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.TE.equalsIgnoreCase(fieldName) && !"Trailers".equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(fieldName) && !com.google.common.net.HttpHeaders.UPGRADE.equalsIgnoreCase(fieldName);
    }

    static boolean isContentSpecificHeader(String fieldName) {
        return com.google.common.net.HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.CONTENT_ENCODING.equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(fieldName);
    }
}
