package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class LineReader {
    private final char[] buf;
    private final CharBuffer cbuf;
    private final LineBuffer lineBuf = new LineBuffer() {
        /* access modifiers changed from: protected */
        public void handleLine(String line, String end) {
            LineReader.this.lines.add(line);
        }
    };
    /* access modifiers changed from: private */
    public final Queue<String> lines = new ArrayDeque();
    private final Readable readable;
    @CheckForNull
    private final Reader reader;

    public LineReader(Readable readable2) {
        CharBuffer createBuffer = CharStreams.createBuffer();
        this.cbuf = createBuffer;
        this.buf = createBuffer.array();
        this.readable = (Readable) Preconditions.checkNotNull(readable2);
        this.reader = readable2 instanceof Reader ? (Reader) readable2 : null;
    }

    @CheckForNull
    public String readLine() throws IOException {
        int read;
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            Java8Compatibility.clear(this.cbuf);
            Reader reader2 = this.reader;
            if (reader2 != null) {
                char[] cArr = this.buf;
                read = reader2.read(cArr, 0, cArr.length);
            } else {
                read = this.readable.read(this.cbuf);
            }
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return this.lines.poll();
    }
}
