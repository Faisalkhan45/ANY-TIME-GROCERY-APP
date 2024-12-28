package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcu extends zzgb {
    final /* synthetic */ zzcv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcu(zzcv zzcv, Class cls) {
        super(cls);
        this.zza = zzcv;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzkz zzb = zzla.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(((zzld) zzaek).zza())));
        zzb.zzb(0);
        return (zzla) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzld.zzd(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM_SIV", zzcv.zzh(16, 1));
        hashMap.put("AES128_GCM_SIV_RAW", zzcv.zzh(16, 3));
        hashMap.put("AES256_GCM_SIV", zzcv.zzh(32, 1));
        hashMap.put("AES256_GCM_SIV_RAW", zzcv.zzh(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzqs.zzb(((zzld) zzaek).zza());
    }
}
