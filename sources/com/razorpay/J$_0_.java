package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class J$_0_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ CheckoutBridge a;

    J$_0_(CheckoutBridge checkoutBridge) {
        this.a = checkoutBridge;
    }

    public final void secure() {
        this.a.interactor.onLoad();
    }

    public final void unSecure() {
    }
}
