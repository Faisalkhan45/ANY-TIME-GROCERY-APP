package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzkx extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzkx zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private int zze;

    static {
        zzkx zzkx = new zzkx();
        zzb = zzkx;
        zzadf.zzG(zzkx.class, zzkx);
    }

    private zzkx() {
    }

    public static zzkw zzb() {
        return (zzkw) zzb.zzt();
    }

    public static zzkx zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzkx) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final int zza() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzd", "zze"});
            case 3:
                return new zzkx();
            case 4:
                return new zzkw((zzkv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
