package com.google.android.gms.wallet.callback;

import android.os.Messenger;
import android.util.Log;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzc implements Runnable {
    final /* synthetic */ zzd zza;
    private final CallbackInput zzb;
    private final String zzc;
    private final zzb zzd;

    zzc(zzd zzd2, CallbackInput callbackInput, Messenger messenger, String str, int i) {
        this.zza = zzd2;
        this.zzb = callbackInput;
        this.zzc = str;
        this.zzd = new zzb(messenger, i);
    }

    public final void run() {
        if (Log.isLoggable("BaseCallbackTaskService", 4)) {
            Log.i("BaseCallbackTaskService", String.format(Locale.US, "Running Callback Task w/ tag %s", new Object[]{this.zzc}));
        }
        try {
            this.zza.onRunTask(this.zzc, this.zzb, this.zzd);
        } catch (Throwable th) {
            zzb zzb2 = this.zzd;
            zzj zza2 = CallbackOutput.zza();
            int i = this.zzb.zza;
            CallbackOutput callbackOutput = zza2.zza;
            callbackOutput.zza = i;
            callbackOutput.zzb = 5;
            String message = th.getMessage();
            CallbackOutput callbackOutput2 = zza2.zza;
            callbackOutput2.zzd = message;
            zzb2.complete(callbackOutput2);
            throw th;
        }
    }
}
