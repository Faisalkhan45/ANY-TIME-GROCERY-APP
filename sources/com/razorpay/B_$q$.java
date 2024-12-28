package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class B_$q$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ int a;
    private /* synthetic */ int b;
    private /* synthetic */ CheckoutBridge c;

    B_$q$(CheckoutBridge checkoutBridge, int i, int i2) {
        this.c = checkoutBridge;
        this.a = i;
        this.b = i2;
    }

    public final void secure() {
        this.c.interactor.setDimensions(this.a, this.b);
    }

    public final void unSecure() {
    }
}
