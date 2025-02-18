package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@ElementTypesAreNonnullByDefault
abstract class AbstractStreamingHasher extends AbstractHasher {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final int chunkSize;

    /* access modifiers changed from: protected */
    public abstract HashCode makeHash();

    /* access modifiers changed from: protected */
    public abstract void process(ByteBuffer byteBuffer);

    protected AbstractStreamingHasher(int chunkSize2) {
        this(chunkSize2, chunkSize2);
    }

    protected AbstractStreamingHasher(int chunkSize2, int bufferSize2) {
        Preconditions.checkArgument(bufferSize2 % chunkSize2 == 0);
        this.buffer = ByteBuffer.allocate(bufferSize2 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = bufferSize2;
        this.chunkSize = chunkSize2;
    }

    /* access modifiers changed from: protected */
    public void processRemaining(ByteBuffer bb) {
        Java8Compatibility.position(bb, bb.limit());
        Java8Compatibility.limit(bb, this.chunkSize + 7);
        while (true) {
            int position = bb.position();
            int i = this.chunkSize;
            if (position < i) {
                bb.putLong(0);
            } else {
                Java8Compatibility.limit(bb, i);
                Java8Compatibility.flip(bb);
                process(bb);
                return;
            }
        }
    }

    public final Hasher putBytes(byte[] bytes, int off, int len) {
        return putBytesInternal(ByteBuffer.wrap(bytes, off, len).order(ByteOrder.LITTLE_ENDIAN));
    }

    /* JADX INFO: finally extract failed */
    public final Hasher putBytes(ByteBuffer readBuffer) {
        ByteOrder order = readBuffer.order();
        try {
            readBuffer.order(ByteOrder.LITTLE_ENDIAN);
            Hasher putBytesInternal = putBytesInternal(readBuffer);
            readBuffer.order(order);
            return putBytesInternal;
        } catch (Throwable th) {
            readBuffer.order(order);
            throw th;
        }
    }

    private Hasher putBytesInternal(ByteBuffer readBuffer) {
        if (readBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(readBuffer);
            munchIfFull();
            return this;
        }
        int bytesToCopy = this.bufferSize - this.buffer.position();
        for (int i = 0; i < bytesToCopy; i++) {
            this.buffer.put(readBuffer.get());
        }
        munch();
        while (readBuffer.remaining() >= this.chunkSize) {
            process(readBuffer);
        }
        this.buffer.put(readBuffer);
        return this;
    }

    public final Hasher putByte(byte b) {
        this.buffer.put(b);
        munchIfFull();
        return this;
    }

    public final Hasher putShort(short s) {
        this.buffer.putShort(s);
        munchIfFull();
        return this;
    }

    public final Hasher putChar(char c) {
        this.buffer.putChar(c);
        munchIfFull();
        return this;
    }

    public final Hasher putInt(int i) {
        this.buffer.putInt(i);
        munchIfFull();
        return this;
    }

    public final Hasher putLong(long l) {
        this.buffer.putLong(l);
        munchIfFull();
        return this;
    }

    public final HashCode hash() {
        munch();
        Java8Compatibility.flip(this.buffer);
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        }
        return makeHash();
    }

    private void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    private void munch() {
        Java8Compatibility.flip(this.buffer);
        while (this.buffer.remaining() >= this.chunkSize) {
            process(this.buffer);
        }
        this.buffer.compact();
    }
}
