package com.google.android.gms.internal.p001authapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.auth-api.zzd  reason: invalid package */
public class zzd extends Binder implements IInterface {
    private static zzf zze = null;

    protected zzd(String str) {
        attachInterface(this, str);
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean z;
        if (i > 16777215) {
            z = super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z = false;
        }
        if (z) {
            return true;
        }
        return dispatchTransaction(i, parcel, parcel2, i2);
    }

    /* access modifiers changed from: protected */
    public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return false;
    }
}
