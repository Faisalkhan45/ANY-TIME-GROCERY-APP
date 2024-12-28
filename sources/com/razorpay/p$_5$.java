package com.razorpay;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/razorpay/OpinionatedSoln$showDialog$4", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "millisUntilFinished", "", "checkout_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: OpinionatedSoln.kt */
public final class p$_5$ extends CountDownTimer {
    private /* synthetic */ AlertDialog a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    p$_5$(AlertDialog alertDialog) {
        super(5000, 1000);
        this.a = alertDialog;
    }

    public final void onTick(long j) {
    }

    public final void onFinish() {
        this.a.dismiss();
        OpinionatedSoln.INSTANCE.sendCallbackIfExists();
    }
}
