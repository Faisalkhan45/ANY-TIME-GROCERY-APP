package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public interface zzxg {
    public static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTaskResponseHandler");

    Context zza();

    Uri.Builder zzb(Intent intent, String str, String str2);

    String zzc(String str);

    HttpURLConnection zzd(URL url);

    void zze(String str, Status status);

    void zzf(Uri uri, String str);
}
