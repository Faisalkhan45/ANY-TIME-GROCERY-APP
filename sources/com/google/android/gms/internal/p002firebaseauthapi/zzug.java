package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzug implements zzyg {
    final /* synthetic */ zzaay zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zzug(zzvf zzvf, zzaay zzaay, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = zzaay;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzd(true);
        this.zza.zzc(((zzzy) obj).zze());
        this.zzc.zza.zzq(this.zza, new zzuf(this, this));
    }
}
