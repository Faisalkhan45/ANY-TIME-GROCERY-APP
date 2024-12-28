package com.razorpay;

import org.json.JSONObject;

/* compiled from: CheckoutPresenterImpl */
final class K_$q$ implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutPresenterImpl b;

    K_$q$(CheckoutPresenterImpl checkoutPresenterImpl, String str) {
        this.b = checkoutPresenterImpl;
        this.a = str;
    }

    public final void run() {
        try {
            this.b.onComplete(new JSONObject(this.a));
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getMessage());
            this.b.destroyActivity(0, BaseUtils.getPaymentCancelledResponse(BaseUtils.getInstance().getMetadata()));
        }
    }
}
