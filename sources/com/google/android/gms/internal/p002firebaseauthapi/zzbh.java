package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbh {
    private final zzof zza;
    private final zzjc zzb = zzjc.zza;

    private zzbh(zzof zzof) {
        this.zza = zzof;
    }

    static final zzbh zza(zzof zzof) throws GeneralSecurityException {
        zzi(zzof);
        return new zzbh(zzof);
    }

    public static final zzbh zzh(zzfq zzfq, zzap zzap) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzmo zza2 = zzfq.zza();
        if (zza2 == null || zza2.zzd().zzd() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        try {
            zzof zzf = zzof.zzf(zzap.zza(zza2.zzd().zzt(), bArr), zzacs.zza());
            zzi(zzf);
            return new zzbh(zzf);
        } catch (zzadn e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static void zzi(zzof zzof) throws GeneralSecurityException {
        if (zzof == null || zzof.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final String toString() {
        return zzcb.zza(this.zza).toString();
    }

    public final zzbh zzb() throws GeneralSecurityException {
        if (this.zza != null) {
            zzoc zzc = zzof.zzc();
            for (zzoe zzoe : this.zza.zzg()) {
                zzns zzb2 = zzoe.zzb();
                if (zzb2.zzb() == zznr.ASYMMETRIC_PRIVATE) {
                    String zzf = zzb2.zzf();
                    zzacc zze = zzb2.zze();
                    zzax zza2 = zzbz.zza(zzf);
                    if (zza2 instanceof zzbw) {
                        zzns zzf2 = ((zzbw) zza2).zzf(zze);
                        zzbz.zzf(zzf2);
                        zzod zzod = (zzod) zzoe.zzu();
                        zzod.zza(zzf2);
                        zzc.zzb((zzoe) zzod.zzi());
                    } else {
                        throw new GeneralSecurityException("manager for key type " + zzf + " is not a PrivateKeyManager");
                    }
                } else {
                    throw new GeneralSecurityException("The keyset contains a non-private key");
                }
            }
            zzc.zzc(this.zza.zzb());
            return new zzbh((zzof) zzc.zzi());
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }

    /* access modifiers changed from: package-private */
    public final zzof zzc() {
        return this.zza;
    }

    public final zzok zzd() {
        return zzcb.zza(this.zza);
    }

    public final Object zze(Class cls) throws GeneralSecurityException {
        Class zze = zzbz.zze(cls);
        if (zze != null) {
            zzcb.zzb(this.zza);
            zzbp zzbp = new zzbp(zze, (zzbo) null);
            zzbp.zzc(this.zzb);
            for (zzoe zzoe : this.zza.zzg()) {
                if (zzoe.zzk() == 3) {
                    Object zzg = zzbz.zzg(zzoe.zzb(), zze);
                    if (zzoe.zza() == this.zza.zzb()) {
                        zzbp.zza(zzg, zzoe);
                    } else {
                        zzbp.zzb(zzg, zzoe);
                    }
                }
            }
            return zzbz.zzk(zzbp.zzd(), cls);
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
    }

    public final void zzf(zzbj zzbj, zzap zzap) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzof zzof = this.zza;
        byte[] zzb2 = zzap.zzb(zzof.zzq(), bArr);
        try {
            if (zzof.zzf(zzap.zza(zzb2, bArr), zzacs.zza()).equals(zzof)) {
                zzmn zza2 = zzmo.zza();
                zza2.zza(zzacc.zzn(zzb2));
                zza2.zzb(zzcb.zza(zzof));
                zzbj.zzb((zzmo) zza2.zzi());
                return;
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (zzadn e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzg(com.google.android.gms.internal.p002firebaseauthapi.zzbj r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzof r0 = r4.zza
            java.util.List r0 = r0.zzg()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0064
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zzoe r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzoe) r1
            com.google.android.gms.internal.firebase-auth-api.zzns r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r3 = com.google.android.gms.internal.p002firebaseauthapi.zznr.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzns r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r3 = com.google.android.gms.internal.p002firebaseauthapi.zznr.SYMMETRIC
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzns r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r3 = com.google.android.gms.internal.p002firebaseauthapi.zznr.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L_0x003b
            goto L_0x000a
        L_0x003b:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r2 = 0
            com.google.android.gms.internal.firebase-auth-api.zzns r3 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zznr r3 = r3.zzb()
            java.lang.String r3 = r3.name()
            r0[r2] = r3
            r2 = 1
            com.google.android.gms.internal.firebase-auth-api.zzns r1 = r1.zzb()
            java.lang.String r1 = r1.zzf()
            r0[r2] = r1
            java.lang.String r1 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r5.<init>(r0)
            throw r5
        L_0x0064:
            com.google.android.gms.internal.firebase-auth-api.zzof r0 = r4.zza
            r5.zzc(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzbh.zzg(com.google.android.gms.internal.firebase-auth-api.zzbj):void");
    }
}
