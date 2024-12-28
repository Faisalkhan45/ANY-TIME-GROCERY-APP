package com.razorpay;

interface RzpInternalCallback {
    void onPaymentError(int i, String str);

    void onPaymentSuccess(String str);
}
