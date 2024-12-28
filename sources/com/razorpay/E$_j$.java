package com.razorpay;

import org.json.JSONObject;

/* compiled from: AutoOtpUtils */
final class E$_j$ {
    static JSONObject a(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("otp_read", z ? 1 : 0);
            return jSONObject;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S1", e.getMessage());
            return null;
        }
    }
}
