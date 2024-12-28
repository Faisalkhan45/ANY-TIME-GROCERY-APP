package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class C__D$ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ CheckoutBridge a;

    C__D$(CheckoutBridge checkoutBridge) {
        this.a = checkoutBridge;
    }

    public final void secure() {
        CheckoutBridge checkoutBridge = this.a;
        String unused = checkoutBridge.integratedPlugin = checkoutBridge.interactor.getSdkPlugins();
    }

    public final void unSecure() {
    }
}
