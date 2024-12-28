package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class z$_w$ implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ CheckoutPresenterImpl c;

    z$_w$(CheckoutPresenterImpl checkoutPresenterImpl, int i, String str) {
        this.c = checkoutPresenterImpl;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        switch (this.a) {
            case 1:
                this.c.view.loadUrl(1, String.format("javascript: handleRelay(%s)", new Object[]{this.b}));
                return;
            case 2:
                this.c.view.loadUrl(2, String.format("javascript: Magic.handleRelay(%s)", new Object[]{this.b}));
                return;
            default:
                return;
        }
    }
}
