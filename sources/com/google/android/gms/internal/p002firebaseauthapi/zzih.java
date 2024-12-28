package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzih  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzih extends zzgc {
    public zzih() {
        super(zzmt.class, new zzif(zzbm.class));
    }

    public static final void zzh(zzmt zzmt) throws GeneralSecurityException {
        zzqs.zzc(zzmt.zza(), 0);
        if (zzmt.zzg().zzd() >= 16) {
            zzn(zzmt.zzf());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    static /* bridge */ /* synthetic */ zzga zzi(int i, int i2, int i3, int i4) {
        zzmv zzb = zzmw.zzb();
        zzmy zzb2 = zzmz.zzb();
        zzb2.zzb(i3);
        zzb2.zza(i2);
        zzb.zzb((zzmz) zzb2.zzi());
        zzb.zza(i);
        return new zzga((zzmw) zzb.zzi(), i4);
    }

    /* access modifiers changed from: private */
    public static void zzn(zzmz zzmz) throws GeneralSecurityException {
        if (zzmz.zza() >= 10) {
            switch (zzmz.zzf() - 2) {
                case 1:
                    if (zzmz.zza() > 20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 2:
                    if (zzmz.zza() > 48) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 3:
                    if (zzmz.zza() > 32) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 4:
                    if (zzmz.zza() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case 5:
                    if (zzmz.zza() > 28) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                default:
                    throw new GeneralSecurityException("unknown hash type");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final zzgb zza() {
        return new zzig(this, zzmw.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzmt.zze(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzh((zzmt) zzaek);
    }

    public final int zzf() {
        return 2;
    }
}
