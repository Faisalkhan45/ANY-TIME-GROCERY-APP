package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzuk implements zzyg {
    final /* synthetic */ zzul zza;

    zzuk(zzul zzul) {
        this.zza = zzul;
    }

    public final void zza(String str) {
        this.zza.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabj zzabj = (zzabj) obj;
        if (TextUtils.isEmpty(zzabj.zzb()) || TextUtils.isEmpty(zzabj.zzc())) {
            this.zza.zzb.zzh(zzai.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        this.zza.zzc.zzO(new zzzy(zzabj.zzc(), zzabj.zzb(), Long.valueOf(zzaaa.zza(zzabj.zzb())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza.zzb, this);
    }
}
