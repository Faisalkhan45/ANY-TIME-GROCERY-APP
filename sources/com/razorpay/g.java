package com.razorpay;

import com.razorpay.AnalyticsProperty;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: RzpAssist */
final class g implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ RzpAssist b;

    g(RzpAssist rzpAssist, String str) {
        this.b = rzpAssist;
        this.a = str;
    }

    public final void run() {
        try {
            JSONObject jSONObject = new JSONObject(this.a);
            OTP otp = new OTP(jSONObject.getString("otp"), jSONObject.getString("sender"), jSONObject.getString("bank"));
            HashMap hashMap = new HashMap();
            hashMap.put("sender", otp.a);
            if (otp.a.contains("RZRPAY")) {
                boolean unused = this.b.isRazorpayOtpReceived = true;
                hashMap.put("razorpay_otp", Boolean.TRUE);
            } else {
                hashMap.put("razorpay_otp", Boolean.FALSE);
                boolean unused2 = this.b.otpRead = true;
                AnalyticsUtil.addProperty("payment_otp_received", new AnalyticsProperty(true, AnalyticsProperty.Scope.PAYMENT));
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.OTP_RECEIVED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
        } catch (Exception e) {
            d__1_.a("Error in parsing json", e);
        }
    }
}
