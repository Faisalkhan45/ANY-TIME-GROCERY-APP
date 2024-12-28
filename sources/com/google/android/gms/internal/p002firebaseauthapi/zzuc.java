package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzuc implements zzyg {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzxa zzc;
    final /* synthetic */ zzvf zzd;

    zzuc(zzvf zzvf, String str, String str2, zzxa zzxa) {
        this.zzd = zzvf;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzxa;
    }

    public final void zza(String str) {
        this.zzc.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzy = (zzzy) obj;
        zzaao zzaao = new zzaao();
        zzaao.zze(zzzy.zze());
        zzaao.zzd(this.zza);
        zzaao.zzg(this.zzb);
        zzvf.zze(this.zzd, this.zzc, zzzy, zzaao, this);
    }
}
