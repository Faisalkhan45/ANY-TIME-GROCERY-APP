package com.razorpay;

import android.webkit.WebView;

interface AddOnPresenter {
    void onPageFinished(int i, WebView webView, String str);

    void onPageStarted(int i, WebView webView, String str);

    void onProgressChanges(int i, int i2);
}
