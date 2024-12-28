package com.razorpay;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONObject;

/* compiled from: SharedPreferenceUtil */
final class l {
    private static SharedPreferences a;
    private static SharedPreferences.Editor b;

    static SharedPreferences a(Context context) {
        if (a == null) {
            a = context.getSharedPreferences("rzp_preference_private", 0);
        }
        return a;
    }

    static SharedPreferences.Editor b(Context context) {
        if (b == null) {
            b = a(context).edit();
        }
        return b;
    }

    static SharedPreferences c(Context context) {
        try {
            return context.getSharedPreferences("rzp_preference_public", 0);
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
            return context.getSharedPreferences("rzp_preference_public", 0);
        }
    }

    static SharedPreferences.Editor d(Context context) {
        return c(context).edit();
    }

    static String a(Context context, String str, String str2) {
        try {
            String a2 = a(context, str);
            if (a2 == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(a2);
            CryptLib cryptLib = new CryptLib();
            if (!str2.equals(jSONObject.getString("sdk_version"))) {
                return null;
            }
            return cryptLib.b(jSONObject.getString("data"), "c", jSONObject.getString("iv"));
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S1", e.getLocalizedMessage());
            d__1_.a("Unable to decrypt value", e);
            return null;
        }
    }

    static String a(Context context, String str) {
        return a(context).getString(str, (String) null);
    }

    static void b(Context context, String str, String str2) {
        SharedPreferences.Editor b2 = b(context);
        b2.putString(str, str2);
        b2.commit();
    }
}
