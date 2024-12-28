package com.razorpay;

import androidx.core.os.EnvironmentCompat;

enum NetworkType {
    WIFI("wifi"),
    CELLULAR("cellular"),
    BLUETOOTH("bluetooth"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
    
    private String mNetworkTypeName;

    private NetworkType(String str) {
        this.mNetworkTypeName = str;
    }

    /* access modifiers changed from: package-private */
    public final String getNetworkTypeName() {
        return this.mNetworkTypeName;
    }
}
