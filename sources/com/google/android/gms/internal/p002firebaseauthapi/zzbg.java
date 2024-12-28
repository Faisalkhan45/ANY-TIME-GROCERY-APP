package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbg {
    public static zzbf zza(String str) throws GeneralSecurityException {
        zzbf zzbf = (zzbf) zzbz.zzl().get(str);
        if (zzbf != null) {
            return zzbf;
        }
        throw new GeneralSecurityException("cannot find key template: ".concat(str));
    }
}
