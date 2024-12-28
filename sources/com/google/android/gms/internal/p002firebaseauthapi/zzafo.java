package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzafo {
    private static final zzafo zza = new zzafo(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzafo() {
        this(0, new int[8], new Object[8], true);
    }

    private zzafo(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzafo zzc() {
        return zza;
    }

    static zzafo zze(zzafo zzafo, zzafo zzafo2) {
        int i = zzafo.zzb + zzafo2.zzb;
        int[] copyOf = Arrays.copyOf(zzafo.zzc, i);
        System.arraycopy(zzafo2.zzc, 0, copyOf, zzafo.zzb, zzafo2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzafo.zzd, i);
        System.arraycopy(zzafo2.zzd, 0, copyOf2, zzafo.zzb, zzafo2.zzb);
        return new zzafo(i, copyOf, copyOf2, true);
    }

    static zzafo zzf() {
        return new zzafo(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzafo)) {
            return false;
        }
        zzafo zzafo = (zzafo) obj;
        int i = this.zzb;
        if (i == zzafo.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzafo.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzafo.zzd;
                    int i3 = this.zzb;
                    int i4 = 0;
                    while (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        }
                    }
                    return true;
                } else if (iArr[i2] != iArr2[i2]) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            switch (i4 & 7) {
                case 0:
                    i2 += zzacn.zzE(i5 << 3) + zzacn.zzF(((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i3]).longValue();
                    i2 += zzacn.zzE(i5 << 3) + 8;
                    break;
                case 2:
                    int zzE = zzacn.zzE(i5 << 3);
                    int zzd2 = ((zzacc) this.zzd[i3]).zzd();
                    i2 += zzE + zzacn.zzE(zzd2) + zzd2;
                    break;
                case 3:
                    int zzD = zzacn.zzD(i5);
                    i2 += zzD + zzD + ((zzafo) this.zzd[i3]).zza();
                    break;
                case 5:
                    ((Integer) this.zzd[i3]).intValue();
                    i2 += zzacn.zzE(i5 << 3) + 4;
                    break;
                default:
                    throw new IllegalStateException(zzadn.zza());
            }
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int zzE = zzacn.zzE(8);
            int zzd2 = ((zzacc) this.zzd[i3]).zzd();
            i2 += zzE + zzE + zzacn.zzE(16) + zzacn.zzE(i4 >>> 3) + zzacn.zzE(24) + zzacn.zzE(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzafo zzd(zzafo zzafo) {
        if (zzafo.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzafo.zzb;
        zzl(i);
        System.arraycopy(zzafo.zzc, 0, this.zzc, this.zzb, zzafo.zzb);
        System.arraycopy(zzafo.zzd, 0, this.zzd, this.zzb, zzafo.zzb);
        this.zzb = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        this.zzf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzaem.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    public final void zzk(zzaco zzaco) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                switch (i2 & 7) {
                    case 0:
                        zzaco.zzt(i3, ((Long) obj).longValue());
                        break;
                    case 1:
                        zzaco.zzm(i3, ((Long) obj).longValue());
                        break;
                    case 2:
                        zzaco.zzd(i3, (zzacc) obj);
                        break;
                    case 3:
                        zzaco.zzE(i3);
                        ((zzafo) obj).zzk(zzaco);
                        zzaco.zzh(i3);
                        break;
                    case 5:
                        zzaco.zzk(i3, ((Integer) obj).intValue());
                        break;
                    default:
                        throw new RuntimeException(zzadn.zza());
                }
            }
        }
    }
}
