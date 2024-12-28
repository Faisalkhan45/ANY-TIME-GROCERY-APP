package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzafm extends RuntimeException {
    public zzafm(zzaek zzaek) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzadn zza() {
        return new zzadn(getMessage());
    }
}
