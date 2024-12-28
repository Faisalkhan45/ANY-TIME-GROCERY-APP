package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzus implements zzyg {
    final /* synthetic */ zzyg zza;
    final /* synthetic */ zzzy zzb;
    final /* synthetic */ zzut zzc;

    zzus(zzut zzut, zzyg zzyg, zzzy zzzy) {
        this.zzc = zzut;
        this.zza = zzyg;
        this.zzb = zzzy;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb2 = ((zzzp) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzc.zza.zzi(this.zzb, (zzzr) zzb2.get(0));
        }
    }
}
