package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzzy;
import com.google.firebase.auth.internal.zzg;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
class zzs implements zzg {
    final /* synthetic */ FirebaseAuth zza;

    zzs(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzzy zzzy, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(zzzy);
        Preconditions.checkNotNull(firebaseUser);
        firebaseUser.zzh(zzzy);
        this.zza.zzE(firebaseUser, zzzy, true);
    }
}
