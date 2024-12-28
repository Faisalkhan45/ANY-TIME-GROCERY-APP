package com.razorpay;

class RzpPluginCompatibilityResponse {
    private String errorMessage;
    private boolean isCompatible;

    RzpPluginCompatibilityResponse(boolean z, String str) {
        this.isCompatible = z;
        this.errorMessage = str;
    }

    public boolean isCompatible() {
        return this.isCompatible;
    }

    public String getErrorMessage() {
        String str = this.errorMessage;
        if (str == null) {
            return "";
        }
        return str;
    }
}
