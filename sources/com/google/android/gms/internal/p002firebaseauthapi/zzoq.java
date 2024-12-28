package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzoq extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzoq zzb;
    private String zzd = "";

    static {
        zzoq zzoq = new zzoq();
        zzb = zzoq;
        zzadf.zzG(zzoq.class, zzoq);
    }

    private zzoq() {
    }

    public static zzoq zzb() {
        return zzb;
    }

    public static zzoq zzc(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzoq) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final String zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzd"});
            case 3:
                return new zzoq();
            case 4:
                return new zzop((zzoo) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
