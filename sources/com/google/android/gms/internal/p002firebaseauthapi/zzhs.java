package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzhs {
    @Nullable
    private Integer zza;
    @Nullable
    private Integer zzb;
    private zzht zzc;

    private zzhs() {
        this.zza = null;
        this.zzb = null;
        throw null;
    }

    /* synthetic */ zzhs(zzhr zzhr) {
        this.zza = null;
        this.zzb = null;
        this.zzc = zzht.zzd;
    }

    public final zzhs zzb(int i) throws GeneralSecurityException {
        if (i < 10 || i > 16) {
            throw new GeneralSecurityException("Invalid tag size for AesCmacParameters: " + i);
        }
        this.zzb = Integer.valueOf(i);
        return this;
    }

    public final zzhs zzc(zzht zzht) {
        this.zzc = zzht;
        return this;
    }

    public final zzhv zzd() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num != null && this.zzb != null) {
            return new zzhv(num.intValue(), this.zzb.intValue(), this.zzc, (zzhu) null);
        }
        throw new GeneralSecurityException("Key size and/or tag size not set");
    }

    public final zzhs zza(int i) throws GeneralSecurityException {
        if (i == 16 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", new Object[]{Integer.valueOf(i * 8)}));
    }
}
