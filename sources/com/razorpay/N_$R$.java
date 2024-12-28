package com.razorpay;

import org.json.JSONObject;

/* compiled from: CheckoutPresenterImpl */
final class N_$R$ implements Runnable {
    private /* synthetic */ boolean a;
    private /* synthetic */ CheckoutPresenterImpl b;

    N_$R$(CheckoutPresenterImpl checkoutPresenterImpl, boolean z) {
        this.b = checkoutPresenterImpl;
        this.a = z;
    }

    public final void run() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("granted", this.a);
            this.b.view.loadUrl(1, String.format("javascript: otpPermissionCallback(%s)", new Object[]{jSONObject.toString()}));
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getLocalizedMessage());
        }
    }
}
