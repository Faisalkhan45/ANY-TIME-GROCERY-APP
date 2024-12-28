package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzlx extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzlx zzb;
    private zzma zzd;

    static {
        zzlx zzlx = new zzlx();
        zzb = zzlx;
        zzadf.zzG(zzlx.class, zzlx);
    }

    private zzlx() {
    }

    public static zzlw zza() {
        return (zzlw) zzb.zzt();
    }

    public static zzlx zzc(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzlx) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zze(zzlx zzlx, zzma zzma) {
        zzma.getClass();
        zzlx.zzd = zzma;
    }

    public final zzma zzd() {
        zzma zzma = this.zzd;
        return zzma == null ? zzma.zzd() : zzma;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzd"});
            case 3:
                return new zzlx();
            case 4:
                return new zzlw((zzlv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
