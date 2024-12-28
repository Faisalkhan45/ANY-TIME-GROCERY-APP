package com.razorpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003$%&B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0011H\u0002J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u000e\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\"\u001a\u00020\u0016J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000ej\b\u0012\u0004\u0012\u00020\u0004`\u000fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u000ej\b\u0012\u0004\u0012\u00020\u0011`\u000fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u000ej\b\u0012\u0004\u0012\u00020\u0011`\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/razorpay/OpinionatedSoln;", "", "()V", "alertShownForStatus", "", "getAlertShownForStatus", "()Z", "setAlertShownForStatus", "(Z)V", "callbackSent", "checkedForSubMinorVersion", "getCheckedForSubMinorVersion", "setCheckedForSubMinorVersion", "dialogItemStatus", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "dialogItemSubTitles", "", "dialogItemTitles", "dismissCallback", "Lcom/razorpay/OpinionatedSoln$DismissCallback;", "checkEnvVariablesForProject", "", "activity", "Landroid/app/Activity;", "checkIfVersionUpdateExists", "version", "getBuildConfigValue", "context", "Landroid/content/Context;", "fieldName", "getUpdatedVersionNumber", "isMinor", "integrationStatusCheck", "sendCallbackIfExists", "showDialog", "DismissCallback", "HandleDialogShowPreference", "MyListAdapter", "checkout_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: OpinionatedSoln.kt */
public final class OpinionatedSoln {
    public static final OpinionatedSoln INSTANCE = new OpinionatedSoln();
    private static boolean alertShownForStatus;
    private static boolean callbackSent;
    private static boolean checkedForSubMinorVersion;
    private static final ArrayList<Boolean> dialogItemStatus = new ArrayList<>();
    private static final ArrayList<String> dialogItemSubTitles = new ArrayList<>();
    private static final ArrayList<String> dialogItemTitles = new ArrayList<>();
    private static DismissCallback dismissCallback;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/razorpay/OpinionatedSoln$DismissCallback;", "", "alertDismissed", "", "checkout_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: OpinionatedSoln.kt */
    public interface DismissCallback {
        void alertDismissed();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/razorpay/OpinionatedSoln$HandleDialogShowPreference;", "", "errorFound", "", "checkout_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: OpinionatedSoln.kt */
    public interface HandleDialogShowPreference {
        void errorFound();
    }

    private OpinionatedSoln() {
    }

    public final boolean getAlertShownForStatus() {
        return alertShownForStatus;
    }

    public final void setAlertShownForStatus(boolean z) {
        alertShownForStatus = z;
    }

    public final boolean getCheckedForSubMinorVersion() {
        return checkedForSubMinorVersion;
    }

    public final void setCheckedForSubMinorVersion(boolean z) {
        checkedForSubMinorVersion = z;
    }

    public final void integrationStatusCheck(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        checkIfVersionUpdateExists(activity, getUpdatedVersionNumber(true));
    }

    public final void integrationStatusCheck(Activity activity, DismissCallback dismissCallback2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(dismissCallback2, "dismissCallback");
        Context context = activity;
        g$_H$.a().a(context);
        CheckoutUtils.e(context);
        callbackSent = false;
        dismissCallback = dismissCallback2;
        checkIfVersionUpdateExists(activity, getUpdatedVersionNumber(true));
    }

    private final void checkEnvVariablesForProject(Activity activity) {
        dialogItemTitles.add("Min SDK Version Check");
        dialogItemSubTitles.add("Min SDK Version Compatible");
        dialogItemStatus.add(Boolean.TRUE);
        showDialog(activity);
    }

    private final String getUpdatedVersionNumber(boolean z) {
        String str = (String) StringsKt.split$default((CharSequence) BuildConfig.VERSION_NAME, new String[]{"."}, false, 0, 6, (Object) null).get(0);
        String str2 = (String) StringsKt.split$default((CharSequence) BuildConfig.VERSION_NAME, new String[]{"."}, false, 0, 6, (Object) null).get(1);
        String str3 = (String) StringsKt.split$default((CharSequence) BuildConfig.VERSION_NAME, new String[]{"."}, false, 0, 6, (Object) null).get(2);
        if (z) {
            return str + '.' + (Integer.parseInt(str2) + 1) + ".0";
        }
        return str + '.' + str2 + '.' + (Integer.parseInt(str3) + 1);
    }

    private final void checkIfVersionUpdateExists(Activity activity, String str) {
        M$_3_.a("https://mvnrepository.com/artifact/com.razorpay/checkout/" + str, new OpinionatedSoln$$ExternalSyntheticLambda0(new Ref.BooleanRef(), activity));
    }

