package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzku  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzku extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzku zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;

    static {
        zzku zzku = new zzku();
        zzb = zzku;
        zzadf.zzG(zzku.class, zzku);
    }

    private zzku() {
    }

    public static zzkt zzb() {
        return (zzkt) zzb.zzt();
    }

    public static zzku zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzku) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzacc zze() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzd", "zze"});
            case 3:
                return new zzku();
            case 4:
                return new zzkt((zzks) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
