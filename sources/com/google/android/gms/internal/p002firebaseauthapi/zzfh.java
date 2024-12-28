package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzfh implements zzey {
    private final zzqv zza;
    private final zzqv zzb;

    private zzfh(byte[] bArr, byte[] bArr2) {
        this.zza = zzqv.zzb(bArr);
        this.zzb = zzqv.zzb(bArr2);
    }

    static zzfh zzc(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        zzpx.zzf(zzpx.zzk(zzpx.zzl(i), 1, bArr2), zzpx.zzi(i, bArr));
        return new zzfh(bArr, bArr2);
    }

    public final zzqv zza() {
        return this.zza;
    }

    public final zzqv zzb() {
        return this.zzb;
    }
}
