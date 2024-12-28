package com.razorpay;

import android.content.Context;
import com.razorpay.AdvertisingIdUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseUtils */
final class E$_6$ implements AdvertisingIdUtil.AdvertisingIdCallback {
    private /* synthetic */ JSONObject a;
    private /* synthetic */ Context b;
    private /* synthetic */ RzpJSONCallback c;

    E$_6$(JSONObject jSONObject, Context context, RzpJSONCallback rzpJSONCallback) {
        this.a = jSONObject;
        this.b = context;
        this.c = rzpJSONCallback;
    }

    public final void onResult(String str) {
        try {
            this.a.put("advertising_id", str);
            this.a.put("is_roming", BaseUtils.isNetworkRoaming(this.b));
            this.a.put("carrier_network", BaseUtils.getCarrierOperatorName(this.b));
            this.a.put("carrier_id", "null");
            Map<String, String> deviceAttributes = BaseUtils.getDeviceAttributes(this.b);
            this.a.put("device_Id", deviceAttributes.get("device_Id"));
            this.a.put("device_manufacturer", deviceAttributes.get("device_manufacturer"));
            this.a.put("device_model", deviceAttributes.get("device_model"));
            this.a.put("serial_number", BaseUtils.buildSerial());
            this.a.put("ip_address", BaseUtils.ipAddress);
            this.a.put("wifi_ssid", BaseUtils.getWifiSSID(this.b));
            this.a.put("android_id", BaseUtils.getAndroidId(this.b));
            this.a.put("safety_net basic_integrity", "true");
            this.a.put("safety_net_cts_profile_match", "null");
            this.c.onResponse(this.a);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
        }
    }
}
