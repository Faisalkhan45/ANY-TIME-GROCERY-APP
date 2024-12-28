package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaa  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaaa {
    public static long zza(String str) {
        Preconditions.checkNotEmpty(str);
        List zzd = zzaf.zzb('.').zzd(str);
        if (zzd.size() >= 2) {
            try {
                zzaab zza = zzaab.zza(new String(Base64Utils.decodeUrlSafeNoPadding((String) zzd.get(1)), Key.STRING_CHARSET_NAME));
                return zza.zzb().longValue() - zza.zzc().longValue();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unable to decode token", e);
            }
        } else {
            throw new RuntimeException("Invalid idToken ".concat(String.valueOf(str)));
        }
    }
}
