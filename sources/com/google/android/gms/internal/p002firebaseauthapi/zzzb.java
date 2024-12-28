package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzzb implements zzxn {
    private static final String zza = zzzb.class.getName();
    private String zzb;

    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        zzb(str);
        return this;
    }

    public final zzzb zzb(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getString("error"));
            jSONObject.getInt("code");
            this.zzb = jSONObject.getString("message");
            return this;
        } catch (NullPointerException | JSONException e) {
            String str2 = zza;
            String message = e.getMessage();
            Log.e(str2, "Failed to parse error for string [" + str + "] with exception: " + message);
            throw new zzvg("Failed to parse error for string [" + str + "]", e);
        }
    }

    public final String zzc() {
        return this.zzb;
    }

    public final boolean zzd() {
        return !TextUtils.isEmpty(this.zzb);
    }
}
