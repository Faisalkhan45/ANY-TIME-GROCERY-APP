package com.google.common.collect;

import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class RegularImmutableSet<E> extends ImmutableSet<E> {
    static final RegularImmutableSet<Object> EMPTY;
    private static final Object[] EMPTY_ARRAY;
    final transient Object[] elements;
    private final transient int hashCode;
    private final transient int mask;
    private final transient int size;
    final transient Object[] table;

    static {
        Object[] objArr = new Object[0];
        EMPTY_ARRAY = objArr;
        EMPTY = new RegularImmutableSet(objArr, 0, objArr, 0, 0);
    }

    RegularImmutableSet(Object[] elements2, int hashCode2, Object[] table2, int mask2, int size2) {
        this.elements = elements2;
        this.hashCode = hashCode2;
        this.table = table2;
        this.mask = mask2;
        this.size = size2;
    }

    public boolean contains(@CheckForNull Object target) {
        Object[] table2 = this.table;
        if (target == null || table2.length == 0) {
            return false;
        }
        int i = Hashing.smearedHash(target);
        while (true) {
            int i2 = i & this.mask;
            Object candidate = table2[i2];
            if (candidate == null) {
                return false;
            }
            if (candidate.equals(target)) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public int size() {
        return this.size;
    }

    public UnmodifiableIterator<E> iterator() {
        return asList().iterator();
    }

    /* access modifiers changed from: package-private */
    public Object[] internalArray() {
        return this.elements;
    }

    /* access modifiers changed from: package-private */
    public int internalArrayStart() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int internalArrayEnd() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] dst, int offset) {
        System.arraycopy(this.elements, 0, dst, offset, this.size);
        return this.size + offset;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(this.elements, this.size);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int hashCode() {
        return this.hashCode;
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return true;
    }
}
