package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzyt extends BroadcastReceiver {
    final /* synthetic */ zzyv zza;
    private final String zzb;

    public zzyt(zzyv zzyv, String str) {
        this.zza = zzyv;
        this.zzb = str;
    }

    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            switch (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode()) {
                case 0:
                    String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    zzyu zzyu = (zzyu) this.zza.zzd.get(this.zzb);
                    if (zzyu != null) {
                        zzyu.zze = zzyv.zzb(str);
                        if (zzyu.zze != null) {
                            if (!zzag.zzd(zzyu.zzd)) {
                                zzyv.zze(this.zza, this.zzb);
                                break;
                            }
                        } else {
                            zzyv.zza.e("Unable to extract verification code.", new Object[0]);
                            break;
                        }
                    } else {
                        zzyv.zza.e("Verification code received with no active retrieval session.", new Object[0]);
                        break;
                    }
                    break;
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
