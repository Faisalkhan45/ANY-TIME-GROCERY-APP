package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public abstract class GmsClientSupervisor {
    static HandlerThread zza;
    private static int zzb = 4225;
    private static final Object zzc = new Object();
    private static zzr zzd;
    private static boolean zze = false;

    public static int getDefaultBindFlags() {
        return zzb;
    }

    public static GmsClientSupervisor getInstance(Context context) {
        Looper looper;
        synchronized (zzc) {
            if (zzd == null) {
                Context applicationContext = context.getApplicationContext();
                if (zze) {
                    looper = getOrStartHandlerThread().getLooper();
                } else {
                    looper = context.getMainLooper();
                }
                zzd = new zzr(applicationContext, looper);
            }
        }
        return zzd;
    }

    public static HandlerThread getOrStartHandlerThread() {
        synchronized (zzc) {
            HandlerThread handlerThread = zza;
            if (handlerThread != null) {
                return handlerThread;
            }
            HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
            zza = handlerThread2;
            handlerThread2.start();
            HandlerThread handlerThread3 = zza;
            return handlerThread3;
        }
    }

    public static void setUseHandlerThreadForCallbacks() {
        synchronized (zzc) {
            zzr zzr = zzd;
            if (zzr != null && !zze) {
                zzr.zzi(getOrStartHandlerThread().getLooper());
            }
            zze = true;
        }
    }

    public boolean bindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        return zzc(new zzn(componentName, getDefaultBindFlags()), connection, realClientName, (Executor) null);
    }

    public void unbindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        zza(new zzn(componentName, getDefaultBindFlags()), connection, realClientName);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzn zzn, ServiceConnection serviceConnection, String str);

    public final void zzb(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        zza(new zzn(str, str2, i, z), serviceConnection, str3);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzc(zzn zzn, ServiceConnection serviceConnection, String str, Executor executor);

    public boolean bindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        return zzc(new zzn(startServiceAction, getDefaultBindFlags(), false), connection, realClientName, (Executor) null);
    }

    public void unbindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        zza(new zzn(startServiceAction, getDefaultBindFlags(), false), connection, realClientName);
    }
}
