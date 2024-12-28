package com.razorpay;

/* compiled from: Lumberjack */
final /* synthetic */ class M$_J_ {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NetworkType.values().length];
        a = iArr;
        try {
            iArr[NetworkType.WIFI.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[NetworkType.CELLULAR.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[NetworkType.BLUETOOTH.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
