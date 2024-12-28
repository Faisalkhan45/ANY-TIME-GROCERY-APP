package com.google.android.gms.wallet.callback;

import com.google.android.gms.wallet.PaymentData;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public abstract class BasePaymentDataCallbacks {
    /* access modifiers changed from: protected */
    public void onPaymentAuthorized(PaymentData paymentData, OnCompleteListener<PaymentAuthorizationResult> onCompleteListener) {
    }

    /* access modifiers changed from: protected */
    public void onPaymentDataChanged(IntermediatePaymentData intermediatePaymentData, OnCompleteListener<PaymentDataRequestUpdate> onCompleteListener) {
    }
}
