package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcc {
    public static final String zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    public static final String zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
    @Deprecated
    public static final zzpb zzc;
    @Deprecated
    public static final zzpb zzd;
    @Deprecated
    public static final zzpb zze;

    static {
        new zzcj();
        new zzcs();
        new zzcv();
        new zzcp();
        new zzdb();
        new zzdf();
        new zzcy();
        new zzdi();
        zzpb zzb2 = zzpb.zzb();
        zzc = zzb2;
        zzd = zzb2;
        zze = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbz.zzo(new zzcg());
        zzit.zza();
        zzbz.zzn(new zzcj(), true);
        zzbz.zzn(new zzcs(), true);
        if (!zzdw.zzb()) {
            zzbz.zzn(new zzcp(), true);
            zzcv.zzg(true);
            zzbz.zzn(new zzcy(), true);
            zzbz.zzn(new zzdb(), true);
            zzbz.zzn(new zzdf(), true);
            zzbz.zzn(new zzdi(), true);
        }
    }
}
