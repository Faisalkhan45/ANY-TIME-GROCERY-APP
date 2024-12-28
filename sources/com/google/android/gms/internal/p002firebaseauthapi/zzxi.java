package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class zzxi {
    final String zza;
    final zzxq zzb;

    public zzxi(String str, zzxq zzxq) {
        this.zza = str;
        this.zzb = zzxq;
    }

    /* access modifiers changed from: package-private */
    public final String zza(String str, String str2) {
        String str3 = this.zza;
        return str3 + str + "?key=" + str2;
    }
}
