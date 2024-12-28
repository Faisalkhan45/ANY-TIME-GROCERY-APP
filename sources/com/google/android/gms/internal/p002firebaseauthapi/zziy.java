package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zziy implements zzbv {
    /* access modifiers changed from: private */
    public static final Logger zza = Logger.getLogger(zziy.class.getName());
    /* access modifiers changed from: private */
    public static final byte[] zzb = {0};

    zziy() {
    }

    public final Class zza() {
        return zzbm.class;
    }

    public final Class zzb() {
        return zzbm.class;
    }

    public final /* bridge */ /* synthetic */ Object zzc(zzbu zzbu) throws GeneralSecurityException {
        for (List it : zzbu.zzd()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzbq zzbq = (zzbq) it2.next();
                    if (zzbq.zzb() instanceof zziu) {
                        zziu zziu = (zziu) zzbq.zzb();
                        zzqv zzb2 = zzqv.zzb(zzbq.zzf());
                        if (!zzb2.equals(zziu.zzc())) {
                            String valueOf = String.valueOf(zziu.zzb());
                            String obj = zziu.zzc().toString();
                            String obj2 = zzb2.toString();
                            throw new GeneralSecurityException("Mac Key with parameters " + valueOf + " has wrong output prefix (" + obj + ") instead of (" + obj2 + ")");
                        }
                    }
                }
            }
        }
        return new zzix(zzbu, (zziw) null);
    }
}
