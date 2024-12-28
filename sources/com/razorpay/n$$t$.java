package com.razorpay;

/* compiled from: Lumberjack */
final class n$$t$ implements Callback {
    n$$t$() {
    }

    public final void run(ResponseObject responseObject) {
        new StringBuilder("Response from lumberjack: ").append(responseObject.getResponseResult());
    }
}
