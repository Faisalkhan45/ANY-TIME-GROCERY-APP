package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncQueue$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AsyncQueue f$0;
    public final /* synthetic */ AsyncQueue.TimerId f$1;

    public /* synthetic */ AsyncQueue$$ExternalSyntheticLambda3(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId) {
        this.f$0 = asyncQueue;
        this.f$1 = timerId;
    }

    public final void run() {
        this.f$0.m427lambda$runDelayedTasksUntil$6$comgooglefirebasefirestoreutilAsyncQueue(this.f$1);
    }
}
