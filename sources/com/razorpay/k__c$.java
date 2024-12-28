package com.razorpay;

import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.ImagesContract;
import com.razorpay.AnalyticsProperty;
import org.json.JSONObject;

/* compiled from: CheckoutPresenterImpl */
final class k__c$ implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ CheckoutPresenterImpl b;

    k__c$(CheckoutPresenterImpl checkoutPresenterImpl, String str) {
        this.b = checkoutPresenterImpl;
        this.a = str;
    }

    public final void run() {
        try {
            JSONObject jSONObject = new JSONObject(this.a);
            this.b.enableAddon(jSONObject);
            if (jSONObject.has("content")) {
                this.b.view.loadDataWithBaseURL(2, "about:blank", jSONObject.getString("content"), "text/html", Key.STRING_CHARSET_NAME, (String) null);
            }
            if (jSONObject.has(ImagesContract.URL)) {
                this.b.view.loadUrl(2, jSONObject.getString(ImagesContract.URL));
            }
            if (!jSONObject.has("focus") || jSONObject.getBoolean("focus")) {
                this.b.view.makeWebViewVisible(2);
            } else {
                this.b.view.makeWebViewVisible(1);
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            e.printStackTrace();
        }
        AnalyticsUtil.addProperty("two_webview_flow", new AnalyticsProperty(true, AnalyticsProperty.Scope.PAYMENT));
    }
}
