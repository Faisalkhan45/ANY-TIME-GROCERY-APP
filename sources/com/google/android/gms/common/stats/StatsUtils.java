package com.google.android.gms.common.stats;

import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;

@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public class StatsUtils {
    public static String getEventKey(PowerManager.WakeLock wakeLock, String secondaryName) {
        String valueOf = String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock)));
        if (true == TextUtils.isEmpty(secondaryName)) {
            secondaryName = "";
        }
        return String.valueOf(valueOf).concat(String.valueOf(secondaryName));
    }
}
