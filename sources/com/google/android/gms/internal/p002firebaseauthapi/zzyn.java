package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzyn extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zza;
    final /* synthetic */ String zzb;

    zzyn(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, String str) {
        this.zza = onVerificationStateChangedCallbacks;
        this.zzb = str;
    }

    public final void onCodeAutoRetrievalTimeOut(String str) {
        zzyp.zza.remove(this.zzb);
        this.zza.onCodeAutoRetrievalTimeOut(str);
    }

    public final void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        this.zza.onCodeSent(str, forceResendingToken);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        zzyp.zza.remove(this.zzb);
        this.zza.onVerificationCompleted(phoneAuthCredential);
    }

    public final void onVerificationFailed(FirebaseException firebaseException) {
        zzyp.zza.remove(this.zzb);
        this.zza.onVerificationFailed(firebaseException);
    }
}
