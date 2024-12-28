package com.razorpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.LinearLayout;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

final class CheckoutUtils {
    private static Dialog a;

    interface BackButtonDialogCallback {
        void onNegativeButtonClick();

        void onPositiveButtonClick();
    }

    static String a(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            return str;
        }
        StringBuilder append = new StringBuilder().append(str);
        String str4 = "?";
        if (str.contains(str4)) {
            str4 = "&";
        }
        String sb = append.append(str4).append(str2).toString();
        if (str3 == null) {
            return sb;
        }
        return sb + "=" + str3;
    }

    static void a(Activity activity) {
        activity.getWindow().addFlags(2048);
        activity.getWindow().clearFlags(1024);
    }

    static void a(Context context, String str) {
        SharedPreferences.Editor b = l.b(context);
        b.putString("rzp_user_email", str);
        b.commit();
    }

    static void a(Context context, String str, String str2) {
        SharedPreferences.Editor b = l.b(context);
        if (str2 == null) {
            b.remove("pref_merchant_options_" + str);
        } else {
            b.putString("pref_merchant_options_" + str, str2);
        }
        b.apply();
    }

    static void b(Context context, String str) {
        SharedPreferences.Editor b = l.b(context);
        b.putString("rzp_user_contact", str);
        b.commit();
    }

    static String a(Context context) {
        return l.a(context).getString("rzp_user_email", (String) null);
    }

    static String b(Context context) {
        return l.a(context).getString("rzp_user_contact", (String) null);
    }

    static void a(Context context, String str, String str2, String str3, BackButtonDialogCallback backButtonDialogCallback) {
        new AlertDialog.Builder(context).setMessage(str).setPositiveButton(str2, new t$$1$(backButtonDialogCallback)).setNegativeButton(str3, new E__a_(backButtonDialogCallback)).show();
    }

    static JSONArray c(Context context) {
        List<ResolveInfo> listOfAppsWhichHandleDeepLink = BaseUtils.getListOfAppsWhichHandleDeepLink(context, "upi://pay");
        if (listOfAppsWhichHandleDeepLink == null || listOfAppsWhichHandleDeepLink.size() <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (ResolveInfo a2 : listOfAppsWhichHandleDeepLink) {
            jSONArray.put(a(context, a2));
        }
        return jSONArray;
    }

    static JSONArray d(Context context) {
        List<ResolveInfo> listOfAppsWhichHandleDeepLink = BaseUtils.getListOfAppsWhichHandleDeepLink(context, "credpay://checkout");
        if (listOfAppsWhichHandleDeepLink == null || listOfAppsWhichHandleDeepLink.size() <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (ResolveInfo a2 : listOfAppsWhichHandleDeepLink) {
            jSONArray.put(a(a2));
        }
        return jSONArray;
    }

    private static JSONObject a(ResolveInfo resolveInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!resolveInfo.activityInfo.packageName.contains(BaseConstants.CRED_PACKAGE)) {
                return null;
            }
            jSONObject.put("package_name", BaseConstants.CRED_PACKAGE);
            jSONObject.put("shortcode", "cred");
            jSONObject.put("uri", "credpay");
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            AnalyticsUtil.reportError(e.getMessage(), "S1", e.getMessage());
            return null;
        }
    }

    private static JSONObject a(Context context, ResolveInfo resolveInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("package_name", resolveInfo.activityInfo.packageName);
            jSONObject.put("app_name", BaseUtils.getAppNameOfResolveInfo(resolveInfo, context));
            jSONObject.put("app_icon", BaseUtils.getBase64FromOtherAppsResource(context, resolveInfo.activityInfo.packageName));
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
            e.printStackTrace();
        }
        return jSONObject;
    }

    static void e(Context context) {
        if (g$_H$.a().d() && context != null && !((Activity) context).isFinishing()) {
            Dialog dialog = a;
            if (dialog == null || !dialog.isShowing()) {
                Dialog dialog2 = new Dialog(context);
                a = dialog2;
                dialog2.requestWindowFeature(1);
                a.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                a.setContentView(R.layout.rzp_loader);
                ((CircularProgressView) a.findViewById(R.id.progressBar)).setColor(Color.parseColor(g$_H$.a().c()));
                ((LinearLayout) a.findViewById(R.id.ll_loader)).setOnClickListener(new l__9_());
                try {
                    a.show();
                } catch (Exception e) {
                    d__1_.a("Error showing loader", e);
                }
            }
        }
    }

    static void a() {
        Dialog dialog = a;
        if (dialog != null) {
            if (dialog.isShowing()) {
                try {
                    a.dismiss();
                } catch (Exception e) {
                    d__1_.a("Error dismissing loader", e);
                }
            }
            a = null;
        }
    }
}
