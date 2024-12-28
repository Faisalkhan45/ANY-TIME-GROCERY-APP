package com.razorpay;

import android.content.Context;

public class AdvertisingIdUtil {

    interface AdvertisingIdCallback {
        void onResult(String str);
    }

    static void getId(Context context, AdvertisingIdCallback advertisingIdCallback) {
        new a_$P$(context, advertisingIdCallback).execute(new Void[0]);
    }
}
