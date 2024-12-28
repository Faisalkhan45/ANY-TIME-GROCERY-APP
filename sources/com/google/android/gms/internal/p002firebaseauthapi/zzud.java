package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzud implements zzyg {
    final /* synthetic */ zzyg zza;
    final /* synthetic */ zzue zzb;

    zzud(zzue zzue, zzyg zzyg) {
        this.zzb = zzue;
        this.zza = zzyg;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabh zzabh = (zzabh) obj;
        if (!TextUtils.isEmpty(zzabh.zzf())) {
            this.zzb.zzb.zzg(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zzd(zzabh.zzd(), zzabh.zzf()));
            return;
        }
        this.zzb.zzc.zzO(new zzzy(zzabh.zze(), zzabh.zzc(), Long.valueOf(zzabh.zzb()), "Bearer"), (String) null, "phone", Boolean.valueOf(zzabh.zzg()), (zze) null, this.zzb.zzb, this.zza);
    }
}
