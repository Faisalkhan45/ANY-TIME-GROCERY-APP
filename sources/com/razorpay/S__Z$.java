package com.razorpay;

/* compiled from: MagicData */
final class S__Z$ implements Callback {
    private /* synthetic */ p$$q_ a;

    S__Z$(p$$q_ p__q_) {
        this.a = p__q_;
    }

    public final void run(ResponseObject responseObject) {
        if (responseObject.getResponseResult() != null) {
            try {
                String versionFromJsonString = BaseUtils.getVersionFromJsonString(responseObject.getResponseResult(), p$$q_.b);
                if (!BaseUtils.getLocalVersion(this.a.a, p$$q_.b).equals(versionFromJsonString)) {
                    M$_3_.a(g$_H$.a().getMagicJsUrl(), new w_$E$(this.a, versionFromJsonString));
                }
            } catch (Exception e) {
                AnalyticsUtil.reportError(getClass().getName(), "S1", "Could not extract version from server json");
            }
        }
    }
}
