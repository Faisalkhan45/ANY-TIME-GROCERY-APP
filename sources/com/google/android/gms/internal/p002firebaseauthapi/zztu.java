package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zze;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zztu implements zzyg {
    final /* synthetic */ zzyf zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ Boolean zzd;
    final /* synthetic */ zze zze;
    final /* synthetic */ zzxa zzf;
    final /* synthetic */ zzzy zzg;

    zztu(zzvf zzvf, zzyf zzyf, String str, String str2, Boolean bool, zze zze2, zzxa zzxa, zzzy zzzy) {
        this.zza = zzyf;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zze2;
        this.zzf = zzxa;
        this.zzg = zzzy;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List list;
        List zzb2 = ((zzzp) obj).zzb();
        if (zzb2 == null || zzb2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        int i = 0;
        zzzr zzzr = (zzzr) zzb2.get(0);
        zzaag zzl = zzzr.zzl();
        if (zzl != null) {
            list = zzl.zzc();
        } else {
            list = null;
        }
        if (list != null && !list.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                while (true) {
                    if (i >= list.size()) {
                        break;
                    } else if (((zzaae) list.get(i)).zzf().equals(this.zzb)) {
                        ((zzaae) list.get(i)).zzh(this.zzc);
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                ((zzaae) list.get(0)).zzh(this.zzc);
            }
        }
        zzzr.zzh(this.zzd.booleanValue());
        zzzr.zze(this.zze);
        this.zzf.zzi(this.zzg, zzzr);
    }
}
