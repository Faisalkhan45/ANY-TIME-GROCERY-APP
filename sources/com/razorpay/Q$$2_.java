package com.razorpay;

import com.razorpay.CheckoutUtils;
import java.util.Map;

/* compiled from: CheckoutPresenterImpl */
final class Q$$2_ implements CheckoutUtils.BackButtonDialogCallback {
    private /* synthetic */ Map a;
    private /* synthetic */ CheckoutPresenterImpl b;

    Q$$2_(CheckoutPresenterImpl checkoutPresenterImpl, Map map) {
        this.b = checkoutPresenterImpl;
        this.a = map;
    }

    public final void onPositiveButtonClick() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.ALERT_PAYMENT_CONTINUE, AnalyticsUtil.getJSONResponse((Map<String, Object>) this.a));
    }

    public final void onNegativeButtonClick() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.ALERT_PAYMENT_CANCELLED, AnalyticsUtil.getJSONResponse((Map<String, Object>) this.a));
        if (this.b.isTwoWebViewFlow) {
            this.b.view.makeWebViewVisible(1);
            this.b.view.loadUrl(2, "about:blank");
            this.b.view.loadUrl(1, "javascript: window.onpaymentcancel()");
        } else {
            this.b.handleRetry((String) null);
            this.b.markPaymentCancelled();
        }
        boolean unused = this.b.isTwoWebViewFlow = false;
    }
}
