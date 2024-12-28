package com.razorpay;

import org.json.JSONObject;

class AnalyticsProperty {
    Scope scope;
    Object value;

    enum Scope {
        PAYMENT,
        ORDER
    }

    AnalyticsProperty(int i, Scope scope2) {
        this.value = Integer.valueOf(i);
        this.scope = scope2;
    }

    AnalyticsProperty(long j, Scope scope2) {
        this.value = Long.valueOf(j);
        this.scope = scope2;
    }

    AnalyticsProperty(String str, Scope scope2) {
        this.value = str;
        this.scope = scope2;
    }

    AnalyticsProperty(boolean z, Scope scope2) {
        this.value = Boolean.valueOf(z);
        this.scope = scope2;
    }

    AnalyticsProperty(JSONObject jSONObject, Scope scope2) {
        this.value = jSONObject;
        this.scope = scope2;
    }
}
