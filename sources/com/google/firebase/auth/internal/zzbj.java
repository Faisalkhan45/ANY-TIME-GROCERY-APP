package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.p002firebaseauthapi.zzg;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbj implements Executor {
    private static final zzbj zza = new zzbj();
    private final Handler zzb = new zzg(Looper.getMainLooper());

    private zzbj() {
    }

    public static zzbj zza() {
        return zza;
    }

    public final void execute(Runnable runnable) {
        this.zzb.post(runnable);
    }
}
