package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzqj {
    public static String zza(byte[] bArr) {
        StringBuilder sb = new StringBuilder(r1 + r1);
        for (byte b : bArr) {
            byte b2 = b & 255;
            sb.append("0123456789abcdef".charAt(b2 >> 4));
            sb.append("0123456789abcdef".charAt(b2 & Ascii.SI));
        }
        return sb.toString();
    }
}
