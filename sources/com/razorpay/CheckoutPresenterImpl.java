package com.razorpay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tasks.Task;
import com.google.common.net.HttpHeaders;
import com.razorpay.AnalyticsProperty;
import com.razorpay.CheckoutBridge;
import com.razorpay.b;
import com.razorpay.c;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

class CheckoutPresenterImpl implements CheckoutInteractor, CheckoutPresenter {
    protected Activity activity;
    private boolean allowRotation = false;
    private AutoReadOtpHelper autoReadOtpHelper;
    private String checkoutContent = "{}";
    private long checkoutLoadStartAt;
    Queue<String> checkoutMessageQueue = new LinkedList();
    O_$v$ checkoutOptions;
    private String checkoutUrl;
    private boolean clearHistory;
    private String dashOptions;
    private JSONObject dashOptionsJSON;
    private boolean isActivityCreated = false;
    private boolean isCheckoutLoaded = false;
    private boolean isMagic = false;
    private boolean isPaymentSuccessful = false;
    /* access modifiers changed from: private */
    public boolean isTwoWebViewFlow = false;
    /* access modifiers changed from: private */
    public Task<Void> loginOtpSmsTask;
    private F_$o_ magicBase = null;
    String merchantKey;
    private int merchantLogoResourceId = 0;
    /* access modifiers changed from: private */
    public BroadcastReceiver otpAutoReadBroadcast = new E$_q$(this);
    private int paymentAttempts = 0;
    private String payment_id = null;
    private long preloadAbortDuration;
    private long preloadCompleteDuration;
    private String sanitizedChallanEncodedString = "";
    private boolean sendSmsHash = false;
    protected CheckoutView view;

    interface CheckoutView {
        void addJavascriptInterfaceToPrimaryWebview(Object obj, String str);

        void checkSmsPermission();

        void clearWebViewHistory(int i);

        void destroy(int i, String str);

        WebView getWebView(int i);

        void hideProgressBar();

        boolean isWebViewVisible(int i);

        void loadData(int i, String str, String str2, String str3);

        void loadDataWithBaseURL(int i, String str, String str2, String str3, String str4, String str5);

        void loadUrl(int i, String str);

        void makeWebViewVisible(int i);

        void showProgressBar(int i);

        void showToast(String str, int i);
    }

    public CheckoutPresenterImpl(Activity activity2, CheckoutView checkoutView) {
        this.activity = activity2;
        this.view = checkoutView;
    }

    public boolean setOptions(Bundle bundle, boolean z) {
        this.isActivityCreated = z;
        if (bundle == null) {
            destroyActivity(0, this.activity.getResources().getString(R.string.activity_result_invalid_parameters));
            return false;
        }
        O_$v$ o_$v$ = new O_$v$(bundle.getString("OPTIONS"));
        this.checkoutOptions = o_$v$;
        JSONObject d = o_$v$.d();
        if (d.has("retry")) {
            g$_H$.a().a(d);
        }
        this.merchantKey = this.checkoutOptions.a();
        this.sendSmsHash = this.checkoutOptions.b();
        this.allowRotation = this.checkoutOptions.c();
        int i = bundle.getInt("IMAGE", 0);
        this.merchantLogoResourceId = i;
        this.checkoutOptions.a(this.activity, i);
        AnalyticsUtil.setup(this.activity, this.merchantKey, g$_H$.a, g$_H$.c, g$_H$.b);
        d.has("ep");
        O_$v$ o_$v$2 = this.checkoutOptions;
        String a = CheckoutUtils.a("https://api.razorpay.com/v1/checkout/public", "version", g$_H$.b);
        Map<String, String> g = g$_H$.a().g();
        for (String next : g.keySet()) {
            a = CheckoutUtils.a(a, next, g.get(next));
        }
        Iterator<String> it = g$_H$.a().h().iterator();
        while (it.hasNext()) {
            String next2 = it.next();
            if (o_$v$2.a(next2)) {
                a = CheckoutUtils.a(a, next2, (String) o_$v$2.c(next2));
            }
        }
        new StringBuilder("Modified Url: ").append(a);
        this.checkoutUrl = a;
        if (a == null) {
            destroyActivity(3, this.activity.getResources().getString(R.string.activity_result_invalid_url));
        }
        if (!z) {
            this.checkoutOptions.h();
            String string = l.a(this.activity).getString("pref_merchant_options_" + this.merchantKey, (String) null);
            this.dashOptions = string;
            if (string != null) {
                try {
                    this.dashOptionsJSON = new JSONObject(this.dashOptions);
                } catch (Exception e) {
                    AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
                }
            }
            String string2 = bundle.getString("FRAMEWORK");
            if (string2 != null) {
                AnalyticsUtil.addProperty("framework", new AnalyticsProperty(string2, AnalyticsProperty.Scope.ORDER));
            }
            AnalyticsUtil.setFramework(string2);
            String string3 = bundle.getString("FRAMEWORK_VERSION");
            if (string3 != null) {
                AnalyticsUtil.addProperty("frameworkVersion", new AnalyticsProperty(string3, AnalyticsProperty.Scope.ORDER));
            }
            if (bundle.getBoolean("DISABLE_FULL_SCREEN", false)) {
                CheckoutUtils.a(this.activity);
            }
            if (bundle.containsKey("PRELOAD_COMPLETE_DURATION")) {
                this.preloadCompleteDuration = bundle.getLong("PRELOAD_COMPLETE_DURATION");
            }
            if (!bundle.containsKey("PRELOAD_ABORT_DURATION")) {
                return true;
            }
            this.preloadAbortDuration = bundle.getLong("PRELOAD_ABORT_DURATION");
            return true;
        }
        this.dashOptions = bundle.getString("DASH_OPTIONS");
        if (!bundle.getBoolean("DISABLE_FULL_SCREEN", false)) {
            return true;
        }
        CheckoutUtils.a(this.activity);
        return true;
    }

