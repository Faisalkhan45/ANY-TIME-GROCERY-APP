package com.razorpay;

import android.util.Log;

/* compiled from: OtpElfData */
final class e_$r$ implements Callback {
    private /* synthetic */ OtpElfData a;

    e_$r$(OtpElfData otpElfData) {
        this.a = otpElfData;
    }

    public final void run(ResponseObject responseObject) {
        if (responseObject.getResponseResult() != null) {
            try {
                String versionFromJsonString = BaseUtils.getVersionFromJsonString(responseObject.getResponseResult(), OtpElfData.versionKey);
                String localVersion = BaseUtils.getLocalVersion(this.a.activity, OtpElfData.versionKey);
                if (!localVersion.equals(versionFromJsonString)) {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.OTPELF_UPDATE_CALLED);
                    this.a.updateOtpElf(versionFromJsonString);
                    return;
                }
                new StringBuilder("OTPElf on latest version: ").append(localVersion);
            } catch (Exception e) {
                AnalyticsUtil.reportError(getClass().getName(), "S1", "Could not extract version from server json");
                Log.e("com.razorpay.checkout", "Could not extract version from server json");
            }
        }
    }
}
