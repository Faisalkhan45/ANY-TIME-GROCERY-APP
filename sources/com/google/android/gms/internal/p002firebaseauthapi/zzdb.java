package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzdb extends zzgc {
    zzdb() {
        super(zzon.class, new zzcz(zzap.class));
    }

    public final zzgb zza() {
        return new zzda(this, zzoq.class);
    }

    public final zznr zzb() {
        return zznr.REMOTE;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzon.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzqs.zzc(((zzon) zzaek).zza(), 0);
    }
}
