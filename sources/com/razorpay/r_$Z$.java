package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class r_$Z$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    r_$Z$(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        CheckoutBridge checkoutBridge = this.b;
        boolean unused = checkoutBridge.isRegistered = checkoutBridge.interactor.isUserRegisteredOnUPI(this.a);
    }

    public final void unSecure() {
    }
}
