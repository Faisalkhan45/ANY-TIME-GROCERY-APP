package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;

public abstract class ForwardingClientStreamTracer extends ClientStreamTracer {
    /* access modifiers changed from: protected */
    public abstract ClientStreamTracer delegate();

    public void streamCreated(Attributes transportAttrs, Metadata headers) {
        delegate().streamCreated(transportAttrs, headers);
    }

    public void outboundHeaders() {
        delegate().outboundHeaders();
    }

    public void inboundHeaders() {
        delegate().inboundHeaders();
    }

    public void inboundTrailers(Metadata trailers) {
        delegate().inboundTrailers(trailers);
    }

    public void streamClosed(Status status) {
        delegate().streamClosed(status);
    }

    public void outboundMessage(int seqNo) {
        delegate().outboundMessage(seqNo);
    }

    public void inboundMessage(int seqNo) {
        delegate().inboundMessage(seqNo);
    }

    public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        delegate().outboundMessageSent(seqNo, optionalWireSize, optionalUncompressedSize);
    }

    public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        delegate().inboundMessageRead(seqNo, optionalWireSize, optionalUncompressedSize);
    }

    public void outboundWireSize(long bytes) {
        delegate().outboundWireSize(bytes);
    }

    public void outboundUncompressedSize(long bytes) {
        delegate().outboundUncompressedSize(bytes);
    }

    public void inboundWireSize(long bytes) {
        delegate().inboundWireSize(bytes);
    }

    public void inboundUncompressedSize(long bytes) {
        delegate().inboundUncompressedSize(bytes);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
