package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzai;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zztq implements zzyg {
    final /* synthetic */ EmailAuthCredential zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    zztq(zzvf zzvf, EmailAuthCredential emailAuthCredential, zzxa zzxa) {
        this.zzc = zzvf;
        this.zza = emailAuthCredential;
        this.zzb = zzxa;
    }

    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zzc.zzN(new zzzg(this.zza, ((zzzy) obj).zze()), this.zzb);
    }
}
