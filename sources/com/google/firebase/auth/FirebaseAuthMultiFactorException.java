package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class FirebaseAuthMultiFactorException extends FirebaseAuthException {
    private MultiFactorResolver zza;

    public FirebaseAuthMultiFactorException(String str, String str2, MultiFactorResolver multiFactorResolver) {
        super(str, str2);
        this.zza = multiFactorResolver;
    }

    public MultiFactorResolver getResolver() {
        return this.zza;
    }
}
