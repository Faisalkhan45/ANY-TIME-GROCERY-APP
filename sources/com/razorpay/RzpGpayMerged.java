package com.razorpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.core.app.NotificationCompat;
import com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient;
import com.google.android.apps.nbu.paisa.inapp.client.api.Wallet;
import com.google.android.apps.nbu.paisa.inapp.client.api.WalletUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.WalletConstants;
import com.google.common.net.HttpHeaders;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

class RzpGpayMerged implements OnCompleteListener<Boolean>, RzpPlugin {
    /* access modifiers changed from: private */
    public static int LOAD_PAYMENT_DATA_REQUEST_CODE = 1;
    /* access modifiers changed from: private */
    public JSONObject apiResponse;
    private Task<Boolean> cardsTask = null;
    /* access modifiers changed from: private */
    public boolean isUpiOnly = false;
    /* access modifiers changed from: private */
    public PaymentsClient mPaymentClient;
    private RzpPluginRegisterCallback registerCallback;
    private RzpInternalCallback rzpInternalCallback;
    private Task<Boolean> upiTask = null;

    RzpGpayMerged() {
    }

    public RzpPluginCompatibilityResponse isCompatible(String str, int i, String str2) {
        String str3;
        if (str.equalsIgnoreCase("custom") && i > 27) {
            return new RzpPluginCompatibilityResponse(true, (String) null);
        }
        if (str.equalsIgnoreCase(BuildConfig.SDK_TYPE) && i > 18) {
            return new RzpPluginCompatibilityResponse(true, (String) null);
        }
        if (str.equalsIgnoreCase("custom")) {
            str3 = "Razorpay's GooglePay plugin requires a min SDK Version 3.8.8 Please update.";
        } else if (str.equalsIgnoreCase(BuildConfig.SDK_TYPE)) {
            str3 = "Razorpay's GooglePay plugin requires a min SDK Version 1.5.6 Please update.";
        } else {
            str3 = "Incompatible Razorpay sdk version. Please update the base sdk";
        }
        return new RzpPluginCompatibilityResponse(false, str3);
    }

