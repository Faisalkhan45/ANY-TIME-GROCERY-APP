package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhh {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    /* synthetic */ zzhh(zzhb zzhb, zzhg zzhg) {
        this.zza = new HashMap(zzhb.zza);
        this.zzb = new HashMap(zzhb.zzb);
        this.zzc = new HashMap(zzhb.zzc);
        this.zzd = new HashMap(zzhb.zzd);
    }

    public final zzaw zza(zzha zzha, @Nullable zzca zzca) throws GeneralSecurityException {
        zzhd zzhd = new zzhd(zzha.getClass(), zzha.zzd(), (zzhc) null);
        if (this.zzb.containsKey(zzhd)) {
            return ((zzfv) this.zzb.get(zzhd)).zza(zzha, zzca);
        }
        String obj = zzhd.toString();
        throw new GeneralSecurityException("No Key Parser for requested key type " + obj + " available");
    }
}
