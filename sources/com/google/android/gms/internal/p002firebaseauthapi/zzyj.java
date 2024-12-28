package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzyj {
    public static void zza(String str, zzxm zzxm, zzyg zzyg, Type type, zzxq zzxq) {
        BufferedOutputStream bufferedOutputStream;
        try {
            Preconditions.checkNotNull(zzxm);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzxm.zza().getBytes(Charset.defaultCharset());
            int length = bytes.length;
            httpURLConnection.setFixedLengthStreamingMode(length);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzxq.zza(httpURLConnection);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), length);
            bufferedOutputStream.write(bytes, 0, length);
            bufferedOutputStream.close();
            zzb(httpURLConnection, zzyg, type);
            return;
        } catch (SocketTimeoutException e) {
            zzyg.zza("TIMEOUT");
            return;
        } catch (UnknownHostException e2) {
            zzyg.zza("<<Network Error>>");
            return;
        } catch (IOException | NullPointerException | JSONException e3) {
            zzyg.zza(e3.getMessage());
            return;
        } catch (Throwable th) {
            zzyi.zza(th, th);
        }
        throw th;
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzyg zzyg, Type type) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (zzc(responseCode)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (!zzc(responseCode)) {
                zzyg.zza((String) zzxl.zza(sb2, String.class));
            } else {
                zzyg.zzb((zzxn) zzxl.zza(sb2, type));
            }
        } catch (SocketTimeoutException e) {
            zzyg.zza("TIMEOUT");
        } catch (zzvg | IOException e2) {
            try {
                zzyg.zza(e2.getMessage());
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            zzyi.zza(th, th2);
        }
        httpURLConnection.disconnect();
        return;
        throw th;
    }

    private static final boolean zzc(int i) {
        return i >= 200 && i < 300;
    }
}
