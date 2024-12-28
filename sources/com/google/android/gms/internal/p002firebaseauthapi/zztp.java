package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zztp implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zztp(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabf zzabf = (zzabf) obj;
        if (zzabf.zzg()) {
            this.zza.zzf(new zztm(zzabf.zzd(), zzabf.zzf(), (zze) null));
            return;
        }
        this.zzb.zzO(new zzzy(zzabf.zze(), zzabf.zzc(), Long.valueOf(zzabf.zzb()), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
