package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class T_$Z$ implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ int b;
    private /* synthetic */ CheckoutPresenterImpl c;

    T_$Z$(CheckoutPresenterImpl checkoutPresenterImpl, String str, int i) {
        this.c = checkoutPresenterImpl;
        this.a = str;
        this.b = i;
    }

    public final void run() {
        this.c.view.showToast(this.a, this.b);
    }
}
