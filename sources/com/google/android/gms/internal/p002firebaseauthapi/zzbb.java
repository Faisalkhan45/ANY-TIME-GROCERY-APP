package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzbb implements zzbc {
    final /* synthetic */ zzgx zza;
    final /* synthetic */ zzgc zzb;

    zzbb(zzgx zzgx, zzgc zzgc) {
        this.zza = zzgx;
        this.zzb = zzgc;
    }

    public final zzax zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzbx(this.zza, this.zzb, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzax zzb() {
        zzgx zzgx = this.zza;
        return new zzbx(zzgx, this.zzb, zzgx.zzj());
    }

    public final Class zzc() {
        return this.zza.getClass();
    }

    public final Class zzd() {
        return this.zzb.getClass();
    }

    public final Set zze() {
        return this.zza.zzm();
    }
}
