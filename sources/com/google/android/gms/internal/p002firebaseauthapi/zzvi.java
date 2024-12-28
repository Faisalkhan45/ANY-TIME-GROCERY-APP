package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvi extends zzyb {
    private final zzre zza;

    public zzvi(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new zzre(str, str2);
    }

    public final String zza() {
        return "checkActionCode";
    }

    public final void zzb() {
        zzm(new zzo(this.zzm));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzd(this.zza, this.zzc);
    }
}
