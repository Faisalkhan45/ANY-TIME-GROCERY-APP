package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzfr implements zzbj {
    private final SharedPreferences.Editor zza;
    private final String zzb = "GenericIdpKeyset";

    public zzfr(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
        }
    }

    public final void zzb(zzmo zzmo) throws IOException {
        if (!this.zza.putString(this.zzb, zzqj.zza(zzmo.zzq())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    public final void zzc(zzof zzof) throws IOException {
        if (!this.zza.putString(this.zzb, zzqj.zza(zzof.zzq())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
