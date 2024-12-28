package com.google.android.gms.internal.p002firebaseauthapi;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaab  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaab {
    private String zza;
    private String zzb;
    private String zzc;
    private Long zzd;
    private Long zze;

    public static zzaab zza(String str) throws UnsupportedEncodingException {
        try {
            zzaab zzaab = new zzaab();
            JSONObject jSONObject = new JSONObject(str);
            zzaab.zza = jSONObject.optString("iss");
            zzaab.zzb = jSONObject.optString("aud");
            zzaab.zzc = jSONObject.optString("sub");
            zzaab.zzd = Long.valueOf(jSONObject.optLong("iat"));
            zzaab.zze = Long.valueOf(jSONObject.optLong("exp"));
            jSONObject.optBoolean("is_anonymous");
            return zzaab;
        } catch (JSONException e) {
            if (Log.isLoggable("JwtToken", 3)) {
                Log.d("JwtToken", "Failed to read JwtToken from JSONObject. ".concat(e.toString()));
            }
            throw new UnsupportedEncodingException("Failed to read JwtToken from JSONObject. ".concat(e.toString()));
        }
    }

    public final Long zzb() {
        return this.zze;
    }

    public final Long zzc() {
        return this.zzd;
    }
}
