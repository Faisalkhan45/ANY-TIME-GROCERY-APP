package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcf implements zzap {
    private final zzbu zza;
    private final zzjd zzb;
    private final zzjd zzc;

    /* synthetic */ zzcf(zzbu zzbu, zzce zzce) {
        zzjd zzjd;
        this.zza = zzbu;
        if (zzbu.zzf()) {
            zzje zzb2 = zzgm.zza().zzb();
            zzjj zza2 = zzgj.zza(zzbu);
            this.zzb = zzb2.zza(zza2, "aead", "encrypt");
            zzjd = zzb2.zza(zza2, "aead", "decrypt");
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
                    byte[] zza2 = ((zzap) zzbq.zze()).zza(copyOfRange, bArr2);
                    zzbq.zza();
                    int length2 = copyOfRange.length;
                    return zza2;
                } catch (GeneralSecurityException e) {
                    zzcg.zza.logp(Level.INFO, "com.google.crypto.tink.aead.AeadWrapper$WrappedAead", "decrypt", "ciphertext prefix matches a key, but cannot decrypt: ".concat(e.toString()));
                }
            }
        }
        for (zzbq zzbq2 : this.zza.zze(zzas.zza)) {
            try {
                byte[] zza3 = ((zzap) zzbq2.zze()).zza(bArr, bArr2);
                zzbq2.zza();
                int length3 = bArr.length;
                return zza3;
            } catch (GeneralSecurityException e2) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
