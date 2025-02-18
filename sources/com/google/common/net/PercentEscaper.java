package com.google.common.net;

import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class PercentEscaper extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = {'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String safeChars, boolean plusForSpace2) {
        Preconditions.checkNotNull(safeChars);
        if (!safeChars.matches(".*[0-9A-Za-z].*")) {
            String safeChars2 = String.valueOf(safeChars).concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
            if (!plusForSpace2 || !safeChars2.contains(" ")) {
                this.plusForSpace = plusForSpace2;
                this.safeOctets = createSafeOctets(safeChars2);
                return;
            }
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
    }

    private static boolean[] createSafeOctets(String safeChars) {
        int maxChar = -1;
        char[] safeCharArray = safeChars.toCharArray();
        for (char c : safeCharArray) {
            maxChar = Math.max(c, maxChar);
        }
        boolean[] octets = new boolean[(maxChar + 1)];
        for (char c2 : safeCharArray) {
            octets[c2] = true;
        }
        return octets;
    }

    /* access modifiers changed from: protected */
    public int nextEscapeIndex(CharSequence csq, int index, int end) {
        Preconditions.checkNotNull(csq);
        while (index < end) {
            char c = csq.charAt(index);
            boolean[] zArr = this.safeOctets;
            if (c >= zArr.length || !zArr[c]) {
                break;
            }
            index++;
        }
        return index;
    }

    public String escape(String s) {
        Preconditions.checkNotNull(s);
        int slen = s.length();
        for (int index = 0; index < slen; index++) {
            char c = s.charAt(index);
            boolean[] zArr = this.safeOctets;
            if (c >= zArr.length || !zArr[c]) {
                return escapeSlow(s, index);
            }
        }
        return s;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public char[] escape(int cp) {
        boolean[] zArr = this.safeOctets;
        if (cp < zArr.length && zArr[cp]) {
            return null;
        }
        if (cp == 32 && this.plusForSpace) {
            return PLUS_SIGN;
        }
        if (cp <= 127) {
            char[] dest = new char[3];
            dest[0] = '%';
            char[] cArr = UPPER_HEX_DIGITS;
            dest[2] = cArr[cp & 15];
            dest[1] = cArr[cp >>> 4];
            return dest;
        } else if (cp <= 2047) {
            char[] dest2 = new char[6];
            dest2[0] = '%';
            dest2[3] = '%';
            char[] cArr2 = UPPER_HEX_DIGITS;
            dest2[5] = cArr2[cp & 15];
            int cp2 = cp >>> 4;
            dest2[4] = cArr2[(cp2 & 3) | 8];
            int cp3 = cp2 >>> 2;
            dest2[2] = cArr2[cp3 & 15];
            dest2[1] = cArr2[(cp3 >>> 4) | 12];
            return dest2;
        } else if (cp <= 65535) {
            char[] dest3 = new char[9];
            dest3[0] = '%';
            dest3[1] = 'E';
            dest3[3] = '%';
            dest3[6] = '%';
            char[] cArr3 = UPPER_HEX_DIGITS;
            dest3[8] = cArr3[cp & 15];
            int cp4 = cp >>> 4;
            dest3[7] = cArr3[(cp4 & 3) | 8];
            int cp5 = cp4 >>> 2;
            dest3[5] = cArr3[cp5 & 15];
            int cp6 = cp5 >>> 4;
            dest3[4] = cArr3[(cp6 & 3) | 8];
            dest3[2] = cArr3[cp6 >>> 2];
            return dest3;
        } else if (cp <= 1114111) {
            char[] dest4 = new char[12];
            dest4[0] = '%';
            dest4[1] = 'F';
            dest4[3] = '%';
            dest4[6] = '%';
            dest4[9] = '%';
            char[] cArr4 = UPPER_HEX_DIGITS;
            dest4[11] = cArr4[cp & 15];
            int cp7 = cp >>> 4;
            dest4[10] = cArr4[(cp7 & 3) | 8];
            int cp8 = cp7 >>> 2;
            dest4[8] = cArr4[cp8 & 15];
            int cp9 = cp8 >>> 4;
            dest4[7] = cArr4[(cp9 & 3) | 8];
            int cp10 = cp9 >>> 2;
            dest4[5] = cArr4[cp10 & 15];
            int cp11 = cp10 >>> 4;
            dest4[4] = cArr4[(cp11 & 3) | 8];
            dest4[2] = cArr4[(cp11 >>> 2) & 7];
            return dest4;
        } else {
            throw new IllegalArgumentException(new StringBuilder(43).append("Invalid unicode character value ").append(cp).toString());
        }
    }
}
