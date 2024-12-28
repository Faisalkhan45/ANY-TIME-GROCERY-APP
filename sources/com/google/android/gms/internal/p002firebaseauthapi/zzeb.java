package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzeb implements zzat {
    private final zzbu zza;
    private final zzjd zzb;
    private final zzjd zzc;

    public zzeb(zzbu zzbu) {
        zzjd zzjd;
        this.zza = zzbu;
        if (zzbu.zzf()) {
            zzje zzb2 = zzgm.zza().zzb();
            zzjj zza2 = zzgj.zza(zzbu);
            this.zzb = zzb2.zza(zza2, "daead", "encrypt");
            zzjd = zzb2.zza(zza2, "daead", "decrypt");
        } else {
            zzjd = zzgj.zza;
            this.zzb = zzjd;
        }
        this.zzc = zzjd;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzbq zzbq : this.zza.zze(copyOf)) {
                try {
                    byte[] zza2 = ((zzat) zzbq.zze()).zza(copyOfRange, bArr2);
                    zzbq.zza();
                    int length2 = copyOfRange.length;
                    return zza2;
                } catch (GeneralSecurityException e) {
                    zzec.zza.logp(Level.INFO, "com.google.crypto.tink.daead.DeterministicAeadWrapper$WrappedDeterministicAead", "decryptDeterministically", "ciphertext prefix matches a key, but cannot decrypt: ".concat(e.toString()));
                }
            }
        }
        for (zzbq zzbq2 : this.zza.zze(zzas.zza)) {
            try {
                byte[] zza3 = ((zzat) zzbq2.zze()).zza(bArr, bArr2);
                zzbq2.zza();
                int length3 = bArr.length;
                return zza3;
            } catch (GeneralSecurityException e2) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }
}
