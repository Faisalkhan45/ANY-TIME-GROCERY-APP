package com.razorpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.razorpay.CheckoutPresenterImpl;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BaseCheckoutActivity */
class b__J_ extends Activity implements CheckoutPresenterImpl.CheckoutView, SmsAgentInterface {
    private static int UPI_REQUEST_CODE = 99;
    protected Object checkoutBridgeObject;
    private RelativeLayout container;
    private ViewGroup parent;
    protected CheckoutPresenter presenter;
    private WebChromeClient primaryWebChromeClient;
    private WebView primaryWebView;
    private WebViewClient primaryWebViewClient;
    private o$_b$ rzpbar;
    private WebChromeClient secondaryWebChromeClient;
    private WebView secondaryWebView;
    private WebViewClient secondaryWebViewClient;
    private m smsAgent;

    b__J_() {
    }

    private void setWebViewClient(int i, WebViewClient webViewClient) {
        switch (i) {
            case 1:
                this.primaryWebViewClient = webViewClient;
                return;
            case 2:
                this.secondaryWebViewClient = webViewClient;
                return;
            default:
                return;
        }
    }

    private void setWebChromeClient(int i, WebChromeClient webChromeClient) {
        switch (i) {
            case 1:
                this.primaryWebChromeClient = webChromeClient;
                return;
            case 2:
                this.secondaryWebChromeClient = webChromeClient;
                return;
            default:
                return;
        }
    }

