package com.google.firebase.firestore.core;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ QueryListener f$1;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda5(FirestoreClient firestoreClient, QueryListener queryListener) {
        this.f$0 = firestoreClient;
        this.f$1 = queryListener;
    }

    public final void run() {
        this.f$0.m333lambda$stopListening$8$comgooglefirebasefirestorecoreFirestoreClient(this.f$1);
    }
}
