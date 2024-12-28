package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzpt implements zzau {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzpv zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzps zzf;
    private final int zzg;

    public zzpt(ECPrivateKey eCPrivateKey, byte[] bArr, String str, int i, zzps zzps) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzpv(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzg = i;
        this.zzf = zzps;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i;
        EllipticCurve curve = this.zzb.getParams().getCurve();
        int i2 = this.zzg;
        int zza2 = zzpx.zza(curve);
        switch (i2 - 1) {
            case 0:
                i = zza2 + zza2 + 1;
                break;
            case 2:
                i = zza2 + zza2;
                break;
            default:
                i = zza2 + 1;
                break;
        }
        int length = bArr.length;
        if (length >= i) {
            return this.zzf.zzb(this.zzc.zza(Arrays.copyOfRange(bArr, 0, i), this.zzd, this.zze, (byte[]) null, this.zzf.zza(), this.zzg)).zza(Arrays.copyOfRange(bArr, i, length), zza);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
