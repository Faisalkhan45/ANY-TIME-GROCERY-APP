package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaeb implements zzaei {
    private final zzaei[] zza;

    zzaeb(zzaei... zzaeiArr) {
        this.zza = zzaeiArr;
    }

    public final zzaeh zzb(Class cls) {
        zzaei[] zzaeiArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzaei zzaei = zzaeiArr[i];
            if (zzaei.zzc(cls)) {
                return zzaei.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        zzaei[] zzaeiArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzaeiArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
