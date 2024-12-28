package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzfb extends zzgb {
    final /* synthetic */ zzfc zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfb(zzfc zzfc, Class cls) {
        super(cls);
        this.zza = zzfc;
    }

    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaek) throws GeneralSecurityException {
        byte[] bArr;
        byte[] bArr2;
        zzne zzne = (zzne) zzaek;
        switch (zzne.zzd().zzf() - 2) {
            case 1:
                bArr = zzqq.zza(32);
                bArr[0] = (byte) (bArr[0] | 7);
                byte b = bArr[31] & 63;
                bArr[31] = (byte) b;
                bArr[31] = (byte) (b | 128);
                bArr2 = zzqt.zzb(bArr);
                break;
            case 2:
            case 3:
            case 4:
                int zzg = zzff.zzg(zzne.zzd().zzf());
                KeyPair zzd = zzpx.zzd(zzpx.zzl(zzg));
                bArr2 = zzpx.zzm(zzg, 1, ((ECPublicKey) zzd.getPublic()).getW());
                bArr = ((ECPrivateKey) zzd.getPrivate()).getS().toByteArray();
                break;
            default:
                throw new GeneralSecurityException("Invalid KEM");
        }
        zznm zzc = zznn.zzc();
        zzc.zzc(0);
        zzc.zza(zzne.zzd());
        zzc.zzb(zzacc.zzn(bArr2));
        zznj zzb = zznk.zzb();
        zzb.zzc(0);
        zzb.zzb((zznn) zzc.zzi());
        zzb.zza(zzacc.zzn(bArr));
        return (zznk) zzb.zzi();
    }

    public final /* synthetic */ zzaek zzb(zzacc zzacc) throws zzadn {
        return zzne.zzc(zzacc, zzacs.zza());
    }

    public final Map zzc() {
        HashMap hashMap = new HashMap();
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzfc.zzh(3, 3, 3, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzfc.zzh(3, 3, 3, 3));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzfc.zzh(3, 3, 4, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzfc.zzh(3, 3, 4, 3));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzfc.zzh(3, 3, 5, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzfc.zzh(3, 3, 5, 3));
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzfc.zzh(4, 3, 3, 1));
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzfc.zzh(4, 3, 3, 3));
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzfc.zzh(4, 3, 4, 1));
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzfc.zzh(4, 3, 4, 3));
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zzfc.zzh(5, 4, 3, 1));
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzfc.zzh(5, 4, 3, 3));
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzfc.zzh(5, 4, 4, 1));
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzfc.zzh(5, 4, 4, 3));
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zzfc.zzh(6, 5, 3, 1));
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzfc.zzh(6, 5, 3, 3));
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzfc.zzh(6, 5, 4, 1));
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzfc.zzh(6, 5, 4, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaek zzaek) throws GeneralSecurityException {
        zzff.zza(((zzne) zzaek).zzd());
    }
}
