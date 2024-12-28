package com.razorpay;

/* compiled from: RzpAssist */
final class d implements Runnable {
    private /* synthetic */ boolean a;
    private /* synthetic */ RzpAssist b;

    d(RzpAssist rzpAssist, boolean z) {
        this.b = rzpAssist;
        this.a = z;
    }

    public final void run() {
        this.b.webview.getSettings().setUseWideViewPort(this.a);
    }
}
