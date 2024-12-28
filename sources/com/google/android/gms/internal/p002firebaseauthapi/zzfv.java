package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzfv {
    private final zzqv zza;
    private final Class zzb;

    /* synthetic */ zzfv(zzqv zzqv, Class cls, zzfu zzfu) {
        this.zza = zzqv;
        this.zzb = cls;
    }

    public static zzfv zzb(zzft zzft, zzqv zzqv, Class cls) {
        return new zzfs(zzqv, cls, zzft);
    }

    public abstract zzaw zza(zzha zzha, @Nullable zzca zzca) throws GeneralSecurityException;

    public final zzqv zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
