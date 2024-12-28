package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbi {
    private final zzoc zza;

    private zzbi(zzoc zzoc) {
        this.zza = zzoc;
    }

    public static zzbi zze() {
        return new zzbi(zzof.zzc());
    }

    public static zzbi zzf(zzbh zzbh) {
        return new zzbi((zzoc) zzbh.zzc().zzu());
    }

    private final synchronized int zzg() {
        int zza2;
        zza2 = zzhj.zza();
        while (zzj(zza2)) {
            zza2 = zzhj.zza();
        }
        return zza2;
    }

    private final synchronized zzoe zzh(zzns zzns, zzoy zzoy) throws GeneralSecurityException {
        zzod zzc;
        int zzg = zzg();
        if (zzoy != zzoy.UNKNOWN_PREFIX) {
            zzc = zzoe.zzc();
            zzc.zza(zzns);
            zzc.zzb(zzg);
            zzc.zzd(3);
            zzc.zzc(zzoy);
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return (zzoe) zzc.zzi();
    }

    private final synchronized zzoe zzi(zznx zznx) throws GeneralSecurityException {
        return zzh(zzbz.zzc(zznx), zznx.zzd());
    }

    private final synchronized boolean zzj(int i) {
        boolean z;
        Iterator it = this.zza.zze().iterator();
        while (true) {
            if (it.hasNext()) {
                if (((zzoe) it.next()).zza() == i) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    @Deprecated
    public final synchronized int zza(zznx zznx, boolean z) throws GeneralSecurityException {
        zzoe zzi;
        zzi = zzi(zznx);
        this.zza.zzb(zzi);
        return zzi.zza();
    }

    public final synchronized zzbh zzb() throws GeneralSecurityException {
        return zzbh.zza((zzof) this.zza.zzi());
    }

    public final synchronized zzbi zzc(zzbf zzbf) throws GeneralSecurityException {
        zza(zzbf.zza(), false);
        return this;
    }

    public final synchronized zzbi zzd(int i) throws GeneralSecurityException {
        int i2 = 0;
        while (i2 < this.zza.zza()) {
            zzoe zzd = this.zza.zzd(i2);
            if (zzd.zza() != i) {
                i2++;
            } else if (zzd.zzk() == 3) {
                this.zza.zzc(i);
            } else {
                throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }
}
