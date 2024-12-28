package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfp implements zzbk {
    private static final String zza = zzfp.class.getSimpleName();
    private KeyStore zzb;

    public zzfp() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                instance.load((KeyStore.LoadStoreParameter) null);
                this.zzb = instance;
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        } else {
            throw new IllegalStateException("need Android Keystore on Android M or newer");
        }
    }

    public final synchronized zzap zza(String str) throws GeneralSecurityException {
        zzfo zzfo;
        zzfo = new zzfo(zzqs.zza("android-keystore://", str), this.zzb);
        byte[] zza2 = zzqq.zza(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(zza2, zzfo.zza(zzfo.zzb(zza2, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzfo;
    }

    public final synchronized boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzc(String str) throws GeneralSecurityException {
        String zza2;
        zza2 = zzqs.zza("android-keystore://", str);
        try {
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        } catch (InterruptedException e2) {
        } catch (NullPointerException e3) {
            Log.w(zza, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            Thread.sleep(20);
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            this.zzb = instance;
            instance.load((KeyStore.LoadStoreParameter) null);
        }
        return this.zzb.containsAlias(zza2);
        return this.zzb.containsAlias(zza2);
    }
}
