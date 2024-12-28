package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;

@CheckReturnValue
final class ArrayDecoders {
    private ArrayDecoders() {
    }

    static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        Registers() {
            this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        }

        Registers(ExtensionRegistryLite extensionRegistry2) {
            if (extensionRegistry2 != null) {
                this.extensionRegistry = extensionRegistry2;
                return;
            }
            throw new NullPointerException();
        }
    }

    static int decodeVarint32(byte[] data, int position, Registers registers) {
        int position2 = position + 1;
        byte value = data[position];
        if (value < 0) {
            return decodeVarint32(value, data, position2, registers);
        }
        registers.int1 = value;
        return position2;
    }

    static int decodeVarint32(int firstByte, byte[] data, int position, Registers registers) {
        int value = firstByte & WorkQueueKt.MASK;
        int position2 = position + 1;
        byte b2 = data[position];
        if (b2 >= 0) {
            registers.int1 = (b2 << 7) | value;
            return position2;
        }
        int value2 = value | ((b2 & Byte.MAX_VALUE) << 7);
        int position3 = position2 + 1;
        byte b3 = data[position2];
        if (b3 >= 0) {
            registers.int1 = (b3 << Ascii.SO) | value2;
            return position3;
        }
        int value3 = value2 | ((b3 & Byte.MAX_VALUE) << Ascii.SO);
        int position4 = position3 + 1;
        byte b4 = data[position3];
        if (b4 >= 0) {
            registers.int1 = (b4 << Ascii.NAK) | value3;
            return position4;
        }
        int value4 = value3 | ((b4 & Byte.MAX_VALUE) << Ascii.NAK);
        int position5 = position4 + 1;
        byte b5 = data[position4];
        if (b5 >= 0) {
            registers.int1 = (b5 << Ascii.FS) | value4;
            return position5;
        }
        int value5 = value4 | ((b5 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int position6 = position5 + 1;
            if (data[position5] < 0) {
                position5 = position6;
            } else {
                registers.int1 = value5;
                return position6;
            }
        }
    }

    static int decodeVarint64(byte[] data, int position, Registers registers) {
        int position2 = position + 1;
        long value = (long) data[position];
        if (value < 0) {
            return decodeVarint64(value, data, position2, registers);
        }
        registers.long1 = value;
        return position2;
    }

    static int decodeVarint64(long firstByte, byte[] data, int position, Registers registers) {
        int position2 = position + 1;
        byte next = data[position];
        int shift = 7;
        long value = (127 & firstByte) | (((long) (next & Byte.MAX_VALUE)) << 7);
        while (next < 0) {
            next = data[position2];
            shift += 7;
            value |= ((long) (next & Byte.MAX_VALUE)) << shift;
            position2++;
        }
        registers.long1 = value;
        return position2;
    }

    static int decodeFixed32(byte[] data, int position) {
        return (data[position] & 255) | ((data[position + 1] & 255) << 8) | ((data[position + 2] & 255) << Ascii.DLE) | ((data[position + 3] & 255) << Ascii.CAN);
    }

    static long decodeFixed64(byte[] data, int position) {
        return (((long) data[position]) & 255) | ((((long) data[position + 1]) & 255) << 8) | ((((long) data[position + 2]) & 255) << 16) | ((((long) data[position + 3]) & 255) << 24) | ((((long) data[position + 4]) & 255) << 32) | ((((long) data[position + 5]) & 255) << 40) | ((((long) data[position + 6]) & 255) << 48) | ((255 & ((long) data[position + 7])) << 56);
    }

    static double decodeDouble(byte[] data, int position) {
        return Double.longBitsToDouble(decodeFixed64(data, position));
    }

    static float decodeFloat(byte[] data, int position) {
        return Float.intBitsToFloat(decodeFixed32(data, position));
    }

    static int decodeString(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int position2 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (length == 0) {
            registers.object1 = "";
            return position2;
        } else {
            registers.object1 = new String(data, position2, length, Internal.UTF_8);
            return position2 + length;
        }
    }

    static int decodeStringRequireUtf8(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int position2 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (length == 0) {
            registers.object1 = "";
            return position2;
        } else {
            registers.object1 = Utf8.decodeUtf8(data, position2, length);
            return position2 + length;
        }
    }

    static int decodeBytes(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int position2 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (length > data.length - position2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        } else if (length == 0) {
            registers.object1 = ByteString.EMPTY;
            return position2;
        } else {
            registers.object1 = ByteString.copyFrom(data, position2, length);
            return position2 + length;
        }
    }

    static int decodeMessageField(Schema schema, byte[] data, int position, int limit, Registers registers) throws IOException {
        Object msg = schema.newInstance();
        int offset = mergeMessageField(msg, schema, data, position, limit, registers);
        schema.makeImmutable(msg);
        registers.object1 = msg;
        return offset;
    }

    static int decodeGroupField(Schema schema, byte[] data, int position, int limit, int endGroup, Registers registers) throws IOException {
        Object msg = schema.newInstance();
        int offset = mergeGroupField(msg, schema, data, position, limit, endGroup, registers);
        schema.makeImmutable(msg);
        registers.object1 = msg;
        return offset;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int mergeMessageField(java.lang.Object r7, com.google.protobuf.Schema r8, byte[] r9, int r10, int r11, com.google.protobuf.ArrayDecoders.Registers r12) throws java.io.IOException {
        /*
            int r0 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x000e
            int r0 = decodeVarint32(r10, r9, r0, r12)
            int r10 = r12.int1
            r6 = r0
            goto L_0x000f
        L_0x000e:
            r6 = r0
        L_0x000f:
            if (r10 < 0) goto L_0x0024
            int r0 = r11 - r6
            if (r10 > r0) goto L_0x0024
            int r4 = r6 + r10
            r0 = r8
            r1 = r7
            r2 = r9
            r3 = r6
            r5 = r12
            r0.mergeFrom(r1, r2, r3, r4, r5)
            r12.object1 = r7
            int r0 = r6 + r10
            return r0
        L_0x0024:
            com.google.protobuf.InvalidProtocolBufferException r0 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.mergeMessageField(java.lang.Object, com.google.protobuf.Schema, byte[], int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    static int mergeGroupField(Object msg, Schema schema, byte[] data, int position, int limit, int endGroup, Registers registers) throws IOException {
        int endPosition = ((MessageSchema) schema).parseProto2Message(msg, data, position, limit, endGroup, registers);
        registers.object1 = msg;
        return endPosition;
    }

    static int decodeVarint32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList output = (IntArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        output.addInt(registers.int1);
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeVarint32(data, nextPosition, registers);
            output.addInt(registers.int1);
        }
        return position2;
    }

    static int decodeVarint64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList output = (LongArrayList) list;
        int position2 = decodeVarint64(data, position, registers);
        output.addLong(registers.long1);
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeVarint64(data, nextPosition, registers);
            output.addLong(registers.long1);
        }
        return position2;
    }

    static int decodeFixed32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList output = (IntArrayList) list;
        output.addInt(decodeFixed32(data, position));
        int position2 = position + 4;
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            output.addInt(decodeFixed32(data, nextPosition));
            position2 = nextPosition + 4;
        }
        return position2;
    }

    static int decodeFixed64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList output = (LongArrayList) list;
        output.addLong(decodeFixed64(data, position));
        int position2 = position + 8;
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            output.addLong(decodeFixed64(data, nextPosition));
            position2 = nextPosition + 8;
        }
        return position2;
    }

    static int decodeFloatList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        FloatArrayList output = (FloatArrayList) list;
        output.addFloat(decodeFloat(data, position));
        int position2 = position + 4;
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            output.addFloat(decodeFloat(data, nextPosition));
            position2 = nextPosition + 4;
        }
        return position2;
    }

    static int decodeDoubleList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        DoubleArrayList output = (DoubleArrayList) list;
        output.addDouble(decodeDouble(data, position));
        int position2 = position + 8;
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            output.addDouble(decodeDouble(data, nextPosition));
            position2 = nextPosition + 8;
        }
        return position2;
    }

    static int decodeBoolList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        BooleanArrayList output = (BooleanArrayList) list;
        int position2 = decodeVarint64(data, position, registers);
        output.addBoolean(registers.long1 != 0);
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeVarint64(data, nextPosition, registers);
            output.addBoolean(registers.long1 != 0);
        }
        return position2;
    }

    static int decodeSInt32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList output = (IntArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        output.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeVarint32(data, nextPosition, registers);
            output.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        return position2;
    }

    static int decodeSInt64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList output = (LongArrayList) list;
        int position2 = decodeVarint64(data, position, registers);
        output.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeVarint64(data, nextPosition, registers);
            output.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        return position2;
    }

    static int decodePackedVarint32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList output = (IntArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            position2 = decodeVarint32(data, position2, registers);
            output.addInt(registers.int1);
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedVarint64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList output = (LongArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            position2 = decodeVarint64(data, position2, registers);
            output.addLong(registers.long1);
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedFixed32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList output = (IntArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            output.addInt(decodeFixed32(data, position2));
            position2 += 4;
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedFixed64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList output = (LongArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            output.addLong(decodeFixed64(data, position2));
            position2 += 8;
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedFloatList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        FloatArrayList output = (FloatArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            output.addFloat(decodeFloat(data, position2));
            position2 += 4;
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedDoubleList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        DoubleArrayList output = (DoubleArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            output.addDouble(decodeDouble(data, position2));
            position2 += 8;
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedBoolList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        BooleanArrayList output = (BooleanArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            position2 = decodeVarint64(data, position2, registers);
            output.addBoolean(registers.long1 != 0);
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedSInt32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList output = (IntArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            position2 = decodeVarint32(data, position2, registers);
            output.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodePackedSInt64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList output = (LongArrayList) list;
        int position2 = decodeVarint32(data, position, registers);
        int fieldLimit = registers.int1 + position2;
        while (position2 < fieldLimit) {
            position2 = decodeVarint64(data, position2, registers);
            output.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        if (position2 == fieldLimit) {
            return position2;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static int decodeStringList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int position2;
        Internal.ProtobufList<?> protobufList = list;
        int position3 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length >= 0) {
            if (length == 0) {
                protobufList.add("");
            } else {
                protobufList.add(new String(data, position3, length, Internal.UTF_8));
                position3 += length;
            }
            while (position2 < limit) {
                int nextPosition = decodeVarint32(data, position2, registers);
                if (tag != registers.int1) {
                    break;
                }
                position2 = decodeVarint32(data, nextPosition, registers);
                int nextLength = registers.int1;
                if (nextLength < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                } else if (nextLength == 0) {
                    protobufList.add("");
                } else {
                    protobufList.add(new String(data, position2, nextLength, Internal.UTF_8));
                    position2 += nextLength;
                }
            }
            return position2;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    static int decodeStringListRequireUtf8(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int position2;
        Internal.ProtobufList<?> protobufList = list;
        int position3 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length >= 0) {
            if (length == 0) {
                protobufList.add("");
            } else if (Utf8.isValidUtf8(data, position3, position3 + length)) {
                protobufList.add(new String(data, position3, length, Internal.UTF_8));
                position3 += length;
            } else {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            while (position2 < limit) {
                int nextPosition = decodeVarint32(data, position2, registers);
                if (tag != registers.int1) {
                    break;
                }
                position2 = decodeVarint32(data, nextPosition, registers);
                int nextLength = registers.int1;
                if (nextLength < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                } else if (nextLength == 0) {
                    protobufList.add("");
                } else if (Utf8.isValidUtf8(data, position2, position2 + nextLength)) {
                    protobufList.add(new String(data, position2, nextLength, Internal.UTF_8));
                    position2 += nextLength;
                } else {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            return position2;
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    static int decodeBytesList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int position2;
        Internal.ProtobufList<?> protobufList = list;
        int position3 = decodeVarint32(data, position, registers);
        int length = registers.int1;
        if (length < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (length <= data.length - position3) {
            if (length == 0) {
                protobufList.add(ByteString.EMPTY);
            } else {
                protobufList.add(ByteString.copyFrom(data, position3, length));
                position3 += length;
            }
            while (position2 < limit) {
                int nextPosition = decodeVarint32(data, position2, registers);
                if (tag != registers.int1) {
                    break;
                }
                position2 = decodeVarint32(data, nextPosition, registers);
                int nextLength = registers.int1;
                if (nextLength < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                } else if (nextLength > data.length - position2) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                } else if (nextLength == 0) {
                    protobufList.add(ByteString.EMPTY);
                } else {
                    protobufList.add(ByteString.copyFrom(data, position2, nextLength));
                    position2 += nextLength;
                }
            }
            return position2;
        } else {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    static int decodeMessageList(Schema<?> schema, int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        Internal.ProtobufList<?> protobufList = list;
        int position2 = decodeMessageField(schema, data, position, limit, registers);
        protobufList.add(registers.object1);
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeMessageField(schema, data, nextPosition, limit, registers);
            protobufList.add(registers.object1);
        }
        return position2;
    }

    static int decodeGroupList(Schema schema, int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        Internal.ProtobufList<?> protobufList = list;
        int endgroup = (tag & -8) | 4;
        int position2 = decodeGroupField(schema, data, position, limit, endgroup, registers);
        protobufList.add(registers.object1);
        while (position2 < limit) {
            int nextPosition = decodeVarint32(data, position2, registers);
            if (tag != registers.int1) {
                break;
            }
            position2 = decodeGroupField(schema, data, nextPosition, limit, endgroup, registers);
            protobufList.add(registers.object1);
        }
        return position2;
    }

    static int decodeExtensionOrUnknownField(int tag, byte[] data, int position, int limit, Object message, MessageLite defaultInstance, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws IOException {
        GeneratedMessageLite.GeneratedExtension extension = registers.extensionRegistry.findLiteExtensionByNumber(defaultInstance, tag >>> 3);
        if (extension == null) {
            return decodeUnknownField(tag, data, position, limit, MessageSchema.getMutableUnknownFields(message), registers);
        }
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> ensureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) message).ensureExtensionsAreMutable();
        return decodeExtension(tag, data, position, limit, (GeneratedMessageLite.ExtendableMessage) message, extension, unknownFieldSchema, registers);
    }

    static int decodeExtension(int tag, byte[] data, int position, int limit, GeneratedMessageLite.ExtendableMessage<?, ?> message, GeneratedMessageLite.GeneratedExtension<?, ?> extension, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws IOException {
        int position2;
        Object oldValue;
        Object oldValue2;
        byte[] bArr = data;
        int i = position;
        GeneratedMessageLite.ExtendableMessage<?, ?> extendableMessage = message;
        GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension = extension;
        Registers registers2 = registers;
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions = extendableMessage.extensions;
        int fieldNumber = tag >>> 3;
        if (!generatedExtension.descriptor.isRepeated() || !generatedExtension.descriptor.isPacked()) {
            Object value = null;
            if (extension.getLiteType() != WireFormat.FieldType.ENUM) {
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema2 = unknownFieldSchema;
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
                    case 1:
                        value = Double.valueOf(decodeDouble(data, position));
                        position2 = i + 8;
                        break;
                    case 2:
                        value = Float.valueOf(decodeFloat(data, position));
                        position2 = i + 4;
                        break;
                    case 3:
                    case 4:
                        position2 = decodeVarint64(bArr, i, registers2);
                        value = Long.valueOf(registers2.long1);
                        break;
                    case 5:
                    case 6:
                        position2 = decodeVarint32(bArr, i, registers2);
                        value = Integer.valueOf(registers2.int1);
                        break;
                    case 7:
                    case 8:
                        value = Long.valueOf(decodeFixed64(data, position));
                        position2 = i + 8;
                        break;
                    case 9:
                    case 10:
                        value = Integer.valueOf(decodeFixed32(data, position));
                        position2 = i + 4;
                        break;
                    case 11:
                        position2 = decodeVarint64(bArr, i, registers2);
                        value = Boolean.valueOf(registers2.long1 != 0);
                        break;
                    case 12:
                        position2 = decodeVarint32(bArr, i, registers2);
                        value = Integer.valueOf(CodedInputStream.decodeZigZag32(registers2.int1));
                        break;
                    case 13:
                        position2 = decodeVarint64(bArr, i, registers2);
                        value = Long.valueOf(CodedInputStream.decodeZigZag64(registers2.long1));
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        position2 = decodeBytes(bArr, i, registers2);
                        value = registers2.object1;
                        break;
                    case 16:
                        position2 = decodeString(bArr, i, registers2);
                        value = registers2.object1;
                        break;
                    case 17:
                        int endTag = (fieldNumber << 3) | 4;
                        Schema<?> schemaFor = Protobuf.getInstance().schemaFor(extension.getMessageDefaultInstance().getClass());
                        if (extension.isRepeated()) {
                            int position3 = decodeGroupField(schemaFor, data, position, limit, endTag, registers);
                            extensions.addRepeatedField(generatedExtension.descriptor, registers2.object1);
                            return position3;
                        }
                        Object oldValue3 = extensions.getField(generatedExtension.descriptor);
                        if (oldValue3 == null) {
                            Object oldValue4 = schemaFor.newInstance();
                            extensions.setField(generatedExtension.descriptor, oldValue4);
                            oldValue = oldValue4;
                        } else {
                            oldValue = oldValue3;
                        }
                        return mergeGroupField(oldValue, schemaFor, data, position, limit, endTag, registers);
                    case 18:
                        Schema<?> schemaFor2 = Protobuf.getInstance().schemaFor(extension.getMessageDefaultInstance().getClass());
                        if (extension.isRepeated()) {
                            int position4 = decodeMessageField(schemaFor2, bArr, i, limit, registers2);
                            extensions.addRepeatedField(generatedExtension.descriptor, registers2.object1);
                            return position4;
                        }
                        int i2 = limit;
                        Object oldValue5 = extensions.getField(generatedExtension.descriptor);
                        if (oldValue5 == null) {
                            Object oldValue6 = schemaFor2.newInstance();
                            extensions.setField(generatedExtension.descriptor, oldValue6);
                            oldValue2 = oldValue6;
                        } else {
                            oldValue2 = oldValue5;
                        }
                        return mergeMessageField(oldValue2, schemaFor2, data, position, limit, registers);
                    default:
                        position2 = i;
                        break;
                }
            } else {
                position2 = decodeVarint32(bArr, i, registers2);
                if (generatedExtension.descriptor.getEnumType().findValueByNumber(registers2.int1) == null) {
                    SchemaUtil.storeUnknownEnum(extendableMessage, fieldNumber, registers2.int1, null, unknownFieldSchema);
                    return position2;
                }
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema3 = unknownFieldSchema;
                value = Integer.valueOf(registers2.int1);
            }
            if (extension.isRepeated()) {
                extensions.addRepeatedField(generatedExtension.descriptor, value);
            } else {
                extensions.setField(generatedExtension.descriptor, value);
            }
            return position2;
        }
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
            case 1:
                DoubleArrayList list = new DoubleArrayList();
                int position5 = decodePackedDoubleList(bArr, i, list, registers2);
                extensions.setField(generatedExtension.descriptor, list);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema4 = unknownFieldSchema;
                return position5;
            case 2:
                FloatArrayList list2 = new FloatArrayList();
                int position6 = decodePackedFloatList(bArr, i, list2, registers2);
                extensions.setField(generatedExtension.descriptor, list2);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema5 = unknownFieldSchema;
                return position6;
            case 3:
            case 4:
                LongArrayList list3 = new LongArrayList();
                int position7 = decodePackedVarint64List(bArr, i, list3, registers2);
                extensions.setField(generatedExtension.descriptor, list3);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema6 = unknownFieldSchema;
                return position7;
            case 5:
            case 6:
                IntArrayList list4 = new IntArrayList();
                int position8 = decodePackedVarint32List(bArr, i, list4, registers2);
                extensions.setField(generatedExtension.descriptor, list4);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema7 = unknownFieldSchema;
                return position8;
            case 7:
            case 8:
                LongArrayList list5 = new LongArrayList();
                int position9 = decodePackedFixed64List(bArr, i, list5, registers2);
                extensions.setField(generatedExtension.descriptor, list5);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema8 = unknownFieldSchema;
                return position9;
            case 9:
            case 10:
                IntArrayList list6 = new IntArrayList();
                int position10 = decodePackedFixed32List(bArr, i, list6, registers2);
                extensions.setField(generatedExtension.descriptor, list6);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema9 = unknownFieldSchema;
                return position10;
            case 11:
                BooleanArrayList list7 = new BooleanArrayList();
                int position11 = decodePackedBoolList(bArr, i, list7, registers2);
                extensions.setField(generatedExtension.descriptor, list7);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema10 = unknownFieldSchema;
                return position11;
            case 12:
                IntArrayList list8 = new IntArrayList();
                int position12 = decodePackedSInt32List(bArr, i, list8, registers2);
                extensions.setField(generatedExtension.descriptor, list8);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema11 = unknownFieldSchema;
                return position12;
            case 13:
                LongArrayList list9 = new LongArrayList();
                int position13 = decodePackedSInt64List(bArr, i, list9, registers2);
                extensions.setField(generatedExtension.descriptor, list9);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema12 = unknownFieldSchema;
                return position13;
            case 14:
                IntArrayList list10 = new IntArrayList();
                int position14 = decodePackedVarint32List(bArr, i, list10, registers2);
                SchemaUtil.filterUnknownEnumList((Object) message, fieldNumber, (List<Integer>) list10, generatedExtension.descriptor.getEnumType(), null, unknownFieldSchema);
                extensions.setField(generatedExtension.descriptor, list10);
                UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema13 = unknownFieldSchema;
                return position14;
            default:
                throw new IllegalStateException("Type cannot be packed: " + generatedExtension.descriptor.getLiteType());
        }
    }

    /* renamed from: com.google.protobuf.ArrayDecoders$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    static int decodeUnknownField(int tag, byte[] data, int position, int limit, UnknownFieldSetLite unknownFields, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(tag) != 0) {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    int position2 = decodeVarint64(data, position, registers);
                    unknownFields.storeField(tag, Long.valueOf(registers.long1));
                    return position2;
                case 1:
                    unknownFields.storeField(tag, Long.valueOf(decodeFixed64(data, position)));
                    return position + 8;
                case 2:
                    int position3 = decodeVarint32(data, position, registers);
                    int length = registers.int1;
                    if (length < 0) {
                        throw InvalidProtocolBufferException.negativeSize();
                    } else if (length <= data.length - position3) {
                        if (length == 0) {
                            unknownFields.storeField(tag, ByteString.EMPTY);
                        } else {
                            unknownFields.storeField(tag, ByteString.copyFrom(data, position3, length));
                        }
                        return position3 + length;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                case 3:
                    UnknownFieldSetLite child = UnknownFieldSetLite.newInstance();
                    int endGroup = (tag & -8) | 4;
                    int lastTag = 0;
                    while (true) {
                        if (position < limit) {
                            position = decodeVarint32(data, position, registers);
                            int lastTag2 = registers.int1;
                            if (lastTag2 == endGroup) {
                                lastTag = lastTag2;
                            } else {
                                lastTag = lastTag2;
                                position = decodeUnknownField(lastTag, data, position, limit, child, registers);
                            }
                        }
                    }
                    if (position > limit || lastTag != endGroup) {
                        throw InvalidProtocolBufferException.parseFailure();
                    }
                    unknownFields.storeField(tag, child);
                    return position;
                case 5:
                    unknownFields.storeField(tag, Integer.valueOf(decodeFixed32(data, position)));
                    return position + 4;
                default:
                    throw InvalidProtocolBufferException.invalidTag();
            }
        } else {
            throw InvalidProtocolBufferException.invalidTag();
        }
    }

    static int skipField(int tag, byte[] data, int position, int limit, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(tag) != 0) {
            switch (WireFormat.getTagWireType(tag)) {
                case 0:
                    return decodeVarint64(data, position, registers);
                case 1:
                    return position + 8;
                case 2:
                    return registers.int1 + decodeVarint32(data, position, registers);
                case 3:
                    int endGroup = (tag & -8) | 4;
                    int lastTag = 0;
                    while (position < limit) {
                        position = decodeVarint32(data, position, registers);
                        lastTag = registers.int1;
                        if (lastTag != endGroup) {
                            position = skipField(lastTag, data, position, limit, registers);
                        } else if (position > limit && lastTag == endGroup) {
                            return position;
                        } else {
                            throw InvalidProtocolBufferException.parseFailure();
                        }
                    }
                    if (position > limit) {
                    }
                    throw InvalidProtocolBufferException.parseFailure();
                case 5:
                    return position + 4;
                default:
                    throw InvalidProtocolBufferException.invalidTag();
            }
        } else {
            throw InvalidProtocolBufferException.invalidTag();
        }
    }
}
