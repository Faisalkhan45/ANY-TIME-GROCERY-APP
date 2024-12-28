package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class J$$A_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ CheckoutBridge a;

    J$$A_(CheckoutBridge checkoutBridge) {
        this.a = checkoutBridge;
    }

    public final void secure() {
        this.a.interactor.onDismiss();
    }

    public final void unSecure() {
    }
}
