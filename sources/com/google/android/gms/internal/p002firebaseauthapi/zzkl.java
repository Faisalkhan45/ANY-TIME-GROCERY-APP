package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzkl extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzkl zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzkr zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zzkl zzkl = new zzkl();
        zzb = zzkl;
        zzadf.zzG(zzkl.class, zzkl);
    }

    private zzkl() {
    }

    public static zzkk zzb() {
        return (zzkk) zzb.zzt();
    }

    public static zzkl zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzkl) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzh(zzkl zzkl, zzkr zzkr) {
        zzkr.getClass();
        zzkl.zze = zzkr;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzkr zze() {
        zzkr zzkr = this.zze;
        return zzkr == null ? zzkr.zzd() : zzkr;
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
                return new zzkl();
            case 4:
                return new zzkk((zzkj) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
