package com.razorpay;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

public class AutoReadOtpHelper extends BroadcastReceiver {
    private Activity activity;
    private String packageName;

    public AutoReadOtpHelper(Activity activity2) {
        this.activity = activity2;
        this.packageName = activity2.getPackageName();
    }

    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = null;
            if (extras != null) {
                status = (Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS");
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_RECEIVED_SMS);
            if (status != null) {
                switch (status.getStatusCode()) {
                    case 0:
                        if (this.activity.getCallingActivity() != null && ((ComponentName) Objects.requireNonNull(this.activity.getCallingActivity())).getPackageName().equalsIgnoreCase(this.packageName)) {
                            try {
                                this.activity.startActivityForResult((Intent) extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT), 1001);
                                AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_SHOWED_ONE_TIME_CONSENT);
                                return;
                            } catch (ActivityNotFoundException e) {
                                AnalyticsUtil.reportError("AutoReadOtpHelper", "S0", e.getLocalizedMessage());
                                e.printStackTrace();
                                return;
                            }
                        } else {
                            return;
                        }
                    case 15:
                        AnalyticsUtil.trackEvent(AnalyticsEvent.AUTO_READ_OTP_SMS_RETRIEVER_API_TIMEOUT);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
