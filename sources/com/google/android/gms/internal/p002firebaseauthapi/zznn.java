package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zznn extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zznn zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zznh zze;
    /* access modifiers changed from: private */
    public zzacc zzf = zzacc.zzb;

    static {
        zznn zznn = new zznn();
        zzb = zznn;
        zzadf.zzG(zznn.class, zznn);
    }

    private zznn() {
    }

    public static zznm zzc() {
        return (zznm) zzb.zzt();
    }

    public static zznn zze() {
        return zzb;
    }

    public static zznn zzf(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zznn) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzi(zznn zznn, zznh zznh) {
        zznh.getClass();
        zznn.zze = zznh;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zznh zzb() {
        zznh zznh = this.zze;
        return zznh == null ? zznh.zzc() : zznh;
    }

    public final zzacc zzg() {
        return this.zzf;
    }

    public final boolean zzl() {
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
                return new zznn();
            case 4:
                return new zznm((zznl) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
