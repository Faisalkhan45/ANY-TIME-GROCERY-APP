package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
class zzabz extends zzaby {
    protected final byte[] zza;

    zzabz(byte[] bArr) {
        if (bArr != null) {
            this.zza = bArr;
            return;
        }
        throw null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacc) || zzd() != ((zzacc) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzabz)) {
            return obj.equals(this);
        }
        zzabz zzabz = (zzabz) obj;
        int zzm = zzm();
        int zzm2 = zzabz.zzm();
        if (zzm != 0 && zzm2 != 0 && zzm != zzm2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzabz.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd > zzabz.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzabz.zzd());
        } else if (!(zzabz instanceof zzabz)) {
            return zzabz.zzg(0, zzd).equals(zzg(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzabz.zza;
            zzabz.zzc();
            int i = 0;
            int i2 = 0;
            while (i < zzd) {
                if (bArr[i] != bArr2[i2]) {
                    return false;
                }
                i++;
                i2++;
            }
            return true;
        }
    }

    public byte zza(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zza.length;
    }

    /* access modifiers changed from: protected */
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzf(int i, int i2, int i3) {
        return zzadl.zzd(i, this.zza, 0, i3);
    }

    public final zzacc zzg(int i, int i2) {
        int zzl = zzl(0, i2, zzd());
        if (zzl == 0) {
            return zzacc.zzb;
        }
        return new zzabw(this.zza, 0, zzl);
    }

    public final zzacg zzh() {
        return zzacg.zzu(this.zza, 0, zzd(), true);
    }

    /* access modifiers changed from: protected */
    public final String zzi(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzj(zzabs zzabs) throws IOException {
        zzabs.zza(this.zza, 0, zzd());
    }

    public final boolean zzk() {
        return zzagc.zzf(this.zza, 0, zzd());
    }
}
