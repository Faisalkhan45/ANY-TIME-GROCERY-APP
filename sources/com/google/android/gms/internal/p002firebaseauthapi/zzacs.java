package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzacs {
    static final zzacs zza = new zzacs(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private final Map zzd;

    zzacs() {
        this.zzd = new HashMap();
    }

    public static zzacs zza() {
        return zza;
    }

    public final zzadd zzb(zzaek zzaek, int i) {
        return (zzadd) this.zzd.get(new zzacr(zzaek, i));
    }

    zzacs(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
