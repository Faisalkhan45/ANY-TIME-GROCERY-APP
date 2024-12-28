package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzot  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzot extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzot zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzow zze;

    static {
        zzot zzot = new zzot();
        zzb = zzot;
        zzadf.zzG(zzot.class, zzot);
    }

    private zzot() {
    }

    public static zzos zzb() {
        return (zzos) zzb.zzt();
    }

    public static zzot zzd(zzacc zzacc, zzacs zzacs) throws zzadn {
        return (zzot) zzadf.zzx(zzb, zzacc, zzacs);
    }

    static /* synthetic */ void zzg(zzot zzot, zzow zzow) {
        zzow.getClass();
        zzot.zze = zzow;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzow zze() {
        zzow zzow = this.zze;
        return zzow == null ? zzow.zzc() : zzow;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzd", "zze"});
            case 3:
                return new zzot();
            case 4:
                return new zzos((zzor) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
