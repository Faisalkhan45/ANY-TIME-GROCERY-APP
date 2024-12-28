package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvo extends zzyb {
    private final zzro zza;

    public zzvo(PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str) {
        super(2);
        Preconditions.checkNotNull(phoneMultiFactorAssertion);
        Preconditions.checkNotEmpty(str);
        this.zza = new zzro(phoneMultiFactorAssertion.zza(), str);
    }

    public final String zza() {
        return "finalizeMfaSignIn";
    }

    public final void zzb() {
        zzx zzN = zzwy.zzN(this.zzd, this.zzk);
        FirebaseUser firebaseUser = this.zze;
        if (firebaseUser == null || firebaseUser.getUid().equalsIgnoreCase(zzN.getUid())) {
            ((zzg) this.zzf).zza(this.zzj, zzN);
            zzm(new zzr(zzN));
            return;
        }
        zzl(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzi(this.zza, this.zzc);
    }
}
