package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzabg implements zzxm {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    private zzabg() {
    }

    public static zzabg zzb(String str, String str2, boolean z) {
        zzabg zzabg = new zzabg();
        zzabg.zzb = Preconditions.checkNotEmpty(str);
        zzabg.zzc = Preconditions.checkNotEmpty(str2);
        zzabg.zzf = z;
        return zzabg;
    }

    public static zzabg zzc(String str, String str2, boolean z) {
        zzabg zzabg = new zzabg();
        zzabg.zza = Preconditions.checkNotEmpty(str);
        zzabg.zzd = Preconditions.checkNotEmpty(str2);
        zzabg.zzf = z;
        return zzabg;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put("phoneNumber", this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put("code", this.zzc);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    public final void zzd(String str) {
        this.zze = str;
    }
}
