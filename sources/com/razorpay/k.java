package com.razorpay;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* compiled from: SecondaryWebChromeClient */
final class k extends WebChromeClient {
    private CheckoutPresenter a;

    public k(CheckoutPresenter checkoutPresenter) {
        this.a = checkoutPresenter;
    }

    public final void onProgressChanged(WebView webView, int i) {
        this.a.onProgressChanges(2, i);
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }
}
