package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzok  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzok extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzok zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzadk zze = zzz();

    static {
        zzok zzok = new zzok();
        zzb = zzok;
        zzadf.zzG(zzok.class, zzok);
    }

    private zzok() {
    }

    public static zzoh zza() {
        return (zzoh) zzb.zzt();
    }

    static /* synthetic */ void zze(zzok zzok, zzoj zzoj) {
        zzoj.getClass();
        zzadk zzadk = zzok.zze;
        if (!zzadk.zzc()) {
            zzok.zze = zzadf.zzA(zzadk);
        }
        zzok.zze.add(zzoj);
    }

    public final zzoj zzb(int i) {
        return (zzoj) this.zze.get(0);
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzoj.class});
            case 3:
                return new zzok();
            case 4:
                return new zzoh((zzog) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
