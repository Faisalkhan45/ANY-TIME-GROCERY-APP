package com.razorpay;

import android.webkit.JavascriptInterface;
import java.util.HashMap;
import java.util.Map;

public class PluginCheckoutBridge extends CheckoutBridge {
    /* access modifiers changed from: private */
    public final PluginCheckoutInteractor pluginCheckoutInteractor;

    @JavascriptInterface
    public /* bridge */ /* synthetic */ void invokePopup(String str) {
        super.invokePopup(str);
    }

    @JavascriptInterface
    public /* bridge */ /* synthetic */ void onCheckoutBackPress() {
        super.onCheckoutBackPress();
    }

    PluginCheckoutBridge(PluginCheckoutInteractor pluginCheckoutInteractor2, int i) {
        super(pluginCheckoutInteractor2, i);
        this.pluginCheckoutInteractor = pluginCheckoutInteractor2;
    }

    @JavascriptInterface
    public void processPayment(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", str);
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_PROCESS_PAYMENT_CALLED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
        super.isWebViewSafeOnUI(new i_$z_(this, str));
    }
}
