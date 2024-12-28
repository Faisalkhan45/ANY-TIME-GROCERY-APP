package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzci  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzci extends zzgb {
    final /* synthetic */ zzcj zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzci(zzcj zzcj, Class cls) {
        super(cls);
        this.zza = zzcj;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzjz zzjz = (zzjz) zzaek;
        new zzcm();
        zzkc zzf = zzcl.zzf(zzjz.zzd());
        zzaek zza2 = new zzih().zza().zza(zzjz.zze());
        zzjv zzb = zzjw.zzb();
        zzb.zza(zzf);
        zzb.zzb((zzmt) zza2);
        zzb.zzc(0);
        return (zzjw) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzjz.zzc(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", zzcj.zzg(16, 16, 32, 16, 5, 1));
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzcj.zzg(16, 16, 32, 16, 5, 3));
        hashMap.put("AES256_CTR_HMAC_SHA256", zzcj.zzg(32, 16, 32, 32, 5, 1));
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzcj.zzg(32, 16, 32, 32, 5, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzjz zzjz = (zzjz) zzaek;
        ((zzcl) new zzcm().zza()).zzd(zzjz.zzd());
        new zzih().zza().zzd(zzjz.zze());
        zzqs.zzb(zzjz.zzd().zza());
    }
}
