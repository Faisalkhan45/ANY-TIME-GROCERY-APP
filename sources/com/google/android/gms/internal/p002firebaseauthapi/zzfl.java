package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.crypto.KeyGenerator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfl {
    /* access modifiers changed from: private */
    public zzbj zza = null;
    private String zzb = null;
    /* access modifiers changed from: private */
    public zzap zzc = null;
    private zzbf zzd = null;
    /* access modifiers changed from: private */
    public zzbi zze;
    private zzfq zzf = null;

    private final zzap zzh() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            zzfp zzfp = new zzfp();
            boolean zzc2 = zzfp.zzc(this.zzb);
            if (!zzc2) {
                try {
                    String str = this.zzb;
                    if (!new zzfp().zzc(str)) {
                        String zza2 = zzqs.zza("android-keystore://", str);
                        KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                        instance.init(new KeyGenParameterSpec.Builder(zza2, 3).setKeySize(256).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
                        instance.generateKey();
                    } else {
                        throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", new Object[]{str}));
                    }
                } catch (GeneralSecurityException | ProviderException e) {
                    Log.w(zzfn.zzb, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            }
            try {
                return zzfp.zza(this.zzb);
            } catch (GeneralSecurityException | ProviderException e2) {
                if (!zzc2) {
                    Log.w(zzfn.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
                throw new KeyStoreException(String.format("the master key %s exists but is unusable", new Object[]{this.zzb}), e2);
            }
        } else {
            Log.w(zzfn.zzb, "Android Keystore requires at least Android M");
            return null;
        }
    }

    private final zzbi zzi() throws GeneralSecurityException, IOException {
        zzap zzap = this.zzc;
        if (zzap != null) {
            try {
                return zzbi.zzf(zzbh.zzh(this.zzf, zzap));
            } catch (zzadn | GeneralSecurityException e) {
                Log.w(zzfn.zzb, "cannot decrypt keyset: ", e);
            }
        }
        return zzbi.zzf(zzar.zzb(this.zzf));
    }

    @Deprecated
    public final zzfl zzd(zznx zznx) {
        int i;
        String zzf2 = zznx.zzf();
        byte[] zzt = zznx.zze().zzt();
        zzoy zzd2 = zznx.zzd();
        int i2 = zzfn.zza;
        zzoy zzoy = zzoy.UNKNOWN_PREFIX;
        switch (zzd2.ordinal()) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            default:
                throw new IllegalArgumentException("Unknown output prefix type");
        }
        this.zzd = zzbf.zze(zzf2, zzt, i);
        return this;
    }

    public final zzfl zze(String str) {
        if (str.startsWith("android-keystore://")) {
            this.zzb = str;
            return this;
        }
        throw new IllegalArgumentException("key URI must start with android-keystore://");
    }

    public final zzfl zzf(Context context, String str, String str2) throws IOException {
        if (context != null) {
            this.zzf = new zzfq(context, "GenericIdpKeyset", str2);
            this.zza = new zzfr(context, "GenericIdpKeyset", str2);
            return this;
        }
        throw new IllegalArgumentException("need an Android context");
    }

    public final synchronized zzfn zzg() throws GeneralSecurityException, IOException {
        zzbi zzbi;
        if (this.zzb != null) {
            this.zzc = zzh();
        }
        try {
            zzbi = zzi();
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(zzfn.zzb, 4)) {
                Log.i(zzfn.zzb, String.format("keyset not found, will generate a new one. %s", new Object[]{e.getMessage()}));
            }
            if (this.zzd != null) {
                zzbi = zzbi.zze();
                zzbi.zzc(this.zzd);
                zzbi.zzd(zzbi.zzb().zzd().zzb(0).zza());
                if (this.zzc != null) {
                    zzbi.zzb().zzf(this.zza, this.zzc);
                } else {
                    zzar.zza(zzbi.zzb(), this.zza);
                }
            } else {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }
        this.zze = zzbi;
        return new zzfn(this, (zzfm) null);
    }
}
