package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzun  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzun implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zzun(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabc zzabc = (zzabc) obj;
        this.zzb.zzO(new zzzy(zzabc.zzd(), zzabc.zzc(), Long.valueOf(zzabc.zzb()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzabc.zze()), (zze) null, this.zza, this);
    }
}
