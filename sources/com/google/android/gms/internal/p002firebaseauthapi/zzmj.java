package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmj extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmj zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zzmj zzmj = new zzmj();
        zzb = zzmj;
        zzadf.zzG(zzmj.class, zzmj);
    }

    private zzmj() {
    }

    public static zzmi zza() {
        return (zzmi) zzb.zzt();
    }

    public static zzmj zzc() {
        return zzb;
    }

    public final zzacc zzd() {
        return this.zzf;
    }

    public final int zzf() {
        int i;
        switch (this.zzd) {
            case 0:
                i = 2;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            case 4:
                i = 6;
                break;
            case 5:
                i = 7;
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

    public final int zzg() {
        int zzb2 = zzmq.zzb(this.zze);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzmj();
            case 4:
                return new zzmi((zzmh) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
