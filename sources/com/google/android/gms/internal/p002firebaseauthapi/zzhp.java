package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzhp extends zzgb {
    zzhp(zzhq zzhq, Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzjq zzjq = (zzjq) zzaek;
        zzjm zzb = zzjn.zzb();
        zzb.zzc(0);
        zzb.zza(zzacc.zzn(zzqq.zza(zzjq.zza())));
        zzb.zzb(zzjq.zze());
        return (zzjn) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzjq.zzd(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzjp zzb = zzjq.zzb();
        zzb.zza(32);
        zzjs zzb2 = zzjt.zzb();
        zzb2.zza(16);
        zzb.zzb((zzjt) zzb2.zzi());
        hashMap.put("AES_CMAC", new zzga((zzjq) zzb.zzi(), 1));
        zzjp zzb3 = zzjq.zzb();
        zzb3.zza(32);
        zzjs zzb4 = zzjt.zzb();
        zzb4.zza(16);
        zzb3.zzb((zzjt) zzb4.zzi());
        hashMap.put("AES256_CMAC", new zzga((zzjq) zzb3.zzi(), 1));
        zzjp zzb5 = zzjq.zzb();
        zzb5.zza(32);
        zzjs zzb6 = zzjt.zzb();
        zzb6.zza(16);
        zzb5.zzb((zzjt) zzb6.zzi());
        hashMap.put("AES256_CMAC_RAW", new zzga((zzjq) zzb5.zzi(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzjq zzjq = (zzjq) zzaek;
        zzhq.zzi(zzjq.zze());
        zzhq.zzn(zzjq.zza());
    }
}
