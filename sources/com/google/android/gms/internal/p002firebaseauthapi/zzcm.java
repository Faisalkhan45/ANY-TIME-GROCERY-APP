package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcm extends zzgc {
    zzcm() {
        super(zzkc.class, new zzck(zzqk.class));
    }

    public static final void zzh(zzkc zzkc) throws GeneralSecurityException {
        zzqs.zzc(zzkc.zza(), 0);
        zzqs.zzb(zzkc.zzg().zzd());
        zzi(zzkc.zzf());
    }

    /* access modifiers changed from: private */
    public static final void zzi(zzki zzki) throws GeneralSecurityException {
        if (zzki.zza() < 12 || zzki.zza() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    public final zzgb zza() {
        return new zzcl(this, zzkf.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzkc.zze(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzh((zzkc) zzaek);
    }
}