    public void onCreate(Bundle bundle) {
        boolean z;
        String str = g$_H$.b;
        try {
            if (!str.equalsIgnoreCase(l.a(this, "sdk_version"))) {
                l.b(this, "rzp_config_json", (String) null);
                l.b(this, "rzp_config_version", (String) null);
                l.b(this, "sdk_version", str);
            }
        } catch (NullPointerException e) {
            l.b(this, "rzp_config_json", (String) null);
            l.b(this, "rzp_config_version", (String) null);
            l.b(this, "sdk_version", str);
        }
        g$_H$.a().a((Context) this);
        BaseUtils.checkForLatestVersion(this, g$_H$.c);
        this.presenter.setCheckoutLoadStartAt();
        AnalyticsUtil.libraryType = "CHECKOUTJS";
        setWebViewClient(1, new PrimaryWebViewClient(this.presenter));
        setWebViewClient(2, new SecondaryWebViewClient(this.presenter));
        setWebChromeClient(1, new d__B_(this.presenter));
        setWebChromeClient(2, new k(this.presenter));
        checkSmsPermission();
        BaseUtils.setup();
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_INIT);
        requestWindowFeature(1);
        super.onCreate(bundle);
        int i = 0;
        if (bundle == null) {
            bundle = getIntent().getExtras();
            z = false;
        } else {
            z = true;
        }
        if (this.presenter.setOptions(bundle, z)) {
            this.parent = (ViewGroup) findViewById(16908290);
            createPrimaryWebView(this.checkoutBridgeObject);
            createSecondaryWebView();
            createContainer();
            this.presenter.loadForm("");
            this.presenter.passPrefillToSegment();
            if ((getWindow().getAttributes().flags & 1024) != 0) {
                Q_$2$.a((Activity) this);
            }
            if (!this.presenter.isAllowRotation()) {
                if (b.a(this)) {
                    setFinishOnTouchOutside(false);
                    WindowManager.LayoutParams attributes = getWindow().getAttributes();
                    int a = b.a(this, 375);
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int i2 = displayMetrics.heightPixels;
                    int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
                    if (identifier > 0) {
                        i = getResources().getDimensionPixelSize(identifier);
                    }
                    int i3 = i2 - i;
                    if (i3 > 600) {
                        i3 = b.a(this, 600);
                    }
                    attributes.height = i3;
                    attributes.width = a;
                    getWindow().setAttributes(attributes);
                } else {
                    setRequestedOrientation(1);
                }
                this.presenter.fetchCondfig();
                this.presenter.handleCardSaving();
                if (!BaseUtils.isDeviceHaveCorrectTlsVersion()) {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_TLS_ERROR);
                    destroy(6, "TLSv1  is not supported for security reasons");
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.presenter.saveInstanceState(bundle);
    }

    public void onBackPressed() {
        this.presenter.backPressed(new HashMap());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            this.presenter.unregisterReceivers();
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.ACTIVITY_ONDESTROY_CALLED);
        try {
            this.presenter.cleanUpOnDestroy();
        } catch (ConcurrentModificationException e) {
            AnalyticsUtil.reportError(getClass().getName(), "S0", e.getLocalizedMessage());
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void createPrimaryWebView(Object obj) {
        WebView webView = new WebView(this);
        this.primaryWebView = webView;
        BaseUtils.setWebViewSettings(this, webView, false);
        this.primaryWebView.clearFormData();
        this.primaryWebView.addJavascriptInterface(obj, "CheckoutBridge");
        this.primaryWebView.setWebChromeClient(this.primaryWebChromeClient);
        this.primaryWebView.setWebViewClient(this.primaryWebViewClient);
    }

    private void createSecondaryWebView() {
        WebView webView = new WebView(this);
        this.secondaryWebView = webView;
        BaseUtils.setWebViewSettings(this, webView, false);
        this.secondaryWebView.clearFormData();
        this.secondaryWebView.addJavascriptInterface(new P$_S_((CheckoutInteractor) this.presenter), "MagicBridge");
        this.secondaryWebView.addJavascriptInterface(new CheckoutBridge((CheckoutInteractor) this.presenter, 2), "CheckoutBridge");
        this.secondaryWebView.setVisibility(8);
        this.secondaryWebView.setWebChromeClient(this.secondaryWebChromeClient);
        this.secondaryWebView.setWebViewClient(this.secondaryWebViewClient);
    }

    private void createContainer() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.container = relativeLayout;
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.container.setBackgroundColor(-1);
        this.parent.addView(this.container);
        this.primaryWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.secondaryWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.primaryWebView.setContentDescription("primary_webview");
        this.secondaryWebView.setContentDescription("secondary_webview");
        this.container.addView(this.primaryWebView);
        this.container.addView(this.secondaryWebView);
        String progressBarColor = this.presenter.getProgressBarColor();
        if (progressBarColor != null) {
            this.rzpbar = new o$_b$(this, this.container, progressBarColor);
        } else {
            this.rzpbar = new o$_b$(this, this.container);
        }
        this.presenter.setUpAddOn();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            this.presenter.sendOtpPermissionCallback(true);
        }
        this.presenter.onActivityResultReceived(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.presenter.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void postSms(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sender", str);
            jSONObject.put("message", str2);
            loadUrl(1, String.format("OTPElf.showOTP('%s','%s')", new Object[]{str2, str}));
        } catch (JSONException e) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e.getMessage());
            e.printStackTrace();
        }
    }

    public void setSmsPermission(boolean z) {
        this.presenter.sendOtpPermissionCallback(z);
        m mVar = this.smsAgent;
        if (mVar != null) {
            mVar.b((SmsAgentInterface) this);
        }
    }

    public void loadUrl(int i, String str) {
        switch (i) {
            case 1:
                this.primaryWebView.loadUrl(str);
                return;
            case 2:
                this.secondaryWebView.loadUrl(str);
                return;
            default:
                return;
        }
    }

    public void checkSmsPermission() {
        m a = m.a();
        this.smsAgent = a;
        a.a((SmsAgentInterface) this);
        this.smsAgent.a((Activity) this);
    }

    public void addJavascriptInterfaceToPrimaryWebview(Object obj, String str) {
        this.primaryWebView.addJavascriptInterface(obj, str);
    }

    public void loadData(int i, String str, String str2, String str3) {
        switch (i) {
            case 1:
                this.primaryWebView.loadData(str, str2, str3);
                return;
            case 2:
                this.secondaryWebView.loadData(str, str2, str3);
                return;
            default:
                return;
        }
    }

    public void loadDataWithBaseURL(int i, String str, String str2, String str3, String str4, String str5) {
        switch (i) {
            case 1:
                this.primaryWebView.loadDataWithBaseURL(str, str2, str3, str4, str5);
                return;
            case 2:
                this.secondaryWebView.loadDataWithBaseURL(str, str2, str3, str4, str5);
                return;
            default:
                return;
        }
    }

    public void makeWebViewVisible(int i) {
        switch (i) {
            case 1:
                if (this.primaryWebView.getVisibility() == 8) {
                    this.primaryWebView.setVisibility(0);
                    this.secondaryWebView.setVisibility(8);
                    CheckoutUtils.a();
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_SECONDARY_TO_PRIMARY_SWITCH);
                    return;
                }
                return;
            case 2:
                if (this.secondaryWebView.getVisibility() == 8) {
                    this.primaryWebView.setVisibility(8);
                    this.secondaryWebView.setVisibility(0);
                    CheckoutUtils.a();
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_PRIMARY_TO_SECONDARY_SWITCH);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isWebViewVisible(int r2) {
        /*
            r1 = this;
            r0 = 1
            switch(r2) {
                case 1: goto L_0x0011;
                case 2: goto L_0x0006;
                default: goto L_0x0005;
            }
        L_0x0005:
            goto L_0x001c
        L_0x0006:
            android.webkit.WebView r2 = r1.secondaryWebView
            if (r2 == 0) goto L_0x001c
            int r2 = r2.getVisibility()
            if (r2 != 0) goto L_0x001c
            goto L_0x001d
        L_0x0011:
            android.webkit.WebView r2 = r1.primaryWebView
            if (r2 == 0) goto L_0x001c
            int r2 = r2.getVisibility()
            if (r2 != 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.b__J_.isWebViewVisible(int):boolean");
    }

    public void showToast(String str, int i) {
        Toast.makeText(this, str, i).show();
    }

    public void destroy(int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("RESULT", str);
        if (str == null || TextUtils.isEmpty(str)) {
            i = 5;
        }
        setResult(i, intent);
        finish();
    }

    public void showProgressBar(int i) {
        o$_b$ o__b_ = this.rzpbar;
        if (o__b_ != null) {
            o__b_.a(i);
        }
    }

    public void hideProgressBar() {
        o$_b$ o__b_ = this.rzpbar;
        if (o__b_ != null) {
            o__b_.a();
        }
    }

    public void clearWebViewHistory(int i) {
        switch (i) {
            case 1:
                this.primaryWebView.clearHistory();
                return;
            case 2:
                this.secondaryWebView.clearHistory();
                return;
            default:
                return;
        }
    }

    public WebView getWebView(int i) {
        switch (i) {
            case 1:
                return this.primaryWebView;
            case 2:
                return this.secondaryWebView;
            default:
                return null;
        }
    }
}
