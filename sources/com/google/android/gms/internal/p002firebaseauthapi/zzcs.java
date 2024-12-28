package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcs extends zzgc {
    zzcs() {
        super(zzku.class, new zzcq(zzap.class));
    }

    static /* bridge */ /* synthetic */ zzga zzg(int i, int i2) {
        zzkw zzb = zzkx.zzb();
        zzb.zza(i);
        return new zzga((zzkx) zzb.zzi(), i2);
    }

    public final zzgb zza() {
        return new zzcr(this, zzkx.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzku.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzku zzku = (zzku) zzaek;
        zzqs.zzc(zzku.zza(), 0);
        zzqs.zzb(zzku.zze().zzd());
    }

    public final int zzf() {
        return 2;
    }
}
