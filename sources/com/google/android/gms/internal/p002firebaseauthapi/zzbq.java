package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbq {
    private final Object zza;
    private final byte[] zzb;
    private final zzoy zzc;
    private final int zzd;
    private final zzaw zze;
    private final int zzf;

    zzbq(Object obj, byte[] bArr, int i, zzoy zzoy, int i2, zzaw zzaw) {
        this.zza = obj;
        this.zzb = Arrays.copyOf(bArr, bArr.length);
        this.zzf = i;
        this.zzc = zzoy;
        this.zzd = i2;
        this.zze = zzaw;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzaw zzb() {
        return this.zze;
    }

    public final zzbn zzc() {
        return this.zze.zza();
    }

    public final zzoy zzd() {
        return this.zzc;
    }

    public final Object zze() {
        return this.zza;
    }

    @Nullable
    public final byte[] zzf() {
        byte[] bArr = this.zzb;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public final int zzg() {
        return this.zzf;
    }
}
