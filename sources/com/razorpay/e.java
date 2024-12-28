package com.razorpay;

import android.view.inputmethod.InputMethodManager;

/* compiled from: RzpAssist */
final class e implements Runnable {
    private /* synthetic */ RzpAssist a;

    e(RzpAssist rzpAssist) {
        this.a = rzpAssist;
    }

    public final void run() {
        ((InputMethodManager) this.a.activity.getSystemService("input_method")).showSoftInput(this.a.webview, 0);
    }
}
