package com.razorpay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.razorpay.AnalyticsProperty;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CheckoutOptions */
class O_$v$ {
    private JSONObject a;

    O_$v$(String str) {
        try {
            this.a = new JSONObject(str);
        } catch (JSONException e) {
            AnalyticsUtil.reportError("CheckoutOptions", "S0", e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        try {
            return this.a.getString("key");
        } catch (JSONException e) {
            d__1_.a("Error reading options!", e);
            AnalyticsUtil.reportError("CheckoutOptions", "S0", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean b() {
        try {
            if (this.a.has("send_sms_hash")) {
                return this.a.getBoolean("send_sms_hash");
            }
            return false;
        } catch (JSONException e) {
            d__1_.a("Error reading options!", e);
            AnalyticsUtil.reportError(getClass().getName(), "error:exception", e.getMessage());
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        try {
            if (this.a.has("allow_rotation")) {
                return this.a.getBoolean("allow_rotation");
            }
            return false;
        } catch (JSONException e) {
            d__1_.a("Error reading options!", e);
            AnalyticsUtil.reportError(getClass().getName(), "error:exception", e.getMessage());
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final JSONObject d() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public final String e() {
        return this.a.toString();
    }

    /* access modifiers changed from: package-private */
    public final String f() {
        try {
            if (this.a.has("order_id")) {
                return this.a.getString("order_id");
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject l() {
        return this.a.optJSONObject("prefill");
    }

    /* access modifiers changed from: package-private */
    public final JSONObject g() {
        return this.a.optJSONObject("otpelf_preferences");
    }

    /* access modifiers changed from: package-private */
    public final void h() {
        try {
            JSONObject jSONObject = new JSONObject(this.a.toString());
            if (jSONObject.has("prefill")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("prefill");
                jSONObject2.remove("card");
                jSONObject2.remove("card[number]");
                jSONObject2.remove("card[expiry]");
                jSONObject2.remove("card[cvv]");
                jSONObject.put("prefill", jSONObject2);
            }
            jSONObject.remove("image");
            AnalyticsUtil.addProperty("merchant options", new AnalyticsProperty(jSONObject, AnalyticsProperty.Scope.ORDER));
        } catch (Exception e) {
            AnalyticsUtil.reportError(getClass().getName(), "S2", e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Activity activity, int i) {
        String base64FromResource;
        b("redirect", Boolean.TRUE);
        if (!(i == 0 || (base64FromResource = BaseUtils.getBase64FromResource(activity.getResources(), i)) == null)) {
            b("image", base64FromResource);
        }
        String a2 = CheckoutUtils.a((Context) activity);
        if (!TextUtils.isEmpty(a2) && (l() == null || !l().has("email"))) {
            a("email", (Object) a2);
        }
        String b = CheckoutUtils.b(activity);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        if (l() == null || !l().has("contact")) {
            a("contact", (Object) b);
        }
    }

    /* access modifiers changed from: package-private */
    public final String i() {
        b("image", (Object) null);
        return this.a.toString();
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, Object obj) {
        JSONObject jSONObject = new JSONObject();
        if (l() != null) {
            jSONObject = l();
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e.getMessage());
        }
        try {
            this.a.put("prefill", jSONObject);
        } catch (JSONException e2) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e2.getMessage());
        }
    }

    private void b(String str, Object obj) {
        try {
            this.a.put(str, obj);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str) {
        return this.a.has(str);
    }

    /* access modifiers changed from: package-private */
    public final String j() {
        if (l() == null) {
            return null;
        }
        return l().optString("contact");
    }

    /* access modifiers changed from: package-private */
    public final String k() {
        if (l() == null) {
            return null;
        }
        return l().optString("contact");
    }

    public final boolean b(String str) {
        try {
            if (!this.a.has("external") || !this.a.getJSONObject("external").getJSONArray("wallets").toString().contains(str)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            AnalyticsUtil.reportError(getClass().getName(), "S2", e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final <T> T c(String str) {
        Object opt = this.a.opt(str);
        if (opt == null) {
            return null;
        }
        return opt.getClass().cast(opt);
    }
}
