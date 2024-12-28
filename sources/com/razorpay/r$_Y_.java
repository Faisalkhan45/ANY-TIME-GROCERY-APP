package com.razorpay;

import android.content.Context;
import java.util.List;
import org.json.JSONObject;

/* compiled from: BaseConfig */
final class r$_Y_ implements Callback {
    private /* synthetic */ Context a;

    r$_Y_(Context context) {
        this.a = context;
    }

    public final void run(ResponseObject responseObject) {
        String str;
        try {
            if (responseObject.getResponseCode() == 200) {
                BaseConfig.saveConfigDataToPreferences(this.a, new JSONObject(responseObject.getResponseResult()).toString());
                List list = responseObject.getHeaders().get("Settingversion");
                if (list != null && list.size() > 0 && (str = (String) list.get(0)) != null && !str.isEmpty()) {
                    BaseConfig.setConfigVersionToPreferences(this.a, str);
                }
            }
        } catch (Exception e) {
        }
    }
}
