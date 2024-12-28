package com.razorpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.exifinterface.media.ExifInterface;
import com.razorpay.CheckoutBridge;
import com.razorpay.CheckoutPresenterImpl;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginOtpElfCheckoutPresenterImpl extends Z$_A_ implements PluginCheckoutInteractor {
    private RzpPlugin extActiveRzpPluginInstance;
    private boolean isExtRzpPluginActive = false;
    private HashMap<String, String> pluginsMap;
    private final RzpInternalCallback rzpInternalCallback = new g$$C_(this);

    public /* bridge */ /* synthetic */ void backPressed(Map map) {
        super.backPressed(map);
    }

    public /* bridge */ /* synthetic */ void callNativeIntent(String str, String str2) {
        super.callNativeIntent(str, str2);
    }

    public /* bridge */ /* synthetic */ void checkSmsPermission() {
        super.checkSmsPermission();
    }

    public /* bridge */ /* synthetic */ void cleanUpOnDestroy() {
        super.cleanUpOnDestroy();
    }

    public /* bridge */ /* synthetic */ void destroyActivity(int i, String str) {
        super.destroyActivity(i, str);
    }

    public /* bridge */ /* synthetic */ void fetchCondfig() {
        super.fetchCondfig();
    }

    public /* bridge */ /* synthetic */ O_$v$ getCheckoutOptions() {
        return super.getCheckoutOptions();
    }

    public /* bridge */ /* synthetic */ void getPdfString(String str, String str2) {
        super.getPdfString(str, str2);
    }

    public /* bridge */ /* synthetic */ String getProgressBarColor() {
        return super.getProgressBarColor();
    }

    public /* bridge */ /* synthetic */ String getSdkPlugins() {
        return super.getSdkPlugins();
    }

    public /* bridge */ /* synthetic */ void handleCardSaving() {
        super.handleCardSaving();
    }

    public /* bridge */ /* synthetic */ void invokePopup(String str) {
        super.invokePopup(str);
    }

    public /* bridge */ /* synthetic */ boolean isAllowRotation() {
        return super.isAllowRotation();
    }

    public /* bridge */ /* synthetic */ boolean isMagicPresent() {
        return super.isMagicPresent();
    }

    public /* bridge */ /* synthetic */ boolean isUserRegistered(String str) {
        return super.isUserRegistered(str);
    }

    public /* bridge */ /* synthetic */ boolean isUserRegisteredOnUPI(String str) {
        return super.isUserRegisteredOnUPI(str);
    }

    public /* bridge */ /* synthetic */ void isWebViewSafe(int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
        super.isWebViewSafe(i, webViewSafeCheckCallback);
    }

    public /* bridge */ /* synthetic */ void isWebViewSafeOnUI(int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
        super.isWebViewSafeOnUI(i, webViewSafeCheckCallback);
    }

    public /* bridge */ /* synthetic */ void loadForm(String str) {
        super.loadForm(str);
    }

    public /* bridge */ /* synthetic */ void onCheckoutBackPress() {
        super.onCheckoutBackPress();
    }

    public /* bridge */ /* synthetic */ void onCheckoutRendered() {
        super.onCheckoutRendered();
    }

    public /* bridge */ /* synthetic */ void onComplete(String str) {
        super.onComplete(str);
    }

    public /* bridge */ /* synthetic */ void onDismiss() {
        super.onDismiss();
    }

    public /* bridge */ /* synthetic */ void onDismiss(String str) {
        super.onDismiss(str);
    }

    public /* bridge */ /* synthetic */ void onError(String str) {
        super.onError(str);
    }

    public /* bridge */ /* synthetic */ void onFault(String str) {
        super.onFault(str);
    }

    public /* bridge */ /* synthetic */ void onLoad() {
        super.onLoad();
    }

    public /* bridge */ /* synthetic */ void onPageFinished(int i, WebView webView, String str) {
        super.onPageFinished(i, webView, str);
    }

    public /* bridge */ /* synthetic */ void onPageStarted(int i, WebView webView, String str) {
        super.onPageStarted(i, webView, str);
    }

    public /* bridge */ /* synthetic */ void onProgressChanges(int i, int i2) {
        super.onProgressChanges(i, i2);
    }

    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public /* bridge */ /* synthetic */ void onSubmit(String str) {
        super.onSubmit(str);
    }

    public /* bridge */ /* synthetic */ void passPrefillToSegment() {
        super.passPrefillToSegment();
    }

    public /* bridge */ /* synthetic */ void relay(String str) {
        super.relay(str);
    }

    public /* bridge */ /* synthetic */ void requestExtraAnalyticsData() {
        super.requestExtraAnalyticsData();
    }

    public /* bridge */ /* synthetic */ void requestOtpPermission() {
        super.requestOtpPermission();
    }

    public /* bridge */ /* synthetic */ void saveInstanceState(Bundle bundle) {
        super.saveInstanceState(bundle);
    }

    public /* bridge */ /* synthetic */ void sendDataToWebView(int i, String str) {
        super.sendDataToWebView(i, str);
    }

    public /* bridge */ /* synthetic */ void sendOtpPermissionCallback(boolean z) {
        super.sendOtpPermissionCallback(z);
    }

    public /* bridge */ /* synthetic */ void setAppToken(String str) {
        super.setAppToken(str);
    }

    public /* bridge */ /* synthetic */ void setCheckoutLoadStartAt() {
        super.setCheckoutLoadStartAt();
    }

    public /* bridge */ /* synthetic */ void setDeviceToken(String str) {
        super.setDeviceToken(str);
    }

    public /* bridge */ /* synthetic */ void setDimensions(int i, int i2) {
        super.setDimensions(i, i2);
    }

    public /* bridge */ /* synthetic */ void setMerchantOptions(String str) {
        super.setMerchantOptions(str);
    }

    public /* bridge */ /* synthetic */ boolean setOptions(Bundle bundle, boolean z) {
        return super.setOptions(bundle, z);
    }

    public /* bridge */ /* synthetic */ void setPaymentID(String str) {
        super.setPaymentID(str);
    }

    public /* bridge */ /* synthetic */ void setUpAddOn() {
        super.setUpAddOn();
    }

    public /* bridge */ /* synthetic */ void showAlertDialog(String str, String str2, String str3) {
        super.showAlertDialog(str, str2, str3);
    }

    public /* bridge */ /* synthetic */ void toast(String str, int i) {
        super.toast(str, i);
    }

    public /* bridge */ /* synthetic */ void unregisterReceivers() {
        super.unregisterReceivers();
    }

    public /* bridge */ /* synthetic */ void verifyGPaySdkResponse(String str) {
        super.verifyGPaySdkResponse(str);
    }

    public PluginOtpElfCheckoutPresenterImpl(Activity activity, CheckoutPresenterImpl.CheckoutView checkoutView, HashMap<String, String> hashMap) {
        super(activity, checkoutView);
        this.pluginsMap = hashMap;
    }

    public void processPayment(String str) {
        HashMap<String, String> hashMap = this.pluginsMap;
        if (hashMap != null && hashMap.size() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("data", str);
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_CALLING_PROCESS_PAYMENT, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap2));
                if (this.pluginsMap.containsKey("com.razorpay.plugin.googlepay_all") && this.pluginsMap.containsValue("com.razorpay.plugin.googlepay")) {
                    this.pluginsMap.remove("com.razorpay.plugin.googlepay");
                }
                for (String loadClass : this.pluginsMap.values()) {
                    try {
                        RzpPlugin rzpPlugin = (RzpPlugin) RzpPlugin.class.getClassLoader().loadClass(loadClass).newInstance();
                        if (rzpPlugin.doesHandlePayload(this.merchantKey, jSONObject, this.activity)) {
                            this.isExtRzpPluginActive = true;
                            this.extActiveRzpPluginInstance = rzpPlugin;
                            rzpPlugin.processPayment(this.merchantKey, jSONObject, this.activity, this.rzpInternalCallback);
                            return;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        AnalyticsUtil.reportError(getClass().getName(), "S0", e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                HashMap hashMap3 = new HashMap();
                hashMap3.put("data", str);
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_CALLING_PROCESS_PAYMENT_EXCEPTION, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap3));
                e2.printStackTrace();
            }
        }
    }

    public void verifyGPayResponse(String str) {
        super.verifyGPaySdkResponse(str);
    }

    /* access modifiers changed from: protected */
    public JSONObject getOptionsForHandleMessage() {
        JSONObject optionsForHandleMessage = super.getOptionsForHandleMessage();
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = false;
            boolean z2 = false;
            for (String next : this.pluginsMap.keySet()) {
                int length = next.length();
                if (next.substring(20, length).equalsIgnoreCase("googlepay_all")) {
                    try {
                        if (Class.forName("com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient").newInstance() != null) {
                            jSONObject.put("googlepay", true);
                            z2 = true;
                        }
                    } catch (ClassNotFoundException e) {
                        AnalyticsUtil.reportError(getClass().getName(), "S2", "GooglePay SDK is not included");
                    }
                }
                if (next.substring(20, length).equalsIgnoreCase("googlepay")) {
                    jSONObject.put(next.substring(20, length), true);
                    z = true;
                }
            }
            if (z && z2) {
                optionsForHandleMessage.put("googlepay_wrapper_version", "both");
            } else if (z2) {
                optionsForHandleMessage.put("googlepay_wrapper_version", ExifInterface.GPS_MEASUREMENT_2D);
            }
            optionsForHandleMessage.put("external_sdks", jSONObject);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e2.getLocalizedMessage());
            e2.printStackTrace();
        }
        return optionsForHandleMessage;
    }

    /* access modifiers changed from: protected */
    public void onError(JSONObject jSONObject) {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PLUGIN_ON_ERROR_CALLED, jSONObject);
        if (this.isExtRzpPluginActive) {
            this.view.loadUrl(1, String.format("javascript: window.onComplete(%s)", new Object[]{jSONObject.toString()}));
            this.isExtRzpPluginActive = false;
            return;
        }
        super.onError(jSONObject);
    }

    public void onActivityResultReceived(int i, int i2, Intent intent) {
        if (this.isExtRzpPluginActive) {
            this.extActiveRzpPluginInstance.onActivityResult(this.merchantKey, i, i2, intent);
        } else {
            super.onActivityResultReceived(i, i2, intent);
        }
    }
}
