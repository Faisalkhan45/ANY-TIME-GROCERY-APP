package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzg;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzvp extends zzyb {
    private final zzrq zza;

    public zzvp(String str) {
        super(1);
        Preconditions.checkNotEmpty(str, "refresh token cannot be null");
        this.zza = new zzrq(str);
    }

    public final String zza() {
        return "getAccessToken";
    }

    public final void zzb() {
        if (TextUtils.isEmpty(this.zzj.zzf())) {
            this.zzj.zzi(this.zza.zza());
        }
        ((zzg) this.zzf).zza(this.zzj, this.zze);
        zzm(zzay.zza(this.zzj.zze()));
    }

    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxb) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxb.zzj(this.zza, this.zzc);
    }
}
