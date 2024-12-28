package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;
import kotlin.time.DurationKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxo {
    private final int zza;

    public zzxo(String str) {
        int i = -1;
        try {
            List zzd = zzaf.zzc("[.-]").zzd(str);
            if (zzd.size() == 1) {
                i = Integer.parseInt(str);
            } else if (zzd.size() >= 3) {
                i = (Integer.parseInt((String) zzd.get(0)) * DurationKt.NANOS_IN_MILLIS) + (Integer.parseInt((String) zzd.get(1)) * 1000) + Integer.parseInt((String) zzd.get(2));
            }
        } catch (IllegalArgumentException e) {
            if (Log.isLoggable("LibraryVersionContainer", 3)) {
                Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", new Object[]{str, e}));
            }
        }
        this.zza = i;
    }

    public static zzxo zza() {
        String version = LibraryVersion.getInstance().getVersion("firebase-auth");
        if (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) {
            version = "-1";
        }
        return new zzxo(version);
    }

    public final String zzb() {
        return String.format("X%s", new Object[]{Integer.toString(this.zza)});
    }
}
