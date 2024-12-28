package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmg extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmg zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzma zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;
    /* access modifiers changed from: private */
    public zzacc zzg = zzacc.zzb;

    static {
        zzmg zzmg = new zzmg();
        zzb = zzmg;
        zzadf.zzG(zzmg.class, zzmg);
    }

    private zzmg() {
    }

    public static zzmf zzc() {
        return (zzmf) zzb.zzt();
    }

    public static zzmg zze() {
        return zzb;
    }

    public static zzmg zzf(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzmg) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzk(zzmg zzmg, zzma zzma) {
        zzma.getClass();
        zzmg.zze = zzma;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzma zzb() {
        zzma zzma = this.zze;
        return zzma == null ? zzma.zzd() : zzma;
    }

    public final zzacc zzg() {
        return this.zzf;
    }

    public final zzacc zzh() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
            case 3:
                return new zzmg();
            case 4:
                return new zzmf((zzme) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
