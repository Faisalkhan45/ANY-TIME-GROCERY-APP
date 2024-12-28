package com.razorpay;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PrimaryWebChromeClient */
final class d__B_ extends WebChromeClient {
    private CheckoutPresenter a;

    public d__B_(CheckoutPresenter checkoutPresenter) {
        this.a = checkoutPresenter;
    }

    public final void onProgressChanged(WebView webView, int i) {
        this.a.onProgressChanges(1, i);
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.messageLevel() != ConsoleMessage.MessageLevel.ERROR) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", consoleMessage.message());
        hashMap.put("source_id", consoleMessage.sourceId());
        hashMap.put("line_number", String.valueOf(consoleMessage.lineNumber()));
        AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_JS_ERROR, AnalyticsUtil.getJSONResponse((Map<String, Object>) hashMap));
        Log.e("com.razorpay.checkout", "Webview JS Error: " + consoleMessage.message());
        return false;
    }
}
