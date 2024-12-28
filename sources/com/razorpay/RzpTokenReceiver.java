package com.razorpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RzpTokenReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String string;
        String b = f$_G$.b(context);
        if (!TextUtils.isEmpty(b)) {
            Bundle resultExtras = getResultExtras(true);
            JSONArray jSONArray = new JSONArray();
            if (!(resultExtras == null || (string = resultExtras.getString("device_token_info_list")) == null)) {
                try {
                    jSONArray = new JSONArray(string);
                } catch (Exception e) {
                }
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("rzp_device_token", b);
                jSONObject.put("card_saving_token_source", context.getPackageName());
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
            }
            resultExtras.putString("device_token_info_list", jSONArray.toString());
        }
    }
}
