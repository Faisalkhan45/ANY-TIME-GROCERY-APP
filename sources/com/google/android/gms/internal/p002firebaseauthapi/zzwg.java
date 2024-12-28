package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzwg extends zzyb {
    private final zzsk zza;

    public zzwg(String str) {
        super(2);
        this.zza = new zzsk(str);
    }

    public final String zza() {
        return "signInAnonymously";
    }

    public final void zzb() {
        zzx zzN = zzwy.zzN(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzN);
        zzm(new zzr(zzN));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzt(this.zza, this.zzc);
    }
}
