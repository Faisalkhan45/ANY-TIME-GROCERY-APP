package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzxc;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzav extends BroadcastReceiver {
    final /* synthetic */ zzax zza;
    private final WeakReference zzb;
    private final TaskCompletionSource zzc;
    private final FirebaseAuth zzd;
    private final FirebaseUser zze;

    zzav(zzax zzax, Activity activity, TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zza = zzax;
        this.zzb = new WeakReference(activity);
        this.zzc = taskCompletionSource;
        this.zzd = firebaseAuth;
        this.zze = firebaseUser;
    }

    public final void onReceive(Context context, Intent intent) {
        if (((Activity) this.zzb.get()) == null) {
            Log.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzc.setException(zzxc.zza(new Status((int) FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzax.zze(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
            if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(stringExtra)) {
                this.zzd.signInWithCredential(zzax.zzi(intent)).addOnSuccessListener(new zzaq(this.zza, this.zzc, context)).addOnFailureListener(new zzap(this.zza, this.zzc, context));
            } else if ("com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(stringExtra)) {
                this.zze.linkWithCredential(zzax.zzi(intent)).addOnSuccessListener(new zzas(this.zza, this.zzc, context)).addOnFailureListener(new zzar(this.zza, this.zzc, context));
            } else if ("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(stringExtra)) {
                this.zze.reauthenticateAndRetrieveData(zzax.zzi(intent)).addOnSuccessListener(new zzau(this.zza, this.zzc, context)).addOnFailureListener(new zzat(this.zza, this.zzc, context));
            } else {
                TaskCompletionSource taskCompletionSource = this.zzc;
                taskCompletionSource.setException(zzxc.zza(zzai.zza("WEB_CONTEXT_CANCELED:Unknown operation received (" + stringExtra + ")")));
            }
        } else if (zzbl.zzd(intent)) {
            this.zzc.setException(zzxc.zza(zzbl.zza(intent)));
            zzax.zze(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.EXTRA_CANCELED")) {
            this.zzc.setException(zzxc.zza(zzai.zza("WEB_CONTEXT_CANCELED")));
            zzax.zze(context);
        }
    }
}
