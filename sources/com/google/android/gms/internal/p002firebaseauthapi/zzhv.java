package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhv extends zziv {
    private final int zza;
    private final int zzb;
    private final zzht zzc;

    /* synthetic */ zzhv(int i, int i2, zzht zzht, zzhu zzhu) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzht;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhv)) {
            return false;
        }
        zzhv zzhv = (zzhv) obj;
        if (zzhv.zza == this.zza && zzhv.zzb() == zzb() && zzhv.zzc == this.zzc) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        int i = this.zzb;
        int i2 = this.zza;
        return "AES-CMAC Parameters (variant: " + valueOf + ", " + i + "-byte tags, and " + i2 + "-byte key)";
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        zzht zzht = this.zzc;
        if (zzht == zzht.zzd) {
            return this.zzb;
        }
        if (zzht == zzht.zza || zzht == zzht.zzb || zzht == zzht.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzht zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzc != zzht.zzd;
    }
}
