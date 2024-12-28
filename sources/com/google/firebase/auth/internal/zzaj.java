package com.google.firebase.auth.internal;

import com.google.firebase.auth.SignInMethodQueryResult;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaj implements SignInMethodQueryResult {
    private final List zza;

    public zzaj(List list) {
        this.zza = list;
    }

    public final List<String> getSignInMethods() {
        return this.zza;
    }
}
