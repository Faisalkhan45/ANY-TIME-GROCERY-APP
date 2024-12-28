package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Comparator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzabu implements Comparator {
    zzabu() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzacc zzacc = (zzacc) obj;
        zzacc zzacc2 = (zzacc) obj2;
        zzabt zzabt = new zzabt(zzacc);
        zzabt zzabt2 = new zzabt(zzacc2);
        while (zzabt.hasNext() && zzabt2.hasNext()) {
            int compareTo = Integer.valueOf(zzabt.zza() & 255).compareTo(Integer.valueOf(zzabt2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzacc.zzd()).compareTo(Integer.valueOf(zzacc2.zzd()));
    }
}
