package com.razorpay;

import android.view.ViewTreeObserver;

/* compiled from: CheckoutUtils */
final class D$_X_ implements ViewTreeObserver.OnGlobalLayoutListener {
    private /* synthetic */ Q_$2$ a;

    D$_X_(Q_$2$ q_$2$) {
        this.a = q_$2$;
    }

    public final void onGlobalLayout() {
        Q_$2$.a(this.a);
    }
}
