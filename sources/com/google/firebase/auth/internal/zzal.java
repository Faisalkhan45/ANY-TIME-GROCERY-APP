package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzal implements Runnable {
    final /* synthetic */ zzam zza;
    private final String zzb;

    zzal(zzam zzam, String str) {
        this.zza = zzam;
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    public final void run() {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzb));
        if (instance.getCurrentUser() != null) {
            Task accessToken = instance.getAccessToken(true);
            zzam.zzg.v("Token refreshing started", new Object[0]);
            accessToken.addOnFailureListener(new zzak(this));
        }
    }
}
