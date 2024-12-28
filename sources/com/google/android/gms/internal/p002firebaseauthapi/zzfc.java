package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfc extends zzgx {
    public zzfc() {
        super(zznk.class, zznn.class, new zzfa(zzau.class));
    }

    static /* bridge */ /* synthetic */ zzga zzh(int i, int i2, int i3, int i4) {
        zzng zza = zznh.zza();
        zza.zzc(i);
        zza.zzb(i2);
        zza.zza(i3);
        zznd zza2 = zzne.zza();
        zza2.zza((zznh) zza.zzi());
        return new zzga((zzne) zza2.zzi(), i4);
    }

    public final zzgb zza() {
        return new zzfb(this, zzne.class);
    }

    public final zznr zzb() {
        return zznr.ASYMMETRIC_PRIVATE;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zznk.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zznk zznk = (zznk) zzaek;
        if (zznk.zzf().zzs()) {
            throw new GeneralSecurityException("Private key is empty.");
        } else if (zznk.zzk()) {
            zzqs.zzc(zznk.zza(), 0);
            zzff.zza(zznk.zze().zzb());
        } else {
            throw new GeneralSecurityException("Missing public key.");
        }
    }

    public final /* synthetic */ zzaek zzg(zzaek zzaek) throws GeneralSecurityException {
        return ((zznk) zzaek).zze();
    }
}
