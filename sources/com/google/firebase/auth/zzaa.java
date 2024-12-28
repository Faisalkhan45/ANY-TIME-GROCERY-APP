package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaa implements Continuation {
    final /* synthetic */ String zza;
    final /* synthetic */ ActionCodeSettings zzb;
    final /* synthetic */ FirebaseUser zzc;

    zzaa(FirebaseUser firebaseUser, String str, ActionCodeSettings actionCodeSettings) {
        this.zzc = firebaseUser;
        this.zza = str;
        this.zzb = actionCodeSettings;
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        return FirebaseAuth.getInstance(this.zzc.zza()).zzr((String) Preconditions.checkNotNull(((GetTokenResult) task.getResult()).getToken()), this.zza, this.zzb);
    }
}
