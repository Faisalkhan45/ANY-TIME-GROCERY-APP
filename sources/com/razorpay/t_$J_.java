package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class t_$J_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ CheckoutBridge c;

    t_$J_(CheckoutBridge checkoutBridge, String str, String str2) {
        this.c = checkoutBridge;
        this.a = str;
        this.b = str2;
    }

    public final void secure() {
        this.c.interactor.getPdfString(this.a, this.b);
    }

    public final void unSecure() {
    }
}
