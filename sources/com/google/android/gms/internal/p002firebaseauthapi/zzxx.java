package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzxx implements Runnable {
    final /* synthetic */ zzxz zza;
    final /* synthetic */ zzxy zzb;

    zzxx(zzxy zzxy, zzxz zzxz) {
        this.zzb = zzxy;
        this.zza = zzxz;
    }

    public final void run() {
        synchronized (this.zzb.zza.zzh) {
            if (!this.zzb.zza.zzh.isEmpty()) {
                this.zza.zza((PhoneAuthProvider.OnVerificationStateChangedCallbacks) this.zzb.zza.zzh.get(0), new Object[0]);
            }
        }
    }
}
