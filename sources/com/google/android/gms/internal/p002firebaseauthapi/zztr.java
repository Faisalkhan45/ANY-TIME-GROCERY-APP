package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zztr implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zztr(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzh zzzh = (zzzh) obj;
        if (zzzh.zzg()) {
            this.zza.zzf(new zztm(zzzh.zzd(), zzzh.zzf(), (zze) null));
            return;
        }
        this.zzb.zzO(new zzzy(zzzh.zze(), zzzh.zzc(), Long.valueOf(zzzh.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzzh.zzh()), (zze) null, this.zza, this);
    }
}
