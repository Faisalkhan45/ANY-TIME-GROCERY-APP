package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

@ElementTypesAreNonnullByDefault
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    public Writer openBufferedStream() throws IOException {
        Writer writer = openStream();
        if (writer instanceof BufferedWriter) {
            return (BufferedWriter) writer;
        }
        return new BufferedWriter(writer);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            out.append(charSequence);
            out.flush();
            closer.close();
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        Preconditions.checkNotNull(lines);
        Preconditions.checkNotNull(lineSeparator);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openBufferedStream());
            for (CharSequence line : lines) {
                out.append(line).append(lineSeparator);
            }
            out.flush();
            closer.close();
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 4 */
    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            long written = CharStreams.copy(readable, out);
            out.flush();
            closer.close();
            return written;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }
}
