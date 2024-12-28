package com.razorpay;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.common.net.HttpHeaders;
import com.razorpay.AnalyticsProperty;
import java.util.HashMap;
import org.json.JSONObject;

public final class RzpAssist implements SmsAgentInterface {
    /* access modifiers changed from: private */
    public Activity activity;
    private String currentLoadingUrl = "";
    private OtpElfData elfData;
    private boolean hasOtpPermission = false;
    private boolean isMagic = false;
    /* access modifiers changed from: private */
    public boolean isRazorpayOtpReceived = false;
    private boolean isRzpAssistEnabled = false;
    private boolean jsInsertedInCurrentPage = false;
    String lastSms;
    private String lastURL = "";
    private String merchantKey;
    String message;
    private JSONObject otpElfPreferences = new JSONObject();
    /* access modifiers changed from: private */
    public boolean otpRead = false;
    private long pageStartTime;
    private JSONObject paymentData = new JSONObject();
    private String paymentId;
    private String sdkType = "standalone";
    private String sdkVersion;
    private int sdkVersionCode;
    String sender;
    private m smsAgent;
    /* access modifiers changed from: private */
    public WebView webview;

    public RzpAssist(String str, Activity activity2, WebView webView, String str2, int i, String str3) {
        if (D$$l_.a().isOTPElfEnabled().booleanValue()) {
            if (str == null || str.isEmpty()) {
                throw new RuntimeException("merchantKey cannot be null or empty");
            }
            this.sdkType = str2;
            this.sdkVersionCode = i;
            this.sdkVersion = str3;
            if (str2.equals("standalone") || str2.equalsIgnoreCase(BuildConfig.SDK_TYPE) || str2.equalsIgnoreCase("custom")) {
                AnalyticsUtil.setup(activity2, str, str2, i, str3);
            }
            this.webview = webView;
            this.merchantKey = str;
            this.activity = activity2;
            OtpElfData otpElfData = new OtpElfData(activity2);
            this.elfData = otpElfData;
            otpElfData.checkForUpdates();
            setup();
            AnalyticsUtil.addProperty("OTPElf Version", new AnalyticsProperty(BaseUtils.getLocalVersion(activity2, OtpElfData.versionKey), AnalyticsProperty.Scope.ORDER));
        }
    }

    private void setup() {
        m a = m.a();
        this.smsAgent = a;
        a.a((SmsAgentInterface) this);
        this.smsAgent.a(this.activity);
        this.webview.addJavascriptInterface(this, "OTPElfBridge");
        this.webview.getSettings().setUseWideViewPort(true);
    }

    /* access modifiers changed from: package-private */
    public final void enableMagic() {
        this.isMagic = true;
    }

    /* access modifiers changed from: package-private */
    public final void setPaymentData(JSONObject jSONObject) {
        this.paymentData = jSONObject;
    }

    public final void setOtpElfPreferences(JSONObject jSONObject) {
        this.otpElfPreferences = jSONObject;
    }

    public final void onPageFinished(WebView webView, String str) {
        AnalyticsUtil.trackPageLoadEnd(str, System.nanoTime() - this.pageStartTime);
        this.lastURL = str;
        this.currentLoadingUrl = "";
        if (D$$l_.a().isOTPElfEnabled().booleanValue() && !this.jsInsertedInCurrentPage) {
            handleJsInsertion();
            this.jsInsertedInCurrentPage = true;
        }
    }

    private void handleJsInsertion() {
        try {
            JSONObject oTPElfSettings = D$$l_.a().getOTPElfSettings();
            oTPElfSettings.put("merchant_key", this.merchantKey);
            oTPElfSettings.put("otp_permission", this.hasOtpPermission);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.sdkType);
            jSONObject.put("version", this.sdkVersion);
            jSONObject.put("platform", "android");
            jSONObject.put("framework", "native");
            jSONObject.put("name", this.sdkType + "_android_native");
            oTPElfSettings.put("sdk", jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            if (!this.isMagic) {
                jSONObject2.put("type", "rzpassist");
                jSONObject2.put("version_code", n$_B$.b.intValue());
            } else {
                jSONObject2.put("type", "magic");
                jSONObject2.put("version_code", n$_B$.c.intValue());
            }
            oTPElfSettings.put("plugin", jSONObject2);
            oTPElfSettings.put("payment_data", this.paymentData);
            oTPElfSettings.put("preferences", this.otpElfPreferences);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("package_name", this.activity.getApplicationContext().getPackageName());
            PackageManager packageManager = this.activity.getPackageManager();
            jSONObject3.put("app_name", AnalyticsUtil.returnUndefinedIfNull(packageManager.getPackageInfo(this.activity.getPackageName(), 0).applicationInfo.loadLabel(packageManager)));
            jSONObject3.put("platform", "mobile_sdk");
            jSONObject3.put("os", "android");
            jSONObject3.put("os_version", Build.VERSION.RELEASE);
            jSONObject3.put("data_network_type", BaseUtils.getDataNetworkType(this.activity).getNetworkTypeName());
            jSONObject3.put("framework", AnalyticsUtil.getFramework());
            jSONObject3.put("library", BuildConfig.SDK_TYPE);
            jSONObject3.put("sdk", jSONObject);
            oTPElfSettings.put("metadata", jSONObject3);
            injectJs("window.__rzp_options = " + oTPElfSettings.toString());
        } catch (Exception e) {
            d__1_.a("Unable to load otpelf settings", e);
        }
        injectJs(this.elfData.getOtpElfJs());
        AnalyticsUtil.trackEvent(AnalyticsEvent.OTPELF_INJECTED);
        String str = this.lastSms;
        if (str != null) {
            injectJs(String.format("OTPElf.showOTP('%s','%s')", new Object[]{str, this.sender}));
            this.lastSms = null;
        }
    }

