package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzgy implements zzha {
    private final String zza;
    private final zzqv zzb;
    private final zzacc zzc;
    private final zznr zzd;
    private final zzoy zze;
    @Nullable
    private final Integer zzf;

    private zzgy(String str, zzacc zzacc, zznr zznr, zzoy zzoy, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzhj.zzb(str);
        this.zzc = zzacc;
        this.zzd = zznr;
        this.zze = zzoy;
        this.zzf = num;
    }

    public static zzgy zza(String str, zzacc zzacc, zznr zznr, zzoy zzoy, @Nullable Integer num) throws GeneralSecurityException {
        if (zzoy == zzoy.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzgy(str, zzacc, zznr, zzoy, num);
    }

    public final zznr zzb() {
        return this.zzd;
    }

    public final zzoy zzc() {
        return this.zze;
    }

    public final zzqv zzd() {
        return this.zzb;
    }

    public final zzacc zze() {
        return this.zzc;
    }

    @Nullable
    public final Integer zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zza;
    }
}
