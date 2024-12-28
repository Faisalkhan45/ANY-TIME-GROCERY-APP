package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzrw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzrw> CREATOR = new zzrx();
    private final String zza;
    private final zzaay zzb;

    public zzrw(String str, zzaay zzaay) {
        this.zza = str;
        this.zzb = zzaay;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzaay zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }
}
