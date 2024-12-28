package com.razorpay;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class BaseUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static String PERMISSION_DISABLED = "permission disabled";
    private static BaseUtils baseUtils;
    static String ipAddress;
    private static boolean isCompatibleWithGooglePay = true;
    private static boolean isGpayCardsUpiRegistered = false;
    private static boolean sWebViewDebuggingEnabled = n$_B$.a.booleanValue();
    private String orderId;
    private String paymentId;

    BaseUtils() {
    }

    public static BaseUtils getInstance() {
        BaseUtils baseUtils2 = baseUtils;
        if (baseUtils2 != null) {
            return baseUtils2;
        }
        BaseUtils baseUtils3 = new BaseUtils();
        baseUtils = baseUtils3;
        return baseUtils3;
    }

    public void setPaymentId(String str) {
        this.paymentId = str;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getMetadata() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("payment_id", this.paymentId);
            jSONObject.put("order_id", this.orderId);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String constructBasicAuth(String str) {
        return Base64.encodeToString((str + ":").getBytes(Key.STRING_CHARSET_NAME), 2);
    }

    static boolean hasPermission(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
            return false;
        }
    }

    static String getKeyId(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.getString("com.razorpay.ApiKey");
        } catch (PackageManager.NameNotFoundException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
            return null;
        }
    }

    static HashMap<String, String> getAllPluginsFromManifest(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return null;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            for (String str : applicationInfo.metaData.keySet()) {
                if (str.contains("com.razorpay.plugin.")) {
                    if (applicationInfo.metaData.getString(str).equalsIgnoreCase("com.razorpay.RzpGpayMerged")) {
                        try {
                            if (Class.forName("com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient").newInstance() != null) {
                                hashMap.put(str, applicationInfo.metaData.getString(str));
                            }
                        } catch (ClassNotFoundException e) {
                            AnalyticsUtil.reportError(e.getMessage(), "S2", "GooglePay SDK is not included");
                        } catch (IllegalAccessException | InstantiationException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (str.contains("com.razorpay.plugin.") && applicationInfo.metaData.getString(str).equalsIgnoreCase("com.razorpay.RzpGooglePay")) {
                    try {
                        if (Class.forName("com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient").newInstance() != null) {
                            hashMap.put(str, applicationInfo.metaData.getString(str));
                        }
                    } catch (ClassNotFoundException e3) {
                        AnalyticsUtil.reportError(e3.getMessage(), "S2", "GooglePay SDK is not included");
                    } catch (IllegalAccessException | InstantiationException e4) {
                        e4.printStackTrace();
                    }
                } else if (str.contains("com.razorpay.plugin.") && applicationInfo.metaData.getString(str) != null) {
                    hashMap.put(str, applicationInfo.metaData.getString(str));
                }
            }
            return hashMap;
        } catch (PackageManager.NameNotFoundException e5) {
            AnalyticsUtil.reportError(e5.getMessage(), "S0", e5.getMessage());
            return null;
        }
    }

    private static void setBaseWebViewSettings() {
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(sWebViewDebuggingEnabled);
        }
    }

    private static void enableJavaScriptInWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
    }

    static void setWebViewSettings(Context context, WebView webView, boolean z) {
        setBaseWebViewSettings();
        enableJavaScriptInWebView(webView);
        CookieManager.getInstance().setAcceptCookie(true);
        webView.setTag("");
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setTextZoom(100);
        String path = context.getApplicationContext().getDir("database", 0).getPath();
        if (Build.VERSION.SDK_INT < 19) {
            settings.setDatabasePath(path);
        }
        if (Build.VERSION.SDK_INT < 24) {
            settings.setGeolocationDatabasePath(path);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        if (z) {
            settings.setCacheMode(2);
        }
        settings.setSaveFormData(false);
        webView.addJavascriptInterface(new n(context), "StorageBridge");
    }

    static boolean hasFeature(Context context, String str) {
        return context.getPackageManager().hasSystemFeature(str);
    }

    static <T> T getSystemService(Context context, String str) {
        return context.getApplicationContext().getSystemService(str);
    }

    static int getNetworkType(Context context) {
        NetworkType dataNetworkType = getDataNetworkType(context);
        if (dataNetworkType == NetworkType.WIFI) {
            return 0;
        }
        if (dataNetworkType == NetworkType.BLUETOOTH) {
            return 1;
        }
        if (dataNetworkType != NetworkType.CELLULAR) {
            return -1;
        }
        String cellularNetworkType = getCellularNetworkType(context);
        if (cellularNetworkType.equalsIgnoreCase("2G")) {
            return 2;
        }
        if (cellularNetworkType.equalsIgnoreCase("3G")) {
            return 3;
        }
        if (cellularNetworkType.equalsIgnoreCase("4G")) {
            return 4;
        }
        return -1;
    }

    static String getCellularNetworkType(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
                switch (telephonyManager.getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3G";
                    case 13:
                        return "4G";
                    default:
                        return "NA";
                }
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getLocalizedMessage());
        }
        return "NA";
    }

    static String getCellularNetworkProviderName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(context, "phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    static NetworkType getDataNetworkType(Context context) {
        ConnectivityManager connectivityManager;
        if (hasPermission(context, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) getSystemService(context, "connectivity")) != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isConnected()) {
                return NetworkType.WIFI;
            }
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(7);
            if (networkInfo2 != null && networkInfo2.isConnected()) {
                return NetworkType.BLUETOOTH;
            }
            NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
            if (networkInfo3 != null && networkInfo3.isConnected()) {
                return NetworkType.CELLULAR;
            }
        }
        return NetworkType.UNKNOWN;
    }

    static String getLocale() {
        return Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
    }

    static ArrayList<String> jsonStringArrayToArrayList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    static String getAppBuildType(Context context) {
        if ((context.getApplicationInfo().flags & 2) != 0) {
            return "development";
        }
        return "production";
    }

    static CharSequence getWebViewUserAgent(Context context) {
        return AnalyticsUtil.returnUndefinedIfNull(new WebView(context).getSettings().getUserAgentString());
    }

    static boolean isDeviceHaveCorrectTlsVersion() {
        try {
            String[] protocols = SSLContext.getDefault().getDefaultSSLParameters().getProtocols();
            if (protocols == null) {
                return false;
            }
            for (String str : protocols) {
                if (str.startsWith("TLS") && !str.equalsIgnoreCase("TLSv1")) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getMessage());
        }
    }

    static void setup() {
        AnalyticsUtil.reset();
    }

    public static double round(double d, int i) {
        if (i >= 0) {
            return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
        }
        throw new IllegalArgumentException();
    }

    public static String nanoTimeToSecondsString(long j, int i) {
        return new StringBuilder().append(round(((double) j) / 1.0E9d, i)).toString();
    }

    static boolean isMerchantAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static Certificate getX509Certificate(SslCertificate sslCertificate) {
        byte[] byteArray = SslCertificate.saveState(sslCertificate).getByteArray("x509-certificate");
        if (byteArray == null) {
            return null;
        }
        try {
            return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(byteArray));
        } catch (CertificateException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
            return null;
        }
    }

    static String makeErrorPayload(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", str);
            jSONObject2.put("description", str2);
            jSONObject.put("error", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e.getMessage(), "error:exception", e.getMessage());
            e.printStackTrace();
            return "{\"error\":{\"code\": \"BAD_REQUEST_ERROR\", \"description\": \"An unknown error occurred.\"}}";
        }
    }

    static PublicKey constructPublicKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes(), 0)));
        } catch (Exception e) {
            return null;
        }
    }

    private static Boolean isUserRegisteredOnTruePay(Context context) {
        try {
            boolean z = true;
            if (context.getPackageManager().getComponentEnabledSetting(new ComponentName("com.truecaller", "com.truecaller.truepay.UserRegistered")) != 1) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            e.printStackTrace();
            AnalyticsUtil.reportError(e.getMessage(), "S1", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean checkUpiRegisteredApp(android.content.Context r0, java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case 1170339061: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0012
        L_0x0008:
            java.lang.String r0 = "com.google.android.apps.nbu.paisa.user"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x0013
        L_0x0012:
            r0 = -1
        L_0x0013:
            switch(r0) {
                case 0: goto L_0x0018;
                default: goto L_0x0016;
            }
        L_0x0016:
            r0 = 1
            return r0
        L_0x0018:
            boolean r0 = isCompatibleWithGooglePay
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.checkUpiRegisteredApp(android.content.Context, java.lang.String):boolean");
    }

    static HashSet<String> getSetOfPackageNamesSupportingUpi(Context context) {
        List<ResolveInfo> listOfAppsWhichHandleDeepLink = getListOfAppsWhichHandleDeepLink(context, "upi://pay");
        HashSet<String> hashSet = new HashSet<>();
        if (listOfAppsWhichHandleDeepLink != null && listOfAppsWhichHandleDeepLink.size() > 0) {
            for (ResolveInfo resolveInfo : listOfAppsWhichHandleDeepLink) {
                try {
                    hashSet.add(resolveInfo.activityInfo.packageName);
                } catch (Exception e) {
                    AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
                }
            }
        }
        if (hashSet.size() > 0 && !checkUpiRegisteredApp(context, "com.google.android.apps.nbu.paisa.user")) {
            hashSet.remove("com.google.android.apps.nbu.paisa.user");
        }
        if (hashSet.size() > 0 && !checkUpiRegisteredApp(context, "com.truecaller")) {
            hashSet.remove("com.truecaller");
        }
        return hashSet;
    }

    static List<ResolveInfo> getListOfAppsWhichHandleDeepLink(Context context, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        return context.getPackageManager().queryIntentActivities(intent, 131072);
    }

    static String getAppNameOfResolveInfo(ResolveInfo resolveInfo, Context context) {
        return getAppNameOfPackageName(resolveInfo.activityInfo.packageName, context);
    }

    static void startActivityForResult(String str, String str2, Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            if (str2 != null && str2.length() > 0) {
                intent.setPackage(str2);
            }
            if (str.startsWith("credpay")) {
                activity.startActivityForResult(intent, 20);
            } else {
                activity.startActivityForResult(intent, 99);
            }
        } catch (ActivityNotFoundException e) {
            AnalyticsUtil.reportError("BaseUtils", "S2", e.getMessage());
        }
    }

    static JSONObject getJSONFromIntentData(Intent intent) {
        Bundle extras;
        JSONObject jSONObject = new JSONObject();
        if (!(intent == null || (extras = intent.getExtras()) == null)) {
            for (String str : extras.keySet()) {
                try {
                    jSONObject.put(str, extras.get(str));
                } catch (JSONException e) {
                    AnalyticsUtil.reportError(e.getMessage(), "error:exception", e.getLocalizedMessage());
                }
            }
        }
        return jSONObject;
    }

    static String getBase64FromOtherAppsResource(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            return getBase64FromResource(packageManager.getResourcesForApplication(applicationInfo), applicationInfo.icon);
        } catch (PackageManager.NameNotFoundException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
    }

    static String getAppNameOfPackageName(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            int i = applicationInfo.labelRes;
            return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : packageManager.getResourcesForApplication(applicationInfo).getString(i);
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "error:exception", e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    static String getBase64FromResource(Resources resources, int i) {
        Drawable drawable;
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
        if (decodeResource == null && (drawable = resources.getDrawable(i)) != null) {
            if (drawable instanceof BitmapDrawable) {
                decodeResource = ((BitmapDrawable) drawable).getBitmap();
            } else {
                decodeResource = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(decodeResource);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
            }
        }
        if (decodeResource == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    static Object getJsonValue(String str, JSONObject jSONObject, Object obj) {
        Object jsonValue = getJsonValue(str.split("\\."), (Object) jSONObject, 0);
        if (jsonValue != null) {
            return jsonValue;
        }
        return obj;
    }

    static void pdfDownloadHelper(Activity activity, String str, String str2) {
        try {
            FileOutputStream openFileOutput = activity.openFileOutput(str, 0);
            openFileOutput.write(Base64.decode(str2, 0));
            openFileOutput.close();
        } catch (Exception e) {
        }
        try {
            Uri uriForFile = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName(), new File(activity.getFilesDir().toString() + "/" + str + ".pdf"));
            Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.putExtra("android.intent.extra.TITLE", str);
            intent.setDataAndType(uriForFile, "application/pdf");
            if (Build.VERSION.SDK_INT >= 26) {
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("/Documents"));
            }
            activity.startActivityForResult(intent, 77);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static Object getJsonValue(String[] strArr, Object obj, int i) {
        while (true) {
            i++;
            if (i == strArr.length) {
                return obj;
            }
            String str = strArr[i];
            if (obj instanceof JSONObject) {
                obj = ((JSONObject) obj).opt(str);
            } else if (!(obj instanceof JSONArray)) {
                return null;
            } else {
                JSONArray jSONArray = (JSONArray) obj;
                if (!TextUtils.isDigitsOnly(str)) {
                    return null;
                }
                obj = jSONArray.opt(Integer.parseInt(str));
            }
        }
    }

    static String getRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    static String getFileFromInternal(Activity activity, String str, String str2) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(activity.openFileInput(getVersionedAssetName(getLocalVersion(activity, str2).toString(), str)), Key.STRING_CHARSET_NAME));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return decryptFile(sb.toString());
            }
        }
    }

    static String decryptFile(String str) {
        String str2;
        try {
            CryptLib cryptLib = new CryptLib();
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update("rzpisunitedred".getBytes(Key.STRING_CHARSET_NAME));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
            }
            if (32 > stringBuffer.toString().length()) {
                str2 = stringBuffer.toString();
            } else {
                str2 = stringBuffer.toString().substring(0, 32);
            }
            return cryptLib.b(str, str2, "glorygloryunited");
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getLocalizedMessage());
            new StringBuilder("Unable to decrypt file, ").append(e.getMessage());
            return null;
        }
    }

    static String getVersionedAssetName(String str, String str2) {
        return str.replaceAll("\\.", "-") + "-" + str2;
    }

    static String getLocalVersion(Activity activity, String str) {
        String a = l.a(activity, str);
        if (a == null) {
            return getVersionFromJsonString("{\n  \"hash\" : \"MD5\",\n  \"magic_hash\": \"MD5\"\n}\n", str);
        }
        return a;
    }

    static String getVersionFromJsonString(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (str2.equalsIgnoreCase("otpelf_version")) {
                return jSONObject.getString("hash");
            }
            if (str2.equalsIgnoreCase("magic_version")) {
                return jSONObject.getString("magic_hash");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    static void updateLocalVersion(Activity activity, String str, String str2) {
        l.b(activity, str, str2);
    }

    static boolean storeFileInInternal(Activity activity, String str, String str2) {
        try {
            FileOutputStream openFileOutput = activity.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
            return true;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S1", "Error in saving file: " + str);
            Log.e("com.razorpay.checkout", "Error in saving file: " + str);
            return false;
        }
    }

    static void checkForLatestVersion(Context context, int i) {
        if (D$$l_.a().isSDKUpdateAlertEnabled() && isMerchantAppDebuggable(context) && i < D$$l_.a().getLatestSDKVersionCode()) {
            Toast.makeText(context, D$$l_.a().getUpdateSDKMsg(), 1).show();
        }
    }

    static int dpToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    static HashMap<String, String> getMapFromJSONObject(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "error:exception", e.getMessage());
            e.printStackTrace();
        }
        return hashMap;
    }

    static void setCompatibleWithGooglePay(boolean z) {
        isCompatibleWithGooglePay = z;
    }

    static String makeUrlEncodedPayload(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            sb.append(String.format("%s=%s&", new Object[]{next, Uri.encode(jSONObject.getString(next))}));
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    static String installedApps(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            for (ApplicationInfo next : context.getPackageManager().getInstalledApplications(0)) {
                if ((next.flags & 1) == 0) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(next.packageName);
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            return "Apps not available";
        }
    }

    static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    static String getDisplayResolution(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return String.format(Locale.ENGLISH, "%dx%dx%d", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels), Integer.valueOf(displayMetrics.densityDpi)});
    }

    /* access modifiers changed from: package-private */
    public String getSystemFontSize(Context context) {
        return String.valueOf(context.getResources().getConfiguration().fontScale);
    }

    private boolean isMocked(Context context, Location location) {
        return location.isFromMockProvider();
    }

    static boolean isNetworkRoaming(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.isNetworkRoaming();
        }
        return false;
    }

    static String getCarrierOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return PERMISSION_DISABLED;
    }

    static Map<String, String> getDeviceAttributes(Context context) {
        HashMap hashMap = new HashMap();
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
            context.getSystemService("phone");
            hashMap.put("device_id", BaseConfig.getAdvertisingId(context));
            hashMap.put("sim_serial_number", PERMISSION_DISABLED);
            hashMap.put("build_unique_id", UUID.randomUUID().toString());
        } else {
            hashMap.put("device_id", PERMISSION_DISABLED);
            hashMap.put("sim_serial_number", PERMISSION_DISABLED);
        }
        hashMap.put("device_manufacturer", Build.MANUFACTURER);
        hashMap.put("device_model", Build.MODEL);
        return hashMap;
    }

    static String getWifiSSID(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
            return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID();
        }
        return PERMISSION_DISABLED;
    }

    static String buildSerial() {
        return Build.SERIAL;
    }

    static void fetchIP(RzpJSONCallback rzpJSONCallback) {
        new Thread(new l_$w$(rzpJSONCallback)).start();
    }

    static void getDeviceParamValues(Context context, RzpJSONCallback rzpJSONCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            fetchIP(new c__C_());
            AdvertisingIdUtil.getId(context, new E$_6$(jSONObject, context, rzpJSONCallback));
        } catch (Exception e) {
            AnalyticsUtil.reportError(e.getMessage(), "S2", e.getMessage());
        }
    }

    static void getSignalStrength(Context context) {
        O_$B_ o_$b_ = new O_$B_();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            telephonyManager.listen(o_$b_, 256);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public static JSONObject getResponseJson(HttpsURLConnection httpsURLConnection) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return new JSONObject(sb.toString());
            }
        }
    }

    static String getGenericPaymentErrorResponse(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", "BAD_REQUEST_ERROR");
            jSONObject.put("description", str);
            jSONObject.put("source", "customer");
            jSONObject.put("step", "payment_authentication");
            jSONObject.put("reason", "payment_timeout");
            return new JSONObject().put("error", jSONObject).toString();
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
            return null;
        }
    }

    static String getGenericPaymentErrorResponse(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", "BAD_REQUEST_ERROR");
            jSONObject.put("description", str);
            jSONObject.put("source", "customer");
            jSONObject.put("step", "payment_authentication");
            jSONObject.put("reason", "payment_error");
            if (str2 != null) {
                jSONObject.put("metadata", new JSONObject(str2));
            }
            return new JSONObject().put("error", jSONObject).toString();
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
            return null;
        }
    }

    static String getPaymentCancelledResponse(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", "BAD_REQUEST_ERROR");
            jSONObject.put("description", "Payment processing cancelled by user");
            jSONObject.put("source", "customer");
            jSONObject.put("step", "payment_authentication");
            jSONObject.put("reason", "payment_cancelled");
            if (str != null) {
                if (str.startsWith("pay")) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("payment_id", str);
                    jSONObject.put("metadata", jSONObject2);
                } else {
                    jSONObject.put("metadata", new JSONObject(str));
                }
            }
            return new JSONObject().put("error", jSONObject).toString();
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e.getMessage(), "S0", e.getLocalizedMessage());
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkGpayCardsUpiRegistered(android.app.Activity r2, java.lang.String r3) {
        /*
            if (r3 != 0) goto L_0x0005
            boolean r2 = isGpayCardsUpiRegistered
            return r2
        L_0x0005:
            r2 = 0
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002a }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x002a }
            java.lang.String r3 = "code"
            java.lang.String r3 = r0.getString(r3)     // Catch:{ JSONException -> 0x002a }
            r0 = -1
            int r1 = r3.hashCode()     // Catch:{ JSONException -> 0x002a }
            switch(r1) {
                case -1534821982: goto L_0x001a;
                default: goto L_0x0019;
            }     // Catch:{ JSONException -> 0x002a }
        L_0x0019:
            goto L_0x0023
        L_0x001a:
            java.lang.String r1 = "google_pay"
            boolean r3 = r3.equals(r1)     // Catch:{ JSONException -> 0x002a }
            if (r3 == 0) goto L_0x0019
            r0 = r2
        L_0x0023:
            switch(r0) {
                case 0: goto L_0x0027;
                default: goto L_0x0026;
            }     // Catch:{ JSONException -> 0x002a }
        L_0x0026:
            return r2
        L_0x0027:
            boolean r2 = isGpayCardsUpiRegistered     // Catch:{ JSONException -> 0x002a }
            return r2
        L_0x002a:
            r3 = move-exception
            r3.printStackTrace()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.checkGpayCardsUpiRegistered(android.app.Activity, java.lang.String):boolean");
    }

    static void setIsGpayCardsUpiRegistered(boolean z) {
        isGpayCardsUpiRegistered = z;
    }
}
