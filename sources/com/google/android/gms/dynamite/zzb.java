package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public final class zzb {
    private static volatile ClassLoader zza = null;
    private static volatile Thread zzb = null;

    public static synchronized ClassLoader zza() {
        ClassLoader classLoader;
        synchronized (zzb.class) {
            if (zza == null) {
                zza = zzb();
            }
            classLoader = zza;
        }
        return classLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (zzb != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.ClassLoader zzb() {
        /*
            java.lang.Class<com.google.android.gms.dynamite.zzb> r0 = com.google.android.gms.dynamite.zzb.class
            monitor-enter(r0)
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0040 }
            r2 = 0
            if (r1 != 0) goto L_0x0015
            java.lang.Thread r1 = zzc()     // Catch:{ all -> 0x0040 }
            zzb = r1     // Catch:{ all -> 0x0040 }
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            monitor-exit(r0)
            return r2
        L_0x0015:
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x0040 }
            monitor-enter(r1)     // Catch:{ all -> 0x0040 }
            java.lang.Thread r3 = zzb     // Catch:{ SecurityException -> 0x0021 }
            java.lang.ClassLoader r2 = r3.getContextClassLoader()     // Catch:{ SecurityException -> 0x0021 }
            goto L_0x003c
        L_0x001f:
            r2 = move-exception
            goto L_0x003e
        L_0x0021:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r4.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r5 = "Failed to get thread context classloader "
            r4.append(r5)     // Catch:{ all -> 0x001f }
            r4.append(r3)     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "DynamiteLoaderV2CL"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x001f }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x001f }
        L_0x003c:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            goto L_0x0013
        L_0x003e:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            throw r2     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.zzb.zzb():java.lang.ClassLoader");
    }

    private static synchronized Thread zzc() {
        zza zza2;
        SecurityException e;
        zza zza3;
        ThreadGroup threadGroup;
        synchronized (zzb.class) {
            ThreadGroup threadGroup2 = Looper.getMainLooper().getThread().getThreadGroup();
            if (threadGroup2 == null) {
                return null;
            }
            synchronized (Void.class) {
                try {
                    int activeGroupCount = threadGroup2.activeGroupCount();
                    ThreadGroup[] threadGroupArr = new ThreadGroup[activeGroupCount];
                    threadGroup2.enumerate(threadGroupArr);
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= activeGroupCount) {
                            threadGroup = null;
                            break;
                        }
                        threadGroup = threadGroupArr[i2];
                        if ("dynamiteLoader".equals(threadGroup.getName())) {
                            break;
                        }
                        i2++;
                    }
                    if (threadGroup == null) {
                        threadGroup = new ThreadGroup(threadGroup2, "dynamiteLoader");
                    }
                    int activeCount = threadGroup.activeCount();
                    Thread[] threadArr = new Thread[activeCount];
                    threadGroup.enumerate(threadArr);
                    while (true) {
                        if (i >= activeCount) {
                            zza2 = null;
                            break;
                        }
                        zza2 = threadArr[i];
                        if ("GmsDynamite".equals(zza2.getName())) {
                            break;
                        }
                        i++;
                    }
                    if (zza2 == null) {
                        try {
                            zza3 = new zza(threadGroup, "GmsDynamite");
                            try {
                                zza3.setContextClassLoader((ClassLoader) null);
                                zza3.start();
                                zza2 = zza3;
                            } catch (SecurityException e2) {
                                e = e2;
                                Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + e.getMessage());
                                zza2 = zza3;
                                return zza2;
                            }
                        } catch (SecurityException e3) {
                            e = e3;
                            zza3 = zza2;
                            Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + e.getMessage());
                            zza2 = zza3;
                            return zza2;
                        }
                    }
                } catch (SecurityException e4) {
                    e = e4;
                    zza3 = null;
                    Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + e.getMessage());
                    zza2 = zza3;
                    return zza2;
                }
            }
            return zza2;
        }
    }
}
