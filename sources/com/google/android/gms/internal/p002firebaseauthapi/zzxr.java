package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxr {
    public static String zza() {
        Locale locale = Locale.getDefault();
        StringBuilder sb = new StringBuilder();
        zzb(sb, locale);
        if (!locale.equals(Locale.US)) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            zzb(sb, Locale.US);
        }
        return sb.toString();
    }

    private static void zzb(StringBuilder sb, Locale locale) {
        String language = locale.getLanguage();
        if (language != null) {
            sb.append(language);
            String country = locale.getCountry();
            if (country != null) {
                sb.append("-");
                sb.append(country);
            }
        }
    }
}
