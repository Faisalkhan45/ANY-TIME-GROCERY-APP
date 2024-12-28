package com.razorpay;

public interface PluginCheckoutInteractor extends CheckoutInteractor {
    void processPayment(String str);

    void verifyGPayResponse(String str);
}
