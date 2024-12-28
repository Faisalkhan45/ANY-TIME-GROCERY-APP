package com.razorpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.json.JSONArray;

/* compiled from: CardSaving */
final class L__R$ extends BroadcastReceiver {
    L__R$() {
    }

    public final void onReceive(Context context, Intent intent) {
        String string;
        Bundle resultExtras = getResultExtras(true);
        if (resultExtras != null && (string = resultExtras.getString("device_token_info_list")) != null) {
            try {
                f$_G$.a(context, new JSONArray(string));
            } catch (Exception e) {
            }
        }
    }
}
