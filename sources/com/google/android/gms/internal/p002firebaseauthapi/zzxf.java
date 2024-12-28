package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final /* synthetic */ class zzxf {
    public static String zza(zzxg zzxg, String str) {
        try {
            String str2 = new String(MessageDigest.getInstance("SHA-256").digest(str.getBytes()));
            int length = str2.length();
            int i = 0;
            while (i < length) {
                if (zzi.zza(str2.charAt(i))) {
                    char[] charArray = str2.toCharArray();
                    while (i < length) {
                        char c = charArray[i];
                        if (zzi.zza(c)) {
                            charArray[i] = (char) (c ^ ' ');
                        }
                        i++;
                    }
                    return String.valueOf(charArray);
                }
                i++;
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            zzxg.zza.e("Failed to get SHA-256 MessageDigest", new Object[0]);
            return null;
        }
    }
}
