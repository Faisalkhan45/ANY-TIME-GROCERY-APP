package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzns  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzns extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzns zzb;
    /* access modifiers changed from: private */
    public String zzd = "";
    /* access modifiers changed from: private */
    public zzacc zze = zzacc.zzb;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzns zzns = new zzns();
        zzb = zzns;
        zzadf.zzG(zzns.class, zzns);
    }

    private zzns() {
    }

    public static zznp zza() {
        return (zznp) zzb.zzt();
    }

    public static zzns zzd() {
        return zzb;
    }

    public final zznr zzb() {
        zznr zzb2 = zznr.zzb(this.zzf);
        return zzb2 == null ? zznr.UNRECOGNIZED : zzb2;
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
                return new zzns();
            case 4:
                return new zznp((zzno) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
