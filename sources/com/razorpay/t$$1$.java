package com.razorpay;

import android.content.DialogInterface;
import com.razorpay.CheckoutUtils;

/* compiled from: CheckoutUtils */
final class t$$1$ implements DialogInterface.OnClickListener {
    private /* synthetic */ CheckoutUtils.BackButtonDialogCallback a;

    t$$1$(CheckoutUtils.BackButtonDialogCallback backButtonDialogCallback) {
        this.a = backButtonDialogCallback;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.onPositiveButtonClick();
    }
}
