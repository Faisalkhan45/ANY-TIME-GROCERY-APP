package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzif  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzif extends zzgw {
    zzif(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zza(zzaek zzaek) throws GeneralSecurityException {
        zzmt zzmt = (zzmt) zzaek;
        int zzf = zzmt.zzf().zzf();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzmt.zzg().zzt(), "HMAC");
        int zza = zzmt.zzf().zza();
        switch (zzf - 2) {
            case 1:
                return new zzqo(new zzqn("HMACSHA1", secretKeySpec), zza);
            case 2:
                return new zzqo(new zzqn("HMACSHA384", secretKeySpec), zza);
            case 3:
                return new zzqo(new zzqn("HMACSHA256", secretKeySpec), zza);
            case 4:
                return new zzqo(new zzqn("HMACSHA512", secretKeySpec), zza);
            case 5:
                return new zzqo(new zzqn("HMACSHA224", secretKeySpec), zza);
            default:
                throw new GeneralSecurityException("unknown hash");
        }
    }
}
