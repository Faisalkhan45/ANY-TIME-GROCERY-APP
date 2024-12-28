package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzez  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzez {
    static zzet zza(zznh zznh) throws GeneralSecurityException {
        if (zznh.zzd() == 3) {
            return new zzeq(16);
        }
        if (zznh.zzd() == 4) {
            return new zzeq(32);
        }
        if (zznh.zzd() == 5) {
            return new zzer();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    static zzex zzb(zznh zznh) throws GeneralSecurityException {
        if (zznh.zzf() == 3) {
            return new zzfi(new zzes("HmacSha256"));
        }
        if (zznh.zzf() == 4) {
            return zzfg.zzc(1);
        }
        if (zznh.zzf() == 5) {
            return zzfg.zzc(2);
        }
        if (zznh.zzf() == 6) {
            return zzfg.zzc(3);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    static zzes zzc(zznh zznh) {
        if (zznh.zze() == 3) {
            return new zzes("HmacSha256");
        }
        if (zznh.zze() == 4) {
            return new zzes("HmacSha384");
        }
        if (zznh.zze() == 5) {
            return new zzes("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }
}
