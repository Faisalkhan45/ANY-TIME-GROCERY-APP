package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class Y_$B$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    Y_$B$(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        CheckoutBridge checkoutBridge = this.b;
        boolean unused = checkoutBridge.isRegistered = checkoutBridge.interactor.isUserRegistered(this.a);
    }

    public final void unSecure() {
    }
}
