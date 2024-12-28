package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ Query f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda8(FirestoreClient firestoreClient, Query query, TaskCompletionSource taskCompletionSource) {
        this.f$0 = firestoreClient;
        this.f$1 = query;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.m332lambda$runCountQuery$16$comgooglefirebasefirestorecoreFirestoreClient(this.f$1, this.f$2);
    }
}
