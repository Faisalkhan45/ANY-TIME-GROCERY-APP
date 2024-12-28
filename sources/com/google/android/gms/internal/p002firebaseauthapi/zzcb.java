package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzcb {
    public static final Charset zza = Charset.forName(Key.STRING_CHARSET_NAME);

    public static zzok zza(zzof zzof) {
        zzoh zza2 = zzok.zza();
        zza2.zzb(zzof.zzb());
        for (zzoe zzoe : zzof.zzg()) {
            zzoi zzb = zzoj.zzb();
            zzb.zzc(zzoe.zzb().zzf());
            zzb.zzd(zzoe.zzk());
            zzb.zzb(zzoe.zze());
            zzb.zza(zzoe.zza());
            zza2.zza((zzoj) zzb.zzi());
        }
        return (zzok) zza2.zzi();
    }

    public static void zzb(zzof zzof) throws GeneralSecurityException {
        int zzb = zzof.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzoe zzoe : zzof.zzg()) {
            if (zzoe.zzk() == 3) {
                if (!zzoe.zzi()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(zzoe.zza())}));
                } else if (zzoe.zze() == zzoy.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(zzoe.zza())}));
                } else if (zzoe.zzk() != 2) {
                    if (zzoe.zza() == zzb) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    z2 &= zzoe.zzb().zzb() == zznr.ASYMMETRIC_PUBLIC;
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(zzoe.zza())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
