package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzy implements Continuation {
    final /* synthetic */ FirebaseUser zza;

    zzy(FirebaseUser firebaseUser) {
        this.zza = firebaseUser;
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        return FirebaseAuth.getInstance(this.zza.zza()).zzi((ActionCodeSettings) null, (String) Preconditions.checkNotNull(((GetTokenResult) task.getResult()).getToken()));
    }
}
