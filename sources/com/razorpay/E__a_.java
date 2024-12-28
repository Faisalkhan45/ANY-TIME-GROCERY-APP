package com.razorpay;

import android.content.DialogInterface;
import com.razorpay.CheckoutUtils;

/* compiled from: CheckoutUtils */
final class E__a_ implements DialogInterface.OnClickListener {
    private /* synthetic */ CheckoutUtils.BackButtonDialogCallback a;

    E__a_(CheckoutUtils.BackButtonDialogCallback backButtonDialogCallback) {
        this.a = backButtonDialogCallback;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.onNegativeButtonClick();
    }
}
