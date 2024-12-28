package com.razorpay;

/* compiled from: CheckoutPresenterImpl */
final class c__h$ implements Callback {
    c__h$(CheckoutPresenterImpl checkoutPresenterImpl) {
    }

    public final void run(ResponseObject responseObject) {
        new StringBuilder("API Cancel hit: ").append(responseObject.getResponseResult());
    }
}
