package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzue  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzue implements zzyg {
    final /* synthetic */ zzabg zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zzue(zzvf zzvf, zzabg zzabg, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = zzabg;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zza.zzd(((zzzy) obj).zze());
        this.zzc.zza.zzt(this.zza, new zzud(this, this));
    }
}
