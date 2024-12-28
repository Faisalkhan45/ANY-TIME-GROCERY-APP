package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzzx implements zzxn {
    private static final String zza = zzzx.class.getSimpleName();
    private List zzb;

    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        zzb(str);
        return this;
    }

    public final zzzx zzb(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("authorizedDomains");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.zzb.add(optJSONArray.getString(i));
                }
            }
            return this;
        } catch (JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final List zzc() {
        return this.zzb;
    }
}
