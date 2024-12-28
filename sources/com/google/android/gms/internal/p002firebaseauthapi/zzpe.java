package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzpe extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzpe zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;

    static {
        zzpe zzpe = new zzpe();
        zzb = zzpe;
        zzadf.zzG(zzpe.class, zzpe);
    }

    private zzpe() {
    }

    public static zzpd zzb() {
        return (zzpd) zzb.zzt();
    }

    public static zzpe zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzpe) zzadf.zzx(zzb, zzacc, zzacs);
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
                return new zzpe();
            case 4:
                return new zzpd((zzpc) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
