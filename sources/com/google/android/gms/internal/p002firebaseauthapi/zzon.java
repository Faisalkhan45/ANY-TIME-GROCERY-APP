package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzon  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzon extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzon zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzoq zze;

    static {
        zzon zzon = new zzon();
        zzb = zzon;
        zzadf.zzG(zzon.class, zzon);
    }

    private zzon() {
    }

    public static zzom zzb() {
        return (zzom) zzb.zzt();
    }

    public static zzon zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzon) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzg(zzon zzon, zzoq zzoq) {
        zzoq.getClass();
        zzon.zze = zzoq;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzoq zze() {
        zzoq zzoq = this.zze;
        return zzoq == null ? zzoq.zzb() : zzoq;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzon();
            case 4:
                return new zzom((zzol) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
