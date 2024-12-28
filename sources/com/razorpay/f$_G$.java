package com.razorpay;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.razorpay.AnalyticsProperty;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CardSaving */
final class f$_G$ {
    static String a(Context context, JSONArray jSONArray) {
        JSONObject jSONObject;
        String str;
        JSONArray jSONArray2 = jSONArray;
        String str2 = "";
        if (jSONArray2 == null || jSONArray.length() == 0) {
            return null;
        }
        if (jSONArray.length() == 1) {
            try {
                jSONObject = jSONArray2.getJSONObject(0);
            } catch (Exception e) {
            }
        } else {
            if (jSONArray.length() != 1) {
                String str3 = "{";
                String str4 = null;
                boolean z = false;
                boolean z2 = true;
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                        if (!z2) {
                            str3 = str3 + ",";
                        } else {
                            z2 = false;
                        }
                        str3 = str3 + "'" + jSONObject2.getString("card_saving_token_source") + "': '" + jSONObject2.getString("rzp_device_token") + "'";
                        if (str4 == null) {
                            str4 = jSONObject2.getString("rzp_device_token");
                        } else if (!str4.equals(jSONObject2.getString("rzp_device_token"))) {
                            z = true;
                        }
                    } catch (Throwable th) {
                    }
                }
                String str5 = str3 + "}";
                if (z) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("packages", str5);
                    AnalyticsUtil.trackEvent(AnalyticsEvent.MULTIPLE_TOKEN_EVENT, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
                    return null;
                }
                try {
                    jSONObject = jSONArray2.getJSONObject(0);
                } catch (Exception e2) {
                }
            }
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            str = jSONObject.getString("rzp_device_token");
            try {
                str2 = jSONObject.getString("card_saving_token_source");
            } catch (Exception e3) {
            }
        } catch (Exception e4) {
            str = str2;
        }
        a(context, str);
        AnalyticsUtil.addProperty("device_token_source_single", new AnalyticsProperty(str2, AnalyticsProperty.Scope.ORDER));
        return str;
    }

    static void a(Context context) {
        if (g$_H$.a().b() && b(context) != null) {
            AnalyticsUtil.addProperty("device_token_source_single", new AnalyticsProperty(context.getPackageName(), AnalyticsProperty.Scope.ORDER));
        } else if (Build.VERSION.SDK_INT >= 24 && g$_H$.a().m()) {
            Intent intent = new Intent();
            intent.setAction("rzp.device_token.share");
            context.sendOrderedBroadcast(intent, (String) null, new L__R$(), (Handler) null, -1, (String) null, (Bundle) null);
        } else if (g$_H$.a().n()) {
            a(context, c(context));
        }
    }

    private static JSONArray c(Context context) {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        for (ResolveInfo resolveInfo : BaseUtils.getListOfAppsWhichHandleDeepLink(context, "io.rzp://rzp.io")) {
            String str = resolveInfo.activityInfo.taskAffinity;
            i++;
            try {
                String b = b(context.createPackageContext(str, 2));
                if (b != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("rzp_device_token", b);
                    jSONObject.put("card_saving_token_source", str);
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e) {
                if (!(e instanceof SecurityException) || Build.VERSION.SDK_INT < 24) {
                    AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
                } else {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.SHARE_PREFERENCES_SECURITY_EXCEPTION);
                }
                d__1_.a("Error fetching global device token", e);
            }
        }
        AnalyticsUtil.addProperty("sdk_count", new AnalyticsProperty(i, AnalyticsProperty.Scope.ORDER));
        AnalyticsUtil.addProperty("sdk_count_with_token", new AnalyticsProperty(jSONArray.length(), AnalyticsProperty.Scope.ORDER));
        return jSONArray;
    }

    static String b(Context context) {
        return l.c(context).getString("rzp_device_token", (String) null);
    }

    static void a(Context context, String str) {
        l.d(context).putString("rzp_device_token", str).apply();
    }
}
