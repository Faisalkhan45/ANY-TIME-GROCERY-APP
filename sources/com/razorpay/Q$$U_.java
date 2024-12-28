package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class Q$$U_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ CheckoutBridge a;

    Q$$U_(CheckoutBridge checkoutBridge) {
        this.a = checkoutBridge;
    }

    public final void secure() {
        this.a.interactor.requestExtraAnalyticsData();
    }

    public final void unSecure() {
    }
}
