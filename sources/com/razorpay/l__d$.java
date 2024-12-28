package com.razorpay;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* compiled from: Checkout */
final class l__d$ extends WebChromeClient {
    l__d$() {
    }

    public final void onProgressChanged(WebView webView, int i) {
        new StringBuilder("Preload progress: ").append(i);
    }
}
