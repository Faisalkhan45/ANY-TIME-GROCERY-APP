package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzjq extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzjq zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzjt zze;

    static {
        zzjq zzjq = new zzjq();
        zzb = zzjq;
        zzadf.zzG(zzjq.class, zzjq);
    }

    private zzjq() {
    }

    public static zzjp zzb() {
        return (zzjp) zzb.zzt();
    }

    public static zzjq zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzjq) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzg(zzjq zzjq, zzjt zzjt) {
        zzjt.getClass();
        zzjq.zze = zzjt;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzjt zze() {
        zzjt zzjt = this.zze;
        return zzjt == null ? zzjt.zzd() : zzjt;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzjq();
            case 4:
                return new zzjp((zzjo) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
