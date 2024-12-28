package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzld  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzld extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzld zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private int zze;

    static {
        zzld zzld = new zzld();
        zzb = zzld;
        zzadf.zzG(zzld.class, zzld);
    }

    private zzld() {
    }

    public static zzlc zzb() {
        return (zzlc) zzb.zzt();
    }

    public static zzld zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzld) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final int zza() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzd"});
            case 3:
                return new zzld();
            case 4:
                return new zzlc((zzlb) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
