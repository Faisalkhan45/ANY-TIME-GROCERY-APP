package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class U$_z$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutBridge b;

    U$_z$(CheckoutBridge checkoutBridge, String str) {
        this.b = checkoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.interactor.setAppToken(this.a);
    }

    public final void unSecure() {
    }
}
