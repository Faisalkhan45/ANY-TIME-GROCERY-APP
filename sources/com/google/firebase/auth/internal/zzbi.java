package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p002firebaseauthapi.zzzy;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbi {
    private volatile int zza = 0;
    /* access modifiers changed from: private */
    public final zzam zzb;
    /* access modifiers changed from: private */
    public volatile boolean zzc = false;

    public zzbi(FirebaseApp firebaseApp) {
        Context applicationContext = firebaseApp.getApplicationContext();
        zzam zzam = new zzam(firebaseApp);
        this.zzb = zzam;
        BackgroundDetector.initialize((Application) applicationContext.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzbh(this));
    }

    /* access modifiers changed from: private */
    public final boolean zzg() {
        return this.zza > 0 && !this.zzc;
    }

    public final void zzc() {
        this.zzb.zzb();
    }

    public final void zze(zzzy zzzy) {
        if (zzzy != null) {
            long zzb2 = zzzy.zzb();
            if (zzb2 <= 0) {
                zzb2 = 3600;
            }
            long zzc2 = zzzy.zzc();
            zzam zzam = this.zzb;
            zzam.zza = zzc2 + (zzb2 * 1000);
            zzam.zzb = -1;
            if (zzg()) {
                this.zzb.zzc();
            }
        }
    }

    public final void zzd(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzg()) {
                this.zzb.zzc();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzb();
        }
        this.zza = i;
    }
}
