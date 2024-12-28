package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzba  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzba implements zzbc {
    final /* synthetic */ zzgc zza;

    zzba(zzgc zzgc) {
        this.zza = zzgc;
    }

    public final zzax zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzaz(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzax zzb() {
        zzgc zzgc = this.zza;
        return new zzaz(zzgc, zzgc.zzj());
    }

    public final Class zzc() {
        return this.zza.getClass();
    }

    public final Class zzd() {
        return null;
    }

    public final Set zze() {
        return this.zza.zzm();
    }
}
