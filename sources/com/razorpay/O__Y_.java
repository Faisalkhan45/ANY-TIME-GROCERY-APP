package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class O__Y_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    O__Y_(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.interactor.onSubmit(this.a);
    }

    public final void unSecure() {
    }
}
