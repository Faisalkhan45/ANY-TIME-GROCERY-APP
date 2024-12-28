package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhb {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    public zzhb() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public final zzhb zza(zzfv zzfv) throws GeneralSecurityException {
        zzhd zzhd = new zzhd(zzfv.zzd(), zzfv.zzc(), (zzhc) null);
        if (this.zzb.containsKey(zzhd)) {
            zzfv zzfv2 = (zzfv) this.zzb.get(zzhd);
            if (!zzfv2.equals(zzfv) || !zzfv.equals(zzfv2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzhd.toString()));
            }
        } else {
            this.zzb.put(zzhd, zzfv);
        }
        return this;
    }

    public final zzhb zzb(zzfz zzfz) throws GeneralSecurityException {
        zzhf zzhf = new zzhf(zzfz.zzb(), zzfz.zzc(), (zzhe) null);
        if (this.zza.containsKey(zzhf)) {
            zzfz zzfz2 = (zzfz) this.zza.get(zzhf);
            if (!zzfz2.equals(zzfz) || !zzfz.equals(zzfz2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzhf.toString()));
            }
        } else {
            this.zza.put(zzhf, zzfz);
        }
        return this;
    }

    public final zzhb zzc(zzgr zzgr) throws GeneralSecurityException {
        zzhd zzhd = new zzhd(zzgr.zzc(), zzgr.zzb(), (zzhc) null);
        if (this.zzd.containsKey(zzhd)) {
            zzgr zzgr2 = (zzgr) this.zzd.get(zzhd);
            if (!zzgr2.equals(zzgr) || !zzgr.equals(zzgr2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzhd.toString()));
            }
        } else {
            this.zzd.put(zzhd, zzgr);
        }
        return this;
    }

    public final zzhb zzd(zzgv zzgv) throws GeneralSecurityException {
        zzhf zzhf = new zzhf(zzgv.zzb(), zzgv.zzc(), (zzhe) null);
        if (this.zzc.containsKey(zzhf)) {
            zzgv zzgv2 = (zzgv) this.zzc.get(zzhf);
            if (!zzgv2.equals(zzgv) || !zzgv.equals(zzgv2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzhf.toString()));
            }
        } else {
            this.zzc.put(zzhf, zzgv);
        }
        return this;
    }

    public zzhb(zzhh zzhh) {
        this.zza = new HashMap(zzhh.zza);
        this.zzb = new HashMap(zzhh.zzb);
        this.zzc = new HashMap(zzhh.zzc);
        this.zzd = new HashMap(zzhh.zzd);
    }
}
