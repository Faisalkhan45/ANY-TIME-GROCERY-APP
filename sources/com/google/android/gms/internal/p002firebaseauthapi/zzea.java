package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzea  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzea {
    public static final String zza = "type.googleapis.com/google.crypto.tink.AesSivKey";
    @Deprecated
    public static final zzpb zzb = zzpb.zzb();
    @Deprecated
    public static final zzpb zzc = zzpb.zzb();

    static {
        new zzdz();
        try {
            zzbz.zzo(new zzec());
            if (!zzdw.zzb()) {
                zzbz.zzn(new zzdz(), true);
            }
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
