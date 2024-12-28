package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzic  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzic {
    private zzin zza = null;
    private zzqw zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzic() {
    }

    /* synthetic */ zzic(zzib zzib) {
    }

    public final zzic zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzic zzb(zzqw zzqw) {
        this.zzb = zzqw;
        return this;
    }

    public final zzic zzc(zzin zzin) {
        this.zza = zzin;
        return this;
    }

    public final zzie zzd() throws GeneralSecurityException {
        zzqw zzqw;
        zzqv zzqv;
        zzin zzin = this.zza;
        if (zzin == null || (zzqw = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzin.zza() != zzqw.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzin.zzd() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with format with ID requirement");
        } else if (this.zza.zzd() || this.zzc == null) {
            if (this.zza.zzc() == zzil.zzd) {
                zzqv = zzqv.zzb(new byte[0]);
            } else if (this.zza.zzc() == zzil.zzc || this.zza.zzc() == zzil.zzb) {
                zzqv = zzqv.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
            } else if (this.zza.zzc() == zzil.zza) {
                zzqv = zzqv.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
            } else {
                throw new IllegalStateException("Unknown HmacParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzc()))));
            }
            return new zzie(this.zza, this.zzb, zzqv, this.zzc, (zzid) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with format without ID requirement");
        }
    }
}
