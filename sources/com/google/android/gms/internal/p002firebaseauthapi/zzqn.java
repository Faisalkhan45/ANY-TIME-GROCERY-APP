package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.util.Arrays;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzqn implements zzjk {
    private final ThreadLocal zza;
    /* access modifiers changed from: private */
    public final String zzb;
    /* access modifiers changed from: private */
    public final Key zzc;
    private final int zzd;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        if (r4.equals("HMACSHA256") != false) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzqn(java.lang.String r4, java.security.Key r5) throws java.security.GeneralSecurityException {
        /*
            r3 = this;
            r3.<init>()
            com.google.android.gms.internal.firebase-auth-api.zzqm r0 = new com.google.android.gms.internal.firebase-auth-api.zzqm
            r0.<init>(r3)
            r3.zza = r0
            r1 = 2
            boolean r2 = com.google.android.gms.internal.p002firebaseauthapi.zzdv.zza(r1)
            if (r2 == 0) goto L_0x0087
            r3.zzb = r4
            r3.zzc = r5
            byte[] r5 = r5.getEncoded()
            int r5 = r5.length
            r2 = 16
            if (r5 < r2) goto L_0x007f
            int r5 = r4.hashCode()
            switch(r5) {
                case -1823053428: goto L_0x004d;
                case 392315023: goto L_0x0043;
                case 392315118: goto L_0x003a;
                case 392316170: goto L_0x0030;
                case 392317873: goto L_0x0026;
                default: goto L_0x0025;
            }
        L_0x0025:
            goto L_0x0057
        L_0x0026:
            java.lang.String r5 = "HMACSHA512"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0057
            r1 = 4
            goto L_0x0058
        L_0x0030:
            java.lang.String r5 = "HMACSHA384"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0057
            r1 = 3
            goto L_0x0058
        L_0x003a:
            java.lang.String r5 = "HMACSHA256"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0057
            goto L_0x0058
        L_0x0043:
            java.lang.String r5 = "HMACSHA224"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0057
            r1 = 1
            goto L_0x0058
        L_0x004d:
            java.lang.String r5 = "HMACSHA1"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0057
            r1 = 0
            goto L_0x0058
        L_0x0057:
            r1 = -1
        L_0x0058:
            switch(r1) {
                case 0: goto L_0x0076;
                case 1: goto L_0x0071;
                case 2: goto L_0x006e;
                case 3: goto L_0x006b;
                case 4: goto L_0x0068;
                default: goto L_0x005b;
            }
        L_0x005b:
            java.security.NoSuchAlgorithmException r5 = new java.security.NoSuchAlgorithmException
            java.lang.String r0 = "unknown Hmac algorithm: "
            java.lang.String r4 = r0.concat(r4)
            r5.<init>(r4)
            throw r5
        L_0x0068:
            r4 = 64
            goto L_0x0073
        L_0x006b:
            r4 = 48
            goto L_0x0073
        L_0x006e:
            r4 = 32
            goto L_0x0073
        L_0x0071:
            r4 = 28
        L_0x0073:
            r3.zzd = r4
            goto L_0x007a
        L_0x0076:
            r4 = 20
            r3.zzd = r4
        L_0x007a:
            r0.get()
            return
        L_0x007f:
            java.security.InvalidAlgorithmParameterException r4 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r5 = "key size too small, need at least 16 bytes"
            r4.<init>(r5)
            throw r4
        L_0x0087:
            java.security.GeneralSecurityException r4 = new java.security.GeneralSecurityException
            java.lang.String r5 = "Can not use HMAC in FIPS-mode, as BoringCrypto module is not available."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzqn.<init>(java.lang.String, java.security.Key):void");
    }

    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        if (i <= this.zzd) {
            ((Mac) this.zza.get()).update(bArr);
            return Arrays.copyOf(((Mac) this.zza.get()).doFinal(), i);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }
}
