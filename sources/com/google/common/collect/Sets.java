package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class Sets {
    private Sets() {
    }

    static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        ImprovedAbstractSet() {
        }

        public boolean removeAll(Collection<?> c) {
            return Sets.removeAllImpl((Set<?>) this, c);
        }

        public boolean retainAll(Collection<?> c) {
            return super.retainAll((Collection) Preconditions.checkNotNull(c));
        }
    }

    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E anElement, E... otherElements) {
        return ImmutableEnumSet.asImmutable(EnumSet.of(anElement, otherElements));
    }

    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> elements) {
        if (elements instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) elements;
        }
        if (elements instanceof Collection) {
            Collection<E> collection = (Collection) elements;
            if (collection.isEmpty()) {
                return ImmutableSet.of();
            }
            return ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator<E> itr = elements.iterator();
        if (!itr.hasNext()) {
            return ImmutableSet.of();
        }
        EnumSet<E> enumSet = EnumSet.of((Enum) itr.next());
        Iterators.addAll(enumSet, itr);
        return ImmutableEnumSet.asImmutable(enumSet);
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> elementType) {
        EnumSet<E> set = EnumSet.noneOf(elementType);
        Iterables.addAll(set, iterable);
        return set;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> set = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new HashSet<>((Collection) elements);
        }
        return newHashSet(elements.iterator());
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
        HashSet<E> set = newHashSet();
        Iterators.addAll(set, elements);
        return set;
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<>(Maps.capacity(expectedSize));
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> elements) {
        Set<E> set = newConcurrentHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            return new LinkedHashSet<>((Collection) elements);
        }
        LinkedHashSet<E> set = newLinkedHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return new LinkedHashSet<>(Maps.capacity(expectedSize));
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
        TreeSet<E> set = newTreeSet();
        Iterables.addAll(set, elements);
        return set;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        return new TreeSet<>((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(Maps.newIdentityHashMap());
    }

    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Iterable<? extends E>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <E> java.util.concurrent.CopyOnWriteArraySet<E> newCopyOnWriteArraySet(java.lang.Iterable<? extends E> r2) {
        /*
            boolean r0 = r2 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0008
            r0 = r2
            java.util.Collection r0 = (java.util.Collection) r0
            goto L_0x000c
        L_0x0008:
            java.util.ArrayList r0 = com.google.common.collect.Lists.newArrayList(r2)
        L_0x000c:
            java.util.concurrent.CopyOnWriteArraySet r1 = new java.util.concurrent.CopyOnWriteArraySet
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Sets.newCopyOnWriteArraySet(java.lang.Iterable):java.util.concurrent.CopyOnWriteArraySet");
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, ((Enum) collection.iterator().next()).getDeclaringClass());
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> type) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        return makeComplementByHand(collection, type);
    }

    private static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> type) {
        EnumSet<E> result = EnumSet.allOf(type);
        result.removeAll(collection);
        return result;
    }

    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        public abstract UnmodifiableIterator<E> iterator();

        private SetView() {
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf(this);
        }

        public <S extends Set<E>> S copyInto(S set) {
            set.addAll(this);
            return set;
        }

        @Deprecated
        public final boolean add(@ParametricNullness E e) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final boolean remove(@CheckForNull Object object) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }
    }

    public static <E> SetView<E> union(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            public int size() {
                int size = set1.size();
                for (E e : set2) {
                    if (!set1.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set1.isEmpty() && set2.isEmpty();
            }

            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    final Iterator<? extends E> itr1;
                    final Iterator<? extends E> itr2;

                    {
                        this.itr1 = set1.iterator();
                        this.itr2 = set2.iterator();
                    }

                    /* access modifiers changed from: protected */
                    @CheckForNull
                    public E computeNext() {
                        if (this.itr1.hasNext()) {
                            return this.itr1.next();
                        }
                        while (this.itr2.hasNext()) {
                            E e = this.itr2.next();
                            if (!set1.contains(e)) {
                                return e;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public boolean contains(@CheckForNull Object object) {
                return set1.contains(object) || set2.contains(object);
            }

            public <S extends Set<E>> S copyInto(S set) {
                set.addAll(set1);
                set.addAll(set2);
                return set;
            }

            public ImmutableSet<E> immutableCopy() {
                return new ImmutableSet.Builder().addAll((Iterable) set1).addAll((Iterable) set2).build();
            }
        };
    }

    public static <E> SetView<E> intersection(final Set<E> set1, final Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    final Iterator<E> itr;

                    {
                        this.itr = set1.iterator();
                    }

                    /* access modifiers changed from: protected */
                    @CheckForNull
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E e = this.itr.next();
                            if (set2.contains(e)) {
                                return e;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (E e : set1) {
                    if (set2.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return Collections.disjoint(set2, set1);
            }

            public boolean contains(@CheckForNull Object object) {
                return set1.contains(object) && set2.contains(object);
            }

            public boolean containsAll(Collection<?> collection) {
                return set1.containsAll(collection) && set2.containsAll(collection);
            }
        };
    }

    public static <E> SetView<E> difference(final Set<E> set1, final Set<?> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() {
                    final Iterator<E> itr;

                    {
                        this.itr = set1.iterator();
                    }

                    /* access modifiers changed from: protected */
                    @CheckForNull
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E e = this.itr.next();
                            if (!set2.contains(e)) {
                                return e;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (E e : set1) {
                    if (!set2.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set2.containsAll(set1);
            }

            public boolean contains(@CheckForNull Object element) {
                return set1.contains(element) && !set2.contains(element);
            }
        };
    }

    public static <E> SetView<E> symmetricDifference(final Set<? extends E> set1, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set1, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            public UnmodifiableIterator<E> iterator() {
                final Iterator<? extends E> itr1 = set1.iterator();
                final Iterator<? extends E> itr2 = set2.iterator();
                return new AbstractIterator<E>() {
                    @CheckForNull
                    public E computeNext() {
                        while (itr1.hasNext()) {
                            E elem1 = itr1.next();
                            if (!set2.contains(elem1)) {
                                return elem1;
                            }
                        }
                        while (itr2.hasNext()) {
                            E elem2 = itr2.next();
                            if (!set1.contains(elem2)) {
                                return elem2;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            public int size() {
                int size = 0;
                for (E e : set1) {
                    if (!set2.contains(e)) {
                        size++;
                    }
                }
                for (E e2 : set2) {
                    if (!set1.contains(e2)) {
                        size++;
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return set1.equals(set2);
            }

            public boolean contains(@CheckForNull Object element) {
                return set1.contains(element) ^ set2.contains(element);
            }
        };
    }

    public static <E> Set<E> filter(Set<E> unfiltered, Predicate<? super E> predicate) {
        if (unfiltered instanceof SortedSet) {
            return filter((SortedSet) unfiltered, predicate);
        }
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredSet((Set) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredSet((Set) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    public static <E> SortedSet<E> filter(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredSortedSet((SortedSet) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredSortedSet((SortedSet) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    public static <E> NavigableSet<E> filter(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
        if (!(unfiltered instanceof FilteredSet)) {
            return new FilteredNavigableSet((NavigableSet) Preconditions.checkNotNull(unfiltered), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet<E> filtered = (FilteredSet) unfiltered;
        return new FilteredNavigableSet((NavigableSet) filtered.unfiltered, Predicates.and(filtered.predicate, predicate));
    }

    private static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        FilteredSet(Set<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        public boolean equals(@CheckForNull Object object) {
            return Sets.equalsImpl(this, object);
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    private static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        FilteredSortedSet(SortedSet<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        @CheckForNull
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).subSet(fromElement, toElement), this.predicate);
        }

        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).headSet(toElement), this.predicate);
        }

        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(fromElement), this.predicate);
        }

        @ParametricNullness
        public E first() {
            return Iterators.find(this.unfiltered.iterator(), this.predicate);
        }

        @ParametricNullness
        public E last() {
            SortedSet<E> sortedUnfiltered = (SortedSet) this.unfiltered;
            while (true) {
                E element = sortedUnfiltered.last();
                if (this.predicate.apply(element)) {
                    return element;
                }
                sortedUnfiltered = sortedUnfiltered.headSet(element);
            }
        }
    }

    private static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        FilteredNavigableSet(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
            super(unfiltered, predicate);
        }

        /* access modifiers changed from: package-private */
        public NavigableSet<E> unfiltered() {
            return (NavigableSet) this.unfiltered;
        }

        @CheckForNull
        public E lower(@ParametricNullness E e) {
            return Iterators.find(unfiltered().headSet(e, false).descendingIterator(), this.predicate, null);
        }

        @CheckForNull
        public E floor(@ParametricNullness E e) {
            return Iterators.find(unfiltered().headSet(e, true).descendingIterator(), this.predicate, null);
        }

        @CheckForNull
        public E ceiling(@ParametricNullness E e) {
            return Iterables.find(unfiltered().tailSet(e, true), this.predicate, null);
        }

        @CheckForNull
        public E higher(@ParametricNullness E e) {
            return Iterables.find(unfiltered().tailSet(e, false), this.predicate, null);
        }

        @CheckForNull
        public E pollFirst() {
            return Iterables.removeFirstMatching(unfiltered(), this.predicate);
        }

        @CheckForNull
        public E pollLast() {
            return Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
        }

        public NavigableSet<E> descendingSet() {
            return Sets.filter(unfiltered().descendingSet(), this.predicate);
        }

        public Iterator<E> descendingIterator() {
            return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
        }

        @ParametricNullness
        public E last() {
            return Iterators.find(unfiltered().descendingIterator(), this.predicate);
        }

        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return Sets.filter(unfiltered().subSet(fromElement, fromInclusive, toElement, toInclusive), this.predicate);
        }

        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return Sets.filter(unfiltered().headSet(toElement, inclusive), this.predicate);
        }

        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return Sets.filter(unfiltered().tailSet(fromElement, inclusive), this.predicate);
        }
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> sets) {
        return CartesianSet.create(sets);
    }

    @SafeVarargs
    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... sets) {
        return cartesianProduct(Arrays.asList(sets));
    }

    private static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        private final transient ImmutableList<ImmutableSet<E>> axes;
        private final transient CartesianList<E> delegate;

        static <E> Set<List<E>> create(List<? extends Set<? extends E>> sets) {
            ImmutableList.Builder<ImmutableSet<E>> axesBuilder = new ImmutableList.Builder<>(sets.size());
            for (Set<? extends E> set : sets) {
                ImmutableSet<E> copy = ImmutableSet.copyOf(set);
                if (copy.isEmpty()) {
                    return ImmutableSet.of();
                }
                axesBuilder.add((Object) copy);
            }
            final ImmutableList<ImmutableSet<E>> axes2 = axesBuilder.build();
            return new CartesianSet(axes2, new CartesianList(new ImmutableList<List<E>>() {
                public int size() {
                    return ImmutableList.this.size();
                }

                public List<E> get(int index) {
                    return ((ImmutableSet) ImmutableList.this.get(index)).asList();
                }

                /* access modifiers changed from: package-private */
                public boolean isPartialView() {
                    return true;
                }
            }));
        }

        private CartesianSet(ImmutableList<ImmutableSet<E>> axes2, CartesianList<E> delegate2) {
            this.axes = axes2;
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        public Collection<List<E>> delegate() {
            return this.delegate;
        }

        public boolean contains(@CheckForNull Object object) {
            if (!(object instanceof List)) {
                return false;
            }
            List<?> list = (List) object;
            if (list.size() != this.axes.size()) {
                return false;
            }
            int i = 0;
            for (Object o : list) {
                if (!((ImmutableSet) this.axes.get(i)).contains(o)) {
                    return false;
                }
                i++;
            }
            return true;
        }

        public boolean equals(@CheckForNull Object object) {
            if (object instanceof CartesianSet) {
                return this.axes.equals(((CartesianSet) object).axes);
            }
            return super.equals(object);
        }

        public int hashCode() {
            int adjust = size() - 1;
            for (int i = 0; i < this.axes.size(); i++) {
                adjust = ~(~(adjust * 31));
            }
            int hash = 1;
            UnmodifiableIterator<ImmutableSet<E>> it = this.axes.iterator();
            while (it.hasNext()) {
                Set<E> axis = it.next();
                hash = ~(~((hash * 31) + ((size() / axis.size()) * axis.hashCode())));
            }
            return ~(~(hash + adjust));
        }
    }

    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    private static final class SubSet<E> extends AbstractSet<E> {
        /* access modifiers changed from: private */
        public final ImmutableMap<E, Integer> inputSet;
        /* access modifiers changed from: private */
        public final int mask;

        SubSet(ImmutableMap<E, Integer> inputSet2, int mask2) {
            this.inputSet = inputSet2;
            this.mask = mask2;
        }

        public Iterator<E> iterator() {
            return new UnmodifiableIterator<E>() {
                final ImmutableList<E> elements;
                int remainingSetBits;

                {
                    this.elements = SubSet.this.inputSet.keySet().asList();
                    this.remainingSetBits = SubSet.this.mask;
                }

                public boolean hasNext() {
                    return this.remainingSetBits != 0;
                }

                public E next() {
                    int index = Integer.numberOfTrailingZeros(this.remainingSetBits);
                    if (index != 32) {
                        this.remainingSetBits &= ~(1 << index);
                        return this.elements.get(index);
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        public int size() {
            return Integer.bitCount(this.mask);
        }

        public boolean contains(@CheckForNull Object o) {
            Integer index = this.inputSet.get(o);
            return (index == null || (this.mask & (1 << index.intValue())) == 0) ? false : true;
        }
    }

    private static final class PowerSet<E> extends AbstractSet<Set<E>> {
        final ImmutableMap<E, Integer> inputSet;

        PowerSet(Set<E> input) {
            Preconditions.checkArgument(input.size() <= 30, "Too many elements to create power set: %s > 30", input.size());
            this.inputSet = Maps.indexMap(input);
        }

        public int size() {
            return 1 << this.inputSet.size();
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) {
                /* access modifiers changed from: protected */
                public Set<E> get(int setBits) {
                    return new SubSet(PowerSet.this.inputSet, setBits);
                }
            };
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.inputSet.keySet().containsAll((Set) obj);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof PowerSet) {
                return this.inputSet.keySet().equals(((PowerSet) obj).inputSet.keySet());
            }
            return super.equals(obj);
        }

        public int hashCode() {
            return this.inputSet.keySet().hashCode() << (this.inputSet.size() - 1);
        }

        public String toString() {
            String valueOf = String.valueOf(this.inputSet);
            return new StringBuilder(String.valueOf(valueOf).length() + 10).append("powerSet(").append(valueOf).append(")").toString();
        }
    }

    public static <E> Set<Set<E>> combinations(Set<E> set, final int size) {
        final ImmutableMap<E, Integer> index = Maps.indexMap(set);
        CollectPreconditions.checkNonnegative(size, "size");
        Preconditions.checkArgument(size <= index.size(), "size (%s) must be <= set.size() (%s)", size, index.size());
        if (size == 0) {
            return ImmutableSet.of(ImmutableSet.of());
        }
        if (size == index.size()) {
            return ImmutableSet.of(index.keySet());
        }
        return new AbstractSet<Set<E>>() {
            public boolean contains(@CheckForNull Object o) {
                if (!(o instanceof Set)) {
                    return false;
                }
                Set<?> s = (Set) o;
                if (s.size() != size || !index.keySet().containsAll(s)) {
                    return false;
                }
                return true;
            }

            public Iterator<Set<E>> iterator() {
                return new AbstractIterator<Set<E>>() {
                    final BitSet bits;

                    {
                        this.bits = new BitSet(index.size());
                    }

                    /* access modifiers changed from: protected */
                    @CheckForNull
                    public Set<E> computeNext() {
                        if (this.bits.isEmpty()) {
                            this.bits.set(0, size);
                        } else {
                            int firstSetBit = this.bits.nextSetBit(0);
                            int bitToFlip = this.bits.nextClearBit(firstSetBit);
                            if (bitToFlip == index.size()) {
                                return (Set) endOfData();
                            }
                            this.bits.set(0, (bitToFlip - firstSetBit) - 1);
                            this.bits.clear((bitToFlip - firstSetBit) - 1, bitToFlip);
                            this.bits.set(bitToFlip);
                        }
                        final BitSet copy = (BitSet) this.bits.clone();
                        return new AbstractSet<E>() {
                            public boolean contains(@CheckForNull Object o) {
                                Integer i = (Integer) index.get(o);
                                return i != null && copy.get(i.intValue());
                            }

                            public Iterator<E> iterator() {
                                return new AbstractIterator<E>() {
                                    int i = -1;

                                    /* access modifiers changed from: protected */
                                    @CheckForNull
                                    public E computeNext() {
                                        int nextSetBit = copy.nextSetBit(this.i + 1);
                                        this.i = nextSetBit;
                                        if (nextSetBit == -1) {
                                            return endOfData();
                                        }
                                        return index.keySet().asList().get(this.i);
                                    }
                                };
                            }

                            public int size() {
                                return size;
                            }
                        };
                    }
                };
            }

            public int size() {
                return IntMath.binomial(index.size(), size);
            }

            public String toString() {
                String valueOf = String.valueOf(index.keySet());
                return new StringBuilder(String.valueOf(valueOf).length() + 32).append("Sets.combinations(").append(valueOf).append(", ").append(size).append(")").toString();
            }
        };
    }

    static int hashCodeImpl(Set<?> s) {
        int hashCode = 0;
        Iterator<?> it = s.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            hashCode = ~(~(hashCode + (o != null ? o.hashCode() : 0)));
        }
        return hashCode;
    }

    static boolean equalsImpl(Set<?> s, @CheckForNull Object object) {
        if (s == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> o = (Set) object;
        try {
            if (s.size() != o.size() || !s.containsAll(o)) {
                return false;
            }
            return true;
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> set) {
        if ((set instanceof ImmutableCollection) || (set instanceof UnmodifiableNavigableSet)) {
            return set;
        }
        return new UnmodifiableNavigableSet(set);
    }

    static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {
        private static final long serialVersionUID = 0;
        private final NavigableSet<E> delegate;
        @CheckForNull
        private transient UnmodifiableNavigableSet<E> descendingSet;
        private final SortedSet<E> unmodifiableDelegate;

        UnmodifiableNavigableSet(NavigableSet<E> delegate2) {
            this.delegate = (NavigableSet) Preconditions.checkNotNull(delegate2);
            this.unmodifiableDelegate = Collections.unmodifiableSortedSet(delegate2);
        }

        /* access modifiers changed from: protected */
        public SortedSet<E> delegate() {
            return this.unmodifiableDelegate;
        }

        @CheckForNull
        public E lower(@ParametricNullness E e) {
            return this.delegate.lower(e);
        }

        @CheckForNull
        public E floor(@ParametricNullness E e) {
            return this.delegate.floor(e);
        }

        @CheckForNull
        public E ceiling(@ParametricNullness E e) {
            return this.delegate.ceiling(e);
        }

        @CheckForNull
        public E higher(@ParametricNullness E e) {
            return this.delegate.higher(e);
        }

        @CheckForNull
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @CheckForNull
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        public NavigableSet<E> descendingSet() {
            UnmodifiableNavigableSet<E> result = this.descendingSet;
            if (result != null) {
                return result;
            }
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet = new UnmodifiableNavigableSet<>(this.delegate.descendingSet());
            this.descendingSet = unmodifiableNavigableSet;
            UnmodifiableNavigableSet<E> result2 = unmodifiableNavigableSet;
            result2.descendingSet = this;
            return result2;
        }

        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(toElement, inclusive));
        }

        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(fromElement, inclusive));
        }
    }

    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.navigableSet(navigableSet);
    }

    static boolean removeAllImpl(Set<?> set, Iterator<?> iterator) {
        boolean changed = false;
        while (iterator.hasNext()) {
            changed |= set.remove(iterator.next());
        }
        return changed;
    }

    static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return removeAllImpl(set, collection.iterator());
        }
        return Iterators.removeAll(set.iterator(), collection);
    }

    static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        private final NavigableSet<E> forward;

        DescendingSet(NavigableSet<E> forward2) {
            this.forward = forward2;
        }

        /* access modifiers changed from: protected */
        public NavigableSet<E> delegate() {
            return this.forward;
        }

        @CheckForNull
        public E lower(@ParametricNullness E e) {
            return this.forward.higher(e);
        }

        @CheckForNull
        public E floor(@ParametricNullness E e) {
            return this.forward.ceiling(e);
        }

        @CheckForNull
        public E ceiling(@ParametricNullness E e) {
            return this.forward.floor(e);
        }

        @CheckForNull
        public E higher(@ParametricNullness E e) {
            return this.forward.lower(e);
        }

        @CheckForNull
        public E pollFirst() {
            return this.forward.pollLast();
        }

        @CheckForNull
        public E pollLast() {
            return this.forward.pollFirst();
        }

        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        public NavigableSet<E> subSet(@ParametricNullness E fromElement, boolean fromInclusive, @ParametricNullness E toElement, boolean toInclusive) {
            return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
        }

        public SortedSet<E> subSet(@ParametricNullness E fromElement, @ParametricNullness E toElement) {
            return standardSubSet(fromElement, toElement);
        }

        public NavigableSet<E> headSet(@ParametricNullness E toElement, boolean inclusive) {
            return this.forward.tailSet(toElement, inclusive).descendingSet();
        }

        public SortedSet<E> headSet(@ParametricNullness E toElement) {
            return standardHeadSet(toElement);
        }

        public NavigableSet<E> tailSet(@ParametricNullness E fromElement, boolean inclusive) {
            return this.forward.headSet(fromElement, inclusive).descendingSet();
        }

        public SortedSet<E> tailSet(@ParametricNullness E fromElement) {
            return standardTailSet(fromElement);
        }

        public Comparator<? super E> comparator() {
            Comparator<? super E> forwardComparator = this.forward.comparator();
            if (forwardComparator == null) {
                return Ordering.natural().reverse();
            }
            return reverse(forwardComparator);
        }

        private static <T> Ordering<T> reverse(Comparator<T> forward2) {
            return Ordering.from(forward2).reverse();
        }

        @ParametricNullness
        public E first() {
            return this.forward.last();
        }

        @ParametricNullness
        public E last() {
            return this.forward.first();
        }

        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        public Object[] toArray() {
            return standardToArray();
        }

        public <T> T[] toArray(T[] array) {
            return standardToArray(array);
        }

        public String toString() {
            return standardToString();
        }
    }

    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> set, Range<K> range) {
        boolean z = true;
        if (set.comparator() != null && set.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(set.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            boolean z2 = range.lowerBoundType() == BoundType.CLOSED;
            K upperEndpoint = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return set.subSet(lowerEndpoint, z2, upperEndpoint, z);
        } else if (range.hasLowerBound()) {
            K lowerEndpoint2 = range.lowerEndpoint();
            if (range.lowerBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return set.tailSet(lowerEndpoint2, z);
        } else if (!range.hasUpperBound()) {
            return (NavigableSet) Preconditions.checkNotNull(set);
        } else {
            K upperEndpoint2 = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return set.headSet(upperEndpoint2, z);
        }
    }
}
