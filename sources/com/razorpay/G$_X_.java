package com.razorpay;

import org.json.JSONObject;

/* compiled from: CheckoutPresenterImpl */
final class G$_X_ implements Runnable {
    private /* synthetic */ JSONObject a;
    private /* synthetic */ CheckoutPresenterImpl b;

    G$_X_(CheckoutPresenterImpl checkoutPresenterImpl, JSONObject jSONObject) {
        this.b = checkoutPresenterImpl;
        this.a = jSONObject;
    }

    public final void run() {
        this.b.handleRetry(this.a.toString());
    }
}
