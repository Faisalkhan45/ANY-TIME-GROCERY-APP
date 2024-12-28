package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcr extends zzgb {
    final /* synthetic */ zzcs zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcr(zzcs zzcs, Class cls) {
        super(cls);
        this.zza = zzcs;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzkt zzb = zzku.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(((zzkx) zzaek).zza())));
        zzb.zzb(0);
        return (zzku) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzkx.zzd(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", zzcs.zzg(16, 1));
        hashMap.put("AES128_GCM_RAW", zzcs.zzg(16, 3));
        hashMap.put("AES256_GCM", zzcs.zzg(32, 1));
        hashMap.put("AES256_GCM_RAW", zzcs.zzg(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzqs.zzb(((zzkx) zzaek).zza());
    }
}
