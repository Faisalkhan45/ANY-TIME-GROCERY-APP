package com.razorpay;

import java.io.Serializable;
import org.json.JSONObject;

public class PaymentData implements Serializable {
    private JSONObject data = new JSONObject();
    private String externalWallet;
    private String orderId;
    private String paymentId;
    private String signature;
    private String userContact;
    private String userEmail;

    public String getUserEmail() {
        return this.userEmail;
    }

    /* access modifiers changed from: package-private */
    public void setUserEmail(String str) {
        this.userEmail = str;
    }

    public String getUserContact() {
        return this.userContact;
    }

    /* access modifiers changed from: package-private */
    public void setUserContact(String str) {
        this.userContact = str;
    }

    /* access modifiers changed from: package-private */
    public void setPaymentId(String str) {
        this.paymentId = str;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    /* access modifiers changed from: package-private */
    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: package-private */
    public void setSignature(String str) {
        this.signature = str;
    }

    /* access modifiers changed from: package-private */
    public void setData(JSONObject jSONObject) {
        this.data = jSONObject;
    }

    public JSONObject getData() {
        return this.data;
    }

    /* access modifiers changed from: package-private */
    public void setExternalWallet(String str) {
        this.externalWallet = str;
    }

    public String getExternalWallet() {
        return this.externalWallet;
    }
}
