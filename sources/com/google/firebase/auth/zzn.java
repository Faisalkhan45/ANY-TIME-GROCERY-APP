package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzzy;
import com.google.firebase.auth.internal.zzbk;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzn implements zzbk {
    final /* synthetic */ FirebaseAuth zza;

    zzn(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzzy zzzy, FirebaseUser firebaseUser) {
        FirebaseAuth.zzH(this.zza, firebaseUser, zzzy, true, true);
    }

    public final void zzb(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
