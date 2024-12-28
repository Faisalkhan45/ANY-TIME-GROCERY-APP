package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzub  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzub implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zzub(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabh zzabh = (zzabh) obj;
        this.zzb.zzO(new zzzy(zzabh.zze(), zzabh.zzc(), Long.valueOf(zzabh.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzabh.zzg()), (zze) null, this.zza, this);
    }
}
