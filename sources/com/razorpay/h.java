package com.razorpay;

import android.app.Activity;
import org.json.JSONObject;

/* compiled from: RzpGpayMerged */
final class h implements Callback {
    private /* synthetic */ Activity a;
    private /* synthetic */ JSONObject b;
    private /* synthetic */ RzpGpayMerged c;

    h(RzpGpayMerged rzpGpayMerged, Activity activity, JSONObject jSONObject) {
        this.c = rzpGpayMerged;
        this.a = activity;
        this.b = jSONObject;
    }

    public final void run(ResponseObject responseObject) {
        try {
            boolean unused = this.c.isUpiOnly = true;
            JSONObject unused2 = this.c.apiResponse = new JSONObject(responseObject.getResponseResult());
            JSONObject jSONObject = new JSONObject(responseObject.getResponseResult());
            if (responseObject.getResponseCode() >= 400) {
                this.c.genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An error occurred while fetching payment details from API.");
            }
            if (jSONObject.has("error")) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PROCESS_PAYMENT_PAYLOAD_ERROR);
                this.c.genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, jSONObject.toString());
                return;
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PROCESS_PAYMENT_CALLED);
            this.c.mPaymentClient.loadPaymentData(this.a, RzpGpayUtilMerged.getPaymentRequestData(responseObject.getResponseResult(), this.b), RzpGpayMerged.LOAD_PAYMENT_DATA_REQUEST_CODE);
        } catch (Exception e) {
            AnalyticsUtil.reportError("RzpGpayMerged", "S0", e.getMessage());
            this.c.genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An internal error has occurred.");
        }
    }
}
