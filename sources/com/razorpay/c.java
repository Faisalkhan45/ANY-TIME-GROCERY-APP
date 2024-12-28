package com.razorpay;

import android.view.WindowManager;

/* compiled from: RzpAssist */
final class c implements Callback {
    c(RzpAssist rzpAssist) {
    }

    public final void run(ResponseObject responseObject) {
        new StringBuilder("API Metadata: ").append(responseObject.getResponseResult());
    }

    /* compiled from: CheckoutPresenterImpl */
    final class _2_ implements Runnable {
        private /* synthetic */ int a;
        private /* synthetic */ int b;
        private /* synthetic */ CheckoutPresenterImpl c;

        _2_(CheckoutPresenterImpl checkoutPresenterImpl, int i, int i2) {
            this.c = checkoutPresenterImpl;
            this.a = i;
            this.b = i2;
        }

        public final void run() {
            WindowManager.LayoutParams attributes = this.c.activity.getWindow().getAttributes();
            new StringBuilder("Height:").append(this.a);
            new StringBuilder("Width:").append(this.b);
            attributes.height = b.a(this.c.activity, this.a);
            attributes.width = b.a(this.c.activity, this.b);
            this.c.activity.getWindow().setAttributes(attributes);
        }
    }
}
