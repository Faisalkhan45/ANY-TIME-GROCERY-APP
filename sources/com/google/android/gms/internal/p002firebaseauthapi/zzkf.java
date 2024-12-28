package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzkf extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzkf zzb;
    private zzki zzd;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzkf zzkf = new zzkf();
        zzb = zzkf;
        zzadf.zzG(zzkf.class, zzkf);
    }

    private zzkf() {
    }

    public static zzke zzb() {
        return (zzke) zzb.zzt();
    }

    public static zzkf zzd() {
        return zzb;
    }

    public static zzkf zze(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzkf) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzg(zzkf zzkf, zzki zzki) {
        zzki.getClass();
        zzkf.zzd = zzki;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzki zzf() {
        zzki zzki = this.zzd;
        return zzki == null ? zzki.zzd() : zzki;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzd", "zze"});
            case 3:
                return new zzkf();
            case 4:
                return new zzke((zzkd) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
