package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvb implements zzyg {
    final /* synthetic */ String zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zzvb(zzvf zzvf, String str, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = str;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzy = (zzzy) obj;
        zzaao zzaao = new zzaao();
        zzaao.zze(zzzy.zze());
        zzaao.zzd(this.zza);
        zzvf.zze(this.zzc, this.zzb, zzzy, zzaao, this);
    }
}
