package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzpz {
    public static final zzpz zza = new zzpz(new zzqa());
    public static final zzpz zzb = new zzpz(new zzqe());
    public static final zzpz zzc = new zzpz(new zzqg());
    public static final zzpz zzd = new zzpz(new zzqf());
    public static final zzpz zze = new zzpz(new zzqb());
    public static final zzpz zzf = new zzpz(new zzqd());
    public static final zzpz zzg = new zzpz(new zzqc());
    private static final Logger zzh = Logger.getLogger(zzpz.class.getName());
    private static final List zzi;
    private static final boolean zzj;
    private final zzqh zzk;

    static {
        if (zzdw.zzb()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt");
            zzj = false;
        } else if (zzqr.zza()) {
            zzi = zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL");
            zzj = true;
        } else {
            zzi = new ArrayList();
            zzj = true;
        }
    }

    public zzpz(zzqh zzqh) {
        this.zzk = zzqh;
    }

    public static List zzb(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            } else {
                zzh.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
            }
        }
        return arrayList;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider zza2 : zzi) {
            try {
                return this.zzk.zza(str, zza2);
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                }
            }
        }
        if (zzj) {
            return this.zzk.zza(str, (Provider) null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }
}
