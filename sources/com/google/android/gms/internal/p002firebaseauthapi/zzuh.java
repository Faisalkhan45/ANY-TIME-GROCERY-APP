package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzuh implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zzuh(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzy = (zzzy) obj;
        zzaao zzaao = new zzaao();
        zzaao.zze(zzzy.zze());
        zzaao.zzd((String) null);
        zzaao.zzg((String) null);
        zzvf.zze(this.zzb, this.zza, zzzy, zzaao, this);
    }
}
