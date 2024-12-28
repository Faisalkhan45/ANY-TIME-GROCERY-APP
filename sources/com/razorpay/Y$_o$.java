package com.razorpay;

import android.content.Context;
import com.razorpay.AdvertisingIdUtil;

/* compiled from: BaseConfig */
final class Y$_o$ implements AdvertisingIdUtil.AdvertisingIdCallback {
    private /* synthetic */ Context a;

    Y$_o$(Context context) {
        this.a = context;
    }

    public final void onResult(String str) {
        BaseConfig.setAdvertisingId(this.a, str);
    }
}
