package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzde  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzde extends zzgb {
    final /* synthetic */ zzdf zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzde(zzdf zzdf, Class cls) {
        super(cls);
        this.zza = zzdf;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzos zzb = zzot.zzb();
        zzb.zza((zzow) zzaek);
        zzb.zzb(0);
        return (zzot) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzow.zzd(zzacc, zzacs.zza());
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzow zzow = (zzow) zzaek;
        if (zzow.zze().isEmpty() || !zzow.zzf()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
