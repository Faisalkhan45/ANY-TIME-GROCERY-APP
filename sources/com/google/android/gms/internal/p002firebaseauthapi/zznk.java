package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zznk extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zznk zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zznn zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zznk zznk = new zznk();
        zzb = zznk;
        zzadf.zzG(zznk.class, zznk);
    }

    private zznk() {
    }

    public static zznj zzb() {
        return (zznj) zzb.zzt();
    }

    public static zznk zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zznk) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzh(zznk zznk, zznn zznn) {
        zznn.getClass();
        zznk.zze = zznn;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zznn zze() {
        zznn zznn = this.zze;
        return zznn == null ? zznn.zze() : zznn;
    }

    public final zzacc zzf() {
        return this.zzf;
    }

    public final boolean zzk() {
        return this.zze != null;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zznk();
            case 4:
                return new zznj((zzni) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
