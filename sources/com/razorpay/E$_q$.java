package com.razorpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* compiled from: CheckoutPresenterImpl */
final class E$_q$ extends BroadcastReceiver {
    private /* synthetic */ CheckoutPresenterImpl a;

    E$_q$(CheckoutPresenterImpl checkoutPresenterImpl) {
        this.a = checkoutPresenterImpl;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a.activity.unregisterReceiver(this.a.otpAutoReadBroadcast);
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            switch (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode()) {
                case 0:
                    Intent intent2 = new Intent();
                    intent2.putExtra(SmsRetriever.EXTRA_SMS_MESSAGE, (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE));
                    this.a.onActivityResultReceived(1001, -1, intent2);
                    return;
                case 15:
                    AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_TIMEOUT);
                    return;
                default:
                    return;
            }
        }
    }
}
