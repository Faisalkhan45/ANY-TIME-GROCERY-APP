package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzja  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzja {
    private HashMap zza = new HashMap();

    public final zzjc zza() {
        if (this.zza != null) {
            zzjc zzjc = new zzjc(Collections.unmodifiableMap(this.zza), (zzjb) null);
            this.zza = null;
            return zzjc;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
