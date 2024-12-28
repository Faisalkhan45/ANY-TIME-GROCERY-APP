package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzlm extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzlm zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;

    static {
        zzlm zzlm = new zzlm();
        zzb = zzlm;
        zzadf.zzG(zzlm.class, zzlm);
    }

    private zzlm() {
    }

    public static zzll zzb() {
        return (zzll) zzb.zzt();
    }

    public static zzlm zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzlm) zzadf.zzx(zzb, zzacc, zzacs);
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
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzd", "zze"});
            case 3:
                return new zzlm();
            case 4:
                return new zzll((zzlk) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
