package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzxs extends LifecycleCallback {
    private final List zza;

    private zzxs(LifecycleFragment lifecycleFragment, List list) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("PhoneAuthActivityStopCallback", this);
        this.zza = list;
    }

    public static void zza(Activity activity, List list) {
        LifecycleFragment fragment = getFragment(activity);
        if (((zzxs) fragment.getCallbackOrNull("PhoneAuthActivityStopCallback", zzxs.class)) == null) {
            new zzxs(fragment, list);
        }
    }

    public final void onStop() {
        synchronized (this.zza) {
            this.zza.clear();
        }
    }
}
