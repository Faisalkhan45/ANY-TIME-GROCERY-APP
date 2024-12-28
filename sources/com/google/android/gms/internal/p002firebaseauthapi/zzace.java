package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzace  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzace extends zzacg {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj = Integer.MAX_VALUE;

    /* synthetic */ zzace(byte[] bArr, int i, int i2, boolean z, zzacd zzacd) {
        super((zzacf) null);
        this.zze = bArr;
        this.zzf = i2;
        this.zzh = 0;
    }

    private final void zzv() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = this.zzj;
        if (i > i2) {
            int i3 = i - i2;
            this.zzg = i3;
            this.zzf = i - i3;
            return;
        }
        this.zzg = 0;
    }

    public final byte zza() throws IOException {
        int i = this.zzh;
        if (i != this.zzf) {
            byte[] bArr = this.zze;
            this.zzh = i + 1;
            return bArr[i];
        }
        throw zzadn.zzi();
    }

    public final int zzb() {
        return this.zzh;
    }

    public final int zzc(int i) throws zzadn {
        if (i >= 0) {
            int i2 = i + this.zzh;
            if (i2 >= 0) {
                int i3 = this.zzj;
                if (i2 <= i3) {
                    this.zzj = i2;
                    zzv();
                    return i3;
                }
                throw zzadn.zzi();
            }
            throw zzadn.zzg();
        }
        throw zzadn.zzf();
    }

    public final int zzd() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 4) {
            byte[] bArr = this.zze;
            this.zzh = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }
        throw zzadn.zzi();
    }

    public final int zzf() throws IOException {
        if (zzp()) {
            this.zzi = 0;
            return 0;
        }
        int zze2 = zze();
        this.zzi = zze2;
        if ((zze2 >>> 3) != 0) {
            return zze2;
        }
        throw zzadn.zzc();
    }

    public final long zzg() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 8) {
            byte[] bArr = this.zze;
            this.zzh = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzadn.zzi();
    }

    /* access modifiers changed from: package-private */
    public final long zzi() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zza = zza();
            j |= ((long) (zza & Byte.MAX_VALUE)) << i;
            if ((zza & 128) == 0) {
                return j;
            }
        }
        throw zzadn.zze();
    }

    public final zzacc zzj() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                zzacc zzo = zzacc.zzo(this.zze, i2, zze2);
                this.zzh += zze2;
                return zzo;
            }
        }
        if (zze2 == 0) {
            return zzacc.zzb;
        }
        if (zze2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zze2 <= i3 - i4) {
                int i5 = zze2 + i4;
                this.zzh = i5;
                return zzacc.zzq(Arrays.copyOfRange(this.zze, i4, i5));
            }
        }
        if (zze2 <= 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    public final String zzk() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                String str = new String(this.zze, i2, zze2, zzadl.zzb);
                this.zzh += zze2;
                return str;
            }
        }
        if (zze2 == 0) {
            return "";
        }
        if (zze2 < 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    public final String zzl() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                String zzd = zzagc.zzd(this.zze, i2, zze2);
                this.zzh += zze2;
                return zzd;
            }
        }
        if (zze2 == 0) {
            return "";
        }
        if (zze2 <= 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    public final void zzm(int i) throws zzadn {
        if (this.zzi != i) {
            throw zzadn.zzb();
        }
    }

    public final void zzn(int i) {
        this.zzj = i;
        zzv();
    }

    public final void zzo(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    public final boolean zzp() throws IOException {
        return this.zzh == this.zzf;
    }

    public final boolean zzq() throws IOException {
        return zzh() != 0;
    }

    public final boolean zzr(int i) throws IOException {
        int zzf2;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzf - this.zzh >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.zze;
                        int i3 = this.zzh;
                        this.zzh = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzadn.zze();
                }
                while (i2 < 10) {
                    if (zza() < 0) {
                        i2++;
                    }
                }
                throw zzadn.zze();
                return true;
            case 1:
                zzo(8);
                return true;
            case 2:
                zzo(zze());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzo(4);
                return true;
            default:
                throw zzadn.zza();
        }
        do {
            zzf2 = zzf();
            if (zzf2 == 0 || !zzr(zzf2)) {
                zzm(((i >>> 3) << 3) | 4);
                return true;
            }
            zzf2 = zzf();
            zzm(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzr(zzf2));
        zzm(((i >>> 3) << 3) | 4);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
        if (r2[r3] >= 0) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zze() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 != r0) goto L_0x0008
            goto L_0x006f
        L_0x0008:
            byte[] r2 = r5.zze
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0013
            r5.zzh = r3
            return r0
        L_0x0013:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006f
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0024
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x006c
        L_0x0024:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0031
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L_0x006c
        L_0x0031:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003f
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x006c
        L_0x003f:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006c
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006c
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x006b
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006f
            goto L_0x006c
        L_0x006b:
            r1 = r3
        L_0x006c:
            r5.zzh = r1
            return r0
        L_0x006f:
            long r0 = r5.zzi()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzace.zze():int");
    }

    public final long zzh() throws IOException {
        long j;
        int i = this.zzh;
        int i2 = this.zzf;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.zzh = i3;
                return (long) b;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                byte b2 = b ^ (bArr[i3] << 7);
                if (b2 < 0) {
                    j = (long) (b2 ^ Byte.MIN_VALUE);
                } else {
                    int i5 = i4 + 1;
                    byte b3 = b2 ^ (bArr[i4] << Ascii.SO);
                    if (b3 >= 0) {
                        i4 = i5;
                        j = (long) (b3 ^ 16256);
                    } else {
                        i4 = i5 + 1;
                        byte b4 = b3 ^ (bArr[i5] << Ascii.NAK);
                        if (b4 < 0) {
                            j = (long) (b4 ^ -2080896);
                        } else {
                            int i6 = i4 + 1;
                            long j2 = (((long) bArr[i4]) << 28) ^ ((long) b4);
                            if (j2 >= 0) {
                                i4 = i6;
                                j = j2 ^ 266354560;
                            } else {
                                int i7 = i6 + 1;
                                long j3 = j2 ^ (((long) bArr[i6]) << 35);
                                if (j3 < 0) {
                                    j = -34093383808L ^ j3;
                                    i4 = i7;
                                } else {
                                    int i8 = i7 + 1;
                                    long j4 = j3 ^ (((long) bArr[i7]) << 42);
                                    if (j4 >= 0) {
                                        i4 = i8;
                                        j = j4 ^ 4363953127296L;
                                    } else {
                                        int i9 = i8 + 1;
                                        long j5 = j4 ^ (((long) bArr[i8]) << 49);
                                        if (j5 < 0) {
                                            j = -558586000294016L ^ j5;
                                            i4 = i9;
                                        } else {
                                            int i10 = i9 + 1;
                                            long j6 = (j5 ^ (((long) bArr[i9]) << 56)) ^ 71499008037633920L;
                                            if (j6 < 0) {
                                                int i11 = i10 + 1;
                                                if (((long) bArr[i10]) >= 0) {
                                                    j = j6;
                                                    i4 = i11;
                                                }
                                            } else {
                                                i4 = i10;
                                                j = j6;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.zzh = i4;
                return j;
            }
        }
        return zzi();
    }
}
