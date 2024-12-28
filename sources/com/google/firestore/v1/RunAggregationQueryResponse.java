package com.google.firestore.v1;

import com.google.firestore.v1.AggregationResult;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RunAggregationQueryResponse extends GeneratedMessageLite<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final RunAggregationQueryResponse DEFAULT_INSTANCE;
    private static volatile Parser<RunAggregationQueryResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public static final int RESULT_FIELD_NUMBER = 1;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    private Timestamp readTime_;
    private AggregationResult result_;
    private ByteString transaction_ = ByteString.EMPTY;

    private RunAggregationQueryResponse() {
    }

    public boolean hasResult() {
        return this.result_ != null;
    }

    public AggregationResult getResult() {
        AggregationResult aggregationResult = this.result_;
        return aggregationResult == null ? AggregationResult.getDefaultInstance() : aggregationResult;
    }

    /* access modifiers changed from: private */
    public void setResult(AggregationResult value) {
        value.getClass();
        this.result_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeResult(AggregationResult value) {
        value.getClass();
        AggregationResult aggregationResult = this.result_;
        if (aggregationResult == null || aggregationResult == AggregationResult.getDefaultInstance()) {
            this.result_ = value;
        } else {
            this.result_ = (AggregationResult) ((AggregationResult.Builder) AggregationResult.newBuilder(this.result_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearResult() {
        this.result_ = null;
    }

    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* access modifiers changed from: private */
    public void setTransaction(ByteString value) {
        Class<?> cls = value.getClass();
        this.transaction_ = value;
    }

    /* access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* access modifiers changed from: private */
    public void setReadTime(Timestamp value) {
        value.getClass();
        this.readTime_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeReadTime(Timestamp value) {
        value.getClass();
        Timestamp timestamp = this.readTime_;
        if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) {
            this.readTime_ = value;
        } else {
            this.readTime_ = (Timestamp) ((Timestamp.Builder) Timestamp.newBuilder(this.readTime_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearReadTime() {
        this.readTime_ = null;
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream input) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream input) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream input) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunAggregationQueryResponse prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(RunAggregationQueryResponse.DEFAULT_INSTANCE);
        }

        public boolean hasResult() {
            return ((RunAggregationQueryResponse) this.instance).hasResult();
        }

        public AggregationResult getResult() {
            return ((RunAggregationQueryResponse) this.instance).getResult();
        }

        public Builder setResult(AggregationResult value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult(value);
            return this;
        }

        public Builder setResult(AggregationResult.Builder builderForValue) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult((AggregationResult) builderForValue.build());
            return this;
        }

        public Builder mergeResult(AggregationResult value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeResult(value);
            return this;
        }

        public Builder clearResult() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearResult();
            return this;
        }

        public ByteString getTransaction() {
            return ((RunAggregationQueryResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setTransaction(value);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearTransaction();
            return this;
        }

        public boolean hasReadTime() {
            return ((RunAggregationQueryResponse) this.instance).hasReadTime();
        }

        public Timestamp getReadTime() {
            return ((RunAggregationQueryResponse) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime(value);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builderForValue) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime((Timestamp) builderForValue.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp value) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeReadTime(value);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RunAggregationQueryResponse$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
            case 1:
                return new RunAggregationQueryResponse();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\n\u0003\t", new Object[]{"result_", "transaction_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RunAggregationQueryResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (RunAggregationQueryResponse.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        RunAggregationQueryResponse defaultInstance = new RunAggregationQueryResponse();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RunAggregationQueryResponse.class, defaultInstance);
    }

    public static RunAggregationQueryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunAggregationQueryResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
