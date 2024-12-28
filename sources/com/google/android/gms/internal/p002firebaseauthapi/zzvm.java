package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzaj;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvm extends zzyb {
    private final zzrs zza;

    public zzvm(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zza = new zzrs(str, str2);
    }

    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public final void zzb() {
        List list;
        if (this.zzl.zzb() == null) {
            list = zzal.zzg();
        } else {
            list = (List) Preconditions.checkNotNull(this.zzl.zzb());
        }
        zzm(new zzaj(list));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzk(this.zza, this.zzc);
    }
}
