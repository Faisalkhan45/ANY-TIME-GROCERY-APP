package com.bumptech.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpUrlFetcher implements DataFetcher<InputStream> {
    static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultHttpUrlConnectionFactory();
    static final int INVALID_STATUS_CODE = -1;
    private static final int MAXIMUM_REDIRECTS = 5;
    static final String REDIRECT_HEADER_FIELD = "Location";
    private static final String TAG = "HttpUrlFetcher";
    private final HttpUrlConnectionFactory connectionFactory;
    private final GlideUrl glideUrl;
    private volatile boolean isCancelled;
    private InputStream stream;
    private final int timeout;
    private HttpURLConnection urlConnection;

    interface HttpUrlConnectionFactory {
        HttpURLConnection build(URL url) throws IOException;
    }

    public HttpUrlFetcher(GlideUrl glideUrl2, int timeout2) {
        this(glideUrl2, timeout2, DEFAULT_CONNECTION_FACTORY);
    }

    HttpUrlFetcher(GlideUrl glideUrl2, int timeout2, HttpUrlConnectionFactory connectionFactory2) {
        this.glideUrl = glideUrl2;
        this.timeout = timeout2;
        this.connectionFactory = connectionFactory2;
    }

    public void loadData(Priority priority, DataFetcher.DataCallback<? super InputStream> callback) {
        StringBuilder sb;
        long startTime = LogTime.getLogTime();
        try {
            callback.onDataReady(loadDataWithRedirects(this.glideUrl.toURL(), 0, (URL) null, this.glideUrl.getHeaders()));
            if (Log.isLoggable(TAG, 2)) {
                sb = new StringBuilder();
                Log.v(TAG, sb.append("Finished http url fetcher fetch in ").append(LogTime.getElapsedMillis(startTime)).toString());
            }
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to load data for url", e);
            }
            callback.onLoadFailed(e);
            if (Log.isLoggable(TAG, 2)) {
                sb = new StringBuilder();
            }
        } catch (Throwable th) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Finished http url fetcher fetch in " + LogTime.getElapsedMillis(startTime));
            }
            throw th;
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    private InputStream loadDataWithRedirects(URL url, int redirects, URL lastUrl, Map<String, String> headers) throws HttpException {
        if (redirects < 5) {
            if (lastUrl != null) {
                try {
                    if (url.toURI().equals(lastUrl.toURI())) {
                        throw new HttpException("In re-direct loop", -1);
                    }
                } catch (URISyntaxException e) {
                }
            }
            HttpURLConnection buildAndConfigureConnection = buildAndConfigureConnection(url, headers);
            this.urlConnection = buildAndConfigureConnection;
            try {
                buildAndConfigureConnection.connect();
                this.stream = this.urlConnection.getInputStream();
                if (this.isCancelled) {
                    return null;
                }
                int statusCode = getHttpStatusCodeOrInvalid(this.urlConnection);
                if (isHttpOk(statusCode)) {
                    return getStreamForSuccessfulRequest(this.urlConnection);
                }
                if (isHttpRedirect(statusCode)) {
                    String redirectUrlString = this.urlConnection.getHeaderField("Location");
                    if (!TextUtils.isEmpty(redirectUrlString)) {
                        try {
                            URL redirectUrl = new URL(url, redirectUrlString);
                            cleanup();
                            return loadDataWithRedirects(redirectUrl, redirects + 1, url, headers);
                        } catch (MalformedURLException e2) {
                            throw new HttpException("Bad redirect url: " + redirectUrlString, statusCode, e2);
                        }
                    } else {
                        throw new HttpException("Received empty or null redirect url", statusCode);
                    }
                } else if (statusCode == -1) {
                    throw new HttpException(statusCode);
                } else {
                    try {
                        throw new HttpException(this.urlConnection.getResponseMessage(), statusCode);
                    } catch (IOException e3) {
                        throw new HttpException("Failed to get a response message", statusCode, e3);
                    }
                }
            } catch (IOException e4) {
                throw new HttpException("Failed to connect or obtain data", getHttpStatusCodeOrInvalid(this.urlConnection), e4);
            }
        } else {
            throw new HttpException("Too many (> 5) redirects!", -1);
        }
    }

    private static int getHttpStatusCodeOrInvalid(HttpURLConnection urlConnection2) {
        try {
            return urlConnection2.getResponseCode();
        } catch (IOException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "Failed to get a response code", e);
            return -1;
        }
    }

    private HttpURLConnection buildAndConfigureConnection(URL url, Map<String, String> headers) throws HttpException {
        try {
            HttpURLConnection urlConnection2 = this.connectionFactory.build(url);
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                urlConnection2.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }
            urlConnection2.setConnectTimeout(this.timeout);
            urlConnection2.setReadTimeout(this.timeout);
            urlConnection2.setUseCaches(false);
            urlConnection2.setDoInput(true);
            urlConnection2.setInstanceFollowRedirects(false);
            return urlConnection2;
        } catch (IOException e) {
            throw new HttpException("URL.openConnection threw", 0, e);
        }
    }

    private static boolean isHttpOk(int statusCode) {
        return statusCode / 100 == 2;
    }

    private static boolean isHttpRedirect(int statusCode) {
        return statusCode / 100 == 3;
    }

    private InputStream getStreamForSuccessfulRequest(HttpURLConnection urlConnection2) throws HttpException {
        try {
            if (TextUtils.isEmpty(urlConnection2.getContentEncoding())) {
                this.stream = ContentLengthInputStream.obtain(urlConnection2.getInputStream(), (long) urlConnection2.getContentLength());
            } else {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Got non empty content encoding: " + urlConnection2.getContentEncoding());
                }
                this.stream = urlConnection2.getInputStream();
            }
            return this.stream;
        } catch (IOException e) {
            throw new HttpException("Failed to obtain InputStream", getHttpStatusCodeOrInvalid(urlConnection2), e);
        }
    }

    public void cleanup() {
        InputStream inputStream = this.stream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
        HttpURLConnection httpURLConnection = this.urlConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.urlConnection = null;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        DefaultHttpUrlConnectionFactory() {
        }

        public HttpURLConnection build(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
}
