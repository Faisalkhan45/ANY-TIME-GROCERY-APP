package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.AbstractStream;
import io.grpc.Metadata;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractStream$StreamObserver$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AbstractStream.StreamObserver f$0;
    public final /* synthetic */ Metadata f$1;

    public /* synthetic */ AbstractStream$StreamObserver$$ExternalSyntheticLambda3(AbstractStream.StreamObserver streamObserver, Metadata metadata) {
        this.f$0 = streamObserver;
        this.f$1 = metadata;
    }

    public final void run() {
        this.f$0.m406lambda$onHeaders$0$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver(this.f$1);
    }
}
