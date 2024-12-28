package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda9 implements OnSuccessListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda9(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    public final void onSuccess(Object obj) {
        this.f$0.setResult((Long) obj);
    }
}
