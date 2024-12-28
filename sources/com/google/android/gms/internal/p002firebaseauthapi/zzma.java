package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzma extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzma zzb;
    private zzmj zzd;
    private zzlu zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzma zzma = new zzma();
        zzb = zzma;
        zzadf.zzG(zzma.class, zzma);
    }

    private zzma() {
    }

    public static zzlz zzb() {
        return (zzlz) zzb.zzt();
    }

    public static zzma zzd() {
        return zzb;
    }

    static /* synthetic */ void zzf(zzma zzma, zzmj zzmj) {
        zzmj.getClass();
        zzma.zzd = zzmj;
    }

    static /* synthetic */ void zzg(zzma zzma, zzlu zzlu) {
        zzlu.getClass();
        zzma.zze = zzlu;
    }

    public final zzlu zza() {
        zzlu zzlu = this.zze;
        return zzlu == null ? zzlu.zzc() : zzlu;
    }

    public final zzmj zze() {
        zzmj zzmj = this.zzd;
        return zzmj == null ? zzmj.zzc() : zzmj;
    }

    public final int zzh() {
        int i;
        switch (this.zzf) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzma();
            case 4:
                return new zzlz((zzly) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
