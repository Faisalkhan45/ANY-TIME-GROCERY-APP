package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzew  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzew implements zzav {
    private final zznn zza;
    private final zzex zzb;
    private final zzet zzc;
    private final zzes zzd;

    private zzew(zznn zznn, zzex zzex, zzes zzes, zzet zzet, byte[] bArr) {
        this.zza = zznn;
        this.zzb = zzex;
        this.zzd = zzes;
        this.zzc = zzet;
    }

    static zzew zza(zznn zznn) throws GeneralSecurityException {
        if (!zznn.zzg().zzs()) {
            zznh zzb2 = zznn.zzb();
            return new zzew(zznn, zzez.zzb(zzb2), zzez.zzc(zzb2), zzez.zza(zzb2), (byte[]) null);
        }
        throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
    }
}
