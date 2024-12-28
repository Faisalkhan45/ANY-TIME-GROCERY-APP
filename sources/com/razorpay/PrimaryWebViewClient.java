package com.razorpay;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class PrimaryWebViewClient extends WebViewClient {
    CheckoutPresenter presenter;

    public PrimaryWebViewClient(CheckoutPresenter checkoutPresenter) {
        this.presenter = checkoutPresenter;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.presenter.destroyActivity(2, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        webView.setTag(str);
        this.presenter.onPageStarted(1, webView, str);
    }

    public void onPageFinished(WebView webView, String str) {
        this.presenter.onPageFinished(1, webView, str);
    }
}
