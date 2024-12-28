package com.razorpay;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: AdvertisingIdUtil */
final class R$$r_ implements IInterface {
    private IBinder a;

    R$$r_(IBinder iBinder) {
        this.a = iBinder;
    }

    public final IBinder asBinder() {
        return this.a;
    }

    public final String a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
