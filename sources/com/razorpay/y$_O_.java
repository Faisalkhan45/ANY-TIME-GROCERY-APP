package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class y$_O_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ CheckoutBridge a;

    y$_O_(CheckoutBridge checkoutBridge) {
        this.a = checkoutBridge;
    }

    public final void secure() {
        this.a.interactor.onCheckoutBackPress();
    }

    public final void unSecure() {
    }
}
