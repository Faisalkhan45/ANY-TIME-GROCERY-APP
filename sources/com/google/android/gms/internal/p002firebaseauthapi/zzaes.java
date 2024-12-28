package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaes  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaes {
    private static final zzaes zza = new zzaes();
    private final zzaex zzb = new zzaec();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzaes() {
    }

    public static zzaes zza() {
        return zza;
    }

    public final zzaew zzb(Class cls) {
        zzadl.zzf(cls, "messageType");
        zzaew zzaew = (zzaew) this.zzc.get(cls);
        if (zzaew == null) {
            zzaew = this.zzb.zza(cls);
            zzadl.zzf(cls, "messageType");
            zzadl.zzf(zzaew, "schema");
            zzaew zzaew2 = (zzaew) this.zzc.putIfAbsent(cls, zzaew);
            return zzaew2 == null ? zzaew : zzaew2;
        }
    }
}
