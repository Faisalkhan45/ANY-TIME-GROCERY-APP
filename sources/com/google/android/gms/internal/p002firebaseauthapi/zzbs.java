package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzbs implements Comparable {
    private final byte[] zza;

    /* synthetic */ zzbs(byte[] bArr, zzbr zzbr) {
        this.zza = Arrays.copyOf(bArr, bArr.length);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbs zzbs = (zzbs) obj;
        int length = this.zza.length;
        int length2 = zzbs.zza.length;
        if (length != length2) {
            return length - length2;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return 0;
            }
            byte b = bArr[i];
            byte b2 = zzbs.zza[i];
            if (b != b2) {
                return b - b2;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbs)) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzbs) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzqj.zza(this.zza);
    }
}
