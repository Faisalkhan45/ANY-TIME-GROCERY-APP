package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzit  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzit {
    @Deprecated
    public static final zzpb zza;
    @Deprecated
    public static final zzpb zzb;
    @Deprecated
    public static final zzpb zzc;

    static {
        new zzih();
        zzpb zzb2 = zzpb.zzb();
        zza = zzb2;
        zzb = zzb2;
        zzc = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbz.zzo(new zziy());
        zzbz.zzn(new zzih(), true);
        zzis.zza();
        if (!zzdw.zzb()) {
            zzbz.zzn(new zzhq(), true);
            zzia.zza();
        }
    }
}
