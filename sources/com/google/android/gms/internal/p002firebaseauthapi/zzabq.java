package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzabq {
    static int zza(byte[] bArr, int i, zzabp zzabp) throws zzadn {
        int zzj = zzj(bArr, i, zzabp);
        int i2 = zzabp.zza;
        if (i2 < 0) {
            throw zzadn.zzf();
        } else if (i2 > bArr.length - zzj) {
            throw zzadn.zzi();
        } else if (i2 == 0) {
            zzabp.zzc = zzacc.zzb;
            return zzj;
        } else {
            zzabp.zzc = zzacc.zzo(bArr, zzj, i2);
            return zzj + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int zzc(zzaew zzaew, byte[] bArr, int i, int i2, int i3, zzabp zzabp) throws IOException {
        Object zze = zzaew.zze();
        int zzn = zzn(zze, zzaew, bArr, i, i2, i3, zzabp);
        zzaew.zzf(zze);
        zzabp.zzc = zze;
        return zzn;
    }

    static int zzd(zzaew zzaew, byte[] bArr, int i, int i2, zzabp zzabp) throws IOException {
        Object zze = zzaew.zze();
        int zzo = zzo(zze, zzaew, bArr, i, i2, zzabp);
        zzaew.zzf(zze);
        zzabp.zzc = zze;
        return zzo;
    }

    static int zze(zzaew zzaew, int i, byte[] bArr, int i2, int i3, zzadk zzadk, zzabp zzabp) throws IOException {
        int zzd = zzd(zzaew, bArr, i2, i3, zzabp);
        zzadk.add(zzabp.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzabp);
            if (i != zzabp.zza) {
                break;
            }
            zzd = zzd(zzaew, bArr, zzj, i3, zzabp);
            zzadk.add(zzabp.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzadk zzadk, zzabp zzabp) throws IOException {
        zzadg zzadg = (zzadg) zzadk;
        int zzj = zzj(bArr, i, zzabp);
        int i2 = zzabp.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzabp);
            zzadg.zzf(zzabp.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzadn.zzi();
    }

    static int zzg(byte[] bArr, int i, zzabp zzabp) throws zzadn {
        int zzj = zzj(bArr, i, zzabp);
        int i2 = zzabp.zza;
        if (i2 < 0) {
            throw zzadn.zzf();
        } else if (i2 == 0) {
            zzabp.zzc = "";
            return zzj;
        } else {
            zzabp.zzc = new String(bArr, zzj, i2, zzadl.zzb);
            return zzj + i2;
        }
    }

    static int zzh(byte[] bArr, int i, zzabp zzabp) throws zzadn {
        int zzj = zzj(bArr, i, zzabp);
        int i2 = zzabp.zza;
        if (i2 < 0) {
            throw zzadn.zzf();
        } else if (i2 == 0) {
            zzabp.zzc = "";
            return zzj;
        } else {
            zzabp.zzc = zzagc.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
    }

    static int zzj(byte[] bArr, int i, zzabp zzabp) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzabp);
        }
        zzabp.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzabp zzabp) {
        int i3 = i & WorkQueueKt.MASK;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzabp.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzabp.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << Ascii.SO);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzabp.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzabp.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzabp.zza = i11;
                return i12;
            }
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzadk zzadk, zzabp zzabp) {
        zzadg zzadg = (zzadg) zzadk;
        int zzj = zzj(bArr, i2, zzabp);
        zzadg.zzf(zzabp.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzabp);
            if (i != zzabp.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzabp);
            zzadg.zzf(zzabp.zza);
        }
        return zzj;
    }

    static int zzm(byte[] bArr, int i, zzabp zzabp) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzabp.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzabp.zzb = j2;
        return i3;
    }

    static int zzn(Object obj, zzaew zzaew, byte[] bArr, int i, int i2, int i3, zzabp zzabp) throws IOException {
        int zzc = ((zzaen) zzaew).zzc(obj, bArr, i, i2, i3, zzabp);
        zzabp.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzo(java.lang.Object r6, com.google.android.gms.internal.p002firebaseauthapi.zzaew r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.p002firebaseauthapi.zzabp r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000e
            int r0 = zzk(r9, r8, r0, r11)
            int r9 = r11.zza
            r3 = r0
            goto L_0x000f
        L_0x000e:
            r3 = r0
        L_0x000f:
            if (r9 < 0) goto L_0x0020
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x0020
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zzi(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x0020:
            com.google.android.gms.internal.firebase-auth-api.zzadn r6 = com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzi()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzo(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzaew, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzabp):int");
    }

    static long zzp(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzafo zzafo, zzabp zzabp) throws zzadn {
        if ((i >>> 3) != 0) {
            switch (i & 7) {
                case 0:
                    int zzm = zzm(bArr, i2, zzabp);
                    zzafo.zzj(i, Long.valueOf(zzabp.zzb));
                    return zzm;
                case 1:
                    zzafo.zzj(i, Long.valueOf(zzp(bArr, i2)));
                    return i2 + 8;
                case 2:
                    int zzj = zzj(bArr, i2, zzabp);
                    int i4 = zzabp.zza;
                    if (i4 < 0) {
                        throw zzadn.zzf();
                    } else if (i4 <= bArr.length - zzj) {
                        if (i4 == 0) {
                            zzafo.zzj(i, zzacc.zzb);
                        } else {
                            zzafo.zzj(i, zzacc.zzo(bArr, zzj, i4));
                        }
                        return zzj + i4;
                    } else {
                        throw zzadn.zzi();
                    }
                case 3:
                    int i5 = (i & -8) | 4;
                    zzafo zzf = zzafo.zzf();
                    int i6 = 0;
                    while (true) {
                        if (i2 < i3) {
                            int zzj2 = zzj(bArr, i2, zzabp);
                            int i7 = zzabp.zza;
                            if (i7 == i5) {
                                i6 = i7;
                                i2 = zzj2;
                            } else {
                                i6 = i7;
                                i2 = zzi(i7, bArr, zzj2, i3, zzf, zzabp);
                            }
                        }
                    }
                    if (i2 > i3 || i6 != i5) {
                        throw zzadn.zzg();
                    }
                    zzafo.zzj(i, zzf);
                    return i2;
                case 5:
                    zzafo.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                default:
                    throw zzadn.zzc();
            }
        } else {
            throw zzadn.zzc();
        }
    }
}
