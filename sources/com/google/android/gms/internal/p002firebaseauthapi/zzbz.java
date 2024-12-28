package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbz {
    private static final Logger zza = Logger.getLogger(zzbz.class.getName());
    private static final AtomicReference zzb = new AtomicReference(new zzbd());
    private static final ConcurrentMap zzc = new ConcurrentHashMap();
    private static final ConcurrentMap zzd = new ConcurrentHashMap();
    private static final ConcurrentMap zze = new ConcurrentHashMap();
    private static final ConcurrentMap zzf = new ConcurrentHashMap();
    private static final ConcurrentMap zzg = new ConcurrentHashMap();

    private zzbz() {
    }

    @Deprecated
    public static zzax zza(String str) throws GeneralSecurityException {
        return ((zzbd) zzb.get()).zza(str);
    }

    public static zzax zzb(String str) throws GeneralSecurityException {
        return ((zzbd) zzb.get()).zzc(str);
    }

    public static synchronized zzns zzc(zznx zznx) throws GeneralSecurityException {
        zzns zza2;
        synchronized (zzbz.class) {
            zzax zzb2 = zzb(zznx.zzf());
            if (((Boolean) zzd.get(zznx.zzf())).booleanValue()) {
                zza2 = zzb2.zza(zznx.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zznx.zzf())));
            }
        }
        return zza2;
    }

    public static synchronized zzaek zzd(zznx zznx) throws GeneralSecurityException {
        zzaek zzb2;
        synchronized (zzbz.class) {
            zzax zzb3 = zzb(zznx.zzf());
            if (((Boolean) zzd.get(zznx.zzf())).booleanValue()) {
                zzb2 = zzb3.zzb(zznx.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zznx.zzf())));
            }
        }
        return zzb2;
    }

    @Nullable
    public static Class zze(Class cls) {
        zzbv zzbv = (zzbv) zzf.get(cls);
        if (zzbv == null) {
            return null;
        }
        return zzbv.zza();
    }

    @Deprecated
    public static Object zzf(zzns zzns) throws GeneralSecurityException {
        String zzf2 = zzns.zzf();
        return ((zzbd) zzb.get()).zza(zzf2).zzc(zzns.zze());
    }

    public static Object zzg(zzns zzns, Class cls) throws GeneralSecurityException {
        return zzh(zzns.zzf(), zzns.zze(), cls);
    }

    public static Object zzh(String str, zzacc zzacc, Class cls) throws GeneralSecurityException {
        return ((zzbd) zzb.get()).zzb(str, cls).zzc(zzacc);
    }

    public static Object zzi(String str, zzaek zzaek, Class cls) throws GeneralSecurityException {
        return ((zzbd) zzb.get()).zzb(str, cls).zzd(zzaek);
    }

    public static Object zzj(String str, byte[] bArr, Class cls) throws GeneralSecurityException {
        return zzh(str, zzacc.zzn(bArr), cls);
    }

    public static Object zzk(zzbu zzbu, Class cls) throws GeneralSecurityException {
        zzbv zzbv = (zzbv) zzf.get(cls);
        if (zzbv == null) {
            throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(zzbu.zzc().getName())));
        } else if (zzbv.zza().equals(zzbu.zzc())) {
            return zzbv.zzc(zzbu);
        } else {
            String obj = zzbv.zza().toString();
            String obj2 = zzbu.zzc().toString();
            throw new GeneralSecurityException("Wrong input primitive class, expected " + obj + ", got " + obj2);
        }
    }

    static synchronized Map zzl() {
        Map unmodifiableMap;
        synchronized (zzbz.class) {
            unmodifiableMap = Collections.unmodifiableMap(zzg);
        }
        return unmodifiableMap;
    }

    public static synchronized void zzm(zzgx zzgx, zzgc zzgc, boolean z) throws GeneralSecurityException {
        synchronized (zzbz.class) {
            AtomicReference atomicReference = zzb;
            zzbd zzbd = new zzbd((zzbd) atomicReference.get());
            zzbd.zzd(zzgx, zzgc);
            String zzd2 = zzgx.zzd();
            String zzd3 = zzgc.zzd();
            zzp(zzd2, zzgx.zza().zzc(), true);
            zzp(zzd3, Collections.emptyMap(), false);
            if (!((zzbd) atomicReference.get()).zzf(zzd2)) {
                zzc.put(zzd2, new zzby(zzgx));
                zzq(zzgx.zzd(), zzgx.zza().zzc());
            }
            ConcurrentMap concurrentMap = zzd;
            concurrentMap.put(zzd2, true);
            concurrentMap.put(zzd3, false);
            atomicReference.set(zzbd);
        }
    }

    public static synchronized void zzn(zzgc zzgc, boolean z) throws GeneralSecurityException {
        synchronized (zzbz.class) {
            AtomicReference atomicReference = zzb;
            zzbd zzbd = new zzbd((zzbd) atomicReference.get());
            zzbd.zze(zzgc);
            String zzd2 = zzgc.zzd();
            zzp(zzd2, zzgc.zza().zzc(), true);
            if (!((zzbd) atomicReference.get()).zzf(zzd2)) {
                zzc.put(zzd2, new zzby(zzgc));
                zzq(zzd2, zzgc.zza().zzc());
            }
            zzd.put(zzd2, true);
            atomicReference.set(zzbd);
        }
    }

    public static synchronized void zzo(zzbv zzbv) throws GeneralSecurityException {
        synchronized (zzbz.class) {
            if (zzbv != null) {
                Class zzb2 = zzbv.zzb();
                ConcurrentMap concurrentMap = zzf;
                if (concurrentMap.containsKey(zzb2)) {
                    zzbv zzbv2 = (zzbv) concurrentMap.get(zzb2);
                    if (!zzbv.getClass().getName().equals(zzbv2.getClass().getName())) {
                        zza.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", "Attempted overwrite of a registered PrimitiveWrapper for type ".concat(zzb2.toString()));
                        throw new GeneralSecurityException(String.format("PrimitiveWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", new Object[]{zzb2.getName(), zzbv2.getClass().getName(), zzbv.getClass().getName()}));
                    }
                }
                concurrentMap.put(zzb2, zzbv);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    private static synchronized void zzp(String str, Map map, boolean z) throws GeneralSecurityException {
        synchronized (zzbz.class) {
            if (z) {
                ConcurrentMap concurrentMap = zzd;
                if (concurrentMap.containsKey(str)) {
                    if (!((Boolean) concurrentMap.get(str)).booleanValue()) {
                        throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(str));
                    }
                }
                if (((zzbd) zzb.get()).zzf(str)) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!zzg.containsKey(entry.getKey())) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String) entry.getKey()) + " from an existing key manager of type " + str);
                        }
                    }
                } else {
                    for (Map.Entry entry2 : map.entrySet()) {
                        if (zzg.containsKey(entry2.getKey())) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template ".concat(String.valueOf((String) entry2.getKey())));
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.android.gms.internal.firebase-auth-api.zzaek, java.lang.Object] */
    private static void zzq(String str, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            zzg.put((String) entry.getKey(), zzbf.zze(str, ((zzga) entry.getValue()).zza.zzq(), ((zzga) entry.getValue()).zzb));
        }
    }
}
