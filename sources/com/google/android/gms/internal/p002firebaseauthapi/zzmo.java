package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzmo extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzmo zzb;
    /* access modifiers changed from: private */
    public zzacc zzd = zzacc.zzb;
    private zzok zze;

    static {
        zzmo zzmo = new zzmo();
        zzb = zzmo;
        zzadf.zzG(zzmo.class, zzmo);
    }

    private zzmo() {
    }

    public static zzmn zza() {
        return (zzmn) zzb.zzt();
    }

    public static zzmo zzc(byte[] bArr, zzacs zzacs) throws zzadn {
        return (zzmo) zzadf.zzy(zzb, bArr, zzacs);
    }

    static /* synthetic */ void zzf(zzmo zzmo, zzok zzok) {
        zzok.getClass();
        zzmo.zze = zzok;
    }

    public final zzacc zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzmo();
            case 4:
                return new zzmn((zzmm) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
