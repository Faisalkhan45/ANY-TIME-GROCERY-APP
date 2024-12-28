package com.razorpay;

/* compiled from: OtpElfData */
final class c_$W_ implements Callback {
    private /* synthetic */ String a;
    private /* synthetic */ OtpElfData b;

    c_$W_(OtpElfData otpElfData, String str) {
        this.b = otpElfData;
        this.a = str;
    }

    public final void run(ResponseObject responseObject) {
        String decryptFile;
        if (responseObject.getResponseResult() != null && (decryptFile = BaseUtils.decryptFile(responseObject.getResponseResult())) != null) {
            if (BaseUtils.storeFileInInternal(this.b.activity, BaseUtils.getVersionedAssetName(this.a, D$$l_.a().getOTPElfJsFileName()), responseObject.getResponseResult())) {
                String unused = this.b.otpElfJs = decryptFile;
                BaseUtils.updateLocalVersion(this.b.activity, OtpElfData.versionKey, this.a);
                return;
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.OTPELF_LOCAL_SAVE_FAILED);
        }
    }
}
