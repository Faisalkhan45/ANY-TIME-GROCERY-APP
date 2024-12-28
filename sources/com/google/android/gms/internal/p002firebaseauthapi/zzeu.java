package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzeu {
    private static final byte[] zza = new byte[0];
    private final zzet zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private BigInteger zzg = BigInteger.ZERO;

    private zzeu(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzet zzet) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzet;
    }

    static zzeu zzb(byte[] bArr, byte[] bArr2, zzex zzex, zzes zzes, zzet zzet, byte[] bArr3) throws GeneralSecurityException {
        zzes zzes2 = zzes;
        byte[] zzb2 = zzff.zzb(zzex.zzb(), zzes.zzc(), zzet.zzb());
        byte[] bArr4 = zzff.zzl;
        byte[] bArr5 = zza;
        byte[] zzc2 = zzpp.zzc(zzff.zza, zzes2.zze(bArr4, bArr5, "psk_id_hash", zzb2), zzes2.zze(zzff.zzl, bArr3, "info_hash", zzb2));
        zzes zzes3 = zzes;
        byte[] zze2 = zzes2.zze(bArr2, bArr5, "secret", zzb2);
        byte[] bArr6 = zzc2;
        byte[] bArr7 = zzb2;
        return new zzeu(bArr, zzes3.zzd(zze2, bArr6, "key", bArr7, zzet.zza()), zzes3.zzd(zze2, bArr6, "base_nonce", bArr7, 12), BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzet);
    }

    private final synchronized byte[] zzc() throws GeneralSecurityException {
        byte[] zzd2;
        byte[] bArr = this.zze;
        byte[] byteArray = this.zzg.toByteArray();
        int length = byteArray.length;
        if (length != 12) {
            if (length > 13) {
                throw new GeneralSecurityException("integer too large");
            } else if (length != 13) {
                byte[] bArr2 = new byte[12];
                System.arraycopy(byteArray, 0, bArr2, 12 - length, length);
                byteArray = bArr2;
            } else if (byteArray[0] == 0) {
                byteArray = Arrays.copyOfRange(byteArray, 1, 13);
            } else {
                throw new GeneralSecurityException("integer too large");
            }
        }
        zzd2 = zzpp.zzd(bArr, byteArray);
        if (this.zzg.compareTo(this.zzc) < 0) {
            this.zzg = this.zzg.add(BigInteger.ONE);
        } else {
            throw new GeneralSecurityException("message limit reached");
        }
        return zzd2;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zzc(this.zzd, zzc(), bArr, bArr2);
    }
}
