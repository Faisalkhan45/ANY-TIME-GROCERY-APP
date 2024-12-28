package com.razorpay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.internal.ImagesContract;
import com.razorpay.AnalyticsProperty;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class AnalyticsUtil {
    static String BUILD_TYPE;
    static String FRAMEWORK;
    static String KEY_TYPE;
    static int MERCHANT_APP_BUILD;
    static CharSequence MERCHANT_APP_NAME;
    static CharSequence MERCHANT_APP_NAMESPACE;
    static CharSequence MERCHANT_APP_VERSION;
    private static boolean isAnalyticsInitialized = false;
    static String libraryType;
    private static String localOrderId;
    private static String localPaymentId;
    private static String sdkType = "standealone";
    private static String sdkVersion;
    private static int sdkVersionCode;
    static int sessionErroredApiCalls = 0;

    AnalyticsUtil() {
    }

    static void setup(Context context, String str, String str2, int i, String str3) {
        sdkType = str2;
        sdkVersionCode = i;
        sdkVersion = str3;
        setAppDetails(context, str);
        init(context, str);
    }

    private static void init(Context context, String str) {
        if (context == null) {
            throw new RuntimeException("Context not set");
        } else if (str != null) {
            U$$U$.a(context, sdkType, sdkVersion);
            U$$U$.b("merchant_key", (Object) str);
            U$$U$.b("merchant_package", (Object) context.getPackageName());
            J$_M_.a(context);
            isAnalyticsInitialized = true;
        } else {
            throw new RuntimeException("Merchant key not set");
        }
    }

    static String getBuildType() {
        return BUILD_TYPE;
    }

    static String getKeyType() {
        return KEY_TYPE;
    }

    static void trackEvent(AnalyticsEvent analyticsEvent) {
        U$$U$.a(analyticsEvent.getEventName());
    }

    static void postData() {
        if (isAnalyticsInitialized) {
            U$$U$.a();
        }
    }

    static void trackEvent(AnalyticsEvent analyticsEvent, Map<String, Object> map) {
        U$$U$.a(analyticsEvent.getEventName(), map);
    }

    static void trackEvent(AnalyticsEvent analyticsEvent, JSONObject jSONObject) {
        U$$U$.a(analyticsEvent.getEventName(), jSONObject);
    }

    static void addProperty(String str, AnalyticsProperty analyticsProperty) {
        if (analyticsProperty.scope == AnalyticsProperty.Scope.PAYMENT) {
            U$$U$.a(str, analyticsProperty.value);
        } else if (analyticsProperty.scope == AnalyticsProperty.Scope.ORDER) {
            U$$U$.b(str, analyticsProperty.value);
        }
    }

    static void addFilteredPropertiesFromPayload(JSONObject jSONObject) {
        U$$U$.a(jSONObject);
    }

    static String getAppDetail() {
        if (isAnalyticsInitialized) {
            return MERCHANT_APP_NAME + "-" + MERCHANT_APP_VERSION + "-" + MERCHANT_APP_BUILD;
        }
        return null;
    }

    static void trackPage(String str, String str2) {
        U$$U$.a(str, str2);
    }

    static void reportError(String str, String str2, String str3) {
        trackEvent(AnalyticsEvent.ERROR_LOGGED, getJSONErrorResponse(str, getErrorProperties(str2, str3)));
        if ((str2.equalsIgnoreCase("S0") || str2.equalsIgnoreCase("S1")) && sessionErroredApiCalls <= 0) {
            U$$U$.b(str2);
            sessionErroredApiCalls++;
        }
    }

    static void reportError(AbstractMethodError abstractMethodError, String str, String str2) {
        trackEvent(AnalyticsEvent.ERROR_LOGGED, getJSONErrorResponse((String) null, getErrorProperties(str, str2)));
        if ((str.equalsIgnoreCase("S0") || str.equalsIgnoreCase("S1")) && sessionErroredApiCalls <= 0) {
            U$$U$.b(str);
            sessionErroredApiCalls++;
        }
    }

    static Map<String, Object> getErrorProperties(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("severity", str);
        hashMap.put("unhandled", Boolean.TRUE);
        hashMap.put("source", "self");
        hashMap.put("stack", "");
        hashMap.put("message", str2);
        return hashMap;
    }

    static JSONObject getAnalyticsDataForCheckout(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("platform", "mobile_sdk");
            jSONObject.put("platform_version", sdkVersion);
            jSONObject.put("os", "android");
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            if (b.a(context)) {
                jSONObject.put("device", "tablet");
            } else {
                jSONObject.put("device", "mobile");
            }
        } catch (Exception e) {
            reportError(e.getLocalizedMessage(), "critical", e.getMessage());
        }
        return jSONObject;
    }

    static void trackPageLoadStart(String str) {
        trackEvent(isCheckoutUrl(str) ? AnalyticsEvent.CHECKOUT_PAGE_LOAD_START : AnalyticsEvent.PAGE_LOAD_START, getJSONResponse(getPageLoadStartProperties(str)));
    }

    static Map<String, Object> getPageLoadStartProperties(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(ImagesContract.URL, str);
        return hashMap;
    }

    static void trackPageLoadEnd(String str, long j) {
        trackEvent(isCheckoutUrl(str) ? AnalyticsEvent.CHECKOUT_PAGE_LOAD_FINISH : AnalyticsEvent.PAGE_LOAD_FINISH, getJSONResponse(getPageLoadEndProperties(str, j)));
    }

    static boolean isCheckoutUrl(String str) {
        if (str.indexOf(D$$l_.a().getCheckoutEndpoint()) == 0) {
            return true;
        }
        return false;
    }

    static Map<String, Object> getPageLoadEndProperties(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put(ImagesContract.URL, str);
        hashMap.put("page_load_time", Double.valueOf(((double) j) / 1.0E9d));
        return hashMap;
    }

    static void reset() {
        isAnalyticsInitialized = false;
        localPaymentId = null;
        localOrderId = null;
        U$$U$.b();
    }

    static void setAppDetails(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            MERCHANT_APP_NAME = returnUndefinedIfNull(packageInfo.applicationInfo.loadLabel(packageManager));
            MERCHANT_APP_VERSION = returnUndefinedIfNull(packageInfo.versionName);
            MERCHANT_APP_NAMESPACE = returnUndefinedIfNull(packageInfo.packageName);
            MERCHANT_APP_BUILD = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            reportError(e.getMessage(), "S0", e.getMessage());
        }
        BUILD_TYPE = BaseUtils.getAppBuildType(context);
        KEY_TYPE = getKeyType(str);
    }

    static String getKeyType(String str) {
        if (isNullOrEmpty(str)) {
            return null;
        }
        String substring = str.substring(0, 8);
        if (substring.equals("rzp_live")) {
            return "live";
        }
        if (substring.equals("rzp_test")) {
            return "test";
        }
        return null;
    }

    static boolean isNullOrEmpty(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return true;
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) <= ' ') {
            i++;
        }
        while (length > i && charSequence.charAt(length - 1) <= ' ') {
            length--;
        }
        if (length - i == 0) {
            return true;
        }
        return false;
    }

    static CharSequence returnUndefinedIfNull(CharSequence charSequence) {
        if (isNullOrEmpty(charSequence)) {
            return "undefined";
        }
        return charSequence;
    }

    static String getLocalPaymentId() {
        if (localPaymentId == null) {
            localPaymentId = getUniqueId();
        }
        return localPaymentId;
    }

    static String getLocalOrderId() {
        if (localOrderId == null) {
            localOrderId = getUniqueId();
        }
        return localOrderId;
    }

    static void refreshPaymentSession() {
        localPaymentId = getUniqueId();
        U$$U$.c();
    }

    static void refreshOrderSession() {
        localOrderId = getUniqueId();
        localPaymentId = getUniqueId();
        U$$U$.d();
        U$$U$.c();
    }

    static void setLocalOrderId(String str) {
        localOrderId = str;
    }

    static String getUniqueId() {
        return tobase62((System.currentTimeMillis() - 1388534400000L) * 1000000) + tobase62((long) Math.floor(Math.random() * 1.4776336E7d));
    }

    static String tobase62(long j) {
        String str = "";
        String[] split = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(str);
        while (j > 0) {
            str = String.valueOf(split[(int) (j % 62)]) + str;
            j = (long) Math.floor((double) (j / 62));
        }
        return str;
    }

    static void reportUncaughtException(Throwable th) {
        trackEvent(AnalyticsEvent.ERROR_LOGGED, getJSONResponse(getErrorProperties("S0", getStackTrace(th))));
    }

    static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    static void setFramework(String str) {
        FRAMEWORK = str;
    }

    static String getFramework() {
        return isNullOrEmpty(FRAMEWORK) ? "native" : FRAMEWORK;
    }

    public static void saveEventsToPreferences(Context context) {
        U$$U$.a(context);
    }

    public static JSONObject getExtraAnalyticsPayload() {
        return U$$U$.e();
    }

    public static JSONObject getJSONResponse(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("response", str);
            return jSONObject;
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    public static JSONObject getJSONResponse(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            try {
                jSONObject.put((String) next.getKey(), next.getValue());
            } catch (JSONException e) {
                reportError(e.getLocalizedMessage(), "S0", "Error adding analytics property " + ((String) next.getKey()) + " to JSONObject");
            }
        }
        return jSONObject;
    }

    public static JSONObject getJSONErrorResponse(String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("severity", map.get("severity"));
            jSONObject3.put("unhandled", map.get("unhandled"));
            jSONObject3.put("source", map.get("source"));
            JSONObject jSONObject4 = new JSONObject();
            if (str != null) {
                jSONObject4.put("stack", str);
            } else {
                jSONObject4.put("stack", "AbstractMethodError");
            }
            jSONObject4.put("message", map.get("message"));
            jSONObject4.put("tags", jSONObject3);
            jSONObject2.put("error", jSONObject4);
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            reportError(e.getLocalizedMessage(), "S0", "Error adding analytics property " + map.get("message") + " to JSONObject");
            return jSONObject;
        }
    }
}
