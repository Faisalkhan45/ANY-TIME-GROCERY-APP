package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmz extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmz zzb;
    /* access modifiers changed from: private */
    public int zzd;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzmz zzmz = new zzmz();
        zzb = zzmz;
        zzadf.zzG(zzmz.class, zzmz);
    }

    private zzmz() {
    }

    public static zzmy zzb() {
        return (zzmy) zzb.zzt();
    }

    public static zzmz zzd() {
        return zzb;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzf() {
        int zzb2 = zzmq.zzb(this.zzd);
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
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzd", "zze"});
            case 3:
                return new zzmz();
            case 4:
                return new zzmy((zzmx) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
