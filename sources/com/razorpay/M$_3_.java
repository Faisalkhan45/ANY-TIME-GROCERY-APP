package com.razorpay;

import android.os.AsyncTask;
import android.util.Log;
import androidx.browser.trusted.sharing.ShareTarget;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* compiled from: Owl */
final class M$_3_ extends AsyncTask<String, Void, ResponseObject> {
    private Callback a;
    private String b = null;
    private Map<String, String> c = new HashMap();
    private String d = null;

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        ResponseObject responseObject = (ResponseObject) obj;
        Callback callback = this.a;
        if (callback != null) {
            callback.run(responseObject);
        }
    }

    private M$_3_(Callback callback) {
        this.a = callback;
    }

    /* access modifiers changed from: package-private */
    public static AsyncTask a(String str, Callback callback) {
        M$_3_ m$_3_ = new M$_3_(callback);
        m$_3_.b = ShareTarget.METHOD_GET;
        return m$_3_.execute(new String[]{str});
    }

    static AsyncTask a(String str, Map<String, String> map, Callback callback) {
        M$_3_ m$_3_ = new M$_3_(callback);
        m$_3_.b = ShareTarget.METHOD_GET;
        m$_3_.c = map;
        return m$_3_.execute(new String[]{str});
    }

    public static AsyncTask a(String str, String str2, Map<String, String> map, Callback callback) {
        M$_3_ m$_3_ = new M$_3_(callback);
        m$_3_.b = "POST";
        m$_3_.d = str2;
        m$_3_.c = map;
        return m$_3_.execute(new String[]{str});
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public ResponseObject doInBackground(String... strArr) {
        InputStream inputStream;
        ResponseObject responseObject = new ResponseObject();
        InputStream inputStream2 = null;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(strArr[0]).openConnection();
            for (Map.Entry next : this.c.entrySet()) {
                httpsURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            httpsURLConnection.setRequestMethod(this.b);
            if (this.d != null) {
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.getOutputStream().write(this.d.getBytes(StandardCharsets.UTF_8));
            }
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setReadTimeout(20000);
            httpsURLConnection.connect();
            int responseCode = httpsURLConnection.getResponseCode();
            responseObject.setResponseCode(responseCode);
            if (responseCode >= 400) {
                inputStream = httpsURLConnection.getErrorStream();
            } else {
                inputStream = httpsURLConnection.getInputStream();
            }
            responseObject.setHeaders(httpsURLConnection.getHeaderFields());
            responseObject.setResponseResult(a(inputStream));
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    AnalyticsUtil.reportError(getClass().getName(), "S2", e.getMessage());
                }
            }
        } catch (Exception e2) {
            Log.e("com.razorpay.checkout", "Input Stream: " + e2.getLocalizedMessage());
            AnalyticsUtil.reportError(getClass().getName(), "S2", e2.getMessage());
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (Throwable th) {
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (Exception e3) {
                    AnalyticsUtil.reportError(getClass().getName(), "S2", e3.getMessage());
                }
            }
            throw th;
        }
        return responseObject;
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }
}
