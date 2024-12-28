package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzxc;
import com.google.android.gms.internal.p002firebaseauthapi.zzxo;
import com.google.android.gms.internal.p002firebaseauthapi.zzyz;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzf {
    /* access modifiers changed from: private */
    public static final String zza = zzf.class.getSimpleName();
    private static final zzf zzb = new zzf();

    private zzf() {
    }

    public static zzf zzb() {
        return zzb;
    }

    /* access modifiers changed from: private */
    public final void zze(FirebaseAuth firebaseAuth, zzbm zzbm, Activity activity, TaskCompletionSource taskCompletionSource) {
        Task task;
        zzbm.zzg(firebaseAuth.getApp().getApplicationContext(), firebaseAuth);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        if (!zzax.zza().zzg(activity, taskCompletionSource2)) {
            task = Tasks.forException(zzxc.zza(new Status(17057, "reCAPTCHA flow already in progress")));
        } else {
            Intent intent = new Intent("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA");
            intent.setClass(activity, RecaptchaActivity.class);
            intent.setPackage(activity.getPackageName());
            intent.putExtra("com.google.firebase.auth.KEY_API_KEY", firebaseAuth.getApp().getOptions().getApiKey());
            if (!TextUtils.isEmpty(firebaseAuth.getTenantId())) {
                intent.putExtra("com.google.firebase.auth.KEY_TENANT_ID", firebaseAuth.getTenantId());
            }
            intent.putExtra("com.google.firebase.auth.internal.CLIENT_VERSION", zzxo.zza().zzb());
            intent.putExtra("com.google.firebase.auth.internal.FIREBASE_APP_NAME", firebaseAuth.getApp().getName());
            activity.startActivity(intent);
            task = taskCompletionSource2.getTask();
        }
        task.addOnSuccessListener(new zzd(this, taskCompletionSource)).addOnFailureListener(new zzc(this, taskCompletionSource));
    }

    public final Task zza(FirebaseAuth firebaseAuth, String str, Activity activity, boolean z) {
        zzw zzw = (zzw) firebaseAuth.getFirebaseAuthSettings();
        SafetyNetClient client = z ? SafetyNet.getClient(firebaseAuth.getApp().getApplicationContext()) : null;
        zzbm zzc = zzbm.zzc();
        if (zzyz.zzg(firebaseAuth.getApp()) || zzw.zze()) {
            return Tasks.forResult(new zze((String) null, (String) null));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task zzb2 = zzc.zzb();
        if (zzb2 != null) {
            if (zzb2.isSuccessful()) {
                return Tasks.forResult(new zze((String) null, (String) zzb2.getResult()));
            }
            String str2 = zza;
            Log.e(str2, "Error in previous reCAPTCHA flow: ".concat(String.valueOf(zzb2.getException().getMessage())));
            Log.e(str2, "Continuing with application verification as normal");
        }
        if (client == null || zzw.zzc()) {
            zze(firebaseAuth, zzc, activity, taskCompletionSource);
        } else {
            FirebaseApp app = firebaseAuth.getApp();
            byte[] bArr = new byte[0];
            if (str != null) {
                try {
                    bArr = str.getBytes(Key.STRING_CHARSET_NAME);
                } catch (UnsupportedEncodingException e) {
                    Log.e(zza, "Failed to getBytes with exception: ".concat(String.valueOf(e.getMessage())));
                }
            }
            client.attest(bArr, app.getOptions().getApiKey()).addOnSuccessListener(new zzb(this, taskCompletionSource, firebaseAuth, zzc, activity)).addOnFailureListener(new zza(this, firebaseAuth, zzc, activity, taskCompletionSource));
        }
        return taskCompletionSource.getTask();
    }
}
