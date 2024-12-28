package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzya  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzya {
    private final zzyb zza;
    private final TaskCompletionSource zzb;

    public zzya(zzyb zzyb, TaskCompletionSource taskCompletionSource) {
        this.zza = zzyb;
        this.zzb = taskCompletionSource;
    }

    public final void zza(Object obj, Status status) {
        FirebaseUser firebaseUser;
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            zzyb zzyb = this.zza;
            if (zzyb.zzs != null) {
                TaskCompletionSource taskCompletionSource = this.zzb;
                FirebaseAuth instance = FirebaseAuth.getInstance(zzyb.zzd);
                zzyb zzyb2 = this.zza;
                zztm zztm = zzyb2.zzs;
                if ("reauthenticateWithCredential".equals(zzyb2.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) {
                    firebaseUser = this.zza.zze;
                } else {
                    firebaseUser = null;
                }
                taskCompletionSource.setException(zzxc.zzc(instance, zztm, firebaseUser));
                return;
            }
            AuthCredential authCredential = zzyb.zzp;
            if (authCredential != null) {
                this.zzb.setException(zzxc.zzb(status, authCredential, zzyb.zzq, zzyb.zzr));
            } else {
                this.zzb.setException(zzxc.zza(status));
            }
        } else {
            this.zzb.setResult(obj);
        }
    }
}
