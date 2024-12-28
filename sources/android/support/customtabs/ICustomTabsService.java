package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import java.util.List;

public interface ICustomTabsService extends IInterface {
    Bundle extraCommand(String str, Bundle bundle) throws RemoteException;

    boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle, List<Bundle> list) throws RemoteException;

    boolean newSession(ICustomTabsCallback iCustomTabsCallback) throws RemoteException;

    boolean newSessionWithExtras(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) throws RemoteException;

    int postMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) throws RemoteException;

    boolean receiveFile(ICustomTabsCallback iCustomTabsCallback, Uri uri, int i, Bundle bundle) throws RemoteException;

    boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback, Uri uri) throws RemoteException;

    boolean requestPostMessageChannelWithExtras(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle) throws RemoteException;

    boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) throws RemoteException;

    boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback, int i, Uri uri, Bundle bundle) throws RemoteException;

    boolean warmup(long j) throws RemoteException;

    public static class Default implements ICustomTabsService {
        public boolean warmup(long flags) throws RemoteException {
            return false;
        }

        public boolean newSession(ICustomTabsCallback callback) throws RemoteException {
            return false;
        }

        public boolean newSessionWithExtras(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
            return false;
        }

        public boolean mayLaunchUrl(ICustomTabsCallback callback, Uri url, Bundle extras, List<Bundle> list) throws RemoteException {
            return false;
        }

        public Bundle extraCommand(String commandName, Bundle args) throws RemoteException {
            return null;
        }

        public boolean updateVisuals(ICustomTabsCallback callback, Bundle bundle) throws RemoteException {
            return false;
        }

        public boolean requestPostMessageChannel(ICustomTabsCallback callback, Uri postMessageOrigin) throws RemoteException {
            return false;
        }

        public boolean requestPostMessageChannelWithExtras(ICustomTabsCallback callback, Uri postMessageOrigin, Bundle extras) throws RemoteException {
            return false;
        }

        public int postMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
            return 0;
        }

        public boolean validateRelationship(ICustomTabsCallback callback, int relation, Uri origin, Bundle extras) throws RemoteException {
            return false;
        }

        public boolean receiveFile(ICustomTabsCallback callback, Uri uri, int purpose, Bundle extras) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICustomTabsService {
        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsService";
        static final int TRANSACTION_extraCommand = 5;
        static final int TRANSACTION_mayLaunchUrl = 4;
        static final int TRANSACTION_newSession = 3;
        static final int TRANSACTION_newSessionWithExtras = 10;
        static final int TRANSACTION_postMessage = 8;
        static final int TRANSACTION_receiveFile = 12;
        static final int TRANSACTION_requestPostMessageChannel = 7;
        static final int TRANSACTION_requestPostMessageChannelWithExtras = 11;
        static final int TRANSACTION_updateVisuals = 6;
        static final int TRANSACTION_validateRelationship = 9;
        static final int TRANSACTION_warmup = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICustomTabsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICustomTabsService)) {
                return new Proxy(obj);
            }
            return (ICustomTabsService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Uri _arg1;
            Bundle _arg2;
            Bundle _arg12;
            Bundle _arg13;
            Uri _arg14;
            Bundle _arg22;
            Uri _arg23;
            Bundle _arg3;
            Bundle _arg15;
            Uri _arg16;
            Bundle _arg24;
            Uri _arg17;
            Bundle _arg32;
            switch (code) {
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = warmup(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result2 = newSession(ICustomTabsCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg0 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    boolean _result3 = mayLaunchUrl(_arg0, _arg1, _arg2, data.createTypedArrayList(Bundle.CREATOR));
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    if (data.readInt() != 0) {
                        _arg12 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    Bundle _result4 = extraCommand(_arg02, _arg12);
                    reply.writeNoException();
                    if (_result4 != null) {
                        reply.writeInt(1);
                        _result4.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg03 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg13 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    boolean _result5 = updateVisuals(_arg03, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg04 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg14 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg14 = null;
                    }
                    boolean _result6 = requestPostMessageChannel(_arg04, _arg14);
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg05 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    String _arg18 = data.readString();
                    if (data.readInt() != 0) {
                        _arg22 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg22 = null;
                    }
                    int _result7 = postMessage(_arg05, _arg18, _arg22);
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg06 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    int _arg19 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg23 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    boolean _result8 = validateRelationship(_arg06, _arg19, _arg23, _arg3);
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg07 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg15 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg15 = null;
                    }
                    boolean _result9 = newSessionWithExtras(_arg07, _arg15);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg08 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg16 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg16 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg24 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg24 = null;
                    }
                    boolean _result10 = requestPostMessageChannelWithExtras(_arg08, _arg16, _arg24);
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    ICustomTabsCallback _arg09 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg17 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg17 = null;
                    }
                    int _arg25 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    boolean _result11 = receiveFile(_arg09, _arg17, _arg25, _arg32);
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ICustomTabsService {
            public static ICustomTabsService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public boolean warmup(long flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(flags);
                    boolean z = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().warmup(flags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        z = true;
                    }
                    boolean _status = z;
                    _reply.recycle();
                    _data.recycle();
                    return _status;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean newSession(ICustomTabsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().newSession(callback);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        z = true;
                    }
                    boolean _status = z;
                    _reply.recycle();
                    _data.recycle();
                    return _status;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean newSessionWithExtras(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().newSessionWithExtras(callback, extras);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean mayLaunchUrl(ICustomTabsCallback callback, Uri url, Bundle extras, List<Bundle> otherLikelyBundles) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (url != null) {
                        _data.writeInt(1);
                        url.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedList(otherLikelyBundles);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mayLaunchUrl(callback, url, extras, otherLikelyBundles);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle extraCommand(String commandName, Bundle args) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(commandName);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().extraCommand(commandName, args);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean updateVisuals(ICustomTabsCallback callback, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateVisuals(callback, bundle);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean requestPostMessageChannel(ICustomTabsCallback callback, Uri postMessageOrigin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (postMessageOrigin != null) {
                        _data.writeInt(1);
                        postMessageOrigin.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPostMessageChannel(callback, postMessageOrigin);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean requestPostMessageChannelWithExtras(ICustomTabsCallback callback, Uri postMessageOrigin, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (postMessageOrigin != null) {
                        _data.writeInt(1);
                        postMessageOrigin.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPostMessageChannelWithExtras(callback, postMessageOrigin, extras);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int postMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(message);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().postMessage(callback, message, extras);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean validateRelationship(ICustomTabsCallback callback, int relation, Uri origin, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(relation);
                    boolean _result = true;
                    if (origin != null) {
                        _data.writeInt(1);
                        origin.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().validateRelationship(callback, relation, origin, extras);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean receiveFile(ICustomTabsCallback callback, Uri uri, int purpose, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(purpose);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().receiveFile(callback, uri, purpose, extras);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICustomTabsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICustomTabsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
