package com.razorpay;

import android.app.Activity;
import android.content.IntentFilter;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: SmsAgent */
class m {
    private static m c;
    ArrayList<SmsAgentInterface> a = new ArrayList<>();
    private SmsReceiver b;

    static m a() {
        if (c == null) {
            c = new m();
        }
        return c;
    }

    m() {
    }

    /* access modifiers changed from: package-private */
    public final void a(SmsAgentInterface smsAgentInterface) {
        this.a.add(smsAgentInterface);
    }

    /* access modifiers changed from: package-private */
    public final void b(SmsAgentInterface smsAgentInterface) {
        try {
            this.a.remove(smsAgentInterface);
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.RECEIVE_SMS") == 0) {
            a(true);
            b();
            AnalyticsUtil.trackEvent(AnalyticsEvent.SMS_PERMISSION_ALREADY_GRANTED);
            return true;
        }
        AnalyticsUtil.trackEvent(AnalyticsEvent.SMS_PERMISSION_ALREADY_NOT_GRANTED);
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        if (this.b == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(1000);
            this.b = new SmsReceiver(this);
            intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(Activity activity) {
        a(false);
        SmsReceiver smsReceiver = this.b;
        if (smsReceiver != null) {
            try {
                activity.unregisterReceiver(smsReceiver);
            } catch (Exception e) {
                AnalyticsUtil.reportError("SmsAgent", "S0", e.getMessage());
            }
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        Iterator<SmsAgentInterface> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().setSmsPermission(z);
        }
    }
}
