package com.razorpay;

import android.app.Activity;
import com.razorpay.OpinionatedSoln;
import org.json.JSONObject;

/* compiled from: Checkout */
final class g__v_ implements OpinionatedSoln.DismissCallback {
    private /* synthetic */ Activity a;
    private /* synthetic */ JSONObject b;
    private /* synthetic */ Checkout c;

    g__v_(Checkout checkout, Activity activity, JSONObject jSONObject) {
        this.c = checkout;
        this.a = activity;
        this.b = jSONObject;
    }

    public final void alertDismissed() {
        this.c.openInternal(this.a, this.b);
    }
}
