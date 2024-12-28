package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final /* synthetic */ class zzyc implements Runnable {
    public final /* synthetic */ zzye zza;
    public final /* synthetic */ zzyd zzb;
    public final /* synthetic */ TaskCompletionSource zzc;

    public /* synthetic */ zzyc(zzye zzye, zzyd zzyd, TaskCompletionSource taskCompletionSource) {
        this.zza = zzye;
        this.zzb = zzyd;
        this.zzc = taskCompletionSource;
    }

    public final void run() {
        this.zzb.zzc(this.zzc, this.zza.zza);
    }
}