    public final void onProgressChanged(int i) {
        D$$l_.a().isOTPElfEnabled().booleanValue();
    }

    public final void onPageStarted(WebView webView, String str) {
        new StringBuilder("RzpAssist onPageStarted: ").append(str);
        AnalyticsUtil.trackPageLoadStart(str);
        this.pageStartTime = System.nanoTime();
        this.currentLoadingUrl = str;
        this.jsInsertedInCurrentPage = false;
    }

    public final void paymentFlowEnd() {
        if (this.sdkType.equals("standalone")) {
            AnalyticsUtil.postData();
        }
        if (D$$l_.a().isOTPElfEnabled().booleanValue()) {
            this.smsAgent.b(this.activity);
            this.smsAgent.b((SmsAgentInterface) this);
        }
    }

    public final void postSms(String str, String str2) {
        if (this.isRzpAssistEnabled) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sender", str);
                jSONObject.put("message", str2);
                this.sender = str;
                this.message = str2;
                this.lastSms = jSONObject.toString();
                injectJs(String.format("OTPElf.showOTP('%s','%s')", new Object[]{str2, str}));
            } catch (Exception e) {
                d__1_.a("Exception", e);
            }
        }
    }

    public final void setSmsPermission(boolean z) {
        setOTPEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public final void setPaymentId(String str) {
        this.paymentId = str;
    }

    private void postStatsToAPI() {
        try {
            String constructBasicAuth = BaseUtils.constructBasicAuth(this.merchantKey);
            HashMap hashMap = new HashMap();
            hashMap.put(HttpHeaders.AUTHORIZATION, "Basic " + constructBasicAuth);
            hashMap.put(HttpHeaders.CONTENT_TYPE, "application/json");
            if (this.paymentId != null) {
                M$_3_.a("https://api.razorpay.com/v1/payments/" + this.paymentId + "/metadata", E$_j$.a(this.otpRead).toString(), hashMap, new c(this));
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError("RzpAssist", "S0", e.getMessage());
        }
    }

    private void injectJs(String str) {
        this.webview.loadUrl(String.format("javascript: %s", new Object[]{str}));
    }

    /* access modifiers changed from: package-private */
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        m mVar = this.smsAgent;
        switch (i) {
            case 1:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    mVar.a(false);
                    AnalyticsUtil.trackEvent(AnalyticsEvent.SMS_PERMISSION_NOW_DENIED);
                    return;
                }
                mVar.a(true);
                mVar.b();
                AnalyticsUtil.trackEvent(AnalyticsEvent.SMS_PERMISSION_NOW_GRANTED);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public final String getLastLoadedUrl() {
        return this.lastURL;
    }

    /* access modifiers changed from: package-private */
    public final String getCurrentLoadingUrl() {
        return this.currentLoadingUrl;
    }

    public final void reset() {
        postStatsToAPI();
        this.lastURL = "";
        this.currentLoadingUrl = "";
        this.otpRead = false;
    }

    /* access modifiers changed from: package-private */
    public final boolean isRazorpayOtpReceived() {
        return this.isRazorpayOtpReceived;
    }

    /* access modifiers changed from: package-private */
    public final void setOTPEnabled(boolean z) {
        this.hasOtpPermission = z;
        AnalyticsUtil.addProperty("otp_autoreading_access", new AnalyticsProperty(z, AnalyticsProperty.Scope.ORDER));
    }

    /* access modifiers changed from: package-private */
    public final void setRzpAssistEnabled(boolean z) {
        this.isRzpAssistEnabled = z;
    }

    @JavascriptInterface
    public final void setUseWideViewPort(boolean z) {
        this.activity.runOnUiThread(new d(this, z));
    }

    @JavascriptInterface
    public final void openKeyboard() {
        this.activity.runOnUiThread(new e(this));
    }

    @JavascriptInterface
    public final void toast(String str) {
        this.activity.runOnUiThread(new f(this, str));
    }

    @JavascriptInterface
    public final void trackEvent(String str, String str2) {
        try {
            AnalyticsEvent analyticsEvent = AnalyticsEvent.JS_EVENT;
            analyticsEvent.setEventName(str);
            AnalyticsUtil.trackEvent(analyticsEvent, new JSONObject(str2));
        } catch (Exception e) {
            d__1_.a("Error in tracking JS Event", e);
        }
    }

    @JavascriptInterface
    public final void trackEvent(String str) {
        AnalyticsEvent analyticsEvent = AnalyticsEvent.JS_EVENT;
        analyticsEvent.setEventName(str);
        AnalyticsUtil.trackEvent(analyticsEvent);
    }

    @JavascriptInterface
    public final void onOtpParsed(String str) {
        this.activity.runOnUiThread(new g(this, str));
    }

    @JavascriptInterface
    public final void copyToClipboard(String str) {
        ((ClipboardManager) this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("rzp_clip_data", str));
    }
}
