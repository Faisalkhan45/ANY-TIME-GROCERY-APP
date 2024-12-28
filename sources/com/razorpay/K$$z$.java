package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class K$$z$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    K$$z$(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.interactor.onComplete(this.a);
    }

    public final void unSecure() {
    }
}
