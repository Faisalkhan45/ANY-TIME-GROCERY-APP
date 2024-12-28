package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmt extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmt zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzmz zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zzmt zzmt = new zzmt();
        zzb = zzmt;
        zzadf.zzG(zzmt.class, zzmt);
    }

    private zzmt() {
    }

    public static zzms zzb() {
        return (zzms) zzb.zzt();
    }

    public static zzmt zzd() {
        return zzb;
    }

    public static zzmt zze(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzmt) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzi(zzmt zzmt, zzmz zzmz) {
        zzmz.getClass();
        zzmt.zze = zzmz;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzmz zzf() {
        zzmz zzmz = this.zze;
        return zzmz == null ? zzmz.zzd() : zzmz;
    }

    public final zzacc zzg() {
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
                return new zzmt();
            case 4:
                return new zzms((zzmr) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
