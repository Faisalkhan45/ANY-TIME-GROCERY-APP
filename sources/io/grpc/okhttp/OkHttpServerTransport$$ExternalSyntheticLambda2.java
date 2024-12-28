package io.grpc.okhttp;

import io.grpc.internal.SerializingExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OkHttpServerTransport$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ OkHttpServerTransport f$0;
    public final /* synthetic */ SerializingExecutor f$1;

    public /* synthetic */ OkHttpServerTransport$$ExternalSyntheticLambda2(OkHttpServerTransport okHttpServerTransport, SerializingExecutor serializingExecutor) {
        this.f$0 = okHttpServerTransport;
        this.f$1 = serializingExecutor;
    }

    public final void run() {
        this.f$0.m455lambda$start$0$iogrpcokhttpOkHttpServerTransport(this.f$1);
    }
}
