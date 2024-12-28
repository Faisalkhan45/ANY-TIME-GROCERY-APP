package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzdz extends zzgc {
    zzdz() {
        super(zzlg.class, new zzdx(zzat.class));
    }

    public final zzgb zza() {
        return new zzdy(this, zzlj.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzlg.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzlg zzlg = (zzlg) zzaek;
        zzqs.zzc(zzlg.zza(), 0);
        if (zzlg.zze().zzd() != 64) {
            int zzd = zzlg.zze().zzd();
            throw new InvalidKeyException("invalid key size: " + zzd + ". Valid keys must have 64 bytes.");
        }
    }
}
