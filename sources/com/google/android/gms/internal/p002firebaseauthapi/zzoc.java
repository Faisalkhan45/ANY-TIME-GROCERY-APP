package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzoc extends zzadb implements zzael {
    private zzoc() {
        super(zzof.zzb);
    }

    public final int zza() {
        return ((zzof) this.zza).zza();
    }

    public final zzoc zzb(zzoe zzoe) {
        zzm();
        zzof.zzi((zzof) this.zza, zzoe);
        return this;
    }

    public final zzoc zzc(int i) {
        zzm();
        ((zzof) this.zza).zzd = i;
        return this;
    }

    public final zzoe zzd(int i) {
        return ((zzof) this.zza).zzd(i);
    }

    public final List zze() {
        return Collections.unmodifiableList(((zzof) this.zza).zzg());
    }

    /* synthetic */ zzoc(zzob zzob) {
        super(zzof.zzb);
    }
}
