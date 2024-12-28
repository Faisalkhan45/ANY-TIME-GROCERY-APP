package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzeh extends zzgc {
    public zzeh() {
        super(zzmg.class, new zzeg(zzav.class));
    }

    public final zznr zzb() {
        return zznr.ASYMMETRIC_PUBLIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzmg.zzf(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzmg zzmg = (zzmg) zzaek;
        zzqs.zzc(zzmg.zza(), 0);
        zzeo.zza(zzmg.zzb());
    }
}
