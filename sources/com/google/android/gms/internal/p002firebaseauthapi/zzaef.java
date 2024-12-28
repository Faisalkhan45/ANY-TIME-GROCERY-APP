package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaef  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaef {
    zzaef() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzaee zzaee = (zzaee) obj;
        zzaed zzaed = (zzaed) obj2;
        if (zzaee.isEmpty()) {
            return 0;
        }
        Iterator it = zzaee.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzaee) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzaee zzaee = (zzaee) obj;
        zzaee zzaee2 = (zzaee) obj2;
        if (!zzaee2.isEmpty()) {
            if (!zzaee.zze()) {
                zzaee = zzaee.zzb();
            }
            zzaee.zzd(zzaee2);
        }
        return zzaee;
    }
}
