package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzyp {
    private static final Map zza = new ArrayMap();

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, zzyb zzyb) {
        zze(str, zzyb);
        return new zzyn(onVerificationStateChangedCallbacks, str);
    }

    public static void zzc() {
        zza.clear();
    }

    public static boolean zzd(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        Map map = zza;
        if (map.containsKey(str)) {
            zzyo zzyo = (zzyo) map.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzyo.zzb < 120000) {
                zzyb zzyb = zzyo.zza;
                if (zzyb == null) {
                    return true;
                }
                zzyb.zzh(onVerificationStateChangedCallbacks, activity, executor, str);
                return true;
            }
            zze(str, (zzyb) null);
            return false;
        }
        zze(str, (zzyb) null);
        return false;
    }

    private static void zze(String str, zzyb zzyb) {
        zza.put(str, new zzyo(zzyb, DefaultClock.getInstance().currentTimeMillis()));
    }
}
