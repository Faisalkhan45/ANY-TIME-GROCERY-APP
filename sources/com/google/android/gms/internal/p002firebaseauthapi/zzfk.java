package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfk {
    private final zzap zza;
    private final zzat zzb;

    public zzfk(zzap zzap) {
        this.zza = zzap;
        this.zzb = null;
    }

    public zzfk(zzat zzat) {
        this.zza = null;
        this.zzb = zzat;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzap zzap = this.zza;
        if (zzap != null) {
            return zzap.zza(bArr, bArr2);
        }
        return this.zzb.zza(bArr, bArr2);
    }
}
