package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxh {
    private static Boolean zza = null;

    public static boolean zza(Context context) {
        if (zza == null) {
            int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000);
            boolean z = true;
            if (!(isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2)) {
                z = false;
            }
            zza = Boolean.valueOf(z);
        }
        return zza.booleanValue();
    }
}
