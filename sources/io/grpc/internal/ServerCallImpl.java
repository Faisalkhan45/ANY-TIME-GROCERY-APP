package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    static final String MISSING_RESPONSE = "Completed without a response";
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    /* access modifiers changed from: private */
    public volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final Context.CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;

    ServerCallImpl(ServerStream stream2, MethodDescriptor<ReqT, RespT> method2, Metadata inboundHeaders, Context.CancellableContext context2, DecompressorRegistry decompressorRegistry2, CompressorRegistry compressorRegistry2, CallTracer serverCallTracer2, Tag tag2) {
        this.stream = stream2;
        this.method = method2;
        this.context = context2;
        this.messageAcceptEncoding = (byte[]) inboundHeaders.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry2;
        this.compressorRegistry = compressorRegistry2;
        this.serverCallTracer = serverCallTracer2;
        serverCallTracer2.reportCallStarted();
        this.tag = tag2;
    }

    public void request(int numMessages) {
        PerfMark.startTask("ServerCall.request", this.tag);
        try {
            this.stream.request(numMessages);
        } finally {
            PerfMark.stopTask("ServerCall.request", this.tag);
        }
    }

    public void sendHeaders(Metadata headers) {
        PerfMark.startTask("ServerCall.sendHeaders", this.tag);
        try {
            sendHeadersInternal(headers);
        } finally {
            PerfMark.stopTask("ServerCall.sendHeaders", this.tag);
        }
    }

    private void sendHeadersInternal(Metadata headers) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        headers.discardAll(GrpcUtil.CONTENT_LENGTH_KEY);
        headers.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (this.messageAcceptEncoding == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
            this.compressor = Codec.Identity.NONE;
        }
        headers.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        headers.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] advertisedEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (advertisedEncodings.length != 0) {
            headers.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, advertisedEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(headers);
    }

    public void sendMessage(RespT message) {
        PerfMark.startTask("ServerCall.sendMessage", this.tag);
        try {
            sendMessageInternal(message);
        } finally {
            PerfMark.stopTask("ServerCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(RespT message) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (!this.method.getType().serverSendsOneMessage() || !this.messageSent) {
            this.messageSent = true;
            try {
                this.stream.writeMessage(this.method.streamResponse(message));
                if (!getMethodDescriptor().getType().serverSendsOneMessage()) {
                    this.stream.flush();
                }
            } catch (RuntimeException e) {
                close(Status.fromThrowable(e), new Metadata());
            } catch (Error e2) {
                close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
                throw e2;
            }
        } else {
            internalClose(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES));
        }
    }

    public void setMessageCompression(boolean enable) {
        this.stream.setMessageCompression(enable);
    }

    public void setCompression(String compressorName) {
        boolean z = true;
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        Compressor lookupCompressor = this.compressorRegistry.lookupCompressor(compressorName);
        this.compressor = lookupCompressor;
        if (lookupCompressor == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Unable to find compressor by name %s", (Object) compressorName);
    }

    public boolean isReady() {
        if (this.closeCalled) {
            return false;
        }
        return this.stream.isReady();
    }

    public void close(Status status, Metadata trailers) {
        PerfMark.startTask("ServerCall.close", this.tag);
        try {
            closeInternal(status, trailers);
        } finally {
            PerfMark.stopTask("ServerCall.close", this.tag);
        }
    }

    private void closeInternal(Status status, Metadata trailers) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (!status.isOk() || !this.method.getType().serverSendsOneMessage() || this.messageSent) {
                this.stream.close(status, trailers);
                this.serverCallTracer.reportCallEnded(status.isOk());
                return;
            }
            internalClose(Status.INTERNAL.withDescription(MISSING_RESPONSE));
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: package-private */
    public ServerStreamListener newServerStreamListener(ServerCall.Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    public String getAuthority() {
        return this.stream.getAuthority();
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    public SecurityLevel getSecurityLevel() {
        Attributes attributes = getAttributes();
        if (attributes == null) {
            return super.getSecurityLevel();
        }
        SecurityLevel securityLevel = (SecurityLevel) attributes.get(GrpcAttributes.ATTR_SECURITY_LEVEL);
        return securityLevel == null ? super.getSecurityLevel() : securityLevel;
    }

    private void internalClose(Status internalError) {
        log.log(Level.WARNING, "Cancelling the stream with status {0}", new Object[]{internalError});
        this.stream.cancel(internalError);
        this.serverCallTracer.reportCallEnded(internalError.isOk());
    }

    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        /* access modifiers changed from: private */
        public final ServerCallImpl<ReqT, ?> call;
        private final Context.CancellableContext context;
        private final ServerCall.Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> call2, ServerCall.Listener<ReqT> listener2, Context.CancellableContext context2) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(call2, NotificationCompat.CATEGORY_CALL);
            this.listener = (ServerCall.Listener) Preconditions.checkNotNull(listener2, "listener must not be null");
            Context.CancellableContext cancellableContext = (Context.CancellableContext) Preconditions.checkNotNull(context2, "context");
            this.context = cancellableContext;
            cancellableContext.addListener(new Context.CancellationListener() {
                public void cancelled(Context context) {
                    if (context.cancellationCause() != null) {
                        boolean unused = ServerStreamListenerImpl.this.call.cancelled = true;
                    }
                }
            }, MoreExecutors.directExecutor());
        }

        public void messagesAvailable(StreamListener.MessageProducer producer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", this.call.tag);
            try {
                messagesAvailableInternal(producer);
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", this.call.tag);
            }
        }

        private void messagesAvailableInternal(StreamListener.MessageProducer producer) {
            InputStream message;
            if (this.call.cancelled) {
                GrpcUtil.closeQuietly(producer);
                return;
            }
            while (true) {
                try {
                    InputStream next = producer.next();
                    message = next;
                    if (next != null) {
                        this.listener.onMessage(this.call.method.parseRequest(message));
                        message.close();
                    } else {
                        return;
                    }
                } catch (Throwable t) {
                    GrpcUtil.closeQuietly(producer);
                    Throwables.throwIfUnchecked(t);
                    throw new RuntimeException(t);
                }
            }
        }

        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", this.call.tag);
            try {
                if (!this.call.cancelled) {
                    this.listener.onHalfClose();
                    PerfMark.stopTask("ServerStreamListener.halfClosed", this.call.tag);
                }
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", this.call.tag);
            }
        }

        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", this.call.tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", this.call.tag);
            }
        }

        private void closedInternal(Status status) {
            Throwable cancelCause = null;
            try {
                if (status.isOk()) {
                    this.listener.onComplete();
                } else {
                    boolean unused = this.call.cancelled = true;
                    this.listener.onCancel();
                    cancelCause = InternalStatus.asRuntimeException(Status.CANCELLED.withDescription("RPC cancelled"), (Metadata) null, false);
                }
            } finally {
                this.context.cancel(cancelCause);
            }
        }

        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", this.call.tag);
            try {
                if (!this.call.cancelled) {
                    this.listener.onReady();
                    PerfMark.stopTask("ServerCall.closed", this.call.tag);
                }
            } finally {
                PerfMark.stopTask("ServerCall.closed", this.call.tag);
            }
        }
    }
}
