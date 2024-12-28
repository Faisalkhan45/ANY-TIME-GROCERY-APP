package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzzt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzzt> CREATOR = new zzzu();
    private final List zza;

    public zzzt() {
        this.zza = new ArrayList();
    }

    public static zzzt zza(zzzt zzzt) {
        Preconditions.checkNotNull(zzzt);
        List list = zzzt.zza;
        zzzt zzzt2 = new zzzt();
        if (list != null && !list.isEmpty()) {
            zzzt2.zza.addAll(list);
        }
        return zzzt2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List zzb() {
        return this.zza;
    }

    zzzt(List list) {
        List list2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.zza = list2;
    }
}
