package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcj extends zzgc {
    zzcj() {
        super(zzjw.class, new zzch(zzap.class));
    }

    static /* bridge */ /* synthetic */ zzga zzg(int i, int i2, int i3, int i4, int i5, int i6) {
        zzke zzb = zzkf.zzb();
        zzkh zzb2 = zzki.zzb();
        zzb2.zza(16);
        zzb.zzb((zzki) zzb2.zzi());
        zzb.zza(i);
        zzmv zzb3 = zzmw.zzb();
        zzmy zzb4 = zzmz.zzb();
        zzb4.zzb(5);
        zzb4.zza(i4);
        zzb3.zzb((zzmz) zzb4.zzi());
        zzb3.zza(32);
        zzjy zza = zzjz.zza();
        zza.zza((zzkf) zzb.zzi());
        zza.zzb((zzmw) zzb3.zzi());
        return new zzga((zzjz) zza.zzi(), i6);
    }

    public final zzgb zza() {
        return new zzci(this, zzjz.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzjw.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzjw zzjw = (zzjw) zzaek;
        zzqs.zzc(zzjw.zza(), 0);
        new zzcm();
        zzcm.zzh(zzjw.zze());
        new zzih();
        zzih.zzh(zzjw.zzf());
    }

    public final int zzf() {
        return 2;
    }
}
