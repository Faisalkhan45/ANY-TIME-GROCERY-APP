package com.google.common.io;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_ARRAY_LEN = 2147483639;
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {
        public void write(int b) {
        }

        public void write(byte[] b) {
            Preconditions.checkNotNull(b);
        }

        public void write(byte[] b, int off, int len) {
            Preconditions.checkNotNull(b);
            Preconditions.checkPositionIndexes(off, off + len, b.length);
        }

        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }
    };
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;

    static byte[] createBuffer() {
        return new byte[8192];
    }

    private ByteStreams() {
    }

    public static long copy(InputStream from, OutputStream to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        byte[] buf = createBuffer();
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                return total;
            }
            to.write(buf, 0, r);
            total += (long) r;
        }
    }

    public static long copy(ReadableByteChannel from, WritableByteChannel to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        if (from instanceof FileChannel) {
            FileChannel sourceChannel = (FileChannel) from;
            long oldPosition = sourceChannel.position();
            long position = oldPosition;
            while (true) {
                long copied = sourceChannel.transferTo(position, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, to);
                position += copied;
                sourceChannel.position(position);
                if (copied <= 0 && position >= sourceChannel.size()) {
                    return position - oldPosition;
                }
            }
        } else {
            ByteBuffer buf = ByteBuffer.wrap(createBuffer());
            long total = 0;
            while (from.read(buf) != -1) {
                Java8Compatibility.flip(buf);
                while (buf.hasRemaining()) {
                    total += (long) to.write(buf);
                }
                Java8Compatibility.clear(buf);
            }
            return total;
        }
    }

    private static byte[] toByteArrayInternal(InputStream in, Queue<byte[]> bufs, int totalLen) throws IOException {
        int bufSize = Math.min(8192, Math.max(128, Integer.highestOneBit(totalLen) * 2));
        while (totalLen < MAX_ARRAY_LEN) {
            byte[] buf = new byte[Math.min(bufSize, MAX_ARRAY_LEN - totalLen)];
            bufs.add(buf);
            int off = 0;
            while (off < buf.length) {
                int r = in.read(buf, off, buf.length - off);
                if (r == -1) {
                    return combineBuffers(bufs, totalLen);
                }
                off += r;
                totalLen += r;
            }
            bufSize = IntMath.saturatedMultiply(bufSize, bufSize < 4096 ? 4 : 2);
        }
        if (in.read() == -1) {
            return combineBuffers(bufs, MAX_ARRAY_LEN);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    private static byte[] combineBuffers(Queue<byte[]> bufs, int totalLen) {
        if (bufs.isEmpty()) {
            return new byte[0];
        }
        byte[] result = bufs.remove();
        if (result.length == totalLen) {
            return result;
        }
        int remaining = totalLen - result.length;
        byte[] result2 = Arrays.copyOf(result, totalLen);
        while (remaining > 0) {
            byte[] buf = bufs.remove();
            int bytesToCopy = Math.min(remaining, buf.length);
            System.arraycopy(buf, 0, result2, totalLen - remaining, bytesToCopy);
            remaining -= bytesToCopy;
        }
        return result2;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        Preconditions.checkNotNull(in);
        return toByteArrayInternal(in, new ArrayDeque(20), 0);
    }

    static byte[] toByteArray(InputStream in, long expectedSize) throws IOException {
        Preconditions.checkArgument(expectedSize >= 0, "expectedSize (%s) must be non-negative", expectedSize);
        if (expectedSize <= 2147483639) {
            byte[] bytes = new byte[((int) expectedSize)];
            int remaining = (int) expectedSize;
            while (remaining > 0) {
                int off = ((int) expectedSize) - remaining;
                int read = in.read(bytes, off, remaining);
                if (read == -1) {
                    return Arrays.copyOf(bytes, off);
                }
                remaining -= read;
            }
            int b = in.read();
            if (b == -1) {
                return bytes;
            }
            Queue<byte[]> bufs = new ArrayDeque<>(22);
            bufs.add(bytes);
            bufs.add(new byte[]{(byte) b});
            return toByteArrayInternal(in, bufs, bytes.length + 1);
        }
        throw new OutOfMemoryError(new StringBuilder(62).append(expectedSize).append(" bytes is too large to fit in a byte array").toString());
    }

    public static long exhaust(InputStream in) throws IOException {
        long total = 0;
        byte[] buf = createBuffer();
        while (true) {
            long read = (long) in.read(buf);
            long read2 = read;
            if (read == -1) {
                return total;
            }
            total += read2;
        }
    }

    public static ByteArrayDataInput newDataInput(byte[] bytes) {
        return newDataInput(new ByteArrayInputStream(bytes));
    }

    public static ByteArrayDataInput newDataInput(byte[] bytes, int start) {
        Preconditions.checkPositionIndex(start, bytes.length);
        return newDataInput(new ByteArrayInputStream(bytes, start, bytes.length - start));
    }

    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream((ByteArrayInputStream) Preconditions.checkNotNull(byteArrayInputStream));
    }

    private static class ByteArrayDataInputStream implements ByteArrayDataInput {
        final DataInput input;

        ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        public void readFully(byte[] b) {
            try {
                this.input.readFully(b);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public void readFully(byte[] b, int off, int len) {
            try {
                this.input.readFully(b, off, len);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int skipBytes(int n) {
            try {
                return this.input.skipBytes(n);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e) {
                throw new IllegalStateException(e);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @CheckForNull
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static ByteArrayDataOutput newDataOutput() {
        return newDataOutput(new ByteArrayOutputStream());
    }

    public static ByteArrayDataOutput newDataOutput(int size) {
        if (size >= 0) {
            return newDataOutput(new ByteArrayOutputStream(size));
        }
        throw new IllegalArgumentException(String.format("Invalid size: %s", new Object[]{Integer.valueOf(size)}));
    }

    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayDataOutputStream((ByteArrayOutputStream) Preconditions.checkNotNull(byteArrayOutputStream));
    }

    private static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        final ByteArrayOutputStream byteArrayOutputStream;
        final DataOutput output;

        ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream2) {
            this.byteArrayOutputStream = byteArrayOutputStream2;
            this.output = new DataOutputStream(byteArrayOutputStream2);
        }

        public void write(int b) {
            try {
                this.output.write(b);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void write(byte[] b) {
            try {
                this.output.write(b);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void write(byte[] b, int off, int len) {
            try {
                this.output.write(b, off, len);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeBoolean(boolean v) {
            try {
                this.output.writeBoolean(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeByte(int v) {
            try {
                this.output.writeByte(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeBytes(String s) {
            try {
                this.output.writeBytes(s);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeChar(int v) {
            try {
                this.output.writeChar(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeChars(String s) {
            try {
                this.output.writeChars(s);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeDouble(double v) {
            try {
                this.output.writeDouble(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeFloat(float v) {
            try {
                this.output.writeFloat(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeInt(int v) {
            try {
                this.output.writeInt(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeLong(long v) {
            try {
                this.output.writeLong(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeShort(int v) {
            try {
                this.output.writeShort(v);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public void writeUTF(String s) {
            try {
                this.output.writeUTF(s);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }

        public byte[] toByteArray() {
            return this.byteArrayOutputStream.toByteArray();
        }
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    public static InputStream limit(InputStream in, long limit) {
        return new LimitedInputStream(in, limit);
    }

    private static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark = -1;

        LimitedInputStream(InputStream in, long limit) {
            super(in);
            Preconditions.checkNotNull(in);
            Preconditions.checkArgument(limit >= 0, "limit must be non-negative");
            this.left = limit;
        }

        public int available() throws IOException {
            return (int) Math.min((long) this.in.available(), this.left);
        }

        public synchronized void mark(int readLimit) {
            this.in.mark(readLimit);
            this.mark = this.left;
        }

        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int result = this.in.read();
            if (result != -1) {
                this.left--;
            }
            return result;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            long j = this.left;
            if (j == 0) {
                return -1;
            }
            int result = this.in.read(b, off, (int) Math.min((long) len, j));
            if (result != -1) {
                this.left -= (long) result;
            }
            return result;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.left = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        public long skip(long n) throws IOException {
            long skipped = this.in.skip(Math.min(n, this.left));
            this.left -= skipped;
            return skipped;
        }
    }

    public static void readFully(InputStream in, byte[] b) throws IOException {
        readFully(in, b, 0, b.length);
    }

    public static void readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int read = read(in, b, off, len);
        if (read != len) {
            throw new EOFException(new StringBuilder(81).append("reached end of stream after reading ").append(read).append(" bytes; ").append(len).append(" bytes expected").toString());
        }
    }

    public static void skipFully(InputStream in, long n) throws IOException {
        long skipped = skipUpTo(in, n);
        if (skipped < n) {
            throw new EOFException(new StringBuilder(100).append("reached end of stream after skipping ").append(skipped).append(" bytes; ").append(n).append(" bytes expected").toString());
        }
    }

    static long skipUpTo(InputStream in, long n) throws IOException {
        long totalSkipped = 0;
        byte[] buf = null;
        while (totalSkipped < n) {
            long remaining = n - totalSkipped;
            long skipped = skipSafely(in, remaining);
            if (skipped == 0) {
                int skip = (int) Math.min(remaining, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                if (buf == null) {
                    buf = new byte[skip];
                }
                long read = (long) in.read(buf, 0, skip);
                skipped = read;
                if (read == -1) {
                    break;
                }
            }
            totalSkipped += skipped;
        }
        return totalSkipped;
    }

    private static long skipSafely(InputStream in, long n) throws IOException {
        int available = in.available();
        if (available == 0) {
            return 0;
        }
        return in.skip(Math.min((long) available, n));
    }

    @ParametricNullness
    public static <T> T readBytes(InputStream input, ByteProcessor<T> processor) throws IOException {
        int read;
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(processor);
        byte[] buf = createBuffer();
        do {
            read = input.read(buf);
            if (read == -1 || !processor.processBytes(buf, 0, read)) {
            }
            read = input.read(buf);
            break;
        } while (!processor.processBytes(buf, 0, read));
        return processor.getResult();
    }

    public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
        Preconditions.checkNotNull(in);
        Preconditions.checkNotNull(b);
        if (len >= 0) {
            Preconditions.checkPositionIndexes(off, off + len, b.length);
            int total = 0;
            while (total < len) {
                int result = in.read(b, off + total, len - total);
                if (result == -1) {
                    break;
                }
                total += result;
            }
            return total;
        }
        throw new IndexOutOfBoundsException(String.format("len (%s) cannot be negative", new Object[]{Integer.valueOf(len)}));
    }
}
