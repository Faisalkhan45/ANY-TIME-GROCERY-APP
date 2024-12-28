package com.razorpay;

import android.content.Context;
import java.lang.Thread;

/* compiled from: RazorpayExceptionHandler */
final class J$_M_ implements Thread.UncaughtExceptionHandler {
    Context a;
    private Thread.UncaughtExceptionHandler b;

    private J$_M_(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.b = uncaughtExceptionHandler;
        this.a = context;
    }

    static void a(Context context) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof J$_M_)) {
            Thread.setDefaultUncaughtExceptionHandler(new J$_M_(context, defaultUncaughtExceptionHandler));
        }
    }

    static void a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof J$_M_) {
            Thread.setDefaultUncaughtExceptionHandler(((J$_M_) defaultUncaughtExceptionHandler).b);
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        new U_$B$(this, th).start();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
