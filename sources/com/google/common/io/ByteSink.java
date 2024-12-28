package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
public abstract class ByteSink {
    public abstract OutputStream openStream() throws IOException;

    protected ByteSink() {
    }

    public CharSink asCharSink(Charset charset) {
        return new AsCharSink(charset);
    }

    public OutputStream openBufferedStream() throws IOException {
        OutputStream out = openStream();
        if (out instanceof BufferedOutputStream) {
            return (BufferedOutputStream) out;
        }
        return new BufferedOutputStream(out);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public void write(byte[] bytes) throws IOException {
        Preconditions.checkNotNull(bytes);
        Closer closer = Closer.create();
        try {
            OutputStream out = (OutputStream) closer.register(openStream());
            out.write(bytes);
            out.flush();
            closer.close();
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 4 */
    public long writeFrom(InputStream input) throws IOException {
        Preconditions.checkNotNull(input);
        Closer closer = Closer.create();
        try {
            OutputStream out = (OutputStream) closer.register(openStream());
            long written = ByteStreams.copy(input, out);
            out.flush();
            closer.close();
            return written;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    private final class AsCharSink extends CharSink {
        private final Charset charset;

        private AsCharSink(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        public Writer openStream() throws IOException {
            return new OutputStreamWriter(ByteSink.this.openStream(), this.charset);
        }

        public String toString() {
            String obj = ByteSink.this.toString();
            String valueOf = String.valueOf(this.charset);
            return new StringBuilder(String.valueOf(obj).length() + 13 + String.valueOf(valueOf).length()).append(obj).append(".asCharSink(").append(valueOf).append(")").toString();
        }
    }
}
