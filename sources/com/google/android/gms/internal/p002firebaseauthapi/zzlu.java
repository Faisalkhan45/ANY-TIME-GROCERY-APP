package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzlu extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzlu zzb;
    private zznx zzd;

    static {
        zzlu zzlu = new zzlu();
        zzb = zzlu;
        zzadf.zzG(zzlu.class, zzlu);
    }

    private zzlu() {
    }

    public static zzlt zza() {
        return (zzlt) zzb.zzt();
    }

    public static zzlu zzc() {
        return zzb;
    }

    static /* synthetic */ void zze(zzlu zzlu, zznx zznx) {
        zznx.getClass();
        zzlu.zzd = zznx;
    }

    public final zznx zzd() {
        zznx zznx = this.zzd;
        return zznx == null ? zznx.zzc() : zznx;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzd"});
            case 3:
                return new zzlu();
            case 4:
                return new zzlt((zzls) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