    public boolean doesHandlePayload(String str, JSONObject jSONObject, Activity activity) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("method") && jSONObject.getString("method").equalsIgnoreCase("upi") && jSONObject.getString("_[app]").equalsIgnoreCase("com.google.android.apps.nbu.paisa.user")) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return jSONObject != null && jSONObject.has("type") && jSONObject.getString("type").equalsIgnoreCase("application") && jSONObject.getString("application_name").equalsIgnoreCase("google_pay");
    }

    public void processPayment(String str, JSONObject jSONObject, Activity activity, RzpInternalCallback rzpInternalCallback2) {
        try {
            this.rzpInternalCallback = rzpInternalCallback2;
            this.mPaymentClient = Wallet.getPaymentsClient();
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PROCESS_PAYMENT_CALLED);
            if (jSONObject.has("type") && jSONObject.getString("type").equalsIgnoreCase("application")) {
                this.apiResponse = jSONObject;
                try {
                    this.mPaymentClient.loadPaymentData(activity, jSONObject.getJSONObject("request").getJSONArray("content").getJSONObject(0).toString(), LOAD_PAYMENT_DATA_REQUEST_CODE);
                } catch (Exception e) {
                    AnalyticsUtil.reportError(getClass().getName(), "S0", e.getMessage());
                    genericOnPaymentFailure("BAD_REQUEST", 1, "An internal error has occured");
                }
            } else if (jSONObject.has("url_data")) {
                this.isUpiOnly = true;
                try {
                    String obj = jSONObject.get("url_data").toString();
                    try {
                        this.apiResponse = RzpGpayUtilMerged.getUpiData(obj);
                        this.mPaymentClient.loadPaymentData(activity, RzpGpayUtilMerged.getPaymentRequestData(obj, jSONObject), LOAD_PAYMENT_DATA_REQUEST_CODE);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An internal error has occurred");
                    }
                } catch (Exception e3) {
                    AnalyticsUtil.reportError(getClass().getName(), "error:exception", e3.getMessage());
                    genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An internal error has occurred");
                }
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.CONTENT_TYPE, ShareTarget.ENCODING_TYPE_URL_ENCODED);
                M$_3_.a("https://api.razorpay.com/v1/payments/create/ajax", RzpGpayUtilMerged.makeApiPayload(jSONObject), hashMap, new h(this, activity, jSONObject));
            }
        } catch (Exception e4) {
            AnalyticsUtil.reportError(getClass().getName(), "error:exception", e4.getMessage());
            genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An internal error has occurred.");
        }
    }

    public void onActivityResult(String str, int i, int i2, Intent intent) {
        if (i == LOAD_PAYMENT_DATA_REQUEST_CODE) {
            switch (i2) {
                case -1:
                    AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PAYMENT_CALLBACK_SUCCESS);
                    processPaymentResponse(WalletUtils.getPaymentDataFromIntent(intent));
                    return;
                case 0:
                    AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PAYMENT_CALLBACK_CANCELLED);
                    genericOnPaymentFailure("BAD_REQUEST_ERROR", 0, "Payment canceled.");
                    return;
                case 1:
                    handleResultStatusCode(intent.getIntExtra("errorCode", 8));
                    return;
                default:
                    return;
            }
        }
    }

    private void handleResultStatusCode(int i) {
        switch (i) {
            case WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR /*405*/:
                genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "There is a problem with merchant's account.");
                return;
            case WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR /*409*/:
                genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "There is a problem with your Google Pay account.");
                return;
            default:
                genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "An internal error has occurred");
                return;
        }
    }

    public void isRegistered(Context context, RzpPluginRegisterCallback rzpPluginRegisterCallback) {
        this.registerCallback = rzpPluginRegisterCallback;
        AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_CHECK_REGISTER_CALLED);
        try {
            this.mPaymentClient = Wallet.getPaymentsClient();
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PAYMENT_IS_REGISTERED_CALLED);
            this.upiTask = this.mPaymentClient.isReadyToPay(context, ((JSONObject) Objects.requireNonNull(RzpGpayUtilMerged.getIsReadyToPayRequest())).toString());
            this.cardsTask = this.mPaymentClient.isReadyToPay(context, ((JSONObject) Objects.requireNonNull(RzpGpayUtilMerged.getCardsIsReadyToPayRequest())).toString());
        } catch (NoSuchAlgorithmException e) {
            AnalyticsUtil.reportError(getClass().getName(), "error:exception", e.getMessage());
        }
        ((Task) Objects.requireNonNull(this.cardsTask)).addOnCompleteListener(this);
        ((Task) Objects.requireNonNull(this.upiTask)).addOnCompleteListener(this);
    }

    public void isRegistered(Context context) {
        Task task;
        this.mPaymentClient = Wallet.getPaymentsClient();
        AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_CHECK_REGISTER_CALLED);
        Task task2 = null;
        try {
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_PAYMENT_IS_REGISTERED_CALLED);
            task = this.mPaymentClient.isReadyToPay(context, ((JSONObject) Objects.requireNonNull(RzpGpayUtilMerged.getIsReadyToPayRequest())).toString());
            try {
                task2 = this.mPaymentClient.isReadyToPay(context, ((JSONObject) Objects.requireNonNull(RzpGpayUtilMerged.getCardsIsReadyToPayRequest())).toString());
            } catch (NoSuchAlgorithmException e) {
                e = e;
                AnalyticsUtil.reportError(getClass().getName(), "error:exception", e.getMessage());
                ((Task) Objects.requireNonNull(task2)).addOnCompleteListener(new i(this));
                ((Task) Objects.requireNonNull(task)).addOnCompleteListener(new j(this));
            }
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            task = null;
            AnalyticsUtil.reportError(getClass().getName(), "error:exception", e.getMessage());
            ((Task) Objects.requireNonNull(task2)).addOnCompleteListener(new i(this));
            ((Task) Objects.requireNonNull(task)).addOnCompleteListener(new j(this));
        }
        ((Task) Objects.requireNonNull(task2)).addOnCompleteListener(new i(this));
        ((Task) Objects.requireNonNull(task)).addOnCompleteListener(new j(this));
    }

    private void processPaymentResponse(String str) {
        AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_VERIFY_PAYMENT_CALLED);
        if (verifyPaymentResponse(str)) {
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_VERIFY_PAYMENT_SUCCESS_CALLED);
            genericOnPaymentSuccess();
            return;
        }
        AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_VERIFY_PAYMENT_ERROR_CALLED);
        genericOnPaymentFailure("BAD_REQUEST_ERROR", 1, "Payment was not successful.");
    }

    private static boolean verifyPaymentResponse(String str) {
        try {
            String string = new JSONObject(str).getJSONObject("paymentMethodData").getJSONObject("tokenizationData").getString("token");
            if (string == null) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_VERIFY_PAYMENT_EXCEPTION);
                return false;
            }
            String string2 = new JSONObject(new JSONObject(string).getString("signedMessage")).getJSONObject("paymentMethodDetails").getString(NotificationCompat.CATEGORY_STATUS);
            if (string2 == null || !string2.equals("SUCCESS")) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            AnalyticsUtil.trackEvent(AnalyticsEvent.GOOGLEPAY_VERIFY_PAYMENT_EXCEPTION);
            return false;
        }
    }

    private void genericOnPaymentSuccess() {
        if (this.isUpiOnly) {
            this.rzpInternalCallback.onPaymentSuccess(RzpGpayUtilMerged.makeExternalSDKPayload(this.apiResponse));
        } else {
            this.rzpInternalCallback.onPaymentSuccess(RzpGpayUtilMerged.makeMergedExternalSDKPayload(this.apiResponse));
        }
    }

    /* access modifiers changed from: private */
    public void genericOnPaymentFailure(String str, int i, String str2) {
        if (this.isUpiOnly) {
            this.rzpInternalCallback.onPaymentError(5, RzpGpayUtilMerged.makeErrorPayload(str, str2));
        } else {
            this.rzpInternalCallback.onPaymentError(5, RzpGpayUtilMerged.makeMergedExternalSDKErrorPayload(this.apiResponse, i, str2));
        }
    }

    public void onComplete(Task<Boolean> task) {
        boolean booleanValue = task.getResult().booleanValue();
        if (task.equals(this.cardsTask)) {
            BaseUtils.setIsGpayCardsUpiRegistered(booleanValue);
            this.registerCallback.onResponse(booleanValue);
            return;
        }
        BaseUtils.setCompatibleWithGooglePay(booleanValue);
    }
}
