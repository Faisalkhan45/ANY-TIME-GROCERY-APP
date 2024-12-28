package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzef  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzef extends zzgx {
    /* access modifiers changed from: private */
    public static final byte[] zza = new byte[0];

    zzef() {
        super(zzmd.class, zzmg.class, new zzed(zzau.class));
    }

    static /* bridge */ /* synthetic */ zzga zzi(int i, int i2, int i3, zzbf zzbf, byte[] bArr, int i4) {
        zzoy zzoy;
        zzlw zza2 = zzlx.zza();
        zzmi zza3 = zzmj.zza();
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzacc.zzn(bArr));
        zzmj zzmj = (zzmj) zza3.zzi();
        zznw zza4 = zznx.zza();
        zza4.zzb(zzbf.zzb());
        zza4.zzc(zzacc.zzn(zzbf.zzc()));
        switch (zzbf.zzd() - 1) {
            case 0:
                zzoy = zzoy.TINK;
                break;
            case 1:
                zzoy = zzoy.LEGACY;
                break;
            case 2:
                zzoy = zzoy.RAW;
                break;
            default:
                zzoy = zzoy.CRUNCHY;
                break;
        }
        zza4.zza(zzoy);
        zzlt zza5 = zzlu.zza();
        zza5.zza((zznx) zza4.zzi());
        zzlz zzb = zzma.zzb();
        zzb.zzb(zzmj);
        zzb.zza((zzlu) zza5.zzi());
        zzb.zzc(i3);
        zza2.zza((zzma) zzb.zzi());
        return new zzga((zzlx) zza2.zzi(), i4);
    }

    public final zzgb zza() {
        return new zzee(this, zzlx.class);
    }

    public final zznr zzb() {
        return zznr.ASYMMETRIC_PRIVATE;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzmd.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzmd zzmd = (zzmd) zzaek;
        if (!zzmd.zzf().zzs()) {
            zzqs.zzc(zzmd.zza(), 0);
            zzeo.zza(zzmd.zze().zzb());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }

    public final /* synthetic */ zzaek zzg(zzaek zzaek) throws GeneralSecurityException {
        return ((zzmd) zzaek).zze();
    }
}
