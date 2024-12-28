package com.razorpay;

public interface PaymentResultWithDataListener {
    void onPaymentError(int i, String str, PaymentData paymentData);

    void onPaymentSuccess(String str, PaymentData paymentData);
}
