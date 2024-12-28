package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzva implements zzyg {
    final /* synthetic */ UserProfileChangeRequest zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zzva(zzvf zzvf, UserProfileChangeRequest userProfileChangeRequest, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = userProfileChangeRequest;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzy = (zzzy) obj;
        zzaao zzaao = new zzaao();
        zzaao.zze(zzzy.zze());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzaao.zzc(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzaao.zzh(this.zza.zza());
        }
        zzvf.zze(this.zzc, this.zzb, zzzy, zzaao, this);
    }
}
