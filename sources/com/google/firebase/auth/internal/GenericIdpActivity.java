package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.p002firebaseauthapi.zzaay;
import com.google.android.gms.internal.p002firebaseauthapi.zzf;
import com.google.android.gms.internal.p002firebaseauthapi.zzxe;
import com.google.android.gms.internal.p002firebaseauthapi.zzxf;
import com.google.android.gms.internal.p002firebaseauthapi.zzxg;
import com.google.android.gms.internal.p002firebaseauthapi.zzyz;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class GenericIdpActivity extends FragmentActivity implements zzxg {
    private static long zzb = 0;
    private static final zzbm zzc = zzbm.zzc();
    private final Executor zzd = zzf.zza().zza(1);
    private boolean zze = false;

    private final void zzh() {
        zzb = 0;
        this.zze = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzc.zzf(this, zzai.zza("WEB_CONTEXT_CANCELED"));
        } else {
            zzc.zzd(this);
        }
        finish();
    }

    private final void zzi(Status status) {
        zzb = 0;
        this.zze = false;
        Intent intent = new Intent();
        zzbl.zzc(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzc.zzf(getApplicationContext(), status);
        } else {
            zzc.zzd(this);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(action) || "android.intent.action.VIEW".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzb < 30000) {
                Log.e("GenericIdpActivity", "Could not start operation - already in progress");
                return;
            }
            zzb = currentTimeMillis;
            if (bundle != null) {
                this.zze = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        Log.e("GenericIdpActivity", "Could not do operation - unknown action: ".concat(String.valueOf(action)));
        zzh();
    }

    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        super.onResume();
        if ("android.intent.action.VIEW".equals(getIntent().getAction())) {
            Intent intent = getIntent();
            if (intent.hasExtra("firebaseError")) {
                zzi(zzbl.zzb(intent.getStringExtra("firebaseError")));
            } else if (!intent.hasExtra("link") || !intent.hasExtra("eventId")) {
                zzh();
            } else {
                String stringExtra = intent.getStringExtra("link");
                String stringExtra2 = intent.getStringExtra("eventId");
                String packageName = getPackageName();
                boolean booleanExtra = intent.getBooleanExtra("encryptionEnabled", true);
                zzi zza = zzj.zzb().zza(this, packageName, stringExtra2);
                if (zza == null) {
                    zzh();
                }
                if (booleanExtra) {
                    stringExtra = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(zza.zza()).getPersistenceKey()).zzb(stringExtra);
                }
                zzaay zzaay = new zzaay(zza, stringExtra);
                String zze2 = zza.zze();
                String zzb2 = zza.zzb();
                zzaay.zzf(zze2);
                if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(zzb2)) {
                    zzb = 0;
                    this.zze = false;
                    Intent intent2 = new Intent();
                    SafeParcelableSerializer.serializeToIntentExtra(zzaay, intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST");
                    intent2.putExtra("com.google.firebase.auth.internal.OPERATION", zzb2);
                    intent2.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
                    if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent2)) {
                        SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
                        edit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzaay));
                        edit.putString("operation", zzb2);
                        edit.putString("tenantId", zze2);
                        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
                        edit.commit();
                    } else {
                        zzc.zzd(this);
                    }
                    finish();
                    return;
                }
                Log.e("GenericIdpActivity", "unsupported operation: ".concat(zzb2));
                zzh();
            }
        } else if (!this.zze) {
            String packageName2 = getPackageName();
            try {
                String lowerCase = Hex.bytesToStringUppercase(AndroidUtilsLight.getPackageCertificateHashBytes(this, packageName2)).toLowerCase(Locale.US);
                FirebaseApp instance = FirebaseApp.getInstance(getIntent().getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME"));
                if (!zzyz.zzg(instance)) {
                    new zzxe(packageName2, lowerCase, getIntent(), instance, this).executeOnExecutor(this.zzd, new Void[0]);
                } else {
                    zzf(zzg(Uri.parse(zzyz.zza(instance.getOptions().getApiKey())).buildUpon(), getIntent(), packageName2, lowerCase).build(), packageName2);
                }
            } catch (PackageManager.NameNotFoundException e) {
                String obj = e.toString();
                Log.e("GenericIdpActivity", "Could not get package signature: " + packageName2 + " " + obj);
                zze(packageName2, (Status) null);
            }
            this.zze = true;
        } else {
            zzh();
        }
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zze);
    }

    public final Context zza() {
        return getApplicationContext();
    }

    public final Uri.Builder zzb(Intent intent, String str, String str2) {
        return zzg(new Uri.Builder().scheme("https").appendPath("__").appendPath("auth").appendPath("handler"), intent, str, str2);
    }

    public final String zzc(String str) {
        return zzyz.zzb(str);
    }

    public final HttpURLConnection zzd(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("GenericIdpActivity", "Error generating URL connection");
            return null;
        }
    }

    public final void zze(String str, Status status) {
        if (status == null) {
            zzh();
        } else {
            zzi(status);
        }
    }

    public final void zzf(Uri uri, String str) {
        if (getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW"), 0) != null) {
            List<ResolveInfo> queryIntentServices = getPackageManager().queryIntentServices(new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION), 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.putExtra("com.android.browser.application_id", str);
                Log.i("GenericIdpActivity", "Opening IDP Sign In link in a browser window.");
                intent.addFlags(1073741824);
                intent.addFlags(268435456);
                startActivity(intent);
                return;
            }
            CustomTabsIntent build = new CustomTabsIntent.Builder().build();
            Log.i("GenericIdpActivity", "Opening IDP Sign In link in a custom chrome tab.");
            build.launchUrl(this, uri);
            return;
        }
        Log.e("GenericIdpActivity", "Device cannot resolve intent for: android.intent.action.VIEW");
        zze(str, (Status) null);
    }

    public final Uri.Builder zzg(Uri.Builder builder, Intent intent, String str, String str2) {
        String str3;
        String str4;
        Uri.Builder builder2 = builder;
        Intent intent2 = intent;
        String stringExtra = intent2.getStringExtra("com.google.firebase.auth.KEY_API_KEY");
        String stringExtra2 = intent2.getStringExtra("com.google.firebase.auth.KEY_PROVIDER_ID");
        String stringExtra3 = intent2.getStringExtra("com.google.firebase.auth.KEY_TENANT_ID");
        String stringExtra4 = intent2.getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME");
        ArrayList<String> stringArrayListExtra = intent2.getStringArrayListExtra("com.google.firebase.auth.KEY_PROVIDER_SCOPES");
        if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
            str3 = null;
        } else {
            str3 = TextUtils.join(",", stringArrayListExtra);
        }
        Bundle bundleExtra = intent2.getBundleExtra("com.google.firebase.auth.KEY_PROVIDER_CUSTOM_PARAMS");
        if (bundleExtra == null) {
            str4 = null;
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                for (String str5 : bundleExtra.keySet()) {
                    String string = bundleExtra.getString(str5);
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put(str5, string);
                    }
                }
            } catch (JSONException e) {
                Log.e("GenericIdpActivity", "Unexpected JSON exception when serializing developer specified custom params");
            }
            str4 = jSONObject.toString();
        }
        String uuid = UUID.randomUUID().toString();
        String zza = zzxf.zza(this, UUID.randomUUID().toString());
        String action = intent.getAction();
        String stringExtra5 = intent2.getStringExtra("com.google.firebase.auth.internal.CLIENT_VERSION");
        String str6 = uuid;
        String str7 = zza;
        String str8 = str4;
        String str9 = zza;
        String str10 = action;
        String str11 = uuid;
        String str12 = stringExtra2;
        String str13 = stringExtra2;
        String str14 = "GenericIdpActivity";
        String str15 = str3;
        zzj.zzb().zzd(getApplicationContext(), str, str6, str7, str10, str12, stringExtra3, stringExtra4);
        String zzc2 = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(stringExtra4).getPersistenceKey()).zzc();
        if (TextUtils.isEmpty(zzc2)) {
            Log.e(str14, "Could not generate an encryption key for Generic IDP - cancelling flow.");
            zzi(zzai.zza("Failed to generate/retrieve public encryption key for Generic IDP flow."));
            return null;
        } else if (str9 == null) {
            return null;
        } else {
            builder2.appendQueryParameter("eid", "p").appendQueryParameter("v", "X".concat(String.valueOf(stringExtra5))).appendQueryParameter("authType", "signInWithRedirect").appendQueryParameter("apiKey", stringExtra).appendQueryParameter("providerId", str13).appendQueryParameter("sessionId", str9).appendQueryParameter("eventId", str11).appendQueryParameter("apn", str).appendQueryParameter("sha1Cert", str2).appendQueryParameter("publicKey", zzc2);
            if (!TextUtils.isEmpty(str15)) {
                builder2.appendQueryParameter("scopes", str15);
            }
            if (!TextUtils.isEmpty(str8)) {
                builder2.appendQueryParameter("customParameters", str8);
            }
            if (!TextUtils.isEmpty(stringExtra3)) {
                builder2.appendQueryParameter("tid", stringExtra3);
            }
            return builder2;
        }
    }
}
