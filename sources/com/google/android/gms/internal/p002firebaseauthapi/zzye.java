package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.ExecutorService;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzye  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class zzye {
    zzxb zza;
    ExecutorService zzb;

    public final Task zzP(zzyd zzyd) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new zzyc(this, zzyd, taskCompletionSource));
        return taskCompletionSource.getTask();
    }
}
