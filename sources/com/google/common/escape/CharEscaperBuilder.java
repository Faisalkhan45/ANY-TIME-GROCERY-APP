package com.google.common.escape;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class CharEscaperBuilder {
    private final Map<Character, String> map = new HashMap();
    private int max = -1;

    private static class CharArrayDecorator extends CharEscaper {
        private final int replaceLength;
        private final char[][] replacements;

        CharArrayDecorator(char[][] replacements2) {
            this.replacements = replacements2;
            this.replaceLength = replacements2.length;
        }

        public String escape(String s) {
            int slen = s.length();
            for (int index = 0; index < slen; index++) {
                char c = s.charAt(index);
                char[][] cArr = this.replacements;
                if (c < cArr.length && cArr[c] != null) {
                    return escapeSlow(s, index);
                }
            }
            return s;
        }

        /* access modifiers changed from: protected */
        @CheckForNull
        public char[] escape(char c) {
            if (c < this.replaceLength) {
                return this.replacements[c];
            }
            return null;
        }
    }

    public CharEscaperBuilder addEscape(char c, String r) {
        this.map.put(Character.valueOf(c), (String) Preconditions.checkNotNull(r));
        if (c > this.max) {
            this.max = c;
        }
        return this;
    }

    public CharEscaperBuilder addEscapes(char[] cs, String r) {
        Preconditions.checkNotNull(r);
        for (char c : cs) {
            addEscape(c, r);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] result = new char[(this.max + 1)][];
        for (Map.Entry<Character, String> entry : this.map.entrySet()) {
            result[entry.getKey().charValue()] = entry.getValue().toCharArray();
        }
        return result;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(toArray());
    }
}
