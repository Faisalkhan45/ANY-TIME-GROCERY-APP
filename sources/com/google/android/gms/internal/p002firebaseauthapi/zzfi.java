package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzfi implements zzex {
    private final zzes zza;

    zzfi(zzes zzes) {
        this.zza = zzes;
    }

    public final byte[] zza(byte[] bArr, zzey zzey) throws GeneralSecurityException {
        byte[] zza2 = zzqt.zza(zzey.zza().zzc(), bArr);
        byte[] zzc = zzpp.zzc(bArr, zzey.zzb().zzc());
        byte[] zzd = zzff.zzd(zzff.zzb);
        zzes zzes = this.zza;
        return zzes.zzb((byte[]) null, zza2, "eae_prk", zzc, "shared_secret", zzd, zzes.zza());
    }

    public final byte[] zzb() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzc(), zzff.zzf)) {
            return zzff.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
