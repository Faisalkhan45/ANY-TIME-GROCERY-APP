package org.checkerframework.checker.formatter.qual;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import org.checkerframework.dataflow.qual.Pure;

public enum ConversionCategory {
    GENERAL("bBhHsS", (int) null),
    CHAR("cC", Character.class, Byte.class, Short.class, Integer.class),
    INT("doxX", Byte.class, Short.class, Integer.class, Long.class, BigInteger.class),
    FLOAT("eEfgGaA", Float.class, Double.class, BigDecimal.class),
    TIME("tT", Long.class, Calendar.class, Date.class),
    CHAR_AND_INT((String) null, Byte.class, Short.class, Integer.class),
    INT_AND_TIME((String) null, Long.class),
    NULL((String) null, new Class[0]),
    UNUSED((String) null, (int) null);
    
    public final String chars;
    public final Class<?>[] types;

    private ConversionCategory(String chars2, Class<?>... types2) {
        this.chars = chars2;
        if (types2 == null) {
            this.types = types2;
            return;
        }
        List<Class<?>> typesWithPrimitives = new ArrayList<>(types2.length);
        for (Class<?> type : types2) {
            typesWithPrimitives.add(type);
            Class<? extends Object> unwrapPrimitive = unwrapPrimitive(type);
            if (unwrapPrimitive != null) {
                typesWithPrimitives.add(unwrapPrimitive);
            }
        }
        this.types = (Class[]) typesWithPrimitives.toArray(new Class[typesWithPrimitives.size()]);
    }

    private static Class<? extends Object> unwrapPrimitive(Class<?> c) {
        if (c == Byte.class) {
            return Byte.TYPE;
        }
        if (c == Character.class) {
            return Character.TYPE;
        }
        if (c == Short.class) {
            return Short.TYPE;
        }
        if (c == Integer.class) {
            return Integer.TYPE;
        }
        if (c == Long.class) {
            return Long.TYPE;
        }
        if (c == Float.class) {
            return Float.TYPE;
        }
        if (c == Double.class) {
            return Double.TYPE;
        }
        if (c == Boolean.class) {
            return Boolean.TYPE;
        }
        return null;
    }

    public static ConversionCategory fromConversionChar(char c) {
        ConversionCategory[] conversionCategoryArr = {GENERAL, CHAR, INT, FLOAT, TIME};
        for (int i = 0; i < 5; i++) {
            ConversionCategory v = conversionCategoryArr[i];
            if (v.chars.contains(String.valueOf(c))) {
                return v;
            }
        }
        throw new IllegalArgumentException("Bad conversion character " + c);
    }

    private static <E> Set<E> arrayToSet(E[] a) {
        return new HashSet(Arrays.asList(a));
    }

    public static boolean isSubsetOf(ConversionCategory a, ConversionCategory b) {
        return intersect(a, b) == a;
    }

    public static ConversionCategory intersect(ConversionCategory a, ConversionCategory b) {
        ConversionCategory conversionCategory = UNUSED;
        if (a == conversionCategory) {
            return b;
        }
        if (b == conversionCategory) {
            return a;
        }
        ConversionCategory conversionCategory2 = GENERAL;
        if (a == conversionCategory2) {
            return b;
        }
        if (b == conversionCategory2) {
            return a;
        }
        Set<Class<?>> as = arrayToSet(a.types);
        as.retainAll(arrayToSet(b.types));
        ConversionCategory[] conversionCategoryArr = {CHAR, INT, FLOAT, TIME, CHAR_AND_INT, INT_AND_TIME, NULL};
        for (int i = 0; i < 7; i++) {
            ConversionCategory v = conversionCategoryArr[i];
            if (arrayToSet(v.types).equals(as)) {
                return v;
            }
        }
        throw new RuntimeException();
    }

    public static ConversionCategory union(ConversionCategory a, ConversionCategory b) {
        ConversionCategory conversionCategory;
        ConversionCategory conversionCategory2 = UNUSED;
        if (a == conversionCategory2 || b == conversionCategory2) {
            return conversionCategory2;
        }
        ConversionCategory conversionCategory3 = GENERAL;
        if (a == conversionCategory3 || b == conversionCategory3) {
            return conversionCategory3;
        }
        ConversionCategory conversionCategory4 = CHAR_AND_INT;
        if ((a == conversionCategory4 && b == INT_AND_TIME) || (a == (conversionCategory = INT_AND_TIME) && b == conversionCategory4)) {
            return INT;
        }
        Set<Class<?>> as = arrayToSet(a.types);
        as.addAll(arrayToSet(b.types));
        ConversionCategory[] conversionCategoryArr = {NULL, conversionCategory4, conversionCategory, CHAR, INT, FLOAT, TIME};
        for (int i = 0; i < 7; i++) {
            ConversionCategory v = conversionCategoryArr[i];
            if (arrayToSet(v.types).equals(as)) {
                return v;
            }
        }
        return GENERAL;
    }

    public boolean isAssignableFrom(Class<?> argType) {
        if (this.types == null || argType == Void.TYPE) {
            return true;
        }
        for (Class<?> c : this.types) {
            if (c.isAssignableFrom(argType)) {
                return true;
            }
        }
        return false;
    }

    @Pure
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name());
        sb.append(" conversion category");
        Class<?>[] clsArr = this.types;
        if (clsArr == null || clsArr.length == 0) {
            return sb.toString();
        }
        StringJoiner sj = new StringJoiner(", ", "(one of: ", ")");
        for (Class<?> cls : this.types) {
            sj.add(cls.getSimpleName());
        }
        sb.append(" ");
        sb.append(sj);
        return sb.toString();
    }
}
