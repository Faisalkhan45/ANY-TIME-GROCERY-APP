package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class StreetViewSource extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzq();
    public static final StreetViewSource DEFAULT = new StreetViewSource(0);
    public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
    private static final String TAG = StreetViewSource.class.getSimpleName();
    private final int type;

    public StreetViewSource(int i) {
        this.type = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof StreetViewSource) && this.type == ((StreetViewSource) obj).type) {
            return true;
        }
        return false;
    }

    public final String toString() {
        String str;
        int i = this.type;
        switch (i) {
            case 0:
                str = "DEFAULT";
                break;
            case 1:
                str = "OUTDOOR";
                break;
            default:
                str = String.format("UNKNOWN(%s)", new Object[]{Integer.valueOf(i)});
                break;
        }
        return String.format("StreetViewSource:%s", new Object[]{str});
    }
}
