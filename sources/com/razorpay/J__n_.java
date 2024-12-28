package com.razorpay;

import com.razorpay.CheckoutUtils;

/* compiled from: CheckoutPresenterImpl */
final class J__n_ implements CheckoutUtils.BackButtonDialogCallback {
    private /* synthetic */ H$$i_ a;

    J__n_(H$$i_ h$$i_) {
        this.a = h$$i_;
    }

    public final void onPositiveButtonClick() {
        this.a.a.view.loadUrl(1, String.format("javascript: CheckoutBridge.isPositiveButtonClicked({isClicked: %s})", new Object[]{Boolean.TRUE}));
    }

    public final void onNegativeButtonClick() {
        this.a.a.view.loadUrl(1, String.format("javascript: CheckoutBridge.isPositiveButtonClicked({isClicked: %s})", new Object[]{Boolean.FALSE}));
    }
}
