package com.google.common.escape;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlin.jvm.internal.CharCompanionObject;

@ElementTypesAreNonnullByDefault
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMax;
    private final char safeMin;

    /* access modifiers changed from: protected */
    @CheckForNull
    public abstract char[] escapeUnsafe(char c);

    protected ArrayBasedCharEscaper(Map<Character, String> replacementMap, char safeMin2, char safeMax2) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin2, safeMax2);
    }

    protected ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin2, char safeMax2) {
        Preconditions.checkNotNull(escaperMap);
        char[][] replacementArray = escaperMap.getReplacementArray();
        this.replacements = replacementArray;
        this.replacementsLength = replacementArray.length;
        if (safeMax2 < safeMin2) {
            safeMax2 = 0;
            safeMin2 = CharCompanionObject.MAX_VALUE;
        }
        this.safeMin = safeMin2;
        this.safeMax = safeMax2;
    }

    public final String escape(String s) {
        Preconditions.checkNotNull(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c < this.replacementsLength && this.replacements[c] != null) || c > this.safeMax || c < this.safeMin) {
                return escapeSlow(s, i);
            }
        }
        return s;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final char[] escape(char c) {
        char[] chars;
        if (c < this.replacementsLength && (chars = this.replacements[c]) != null) {
            return chars;
        }
        if (c < this.safeMin || c > this.safeMax) {
            return escapeUnsafe(c);
        }
        return null;
    }
}
