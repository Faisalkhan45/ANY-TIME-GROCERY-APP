package com.razorpay;

/* compiled from: RazorpayExceptionHandler */
final class U_$B$ extends Thread {
    private /* synthetic */ Throwable a;
    private /* synthetic */ J$_M_ b;

    U_$B$(J$_M_ j$_m_, Throwable th) {
        this.b = j$_m_;
        this.a = th;
    }

    public final void run() {
        AnalyticsUtil.reportUncaughtException(this.a);
        AnalyticsUtil.saveEventsToPreferences(this.b.a);
    }
}
