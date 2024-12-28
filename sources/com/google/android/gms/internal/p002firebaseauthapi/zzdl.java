package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Build;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzdl {
    private static final ThreadLocal zza = new zzdk();
    private final SecretKey zzb;
    private final boolean zzc;

    public zzdl(byte[] bArr, boolean z) throws GeneralSecurityException {
        if (zzdv.zza(2)) {
            zzqs.zzb(bArr.length);
            this.zzb = new SecretKeySpec(bArr, "AES");
            this.zzc = z;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        AlgorithmParameterSpec algorithmParameterSpec;
        int i = 12;
        if (bArr.length == 12) {
            boolean z = this.zzc;
            int i2 = true != z ? 16 : 28;
            int length = bArr2.length;
            if (length < i2) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (!z || ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
                if (!zzqr.zza() || Integer.valueOf(Build.VERSION.SDK_INT).intValue() > 19) {
                    algorithmParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
                } else {
                    algorithmParameterSpec = new IvParameterSpec(bArr, 0, 12);
                }
                ThreadLocal threadLocal = zza;
                ((Cipher) threadLocal.get()).init(2, this.zzb, algorithmParameterSpec);
                boolean z2 = this.zzc;
                if (true != z2) {
                    i = 0;
                }
                if (z2) {
                    length -= 12;
                }
                return ((Cipher) threadLocal.get()).doFinal(bArr2, i, length);
            } else {
                throw new GeneralSecurityException("iv does not match prepended iv");
            }
        } else {
            throw new GeneralSecurityException("iv is wrong size");
        }
    }
}
