package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzoe extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzoe zzb;
    private zzns zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzoe zzoe = new zzoe();
        zzb = zzoe;
        zzadf.zzG(zzoe.class, zzoe);
    }

    private zzoe() {
    }

    public static zzod zzc() {
        return (zzod) zzb.zzt();
    }

    static /* synthetic */ void zzf(zzoe zzoe, zzns zzns) {
        zzns.getClass();
        zzoe.zzd = zzns;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzns zzb() {
        zzns zzns = this.zzd;
        return zzns == null ? zzns.zzd() : zzns;
    }

    public final zzoy zze() {
        zzoy zzb2 = zzoy.zzb(this.zzg);
        return zzb2 == null ? zzoy.UNRECOGNIZED : zzb2;
    }

    public final boolean zzi() {
        return this.zzd != null;
    }

    public final int zzk() {
        int i;
        switch (this.zze) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
            case 3:
                return new zzoe();
            case 4:
                return new zzod((zzob) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
