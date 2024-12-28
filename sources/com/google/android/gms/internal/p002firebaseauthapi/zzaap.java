package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaap  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaap implements zzxn {
    private static final String zza = zzaap.class.getSimpleName();
    private String zzb;
    private String zzc;
    private Boolean zzd;
    private String zze;
    private String zzf;
    private zzaag zzg;
    private String zzh;
    private String zzi;
    private long zzj;

    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email", (String) null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("passwordHash", (String) null));
            this.zzd = Boolean.valueOf(jSONObject.optBoolean("emailVerified", false));
            this.zze = Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            this.zzf = Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzg = zzaag.zza(jSONObject.optJSONArray("providerUserInfo"));
            this.zzh = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zzi = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzj = jSONObject.optLong("expiresIn", 0);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final long zzb() {
        return this.zzj;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final String zze() {
        return this.zzi;
    }

    public final List zzf() {
        zzaag zzaag = this.zzg;
        if (zzaag != null) {
            return zzaag.zzc();
        }
        return null;
    }
}
