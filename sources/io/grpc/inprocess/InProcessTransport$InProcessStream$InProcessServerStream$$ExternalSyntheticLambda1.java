package io.grpc.inprocess;

import io.grpc.inprocess.InProcessTransport;
import io.grpc.internal.StreamListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ InProcessTransport.InProcessStream.InProcessServerStream f$0;
    public final /* synthetic */ StreamListener.MessageProducer f$1;

    public /* synthetic */ InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda1(InProcessTransport.InProcessStream.InProcessServerStream inProcessServerStream, StreamListener.MessageProducer messageProducer) {
        this.f$0 = inProcessServerStream;
        this.f$1 = messageProducer;
    }

    public final void run() {
        this.f$0.m445lambda$clientRequested$1$iogrpcinprocessInProcessTransport$InProcessStream$InProcessServerStream(this.f$1);
    }
}
