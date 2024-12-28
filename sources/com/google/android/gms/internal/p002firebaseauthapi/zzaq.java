package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaq implements zzbj {
    private final OutputStream zza;

    private zzaq(OutputStream outputStream) {
        this.zza = outputStream;
    }

    public static zzbj zza(OutputStream outputStream) {
        return new zzaq(outputStream);
    }

    public final void zzb(zzmo zzmo) throws IOException {
        throw null;
    }

    public final void zzc(zzof zzof) throws IOException {
        try {
            zzof.zzp(this.zza);
        } finally {
            this.zza.close();
        }
    }
}
