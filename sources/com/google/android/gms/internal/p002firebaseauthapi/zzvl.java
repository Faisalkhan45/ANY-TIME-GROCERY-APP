package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzan;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvl extends zzyb {
    public zzvl() {
        super(5);
    }

    public final String zza() {
        return "delete";
    }

    public final void zzb() {
        ((zzan) this.zzf).zza();
        zzm((Object) null);
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzg(new zzrk(this.zze.zzf()), this.zzc);
    }
}
