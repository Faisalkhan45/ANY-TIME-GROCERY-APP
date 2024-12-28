package com.razorpay;

import android.app.Activity;
import android.webkit.WebView;
import com.razorpay.CheckoutPresenterImpl;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OtpElfCheckoutPresenterImpl */
class Z$_A_ extends CheckoutPresenterImpl {
    private boolean isSecondaryRzpAssistEnabled = true;
    private RzpAssist primaryRzpAssist;
    private RzpAssist secondaryRzpAssist;

    public Z$_A_(Activity activity, CheckoutPresenterImpl.CheckoutView checkoutView) {
        super(activity, checkoutView);
    }

    public void setUpAddOn() {
        RzpAssist rzpAssist;
        RzpAssist rzpAssist2 = new RzpAssist(this.merchantKey, this.activity, this.view.getWebView(1), g$_H$.a, g$_H$.c, g$_H$.b);
        this.primaryRzpAssist = rzpAssist2;
        rzpAssist2.setRzpAssistEnabled(true);
        RzpAssist rzpAssist3 = new RzpAssist(this.merchantKey, this.activity, this.view.getWebView(2), g$_H$.a, g$_H$.c, g$_H$.b);
        this.secondaryRzpAssist = rzpAssist3;
        rzpAssist3.setRzpAssistEnabled(true);
        if (!(this.checkoutOptions.g() == null || (rzpAssist = this.primaryRzpAssist) == null)) {
            rzpAssist.setOtpElfPreferences(this.checkoutOptions.g());
        }
        super.setUpAddOn();
    }

    /* access modifiers changed from: protected */
    public void enableAddon(JSONObject jSONObject) {
        super.enableAddon(jSONObject);
        try {
            if (jSONObject.has("otpelf")) {
                boolean z = jSONObject.getBoolean("otpelf");
                this.isSecondaryRzpAssistEnabled = z;
                RzpAssist rzpAssist = this.secondaryRzpAssist;
                if (rzpAssist != null) {
                    rzpAssist.setRzpAssistEnabled(z);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onProgressChanges(int i, int i2) {
        switch (i) {
            case 1:
                RzpAssist rzpAssist = this.primaryRzpAssist;
                if (rzpAssist != null) {
                    rzpAssist.onProgressChanged(i2);
                    break;
                }
                break;
            case 2:
                RzpAssist rzpAssist2 = this.secondaryRzpAssist;
                if (rzpAssist2 != null && this.isSecondaryRzpAssistEnabled) {
                    rzpAssist2.onProgressChanged(i2);
                    break;
                }
        }
        super.onProgressChanges(i, i2);
    }

    public void onPageStarted(int i, WebView webView, String str) {
        super.onPageStarted(i, webView, str);
        switch (i) {
            case 1:
                RzpAssist rzpAssist = this.primaryRzpAssist;
                if (rzpAssist != null) {
                    rzpAssist.onPageStarted(webView, str);
                    return;
                }
                return;
            case 2:
                RzpAssist rzpAssist2 = this.secondaryRzpAssist;
                if (rzpAssist2 != null && this.isSecondaryRzpAssistEnabled) {
                    rzpAssist2.onPageStarted(webView, str);
                }
                if (this.view.isWebViewVisible(2)) {
                    CheckoutUtils.e(this.activity);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPageFinished(int i, WebView webView, String str) {
        super.onPageFinished(i, webView, str);
        switch (i) {
            case 2:
                RzpAssist rzpAssist = this.secondaryRzpAssist;
                if (rzpAssist != null && this.isSecondaryRzpAssistEnabled) {
                    rzpAssist.onPageFinished(webView, str);
                }
                if (this.view.isWebViewVisible(2)) {
                    CheckoutUtils.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void primaryWebviewPageFinished(String str, WebView webView) {
        super.primaryWebviewPageFinished(str, webView);
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            rzpAssist.onPageFinished(webView, str);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            rzpAssist.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    /* access modifiers changed from: protected */
    public void addOnFlowEnd() {
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            rzpAssist.paymentFlowEnd();
        }
        RzpAssist rzpAssist2 = this.secondaryRzpAssist;
        if (rzpAssist2 != null) {
            rzpAssist2.paymentFlowEnd();
        }
        super.addOnFlowEnd();
    }

    public void backPressed(Map<String, Object> map) {
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            map.put("current_loading_url_primary_webview", rzpAssist.getCurrentLoadingUrl());
            map.put("last_loaded_url_primary_webview", this.primaryRzpAssist.getLastLoadedUrl());
        }
        RzpAssist rzpAssist2 = this.secondaryRzpAssist;
        if (rzpAssist2 != null) {
            map.put("current_loading_url_secondary_webview", rzpAssist2.getCurrentLoadingUrl());
            map.put("last_loaded_url_secondary_webview", this.secondaryRzpAssist.getLastLoadedUrl());
        }
        super.backPressed(map);
    }

    public void setPaymentID(String str) {
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            rzpAssist.setPaymentId(str);
        }
        super.setPaymentID(str);
    }

    /* access modifiers changed from: protected */
    public void addAnalyticsData(JSONObject jSONObject) {
        try {
            RzpAssist rzpAssist = this.primaryRzpAssist;
            if (rzpAssist != null) {
                rzpAssist.setPaymentData(jSONObject);
                jSONObject.put("razorpay_otp", this.primaryRzpAssist.isRazorpayOtpReceived());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.addAnalyticsData(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void helpersReset() {
        super.helpersReset();
        RzpAssist rzpAssist = this.primaryRzpAssist;
        if (rzpAssist != null) {
            rzpAssist.reset();
        }
        RzpAssist rzpAssist2 = this.secondaryRzpAssist;
        if (rzpAssist2 != null && this.isSecondaryRzpAssistEnabled) {
            rzpAssist2.reset();
        }
    }
}
