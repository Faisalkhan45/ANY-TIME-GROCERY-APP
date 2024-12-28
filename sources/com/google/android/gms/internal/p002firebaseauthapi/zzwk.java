package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzwk extends zzyb {
    private final zzss zza;

    public zzwk(EmailAuthCredential emailAuthCredential) {
        super(2);
        Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
        this.zza = new zzss(emailAuthCredential);
    }

    public final String zza() {
        return "sendSignInLinkToEmail";
    }

    public final void zzb() {
        zzx zzN = zzwy.zzN(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzN);
        zzm(new zzr(zzN));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzx(this.zza, this.zzc);
    }
}
