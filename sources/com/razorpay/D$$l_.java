package com.razorpay;

/* compiled from: CoreConfig */
final class D$$l_ extends BaseConfig {
    private static BaseConfig a;

    private D$$l_() {
    }

    public static BaseConfig a() {
        if (a == null) {
            a = new D$$l_();
        }
        return a;
    }

    public static void a(BaseConfig baseConfig) {
        a = baseConfig;
    }
}
