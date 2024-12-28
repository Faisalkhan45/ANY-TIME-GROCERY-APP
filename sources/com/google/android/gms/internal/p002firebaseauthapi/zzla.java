package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzla  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzla extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzla zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;

    static {
        zzla zzla = new zzla();
        zzb = zzla;
        zzadf.zzG(zzla.class, zzla);
    }

    private zzla() {
    }

    public static zzkz zzb() {
        return (zzkz) zzb.zzt();
    }

    public static zzla zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzla) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzacc zze() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzd", "zze"});
            case 3:
                return new zzla();
            case 4:
                return new zzkz((zzky) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
