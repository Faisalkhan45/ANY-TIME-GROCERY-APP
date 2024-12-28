package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzof extends zzadf implements zzael {
    /* access modifiers changed from: private */
    public static final zzof zzb;
    /* access modifiers changed from: private */
    public int zzd;
    private zzadk zze = zzz();

    static {
        zzof zzof = new zzof();
        zzb = zzof;
        zzadf.zzG(zzof.class, zzof);
    }

    private zzof() {
    }

    public static zzoc zzc() {
        return (zzoc) zzb.zzt();
    }

    public static zzof zzf(byte[] bArr, zzacs zzacs) throws zzadn {
        return (zzof) zzadf.zzy(zzb, bArr, zzacs);
    }

    static /* synthetic */ void zzi(zzof zzof, zzoe zzoe) {
        zzoe.getClass();
        zzadk zzadk = zzof.zze;
        if (!zzadk.zzc()) {
            zzof.zze = zzadf.zzA(zzadk);
        }
        zzof.zze.add(zzoe);
    }

    public final int zza() {
        return this.zze.size();
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzoe zzd(int i) {
        return (zzoe) this.zze.get(i);
    }

    public final List zzg() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzj(int i, Object obj, Object obj2) {
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 2:
                return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzoe.class});
            case 3:
                return new zzof();
            case 4:
                return new zzoc((zzob) null);
            case 5:
                return zzb;
            default:
                return null;
        }
    }
}
