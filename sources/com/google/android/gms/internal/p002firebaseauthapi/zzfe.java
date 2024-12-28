package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfe extends zzgc {
    public zzfe() {
        super(zznn.class, new zzfd(zzav.class));
    }

    public final zznr zzb() {
        return zznr.ASYMMETRIC_PUBLIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zznn.zzf(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zznn zznn = (zznn) zzaek;
        zzqs.zzc(zznn.zza(), 0);
        if (zznn.zzl()) {
            zzff.zza(zznn.zzb());
            return;
        }
        throw new GeneralSecurityException("Missing HPKE key params.");
    }
}
