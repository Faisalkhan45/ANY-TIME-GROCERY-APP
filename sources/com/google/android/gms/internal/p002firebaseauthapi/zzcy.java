package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcy extends zzgc {
    zzcy() {
        super(zzlm.class, new zzcw(zzap.class));
    }

    public final zzgb zza() {
        return new zzcx(this, zzlp.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzlm.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzlm zzlm = (zzlm) zzaek;
        zzqs.zzc(zzlm.zza(), 0);
        if (zzlm.zze().zzd() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }
}
