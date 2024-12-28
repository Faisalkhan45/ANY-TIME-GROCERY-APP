package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.EmailAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzss  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzss extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzss> CREATOR = new zzst();
    private final EmailAuthCredential zza;

    public zzss(EmailAuthCredential emailAuthCredential) {
        this.zza = emailAuthCredential;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final EmailAuthCredential zza() {
        return this.zza;
    }
}
