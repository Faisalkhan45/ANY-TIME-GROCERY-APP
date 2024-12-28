package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzdh extends zzgb {
    final /* synthetic */ zzdi zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdh(zzdi zzdi, Class cls) {
        super(cls);
        this.zza = zzdi;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzph zzph = (zzph) zzaek;
        zzpd zzb = zzpe.zzb();
        zzb.zzb(0);
        zzb.zza(zzacc.zzn(zzqq.zza(32)));
        return (zzpe) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzph.zzc(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("XCHACHA20_POLY1305", new zzga(zzph.zzb(), 1));
        hashMap.put("XCHACHA20_POLY1305_RAW", new zzga(zzph.zzb(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzph zzph = (zzph) zzaek;
    }
}
