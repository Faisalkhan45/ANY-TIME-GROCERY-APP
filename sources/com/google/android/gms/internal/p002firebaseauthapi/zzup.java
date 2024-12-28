package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzup  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzup implements zzyg {
    final /* synthetic */ zzzi zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zzup(zzvf zzvf, zzzi zzzi, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = zzzi;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzc(((zzzy) obj).zze());
        this.zzc.zza.zzd(this.zza, new zzuo(this));
    }
}
