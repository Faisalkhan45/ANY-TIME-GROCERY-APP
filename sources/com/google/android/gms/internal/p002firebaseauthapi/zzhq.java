package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhq extends zzgc {
    zzhq() {
        super(zzjn.class, new zzho(zzbm.class));
    }

    /* access modifiers changed from: private */
    public static void zzi(zzjt zzjt) throws GeneralSecurityException {
        if (zzjt.zza() < 10) {
            throw new GeneralSecurityException("tag size too short");
        } else if (zzjt.zza() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* access modifiers changed from: private */
    public static void zzn(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    public final zzgb zza() {
        return new zzhp(this, zzjq.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzjn.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzjn zzjn = (zzjn) zzaek;
        zzqs.zzc(zzjn.zza(), 0);
        zzn(zzjn.zzf().zzd());
        zzi(zzjn.zze());
    }
}
