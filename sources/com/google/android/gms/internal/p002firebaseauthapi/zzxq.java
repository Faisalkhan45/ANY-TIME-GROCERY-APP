package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxq {
    private final Context zza;
    private zzyk zzb;
    private final String zzc;
    private final FirebaseApp zzd;
    private boolean zze = false;
    private String zzf;

    public zzxq(Context context, FirebaseApp firebaseApp, String str) {
        this.zza = (Context) Preconditions.checkNotNull(context);
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzc = String.format("Android/%s/%s", new Object[]{"Fallback", str});
    }

    public final void zza(URLConnection uRLConnection) {
        String str;
        String str2;
        if (this.zze) {
            str = String.valueOf(this.zzc).concat("/FirebaseUI-Android");
        } else {
            str = String.valueOf(this.zzc).concat("/FirebaseCore-Android");
        }
        if (this.zzb == null) {
            Context context = this.zza;
            this.zzb = new zzyk(context, context.getPackageName());
        }
        uRLConnection.setRequestProperty("X-Android-Package", this.zzb.zzb());
        uRLConnection.setRequestProperty("X-Android-Cert", this.zzb.zza());
        uRLConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, zzxr.zza());
        uRLConnection.setRequestProperty("X-Client-Version", str);
        uRLConnection.setRequestProperty("X-Firebase-Locale", this.zzf);
        uRLConnection.setRequestProperty("X-Firebase-GMPID", this.zzd.getOptions().getApplicationId());
        HeartBeatController heartBeatController = (HeartBeatController) FirebaseAuth.getInstance(this.zzd).zzy().get();
        if (heartBeatController != null) {
            try {
                str2 = (String) Tasks.await(heartBeatController.getHeartBeatsHeader());
            } catch (InterruptedException | ExecutionException e) {
                Log.w("LocalRequestInterceptor", "Unable to get heartbeats: ".concat(String.valueOf(e.getMessage())));
                str2 = null;
            }
        } else {
            str2 = null;
        }
        uRLConnection.setRequestProperty("X-Firebase-Client", str2);
        this.zzf = null;
    }

    public final void zzb(String str) {
        this.zze = !TextUtils.isEmpty(str);
    }

    public final void zzc(String str) {
        this.zzf = str;
    }
}
