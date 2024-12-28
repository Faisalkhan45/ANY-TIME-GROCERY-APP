package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzhd {
    private final Class zza;
    private final zzqv zzb;

    /* synthetic */ zzhd(Class cls, zzqv zzqv, zzhc zzhc) {
        this.zza = cls;
        this.zzb = zzqv;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhd)) {
            return false;
        }
        zzhd zzhd = (zzhd) obj;
        if (!zzhd.zza.equals(this.zza) || !zzhd.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        String valueOf = String.valueOf(this.zzb);
        return simpleName + ", object identifier: " + valueOf;
    }
}
