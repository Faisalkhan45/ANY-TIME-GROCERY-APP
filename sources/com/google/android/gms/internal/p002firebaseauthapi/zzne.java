package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzne  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzne extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzne zzb;
    private zznh zzd;

    static {
        zzne zzne = new zzne();
        zzb = zzne;
        zzadf.zzG(zzne.class, zzne);
    }

    private zzne() {
    }

    public static zznd zza() {
        return (zznd) zzb.zzt();
    }

    public static zzne zzc(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzne) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zze(zzne zzne, zznh zznh) {
        zznh.getClass();
        zzne.zzd = zznh;
    }

    public final zznh zzd() {
        zznh zznh = this.zzd;
        return zznh == null ? zznh.zzc() : zznh;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzd"});
            case 3:
                return new zzne();
            case 4:
                return new zznd((zznc) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
