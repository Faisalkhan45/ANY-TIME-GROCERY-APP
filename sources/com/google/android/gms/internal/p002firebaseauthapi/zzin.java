package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzin  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzin extends zziv {
    private final int zza;
    private final int zzb;
    private final zzil zzc;
    private final zzik zzd;

    /* synthetic */ zzin(int i, int i2, zzil zzil, zzik zzik, zzim zzim) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzil;
        this.zzd = zzik;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzin)) {
            return false;
        }
        zzin zzin = (zzin) obj;
        if (zzin.zza == this.zza && zzin.zzb() == zzb() && zzin.zzc == this.zzc && zzin.zzd == this.zzd) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        int i = this.zzb;
        int i2 = this.zza;
        return "HMAC Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + i + "-byte tags, and " + i2 + "-byte key)";
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        zzil zzil = this.zzc;
        if (zzil == zzil.zzd) {
            return this.zzb;
        }
        if (zzil == zzil.zza || zzil == zzil.zzb || zzil == zzil.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzil zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzc != zzil.zzd;
    }
}
