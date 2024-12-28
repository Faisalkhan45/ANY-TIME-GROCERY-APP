package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcx extends zzgb {
    final /* synthetic */ zzcy zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcx(zzcy zzcy, Class cls) {
        super(cls);
        this.zza = zzcy;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzlp zzlp = (zzlp) zzaek;
        zzll zzb = zzlm.zzb();
        zzb.zzb(0);
        zzb.zza(zzacc.zzn(zzqq.zza(32)));
        return (zzlm) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzlp.zzc(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("CHACHA20_POLY1305", new zzga(zzlp.zzb(), 1));
        hashMap.put("CHACHA20_POLY1305_RAW", new zzga(zzlp.zzb(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzlp zzlp = (zzlp) zzaek;
    }
}
