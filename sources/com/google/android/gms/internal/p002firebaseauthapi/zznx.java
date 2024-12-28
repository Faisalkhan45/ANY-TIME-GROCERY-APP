package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zznx extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zznx zzb;
    private String zzd = "";
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zznx zznx = new zznx();
        zzb = zznx;
        zzadf.zzG(zznx.class, zznx);
    }

    private zznx() {
    }

    public static zznw zza() {
        return (zznw) zzb.zzt();
    }

    public static zznx zzc() {
        return zzb;
    }

    static /* synthetic */ void zzg(zznx zznx, String str) {
        str.getClass();
        zznx.zzd = str;
    }

    public final zzoy zzd() {
        zzoy zzb2 = zzoy.zzb(this.zzf);
        return zzb2 == null ? zzoy.UNRECOGNIZED : zzb2;
    }

    public final zzacc zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zznx();
            case 4:
                return new zznw((zznv) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
