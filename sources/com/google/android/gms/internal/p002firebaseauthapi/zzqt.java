package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.primitives.SignedBytes;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzqt {
    public static byte[] zza(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long[] jArr = new long[11];
            byte[] copyOf = Arrays.copyOf(bArr3, 32);
            copyOf[0] = (byte) (copyOf[0] & 248);
            byte b = copyOf[31] & Byte.MAX_VALUE;
            copyOf[31] = (byte) b;
            copyOf[31] = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
            int i = zzpr.zzb;
            if (bArr4.length == 32) {
                byte[] copyOf2 = Arrays.copyOf(bArr4, 32);
                copyOf2[31] = (byte) (copyOf2[31] & Byte.MAX_VALUE);
                int i2 = 0;
                while (i2 < 7) {
                    if (!zzpp.zzb(zzpr.zza[i2], copyOf2)) {
                        i2++;
                    } else {
                        throw new InvalidKeyException("Banned public key: ".concat(zzqj.zza(zzpr.zza[i2])));
                    }
                }
                long[] zzk = zzqi.zzk(copyOf2);
                long[] jArr2 = new long[19];
                long[] jArr3 = new long[19];
                jArr3[0] = 1;
                long[] jArr4 = new long[19];
                jArr4[0] = 1;
                long[] jArr5 = new long[19];
                long[] jArr6 = new long[19];
                long[] jArr7 = new long[19];
                jArr7[0] = 1;
                long[] jArr8 = new long[19];
                long[] jArr9 = new long[19];
                jArr9[0] = 1;
                System.arraycopy(zzk, 0, jArr2, 0, 10);
                int i3 = 0;
                while (i3 < 32) {
                    byte b2 = copyOf[(32 - i3) - 1] & 255;
                    int i4 = 0;
                    while (i4 < 8) {
                        int i5 = (b2 >> (7 - i4)) & 1;
                        zzpr.zza(jArr4, jArr2, i5);
                        zzpr.zza(jArr5, jArr3, i5);
                        byte[] bArr5 = copyOf;
                        long[] copyOf3 = Arrays.copyOf(jArr4, 10);
                        byte b3 = b2;
                        long[] jArr10 = jArr;
                        long[] jArr11 = new long[19];
                        int i6 = i3;
                        long[] jArr12 = new long[19];
                        int i7 = i4;
                        long[] jArr13 = new long[19];
                        int i8 = i5;
                        long[] jArr14 = new long[19];
                        long[] jArr15 = jArr9;
                        long[] jArr16 = new long[19];
                        long[] jArr17 = new long[19];
                        zzqi.zzi(jArr4, jArr4, jArr5);
                        zzqi.zzh(jArr5, copyOf3, jArr5);
                        long[] copyOf4 = Arrays.copyOf(jArr2, 10);
                        zzqi.zzi(jArr2, jArr2, jArr3);
                        zzqi.zzh(jArr3, copyOf4, jArr3);
                        zzqi.zzb(jArr13, jArr2, jArr5);
                        zzqi.zzb(jArr14, jArr4, jArr3);
                        zzqi.zze(jArr13);
                        zzqi.zzd(jArr13);
                        zzqi.zze(jArr14);
                        zzqi.zzd(jArr14);
                        long[] jArr18 = jArr2;
                        System.arraycopy(jArr13, 0, copyOf4, 0, 10);
                        zzqi.zzi(jArr13, jArr13, jArr14);
                        zzqi.zzh(jArr14, copyOf4, jArr14);
                        zzqi.zzg(jArr17, jArr13);
                        zzqi.zzg(jArr16, jArr14);
                        zzqi.zzb(jArr14, jArr16, zzk);
                        zzqi.zze(jArr14);
                        zzqi.zzd(jArr14);
                        System.arraycopy(jArr17, 0, jArr6, 0, 10);
                        System.arraycopy(jArr14, 0, jArr7, 0, 10);
                        zzqi.zzg(jArr11, jArr4);
                        zzqi.zzg(jArr12, jArr5);
                        zzqi.zzb(jArr8, jArr11, jArr12);
                        zzqi.zze(jArr8);
                        zzqi.zzd(jArr8);
                        zzqi.zzh(jArr12, jArr11, jArr12);
                        long[] jArr19 = new long[19];
                        Arrays.fill(jArr19, 10, 18, 0);
                        zzqi.zzf(jArr19, jArr12, 121665);
                        zzqi.zzd(jArr19);
                        zzqi.zzi(jArr19, jArr19, jArr11);
                        long[] jArr20 = jArr15;
                        zzqi.zzb(jArr20, jArr12, jArr19);
                        zzqi.zze(jArr20);
                        zzqi.zzd(jArr20);
                        int i9 = i8;
                        zzpr.zza(jArr8, jArr6, i9);
                        zzpr.zza(jArr20, jArr7, i9);
                        i4 = i7 + 1;
                        byte[] bArr6 = bArr2;
                        jArr2 = jArr6;
                        b2 = b3;
                        copyOf = bArr5;
                        jArr = jArr10;
                        i3 = i6;
                        jArr6 = jArr18;
                        long[] jArr21 = jArr4;
                        jArr4 = jArr8;
                        jArr8 = jArr21;
                        long[] jArr22 = jArr5;
                        jArr5 = jArr20;
                        jArr9 = jArr22;
                        long[] jArr23 = jArr7;
                        jArr7 = jArr3;
                        jArr3 = jArr23;
                    }
                    byte[] bArr7 = copyOf;
                    long[] jArr24 = jArr;
                    long[] jArr25 = jArr2;
                    i3++;
                    byte[] bArr8 = bArr2;
                }
                long[] jArr26 = jArr;
                long[] jArr27 = new long[10];
                long[] jArr28 = new long[10];
                long[] jArr29 = new long[10];
                long[] jArr30 = new long[10];
                long[] jArr31 = new long[10];
                long[] jArr32 = new long[10];
                long[] jArr33 = new long[10];
                long[] jArr34 = new long[10];
                long[] jArr35 = new long[10];
                long[] jArr36 = new long[10];
                long[] jArr37 = jArr2;
                long[] jArr38 = new long[10];
                zzqi.zzg(jArr28, jArr5);
                zzqi.zzg(jArr38, jArr28);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zza(jArr29, jArr36, jArr5);
                zzqi.zza(jArr30, jArr29, jArr28);
                zzqi.zzg(jArr36, jArr30);
                zzqi.zza(jArr31, jArr36, jArr29);
                zzqi.zzg(jArr36, jArr31);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zza(jArr32, jArr36, jArr31);
                zzqi.zzg(jArr36, jArr32);
                zzqi.zzg(jArr38, jArr36);
                for (int i10 = 2; i10 < 10; i10 += 2) {
                    zzqi.zzg(jArr36, jArr38);
                    zzqi.zzg(jArr38, jArr36);
                }
                zzqi.zza(jArr33, jArr38, jArr32);
                zzqi.zzg(jArr36, jArr33);
                zzqi.zzg(jArr38, jArr36);
                for (int i11 = 2; i11 < 20; i11 += 2) {
                    zzqi.zzg(jArr36, jArr38);
                    zzqi.zzg(jArr38, jArr36);
                }
                zzqi.zza(jArr36, jArr38, jArr33);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zzg(jArr36, jArr38);
                for (int i12 = 2; i12 < 10; i12 += 2) {
                    zzqi.zzg(jArr38, jArr36);
                    zzqi.zzg(jArr36, jArr38);
                }
                zzqi.zza(jArr34, jArr36, jArr32);
                zzqi.zzg(jArr36, jArr34);
                zzqi.zzg(jArr38, jArr36);
                for (int i13 = 2; i13 < 50; i13 += 2) {
                    zzqi.zzg(jArr36, jArr38);
                    zzqi.zzg(jArr38, jArr36);
                }
                zzqi.zza(jArr35, jArr38, jArr34);
                zzqi.zzg(jArr38, jArr35);
                zzqi.zzg(jArr36, jArr38);
                for (int i14 = 2; i14 < 100; i14 += 2) {
                    zzqi.zzg(jArr38, jArr36);
                    zzqi.zzg(jArr36, jArr38);
                }
                zzqi.zza(jArr38, jArr36, jArr35);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zzg(jArr38, jArr36);
                for (int i15 = 2; i15 < 50; i15 += 2) {
                    zzqi.zzg(jArr36, jArr38);
                    zzqi.zzg(jArr38, jArr36);
                }
                zzqi.zza(jArr36, jArr38, jArr34);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zzg(jArr36, jArr38);
                zzqi.zzg(jArr38, jArr36);
                zzqi.zza(jArr27, jArr38, jArr30);
                long[] jArr39 = jArr26;
                zzqi.zza(jArr39, jArr4, jArr27);
                long[] jArr40 = new long[10];
                long[] jArr41 = new long[10];
                long[] jArr42 = new long[11];
                long[] jArr43 = new long[11];
                long[] jArr44 = new long[11];
                zzqi.zza(jArr40, zzk, jArr39);
                zzqi.zzi(jArr41, zzk, jArr39);
                long[] jArr45 = new long[10];
                jArr45[0] = 486662;
                zzqi.zzi(jArr43, jArr41, jArr45);
                zzqi.zza(jArr43, jArr43, jArr3);
                long[] jArr46 = jArr37;
                zzqi.zzi(jArr43, jArr43, jArr46);
                zzqi.zza(jArr43, jArr43, jArr40);
                zzqi.zza(jArr43, jArr43, jArr46);
                zzqi.zzf(jArr42, jArr43, 4);
                zzqi.zzd(jArr42);
                zzqi.zza(jArr43, jArr40, jArr3);
                zzqi.zzh(jArr43, jArr43, jArr3);
                zzqi.zza(jArr44, jArr41, jArr46);
                zzqi.zzi(jArr43, jArr43, jArr44);
                zzqi.zzg(jArr43, jArr43);
                if (zzpp.zzb(zzqi.zzj(jArr42), zzqi.zzj(jArr43))) {
                    return zzqi.zzj(jArr39);
                }
                throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: ".concat(zzqj.zza(bArr2)));
            }
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        throw new InvalidKeyException("Private key must have 32 bytes.");
    }

    public static byte[] zzb(byte[] bArr) throws InvalidKeyException {
        if (bArr.length == 32) {
            byte[] bArr2 = new byte[32];
            bArr2[0] = 9;
            return zza(bArr, bArr2);
        }
        throw new InvalidKeyException("Private key must have 32 bytes.");
    }
}
