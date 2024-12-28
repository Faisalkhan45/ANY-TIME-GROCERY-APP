package com.razorpay;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import org.json.JSONObject;

/* compiled from: MagicBase */
class F_$o_ implements SmsAgentInterface {
    Context a = this.a;
    m b;
    boolean c = false;
    boolean d = false;
    private WebView e;
    private String f;
    private boolean g = false;
    private p$$q_ h;

    F_$o_(Activity activity, WebView webView) {
        this.e = webView;
        m a2 = m.a();
        this.b = a2;
        a2.a((SmsAgentInterface) this);
        p$$q_ p__q_ = new p$$q_(activity);
        this.h = p__q_;
        M$_3_.a(g$_H$.a().getMagicVersionUrl(), new S__Z$(p__q_));
    }

    public final void a() {
        if (!this.d) {
            try {
                JSONObject magicSettings = g$_H$.a().getMagicSettings();
                magicSettings.put("merchant_key", (Object) null);
                magicSettings.put("otp_permission", this.g);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", g$_H$.a);
                jSONObject.put("version_code", g$_H$.c);
                magicSettings.put("sdk", jSONObject);
                a("window.__rzp_options = " + magicSettings.toString());
            } catch (Exception e2) {
                d__1_.a("Unable to load magic settings", e2);
            }
            a(this.h.a());
            String str = this.f;
            if (str != null) {
                a(String.format("Magic.elfBridge.setSms(%s)", new Object[]{str}));
                this.f = null;
            }
            this.d = true;
        }
    }

    private void a(String str) {
        this.e.loadUrl(String.format("javascript: %s", new Object[]{str}));
    }

    public void postSms(String str, String str2) {
        if (this.c) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sender", str);
                jSONObject.put("message", str2);
                this.f = jSONObject.toString();
                a(String.format("Magic.elfBridge.setSms(%s)", new Object[]{jSONObject.toString()}));
            } catch (Exception e2) {
                d__1_.a("Exception", e2);
            }
        }
    }

    public void setSmsPermission(boolean z) {
        this.g = z;
    }
}
