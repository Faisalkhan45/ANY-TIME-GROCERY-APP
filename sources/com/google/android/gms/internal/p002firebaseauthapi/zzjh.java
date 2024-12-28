package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjh {
    private final zzbe zza;
    private final int zzb;
    private final zzbn zzc;

    /* synthetic */ zzjh(zzbe zzbe, int i, zzbn zzbn, zzjg zzjg) {
        this.zza = zzbe;
        this.zzb = i;
        this.zzc = zzbn;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjh)) {
            return false;
        }
        zzjh zzjh = (zzjh) obj;
        if (this.zza == zzjh.zza && this.zzb == zzjh.zzb && this.zzc.equals(zzjh.zzc)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb), Integer.valueOf(this.zzc.hashCode())});
    }

    public final String toString() {
        return String.format("(status=%s, keyId=%s, parameters='%s')", new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc});
    }

    public final int zza() {
        return this.zzb;
    }
}
