package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzts  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzts implements zzyg {
    final /* synthetic */ zzyf zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzzy zzc;
    final /* synthetic */ zzaao zzd;
    final /* synthetic */ zzvf zze;

    zzts(zzvf zzvf, zzyf zzyf, zzxa zzxa, zzzy zzzy, zzaao zzaao) {
        this.zze = zzvf;
        this.zza = zzyf;
        this.zzb = zzxa;
        this.zzc = zzzy;
        this.zzd = zzaao;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb2 = ((zzzp) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzvf.zzf(this.zze, this.zzb, this.zzc, (zzzr) zzb2.get(0), this.zzd, this.zza);
        }
    }
}
