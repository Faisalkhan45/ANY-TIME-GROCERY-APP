package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: PluginCheckoutBridge */
final class i_$z_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ PluginCheckoutBridge b;

    i_$z_(PluginCheckoutBridge pluginCheckoutBridge, String str) {
        this.b = pluginCheckoutBridge;
        this.a = str;
    }

    public final void secure() {
        this.b.pluginCheckoutInteractor.processPayment(this.a);
    }

    public final void unSecure() {
    }
}
