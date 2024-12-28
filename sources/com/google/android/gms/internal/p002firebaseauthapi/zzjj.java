package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjj {
    private final zzjc zza;
    private final List zzb;
    @Nullable
    private final Integer zzc;

    /* synthetic */ zzjj(zzjc zzjc, List list, Integer num, zzji zzji) {
        this.zza = zzjc;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjj)) {
            return false;
        }
        zzjj zzjj = (zzjj) obj;
        if (this.zza.equals(zzjj.zza) && this.zzb.equals(zzjj.zzb)) {
            Integer num = this.zzc;
            Integer num2 = zzjj.zzc;
            if (num == num2) {
                return true;
            }
            if (num == null || !num.equals(num2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", new Object[]{this.zza, this.zzb, this.zzc});
    }
}
