package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzui  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzui implements zzyg {
    final /* synthetic */ zzyg zza;
    final /* synthetic */ zzzy zzb;
    final /* synthetic */ zzuj zzc;

    zzui(zzuj zzuj, zzyg zzyg, zzzy zzzy) {
        this.zzc = zzuj;
        this.zza = zzyg;
        this.zzb = zzzy;
    }

    public final void zza(String str) {
        this.zzc.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb2 = ((zzzp) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzaao zzaao = new zzaao();
        zzaao.zze(this.zzb.zze());
        zzaao.zzb(this.zzc.zza);
        zzuj zzuj = this.zzc;
        zzvf.zzf(zzuj.zzc, zzuj.zzb, this.zzb, (zzzr) zzb2.get(0), zzaao, this.zza);
    }
}
