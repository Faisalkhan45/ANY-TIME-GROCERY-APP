package com.razorpay;

import android.webkit.JavascriptInterface;

/* compiled from: MagicBridge */
public final class P$_S_ {
    private CheckoutInteractor a;

    P$_S_(CheckoutInteractor checkoutInteractor) {
        this.a = checkoutInteractor;
    }

    @JavascriptInterface
    public final void relay(String str) {
        this.a.sendDataToWebView(1, str);
    }
}
