package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.lang.Thread;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda0 implements Thread.UncaughtExceptionHandler {
    public final /* synthetic */ AsyncQueue.SynchronizedShutdownAwareExecutor f$0;

    public /* synthetic */ AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda0(AsyncQueue.SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor) {
        this.f$0 = synchronizedShutdownAwareExecutor;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        this.f$0.m428lambda$new$0$comgooglefirebasefirestoreutilAsyncQueue$SynchronizedShutdownAwareExecutor(thread, th);
    }
}
