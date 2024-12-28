package com.razorpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import java.util.Iterator;

public class SmsReceiver extends BroadcastReceiver {
    private m smsAgent;

    SmsReceiver() {
        this.smsAgent = null;
    }

    SmsReceiver(m mVar) {
        this.smsAgent = mVar;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                Object[] objArr = (Object[]) extras.get("pdus");
                if (objArr.length > 0) {
                    SmsMessage createFromPdu = SmsMessage.createFromPdu((byte[]) objArr[0]);
                    String displayOriginatingAddress = createFromPdu.getDisplayOriginatingAddress();
                    String displayMessageBody = createFromPdu.getDisplayMessageBody();
                    m mVar = this.smsAgent;
                    if (mVar != null) {
                        Iterator<SmsAgentInterface> it = mVar.a.iterator();
                        while (it.hasNext()) {
                            it.next().postSms(displayOriginatingAddress, displayMessageBody);
                        }
                    } else {
                        Intent intent2 = new Intent("com.razorpay.events.SMS_PROCESSED");
                        intent2.putExtra("extra_sender", displayOriginatingAddress);
                        intent2.putExtra("extra_message", displayMessageBody);
                        context.sendBroadcast(intent2);
                    }
                    Log.i("com.razorpay.checkout", "SmsReceiver senderNum: " + displayOriginatingAddress + "; message: " + displayMessageBody);
                }
            } catch (Exception e) {
                AnalyticsUtil.reportError("SmsReceiver", "S0", e.getMessage());
                Log.e("com.razorpay.checkout", "SmsReceiver Exception smsReceiver" + e);
            }
        }
    }
}
