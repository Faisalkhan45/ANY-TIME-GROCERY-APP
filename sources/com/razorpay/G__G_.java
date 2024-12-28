package com.razorpay;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: AdvertisingIdUtil */
final class G__G_ implements ServiceConnection {
    private boolean a;
    private final LinkedBlockingQueue<IBinder> b;

    private G__G_() {
        this.a = false;
        this.b = new LinkedBlockingQueue<>(1);
    }

    /* synthetic */ G__G_(byte b2) {
        this();
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.b.put(iBinder);
        } catch (Exception e) {
            AnalyticsUtil.reportError(getClass().getName(), "S1", e.getMessage());
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }

    /* access modifiers changed from: package-private */
    public final IBinder a() {
        if (!this.a) {
            this.a = true;
        }
        return this.b.take();
    }
}
