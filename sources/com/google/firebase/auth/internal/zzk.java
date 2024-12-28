package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.internal.p002firebaseauthapi.zzaq;
import com.google.android.gms.internal.p002firebaseauthapi.zzau;
import com.google.android.gms.internal.p002firebaseauthapi.zzbj;
import com.google.android.gms.internal.p002firebaseauthapi.zzei;
import com.google.android.gms.internal.p002firebaseauthapi.zzen;
import com.google.android.gms.internal.p002firebaseauthapi.zzfl;
import com.google.android.gms.internal.p002firebaseauthapi.zzfn;
import com.google.android.gms.internal.p002firebaseauthapi.zzu;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzk {
    private static zzk zza;
    private final String zzb;
    private final zzfn zzc;

    private zzk(Context context, String str, boolean z) {
        zzfn zzfn;
        this.zzb = str;
        try {
            zzei.zza();
            zzfl zzfl = new zzfl();
            zzfl.zzf(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", new Object[]{str}));
            zzfl.zzd(zzen.zza);
            zzfl.zze(String.format("android-keystore://firebear_master_key_id.%s", new Object[]{str}));
            zzfn = zzfl.zzg();
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered during crypto setup:\n".concat(String.valueOf(e.getMessage())));
            zzfn = null;
        }
        this.zzc = zzfn;
    }

    public static zzk zza(Context context, String str) {
        zzk zzk = zza;
        if (zzk == null || !zzu.zza(zzk.zzb, str)) {
            zza = new zzk(context, str, true);
        }
        return zza;
    }

    public final String zzb(String str) {
        String str2;
        zzfn zzfn = this.zzc;
        if (zzfn != null) {
            try {
                synchronized (zzfn) {
                    str2 = new String(((zzau) this.zzc.zza().zze(zzau.class)).zza(Base64.decode(str, 8), (byte[]) null), Key.STRING_CHARSET_NAME);
                }
                return str2;
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                Log.e("FirebearCryptoHelper", "Exception encountered while decrypting bytes:\n".concat(String.valueOf(e.getMessage())));
                return null;
            }
        } else {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
    }

    public final String zzc() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzbj zza2 = zzaq.zza(byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zzb().zzg(zza2);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered when attempting to get Public Key:\n".concat(String.valueOf(e.getMessage())));
            return null;
        }
    }
}
