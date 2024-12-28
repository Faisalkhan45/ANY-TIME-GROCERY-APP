package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class H$$i_ implements Runnable {
    final /* synthetic */ CheckoutPresenterImpl a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;
    private /* synthetic */ String d;

    H$$i_(CheckoutPresenterImpl checkoutPresenterImpl, String str, String str2, String str3) {
        this.a = checkoutPresenterImpl;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final void run() {
        CheckoutUtils.a(this.a.activity, this.b, this.c, this.d, new J__n_(this));
    }
}
