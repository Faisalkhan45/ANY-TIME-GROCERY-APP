package com.google.android.gms.wallet.callback;

import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzf implements OnCompleteListener<PaymentDataRequestUpdate> {
    private final OnCompleteListener<CallbackOutput> zza;

    zzf(OnCompleteListener<CallbackOutput> onCompleteListener) {
        this.zza = onCompleteListener;
    }

    /* renamed from: zza */
    public final synchronized void complete(PaymentDataRequestUpdate paymentDataRequestUpdate) {
        OnCompleteListener<CallbackOutput> onCompleteListener = this.zza;
        zzj zza2 = CallbackOutput.zza();
        CallbackOutput callbackOutput = zza2.zza;
        callbackOutput.zza = 2;
        callbackOutput.zzb = 1;
        byte[] serializeToBytes = SafeParcelableSerializer.serializeToBytes(paymentDataRequestUpdate);
        CallbackOutput callbackOutput2 = zza2.zza;
        callbackOutput2.zzc = serializeToBytes;
        onCompleteListener.complete(callbackOutput2);
    }
}
