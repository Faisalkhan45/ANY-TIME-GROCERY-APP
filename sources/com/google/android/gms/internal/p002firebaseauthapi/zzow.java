package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzow  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzow extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzow zzb;
    private String zzd = "";
    private zznx zze;

    static {
        zzow zzow = new zzow();
        zzb = zzow;
        zzadf.zzG(zzow.class, zzow);
    }

    private zzow() {
    }

    public static zzow zzc() {
        return zzb;
    }

    public static zzow zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzow) zzadf.zzx(zzb, zzacc, zzacs);
    }

    public final zznx zza() {
        zznx zznx = this.zze;
        return zznx == null ? zznx.zzc() : zznx;
    }

    public final String zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zze != null;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzow();
            case 4:
                return new zzov((zzou) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
