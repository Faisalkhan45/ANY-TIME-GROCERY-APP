package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
class AppendableWriter extends Writer {
    private boolean closed;
    private final Appendable target;

    AppendableWriter(Appendable target2) {
        this.target = (Appendable) Preconditions.checkNotNull(target2);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        checkNotClosed();
        this.target.append(new String(cbuf, off, len));
    }

    public void write(int c) throws IOException {
        checkNotClosed();
        this.target.append((char) c);
    }

    public void write(String str) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str);
    }

    public void write(String str, int off, int len) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str, off, off + len);
    }

    public void flush() throws IOException {
        checkNotClosed();
        Appendable appendable = this.target;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    public void close() throws IOException {
        this.closed = true;
        Appendable appendable = this.target;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    public Writer append(char c) throws IOException {
        checkNotClosed();
        this.target.append(c);
        return this;
    }

    public Writer append(@CheckForNull CharSequence charSeq) throws IOException {
        checkNotClosed();
        this.target.append(charSeq);
        return this;
    }

    public Writer append(@CheckForNull CharSequence charSeq, int start, int end) throws IOException {
        checkNotClosed();
        this.target.append(charSeq, start, end);
        return this;
    }

    private void checkNotClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write to a closed writer.");
        }
    }
}
