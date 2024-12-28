package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzwo extends zzyb {
    private final zzta zza;

    public zzwo(String str, String str2) {
        super(2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(str2);
        this.zza = new zzta(str, str2);
    }

    public final String zza() {
        return "unenrollMfa";
    }

    public final void zzb() {
        ((zzg) this.zzf).zza(this.zzj, zzwy.zzN(this.zzd, this.zzk));
        zzm((Object) null);
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzB(this.zza, this.zzc);
    }
}
