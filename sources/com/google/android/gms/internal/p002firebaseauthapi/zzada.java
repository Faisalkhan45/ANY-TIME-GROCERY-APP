package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzada  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzada implements zzaei {
    private static final zzada zza = new zzada();

    private zzada() {
    }

    public static zzada zza() {
        return zza;
    }

    public final zzaeh zzb(Class cls) {
        if (zzadf.class.isAssignableFrom(cls)) {
            try {
                return (zzaeh) zzadf.zzv(cls.asSubclass(zzadf.class)).zzj(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzadf.class.isAssignableFrom(cls);
    }
}
