package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhl {
    private zzhv zza = null;
    private zzqw zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzhl() {
    }

    /* synthetic */ zzhl(zzhk zzhk) {
    }

    public final zzhl zza(zzqw zzqw) throws GeneralSecurityException {
        this.zzb = zzqw;
        return this;
    }

    public final zzhl zzb(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzhl zzc(zzhv zzhv) {
        this.zza = zzhv;
        return this;
    }

    public final zzhn zzd() throws GeneralSecurityException {
        zzqw zzqw;
        zzqv zzqv;
        zzhv zzhv = this.zza;
        if (zzhv == null || (zzqw = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        } else if (zzhv.zza() != zzqw.zza()) {
            throw new GeneralSecurityException("Key size mismatch");
        } else if (zzhv.zzd() && this.zzc == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with format with ID requirement");
        } else if (this.zza.zzd() || this.zzc == null) {
            if (this.zza.zzc() == zzht.zzd) {
                zzqv = zzqv.zzb(new byte[0]);
            } else if (this.zza.zzc() == zzht.zzc || this.zza.zzc() == zzht.zzb) {
                zzqv = zzqv.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
            } else if (this.zza.zzc() == zzht.zza) {
                zzqv = zzqv.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
            } else {
                throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzc()))));
            }
            return new zzhn(this.zza, this.zzb, zzqv, this.zzc, (zzhm) null);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with format without ID requirement");
        }
    }
}
