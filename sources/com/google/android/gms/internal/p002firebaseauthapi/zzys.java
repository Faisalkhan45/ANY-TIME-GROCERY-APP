package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzys  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzys extends zzxa {
    final /* synthetic */ zzyv zza;
    private final String zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzys(zzyv zzyv, zzxa zzxa, String str) {
        super(zzxa);
        this.zza = zzyv;
        this.zzb = str;
    }

    public final void zzb(String str) {
        zzyv.zza.d("onCodeSent", new Object[0]);
        zzyu zzyu = (zzyu) this.zza.zzd.get(this.zzb);
        if (zzyu != null) {
            for (zzxa zzb2 : zzyu.zzb) {
                zzb2.zzb(str);
            }
            zzyu.zzg = true;
            zzyu.zzd = str;
            if (zzyu.zza <= 0) {
                this.zza.zzh(this.zzb);
            } else if (!zzyu.zzc) {
                this.zza.zzn(this.zzb);
            } else if (!zzag.zzd(zzyu.zze)) {
                zzyv.zze(this.zza, this.zzb);
            }
        }
    }

    public final void zzh(Status status) {
        Logger zza2 = zzyv.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        String statusMessage = status.getStatusMessage();
        zza2.e("SMS verification code request failed: " + statusCodeString + " " + statusMessage, new Object[0]);
        zzyu zzyu = (zzyu) this.zza.zzd.get(this.zzb);
        if (zzyu != null) {
            for (zzxa zzh : zzyu.zzb) {
                zzh.zzh(status);
            }
            this.zza.zzj(this.zzb);
        }
    }
}
