package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzdy extends zzgb {
    final /* synthetic */ zzdz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdy(zzdz zzdz, Class cls) {
        super(cls);
        this.zza = zzdz;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzlf zzb = zzlg.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(((zzlj) zzaek).zza())));
        zzb.zzb(0);
        return (zzlg) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzlj.zzd(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzli zzb = zzlj.zzb();
        zzb.zza(64);
        hashMap.put("AES256_SIV", new zzga((zzlj) zzb.zzi(), 1));
        zzli zzb2 = zzlj.zzb();
        zzb2.zza(64);
        hashMap.put("AES256_SIV_RAW", new zzga((zzlj) zzb2.zzi(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzlj zzlj = (zzlj) zzaek;
        if (zzlj.zza() != 64) {
            int zza2 = zzlj.zza();
            throw new InvalidAlgorithmParameterException("invalid key size: " + zza2 + ". Valid keys must have 64 bytes.");
        }
    }
}
