package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzacv {
    private static final zzact zza = new zzacu();
    private static final zzact zzb;

    static {
        zzact zzact;
        try {
            zzact = (zzact) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzact = null;
        }
        zzb = zzact;
    }

    static zzact zza() {
        zzact zzact = zzb;
        if (zzact != null) {
            return zzact;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzact zzb() {
        return zza;
    }
}
