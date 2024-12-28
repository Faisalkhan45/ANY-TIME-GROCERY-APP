package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzd extends Fragment {
    boolean zza;
    private int zzb;
    private zzc<?> zzc;

    /* access modifiers changed from: private */
    public final void zzb(Task<? extends AutoResolvableResult> task) {
        if (!this.zza) {
            this.zza = true;
            Activity activity = getActivity();
            activity.getFragmentManager().beginTransaction().remove(this).commit();
            if (task != null) {
                AutoResolveHelper.zzf(activity, this.zzb, task);
            } else {
                AutoResolveHelper.zze(activity, this.zzb, 0, new Intent());
            }
        }
    }

    private final void zzc() {
        zzc<?> zzc2 = this.zzc;
        if (zzc2 != null) {
            zzc2.zzb(this);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzb = getArguments().getInt("requestCode");
        if (AutoResolveHelper.zza != getArguments().getLong("initializationElapsedRealtime")) {
            this.zzc = null;
        } else {
            this.zzc = zzc.zzb.get(getArguments().getInt("resolveCallId"));
        }
        boolean z = false;
        if (bundle != null && bundle.getBoolean("delivered")) {
            z = true;
        }
        this.zza = z;
    }

    public final void onPause() {
        super.onPause();
        zzc();
    }

    public final void onResume() {
        super.onResume();
        zzc<?> zzc2 = this.zzc;
        if (zzc2 != null) {
            zzc2.zzc(this);
            return;
        }
        if (Log.isLoggable("AutoResolveHelper", 5)) {
            Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
        }
        zzb((Task<? extends AutoResolvableResult>) null);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("delivered", this.zza);
        zzc();
    }
}
