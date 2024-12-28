package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzzd extends AbstractSafeParcelable implements zzxn<zzzd> {
    public static final Parcelable.Creator<zzzd> CREATOR = new zzze();
    private static final String zza = zzzd.class.getSimpleName();
    private String zzb;
    private boolean zzc;
    private String zzd;
    private boolean zze;
    private zzaaw zzf;
    private List zzg;

    public zzzd() {
        this.zzf = new zzaaw((List) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", (String) null);
            this.zzc = jSONObject.optBoolean("registered", false);
            this.zzd = jSONObject.optString("providerId", (String) null);
            this.zze = jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzf = new zzaaw((List) null);
            } else {
                this.zzf = new zzaaw(1, zzabk.zzb(jSONObject.optJSONArray("allProviders")));
            }
            this.zzg = zzabk.zzb(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final List zzb() {
        return this.zzg;
    }

    public zzzd(String str, boolean z, String str2, boolean z2, zzaaw zzaaw, List list) {
        zzaaw zzaaw2;
        this.zzb = str;
        this.zzc = z;
        this.zzd = str2;
        this.zze = z2;
        if (zzaaw == null) {
            zzaaw2 = new zzaaw((List) null);
        } else {
            zzaaw2 = zzaaw.zza(zzaaw);
        }
        this.zzf = zzaaw2;
        this.zzg = list;
    }
}
