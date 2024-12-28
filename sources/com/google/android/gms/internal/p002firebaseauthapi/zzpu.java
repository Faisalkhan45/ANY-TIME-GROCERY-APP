package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzpu implements zzav {
    private final zzpw zza;
    private final String zzb;
    private final byte[] zzc;
    private final zzps zzd;

    public zzpu(ECPublicKey eCPublicKey, byte[] bArr, String str, int i, zzps zzps) throws GeneralSecurityException {
        zzpx.zze(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zza = new zzpw(eCPublicKey);
        this.zzc = bArr;
        this.zzb = str;
        this.zzd = zzps;
    }
}
