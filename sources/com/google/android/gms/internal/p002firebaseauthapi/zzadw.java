package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzadw extends zzady {
    private zzadw() {
        super((zzadx) null);
    }

    /* synthetic */ zzadw(zzadv zzadv) {
        super((zzadx) null);
    }

    /* access modifiers changed from: package-private */
    public final List zza(Object obj, long j) {
        int i;
        zzadk zzadk = (zzadk) zzafx.zzf(obj, j);
        if (zzadk.zzc()) {
            return zzadk;
        }
        int size = zzadk.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        zzadk zzd = zzadk.zzd(i);
        zzafx.zzs(obj, j, zzd);
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        ((zzadk) zzafx.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Object obj, Object obj2, long j) {
        zzadk zzadk = (zzadk) zzafx.zzf(obj, j);
        zzadk zzadk2 = (zzadk) zzafx.zzf(obj2, j);
        int size = zzadk.size();
        int size2 = zzadk2.size();
        if (size > 0 && size2 > 0) {
            if (!zzadk.zzc()) {
                zzadk = zzadk.zzd(size2 + size);
            }
            zzadk.addAll(zzadk2);
        }
        if (size > 0) {
            zzadk2 = zzadk;
        }
        zzafx.zzs(obj, j, zzadk2);
    }
}
