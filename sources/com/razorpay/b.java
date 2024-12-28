package com.razorpay;

import android.content.Context;
import android.util.TypedValue;
import com.razorpay.CheckoutBridge;

/* compiled from: ResourceUtils */
final class b {
    static boolean a(Context context) {
        return context.getResources().getBoolean(R.bool.isTablet);
    }

    static int a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    /* compiled from: CheckoutPresenterImpl */
    final class _f_ implements Runnable {
        private /* synthetic */ int a;
        private /* synthetic */ CheckoutBridge.WebViewSafeCheckCallback b;
        private /* synthetic */ CheckoutPresenterImpl c;

        _f_(CheckoutPresenterImpl checkoutPresenterImpl, int i, CheckoutBridge.WebViewSafeCheckCallback webViewSafeCheckCallback) {
            this.c = checkoutPresenterImpl;
            this.a = i;
            this.b = webViewSafeCheckCallback;
        }

        public final void run() {
            this.c.executeWebViewCallback(this.a, this.b);
        }
    }
}
