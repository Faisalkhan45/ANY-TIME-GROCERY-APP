package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmw extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmw zzb;
    private zzmz zzd;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzmw zzmw = new zzmw();
        zzb = zzmw;
        zzadf.zzG(zzmw.class, zzmw);
    }

    private zzmw() {
    }

    public static zzmv zzb() {
        return (zzmv) zzb.zzt();
    }

    public static zzmw zzd() {
        return zzb;
    }

    public static zzmw zze(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzmw) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzg(zzmw zzmw, zzmz zzmz) {
        zzmz.getClass();
        zzmw.zzd = zzmz;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzmz zzf() {
        zzmz zzmz = this.zzd;
        return zzmz == null ? zzmz.zzd() : zzmz;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzmw();
            case 4:
                return new zzmv((zzmu) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
