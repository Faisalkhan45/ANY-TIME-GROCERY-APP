package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.AbstractStream;
import io.grpc.Status;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractStream$StreamObserver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AbstractStream.StreamObserver f$0;
    public final /* synthetic */ Status f$1;

    public /* synthetic */ AbstractStream$StreamObserver$$ExternalSyntheticLambda1(AbstractStream.StreamObserver streamObserver, Status status) {
        this.f$0 = streamObserver;
        this.f$1 = status;
    }

    public final void run() {
        this.f$0.m405lambda$onClose$3$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver(this.f$1);
    }
}
