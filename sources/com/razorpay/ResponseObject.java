package com.razorpay;

import java.util.List;
import java.util.Map;

class ResponseObject {
    private Map<String, List<String>> headers;
    private int responseCode;
    private String responseResult;

    ResponseObject() {
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public String getResponseResult() {
        return this.responseResult;
    }

    public void setResponseResult(String str) {
        this.responseResult = str;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, List<String>> map) {
        this.headers = map;
    }
}
