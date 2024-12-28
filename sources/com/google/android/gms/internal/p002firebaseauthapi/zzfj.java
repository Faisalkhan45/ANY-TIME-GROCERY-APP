package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzfj implements zzey {
    private final zzqv zza;
    private final zzqv zzb;

    private zzfj(byte[] bArr, byte[] bArr2) {
        this.zza = zzqv.zzb(bArr);
        this.zzb = zzqv.zzb(bArr2);
    }

    static zzfj zzc(byte[] bArr) throws GeneralSecurityException {
        return new zzfj(bArr, zzqt.zzb(bArr));
    }

    public final zzqv zza() {
        return this.zza;
    }

    public final zzqv zzb() {
        return this.zzb;
    }
}
