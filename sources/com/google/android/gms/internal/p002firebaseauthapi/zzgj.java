package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzgj {
    public static final zzjd zza = new zzgi((zzgh) null);

    public static zzjj zza(zzbu zzbu) {
        zzbe zzbe;
        zzjf zzjf = new zzjf();
        zzjf.zzb(zzbu.zzb());
        for (List it : zzbu.zzd()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzbq zzbq = (zzbq) it2.next();
                    switch (zzbq.zzg() - 2) {
                        case 1:
                            zzbe = zzbe.zza;
                            break;
                        case 2:
                            zzbe = zzbe.zzb;
                            break;
                        case 3:
                            zzbe = zzbe.zzc;
                            break;
                        default:
                            throw new IllegalStateException("Unknown key status");
                    }
                    zzjf.zza(zzbe, zzbq.zza(), zzbq.zzc());
                }
            }
        }
        if (zzbu.zza() != null) {
            zzjf.zzc(zzbu.zza().zza());
        }
        try {
            return zzjf.zzd();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