    /* access modifiers changed from: private */
    /* renamed from: checkIfVersionUpdateExists$lambda-0  reason: not valid java name */
    public static final void m435checkIfVersionUpdateExists$lambda0(Ref.BooleanRef booleanRef, Activity activity, ResponseObject responseObject) {
        Intrinsics.checkNotNullParameter(booleanRef, "$versionUpdateExists");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        if (responseObject != null && responseObject.getResponseCode() == 200) {
            booleanRef.element = true;
            dialogItemTitles.add("Version Upgrade Check");
            dialogItemSubTitles.add("A version update was found. Click here to go to docs");
            dialogItemStatus.add(Boolean.FALSE);
            INSTANCE.checkEnvVariablesForProject(activity);
        } else if (!checkedForSubMinorVersion) {
            checkedForSubMinorVersion = true;
            OpinionatedSoln opinionatedSoln = INSTANCE;
            opinionatedSoln.checkIfVersionUpdateExists(activity, opinionatedSoln.getUpdatedVersionNumber(false));
        } else {
            booleanRef.element = false;
            dialogItemTitles.add("Version Upgrade Check");
            dialogItemSubTitles.add("Running the latest version");
            dialogItemStatus.add(Boolean.TRUE);
            INSTANCE.checkEnvVariablesForProject(activity);
        }
    }

    public final void showDialog(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Context context = activity;
        Object buildConfigValue = getBuildConfigValue(context, "DEBUG");
        if (buildConfigValue == null) {
            CheckoutUtils.a();
            sendCallbackIfExists();
        } else if (!((Boolean) buildConfigValue).booleanValue() || alertShownForStatus) {
            CheckoutUtils.a();
            sendCallbackIfExists();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View inflate = activity.getLayoutInflater().inflate(R.layout.sdk_integration_status, (ViewGroup) null);
            Intrinsics.checkNotNullExpressionValue(inflate, "activity.layoutInflater.…integration_status, null)");
            ListView listView = (ListView) inflate.findViewById(R.id.check_list);
            ArrayList<String> arrayList = dialogItemTitles;
            ArrayList<String> arrayList2 = dialogItemSubTitles;
            ArrayList<Boolean> arrayList3 = dialogItemStatus;
            listView.setAdapter(new V$$3$(activity, arrayList, arrayList2, arrayList3));
            listView.setOnItemClickListener(new OpinionatedSoln$$ExternalSyntheticLambda1(activity));
            builder.setView(inflate);
            builder.setOnDismissListener(new OpinionatedSoln$$ExternalSyntheticLambda2());
            Iterator<Boolean> it = arrayList3.iterator();
            boolean z = true;
            while (it.hasNext()) {
                if (!it.next().booleanValue()) {
                    z = false;
                }
            }
            if (z) {
                builder.setNegativeButton("Hide notification forever", new OpinionatedSoln$$ExternalSyntheticLambda3(activity));
            }
            Boolean opinionatedSolnPreference = BaseConfig.getOpinionatedSolnPreference(context);
            Intrinsics.checkNotNullExpressionValue(opinionatedSolnPreference, "getOpinionatedSolnPreference(activity)");
            if (opinionatedSolnPreference.booleanValue() || !z) {
                if (!z) {
                    BaseConfig.setOpinionatedSolnPreference(context, Boolean.TRUE);
                }
                AlertDialog show = builder.show();
                alertShownForStatus = true;
                new p$_5$(show).start();
                CheckoutUtils.a();
                return;
            }
            HashMap hashMap = new HashMap();
            Iterator<String> it2 = dialogItemTitles.iterator();
            String str = "";
            while (it2.hasNext()) {
                String next = it2.next();
                Intrinsics.checkNotNullExpressionValue(next, "item");
                ArrayList<String> arrayList4 = dialogItemSubTitles;
                ArrayList<String> arrayList5 = dialogItemTitles;
                String str2 = arrayList4.get(arrayList5.indexOf(next));
                Intrinsics.checkNotNullExpressionValue(str2, "dialogItemSubTitles[dial…ItemTitles.indexOf(item)]");
                hashMap.put(next, str2);
                if (!dialogItemStatus.get(arrayList5.indexOf(next)).booleanValue()) {
                    str = "https://razorpay.com/docs/payments/payment-gateway/android-integration/standard";
                }
            }
            Log.w("com.razorpay.checkout", "RAZORPAY_SDK: " + hashMap + 10 + str);
            sendCallbackIfExists();
            CheckoutUtils.a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog$lambda-1  reason: not valid java name */
    public static final void m436showDialog$lambda1(Activity activity, AdapterView adapterView, View view, int i, long j) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        if (i == 0) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://razorpay.com/docs/payments/payment-gateway/android-integration/standard/#list-of-razorpay-android-standard-sdk-versions-last"));
            activity.startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog$lambda-2  reason: not valid java name */
    public static final void m437showDialog$lambda2(DialogInterface dialogInterface) {
        INSTANCE.sendCallbackIfExists();
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog$lambda-3  reason: not valid java name */
    public static final void m438showDialog$lambda3(Activity activity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Context context = activity;
        BaseConfig.setOpinionatedSolnPreference(context, Boolean.FALSE);
        Toast.makeText(context, "Status will be shown in logs. RAZORPAY_SDK", 1).show();
        INSTANCE.sendCallbackIfExists();
    }

    public final void sendCallbackIfExists() {
        DismissCallback dismissCallback2 = dismissCallback;
        if (dismissCallback2 != null && !callbackSent) {
            if (dismissCallback2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dismissCallback");
                dismissCallback2 = null;
            }
            dismissCallback2.alertDismissed();
            callbackSent = true;
        }
    }

    public final Object getBuildConfigValue(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Field field = str != null ? Class.forName(context.getPackageName() + ".BuildConfig").getField(str) : null;
            if (field != null) {
                return field.get((Object) null);
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
