package com.google.firestore.v1;

import com.google.firestore.v1.Target;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

public final class ListenRequest extends GeneratedMessageLite<ListenRequest, Builder> implements ListenRequestOrBuilder {
    public static final int ADD_TARGET_FIELD_NUMBER = 2;
    public static final int DATABASE_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final ListenRequest DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 4;
    private static volatile Parser<ListenRequest> PARSER = null;
    public static final int REMOVE_TARGET_FIELD_NUMBER = 3;
    private String database_ = "";
    private MapFieldLite<String, String> labels_ = MapFieldLite.emptyMapField();
    private int targetChangeCase_ = 0;
    private Object targetChange_;

    private ListenRequest() {
    }

    public enum TargetChangeCase {
        ADD_TARGET(2),
        REMOVE_TARGET(3),
        TARGETCHANGE_NOT_SET(0);
        
        private final int value;

        private TargetChangeCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static TargetChangeCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static TargetChangeCase forNumber(int value2) {
            switch (value2) {
                case 0:
                    return TARGETCHANGE_NOT_SET;
                case 2:
                    return ADD_TARGET;
                case 3:
                    return REMOVE_TARGET;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public TargetChangeCase getTargetChangeCase() {
        return TargetChangeCase.forNumber(this.targetChangeCase_);
    }

    /* access modifiers changed from: private */
    public void clearTargetChange() {
        this.targetChangeCase_ = 0;
        this.targetChange_ = null;
    }

    public String getDatabase() {
        return this.database_;
    }

    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* access modifiers changed from: private */
    public void setDatabase(String value) {
        Class<?> cls = value.getClass();
        this.database_ = value;
    }

    /* access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* access modifiers changed from: private */
    public void setDatabaseBytes(ByteString value) {
        checkByteStringIsUtf8(value);
        this.database_ = value.toStringUtf8();
    }

    public boolean hasAddTarget() {
        return this.targetChangeCase_ == 2;
    }

    public Target getAddTarget() {
        if (this.targetChangeCase_ == 2) {
            return (Target) this.targetChange_;
        }
        return Target.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setAddTarget(Target value) {
        value.getClass();
        this.targetChange_ = value;
        this.targetChangeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeAddTarget(Target value) {
        value.getClass();
        if (this.targetChangeCase_ != 2 || this.targetChange_ == Target.getDefaultInstance()) {
            this.targetChange_ = value;
        } else {
            this.targetChange_ = ((Target.Builder) Target.newBuilder((Target) this.targetChange_).mergeFrom(value)).buildPartial();
        }
        this.targetChangeCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void clearAddTarget() {
        if (this.targetChangeCase_ == 2) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    public boolean hasRemoveTarget() {
        return this.targetChangeCase_ == 3;
    }

    public int getRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            return ((Integer) this.targetChange_).intValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void setRemoveTarget(int value) {
        this.targetChangeCase_ = 3;
        this.targetChange_ = Integer.valueOf(value);
    }

    /* access modifiers changed from: private */
    public void clearRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, String> internalGetLabels() {
        return this.labels_;
    }

    private MapFieldLite<String, String> internalGetMutableLabels() {
        if (!this.labels_.isMutable()) {
            this.labels_ = this.labels_.mutableCopy();
        }
        return this.labels_;
    }

    public int getLabelsCount() {
        return internalGetLabels().size();
    }

    public boolean containsLabels(String key) {
        Class<?> cls = key.getClass();
        return internalGetLabels().containsKey(key);
    }

    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(internalGetLabels());
    }

    public String getLabelsOrDefault(String key, String defaultValue) {
        Class<?> cls = key.getClass();
        Map<String, String> map = internalGetLabels();
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    public String getLabelsOrThrow(String key) {
        Class<?> cls = key.getClass();
        Map<String, String> map = internalGetLabels();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: private */
    public Map<String, String> getMutableLabelsMap() {
        return internalGetMutableLabels();
    }

    public static ListenRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenRequest parseFrom(InputStream input) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenRequest parseDelimitedFrom(InputStream input) throws IOException {
        return (ListenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenRequest parseFrom(CodedInputStream input) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListenRequest, Builder> implements ListenRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(ListenRequest.DEFAULT_INSTANCE);
        }

        public TargetChangeCase getTargetChangeCase() {
            return ((ListenRequest) this.instance).getTargetChangeCase();
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearTargetChange();
            return this;
        }

        public String getDatabase() {
            return ((ListenRequest) this.instance).getDatabase();
        }

        public ByteString getDatabaseBytes() {
            return ((ListenRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String value) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabase(value);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString value) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabaseBytes(value);
            return this;
        }

        public boolean hasAddTarget() {
            return ((ListenRequest) this.instance).hasAddTarget();
        }

        public Target getAddTarget() {
            return ((ListenRequest) this.instance).getAddTarget();
        }

        public Builder setAddTarget(Target value) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget(value);
            return this;
        }

        public Builder setAddTarget(Target.Builder builderForValue) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget((Target) builderForValue.build());
            return this;
        }

        public Builder mergeAddTarget(Target value) {
            copyOnWrite();
            ((ListenRequest) this.instance).mergeAddTarget(value);
            return this;
        }

        public Builder clearAddTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearAddTarget();
            return this;
        }

        public boolean hasRemoveTarget() {
            return ((ListenRequest) this.instance).hasRemoveTarget();
        }

        public int getRemoveTarget() {
            return ((ListenRequest) this.instance).getRemoveTarget();
        }

        public Builder setRemoveTarget(int value) {
            copyOnWrite();
            ((ListenRequest) this.instance).setRemoveTarget(value);
            return this;
        }

        public Builder clearRemoveTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearRemoveTarget();
            return this;
        }

        public int getLabelsCount() {
            return ((ListenRequest) this.instance).getLabelsMap().size();
        }

        public boolean containsLabels(String key) {
            Class<?> cls = key.getClass();
            return ((ListenRequest) this.instance).getLabelsMap().containsKey(key);
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().clear();
            return this;
        }

        public Builder removeLabels(String key) {
            Class<?> cls = key.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((ListenRequest) this.instance).getLabelsMap());
        }

        public String getLabelsOrDefault(String key, String defaultValue) {
            Class<?> cls = key.getClass();
            Map<String, String> map = ((ListenRequest) this.instance).getLabelsMap();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        public String getLabelsOrThrow(String key) {
            Class<?> cls = key.getClass();
            Map<String, String> map = ((ListenRequest) this.instance).getLabelsMap();
            if (map.containsKey(key)) {
                return map.get(key);
            }
            throw new IllegalArgumentException();
        }

        public Builder putLabels(String key, String value) {
            Class<?> cls = key.getClass();
            Class<?> cls2 = value.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().put(key, value);
            return this;
        }

        public Builder putAllLabels(Map<String, String> values) {
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().putAll(values);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListenRequest$1  reason: invalid class name */
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
                return new ListenRequest();
            case 2:
                return new Builder((AnonymousClass1) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u0002<\u0000\u00037\u0000\u00042", new Object[]{"targetChange_", "targetChangeCase_", "database_", Target.class, "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListenRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenRequest.class) {
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
        ListenRequest defaultInstance = new ListenRequest();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ListenRequest.class, defaultInstance);
    }

    public static ListenRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
