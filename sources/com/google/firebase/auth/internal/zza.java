package com.google.firebase.auth.internal;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zza implements OnFailureListener {
    final /* synthetic */ FirebaseAuth zza;
    final /* synthetic */ zzbm zzb;
    final /* synthetic */ Activity zzc;
    final /* synthetic */ TaskCompletionSource zzd;
    final /* synthetic */ zzf zze;

    zza(zzf zzf, FirebaseAuth firebaseAuth, zzbm zzbm, Activity activity, TaskCompletionSource taskCompletionSource) {
        this.zze = zzf;
        this.zza = firebaseAuth;
        this.zzb = zzbm;
        this.zzc = activity;
        this.zzd = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        Log.e(zzf.zza, "Problem retrieving SafetyNet Token: ".concat(String.valueOf(exc.getMessage())));
        this.zze.zze(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
