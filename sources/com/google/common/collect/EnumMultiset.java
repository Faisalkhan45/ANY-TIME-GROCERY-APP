package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient int[] counts;
    private transient int distinctElements;
    /* access modifiers changed from: private */
    public transient E[] enumConstants;
    private transient long size;
    private transient Class<E> type;

    public /* bridge */ /* synthetic */ boolean contains(@CheckForNull Object obj) {
        return super.contains(obj);
    }

    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ boolean setCount(@ParametricNullness Object obj, int i, int i2) {
        return super.setCount(obj, i, i2);
    }

    static /* synthetic */ int access$210(EnumMultiset x0) {
        int i = x0.distinctElements;
        x0.distinctElements = i - 1;
        return i;
    }

    static /* synthetic */ long access$322(EnumMultiset x0, long x1) {
        long j = x0.size - x1;
        x0.size = j;
        return j;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> type2) {
        return new EnumMultiset<>(type2);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements) {
        Iterator<E> iterator = elements.iterator();
        Preconditions.checkArgument(iterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> multiset = new EnumMultiset<>(((Enum) iterator.next()).getDeclaringClass());
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements, Class<E> type2) {
        EnumMultiset<E> result = create(type2);
        Iterables.addAll(result, elements);
        return result;
    }

    private EnumMultiset(Class<E> type2) {
        this.type = type2;
        Preconditions.checkArgument(type2.isEnum());
        E[] eArr = (Enum[]) type2.getEnumConstants();
        this.enumConstants = eArr;
        this.counts = new int[eArr.length];
    }

    private boolean isActuallyE(@CheckForNull Object o) {
        if (!(o instanceof Enum)) {
            return false;
        }
        Enum<?> e = (Enum) o;
        int index = e.ordinal();
        Enum<?>[] enumArr = this.enumConstants;
        if (index >= enumArr.length || enumArr[index] != e) {
            return false;
        }
        return true;
    }

    private void checkIsE(Object element) {
        Preconditions.checkNotNull(element);
        if (!isActuallyE(element)) {
            String valueOf = String.valueOf(this.type);
            String valueOf2 = String.valueOf(element);
            throw new ClassCastException(new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append("Expected an ").append(valueOf).append(" but got ").append(valueOf2).toString());
        }
    }

    /* access modifiers changed from: package-private */
    public int distinctElements() {
        return this.distinctElements;
    }

    public int size() {
        return Ints.saturatedCast(this.size);
    }

    public int count(@CheckForNull Object element) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        return this.counts[((Enum) element).ordinal()];
    }

    public int add(E element, int occurrences) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = element.ordinal();
        int oldCount = this.counts[index];
        long newCount = ((long) oldCount) + ((long) occurrences);
        Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", newCount);
        this.counts[index] = (int) newCount;
        if (oldCount == 0) {
            this.distinctElements++;
        }
        this.size += (long) occurrences;
        return oldCount;
    }

    public int remove(@CheckForNull Object element, int occurrences) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        Enum<?> e = (Enum) element;
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = e.ordinal();
        int[] iArr = this.counts;
        int oldCount = iArr[index];
        if (oldCount == 0) {
            return 0;
        }
        if (oldCount <= occurrences) {
            iArr[index] = 0;
            this.distinctElements--;
            this.size -= (long) oldCount;
        } else {
            iArr[index] = oldCount - occurrences;
            this.size -= (long) occurrences;
        }
        return oldCount;
    }

    public int setCount(E element, int count) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(count, "count");
        int index = element.ordinal();
        int[] iArr = this.counts;
        int oldCount = iArr[index];
        iArr[index] = count;
        this.size += (long) (count - oldCount);
        if (oldCount == 0 && count > 0) {
            this.distinctElements++;
        } else if (oldCount > 0 && count == 0) {
            this.distinctElements--;
        }
        return oldCount;
    }

    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0;
        this.distinctElements = 0;
    }

    abstract class Itr<T> implements Iterator<T> {
        int index = 0;
        int toRemove = -1;

        /* access modifiers changed from: package-private */
        public abstract T output(int i);

        Itr() {
        }

        public boolean hasNext() {
            while (this.index < EnumMultiset.this.enumConstants.length) {
                int[] access$100 = EnumMultiset.this.counts;
                int i = this.index;
                if (access$100[i] > 0) {
                    return true;
                }
                this.index = i + 1;
            }
            return false;
        }

        public T next() {
            if (hasNext()) {
                T result = output(this.index);
                int i = this.index;
                this.toRemove = i;
                this.index = i + 1;
                return result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove >= 0);
            if (EnumMultiset.this.counts[this.toRemove] > 0) {
                EnumMultiset.access$210(EnumMultiset.this);
                EnumMultiset enumMultiset = EnumMultiset.this;
                EnumMultiset.access$322(enumMultiset, (long) enumMultiset.counts[this.toRemove]);
                EnumMultiset.this.counts[this.toRemove] = 0;
            }
            this.toRemove = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> elementIterator() {
        return new EnumMultiset<E>.Itr<E>() {
            /* access modifiers changed from: package-private */
            public E output(int index) {
                return EnumMultiset.this.enumConstants[index];
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new EnumMultiset<E>.Itr<Multiset.Entry<E>>() {
            /* access modifiers changed from: package-private */
            public Multiset.Entry<E> output(final int index) {
                return new Multisets.AbstractEntry<E>() {
                    public E getElement() {
                        return EnumMultiset.this.enumConstants[index];
                    }

                    public int getCount() {
                        return EnumMultiset.this.counts[index];
                    }
                };
            }
        };
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.type);
        Serialization.writeMultiset(this, stream);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        Class<E> localType = (Class) stream.readObject();
        this.type = localType;
        E[] eArr = (Enum[]) localType.getEnumConstants();
        this.enumConstants = eArr;
        this.counts = new int[eArr.length];
        Serialization.populateMultiset(this, stream);
    }
}
