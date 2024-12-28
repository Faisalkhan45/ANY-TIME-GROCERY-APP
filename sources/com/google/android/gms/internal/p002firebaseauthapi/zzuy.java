package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzuy implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    zzuy(zzvf zzvf, zzxa zzxa) {
        this.zzb = zzvf;
        this.zza = zzxa;
    }

    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaba zzaba = (zzaba) obj;
        if (!zzaba.zzm()) {
            zzvf.zzd(this.zzb, zzaba, this.zza, this);
        } else {
            this.zza.zzf(new zztm(zzaba.zzg(), zzaba.zzl(), zzaba.zzc()));
        }
    }
}
