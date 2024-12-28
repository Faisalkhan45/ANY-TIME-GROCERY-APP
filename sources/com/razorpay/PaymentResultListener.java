package com.razorpay;

public interface PaymentResultListener {
    void onPaymentError(int i, String str);

    void onPaymentSuccess(String str);
}
