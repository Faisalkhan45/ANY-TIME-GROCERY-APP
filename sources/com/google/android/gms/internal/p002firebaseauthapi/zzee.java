package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzee  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzee extends zzgb {
    final /* synthetic */ zzef zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzee(zzef zzef, Class cls) {
        super(cls);
        this.zza = zzef;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        zzlx zzlx = (zzlx) zzaek;
        KeyPair zzd = zzpx.zzd(zzpx.zzl(zzeo.zzc(zzlx.zzd().zze().zzf())));
        ECPoint w = ((ECPublicKey) zzd.getPublic()).getW();
        zzmf zzc = zzmg.zzc();
        zzc.zzb(0);
        zzc.zza(zzlx.zzd());
        zzc.zzc(zzacc.zzn(w.getAffineX().toByteArray()));
        zzc.zzd(zzacc.zzn(w.getAffineY().toByteArray()));
        zzmc zzb = zzmd.zzb();
        zzb.zzc(0);
        zzb.zzb((zzmg) zzc.zzi());
        zzb.zza(zzacc.zzn(((ECPrivateKey) zzd.getPrivate()).getS().toByteArray()));
        return (zzmd) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzlx.zzc(zzacc, zzacs.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzef.zzi(4, 5, 3, zzbg.zza("AES128_GCM"), zzef.zza, 1));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzef.zzi(4, 5, 3, zzbg.zza("AES128_GCM"), zzef.zza, 3));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzef.zzi(4, 5, 4, zzbg.zza("AES128_GCM"), zzef.zza, 1));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzef.zzi(4, 5, 4, zzbg.zza("AES128_GCM"), zzef.zza, 3));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzef.zzi(4, 5, 4, zzbg.zza("AES128_GCM"), zzef.zza, 3));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzef.zzi(4, 5, 3, zzbg.zza("AES128_CTR_HMAC_SHA256"), zzef.zza, 1));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzef.zzi(4, 5, 3, zzbg.zza("AES128_CTR_HMAC_SHA256"), zzef.zza, 3));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzef.zzi(4, 5, 4, zzbg.zza("AES128_CTR_HMAC_SHA256"), zzef.zza, 1));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzef.zzi(4, 5, 4, zzbg.zza("AES128_CTR_HMAC_SHA256"), zzef.zza, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzeo.zza(((zzlx) zzaek).zzd());
    }
}
