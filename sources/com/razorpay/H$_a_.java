package com.razorpay;

import com.razorpay.CheckoutBridge;

/* compiled from: CheckoutBridge */
final class H$_a_ implements CheckoutBridge.WebViewSafeCheckCallback {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;
    private /* synthetic */ CheckoutBridge d;

    H$_a_(CheckoutBridge checkoutBridge, String str, String str2, String str3) {
        this.d = checkoutBridge;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void secure() {
        this.d.interactor.showAlertDialog(this.a, this.b, this.c);
    }

    public final void unSecure() {
    }
}
