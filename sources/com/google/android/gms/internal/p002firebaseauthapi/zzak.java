package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzak  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzak extends zzal {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzal zzc;

    zzak(zzal zzal, int i, int i2) {
        this.zzc = zzal;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzy.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    public final zzal zzf(int i, int i2) {
        zzy.zzc(i, i2, this.zzb);
        zzal zzal = this.zzc;
        int i3 = this.zza;
        return zzal.subList(i + i3, i2 + i3);
    }
}
