package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzkr extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzkr zzb;
    /* access modifiers changed from: private */
    public int zzd;

    static {
        zzkr zzkr = new zzkr();
        zzb = zzkr;
        zzadf.zzG(zzkr.class, zzkr);
    }

    private zzkr() {
    }

    public static zzkq zzb() {
        return (zzkq) zzb.zzt();
    }

    public static zzkr zzd() {
        return zzb;
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
                return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzd"});
            case 3:
                return new zzkr();
            case 4:
                return new zzkq((zzkp) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
