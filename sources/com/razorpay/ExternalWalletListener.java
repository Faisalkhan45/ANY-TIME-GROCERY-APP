package com.razorpay;

public interface ExternalWalletListener {
    void onExternalWalletSelected(String str, PaymentData paymentData);
}
