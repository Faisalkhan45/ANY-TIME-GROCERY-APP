package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.OnFailureListener;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzyr implements OnFailureListener {
    zzyr(zzyv zzyv) {
    }

    public final void onFailure(Exception exc) {
        zzyv.zza.e("SmsRetrieverClient failed to start: ".concat(String.valueOf(exc.getMessage())), new Object[0]);
    }
}
