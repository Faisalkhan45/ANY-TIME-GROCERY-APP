package com.razorpay;

import org.json.JSONObject;

/* compiled from: CheckoutPresenterImpl */
final class J__A$ implements Runnable {
    private /* synthetic */ JSONObject a;
    private /* synthetic */ CheckoutPresenterImpl b;

    J__A$(CheckoutPresenterImpl checkoutPresenterImpl, JSONObject jSONObject) {
        this.b = checkoutPresenterImpl;
        this.a = jSONObject;
    }

    public final void run() {
        try {
            this.b.view.loadUrl(1, String.format("javascript: CheckoutBridge.sendExtraAnalyticsData(%s)", new Object[]{this.a.toString()}));
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getLocalizedMessage());
        }
    }
}
