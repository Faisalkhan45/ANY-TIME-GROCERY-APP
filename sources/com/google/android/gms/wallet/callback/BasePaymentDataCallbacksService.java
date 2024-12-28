package com.google.android.gms.wallet.callback;

import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.wallet.PaymentData;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public abstract class BasePaymentDataCallbacksService extends zzd {
    /* access modifiers changed from: protected */
    public abstract BasePaymentDataCallbacks createPaymentDataCallbacks();

    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public void onCreate() {
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public final void onRunTask(String str, CallbackInput callbackInput, OnCompleteListener<CallbackOutput> onCompleteListener) {
        if (callbackInput.getCallbackType() != 0) {
            BasePaymentDataCallbacks createPaymentDataCallbacks = createPaymentDataCallbacks();
            if (callbackInput.getCallbackType() == 1) {
                createPaymentDataCallbacks.onPaymentAuthorized((PaymentData) callbackInput.deserializeRequest(PaymentData.CREATOR), new zze(onCompleteListener));
            } else if (callbackInput.getCallbackType() == 2) {
                createPaymentDataCallbacks.onPaymentDataChanged((IntermediatePaymentData) callbackInput.deserializeRequest(IntermediatePaymentData.CREATOR), new zzf(onCompleteListener));
            } else {
                throw new IllegalStateException("Unknown Callback Types");
            }
        } else {
            throw new IllegalStateException("Callback Types must be set");
        }
    }
}
