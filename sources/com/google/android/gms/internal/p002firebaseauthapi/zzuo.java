package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzuo implements zzyg {
    final /* synthetic */ zzup zza;

    zzuo(zzup zzup) {
        this.zza = zzup;
    }

    public final void zza(String str) {
        this.zza.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzj zzzj = (zzzj) obj;
        this.zza.zzc.zzO(new zzzy(zzzj.zzc(), zzzj.zzb(), Long.valueOf(zzaaa.zza(zzzj.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}
