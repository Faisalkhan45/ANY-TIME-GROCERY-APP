package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class Cap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String TAG = Cap.class.getSimpleName();
    private final BitmapDescriptor bitmapDescriptor;
    private final int type;
    private final Float zzcn;

    private Cap(int i, BitmapDescriptor bitmapDescriptor2, Float f) {
        Preconditions.checkArgument(i != 3 || (bitmapDescriptor2 != null && (f != null && (f.floatValue() > 0.0f ? 1 : (f.floatValue() == 0.0f ? 0 : -1)) > 0)), String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), bitmapDescriptor2, f}));
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor2;
        this.zzcn = f;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Cap(int r2, android.os.IBinder r3, java.lang.Float r4) {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x0005
            r3 = 0
            goto L_0x0010
        L_0x0005:
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = new com.google.android.gms.maps.model.BitmapDescriptor
            r0.<init>(r3)
            r3 = r0
        L_0x0010:
            r1.<init>((int) r2, (com.google.android.gms.maps.model.BitmapDescriptor) r3, (java.lang.Float) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.<init>(int, android.os.IBinder, java.lang.Float):void");
    }

    protected Cap(BitmapDescriptor bitmapDescriptor2, float f) {
        this(3, bitmapDescriptor2, Float.valueOf(f));
    }

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        BitmapDescriptor bitmapDescriptor2 = this.bitmapDescriptor;
        if (bitmapDescriptor2 == null) {
            iBinder = null;
        } else {
            iBinder = bitmapDescriptor2.zzb().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcn);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        if (this.type != cap.type || !Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) || !Objects.equal(this.zzcn, cap.zzcn)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return new StringBuilder(23).append("[Cap: type=").append(this.type).append("]").toString();
    }

    /* access modifiers changed from: package-private */
    public final Cap zzh() {
        int i = this.type;
        switch (i) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzcn.floatValue());
            default:
                Log.w(TAG, new StringBuilder(29).append("Unknown Cap type: ").append(i).toString());
                return this;
        }
    }
}
