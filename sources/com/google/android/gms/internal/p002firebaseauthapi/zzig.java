package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzig  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzig extends zzgb {
    final /* synthetic */ zzih zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzig(zzih zzih, Class cls) {
        super(cls);
        this.zza = zzih;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzmw zzmw = (zzmw) zzaek;
        zzms zzb = zzmt.zzb();
        zzb.zzc(0);
        zzb.zzb(zzmw.zzf());
        zzb.zza(zzacc.zzn(zzqq.zza(zzmw.zza())));
        return (zzmt) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzmw.zze(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("HMAC_SHA256_128BITTAG", zzih.zzi(32, 16, 5, 1));
        hashMap.put("HMAC_SHA256_128BITTAG_RAW", zzih.zzi(32, 16, 5, 3));
        hashMap.put("HMAC_SHA256_256BITTAG", zzih.zzi(32, 32, 5, 1));
        hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzih.zzi(32, 32, 5, 3));
        hashMap.put("HMAC_SHA512_128BITTAG", zzih.zzi(64, 16, 6, 1));
        hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzih.zzi(64, 16, 6, 3));
        hashMap.put("HMAC_SHA512_256BITTAG", zzih.zzi(64, 32, 6, 1));
        hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzih.zzi(64, 32, 6, 3));
        hashMap.put("HMAC_SHA512_512BITTAG", zzih.zzi(64, 64, 6, 1));
        hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzih.zzi(64, 64, 6, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzmw zzmw = (zzmw) zzaek;
        if (zzmw.zza() >= 16) {
            zzih.zzn(zzmw.zzf());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
