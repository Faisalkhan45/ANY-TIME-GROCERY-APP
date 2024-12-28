package com.google.firebase.auth.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.safetynet.SafetyNetApi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbf {
    private static final String zza = zzbf.class.getSimpleName();

    private zzbf() {
    }

    public static boolean zza(SafetyNetApi.AttestationResponse attestationResponse) {
        if (attestationResponse == null || TextUtils.isEmpty(attestationResponse.getJwsResult())) {
            Log.e(zza, "No SafetyNet AttestationResponse passed.");
            return false;
        }
        zzbe zza2 = zzbe.zza(attestationResponse.getJwsResult());
        if (zza2 == null) {
            Log.e(zza, "Unable to parse SafetyNet AttestationResponse");
            return false;
        } else if (!zza2.zzc()) {
            Log.e(zza, "SafetyNet Attestation fails basic integrity.");
            return false;
        } else if (TextUtils.isEmpty(zza2.zzb())) {
            return true;
        } else {
            Log.e(zza, "SafetyNet Attestation has advice: \n".concat(String.valueOf(zza2.zzb())));
            return false;
        }
    }
}
