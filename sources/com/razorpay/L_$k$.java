package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class L_$k$ implements Runnable {
    private /* synthetic */ CheckoutPresenterImpl a;

    L_$k$(CheckoutPresenterImpl checkoutPresenterImpl) {
        this.a = checkoutPresenterImpl;
    }

    public final void run() {
        this.a.helpersReset();
        this.a.loadForm("");
    }
}
