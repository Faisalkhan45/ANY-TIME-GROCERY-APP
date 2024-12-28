package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzbd {
    private static final Logger zza = Logger.getLogger(zzbd.class.getName());
    private final ConcurrentMap zzb;

    zzbd() {
        this.zzb = new ConcurrentHashMap();
    }

    private final zzax zzg(String str, Class cls) throws GeneralSecurityException {
        zzbc zzh = zzh(str);
        if (cls == null) {
            return zzh.zzb();
        }
        if (zzh.zze().contains(cls)) {
            return zzh.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzh.zzc());
        Set<Class> zze = zzh.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class cls2 : zze) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", supported primitives: " + sb2);
    }

    private final synchronized zzbc zzh(String str) throws GeneralSecurityException {
        if (this.zzb.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzbc) this.zzb.get(str);
    }

    private final synchronized void zzi(zzbc zzbc, boolean z) throws GeneralSecurityException {
        String zze = zzbc.zzb().zze();
        zzbc zzbc2 = (zzbc) this.zzb.get(zze);
        if (zzbc2 != null) {
            if (!zzbc2.zzc().equals(zzbc.zzc())) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.KeyManagerRegistry", "registerKeyManagerContainer", "Attempted overwrite of a registered key manager for key type ".concat(zze));
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{zze, zzbc2.zzc().getName(), zzbc.zzc().getName()}));
            }
        }
        if (!z) {
            this.zzb.putIfAbsent(zze, zzbc);
        } else {
            this.zzb.put(zze, zzbc);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final zzax zza(String str) throws GeneralSecurityException {
        return zzg(str, (Class) null);
    }

    /* access modifiers changed from: package-private */
    public final zzax zzb(String str, Class cls) throws GeneralSecurityException {
        return zzg(str, cls);
    }

    /* access modifiers changed from: package-private */
    public final zzax zzc(String str) throws GeneralSecurityException {
        return zzh(str).zzb();
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd(zzgx zzgx, zzgc zzgc) throws GeneralSecurityException {
        Class zzd;
        int zzf = zzgc.zzf();
        if (!zzdv.zza(1)) {
            String valueOf = String.valueOf(zzgx.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        } else if (zzdv.zza(zzf)) {
            String zzd2 = zzgx.zzd();
            String zzd3 = zzgc.zzd();
            if (!(!this.zzb.containsKey(zzd2) || ((zzbc) this.zzb.get(zzd2)).zzd() == null || (zzd = ((zzbc) this.zzb.get(zzd2)).zzd()) == null)) {
                if (!zzd.getName().equals(zzgc.getClass().getName())) {
                    Logger logger = zza;
                    Level level = Level.WARNING;
                    logger.logp(level, "com.google.crypto.tink.KeyManagerRegistry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + zzd2 + " with inconsistent public key type " + zzd3);
                    throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", new Object[]{zzgx.getClass().getName(), zzd.getName(), zzgc.getClass().getName()}));
                }
            }
            zzi(new zzbb(zzgx, zzgc), true);
            zzi(new zzba(zzgc), false);
        } else {
            String valueOf2 = String.valueOf(zzgc.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf2 + " as it is not FIPS compatible.");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zze(zzgc zzgc) throws GeneralSecurityException {
        if (zzdv.zza(zzgc.zzf())) {
            zzi(new zzba(zzgc), false);
        } else {
            String valueOf = String.valueOf(zzgc.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        return this.zzb.containsKey(str);
    }

    zzbd(zzbd zzbd) {
        this.zzb = new ConcurrentHashMap(zzbd.zzb);
    }
}
