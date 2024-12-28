package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class I$_e_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    I$_e_(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.interactor.onError(this.a);
    }

    public final void unSecure() {
    }
}
