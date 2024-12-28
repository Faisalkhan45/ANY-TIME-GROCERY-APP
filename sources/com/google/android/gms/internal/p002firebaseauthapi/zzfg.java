package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzfg implements zzex {
    private final zzes zza;
    private final int zzb;

    private zzfg(zzes zzes, int i) {
        this.zza = zzes;
        this.zzb = i;
    }

    static zzfg zzc(int i) throws GeneralSecurityException {
        switch (i - 1) {
            case 0:
                return new zzfg(new zzes("HmacSha256"), 1);
            case 1:
                return new zzfg(new zzes("HmacSha384"), 2);
            default:
                return new zzfg(new zzes("HmacSha512"), 3);
        }
    }

    public final byte[] zza(byte[] bArr, zzey zzey) throws GeneralSecurityException {
        byte[] zzh = zzpx.zzh(zzpx.zzi(this.zzb, zzey.zza().zzc()), zzpx.zzk(zzpx.zzl(this.zzb), 1, bArr));
        byte[] zzc = zzpp.zzc(bArr, zzey.zzb().zzc());
        byte[] zzd = zzff.zzd(zzb());
        zzes zzes = this.zza;
        return zzes.zzb((byte[]) null, zzh, "eae_prk", zzc, "shared_secret", zzd, zzes.zza());
    }

    public final byte[] zzb() throws GeneralSecurityException {
        switch (this.zzb - 1) {
            case 0:
                return zzff.zzc;
            case 1:
                return zzff.zzd;
            default:
                return zzff.zze;
        }
    }
}
