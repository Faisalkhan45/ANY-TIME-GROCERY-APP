package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzep  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzep implements zzps {
    private final String zza;
    private final int zzb;
    private zzku zzc;
    private zzjw zzd;
    private int zze;
    private zzlg zzf;

    zzep(zznx zznx) throws GeneralSecurityException {
        String zzf2 = zznx.zzf();
        this.zza = zzf2;
        if (zzf2.equals(zzcc.zzb)) {
            try {
                zzkx zzd2 = zzkx.zzd(zznx.zze(), zzacs.zza());
                this.zzc = (zzku) zzbz.zzd(zznx);
                this.zzb = zzd2.zza();
            } catch (zzadn e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzf2.equals(zzcc.zza)) {
            try {
                zzjz zzc2 = zzjz.zzc(zznx.zze(), zzacs.zza());
                this.zzd = (zzjw) zzbz.zzd(zznx);
                this.zze = zzc2.zzd().zza();
                this.zzb = this.zze + zzc2.zze().zza();
            } catch (zzadn e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else if (zzf2.equals(zzea.zza)) {
            try {
                zzlj zzd3 = zzlj.zzd(zznx.zze(), zzacs.zza());
                this.zzf = (zzlg) zzbz.zzd(zznx);
                this.zzb = zzd3.zza();
            } catch (zzadn e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        } else {
            throw new GeneralSecurityException("unsupported AEAD DEM key type: ".concat(String.valueOf(zzf2)));
        }
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzfk zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zza.equals(zzcc.zzb)) {
            zzkt zzb2 = zzku.zzb();
            zzb2.zzh(this.zzc);
            zzb2.zza(zzacc.zzo(bArr, 0, this.zzb));
            return new zzfk((zzap) zzbz.zzi(this.zza, (zzku) zzb2.zzi(), zzap.class));
        } else if (this.zza.equals(zzcc.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzkb zzb3 = zzkc.zzb();
            zzb3.zzh(this.zzd.zze());
            zzb3.zza(zzacc.zzn(copyOfRange));
            zzms zzb4 = zzmt.zzb();
            zzb4.zzh(this.zzd.zzf());
            zzb4.zza(zzacc.zzn(copyOfRange2));
            zzjv zzb5 = zzjw.zzb();
            zzb5.zzc(this.zzd.zza());
            zzb5.zza((zzkc) zzb3.zzi());
            zzb5.zzb((zzmt) zzb4.zzi());
            return new zzfk((zzap) zzbz.zzi(this.zza, (zzjw) zzb5.zzi(), zzap.class));
        } else if (this.zza.equals(zzea.zza)) {
            zzlf zzb6 = zzlg.zzb();
            zzb6.zzh(this.zzf);
            zzb6.zza(zzacc.zzo(bArr, 0, this.zzb));
            return new zzfk((zzat) zzbz.zzi(this.zza, (zzlg) zzb6.zzi(), zzat.class));
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
