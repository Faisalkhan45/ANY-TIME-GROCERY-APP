package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcl extends zzgb {
    final /* synthetic */ zzcm zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcl(zzcm zzcm, Class cls) {
        super(cls);
        this.zza = zzcm;
    }

    public static final zzkc zzf(zzkf zzkf) throws GeneralSecurityException {
        zzkb zzb = zzkc.zzb();
        zzb.zzb(zzkf.zzf());
        zzb.zza(zzacc.zzn(zzqq.zza(zzkf.zza())));
        zzb.zzc(0);
        return (zzkc) zzb.zzi();
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        return zzf((zzkf) zzaek);
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzkf.zze(zzacc, zzacs.zza());
    }

    /* renamed from: zze */
    public final void zzd(zzkf zzkf) throws GeneralSecurityException {
        zzqs.zzb(zzkf.zza());
        zzcm.zzi(zzkf.zzf());
    }
}
