package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjz extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzjz zzb;
    private zzkf zzd;
    private zzmw zze;

    static {
        zzjz zzjz = new zzjz();
        zzb = zzjz;
        zzadf.zzG(zzjz.class, zzjz);
    }

    private zzjz() {
    }

    public static zzjy zza() {
        return (zzjy) zzb.zzt();
    }

    public static zzjz zzc(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzjz) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzf(zzjz zzjz, zzkf zzkf) {
        zzkf.getClass();
        zzjz.zzd = zzkf;
    }

    static /* synthetic */ void zzg(zzjz zzjz, zzmw zzmw) {
        zzmw.getClass();
        zzjz.zze = zzmw;
    }

    public final zzkf zzd() {
        zzkf zzkf = this.zzd;
        return zzkf == null ? zzkf.zzd() : zzkf;
    }

    public final zzmw zze() {
        zzmw zzmw = this.zze;
        return zzmw == null ? zzmw.zzd() : zzmw;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzjz();
            case 4:
                return new zzjy((zzjx) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
