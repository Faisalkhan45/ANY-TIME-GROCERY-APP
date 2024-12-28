package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzacg {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzach zzc;

    /* synthetic */ zzacg(zzacf zzacf) {
    }

    public static int zzs(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzt(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    static zzacg zzu(byte[] bArr, int i, int i2, boolean z) {
        zzace zzace = new zzace(bArr, 0, i2, z, (zzacd) null);
        try {
            zzace.zzc(i2);
            return zzace;
        } catch (zzadn e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzb();

    public abstract int zzc(int i) throws zzadn;

    public abstract int zzf() throws IOException;

    public abstract zzacc zzj() throws IOException;

    public abstract String zzk() throws IOException;

    public abstract String zzl() throws IOException;

    public abstract void zzm(int i) throws zzadn;

    public abstract void zzn(int i);

    public abstract boolean zzp() throws IOException;

    public abstract boolean zzq() throws IOException;

    public abstract boolean zzr(int i) throws IOException;
}
