package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjn extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzjn zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;
    private zzjt zzf;

    static {
        zzjn zzjn = new zzjn();
        zzb = zzjn;
        zzadf.zzG(zzjn.class, zzjn);
    }

    private zzjn() {
    }

    public static zzjm zzb() {
        return (zzjm) zzb.zzt();
    }

    public static zzjn zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzjn) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzi(zzjn zzjn, zzjt zzjt) {
        zzjt.getClass();
        zzjn.zzf = zzjt;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzjt zze() {
        zzjt zzjt = this.zzf;
        return zzjt == null ? zzjt.zzd() : zzjt;
    }

    public final zzacc zzf() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzjn();
            case 4:
                return new zzjm((zzjl) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
