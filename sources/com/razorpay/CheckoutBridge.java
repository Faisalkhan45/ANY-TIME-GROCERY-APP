package com.razorpay;

import android.webkit.JavascriptInterface;

class CheckoutBridge {
    /* access modifiers changed from: private */
    public String integratedPlugin;
    CheckoutInteractor interactor;
    /* access modifiers changed from: private */
    public boolean isRegistered = true;
    private int webViewType;

    interface WebViewSafeCheckCallback {
        void secure();

        void unSecure();
    }

    CheckoutBridge(CheckoutInteractor checkoutInteractor, int i) {
        this.interactor = checkoutInteractor;
        this.webViewType = i;
    }

    @JavascriptInterface
    public final void onload() {
        isWebViewSafeOnUI(new J$_0_(this));
    }

    @JavascriptInterface
    public void invokePopup(String str) {
        this.interactor.invokePopup(str);
        isWebViewSafeOnUI(new b_$A$(this, str));
    }

    @JavascriptInterface
    public void onCheckoutBackPress() {
        this.interactor.onCheckoutBackPress();
        isWebViewSafeOnUI(new y$_O_(this));
    }

    @JavascriptInterface
    public final void setAppToken(String str) {
        this.interactor.setAppToken(str);
        isWebViewSafeOnUI(new U$_z$(this, str));
    }

    @JavascriptInterface
    public final void setDeviceToken(String str) {
        isWebViewSafeOnUI(new I$_n_(this, str));
    }

    @JavascriptInterface
    public final void callNativeIntent(String str) {
        isWebViewSafeOnUI(new N$$J$(this, str));
    }

    @JavascriptInterface
    public final void callNativeIntent(String str, String str2) {
        isWebViewSafeOnUI(new S$_U_(this, str, str2));
    }

    @JavascriptInterface
    public final void setPaymentID(String str) {
        isWebViewSafeOnUI(new x_$Q_(this, str));
    }

    @JavascriptInterface
    public final void setMerchantOptions(String str) {
        isWebViewSafeOnUI(new n__T$(this, str));
    }

    @JavascriptInterface
    public final void onsubmit(String str) {
        isWebViewSafeOnUI(new O__Y_(this, str));
    }

    @JavascriptInterface
    public final void onfault(String str) {
        isWebViewSafeOnUI(new h__y_(this, str));
    }

    @JavascriptInterface
    public final void oncomplete(String str) {
        isWebViewSafeOnUI(new K$$z$(this, str));
    }

    @JavascriptInterface
    public final void setDimensions(int i, int i2) {
        isWebViewSafeOnUI(new B_$q$(this, i, i2));
    }

    @JavascriptInterface
    public final void ondismiss() {
        isWebViewSafeOnUI(new J$$A_(this));
    }

    @JavascriptInterface
    public final void ondismiss(String str) {
        isWebViewSafeOnUI(new T__j$(this, str));
    }

    @JavascriptInterface
    public final void requestExtraAnalyticsData() {
        isWebViewSafeOnUI(new Q$$U_(this));
    }

    @JavascriptInterface
    public final void onerror(String str) {
        isWebViewSafeOnUI(new I$_e_(this, str));
    }

    @JavascriptInterface
    public final boolean isUserRegisteredOnUPI(String str) {
        isWebViewSafe(new r_$Z$(this, str));
        return this.isRegistered;
    }

    @JavascriptInterface
    public final boolean isUserRegistered(String str) {
        isWebViewSafe(new Y_$B$(this, str));
        return this.isRegistered;
    }

    @JavascriptInterface
    public final void relay(String str) {
        isWebViewSafeOnUI(new L$$C_(this, str));
    }

    @JavascriptInterface
    public final void toast(String str, int i) {
        isWebViewSafeOnUI(new B$$J$(this, str, i));
    }

    @JavascriptInterface
    public final void showAlertDialog(String str, String str2, String str3) {
        isWebViewSafeOnUI(new H$_a_(this, str, str2, str3));
    }

    @JavascriptInterface
    public final String getSdkPlugins() {
        isWebViewSafe(new C__D$(this));
        return this.integratedPlugin;
    }

    /* access modifiers changed from: package-private */
    public void isWebViewSafeOnUI(WebViewSafeCheckCallback webViewSafeCheckCallback) {
        this.interactor.isWebViewSafeOnUI(this.webViewType, webViewSafeCheckCallback);
    }

    /* access modifiers changed from: package-private */
    public void isWebViewSafe(WebViewSafeCheckCallback webViewSafeCheckCallback) {
        this.interactor.isWebViewSafe(this.webViewType, webViewSafeCheckCallback);
    }

    @JavascriptInterface
    public final void onCheckoutRendered() {
        isWebViewSafe(new Q__v$(this));
    }

    @JavascriptInterface
    public final void getPdfString(String str, String str2) {
        isWebViewSafe(new t_$J_(this, str, str2));
    }
}
