package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmd extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmd zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzmg zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zzmd zzmd = new zzmd();
        zzb = zzmd;
        zzadf.zzG(zzmd.class, zzmd);
    }

    private zzmd() {
    }

    public static zzmc zzb() {
        return (zzmc) zzb.zzt();
    }

    public static zzmd zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzmd) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzh(zzmd zzmd, zzmg zzmg) {
        zzmg.getClass();
        zzmd.zze = zzmg;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzmg zze() {
        zzmg zzmg = this.zze;
        return zzmg == null ? zzmg.zze() : zzmg;
    }

    public final zzacc zzf() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzmd();
            case 4:
                return new zzmc((zzmb) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
