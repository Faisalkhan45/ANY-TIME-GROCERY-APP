package com.razorpay;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.razorpay.AnalyticsProperty;
import com.razorpay.OpinionatedSoln;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Checkout extends Fragment {
    static final String EVENT_CALLBACK_DNE = "dne";
    static final String EVENT_CALLBACK_THREW_ERROR = "threw_error";
    static final int EXTERNAL_WALLET = 4;
    public static final int INCOMPATIBLE_PLUGIN = 7;
    static final int INTEGRATION_ONE = 1;
    static final int INTEGRATION_THREE = 3;
    static final int INTEGRATION_TWO = 2;
    public static final int INVALID_OPTIONS = 3;
    public static final int NETWORK_ERROR = 2;
    public static final int PAYMENT_CANCELED = 0;
    static final int PAYMENT_SUCCESS = 1;
    public static final int RZP_REQUEST_CODE = 62442;
    public static final int TLS_ERROR = 6;
    private static PaymentData paymentData;
    private static long sPreloadAbortDuration;
    /* access modifiers changed from: private */
    public static long sPreloadCompleteDuration;
    /* access modifiers changed from: private */
    public static boolean sPreloadFailed;
    /* access modifiers changed from: private */
    public static WebView sPreloadView;
    private int checkoutImage;
    private boolean disableFullScreen;
    private ExternalWalletListener externalWalletListenerFromClass = null;
    private boolean isMethodOverridden;
    private String key;
    private Activity merchantActivity;
    private String merchantClassName;
    private JSONObject options;
    private PaymentResultListener paymentResultListenerFromClass = null;
    private PaymentResultWithDataListener paymentResultWithDataListenerFromClass = null;

    @Deprecated
    public final void setPublicKey(String str) {
        this.key = str;
    }

    public final void setImage(int i) {
        this.checkoutImage = i;
    }

    public final void setKeyID(String str) {
        setPublicKey(str);
    }

    public final void setFullScreenDisable(boolean z) {
        this.disableFullScreen = z;
    }

    public final void open(Activity activity, JSONObject jSONObject) {
        if (OpinionatedSoln.INSTANCE.getBuildConfigValue(activity, "DEBUG") == null) {
            openInternal(activity, jSONObject);
        } else if (!OpinionatedSoln.INSTANCE.getAlertShownForStatus()) {
            sdkCheckIntegrationInternal(activity, new g__v_(this, activity, jSONObject));
        } else {
            openInternal(activity, jSONObject);
        }
    }

    /* access modifiers changed from: private */
    public void openInternal(Activity activity, JSONObject jSONObject) {
        if (TextUtils.isEmpty(this.key)) {
            this.key = BaseUtils.getKeyId(activity);
        }
        g$_H$.getAdvertisingIdFromUtil(activity);
        if (TextUtils.isEmpty(this.key)) {
            throw new RuntimeException("Please set your Razorpay API key in AndroidManifest.xml");
        } else if (jSONObject == null || jSONObject.length() == 0) {
            throw new RuntimeException("Checkout options cannot be null or empty");
        } else {
            try {
                jSONObject.put("key", this.key);
            } catch (JSONException e) {
                AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
            }
            abortPreloadIfRequired();
            this.options = jSONObject;
            this.merchantClassName = activity.getClass().getName();
            this.merchantActivity = activity;
            try {
                activity.getFragmentManager().beginTransaction().add(this, (String) null).commitAllowingStateLoss();
            } catch (IllegalStateException e2) {
                AnalyticsUtil.reportError("Checkout", "S2", e2.getMessage());
            }
        }
    }

    public static void preload(Context context) {
        sPreloadCompleteDuration = 0;
        sPreloadAbortDuration = 0;
        sPreloadFailed = false;
        Context applicationContext = context.getApplicationContext();
        WebView webView = new WebView(applicationContext);
        sPreloadView = webView;
        BaseUtils.setWebViewSettings(applicationContext, webView, false);
        sPreloadView.setWebViewClient(new WebViewClient() {
            long pageStartAt;

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                this.pageStartAt = System.nanoTime();
                if (Checkout.sPreloadView == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("error_location", "Checkout->Preload()->onPageStarted");
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_UNEXPECTED_NULL, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
                    return;
                }
                Checkout.sPreloadView.setTag(Long.valueOf(this.pageStartAt));
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Checkout.failPreload();
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                Checkout.failPreload();
            }

            public void onPageFinished(WebView webView, String str) {
                long nanoTime = System.nanoTime();
                if (!Checkout.sPreloadFailed) {
                    long unused = Checkout.sPreloadCompleteDuration = nanoTime - this.pageStartAt;
                    new StringBuilder("Preload finished in ").append(BaseUtils.nanoTimeToSecondsString(Checkout.sPreloadCompleteDuration, 2)).append(" sec.");
                }
                Checkout.destroyPreloadView();
            }
        });
        sPreloadView.setWebChromeClient(new l__d$());
        sPreloadView.loadUrl("https://api.razorpay.com/v1/checkout/public");
    }

    /* access modifiers changed from: private */
    public static void failPreload() {
        sPreloadFailed = true;
    }

    /* access modifiers changed from: private */
    public static void destroyPreloadView() {
        try {
            sPreloadView.stopLoading();
        } catch (Exception e) {
        }
        sPreloadView = null;
    }

    private static void abortPreloadIfRequired() {
        long nanoTime = System.nanoTime();
        try {
            WebView webView = sPreloadView;
            if (webView != null) {
                sPreloadAbortDuration = nanoTime - ((Long) webView.getTag()).longValue();
                new StringBuilder("Preload aborted in ").append(BaseUtils.nanoTimeToSecondsString(sPreloadAbortDuration, 2)).append(" sec.");
            }
        } catch (Exception e) {
        }
        destroyPreloadView();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.options != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getActivity().getPackageName(), "com.razorpay.CheckoutActivity"));
            long j = sPreloadCompleteDuration;
            if (j > 0) {
                intent.putExtra("PRELOAD_COMPLETE_DURATION", j);
            } else {
                long j2 = sPreloadAbortDuration;
                if (j2 > 0) {
                    intent.putExtra("PRELOAD_ABORT_DURATION", j2);
                }
            }
            intent.putExtra("OPTIONS", this.options.toString());
            intent.putExtra("IMAGE", this.checkoutImage);
            intent.putExtra("DISABLE_FULL_SCREEN", this.disableFullScreen);
            this.options = null;
            startActivityForResult(intent, RZP_REQUEST_CODE);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 62442) {
            if (this.merchantActivity == null) {
                this.merchantActivity = getActivity();
            }
            this.merchantClassName = this.merchantActivity.getClass().getName();
            String paymentResult = getPaymentResult(intent);
            if (paymentResult == null || TextUtils.isEmpty(paymentResult)) {
                paymentResult = BaseUtils.getGenericPaymentErrorResponse("Payment Error", BaseUtils.getInstance().getMetadata());
            } else if (paymentResult.contains("cancelled") && !paymentResult.contains("error")) {
                paymentResult = BaseUtils.getPaymentCancelledResponse(BaseUtils.getInstance().getMetadata());
            }
            trackOnActivityResultEvent(i2, paymentResult);
            createPaymentData(this.merchantActivity, paymentResult);
            if (i2 == 1) {
                handleOnSuccess();
            } else if (i2 == 4) {
                handleExternalWalletSelected();
            } else {
                handleOnError(i2, paymentResult);
            }
            try {
                getActivity().getFragmentManager().beginTransaction().remove(this).commit();
            } catch (Exception e) {
                AnalyticsUtil.reportError(e.getMessage(), "S1", e.getMessage());
            }
        }
    }

    private void handleExternalWalletSelected() {
        ExternalWalletListener externalWalletListener = this.externalWalletListenerFromClass;
        if (externalWalletListener != null) {
            externalWalletListener.onExternalWalletSelected(paymentData.getExternalWallet(), paymentData);
        } else if (getActivity() instanceof ExternalWalletListener) {
            try {
                ExternalWalletListener externalWalletListener2 = (ExternalWalletListener) getActivity();
                String externalWallet = paymentData.getExternalWallet();
                if (!TextUtils.isEmpty(externalWallet)) {
                    externalWalletListener2.onExternalWalletSelected(externalWallet, paymentData);
                    AnalyticsUtil.trackEvent(AnalyticsEvent.MERCHANT_EXTERNAL_WALLET_SELECTED_CALLED);
                    AnalyticsUtil.postData();
                }
            } catch (Exception e) {
                handleMerchantCallbackError(this.merchantActivity, 4, EVENT_CALLBACK_THREW_ERROR, e);
            }
        } else {
            handleMerchantCallbackError(this.merchantActivity, 4, EVENT_CALLBACK_DNE, new Exception());
        }
    }

    private void handleOnError(int i, String str) {
        this.isMethodOverridden = true;
        onError(i, str);
        if (this.isMethodOverridden) {
            trackIntegrationType(i, 1);
        }
    }

    private void handleOnSuccess() {
        String paymentId = paymentData.getPaymentId();
        if (paymentId != null) {
            this.isMethodOverridden = true;
            onSuccess(paymentId);
        }
        if (this.isMethodOverridden) {
            trackIntegrationType(1, 1);
        }
    }

    public void onSuccess(String str) {
        this.isMethodOverridden = false;
        if (!handleOnSuccessViaInterface(str)) {
            handleOnSuccessViaReflection(str);
        }
    }

    public void onError(int i, String str) {
        this.isMethodOverridden = false;
        if (!handleOnErrorViaInterface(i, str)) {
            handleOnErrorViaReflection(i, str);
        }
    }

    private void handleOnErrorViaReflection(int i, String str) {
        Method method;
        try {
            method = getMerchantClassMethod("onPaymentError", Integer.TYPE, String.class);
        } catch (Exception e) {
            handleMerchantCallbackError(this.merchantActivity, i, EVENT_CALLBACK_DNE, e);
            method = null;
        }
        try {
            invokeMethod(method, Integer.valueOf(i), str);
            trackIntegrationType(i, 2);
        } catch (Exception e2) {
            handleMerchantCallbackError(this.merchantActivity, i, EVENT_CALLBACK_THREW_ERROR, e2);
        }
    }

    private boolean handleOnErrorViaInterface(int i, String str) {
        PaymentResultListener paymentResultListener = this.paymentResultListenerFromClass;
        if (paymentResultListener != null) {
            paymentResultListener.onPaymentError(i, str);
            return true;
        } else if (getActivity() instanceof PaymentResultListener) {
            try {
                ((PaymentResultListener) getActivity()).onPaymentError(i, str);
                trackIntegrationType(i, 3);
            } catch (Exception e) {
                handleMerchantCallbackError(this.merchantActivity, i, EVENT_CALLBACK_THREW_ERROR, e);
            }
            return true;
        } else {
            PaymentResultWithDataListener paymentResultWithDataListener = this.paymentResultWithDataListenerFromClass;
            if (paymentResultWithDataListener != null) {
                paymentResultWithDataListener.onPaymentError(i, str, paymentData);
                return true;
            } else if (!(getActivity() instanceof PaymentResultWithDataListener)) {
                return false;
            } else {
                try {
                    ((PaymentResultWithDataListener) getActivity()).onPaymentError(i, str, paymentData);
                    trackIntegrationType(i, 3);
                } catch (Exception e2) {
                    handleMerchantCallbackError(this.merchantActivity, i, EVENT_CALLBACK_THREW_ERROR, e2);
                }
                return true;
            }
        }
    }

    private boolean handleOnSuccessViaInterface(String str) {
        PaymentResultListener paymentResultListener = this.paymentResultListenerFromClass;
        if (paymentResultListener != null) {
            paymentResultListener.onPaymentSuccess(str);
            return true;
        } else if (getActivity() instanceof PaymentResultListener) {
            try {
                ((PaymentResultListener) getActivity()).onPaymentSuccess(str);
                trackIntegrationType(1, 3);
            } catch (Exception e) {
                handleMerchantCallbackError(this.merchantActivity, 1, EVENT_CALLBACK_THREW_ERROR, e);
            }
            return true;
        } else {
            PaymentResultWithDataListener paymentResultWithDataListener = this.paymentResultWithDataListenerFromClass;
            if (paymentResultWithDataListener != null) {
                paymentResultWithDataListener.onPaymentSuccess(str, paymentData);
                return true;
            } else if (!(getActivity() instanceof PaymentResultWithDataListener)) {
                return false;
            } else {
                try {
                    ((PaymentResultWithDataListener) getActivity()).onPaymentSuccess(str, paymentData);
                    trackIntegrationType(1, 3);
                } catch (Exception e2) {
                    handleMerchantCallbackError(this.merchantActivity, 1, EVENT_CALLBACK_THREW_ERROR, e2);
                }
                return true;
            }
        }
    }

    private void handleOnSuccessViaReflection(String str) {
        Method method;
        try {
            method = getMerchantClassMethod("onPaymentSuccess", String.class);
        } catch (Exception e) {
            handleMerchantCallbackError(this.merchantActivity, 1, EVENT_CALLBACK_DNE, e);
            method = null;
        }
        try {
            invokeMethod(method, str);
            trackIntegrationType(1, 2);
        } catch (Exception e2) {
            handleMerchantCallbackError(this.merchantActivity, 1, EVENT_CALLBACK_THREW_ERROR, e2);
        }
    }

    private void invokeMethod(Method method, Object... objArr) {
        if (method != null) {
            method.invoke(this.merchantActivity, objArr);
        }
    }

    private Method getMerchantClassMethod(String str, Class... clsArr) {
        return Class.forName(this.merchantClassName).getMethod(str, clsArr);
    }

    private static void trackIntegrationType(int i, int i2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("integration_type", Integer.toString(i2));
            if (i == 1) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.MERCHANT_ON_SUCCESS_CALLED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
            } else {
                AnalyticsUtil.trackEvent(AnalyticsEvent.MERCHANT_ON_ERROR_CALLED, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
            }
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
        }
    }

    private static void trackOnActivityResultEvent(int i, String str) {
        try {
            AnalyticsUtil.addProperty("onActivityResult result", new AnalyticsProperty(str, AnalyticsProperty.Scope.ORDER));
            AnalyticsUtil.addProperty("onActivityResult resultCode", new AnalyticsProperty(String.valueOf(i), AnalyticsProperty.Scope.ORDER));
            if (i == 1) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CALLING_ON_SUCCESS);
            } else if (i == 4) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CALLING_EXTERNAL_WALLET_SELECTED);
            } else {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CALLING_ON_ERROR);
            }
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
        }
    }

    private static void handleMerchantCallbackError(Activity activity, int i, String str, Exception exc) {
        String str2;
        String str3;
        if (i == 1) {
            str3 = "onPaymentSuccess";
            str2 = "success";
        } else if (i == 4) {
            str3 = "onExternalWalletSelected";
            str2 = "redirected";
        } else {
            str3 = "onPaymentError";
            str2 = "error";
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("event_details", exc.getMessage());
            hashMap.put("event_type", exc.getMessage());
            hashMap.put("payment_status", str2);
            AnalyticsUtil.trackEvent(AnalyticsEvent.HANDOVER_ERROR, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
        }
        if (str.equals(EVENT_CALLBACK_DNE)) {
            if (i == 4) {
                Toast.makeText(activity, "Error: ExternalWalletListener probably not implemented in your activity", 0).show();
            } else {
                Toast.makeText(activity, "Error: " + str3 + " probably not implemented in your activity", 0).show();
            }
        } else if (str.equals(EVENT_CALLBACK_THREW_ERROR)) {
            Toast.makeText(activity, "Your " + str3 + " method is throwing an error. Wrap the entire code of the method inside a try catch.", 0).show();
        }
    }

    private static void createPaymentData(Activity activity, String str) {
        PaymentData paymentData2 = new PaymentData();
        paymentData = paymentData2;
        paymentData2.setUserContact(CheckoutUtils.b(activity));
        paymentData.setUserEmail(CheckoutUtils.a((Context) activity));
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optJSONObject("error") != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("error", jSONObject.getJSONObject("error"));
                JSONObject jSONObject3 = jSONObject.getJSONObject("error").getJSONObject("metadata");
                if (jSONObject3.has("payment_id")) {
                    paymentData.setPaymentId(jSONObject3.getString("payment_id"));
                }
                if (jSONObject3.has("order_id")) {
                    paymentData.setOrderId(jSONObject3.getString("order_id"));
                }
                paymentData.setData(jSONObject2);
            } else {
                paymentData.setData(jSONObject);
            }
            if (jSONObject.has("razorpay_payment_id")) {
                paymentData.setPaymentId(jSONObject.getString("razorpay_payment_id"));
            }
            if (jSONObject.has("razorpay_order_id")) {
                paymentData.setOrderId(jSONObject.getString("razorpay_order_id"));
            }
            if (jSONObject.has("razorpay_signature")) {
                paymentData.setSignature(jSONObject.getString("razorpay_signature"));
            }
            if (jSONObject.has("external_wallet")) {
                paymentData.setExternalWallet(jSONObject.getString("external_wallet"));
            }
        } catch (JSONException e) {
            JSONObject jSONObject4 = new JSONObject();
            JSONObject jSONObject5 = new JSONObject();
            try {
                jSONObject5.put("code", "RESPONSE");
                jSONObject5.put("description", str);
                jSONObject4.put("error", jSONObject5);
            } catch (JSONException e2) {
            }
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
        }
    }

    @Deprecated
    public static void handleActivityResult(Activity activity, int i, int i2, Intent intent, PaymentResultWithDataListener paymentResultWithDataListener, ExternalWalletListener externalWalletListener) {
        if (i == 62442) {
            String paymentResult = getPaymentResult(intent);
            if (paymentResult == null || TextUtils.isEmpty(paymentResult)) {
                paymentResult = BaseUtils.getGenericPaymentErrorResponse("Payment Error", BaseUtils.getInstance().getMetadata());
            } else if (paymentResult.contains("cancelled") && !paymentResult.contains("error")) {
                paymentResult = BaseUtils.getPaymentCancelledResponse(BaseUtils.getInstance().getMetadata());
            }
            trackOnActivityResultEvent(i2, paymentResult);
            createPaymentData(activity, paymentResult);
            if (i2 == 1) {
                try {
                    paymentResultWithDataListener.onPaymentSuccess(paymentData.getPaymentId(), paymentData);
                } catch (Exception e) {
                    handleMerchantCallbackError(activity, i2, EVENT_CALLBACK_THREW_ERROR, e);
                }
            } else if (i2 != 4) {
                try {
                    paymentResultWithDataListener.onPaymentError(i2, paymentResult, paymentData);
                } catch (Exception e2) {
                    handleMerchantCallbackError(activity, i2, EVENT_CALLBACK_THREW_ERROR, e2);
                }
            } else if (externalWalletListener != null) {
                try {
                    externalWalletListener.onExternalWalletSelected(paymentData.getExternalWallet(), paymentData);
                } catch (Exception e3) {
                    handleMerchantCallbackError(activity, i2, EVENT_CALLBACK_THREW_ERROR, e3);
                }
            }
        }
    }

    public void merchantActivityResult(Activity activity, int i, int i2, Intent intent, PaymentResultWithDataListener paymentResultWithDataListener, ExternalWalletListener externalWalletListener) {
        this.merchantActivity = activity;
        this.paymentResultWithDataListenerFromClass = paymentResultWithDataListener;
        this.externalWalletListenerFromClass = externalWalletListener;
        onActivityResult(i, i2, intent);
    }

    public void merchantActivityResult(Activity activity, int i, int i2, Intent intent, PaymentResultListener paymentResultListener, ExternalWalletListener externalWalletListener) {
        this.merchantActivity = activity;
        this.paymentResultListenerFromClass = paymentResultListener;
        this.externalWalletListenerFromClass = externalWalletListener;
        onActivityResult(i, i2, intent);
    }

    public static void clearUserData(Context context) {
        CheckoutUtils.b(context, (String) null);
        CheckoutUtils.a(context, (String) null);
        f$_G$.a(context, (String) null);
        CookieManager.getInstance().setCookie("https://api.razorpay.com", "razorpay_api_session=");
    }

    private static String getPaymentResult(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        return extras.getString("RESULT");
    }

    public static void sdkCheckIntegration(Activity activity) {
        OpinionatedSoln.INSTANCE.integrationStatusCheck(activity);
    }

    private void sdkCheckIntegrationInternal(Activity activity, OpinionatedSoln.DismissCallback dismissCallback) {
        OpinionatedSoln.INSTANCE.integrationStatusCheck(activity, dismissCallback);
    }
}
