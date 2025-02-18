package com.google.common.escape;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;

@ElementTypesAreNonnullByDefault
public final class ArrayBasedEscaperMap {
    private static final char[][] EMPTY_REPLACEMENT_ARRAY = ((char[][]) Array.newInstance(Character.TYPE, new int[]{0, 0}));
    private final char[][] replacementArray;

    public static ArrayBasedEscaperMap create(Map<Character, String> replacements) {
        return new ArrayBasedEscaperMap(createReplacementArray(replacements));
    }

    private ArrayBasedEscaperMap(char[][] replacementArray2) {
        this.replacementArray = replacementArray2;
    }

    /* access modifiers changed from: package-private */
    public char[][] getReplacementArray() {
        return this.replacementArray;
    }

    static char[][] createReplacementArray(Map<Character, String> map) {
        Preconditions.checkNotNull(map);
        if (map.isEmpty()) {
            return EMPTY_REPLACEMENT_ARRAY;
        }
        char[][] replacements = new char[(((Character) Collections.max(map.keySet())).charValue() + 1)][];
        for (Character c : map.keySet()) {
            replacements[c.charValue()] = map.get(c).toCharArray();
        }
        return replacements;
    }
}
