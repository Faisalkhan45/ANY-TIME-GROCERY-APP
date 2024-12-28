package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzgg extends zzaw {
    private final zzgy zza;

    public zzgg(zzgy zzgy, @Nullable zzca zzca) throws GeneralSecurityException {
        int i = zzgd.zzb[zzgy.zzb().ordinal()];
        this.zza = zzgy;
    }

    public final zzbn zza() {
        zzgy zzgy = this.zza;
        return new zzgf(zzgy.zzg(), zzgy.zzc(), (zzge) null);
    }
}
