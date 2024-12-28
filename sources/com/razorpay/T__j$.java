package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class T__j$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    T__j$(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.interactor.onDismiss(this.a);
    }

    public final void unSecure() {
    }
}
