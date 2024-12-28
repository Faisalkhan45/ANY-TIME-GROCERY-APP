package com.razorpay;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

/* compiled from: BaseUtils */
public final class O_$B_ extends PhoneStateListener {
    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        if (!signalStrength.isGsm()) {
            signalStrength.getCdmaDbm();
        } else if (signalStrength.getGsmSignalStrength() != 99) {
            signalStrength.getGsmSignalStrength();
        } else {
            signalStrength.getGsmSignalStrength();
        }
    }
}
