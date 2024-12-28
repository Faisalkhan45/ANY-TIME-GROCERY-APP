package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzpo implements zzat {
    private static final Collection zza = Arrays.asList(new Integer[]{64});
    private static final byte[] zzb = new byte[16];
    private final zzql zzc;
    private final byte[] zzd;

    public zzpo(byte[] bArr) throws GeneralSecurityException {
        if (zzdv.zza(1)) {
            Collection collection = zza;
            int length = bArr.length;
            if (collection.contains(Integer.valueOf(length))) {
                int i = length >> 1;
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, i);
                this.zzd = Arrays.copyOfRange(bArr, i, length);
                this.zzc = new zzql(copyOfRange);
                return;
            }
            throw new InvalidKeyException("invalid key size: " + length + " bytes; key must have 64 bytes");
        }
        throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        int length = bArr.length;
        if (length >= 16) {
            Cipher cipher = (Cipher) zzpz.zza.zza("AES/CTR/NoPadding");
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
            byte[] bArr4 = (byte[]) copyOfRange.clone();
            bArr4[8] = (byte) (bArr4[8] & Byte.MAX_VALUE);
            bArr4[12] = (byte) (bArr4[12] & Byte.MAX_VALUE);
            cipher.init(2, new SecretKeySpec(this.zzd, "AES"), new IvParameterSpec(bArr4));
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, length);
            byte[] doFinal = cipher.doFinal(copyOfRange2);
            if (copyOfRange2.length == 0 && doFinal == null && zzqr.zza()) {
                doFinal = new byte[0];
            }
            byte[][] bArr5 = {bArr2, doFinal};
            byte[] zza2 = this.zzc.zza(zzb, 16);
            for (int i = 0; i <= 0; i++) {
                byte[] bArr6 = bArr5[i];
                if (bArr6 == null) {
                    bArr6 = new byte[0];
                }
                zza2 = zzpp.zzd(zziz.zzb(zza2), this.zzc.zza(bArr6, 16));
            }
            byte[] bArr7 = bArr5[1];
            int length2 = bArr7.length;
            if (length2 >= 16) {
                int length3 = zza2.length;
                if (length2 >= length3) {
                    int i2 = length2 - length3;
                    bArr3 = Arrays.copyOf(bArr7, length2);
                    for (int i3 = 0; i3 < zza2.length; i3++) {
                        int i4 = i2 + i3;
                        bArr3[i4] = (byte) (bArr3[i4] ^ zza2[i3]);
                    }
                } else {
                    throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
                }
            } else {
                bArr3 = zzpp.zzd(zziz.zza(bArr7), zziz.zzb(zza2));
            }
            if (zzpp.zzb(copyOfRange, this.zzc.zza(bArr3, 16))) {
                return doFinal;
            }
            throw new AEADBadTagException("Integrity check failed.");
        }
        throw new GeneralSecurityException("Ciphertext too short.");
    }
}
