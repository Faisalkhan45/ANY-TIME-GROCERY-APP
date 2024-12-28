package com.razorpay;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.razorpay.AnalyticsProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Lumberjack */
final class U$$U$ {
    private static String a = Build.MANUFACTURER;
    private static String b = Build.MODEL;
    private static String c = Build.DEVICE;
    private static String d;
    private static String e;
    private static boolean f;
    private static boolean g;
    private static boolean h;
    private static float i;
    private static int j;
    private static int k;
    private static JSONObject l;
    private static JSONObject m;
    private static boolean n = false;
    private static String o = "standalone";
    private static String p;
    private static ArrayList<JSONObject> q = new ArrayList<>();
    private static Map<String, Object> r;
    private static Map<String, Object> s;

    private static void b(JSONObject jSONObject) {
        if (!n) {
            q.add(jSONObject);
            return;
        }
        try {
            JSONObject c2 = c(jSONObject);
            synchronized (l) {
                l.getJSONArray("events").put(c2);
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", e2.getMessage());
        }
    }

    static void a(String str, Object obj) {
        r.put(str, obj);
    }

    static void b(String str, Object obj) {
        s.put(str, obj);
    }

    private static JSONObject c(String str) {
        try {
            return new JSONObject("{event: '" + str + "',timestamp: '" + String.valueOf(System.currentTimeMillis() / 1000) + "'}");
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", "Error in creating base for trackEvent");
            return null;
        }
    }

    static void a(String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            try {
                jSONObject.put((String) next.getKey(), next.getValue());
            } catch (JSONException e2) {
                AnalyticsUtil.reportError(e2.getMessage(), "S0", "Error adding analytics property " + ((String) next.getKey()) + " to JSONObject");
            }
        }
        a(str, jSONObject);
    }

    static void a(String str, JSONObject jSONObject) {
        try {
            JSONObject c2 = c(str);
            if (c2 == null) {
                c2 = new JSONObject();
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            jSONObject.put("local_order_id", AnalyticsUtil.getLocalOrderId());
            jSONObject.put("sdk_session_id", AnalyticsUtil.getLocalOrderId());
            jSONObject.put("local_payment_id", AnalyticsUtil.getLocalPaymentId());
            c2.put("properties", jSONObject);
            b(c2);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", "Error in adding properties to base json for event tracking");
        }
    }

    static void a(String str) {
        a(str, new JSONObject());
    }

    static void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(ImagesContract.URL, str2);
        a("Viewed " + str + " Page", (Map<String, Object>) hashMap);
    }

    private static JSONObject c(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        try {
            if (jSONObject.has("properties")) {
                jSONObject2 = jSONObject.getJSONObject("properties");
            }
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            jSONObject2.put("merchant_app_name", AnalyticsUtil.MERCHANT_APP_NAME);
            jSONObject2.put("merchant_app_version", AnalyticsUtil.MERCHANT_APP_VERSION);
            jSONObject2.put("merchant_app_build", AnalyticsUtil.MERCHANT_APP_BUILD);
            jSONObject2.put("platform", "mobile_sdk");
            jSONObject2.put("platform_version", p);
            jSONObject2.put("os", "android");
            jSONObject2.put("os_version", Build.VERSION.RELEASE);
            jSONObject2.put("library", AnalyticsUtil.libraryType);
            for (Map.Entry next : r.entrySet()) {
                try {
                    jSONObject2.put((String) next.getKey(), next.getValue());
                } catch (Exception e2) {
                    AnalyticsUtil.reportError(e2.getMessage(), "S0", "Error adding analytics property " + ((String) next.getKey()) + " to JSONObject");
                }
            }
            for (Map.Entry next2 : s.entrySet()) {
                try {
                    jSONObject2.put((String) next2.getKey(), next2.getValue());
                } catch (Exception e3) {
                    AnalyticsUtil.reportError(e3.getMessage(), "S0", "Error adding analytics property " + ((String) next2.getKey()) + " to JSONObject");
                }
            }
            jSONObject.put("properties", jSONObject2);
        } catch (Exception e4) {
        }
        return jSONObject;
    }

    static void a() {
        synchronized (l) {
            JSONObject e2 = e(l);
            l = e2;
            d(e2);
        }
        f();
    }

    private static void d(JSONObject jSONObject) {
        if (D$$l_.a().isLumberJackEnabled().booleanValue()) {
            HashMap hashMap = new HashMap();
            hashMap.put("x-identifier", D$$l_.a().getLumberjackSdkIdentifier());
            hashMap.put(HttpHeaders.CONTENT_TYPE, "application/json");
            M$_3_.a(D$$l_.a().getLumberjackEndpoint(), jSONObject.toString(), hashMap, new n$$t$());
        }
    }

