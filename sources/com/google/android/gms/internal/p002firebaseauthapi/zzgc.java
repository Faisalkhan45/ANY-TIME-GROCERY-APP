package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzgc {
    private final Class zza;
    private final Map zzb;
    private final Class zzc;

    @SafeVarargs
    protected zzgc(Class cls, zzgw... zzgwArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i <= 0) {
            zzgw zzgw = zzgwArr[i];
            if (!hashMap.containsKey(zzgw.zzb())) {
                hashMap.put(zzgw.zzb(), zzgw);
                i++;
            } else {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive ".concat(String.valueOf(zzgw.zzb().getCanonicalName())));
            }
        }
        this.zzc = zzgwArr[0].zzb();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public zzgb zza() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zznr zzb();

    public abstract zzaek zzc(zzacc zzacc) throws zzadn;

    public abstract String zzd();

    public abstract void zze(zzaek zzaek) throws GeneralSecurityException;

    public int zzf() {
        return 1;
    }

    public final Class zzj() {
        return this.zzc;
    }

    public final Class zzk() {
        return this.zza;
    }

    public final Object zzl(zzaek zzaek, Class cls) throws GeneralSecurityException {
        zzgw zzgw = (zzgw) this.zzb.get(cls);
        if (zzgw != null) {
            return zzgw.zza(zzaek);
        }
        String canonicalName = cls.getCanonicalName();
        throw new IllegalArgumentException("Requested primitive class " + canonicalName + " not supported.");
    }

    public final Set zzm() {
        return this.zzb.keySet();
    }
}
