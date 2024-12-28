package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzgb {
    private final Class zza;

    public zzgb(Class cls) {
        this.zza = cls;
    }

    public abstract zzaek zza(zzaek zzaek) throws GeneralSecurityException;

    public abstract zzaek zzb(zzacc zzacc) throws zzadn;

    public Map zzc() throws GeneralSecurityException {
        return Collections.emptyMap();
    }

    public abstract void zzd(zzaek zzaek) throws GeneralSecurityException;

    public final Class zzg() {
        return this.zza;
    }
}
