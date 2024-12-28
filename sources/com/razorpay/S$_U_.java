package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class S$_U_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ CheckoutBridge c;

    S$_U_(CheckoutBridge checkoutBridge, String str, String str2) {
        this.c = checkoutBridge;
        this.a = str;
        this.b = str2;
    }

    public final void secure() {
        this.c.interactor.callNativeIntent(this.a, this.b);
    }

    public final void unSecure() {
    }
}
