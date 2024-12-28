package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaag  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaag> CREATOR = new zzaah();
    private final List zza;

    public zzaag() {
        this.zza = new ArrayList();
    }

    public static zzaag zza(JSONArray jSONArray) throws JSONException {
        zzaae zzaae;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new zzaag(new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject == null) {
                zzaae = new zzaae();
            } else {
                zzaae = new zzaae(Strings.emptyToNull(jSONObject.optString("federatedId", (String) null)), Strings.emptyToNull(jSONObject.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null)), Strings.emptyToNull(jSONObject.optString("providerId", (String) null)), (String) null, Strings.emptyToNull(jSONObject.optString("phoneNumber", (String) null)), Strings.emptyToNull(jSONObject.optString("email", (String) null)));
            }
            arrayList.add(zzaae);
        }
        return new zzaag(arrayList);
    }

    public static zzaag zzb(zzaag zzaag) {
        List list = zzaag.zza;
        zzaag zzaag2 = new zzaag();
        if (list != null) {
            zzaag2.zza.addAll(list);
        }
        return zzaag2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List zzc() {
        return this.zza;
    }

    zzaag(List list) {
        if (list == null || list.isEmpty()) {
            this.zza = Collections.emptyList();
        } else {
            this.zza = Collections.unmodifiableList(list);
        }
    }
}
