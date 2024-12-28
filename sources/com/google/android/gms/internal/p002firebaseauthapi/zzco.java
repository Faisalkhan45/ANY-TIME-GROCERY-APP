package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzco extends zzgb {
    final /* synthetic */ zzcp zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzco(zzcp zzcp, Class cls) {
        super(cls);
        this.zza = zzcp;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzko zzko = (zzko) zzaek;
        zzkk zzb = zzkl.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(zzko.zza())));
        zzb.zzb(zzko.zze());
        zzb.zzc(0);
        return (zzkl) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzko.zzd(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzcp.zzg(16, 16, 1));
        hashMap.put("AES128_EAX_RAW", zzcp.zzg(16, 16, 3));
        hashMap.put("AES256_EAX", zzcp.zzg(32, 16, 1));
        hashMap.put("AES256_EAX_RAW", zzcp.zzg(32, 16, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzko zzko = (zzko) zzaek;
        zzqs.zzb(zzko.zza());
        if (zzko.zze().zza() != 12 && zzko.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
