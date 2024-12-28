package com.google.android.gms.internal.p002firebaseauthapi;

import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzafu extends zzafw {
    zzafu(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzafx.zzb) {
            zzafx.zzD(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzafx.zzE(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j, byte b) {
        if (zzafx.zzb) {
            zzafx.zzD(obj, j, b);
        } else {
            zzafx.zzE(obj, j, b);
        }
    }

    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzg(Object obj, long j) {
        if (zzafx.zzb) {
            return zzafx.zzt(obj, j);
        }
        return zzafx.zzu(obj, j);
    }
}
