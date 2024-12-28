package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class d__w$ implements Runnable {
    private /* synthetic */ CheckoutPresenterImpl a;

    d__w$(CheckoutPresenterImpl checkoutPresenterImpl) {
        this.a = checkoutPresenterImpl;
    }

    public final void run() {
        this.a.view.loadUrl(1, this.a.getHandleMessageFormattedString());
        this.a.view.loadUrl(1, String.format("javascript: CheckoutBridge.sendAnalyticsData({data: %s})", new Object[]{AnalyticsUtil.getAnalyticsDataForCheckout(this.a.activity).toString()}));
    }
}
