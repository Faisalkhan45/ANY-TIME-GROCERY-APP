package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public abstract class RemoteCreator<T> {
    private final String zza;
    private Object zzb;

    /* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String message) {
            super(message);
        }

        public RemoteCreatorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    protected RemoteCreator(String str) {
        this.zza = str;
    }

    /* access modifiers changed from: protected */
    public abstract T getRemoteCreator(IBinder iBinder);

    /* access modifiers changed from: protected */
    public final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        if (this.zzb == null) {
            Preconditions.checkNotNull(context);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext != null) {
                try {
                    this.zzb = getRemoteCreator((IBinder) remoteContext.getClassLoader().loadClass(this.zza).newInstance());
                } catch (ClassNotFoundException e) {
                    throw new RemoteCreatorException("Could not load creator class.", e);
                } catch (InstantiationException e2) {
                    throw new RemoteCreatorException("Could not instantiate creator.", e2);
                } catch (IllegalAccessException e3) {
                    throw new RemoteCreatorException("Could not access creator.", e3);
                }
            } else {
                throw new RemoteCreatorException("Could not get remote context.");
            }
        }
        return this.zzb;
    }
}
