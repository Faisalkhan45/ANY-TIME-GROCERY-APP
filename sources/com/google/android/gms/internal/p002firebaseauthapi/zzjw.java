package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjw extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzjw zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzkc zze;
    private zzmt zzf;

    static {
        zzjw zzjw = new zzjw();
        zzb = zzjw;
        zzadf.zzG(zzjw.class, zzjw);
    }

    private zzjw() {
    }

    public static zzjv zzb() {
        return (zzjv) zzb.zzt();
    }

    public static zzjw zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzjw) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzh(zzjw zzjw, zzkc zzkc) {
        zzkc.getClass();
        zzjw.zze = zzkc;
    }

    static /* synthetic */ void zzi(zzjw zzjw, zzmt zzmt) {
        zzmt.getClass();
        zzjw.zzf = zzmt;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzkc zze() {
        zzkc zzkc = this.zze;
        return zzkc == null ? zzkc.zzd() : zzkc;
    }

    public final zzmt zzf() {
        zzmt zzmt = this.zzf;
        return zzmt == null ? zzmt.zzd() : zzmt;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzjw();
            case 4:
                return new zzjv((zzju) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
