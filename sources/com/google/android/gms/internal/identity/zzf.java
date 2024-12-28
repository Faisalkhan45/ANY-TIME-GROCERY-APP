package com.google.android.gms.internal.identity;

import android.app.Activity;

public final class zzf extends zzh {
    private Activity mActivity;
    private final int zzj;

    public zzf(int i, Activity activity) {
        this.zzj = i;
        this.mActivity = activity;
    }

    /* access modifiers changed from: private */
    public final void setActivity(Activity activity) {
        this.mActivity = null;
    }

    /* JADX WARNING: type inference failed for: r6v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r5, android.os.Bundle r6) {
        /*
            r4 = this;
            r0 = 1073741824(0x40000000, float:2.0)
            java.lang.String r1 = "AddressClientImpl"
            r2 = 1
            if (r5 != r2) goto L_0x0025
            android.content.Intent r5 = new android.content.Intent
            r5.<init>()
            r5.putExtras(r6)
            android.app.Activity r6 = r4.mActivity
            int r3 = r4.zzj
            android.app.PendingIntent r5 = r6.createPendingResult(r3, r5, r0)
            if (r5 != 0) goto L_0x001a
            return
        L_0x001a:
            r5.send(r2)     // Catch:{ CanceledException -> 0x001e }
            return
        L_0x001e:
            r5 = move-exception
            java.lang.String r6 = "Exception settng pending result"
        L_0x0021:
            android.util.Log.w(r1, r6, r5)
            return
        L_0x0025:
            r3 = 0
            if (r6 == 0) goto L_0x0031
            java.lang.String r3 = "com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT"
            android.os.Parcelable r6 = r6.getParcelable(r3)
            r3 = r6
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
        L_0x0031:
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult
            r6.<init>(r5, r3)
            boolean r5 = r6.hasResolution()
            if (r5 == 0) goto L_0x0048
            android.app.Activity r5 = r4.mActivity     // Catch:{ SendIntentException -> 0x0044 }
            int r0 = r4.zzj     // Catch:{ SendIntentException -> 0x0044 }
            r6.startResolutionForResult(r5, r0)     // Catch:{ SendIntentException -> 0x0044 }
            return
        L_0x0044:
            r5 = move-exception
            java.lang.String r6 = "Exception starting pending intent"
            goto L_0x0021
        L_0x0048:
            android.app.Activity r5 = r4.mActivity     // Catch:{ CanceledException -> 0x005b }
            int r6 = r4.zzj     // Catch:{ CanceledException -> 0x005b }
            android.content.Intent r3 = new android.content.Intent     // Catch:{ CanceledException -> 0x005b }
            r3.<init>()     // Catch:{ CanceledException -> 0x005b }
            android.app.PendingIntent r5 = r5.createPendingResult(r6, r3, r0)     // Catch:{ CanceledException -> 0x005b }
            if (r5 == 0) goto L_0x005a
            r5.send(r2)     // Catch:{ CanceledException -> 0x005b }
        L_0x005a:
            return
        L_0x005b:
            r5 = move-exception
            java.lang.String r6 = "Exception setting pending result"
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.identity.zzf.zza(int, android.os.Bundle):void");
    }
}
