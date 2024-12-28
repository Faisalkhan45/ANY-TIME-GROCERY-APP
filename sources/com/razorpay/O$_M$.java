package com.razorpay;

import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import com.google.android.gms.auth.api.phone.SmsRetriever;

/* compiled from: CheckoutPresenterImpl */
final class O$_M$ extends CountDownTimer {
    private /* synthetic */ CheckoutPresenterImpl a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    O$_M$(CheckoutPresenterImpl checkoutPresenterImpl, long j, long j2) {
        super(2000, 1000);
        this.a = checkoutPresenterImpl;
    }

    public final void onTick(long j) {
    }

    public final void onFinish() {
        try {
            if (this.a.loginOtpSmsTask.isSuccessful()) {
                this.a.activity.registerReceiver(this.a.otpAutoReadBroadcast, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION), SmsRetriever.SEND_PERMISSION, (Handler) null);
                return;
            }
            this.a.startSmsRetrieverForSavedCardsOTP();
        } catch (Exception e) {
            AnalyticsUtil.reportError("CxPsntrImpl", "S2", e.getMessage());
            this.a.startSmsRetrieverForSavedCardsOTP();
        } catch (AbstractMethodError e2) {
            AnalyticsUtil.reportError(e2.getMessage(), "S2", e2.getMessage());
            this.a.startSmsRetrieverForSavedCardsOTP();
        }
    }
}
