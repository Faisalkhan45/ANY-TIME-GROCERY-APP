package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzko  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzko extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzko zzb;
    private zzkr zzd;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzko zzko = new zzko();
        zzb = zzko;
        zzadf.zzG(zzko.class, zzko);
    }

    private zzko() {
    }

    public static zzkn zzb() {
        return (zzkn) zzb.zzt();
    }

    public static zzko zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzko) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzf(zzko zzko, zzkr zzkr) {
        zzkr.getClass();
        zzko.zzd = zzkr;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzkr zze() {
        zzkr zzkr = this.zzd;
        return zzkr == null ? zzkr.zzd() : zzkr;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzd", "zze"});
            case 3:
                return new zzko();
            case 4:
                return new zzkn((zzkm) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
