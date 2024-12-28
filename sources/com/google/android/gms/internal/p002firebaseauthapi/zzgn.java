package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzgn {
    private static final zzgn zza = new zzgn();
    private final AtomicReference zzb = new AtomicReference(new zzhh(new zzhb(), (zzhg) null));

    public static zzgn zzb() {
        return zza;
    }

    public final zzaw zza(zzgy zzgy, zzca zzca) {
        try {
            return ((zzhh) this.zzb.get()).zza(zzgy, zzca);
        } catch (GeneralSecurityException e) {
            try {
                return new zzgg(zzgy, zzca);
            } catch (GeneralSecurityException e2) {
                throw new zzhi("Creating a LegacyProtoKey failed", e2);
            }
        }
    }

    public final synchronized void zzc(zzfv zzfv) throws GeneralSecurityException {
        zzhb zzhb = new zzhb((zzhh) this.zzb.get());
        zzhb.zza(zzfv);
        this.zzb.set(new zzhh(zzhb, (zzhg) null));
    }

    public final synchronized void zzd(zzfz zzfz) throws GeneralSecurityException {
        zzhb zzhb = new zzhb((zzhh) this.zzb.get());
        zzhb.zzb(zzfz);
        this.zzb.set(new zzhh(zzhb, (zzhg) null));
    }

    public final synchronized void zze(zzgr zzgr) throws GeneralSecurityException {
        zzhb zzhb = new zzhb((zzhh) this.zzb.get());
        zzhb.zzc(zzgr);
        this.zzb.set(new zzhh(zzhb, (zzhg) null));
    }

    public final synchronized void zzf(zzgv zzgv) throws GeneralSecurityException {
        zzhb zzhb = new zzhb((zzhh) this.zzb.get());
        zzhb.zzd(zzgv);
        this.zzb.set(new zzhh(zzhb, (zzhg) null));
    }
}
