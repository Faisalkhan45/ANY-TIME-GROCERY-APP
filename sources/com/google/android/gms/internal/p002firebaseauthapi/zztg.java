package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.UserProfileChangeRequest;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zztg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zztg> CREATOR = new zzth();
    private final UserProfileChangeRequest zza;
    private final String zzb;

    public zztg(UserProfileChangeRequest userProfileChangeRequest, String str) {
        this.zza = userProfileChangeRequest;
        this.zzb = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final UserProfileChangeRequest zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }
}
