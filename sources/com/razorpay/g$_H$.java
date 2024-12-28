package com.razorpay;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ConfigCheckout */
final class g$_H$ extends BaseConfig {
    static String a = BuildConfig.SDK_TYPE;
    static String b = BuildConfig.VERSION_NAME;
    static int c = 56;
    static boolean d = true;
    private static g$_H$ e;
    private static String f = "2HujvzmUo2nuRLLqhIHIV4sCEmRw9FIc";
    private static String g = "3.0.5";
    private ArrayList<String> h = new ArrayList<>();
    private Map<String, String> i = new HashMap();
    private boolean j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n;
    private Boolean o;
    private int p;
    private boolean q = false;
    private boolean r;
    private String s;
    private String t;
    private String u;

    private g$_H$() {
    }

    public static g$_H$ a() {
        if (e == null) {
            g$_H$ g__h_ = new g$_H$();
            e = g__h_;
            D$$l_.a(g__h_);
        }
        return e;
    }

    public final void a(Context context) {
        setConfig(BaseConfig.getConfig(context, R.raw.rzp_config));
    }

    public final void a(JSONObject jSONObject) {
        try {
            a(jSONObject, true);
        } catch (Exception e2) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e2.getLocalizedMessage());
        }
    }

    private void a(JSONObject jSONObject, boolean z) {
        if (!this.q) {
            this.o = Boolean.valueOf(((Boolean) BaseUtils.getJsonValue("retry.enabled", jSONObject, (Object) Boolean.TRUE)).booleanValue());
            this.p = ((Integer) BaseUtils.getJsonValue("retry.max_count", jSONObject, (Object) -1)).intValue();
        }
        this.q = z;
    }

    public final void setConfig(JSONObject jSONObject) {
        try {
            this.h = BaseUtils.jsonStringArrayToArrayList((JSONArray) BaseUtils.getJsonValue("checkout.append_keys", jSONObject, (Object) new JSONArray()));
            JSONObject jSONObject2 = (JSONObject) BaseUtils.getJsonValue("checkout.url_config", jSONObject, (Object) new JSONObject());
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.i.put(next, jSONObject2.getString(next));
            }
            this.j = ((Boolean) BaseUtils.getJsonValue("card_saving.broadcast_receiver_flow", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.k = ((Boolean) BaseUtils.getJsonValue("card_saving.shared_preferences_flow", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.l = ((Boolean) BaseUtils.getJsonValue("card_saving.local", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.m = (String) BaseUtils.getJsonValue("native_loader.color", jSONObject, (Object) "");
            this.n = ((Boolean) BaseUtils.getJsonValue("native_loader.enable", jSONObject, (Object) "")).booleanValue();
            a(jSONObject, false);
            this.s = (String) BaseUtils.getJsonValue("back_button.alert_message", jSONObject, (Object) "");
            this.r = ((Boolean) BaseUtils.getJsonValue("back_button.enable", jSONObject, (Object) Boolean.FALSE)).booleanValue();
            this.u = (String) BaseUtils.getJsonValue("back_button.positive_text", jSONObject, (Object) "");
            this.t = (String) BaseUtils.getJsonValue("back_button.negative_text", jSONObject, (Object) "");
        } catch (Exception e2) {
            AnalyticsUtil.reportError(getClass().getName(), "S2", e2.getMessage());
            Log.e("com.razorpay.checkout", "Error in setting Config, ErrorMessage=" + e2.getMessage());
            e2.printStackTrace();
        }
        super.setConfig(jSONObject);
    }

    public final boolean b() {
        return this.l;
    }

    public final String c() {
        return this.m;
    }

    public final boolean d() {
        return this.n;
    }

    public final boolean e() {
        return this.o.booleanValue();
    }

    public final int f() {
        return this.p;
    }

    public final Map<String, String> g() {
        return this.i;
    }

    public final ArrayList<String> h() {
        return this.h;
    }

    public final String i() {
        return this.t;
    }

    public final String j() {
        return this.u;
    }

    public final boolean k() {
        return this.r;
    }

    public final String l() {
        return this.s;
    }

    public final boolean m() {
        return this.j;
    }

    public final boolean n() {
        return this.k;
    }

    static void a(Context context, String str) {
        if (a().isConfigEnabled()) {
            HashMap hashMap = new HashMap();
            hashMap.put("AuthKey", f);
            hashMap.put("Content-type", "application/json");
            hashMap.put("CurrentSettingVersion", b(context));
            Uri.Builder appendQueryParameter = Uri.parse(a().getConfigEndpoint()).buildUpon().appendQueryParameter("tenant", "android_" + a).appendQueryParameter("sdk_version", b).appendQueryParameter("sdk_type", a).appendQueryParameter("magic_enabled", String.valueOf(d)).appendQueryParameter("sdk_version_code", String.valueOf(c)).appendQueryParameter("app_version", BuildConfig.VERSION_NAME).appendQueryParameter("version", getCurrentConfigVersionTag(b(context)));
            BaseConfig.getFetchConfigBuilder(appendQueryParameter, context, str);
            BaseConfig.fetchConfig(appendQueryParameter.build().toString(), hashMap, context);
        }
    }

    private static String b(Context context) {
        String baseCurrentConfigVersion = BaseConfig.getBaseCurrentConfigVersion(context);
        if (baseCurrentConfigVersion == null) {
            return g;
        }
        return baseCurrentConfigVersion;
    }
}
