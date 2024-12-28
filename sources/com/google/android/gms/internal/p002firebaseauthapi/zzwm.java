package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzag;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzwm extends zzyb {
    private final zzsw zza;

    public zzwm(zzag zzag, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3) {
        super(8);
        Preconditions.checkNotNull(zzag);
        Preconditions.checkNotEmpty(str);
        this.zza = new zzsw(Preconditions.checkNotEmpty(zzag.zzc()), str, str2, j, z, z2, str3, str4, z3);
    }

    public final String zza() {
        return "startMfaEnrollmentWithPhoneNumber";
    }

    public final void zzb() {
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzz(this.zza, this.zzc);
    }
}
