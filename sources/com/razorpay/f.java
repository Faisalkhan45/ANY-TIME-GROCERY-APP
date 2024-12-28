package com.razorpay;

import android.widget.Toast;

/* compiled from: RzpAssist */
final class f implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ RzpAssist b;

    f(RzpAssist rzpAssist, String str) {
        this.b = rzpAssist;
        this.a = str;
    }

    public final void run() {
        Toast.makeText(this.b.activity, this.a, 1).show();
    }
}
