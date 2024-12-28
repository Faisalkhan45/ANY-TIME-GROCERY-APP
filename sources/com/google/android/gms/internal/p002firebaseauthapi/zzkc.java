package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzkc extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzkc zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzki zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zzkc zzkc = new zzkc();
        zzb = zzkc;
        zzadf.zzG(zzkc.class, zzkc);
    }

    private zzkc() {
    }

    public static zzkb zzb() {
        return (zzkb) zzb.zzt();
    }

    public static zzkc zzd() {
        return zzb;
    }

    public static zzkc zze(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzkc) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzi(zzkc zzkc, zzki zzki) {
        zzki.getClass();
        zzkc.zze = zzki;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzki zzf() {
        zzki zzki = this.zze;
        return zzki == null ? zzki.zzd() : zzki;
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
                return new zzkc();
            case 4:
                return new zzkb((zzka) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