    private void injectJs(String str) {
        this.view.getWebView(1).loadUrl(String.format("javascript: %s", new Object[]{str}));
    }

    public void onActivityResultReceived(int i, int i2, Intent intent) {
        Intent intent2;
        if (i == 77) {
            if (i2 == -1 && intent.getData() != null) {
                Uri data = intent.getData();
                try {
                    ParcelFileDescriptor openFileDescriptor = this.activity.getContentResolver().openFileDescriptor(data, "w");
                    FileOutputStream fileOutputStream = new FileOutputStream(openFileDescriptor.getFileDescriptor());
                    fileOutputStream.write(Base64.decode(this.sanitizedChallanEncodedString, 0));
                    fileOutputStream.close();
                    openFileDescriptor.close();
                    intent2 = new Intent("android.intent.action.VIEW");
                } catch (IOException e) {
                    e.printStackTrace();
                    intent2 = new Intent("android.intent.action.VIEW");
                } catch (Throwable th) {
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    intent3.setDataAndType(data, "application/pdf");
                    intent3.addFlags(1);
                    this.activity.startActivity(intent3);
                    throw th;
                }
                intent2.setDataAndType(data, "application/pdf");
                intent2.addFlags(1);
                this.activity.startActivity(intent2);
            }
        } else if (i == 1001) {
            if (i2 == -1) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_RECEIVED_SMS);
                String stringExtra = intent.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                if (this.isCheckoutLoaded) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sender", "razorpay");
                        jSONObject.put("message", stringExtra);
                        String.format("javascript: OTPElf.elfBridge.setSms(%s)", new Object[]{jSONObject.toString()});
                        injectJs(String.format("OTPElf.showOTP('%s','%s')", new Object[]{stringExtra, "razorpay"}));
                        AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_OTP_POPULATION_JS);
                    } catch (JSONException e2) {
                        AnalyticsUtil.reportError("CxPsntrImpl", "S0", e2.getLocalizedMessage());
                        e2.printStackTrace();
                    }
                }
            } else {
                AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_CONSENT_DECLINED);
            }
        } else if (i == 99) {
            JSONObject jSONFromIntentData = BaseUtils.getJSONFromIntentData(intent);
            loadResultToWebView(jSONFromIntentData, String.format("javascript: upiIntentResponse(%s)", new Object[]{jSONFromIntentData.toString()}));
        } else if (i == 20) {
            try {
                JSONObject jSONObject2 = new JSONObject("{'data':" + i2 + "}");
                jSONObject2.put("provider", "CRED");
                loadResultToWebView(jSONObject2, String.format("javascript:externalAppResponse(%s)", new Object[]{jSONObject2.toString()}));
            } catch (JSONException e3) {
                AnalyticsUtil.reportError("CxPsntrImpl", "S0", e3.getMessage());
            }
        }
    }

    private void loadResultToWebView(JSONObject jSONObject, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("result", jSONObject);
        AnalyticsUtil.trackEvent(AnalyticsEvent.NATIVE_INTENT_ONACTIVITY_RESULT, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
        if (this.isCheckoutLoaded) {
            this.view.loadUrl(1, str);
            return;
        }
        if (this.checkoutMessageQueue == null) {
            this.checkoutMessageQueue = new LinkedList();
        }
        this.checkoutMessageQueue.add(str);
    }

    public void verifyGPaySdkResponse(String str) {
        String format = String.format("javascript: window.externalSDKResponse(%s)", new Object[]{str});
        if (this.isCheckoutLoaded) {
            this.view.loadUrl(1, format);
            return;
        }
        if (this.checkoutMessageQueue == null) {
            this.checkoutMessageQueue = new LinkedList();
        }
        this.checkoutMessageQueue.add(format);
    }

    public void unregisterReceivers() {
        try {
            BroadcastReceiver broadcastReceiver = this.otpAutoReadBroadcast;
            if (broadcastReceiver != null) {
                this.activity.unregisterReceiver(broadcastReceiver);
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPrntrImpl", "S2", e.getMessage());
        }
        try {
            AutoReadOtpHelper autoReadOtpHelper2 = this.autoReadOtpHelper;
            if (autoReadOtpHelper2 != null) {
                this.activity.unregisterReceiver(autoReadOtpHelper2);
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError("CxPrntrImpl", "S2", e2.getMessage());
        }
    }

    public String getSdkPlugins() {
        HashMap<String, String> allPluginsFromManifest = BaseUtils.getAllPluginsFromManifest(this.activity);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isAmazonPluginIntegrated", false);
            jSONObject.put("isGooglePayPluginIntegrated", false);
            if (allPluginsFromManifest != null) {
                if (allPluginsFromManifest.size() != 0) {
                    for (String next : allPluginsFromManifest.values()) {
                        if (allPluginsFromManifest.size() > 0 && next.equalsIgnoreCase("com.razorpay.RazorpayAmazon")) {
                            jSONObject.put("isAmazonPluginIntegrated", true);
                        }
                        if (allPluginsFromManifest.size() > 0 && next.equalsIgnoreCase("com.razorpay.RzpGpayMerged")) {
                            jSONObject.put("isGooglePayPluginIntegrated", true);
                        }
                    }
                    return jSONObject.toString();
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void onCheckoutRendered() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_RENDERED_COMPLETE);
    }

    public void getPdfString(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && str2.contains("base64,")) {
            String str3 = str2.split("base64,")[1];
            this.sanitizedChallanEncodedString = str3;
            BaseUtils.pdfDownloadHelper(this.activity, str, str3);
        }
    }

    public O_$v$ getCheckoutOptions() {
        return this.checkoutOptions;
    }

    public void setUpAddOn() {
        this.magicBase = new F_$o_(this.activity, this.view.getWebView(2));
    }

    public void loadForm(String str) {
        if (this.paymentAttempts != 0) {
            AnalyticsUtil.postData();
        }
        this.paymentAttempts++;
        AnalyticsUtil.addProperty("payment_attempt", new AnalyticsProperty(this.paymentAttempts, AnalyticsProperty.Scope.ORDER));
        this.clearHistory = true;
        this.view.loadUrl(1, (this.checkoutUrl + str).replace(" ", "%20"));
    }

    public void passPrefillToSegment() {
        String k = this.checkoutOptions.k();
        if (!TextUtils.isEmpty(k)) {
            AnalyticsUtil.addProperty("email", new AnalyticsProperty(k, AnalyticsProperty.Scope.ORDER));
        }
        String j = this.checkoutOptions.j();
        if (!TextUtils.isEmpty(j)) {
            AnalyticsUtil.addProperty("contact", new AnalyticsProperty(j, AnalyticsProperty.Scope.ORDER));
        }
    }

    public void handleCardSaving() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CARD_SAVING_START);
        f$_G$.a(this.activity.getApplicationContext());
    }

    public void saveInstanceState(Bundle bundle) {
        if (this.merchantLogoResourceId != 0) {
            bundle.putString("OPTIONS", this.checkoutOptions.i());
            bundle.putInt("IMAGE", this.merchantLogoResourceId);
        } else {
            bundle.putString("OPTIONS", this.checkoutOptions.e());
        }
        bundle.putString("DASH_OPTIONS", this.dashOptions);
        if (this.activity.getIntent() != null) {
            bundle.putBoolean("DISABLE_FULL_SCREEN", this.activity.getIntent().getBooleanExtra("DISABLE_FULL_SCREEN", false));
        }
    }

    public void setCheckoutLoadStartAt() {
        this.checkoutLoadStartAt = System.nanoTime();
    }

    public void destroyActivity(int i, String str) {
        AnalyticsUtil.addProperty("destroy_resultCode", new AnalyticsProperty(String.valueOf(i), AnalyticsProperty.Scope.ORDER));
        AnalyticsUtil.addProperty("destroy_result", new AnalyticsProperty(str, AnalyticsProperty.Scope.ORDER));
        AnalyticsUtil.trackEvent(AnalyticsEvent.INTERNAL_DESTROY_METHOD_CALLED);
        cleanUpOnDestroy();
        this.view.destroy(i, str);
    }

    public void onCheckoutBackPress() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_SOFT_BACK_PRESSED);
        destroyActivity(0, BaseUtils.getPaymentCancelledResponse(BaseUtils.getInstance().getMetadata()));
    }

    /* access modifiers changed from: protected */
    public void enableAddon(JSONObject jSONObject) {
        try {
            if (jSONObject.has("magic")) {
                boolean z = jSONObject.getBoolean("magic");
                this.isMagic = z;
                F_$o_ f_$o_ = this.magicBase;
                if (f_$o_ != null) {
                    f_$o_.c = z;
                }
                AnalyticsUtil.addProperty("is_magic", new AnalyticsProperty(this.isMagic, AnalyticsProperty.Scope.PAYMENT));
            }
        } catch (JSONException e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void invokePopup(String str) {
        this.isTwoWebViewFlow = true;
        try {
            this.activity.runOnUiThread(new k__c$(this, str));
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void fetchCondfig() {
        g$_H$.d = isMagicPresent();
        g$_H$.a((Context) this.activity, this.merchantKey);
    }

    public boolean isMagicPresent() {
        return false;
    }

    public void onProgressChanges(int i, int i2) {
        switch (i) {
            case 1:
                this.view.showProgressBar(i2);
                return;
            default:
                return;
        }
    }

    public void onPageStarted(int i, WebView webView, String str) {
        switch (i) {
            case 1:
                CheckoutUtils.e(this.activity);
                return;
            case 2:
                F_$o_ f_$o_ = this.magicBase;
                if (f_$o_ != null && this.isMagic) {
                    f_$o_.d = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPageFinished(int i, WebView webView, String str) {
        switch (i) {
            case 1:
                primaryWebviewPageFinished(str, webView);
                return;
            case 2:
                F_$o_ f_$o_ = this.magicBase;
                if (f_$o_ != null && this.isMagic) {
                    f_$o_.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void sendQueuedMessagesToCheckout() {
        Queue<String> queue = this.checkoutMessageQueue;
        if (queue != null && !queue.isEmpty()) {
            for (String loadUrl : this.checkoutMessageQueue) {
                this.view.loadUrl(1, loadUrl);
            }
            this.checkoutMessageQueue.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void primaryWebviewPageFinished(String str, WebView webView) {
        long nanoTime = System.nanoTime();
        CheckoutUtils.a();
        this.view.hideProgressBar();
        if (str.contains("https://api.razorpay.com") && str.contains("android") && str.contains(BuildConfig.VERSION_NAME)) {
            if (this.paymentAttempts == 1) {
                this.isCheckoutLoaded = true;
                sendQueuedMessagesToCheckout();
                HashMap hashMap = new HashMap();
                long j = nanoTime - this.checkoutLoadStartAt;
                hashMap.put("checkout_load_duration", Long.valueOf(j));
                new StringBuilder("Checkout loaded in ").append(BaseUtils.nanoTimeToSecondsString(j, 2)).append(" sec.");
                long j2 = this.preloadCompleteDuration;
                if (j2 > 0) {
                    hashMap.put("preload_finish_duration", Long.valueOf(j2));
                    new StringBuilder("Preload was completed in ").append(BaseUtils.nanoTimeToSecondsString(this.preloadCompleteDuration, 2)).append(" sec.");
                } else {
                    long j3 = this.preloadAbortDuration;
                    if (j3 > 0) {
                        hashMap.put("preload_abort_duration", Long.valueOf(j3));
                        new StringBuilder("Preload was aborted in ").append(BaseUtils.nanoTimeToSecondsString(this.preloadAbortDuration, 2)).append(" sec.");
                    }
                }
                long j4 = this.preloadCompleteDuration - j;
                if (j4 > 0) {
                    hashMap.put("time_shaved_off", Long.valueOf(j4));
                    new StringBuilder("Load time shaved is ").append(BaseUtils.nanoTimeToSecondsString(j4, 2)).append(" sec.");
                }
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_LOADED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
            }
            if (this.clearHistory) {
                this.view.clearWebViewHistory(1);
                this.clearHistory = false;
            }
        }
    }

    public String getProgressBarColor() {
        String str = null;
        try {
            if (this.checkoutOptions.d() != null) {
                String string = this.checkoutOptions.d().getJSONObject("theme").getString(TypedValues.Custom.S_COLOR);
                Color.parseColor(string);
                return string;
            }
            throw new Exception("No options defined");
        } catch (Exception e) {
            JSONObject jSONObject = this.dashOptionsJSON;
            if (jSONObject != null) {
                str = jSONObject.getJSONObject("theme").getString(TypedValues.Custom.S_COLOR);
                Color.parseColor(str);
            } else {
                throw new Exception("No dash options defined");
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e2.getMessage());
        }
        AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getMessage());
        return str;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void sendOtpPermissionCallback(boolean z) {
        this.activity.runOnUiThread(new N_$R$(this, z));
    }

    /* access modifiers changed from: protected */
    public void addOnFlowEnd() {
        F_$o_ f_$o_ = this.magicBase;
        if (f_$o_ != null) {
            f_$o_.b.b((SmsAgentInterface) f_$o_);
            f_$o_.b.b((Activity) f_$o_.a);
        }
    }

    public void cleanUpOnDestroy() {
        try {
            markPaymentCancelled();
            this.activity.unregisterReceiver(this.autoReadOtpHelper);
            this.activity.unregisterReceiver(this.otpAutoReadBroadcast);
            J$_M_.a();
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: private */
    public void markPaymentCancelled() {
        if (this.payment_id != null && !this.isPaymentSuccessful) {
            try {
                String constructBasicAuth = BaseUtils.constructBasicAuth(this.merchantKey);
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.AUTHORIZATION, "Basic " + constructBasicAuth);
                M$_3_.a("https://api.razorpay.com/v1/payments/" + this.payment_id + "/cancel?platform=android_sdk", hashMap, new c__h$(this));
                this.payment_id = null;
            } catch (Exception e) {
                AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            }
        }
    }

    public void backPressed(Map<String, Object> map) {
        boolean z;
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_HARD_BACK_PRESSED, AnalyticsUtil.getJSONResponse(map));
        WebView webView = this.view.getWebView(1);
        if ((webView.getTag() == null ? "" : webView.getTag().toString()).contains(g$_H$.a().getCheckoutEndpoint())) {
            z = true;
        } else {
            z = false;
        }
        if (z && !this.view.isWebViewVisible(2)) {
            this.view.loadUrl(1, "javascript: window.backPressed ? window.backPressed('onCheckoutBackPress') : CheckoutBridge.onCheckoutBackPress();");
            map.put("in_checkout", "true");
        } else if (!g$_H$.a().k()) {
            destroyActivity(0, "BackPressed");
        } else {
            CheckoutUtils.a(this.activity, g$_H$.a().l(), g$_H$.a().j(), g$_H$.a().i(), new Q$$2_(this, map));
        }
    }

    public void onLoad() {
        this.activity.runOnUiThread(new d__w$(this));
        if (Build.VERSION.SDK_INT < 29 && ActivityCompat.checkSelfPermission(this.activity, "android.permission.RECEIVE_SMS") == 0) {
            return;
        }
        if (this.sendSmsHash) {
            this.loginOtpSmsTask = SmsRetriever.getClient(this.activity).startSmsRetriever();
            try {
                new O$_M$(this, 2000, 1000).start();
            } catch (Exception e) {
                AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getMessage());
                startSmsRetrieverForSavedCardsOTP();
            }
        } else {
            startSmsRetrieverForSavedCardsOTP();
        }
    }

    /* access modifiers changed from: private */
    public void startSmsRetrieverForSavedCardsOTP() {
        this.loginOtpSmsTask = SmsRetriever.getClient(this.activity).startSmsUserConsent((String) null);
        this.autoReadOtpHelper = new AutoReadOtpHelper(this.activity);
        this.activity.registerReceiver(this.autoReadOtpHelper, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
    }

    /* access modifiers changed from: protected */
    public JSONObject getOptionsForHandleMessage() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("options", this.checkoutOptions.d());
            jSONObject.put("data", this.checkoutContent);
            jSONObject.put("id", AnalyticsUtil.getLocalOrderId());
            jSONObject.put("pdf_download_supported", true);
            jSONObject.put("key_id", this.merchantKey);
            jSONObject.put("externalSDKs", new JSONObject());
            if (this.checkoutOptions.b()) {
                jSONObject.put("sms_hash", new AppSignatureHelper(this.activity).getAppSignatures().get(0));
            }
            jSONObject.put("upi_intents_data", CheckoutUtils.c(this.activity));
            jSONObject.put("uri_data", CheckoutUtils.d(this.activity));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("openedAt", System.currentTimeMillis());
            jSONObject.put("metadata", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("framework", AnalyticsUtil.getFramework());
            jSONObject3.put("type", g$_H$.a);
            jSONObject3.put("name", g$_H$.a + "_android_" + AnalyticsUtil.getFramework());
            jSONObject3.put("version", BuildConfig.VERSION_NAME);
            jSONObject3.put("platform", "android");
            jSONObject.put("sdk", jSONObject3);
            String b = f$_G$.b(this.activity.getApplicationContext());
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put("device_token", b);
            }
            jSONObject.put("sdk_popup", true);
            jSONObject.put("magic", true);
            jSONObject.put("network_type", BaseUtils.getNetworkType(this.activity));
            jSONObject.put("activity_recreated", this.isActivityCreated);
        } catch (JSONException e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getLocalizedMessage());
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public String getHandleMessageFormattedString() {
        return String.format("javascript: handleMessage(%s)", new Object[]{getOptionsForHandleMessage().toString()});
    }

    public void setAppToken(String str) {
        l.b(this.activity).putString("rzp_app_token", str).apply();
    }

    public void setDeviceToken(String str) {
        f$_G$.a((Context) this.activity, str);
    }

    public void callNativeIntent(String str, String str2) {
        BaseUtils.startActivityForResult(str, str2, this.activity);
        HashMap hashMap = new HashMap();
        if (str == null) {
            str = "null";
        }
        hashMap.put(ImagesContract.URL, str);
        if (str2 == null) {
            str2 = "null";
        }
        hashMap.put("package_name", str2);
        AnalyticsUtil.trackEvent(AnalyticsEvent.NATIVE_INTENT_CALLED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
    }

    public void setPaymentID(String str) {
        new StringBuilder("setPaymentID called: ").append(str);
        this.payment_id = str;
        BaseUtils.getInstance().setPaymentId(str);
        if (this.checkoutOptions.f() != null) {
            BaseUtils.getInstance().setOrderId(this.checkoutOptions.f());
        }
        AnalyticsUtil.addProperty("payment_id", new AnalyticsProperty(str, AnalyticsProperty.Scope.PAYMENT));
        AnalyticsUtil.trackEvent(AnalyticsEvent.PAYMENT_ID_ATTACHED);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMerchantOptions(java.lang.String r5) {
        /*
            r4 = this;
            r4.dashOptions = r5
            r0 = 0
            if (r5 == 0) goto L_0x0018
            java.lang.String r1 = "undefined"
            boolean r1 = r5.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x001b }
            if (r1 == 0) goto L_0x000e
            goto L_0x0018
        L_0x000e:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x001b }
            java.lang.String r2 = r4.dashOptions     // Catch:{ Exception -> 0x001b }
            r1.<init>(r2)     // Catch:{ Exception -> 0x001b }
            r4.dashOptionsJSON = r1     // Catch:{ Exception -> 0x001b }
            goto L_0x002e
        L_0x0018:
            r4.dashOptionsJSON = r0     // Catch:{ Exception -> 0x001b }
            goto L_0x002e
        L_0x001b:
            r1 = move-exception
            java.lang.String r2 = "Error parsing merchant dash options JSON"
            com.razorpay.d__1_.a(r2, r1)
            r4.dashOptionsJSON = r0
            java.lang.String r1 = r1.getMessage()
            java.lang.String r2 = "CxPsntrImpl"
            java.lang.String r3 = "S0"
            com.razorpay.AnalyticsUtil.reportError((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r1)
        L_0x002e:
            org.json.JSONObject r1 = r4.dashOptionsJSON
            if (r1 != 0) goto L_0x003a
            android.app.Activity r5 = r4.activity
            java.lang.String r1 = r4.merchantKey
            com.razorpay.CheckoutUtils.a((android.content.Context) r5, (java.lang.String) r1, (java.lang.String) r0)
            return
        L_0x003a:
            android.app.Activity r0 = r4.activity
            java.lang.String r1 = r4.merchantKey
            com.razorpay.CheckoutUtils.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.CheckoutPresenterImpl.setMerchantOptions(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public void addAnalyticsData(JSONObject jSONObject) {
        AnalyticsUtil.addFilteredPropertiesFromPayload(jSONObject);
    }

    public void onSubmit(String str) {
        if (this.paymentAttempts > 1) {
            AnalyticsUtil.refreshPaymentSession();
        }
        if (this.autoReadOtpHelper != null && this.loginOtpSmsTask.isComplete()) {
            try {
                this.activity.unregisterReceiver(this.autoReadOtpHelper);
            } catch (IllegalArgumentException e) {
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.checkoutContent = str;
            addAnalyticsData(jSONObject);
            savePrefillData(jSONObject);
            if (jSONObject.has("method")) {
                String string = jSONObject.getString("method");
                if (!string.equalsIgnoreCase("netbanking")) {
                    if (!string.equalsIgnoreCase("card")) {
                        if (string.equals("wallet") && jSONObject.has("wallet")) {
                            String string2 = jSONObject.getString("wallet");
                            if (this.checkoutOptions.b(string2)) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("external_wallet", string2);
                                AnalyticsUtil.addProperty("external_wallet", new AnalyticsProperty(string2, AnalyticsProperty.Scope.ORDER));
                                AnalyticsUtil.trackEvent(AnalyticsEvent.EXTERNAL_WALLET_SELECTED);
                                onComplete(jSONObject2);
                            }
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= 29 || ActivityCompat.checkSelfPermission(this.activity, "android.permission.RECEIVE_SMS") != 0) {
                    Log.d("SMS", "received method as netbanking");
                    startSmsRetrieverForSavedCardsOTP();
                }
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_SUBMIT);
            AnalyticsUtil.postData();
        } catch (Exception e2) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e2.getMessage());
            d__1_.a("Error in submit", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void onComplete(JSONObject jSONObject) {
        try {
            if (jSONObject.has("error")) {
                AnalyticsUtil.addProperty("payment_status", new AnalyticsProperty("fail", AnalyticsProperty.Scope.PAYMENT));
                AnalyticsUtil.addProperty("payload", new AnalyticsProperty(jSONObject.toString(), AnalyticsProperty.Scope.PAYMENT));
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PAYMENT_COMPLETE);
                if (this.isTwoWebViewFlow) {
                    this.view.makeWebViewVisible(1);
                }
                onError(jSONObject);
            } else if (jSONObject.has("razorpay_fund_account_id")) {
                destroyActivity(1, jSONObject.toString());
            } else if (jSONObject.has("razorpay_payment_id")) {
                String string = jSONObject.getString("razorpay_payment_id");
                this.payment_id = string;
                AnalyticsUtil.addProperty("payment_id", new AnalyticsProperty(string, AnalyticsProperty.Scope.PAYMENT));
                AnalyticsUtil.addProperty("payment_status", new AnalyticsProperty("success", AnalyticsProperty.Scope.PAYMENT));
                AnalyticsUtil.addProperty("payload", new AnalyticsProperty(jSONObject.toString(), AnalyticsProperty.Scope.PAYMENT));
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PAYMENT_COMPLETE);
                this.isPaymentSuccessful = true;
                destroyActivity(1, jSONObject.toString());
            } else if (jSONObject.has("external_wallet")) {
                destroyActivity(4, jSONObject.toString());
            } else {
                destroyActivity(0, "Post payment parsing error");
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getMessage());
            destroyActivity(0, e.getMessage());
        }
        this.isTwoWebViewFlow = false;
    }

    /* access modifiers changed from: protected */
    public void onError(JSONObject jSONObject) {
        if (this.isTwoWebViewFlow) {
            this.view.loadUrl(1, String.format("javascript: window.onComplete(%s)", new Object[]{jSONObject.toString()}));
            return;
        }
        this.activity.runOnUiThread(new G$_X_(this, jSONObject));
    }

    /* access modifiers changed from: private */
    public void handleRetry(String str) {
        boolean z;
        String str2 = "?";
        int i = this.paymentAttempts;
        int f = g$_H$.a().f();
        if (!g$_H$.a().e() || (f != -1 && f <= i)) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            destroyActivity(0, str);
        } else if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error")) {
                    StringBuilder append = new StringBuilder().append(str);
                    if (this.checkoutUrl.contains(str2)) {
                        str2 = "&";
                    }
                    str = append.append(str2).toString();
                    if (jSONObject.get("error") instanceof JSONObject) {
                        str = str + "error=" + ((JSONObject) jSONObject.get("error")).toString();
                    }
                }
                helpersReset();
                loadForm(str);
            } catch (Exception e) {
                destroyActivity(0, "");
                AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getMessage());
            }
        } else {
            destroyActivity(0, "");
        }
    }

    /* access modifiers changed from: protected */
    public void helpersReset() {
    }

    private void savePrefillData(JSONObject jSONObject) {
        try {
            if (jSONObject.has("contact")) {
                CheckoutUtils.b(this.activity, jSONObject.getString("contact"));
                this.checkoutOptions.a("contact", (Object) jSONObject.getString("contact"));
            }
            if (jSONObject.has("email")) {
                CheckoutUtils.a((Context) this.activity, jSONObject.getString("email"));
                this.checkoutOptions.a("email", (Object) jSONObject.getString("email"));
            }
        } catch (JSONException e) {
            d__1_.a("Error parsing JSON", e);
        }
    }

    public void onFault(String str) {
        try {
            destroyActivity(3, new JSONObject(str).toString());
        } catch (JSONException e) {
            destroyActivity(3, BaseUtils.getGenericPaymentErrorResponse(str, BaseUtils.getInstance().getMetadata()));
        }
    }

    public void onComplete(String str) {
        this.activity.runOnUiThread(new K_$q$(this, str));
    }

    public void setDimensions(int i, int i2) {
        if (b.a(this.activity)) {
            this.activity.runOnUiThread(new c._2_(this, i2, i));
        }
    }

    public void onDismiss() {
        destroyActivity(0, BaseUtils.getPaymentCancelledResponse(BaseUtils.getInstance().getMetadata()));
    }

    public void onDismiss(String str) {
        try {
            destroyActivity(0, new JSONObject(str).toString());
        } catch (JSONException e) {
            destroyActivity(0, BaseUtils.getGenericPaymentErrorResponse(str, BaseUtils.getInstance().getMetadata()));
        }
    }

    public void requestExtraAnalyticsData() {
        this.activity.runOnUiThread(new J__A$(this, AnalyticsUtil.getExtraAnalyticsPayload()));
    }

    public void onError(String str) {
        try {
            onError(new JSONObject(str));
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getMessage());
            this.activity.runOnUiThread(new L_$k$(this));
        }
    }

    public void relay(String str) {
    }

    public void requestOtpPermission() {
    }

    public void toast(String str, int i) {
        this.activity.runOnUiThread(new T_$Z$(this, str, i));
    }

    public void showAlertDialog(String str, String str2, String str3) {
        this.activity.runOnUiThread(new H$$i_(this, str, str3, str2));
    }

    public void sendDataToWebView(int i, String str) {
        this.activity.runOnUiThread(new z$_w$(this, i, str));
    }

    public void checkSmsPermission() {
        this.view.checkSmsPermission();
    }

    public void isWebViewSafeOnUI(int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
        this.activity.runOnUiThread(new b._f_(this, i, webViewSafeCheckCallback));
    }

    /* access modifiers changed from: private */
    public void executeWebViewCallback(int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
        CheckoutView checkoutView;
        int i2 = 1;
        if (i == 1) {
            checkoutView = this.view;
        } else {
            checkoutView = this.view;
            i2 = 2;
        }
        try {
            String host = new URL(checkoutView.getWebView(i2).getTag().toString()).getHost();
            if (host == null || (!host.endsWith("razorpay.com") && !host.endsWith("razorpay.in"))) {
                webViewSafeCheckCallback.unSecure();
            } else {
                webViewSafeCheckCallback.secure();
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S0", e.getLocalizedMessage());
            webViewSafeCheckCallback.unSecure();
        }
    }

    public void isWebViewSafe(int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
        executeWebViewCallback(i, webViewSafeCheckCallback);
    }

    public boolean isUserRegisteredOnUPI(String str) {
        return BaseUtils.checkUpiRegisteredApp(this.activity, str);
    }

    public boolean isUserRegistered(String str) {
        return BaseUtils.checkGpayCardsUpiRegistered(this.activity, str);
    }

    public boolean isAllowRotation() {
        return this.allowRotation;
    }
}
