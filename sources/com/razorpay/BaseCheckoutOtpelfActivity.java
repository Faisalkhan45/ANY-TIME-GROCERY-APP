package com.razorpay;

import android.os.Bundle;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

abstract class BaseCheckoutOtpelfActivity extends b__J_ {
    BaseCheckoutOtpelfActivity() {
    }

    public void onCreate(Bundle bundle) {
        HashMap<String, String> allPluginsFromManifest = BaseUtils.getAllPluginsFromManifest(this);
        if (allPluginsFromManifest == null || allPluginsFromManifest.size() == 0) {
            this.presenter = new Z$_A_(this, this);
            this.checkoutBridgeObject = new CheckoutBridge((CheckoutInteractor) this.presenter, 1);
            super.onCreate(bundle);
            return;
        }
        this.presenter = new PluginOtpElfCheckoutPresenterImpl(this, this, allPluginsFromManifest);
        this.checkoutBridgeObject = new PluginCheckoutBridge((PluginCheckoutInteractor) this.presenter, 1);
        super.onCreate(bundle);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isAmazonPluginIntegrated", false);
            jSONObject.put("isGooglePayPluginIntegrated", false);
        } catch (JSONException e) {
        }
        for (String next : allPluginsFromManifest.values()) {
            try {
                if (allPluginsFromManifest.size() > 0 && next.equalsIgnoreCase("com.razorpay.RazorpayAmazon")) {
                    jSONObject.put("isAmazonPluginIntegrated", true);
                }
                if (allPluginsFromManifest.size() > 0 && next.equalsIgnoreCase("com.razorpay.RzpGpayMerged")) {
                    jSONObject.put("isGooglePayPluginIntegrated", true);
                }
                RzpPlugin rzpPlugin = (RzpPlugin) RzpPlugin.class.getClassLoader().loadClass(next).newInstance();
                RzpPluginCompatibilityResponse isCompatible = rzpPlugin.isCompatible(g$_H$.a, g$_H$.c, g$_H$.b);
                if (!isCompatible.isCompatible()) {
                    destroy(7, isCompatible.getErrorMessage());
                    return;
                }
                rzpPlugin.isRegistered(this, new B$$W$(this));
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
