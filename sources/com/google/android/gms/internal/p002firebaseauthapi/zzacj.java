package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzacj extends zzacn {
    private final byte[] zza;
    private final int zzb;
    private int zzc;

    zzacj(byte[] bArr, int i, int i2) {
        super((zzacm) null);
        if (bArr != null) {
            int length = bArr.length;
            if (((length - i2) | i2) >= 0) {
                this.zza = bArr;
                this.zzc = 0;
                this.zzb = i2;
                return;
            }
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i2)}));
        }
        throw new NullPointerException("buffer");
    }

    public final void zzN() {
    }

    public final void zzP(int i, boolean z) throws IOException {
        zzs(i << 3);
        zzO(z ? (byte) 1 : 0);
    }

    public final void zzQ(int i, zzacc zzacc) throws IOException {
        zzs((i << 3) | 2);
        zzs(zzacc.zzd());
        zzacc.zzj(this);
    }

    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zze(bArr, 0, i2);
    }

    public final int zzb() {
        return this.zzb - this.zzc;
    }

    public final void zzh(int i, int i2) throws IOException {
        zzs((i << 3) | 5);
        zzi(i2);
    }

    public final void zzi(int i) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            int i3 = i2 + 1;
            this.zzc = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            this.zzc = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            this.zzc = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.zzc = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzj(int i, long j) throws IOException {
        zzs((i << 3) | 1);
        zzk(j);
    }

    public final void zzk(long j) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            int i2 = i + 1;
            this.zzc = i2;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            this.zzc = i3;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            this.zzc = i4;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            this.zzc = i5;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            this.zzc = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            this.zzc = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            this.zzc = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.zzc = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzl(int i, int i2) throws IOException {
        zzs(i << 3);
        zzm(i2);
    }

    public final void zzm(int i) throws IOException {
        if (i >= 0) {
            zzs(i);
        } else {
            zzu((long) i);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzn(int i, zzaek zzaek, zzaew zzaew) throws IOException {
        zzs((i << 3) | 2);
        zzs(((zzabm) zzaek).zzn(zzaew));
        zzaew.zzn(zzaek, this.zze);
    }

    public final void zzo(int i, String str) throws IOException {
        zzs((i << 3) | 2);
        zzp(str);
    }

    public final void zzp(String str) throws IOException {
        int i = this.zzc;
        try {
            int zzE = zzE(str.length() * 3);
            int zzE2 = zzE(str.length());
            if (zzE2 == zzE) {
                int i2 = i + zzE2;
                this.zzc = i2;
                int zzb2 = zzagc.zzb(str, this.zza, i2, this.zzb - i2);
                this.zzc = i;
                zzs((zzb2 - i) - zzE2);
                this.zzc = zzb2;
                return;
            }
            zzs(zzagc.zzc(str));
            byte[] bArr = this.zza;
            int i3 = this.zzc;
            this.zzc = zzagc.zzb(str, bArr, i3, this.zzb - i3);
        } catch (zzagb e) {
            this.zzc = i;
            zzJ(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzack(e2);
        }
    }

    public final void zzq(int i, int i2) throws IOException {
        zzs((i << 3) | i2);
    }

    public final void zzr(int i, int i2) throws IOException {
        zzs(i << 3);
        zzs(i2);
    }

    public final void zzt(int i, long j) throws IOException {
        zzs(i << 3);
        zzu(j);
    }

    public final void zzO(byte b) throws IOException {
        try {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zze(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zza, this.zzc, i2);
            this.zzc += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(i2)}), e);
        }
    }

    public final void zzs(int i) throws IOException {
        while ((i & -128) != 0) {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            this.zzc = i2 + 1;
            bArr[i2] = (byte) ((i & WorkQueueKt.MASK) | 128);
            i >>>= 7;
        }
        try {
            byte[] bArr2 = this.zza;
            int i3 = this.zzc;
            this.zzc = i3 + 1;
            bArr2[i3] = (byte) i;
        } catch (IndexOutOfBoundsException e) {
            throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
        }
    }

    public final void zzu(long j) throws IOException {
        if (!zzacn.zzb || this.zzb - this.zzc < 10) {
            while ((j & -128) != 0) {
                byte[] bArr = this.zza;
                int i = this.zzc;
                this.zzc = i + 1;
                bArr[i] = (byte) ((((int) j) & WorkQueueKt.MASK) | 128);
                j >>>= 7;
            }
            try {
                byte[] bArr2 = this.zza;
                int i2 = this.zzc;
                this.zzc = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
            } catch (IndexOutOfBoundsException e) {
                throw new zzack(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1}), e);
            }
        } else {
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                zzafx.zzn(bArr3, (long) i3, (byte) ((((int) j) & WorkQueueKt.MASK) | 128));
                j >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            zzafx.zzn(bArr4, (long) i4, (byte) ((int) j));
        }
    }
}
