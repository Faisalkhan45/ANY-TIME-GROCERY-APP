package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzdf extends zzgc {
    zzdf() {
        super(zzot.class, new zzdd(zzap.class));
    }

    public final zzgb zza() {
        return new zzde(this, zzow.class);
    }

    public final zznr zzb() {
        return zznr.REMOTE;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzot.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzqs.zzc(((zzot) zzaek).zza(), 0);
    }
}
