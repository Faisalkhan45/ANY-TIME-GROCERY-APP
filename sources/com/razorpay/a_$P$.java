package com.razorpay;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.razorpay.AdvertisingIdUtil;

/* compiled from: AdvertisingIdUtil */
final class a_$P$ extends AsyncTask<Void, Void, String> {
    private AdvertisingIdUtil.AdvertisingIdCallback a;
    private Context b;

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        super.onPostExecute(str);
        this.a.onResult(str);
    }

    a_$P$(Context context, AdvertisingIdUtil.AdvertisingIdCallback advertisingIdCallback) {
        this.b = context;
        this.a = advertisingIdCallback;
    }

    private String a() {
        G__G_ g__g_ = new G__G_((byte) 0);
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (!this.b.bindService(intent, g__g_, 1)) {
            return "permission disabled";
        }
        try {
            String a2 = new R$$r_(g__g_.a()).a();
            try {
                this.b.unbindService(g__g_);
            } catch (IllegalArgumentException e) {
                AnalyticsUtil.reportError(getClass().getName(), "S1", e.getLocalizedMessage());
            }
            return a2;
        } catch (Exception e2) {
            String message = e2.getMessage();
            try {
                this.b.unbindService(g__g_);
            } catch (IllegalArgumentException e3) {
                AnalyticsUtil.reportError(getClass().getName(), "S1", e3.getLocalizedMessage());
            }
            return message;
        } catch (Throwable th) {
            try {
                this.b.unbindService(g__g_);
            } catch (IllegalArgumentException e4) {
                AnalyticsUtil.reportError(getClass().getName(), "S1", e4.getLocalizedMessage());
            }
            throw th;
        }
    }
}
