package com.razorpay;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PluginOtpElfCheckoutPresenterImpl */
final class g$$C_ implements RzpInternalCallback {
    private /* synthetic */ PluginOtpElfCheckoutPresenterImpl a;

    g$$C_(PluginOtpElfCheckoutPresenterImpl pluginOtpElfCheckoutPresenterImpl) {
        this.a = pluginOtpElfCheckoutPresenterImpl;
    }

    public final void onPaymentSuccess(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("provider") || !jSONObject.getString("provider").equals("GOOGLE_PAY")) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_INTERNAL_CALLBACK_SUCCESS, AnalyticsUtil.getJSONResponse(str));
                this.a.onComplete(str);
                return;
            }
            this.a.verifyGPayResponse(str);
        } catch (JSONException e) {
            e.printStackTrace();
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_INTERNAL_CALLBACK_ERROR);
        }
    }

    public final void onPaymentError(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("response", str);
        hashMap.put("code", Integer.valueOf(i));
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_INTERNAL_CALLBACK_ERROR, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("provider") || !jSONObject.getString("provider").equals("GOOGLE_PAY")) {
                this.a.onComplete(jSONObject.toString());
            } else {
                this.a.verifyGPayResponse(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_INTERNAL_CALLBACK_ERROR_EXCEPTION);
            this.a.onComplete(str);
        }
    }
}
