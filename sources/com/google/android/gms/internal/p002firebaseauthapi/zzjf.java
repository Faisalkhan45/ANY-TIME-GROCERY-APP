package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjf {
    @Nullable
    private ArrayList zza = new ArrayList();
    private zzjc zzb = zzjc.zza;
    @Nullable
    private Integer zzc = null;

    public final zzjf zza(zzbe zzbe, int i, zzbn zzbn) {
        ArrayList arrayList = this.zza;
        if (arrayList != null) {
            arrayList.add(new zzjh(zzbe, i, zzbn, (zzjg) null));
            return this;
        }
        throw new IllegalStateException("addEntry cannot be called after build()");
    }

    public final zzjf zzb(zzjc zzjc) {
        if (this.zza != null) {
            this.zzb = zzjc;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zzjf zzc(int i) {
        if (this.zza != null) {
            this.zzc = Integer.valueOf(i);
            return this;
        }
        throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
    }

    public final zzjj zzd() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList arrayList = this.zza;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    int i2 = i + 1;
                    if (((zzjh) arrayList.get(i)).zza() != intValue) {
                        i = i2;
                    }
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            zzjj zzjj = new zzjj(this.zzb, Collections.unmodifiableList(this.zza), this.zzc, (zzji) null);
            this.zza = null;
            return zzjj;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
