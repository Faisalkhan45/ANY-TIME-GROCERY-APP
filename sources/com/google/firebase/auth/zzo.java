package com.google.firebase.auth;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zze;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzo implements OnCompleteListener {
    final /* synthetic */ String zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ TimeUnit zzc;
    final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd;
    final /* synthetic */ Activity zze;
    final /* synthetic */ Executor zzf;
    final /* synthetic */ boolean zzg;
    final /* synthetic */ FirebaseAuth zzh;

    zzo(FirebaseAuth firebaseAuth, String str, long j, TimeUnit timeUnit, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, boolean z) {
        this.zzh = firebaseAuth;
        this.zza = str;
        this.zzb = j;
        this.zzc = timeUnit;
        this.zzd = onVerificationStateChangedCallbacks;
        this.zze = activity;
        this.zzf = executor;
        this.zzg = z;
    }

    public final void onComplete(Task task) {
        String str;
        String str2;
        String str3;
        if (!task.isSuccessful()) {
            if (task.getException() != null) {
                str3 = task.getException().getMessage();
            } else {
                str3 = "";
            }
            Log.e("FirebaseAuth", "Error while validating application identity: ".concat(String.valueOf(str3)));
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str2 = null;
            str = null;
        } else {
            String zzb2 = ((zze) task.getResult()).zzb();
            str2 = ((zze) task.getResult()).zza();
            str = zzb2;
        }
        this.zzh.zzJ(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, str2, str);
    }
}
