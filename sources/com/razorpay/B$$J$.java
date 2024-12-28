package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class B$$J$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ int b;
    private /* synthetic */ CheckoutBridge c;

    B$$J$(CheckoutBridge checkoutBridge, String str, int i) {
        this.c = checkoutBridge;
        this.a = str;
        this.b = i;
    }

    public final void secure() {
        this.c.interactor.toast(this.a, this.b);
    }

    public final void unSecure() {
    }
}
