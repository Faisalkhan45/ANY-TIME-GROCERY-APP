package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzyz {
    private static final Map zza = new ArrayMap();
    private static final Map zzb = new ArrayMap();

    public static String zza(String str) {
        zzyx zzyx;
        Map map = zza;
        synchronized (map) {
            zzyx = (zzyx) map.get(str);
        }
        if (zzyx != null) {
            return zzh(zzyx.zzb(), zzyx.zza(), zzyx.zzb().contains(":")).concat("emulator/auth/handler");
        }
        throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
    }

    public static String zzb(String str) {
        zzyx zzyx;
        Map map = zza;
        synchronized (map) {
            zzyx = (zzyx) map.get(str);
        }
        return (zzyx != null ? "".concat(zzh(zzyx.zzb(), zzyx.zza(), zzyx.zzb().contains(":"))) : "https://").concat("www.googleapis.com/identitytoolkit/v3/relyingparty");
    }

    public static String zzc(String str) {
        zzyx zzyx;
        Map map = zza;
        synchronized (map) {
            zzyx = (zzyx) map.get(str);
        }
        return (zzyx != null ? "".concat(zzh(zzyx.zzb(), zzyx.zza(), zzyx.zzb().contains(":"))) : "https://").concat("identitytoolkit.googleapis.com/v2");
    }

    public static String zzd(String str) {
        zzyx zzyx;
        Map map = zza;
        synchronized (map) {
            zzyx = (zzyx) map.get(str);
        }
        return (zzyx != null ? "".concat(zzh(zzyx.zzb(), zzyx.zza(), zzyx.zzb().contains(":"))) : "https://").concat("securetoken.googleapis.com/v1");
    }

    public static void zze(String str, zzyy zzyy) {
        Map map = zzb;
        synchronized (map) {
            if (map.containsKey(str)) {
                ((List) map.get(str)).add(new WeakReference(zzyy));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WeakReference(zzyy));
                map.put(str, arrayList);
            }
        }
    }

    public static void zzf(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map map = zza;
        synchronized (map) {
            map.put(apiKey, new zzyx(str, i));
        }
        Map map2 = zzb;
        synchronized (map2) {
            if (map2.containsKey(apiKey)) {
                boolean z = false;
                for (WeakReference weakReference : (List) map2.get(apiKey)) {
                    zzyy zzyy = (zzyy) weakReference.get();
                    if (zzyy != null) {
                        zzyy.zzi();
                        z = true;
                    }
                }
                if (!z) {
                    zza.remove(apiKey);
                }
            }
        }
    }

    public static boolean zzg(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }

    private static String zzh(String str, int i, boolean z) {
        if (z) {
            return "http://[" + str + "]:" + i + "/";
        }
        return "http://" + str + ":" + i + "/";
    }
}