    private static void f() {
        try {
            JSONObject jSONObject = l;
            if (jSONObject != null) {
                synchronized (jSONObject) {
                    l.put("events", new JSONArray());
                }
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", e2.getMessage());
        }
    }

    static void a(Context context, String str, String str2) {
        o = str;
        p = str2;
        try {
            d = BaseUtils.getCellularNetworkType(context);
            e = BaseUtils.getCellularNetworkProviderName(context);
            switch (M$_J_.a[BaseUtils.getDataNetworkType(context).ordinal()]) {
                case 1:
                    h = true;
                    break;
                case 2:
                    f = true;
                    break;
                case 3:
                    g = true;
                    break;
            }
            Display defaultDisplay = ((WindowManager) BaseUtils.getSystemService(context, "window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            i = displayMetrics.density;
            k = displayMetrics.heightPixels;
            j = displayMetrics.widthPixels;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", D$$l_.a().getLumberjackKey());
            jSONObject.put("events", new JSONArray());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("mode", AnalyticsUtil.getKeyType());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("id", BaseConfig.getAdvertisingId(context));
            jSONObject3.put("manufacturer", a);
            jSONObject3.put("model", b);
            jSONObject3.put("name", c);
            jSONObject3.put("type", "phone");
            jSONObject3.put("version", "Android" + Build.VERSION.RELEASE);
            jSONObject3.put(a, Build.MANUFACTURER);
            jSONObject3.put(b, Build.MODEL);
            jSONObject3.put("device_size", BaseUtils.getDisplayWidth(context) + "w X " + BaseUtils.getDisplayHeight(context) + "h");
            jSONObject3.put("device_resolution", BaseUtils.getDisplayResolution(context));
            jSONObject2.put("device", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("version", p);
            jSONObject4.put("platform", "android");
            jSONObject4.put("type", o);
            jSONObject4.put("framework", AnalyticsUtil.getFramework());
            jSONObject4.put("name", o + "_android_" + AnalyticsUtil.getFramework());
            jSONObject2.put("sdk", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("bluetooth", g);
            jSONObject5.put("carrier", e);
            jSONObject5.put("cellular", f);
            jSONObject5.put("cellular_network_type", d);
            jSONObject5.put("wifi", h);
            jSONObject5.put("carrier_network", BaseUtils.getCarrierOperatorName(context));
            jSONObject5.put("network_type", BaseUtils.getNetworkType(context));
            jSONObject5.put("ip_address", BaseUtils.ipAddress);
            jSONObject5.put("is_roming", BaseUtils.isNetworkRoaming(context));
            Map<String, String> deviceAttributes = BaseUtils.getDeviceAttributes(context);
            jSONObject5.put("device_Id", deviceAttributes.get("device_Id"));
            String str3 = a;
            jSONObject5.put(str3, deviceAttributes.get(str3));
            String str4 = b;
            jSONObject5.put(str4, deviceAttributes.get(str4));
            jSONObject2.put("network", jSONObject5);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("density", (double) i);
            jSONObject6.put("width", j);
            jSONObject6.put("height", k);
            jSONObject2.put("screen", jSONObject6);
            jSONObject2.put("locale", BaseUtils.getLocale());
            jSONObject2.put("timezone", AnalyticsUtil.returnUndefinedIfNull(TimeZone.getDefault().getID()));
            jSONObject2.put("framework", o + "_android_" + AnalyticsUtil.getFramework());
            jSONObject2.put("user_agent", AnalyticsUtil.returnUndefinedIfNull(System.getProperty("http.agent")));
            jSONObject2.put("sdk_session_id", AnalyticsUtil.getLocalOrderId());
            jSONObject2.put("local_order_id", AnalyticsUtil.getLocalOrderId());
            jSONObject2.put("webview_user_agent", BaseUtils.getWebViewUserAgent(context));
            m = jSONObject2;
            jSONObject.put("context", jSONObject2);
            l = jSONObject;
            f(i());
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", "Error in creating BaseImportJSON");
            l = new JSONObject();
        }
        n = true;
        g();
        String a2 = l.a(context, "SavedEventsData", str2);
        if (a2 != null && a2.length() != 0) {
            try {
                d(new JSONObject(a2));
            } catch (Exception e3) {
                AnalyticsUtil.reportError(e3.getMessage(), "S1", e3.getMessage());
            } catch (Throwable th) {
                l.b(context, "SavedEventsData", (String) null);
                throw th;
            }
            l.b(context, "SavedEventsData", (String) null);
        }
    }

    private static void g() {
        Iterator<JSONObject> it = q.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
        h();
    }

    private static void h() {
        q = new ArrayList<>();
    }

    private static void a(JSONObject jSONObject, String str, AnalyticsProperty.Scope scope) {
        try {
            Object a2 = a(jSONObject, str);
            if (a2 == null) {
                return;
            }
            if (scope == AnalyticsProperty.Scope.PAYMENT) {
                a(str, a2);
            } else if (scope == AnalyticsProperty.Scope.ORDER) {
                b(str, a2);
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S2", e2.getMessage());
        }
    }

    private static Object a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.get(str);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S2", e2.getMessage());
            return null;
        }
    }

    private static String b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S2", e2.getMessage());
            return null;
        }
    }

    private static boolean c(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getBoolean(str);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S2", e2.getMessage());
            return false;
        }
    }

    static void a(JSONObject jSONObject) {
        try {
            b("amount", (Object) Long.valueOf(Long.parseLong(b(jSONObject, "amount"))));
        } catch (Exception e2) {
        }
        try {
            b("framework", (Object) jSONObject.has("framework") ? b(jSONObject, "framework") : "native");
        } catch (Exception e3) {
        }
        try {
            a(jSONObject, "contact", AnalyticsProperty.Scope.ORDER);
            a(jSONObject, "email", AnalyticsProperty.Scope.ORDER);
            a(jSONObject, "order_id", AnalyticsProperty.Scope.ORDER);
            String b2 = b(jSONObject, "method");
            if (b2 != null) {
                if (jSONObject.has("token")) {
                    b2 = "saved card";
                }
                a("method", (Object) b2);
                boolean z = false;
                if (b2.equals("card")) {
                    String b3 = b(jSONObject, "card[number]");
                    if (!AnalyticsUtil.isNullOrEmpty(b3) && b3.length() >= 6) {
                        a("card_number", (Object) b3.substring(0, 6));
                    }
                } else if (b2.equals("saved card")) {
                    boolean c2 = c(jSONObject, "razorpay_otp");
                    StringBuilder sb = new StringBuilder();
                    if (!c2) {
                        z = true;
                    }
                    b("Checkout Login", (Object) sb.append(z).toString());
                } else if (b2.equals("netbanking")) {
                    a(jSONObject, "bank", AnalyticsProperty.Scope.PAYMENT);
                } else if (b2.equals("wallet")) {
                    a(jSONObject, "wallet", AnalyticsProperty.Scope.PAYMENT);
                } else if (b2.equals("upi")) {
                    a("flow", (Object) b(jSONObject, "_[flow]"));
                }
            }
        } catch (Exception e4) {
            new StringBuilder("Failed to add props to lumberjack: ").append(e4.getMessage());
            AnalyticsUtil.reportError(e4.getMessage(), "S2", e4.getMessage());
        }
    }

    static void b() {
        d();
        c();
        f();
        h();
        n = false;
    }

    static void c() {
        r = new HashMap();
    }

    static void d() {
        s = new HashMap();
    }

    private static JSONObject e(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("events");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                if (jSONObject2.has("properties")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("properties");
                    if (jSONObject3.has(ImagesContract.URL)) {
                        String string = jSONObject3.getString(ImagesContract.URL);
                        if (string.startsWith("data:")) {
                            string = "Data present in url";
                        }
                        jSONObject3.put(ImagesContract.URL, string);
                    }
                    jSONObject2.put("properties", jSONObject3);
                }
                jSONArray.put(i2, jSONObject2);
            }
            jSONObject.put("events", jSONArray);
        } catch (JSONException e2) {
            d__1_.a("Error in filtering payload", e2);
        }
        return jSONObject;
    }

    static JSONObject e() {
        return m;
    }

    static void a(Context context) {
        synchronized (l) {
            String jSONObject = e(l).toString();
            String str = p;
            try {
                String randomString = BaseUtils.getRandomString();
                String a2 = new CryptLib().a(jSONObject, "c", randomString);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("data", a2);
                jSONObject2.put("iv", randomString);
                jSONObject2.put("sdk_version", str);
                l.b(context, "SavedEventsData", jSONObject2.toString());
            } catch (Exception e2) {
                AnalyticsUtil.reportError(e2.getMessage(), "S1", e2.getLocalizedMessage());
                d__1_.a("Unable to encrypt value", e2);
            }
        }
    }

    private static JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", D$$l_.a().getLumberjackKey());
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", "checkout.mobile.sessionCreated.metrics");
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", "session_created");
            jSONObject3.put("platform", "android");
            jSONObject3.put("framework", o + "_android_" + AnalyticsUtil.getFramework());
            jSONArray2.put(jSONObject3);
            jSONObject2.put("labels", jSONArray2);
            jSONArray.put(jSONObject2);
            jSONObject.put("metrics", jSONArray);
        } catch (JSONException e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", e2.getLocalizedMessage());
        }
        return jSONObject;
    }

    private static JSONObject d(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", D$$l_.a().getLumberjackKey());
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", "checkout.mobile.sessionErrored.metrics");
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", "session_errored");
            jSONObject3.put("platform", "android");
            jSONObject3.put("framework", o + "_android_" + AnalyticsUtil.getFramework());
            jSONObject3.put("severity", str);
            jSONArray2.put(jSONObject3);
            jSONObject2.put("labels", jSONArray2);
            jSONArray.put(jSONObject2);
            jSONObject.put("metrics", jSONArray);
        } catch (JSONException e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S0", e2.getLocalizedMessage());
        }
        return jSONObject;
    }

    static void b(String str) {
        f(d(str));
    }

    private static void f(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        hashMap.put("accept", "application/json");
        hashMap.put("content-type", "applications/json");
        M$_3_.a("https://lumberjack-metrics.razorpay.com/v1/frontend-metrics", jSONObject.toString(), hashMap, new M$$8$());
    }
}
