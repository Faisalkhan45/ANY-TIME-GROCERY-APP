package com.google.firestore.v1;

import com.google.firestore.v1.StructuredQuery;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class StructuredAggregationQuery extends GeneratedMessageLite<StructuredAggregationQuery, Builder> implements StructuredAggregationQueryOrBuilder {
    public static final int AGGREGATIONS_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final StructuredAggregationQuery DEFAULT_INSTANCE;
    private static volatile Parser<StructuredAggregationQuery> PARSER = null;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Aggregation> aggregations_ = emptyProtobufList();
    private int queryTypeCase_ = 0;
    private Object queryType_;

    public interface AggregationOrBuilder extends MessageLiteOrBuilder {
        String getAlias();

        ByteString getAliasBytes();

        Aggregation.Count getCount();

        Aggregation.OperatorCase getOperatorCase();

        boolean hasCount();
    }

    private StructuredAggregationQuery() {
    }

    public static final class Aggregation extends GeneratedMessageLite<Aggregation, Builder> implements AggregationOrBuilder {
        public static final int ALIAS_FIELD_NUMBER = 7;
        public static final int COUNT_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Aggregation DEFAULT_INSTANCE;
        private static volatile Parser<Aggregation> PARSER;
        private String alias_ = "";
        private int operatorCase_ = 0;
        private Object operator_;

        public interface CountOrBuilder extends MessageLiteOrBuilder {
            Int64Value getUpTo();

            boolean hasUpTo();
        }

        private Aggregation() {
        }

        public static final class Count extends GeneratedMessageLite<Count, Builder> implements CountOrBuilder {
            /* access modifiers changed from: private */
            public static final Count DEFAULT_INSTANCE;
            private static volatile Parser<Count> PARSER = null;
            public static final int UP_TO_FIELD_NUMBER = 1;
            private Int64Value upTo_;

            private Count() {
            }

            public boolean hasUpTo() {
                return this.upTo_ != null;
            }

            public Int64Value getUpTo() {
                Int64Value int64Value = this.upTo_;
                return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
            }

            /* access modifiers changed from: private */
            public void setUpTo(Int64Value value) {
                value.getClass();
                this.upTo_ = value;
            }

            /* access modifiers changed from: private */
            public void mergeUpTo(Int64Value value) {
                value.getClass();
                Int64Value int64Value = this.upTo_;
                if (int64Value == null || int64Value == Int64Value.getDefaultInstance()) {
                    this.upTo_ = value;
                } else {
                    this.upTo_ = (Int64Value) ((Int64Value.Builder) Int64Value.newBuilder(this.upTo_).mergeFrom(value)).buildPartial();
                }
            }

            /* access modifiers changed from: private */
            public void clearUpTo() {
                this.upTo_ = null;
            }

            public static Count parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Count parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Count parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Count parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Count parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Count parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Count parseFrom(InputStream input) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Count parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Count parseDelimitedFrom(InputStream input) throws IOException {
                return (Count) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Count parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Count) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Count parseFrom(CodedInputStream input) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Count parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Count prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Count, Builder> implements CountOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 x0) {
                    this();
                }

                private Builder() {
                    super(Count.DEFAULT_INSTANCE);
                }

                public boolean hasUpTo() {
                    return ((Count) this.instance).hasUpTo();
                }

                public Int64Value getUpTo() {
                    return ((Count) this.instance).getUpTo();
                }

                public Builder setUpTo(Int64Value value) {
                    copyOnWrite();
                    ((Count) this.instance).setUpTo(value);
                    return this;
                }

                public Builder setUpTo(Int64Value.Builder builderForValue) {
                    copyOnWrite();
                    ((Count) this.instance).setUpTo((Int64Value) builderForValue.build());
                    return this;
                }

                public Builder mergeUpTo(Int64Value value) {
                    copyOnWrite();
                    ((Count) this.instance).mergeUpTo(value);
                    return this;
                }

                public Builder clearUpTo() {
                    copyOnWrite();
                    ((Count) this.instance).clearUpTo();
                    return this;
                }
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                    case 1:
                        return new Count();
                    case 2:
                        return new Builder((AnonymousClass1) null);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"upTo_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Count> parser = PARSER;
                        if (parser == null) {
                            synchronized (Count.class) {
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
                Count defaultInstance = new Count();
                DEFAULT_INSTANCE = defaultInstance;
                GeneratedMessageLite.registerDefaultInstance(Count.class, defaultInstance);
            }

            public static Count getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Count> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public enum OperatorCase {
            COUNT(1),
            OPERATOR_NOT_SET(0);
            
            private final int value;

            private OperatorCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static OperatorCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static OperatorCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPERATOR_NOT_SET;
                    case 1:
                        return COUNT;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public OperatorCase getOperatorCase() {
            return OperatorCase.forNumber(this.operatorCase_);
        }

        /* access modifiers changed from: private */
        public void clearOperator() {
            this.operatorCase_ = 0;
            this.operator_ = null;
        }

        public boolean hasCount() {
            return this.operatorCase_ == 1;
        }

        public Count getCount() {
            if (this.operatorCase_ == 1) {
                return (Count) this.operator_;
            }
            return Count.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCount(Count value) {
            value.getClass();
            this.operator_ = value;
            this.operatorCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeCount(Count value) {
            value.getClass();
            if (this.operatorCase_ != 1 || this.operator_ == Count.getDefaultInstance()) {
                this.operator_ = value;
            } else {
                this.operator_ = ((Count.Builder) Count.newBuilder((Count) this.operator_).mergeFrom(value)).buildPartial();
            }
            this.operatorCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearCount() {
            if (this.operatorCase_ == 1) {
                this.operatorCase_ = 0;
                this.operator_ = null;
            }
        }

        public String getAlias() {
            return this.alias_;
        }

        public ByteString getAliasBytes() {
            return ByteString.copyFromUtf8(this.alias_);
        }

        /* access modifiers changed from: private */
        public void setAlias(String value) {
            Class<?> cls = value.getClass();
            this.alias_ = value;
        }

        /* access modifiers changed from: private */
        public void clearAlias() {
            this.alias_ = getDefaultInstance().getAlias();
        }

        /* access modifiers changed from: private */
        public void setAliasBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.alias_ = value.toStringUtf8();
        }

        public static Aggregation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Aggregation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Aggregation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Aggregation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Aggregation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Aggregation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Aggregation parseFrom(InputStream input) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Aggregation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Aggregation parseDelimitedFrom(InputStream input) throws IOException {
            return (Aggregation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Aggregation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Aggregation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Aggregation parseFrom(CodedInputStream input) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Aggregation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Aggregation prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Aggregation, Builder> implements AggregationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Aggregation.DEFAULT_INSTANCE);
            }

            public OperatorCase getOperatorCase() {
                return ((Aggregation) this.instance).getOperatorCase();
            }

            public Builder clearOperator() {
                copyOnWrite();
                ((Aggregation) this.instance).clearOperator();
                return this;
            }

            public boolean hasCount() {
                return ((Aggregation) this.instance).hasCount();
            }

            public Count getCount() {
                return ((Aggregation) this.instance).getCount();
            }

            public Builder setCount(Count value) {
                copyOnWrite();
                ((Aggregation) this.instance).setCount(value);
                return this;
            }

            public Builder setCount(Count.Builder builderForValue) {
                copyOnWrite();
                ((Aggregation) this.instance).setCount((Count) builderForValue.build());
                return this;
            }

            public Builder mergeCount(Count value) {
                copyOnWrite();
                ((Aggregation) this.instance).mergeCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((Aggregation) this.instance).clearCount();
                return this;
            }

            public String getAlias() {
                return ((Aggregation) this.instance).getAlias();
            }

            public ByteString getAliasBytes() {
                return ((Aggregation) this.instance).getAliasBytes();
            }

            public Builder setAlias(String value) {
                copyOnWrite();
                ((Aggregation) this.instance).setAlias(value);
                return this;
            }

            public Builder clearAlias() {
                copyOnWrite();
                ((Aggregation) this.instance).clearAlias();
                return this;
            }

            public Builder setAliasBytes(ByteString value) {
                copyOnWrite();
                ((Aggregation) this.instance).setAliasBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new Aggregation();
                case 2:
                    return new Builder((AnonymousClass1) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0007\u0002\u0000\u0000\u0000\u0001<\u0000\u0007Ȉ", new Object[]{"operator_", "operatorCase_", Count.class, "alias_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Aggregation> parser = PARSER;
                    if (parser == null) {
                        synchronized (Aggregation.class) {
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
            Aggregation defaultInstance = new Aggregation();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Aggregation.class, defaultInstance);
        }

        public static Aggregation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Aggregation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredAggregationQuery$1  reason: invalid class name */
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

    public enum QueryTypeCase {
        STRUCTURED_QUERY(1),
        QUERYTYPE_NOT_SET(0);
        
        private final int value;

        private QueryTypeCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static QueryTypeCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return QUERYTYPE_NOT_SET;
                case 1:
                    return STRUCTURED_QUERY;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 1;
    }

    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 1) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery value) {
        value.getClass();
        this.queryType_ = value;
        this.queryTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery value) {
        value.getClass();
        if (this.queryTypeCase_ != 1 || this.queryType_ == StructuredQuery.getDefaultInstance()) {
            this.queryType_ = value;
        } else {
            this.queryType_ = ((StructuredQuery.Builder) StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom(value)).buildPartial();
        }
        this.queryTypeCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 1) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    public List<Aggregation> getAggregationsList() {
        return this.aggregations_;
    }

    public List<? extends AggregationOrBuilder> getAggregationsOrBuilderList() {
        return this.aggregations_;
    }

    public int getAggregationsCount() {
        return this.aggregations_.size();
    }

    public Aggregation getAggregations(int index) {
        return (Aggregation) this.aggregations_.get(index);
    }

    public AggregationOrBuilder getAggregationsOrBuilder(int index) {
        return (AggregationOrBuilder) this.aggregations_.get(index);
    }

    private void ensureAggregationsIsMutable() {
        Internal.ProtobufList<Aggregation> tmp = this.aggregations_;
        if (!tmp.isModifiable()) {
            this.aggregations_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setAggregations(int index, Aggregation value) {
        value.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addAggregations(Aggregation value) {
        value.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.add(value);
    }

    /* access modifiers changed from: private */
    public void addAggregations(int index, Aggregation value) {
        value.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllAggregations(Iterable<? extends Aggregation> values) {
        ensureAggregationsIsMutable();
        AbstractMessageLite.addAll(values, this.aggregations_);
    }

    /* access modifiers changed from: private */
    public void clearAggregations() {
        this.aggregations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAggregations(int index) {
        ensureAggregationsIsMutable();
        this.aggregations_.remove(index);
    }

    public static StructuredAggregationQuery parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredAggregationQuery parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredAggregationQuery parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredAggregationQuery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredAggregationQuery parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredAggregationQuery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredAggregationQuery parseFrom(InputStream input) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredAggregationQuery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StructuredAggregationQuery parseDelimitedFrom(InputStream input) throws IOException {
        return (StructuredAggregationQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredAggregationQuery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredAggregationQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StructuredAggregationQuery parseFrom(CodedInputStream input) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredAggregationQuery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredAggregationQuery prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StructuredAggregationQuery, Builder> implements StructuredAggregationQueryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(StructuredAggregationQuery.DEFAULT_INSTANCE);
        }

        public QueryTypeCase getQueryTypeCase() {
            return ((StructuredAggregationQuery) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearQueryType();
            return this;
        }

        public boolean hasStructuredQuery() {
            return ((StructuredAggregationQuery) this.instance).hasStructuredQuery();
        }

        public StructuredQuery getStructuredQuery() {
            return ((StructuredAggregationQuery) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setStructuredQuery(value);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builderForValue) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setStructuredQuery((StructuredQuery) builderForValue.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery value) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).mergeStructuredQuery(value);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearStructuredQuery();
            return this;
        }

        public List<Aggregation> getAggregationsList() {
            return Collections.unmodifiableList(((StructuredAggregationQuery) this.instance).getAggregationsList());
        }

        public int getAggregationsCount() {
            return ((StructuredAggregationQuery) this.instance).getAggregationsCount();
        }

        public Aggregation getAggregations(int index) {
            return ((StructuredAggregationQuery) this.instance).getAggregations(index);
        }

        public Builder setAggregations(int index, Aggregation value) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setAggregations(index, value);
            return this;
        }

        public Builder setAggregations(int index, Aggregation.Builder builderForValue) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setAggregations(index, (Aggregation) builderForValue.build());
            return this;
        }

        public Builder addAggregations(Aggregation value) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(value);
            return this;
        }

        public Builder addAggregations(int index, Aggregation value) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(index, value);
            return this;
        }

        public Builder addAggregations(Aggregation.Builder builderForValue) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations((Aggregation) builderForValue.build());
            return this;
        }

        public Builder addAggregations(int index, Aggregation.Builder builderForValue) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(index, (Aggregation) builderForValue.build());
            return this;
        }

        public Builder addAllAggregations(Iterable<? extends Aggregation> values) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAllAggregations(values);
            return this;
        }

        public Builder clearAggregations() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearAggregations();
            return this;
        }

        public Builder removeAggregations(int index) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).removeAggregations(index);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
            case 1:
                return new StructuredAggregationQuery();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0003\u0002\u0000\u0001\u0000\u0001<\u0000\u0003\u001b", new Object[]{"queryType_", "queryTypeCase_", StructuredQuery.class, "aggregations_", Aggregation.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredAggregationQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (StructuredAggregationQuery.class) {
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
        StructuredAggregationQuery defaultInstance = new StructuredAggregationQuery();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(StructuredAggregationQuery.class, defaultInstance);
    }

    public static StructuredAggregationQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredAggregationQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
