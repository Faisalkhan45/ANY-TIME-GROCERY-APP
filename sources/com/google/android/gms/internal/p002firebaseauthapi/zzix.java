package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzix  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzix implements zzbm {
    private final zzbu zza;
    private final zzjd zzb;
    private final zzjd zzc;

    /* synthetic */ zzix(zzbu zzbu, zziw zziw) {
        zzjd zzjd;
        this.zza = zzbu;
        if (zzbu.zzf()) {
            zzje zzb2 = zzgm.zza().zzb();
            zzjj zza2 = zzgj.zza(zzbu);
            this.zzb = zzb2.zza(zza2, "mac", "compute");
            zzjd = zzb2.zza(zza2, "mac", "verify");
        } else {
            zzjd = zzgj.zza;
            this.zzb = zzjd;
        }
        this.zzc = zzjd;
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzbq zzbq : this.zza.zze(copyOf)) {
                if (zzbq.zzd().equals(zzoy.LEGACY)) {
                    bArr3 = zzpp.zzc(bArr2, zziy.zzb);
                } else {
                    bArr3 = bArr2;
                }
                try {
                    ((zzbm) zzbq.zze()).zza(copyOfRange, bArr3);
                    zzbq.zza();
                    return;
                } catch (GeneralSecurityException e) {
                    zziy.zza.logp(Level.INFO, "com.google.crypto.tink.mac.MacWrapper$WrappedMac", "verifyMac", "tag prefix matches a key, but cannot verify: ".concat(e.toString()));
                }
            }
            for (zzbq zzbq2 : this.zza.zze(zzas.zza)) {
                try {
                    ((zzbm) zzbq2.zze()).zza(bArr, bArr2);
                    zzbq2.zza();
                    return;
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }
        throw new GeneralSecurityException("tag too short");
    }
}
