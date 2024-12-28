package com.razorpay;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseUtils */
final class c__C_ implements RzpJSONCallback {
    c__C_() {
    }

    public final void onResponse(JSONObject jSONObject) {
        try {
            if (jSONObject.getString("ip") != null) {
                BaseUtils.ipAddress = jSONObject.getString("ip");
            }
        } catch (JSONException e) {
        }
    }
}
