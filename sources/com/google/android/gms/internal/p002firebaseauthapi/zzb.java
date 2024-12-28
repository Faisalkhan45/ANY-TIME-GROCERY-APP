package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzb extends ContextCompat {
    @Deprecated
    public static Intent zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        int i;
        if (!zza.zza()) {
            return context.registerReceiver(broadcastReceiver, intentFilter);
        }
        if (true != zza.zza()) {
            i = 0;
        } else {
            i = 2;
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, i);
    }
}
