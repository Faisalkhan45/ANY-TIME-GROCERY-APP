package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public class TreeRangeSet<C extends Comparable<?>> extends AbstractRangeSet<C> implements Serializable {
    @CheckForNull
    private transient Set<Range<C>> asDescendingSetOfRanges;
    @CheckForNull
    private transient Set<Range<C>> asRanges;
    @CheckForNull
    private transient RangeSet<C> complement;
    final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;

    public /* bridge */ /* synthetic */ void addAll(RangeSet rangeSet) {
        super.addAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ void addAll(Iterable iterable) {
        super.addAll(iterable);
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ boolean enclosesAll(Iterable iterable) {
        return super.enclosesAll(iterable);
    }

    public /* bridge */ /* synthetic */ boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ void removeAll(RangeSet rangeSet) {
        super.removeAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ void removeAll(Iterable iterable) {
        super.removeAll(iterable);
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create() {
        return new TreeRangeSet<>(new TreeMap());
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(RangeSet<C> rangeSet) {
        TreeRangeSet<C> result = create();
        result.addAll((RangeSet) rangeSet);
        return result;
    }

    public static <C extends Comparable<?>> TreeRangeSet<C> create(Iterable<Range<C>> ranges) {
        TreeRangeSet<C> result = create();
        result.addAll((Iterable) ranges);
        return result;
    }

    private TreeRangeSet(NavigableMap<Cut<C>, Range<C>> rangesByLowerCut) {
        this.rangesByLowerBound = rangesByLowerCut;
    }

    public Set<Range<C>> asRanges() {
        Set<Range<C>> result = this.asRanges;
        if (result != null) {
            return result;
        }
        AsRanges asRanges2 = new AsRanges(this, this.rangesByLowerBound.values());
        this.asRanges = asRanges2;
        return asRanges2;
    }

    public Set<Range<C>> asDescendingSetOfRanges() {
        Set<Range<C>> result = this.asDescendingSetOfRanges;
        if (result != null) {
            return result;
        }
        AsRanges asRanges2 = new AsRanges(this, this.rangesByLowerBound.descendingMap().values());
        this.asDescendingSetOfRanges = asRanges2;
        return asRanges2;
    }

    final class AsRanges extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
        final Collection<Range<C>> delegate;

        AsRanges(TreeRangeSet this$0, Collection<Range<C>> delegate2) {
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        public Collection<Range<C>> delegate() {
            return this.delegate;
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        public boolean equals(@CheckForNull Object o) {
            return Sets.equalsImpl(this, o);
        }
    }

    @CheckForNull
    public Range<C> rangeContaining(C value) {
        Preconditions.checkNotNull(value);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(Cut.belowValue(value));
        if (floorEntry == null || !floorEntry.getValue().contains(value)) {
            return null;
        }
        return floorEntry.getValue();
    }

    public boolean intersects(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> ceilingEntry = this.rangesByLowerBound.ceilingEntry(range.lowerBound);
        if (ceilingEntry != null && ceilingEntry.getValue().isConnected(range) && !ceilingEntry.getValue().intersection(range).isEmpty()) {
            return true;
        }
        Map.Entry<Cut<C>, Range<C>> priorEntry = this.rangesByLowerBound.lowerEntry(range.lowerBound);
        if (priorEntry == null || !priorEntry.getValue().isConnected(range) || priorEntry.getValue().intersection(range).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean encloses(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        return floorEntry != null && floorEntry.getValue().encloses(range);
    }

    /* access modifiers changed from: private */
    @CheckForNull
    public Range<C> rangeEnclosing(Range<C> range) {
        Preconditions.checkNotNull(range);
        Map.Entry<Cut<C>, Range<C>> floorEntry = this.rangesByLowerBound.floorEntry(range.lowerBound);
        if (floorEntry == null || !floorEntry.getValue().encloses(range)) {
            return null;
        }
        return floorEntry.getValue();
    }

    public Range<C> span() {
        Map.Entry<Cut<C>, Range<C>> firstEntry = this.rangesByLowerBound.firstEntry();
        Map.Entry<Cut<C>, Range<C>> lastEntry = this.rangesByLowerBound.lastEntry();
        if (firstEntry != null && lastEntry != null) {
            return Range.create(firstEntry.getValue().lowerBound, lastEntry.getValue().upperBound);
        }
        throw new NoSuchElementException();
    }

    public void add(Range<C> rangeToAdd) {
        Preconditions.checkNotNull(rangeToAdd);
        if (!rangeToAdd.isEmpty()) {
            Cut<C> lbToAdd = rangeToAdd.lowerBound;
            Cut<C> ubToAdd = rangeToAdd.upperBound;
            Map.Entry<Cut<C>, Range<C>> entryBelowLB = this.rangesByLowerBound.lowerEntry(lbToAdd);
            if (entryBelowLB != null) {
                Range<C> rangeBelowLB = entryBelowLB.getValue();
                if (rangeBelowLB.upperBound.compareTo(lbToAdd) >= 0) {
                    if (rangeBelowLB.upperBound.compareTo(ubToAdd) >= 0) {
                        ubToAdd = rangeBelowLB.upperBound;
                    }
                    lbToAdd = rangeBelowLB.lowerBound;
                }
            }
            Map.Entry<Cut<C>, Range<C>> entryBelowUB = this.rangesByLowerBound.floorEntry(ubToAdd);
            if (entryBelowUB != null) {
                Range<C> rangeBelowUB = entryBelowUB.getValue();
                if (rangeBelowUB.upperBound.compareTo(ubToAdd) >= 0) {
                    ubToAdd = rangeBelowUB.upperBound;
                }
            }
            this.rangesByLowerBound.subMap(lbToAdd, ubToAdd).clear();
            replaceRangeWithSameLowerBound(Range.create(lbToAdd, ubToAdd));
        }
    }

    public void remove(Range<C> rangeToRemove) {
        Preconditions.checkNotNull(rangeToRemove);
        if (!rangeToRemove.isEmpty()) {
            Map.Entry<Cut<C>, Range<C>> entryBelowLB = this.rangesByLowerBound.lowerEntry(rangeToRemove.lowerBound);
            if (entryBelowLB != null) {
                Range<C> rangeBelowLB = entryBelowLB.getValue();
                if (rangeBelowLB.upperBound.compareTo(rangeToRemove.lowerBound) >= 0) {
                    if (rangeToRemove.hasUpperBound() && rangeBelowLB.upperBound.compareTo(rangeToRemove.upperBound) >= 0) {
                        replaceRangeWithSameLowerBound(Range.create(rangeToRemove.upperBound, rangeBelowLB.upperBound));
                    }
                    replaceRangeWithSameLowerBound(Range.create(rangeBelowLB.lowerBound, rangeToRemove.lowerBound));
                }
            }
            Map.Entry<Cut<C>, Range<C>> entryBelowUB = this.rangesByLowerBound.floorEntry(rangeToRemove.upperBound);
            if (entryBelowUB != null) {
                Range<C> rangeBelowUB = entryBelowUB.getValue();
                if (rangeToRemove.hasUpperBound() && rangeBelowUB.upperBound.compareTo(rangeToRemove.upperBound) >= 0) {
                    replaceRangeWithSameLowerBound(Range.create(rangeToRemove.upperBound, rangeBelowUB.upperBound));
                }
            }
            this.rangesByLowerBound.subMap(rangeToRemove.lowerBound, rangeToRemove.upperBound).clear();
        }
    }

    private void replaceRangeWithSameLowerBound(Range<C> range) {
        if (range.isEmpty()) {
            this.rangesByLowerBound.remove(range.lowerBound);
        } else {
            this.rangesByLowerBound.put(range.lowerBound, range);
        }
    }

    public RangeSet<C> complement() {
        RangeSet<C> result = this.complement;
        if (result != null) {
            return result;
        }
        Complement complement2 = new Complement();
        this.complement = complement2;
        return complement2;
    }

    static final class RangesByUpperBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        /* access modifiers changed from: private */
        public final Range<Cut<C>> upperBoundWindow;

        RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound2) {
            this.rangesByLowerBound = rangesByLowerBound2;
            this.upperBoundWindow = Range.all();
        }

        private RangesByUpperBound(NavigableMap<Cut<C>, Range<C>> rangesByLowerBound2, Range<Cut<C>> upperBoundWindow2) {
            this.rangesByLowerBound = rangesByLowerBound2;
            this.upperBoundWindow = upperBoundWindow2;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            if (window.isConnected(this.upperBoundWindow)) {
                return new RangesByUpperBound(this.rangesByLowerBound, window.intersection(this.upperBoundWindow));
            }
            return ImmutableSortedMap.of();
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        public boolean containsKey(@CheckForNull Object key) {
            return get(key) != null;
        }

        @CheckForNull
        public Range<C> get(@CheckForNull Object key) {
            Map.Entry<Cut<C>, Range<C>> candidate;
            if (key instanceof Cut) {
                try {
                    Cut cut = (Cut) key;
                    if (this.upperBoundWindow.contains(cut) && (candidate = this.rangesByLowerBound.lowerEntry(cut)) != null && candidate.getValue().upperBound.equals(cut)) {
                        return candidate.getValue();
                    }
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            final Iterator<Range<C>> backingItr;
            if (!this.upperBoundWindow.hasLowerBound()) {
                backingItr = this.rangesByLowerBound.values().iterator();
            } else {
                Map.Entry<Cut<C>, Range<C>> lowerEntry = this.rangesByLowerBound.lowerEntry(this.upperBoundWindow.lowerEndpoint());
                if (lowerEntry == null) {
                    backingItr = this.rangesByLowerBound.values().iterator();
                } else if (this.upperBoundWindow.lowerBound.isLessThan(lowerEntry.getValue().upperBound)) {
                    backingItr = this.rangesByLowerBound.tailMap(lowerEntry.getKey(), true).values().iterator();
                } else {
                    backingItr = this.rangesByLowerBound.tailMap(this.upperBoundWindow.lowerEndpoint(), true).values().iterator();
                }
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!backingItr.hasNext()) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> range = (Range) backingItr.next();
                    if (RangesByUpperBound.this.upperBoundWindow.upperBound.isLessThan(range.upperBound)) {
                        return (Map.Entry) endOfData();
                    }
                    return Maps.immutableEntry(range.upperBound, range);
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            Collection<Range<C>> candidates;
            if (this.upperBoundWindow.hasUpperBound()) {
                candidates = this.rangesByLowerBound.headMap(this.upperBoundWindow.upperEndpoint(), false).descendingMap().values();
            } else {
                candidates = this.rangesByLowerBound.descendingMap().values();
            }
            final PeekingIterator<Range<C>> backingItr = Iterators.peekingIterator(candidates.iterator());
            if (backingItr.hasNext() && this.upperBoundWindow.upperBound.isLessThan(backingItr.peek().upperBound)) {
                backingItr.next();
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!backingItr.hasNext()) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> range = (Range) backingItr.next();
                    if (RangesByUpperBound.this.upperBoundWindow.lowerBound.isLessThan(range.upperBound)) {
                        return Maps.immutableEntry(range.upperBound, range);
                    }
                    return (Map.Entry) endOfData();
                }
            };
        }

        public int size() {
            if (this.upperBoundWindow.equals(Range.all())) {
                return this.rangesByLowerBound.size();
            }
            return Iterators.size(entryIterator());
        }

        public boolean isEmpty() {
            if (this.upperBoundWindow.equals(Range.all())) {
                return this.rangesByLowerBound.isEmpty();
            }
            return !entryIterator().hasNext();
        }
    }

    private static final class ComplementRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        /* access modifiers changed from: private */
        public final Range<Cut<C>> complementLowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> positiveRangesByUpperBound;

        ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound2) {
            this(positiveRangesByLowerBound2, Range.all());
        }

        private ComplementRangesByLowerBound(NavigableMap<Cut<C>, Range<C>> positiveRangesByLowerBound2, Range<Cut<C>> window) {
            this.positiveRangesByLowerBound = positiveRangesByLowerBound2;
            this.positiveRangesByUpperBound = new RangesByUpperBound(positiveRangesByLowerBound2);
            this.complementLowerBoundWindow = window;
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> subWindow) {
            if (!this.complementLowerBoundWindow.isConnected(subWindow)) {
                return ImmutableSortedMap.of();
            }
            return new ComplementRangesByLowerBound(this.positiveRangesByLowerBound, subWindow.intersection(this.complementLowerBoundWindow));
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            Collection<Range<C>> positiveRanges;
            Cut<C> firstComplementRangeLowerBound;
            if (this.complementLowerBoundWindow.hasLowerBound()) {
                positiveRanges = this.positiveRangesByUpperBound.tailMap(this.complementLowerBoundWindow.lowerEndpoint(), this.complementLowerBoundWindow.lowerBoundType() == BoundType.CLOSED).values();
            } else {
                positiveRanges = this.positiveRangesByUpperBound.values();
            }
            PeekingIterator<Range<C>> positiveItr = Iterators.peekingIterator(positiveRanges.iterator());
            if (this.complementLowerBoundWindow.contains(Cut.belowAll()) && (!positiveItr.hasNext() || positiveItr.peek().lowerBound != Cut.belowAll())) {
                firstComplementRangeLowerBound = Cut.belowAll();
            } else if (!positiveItr.hasNext()) {
                return Iterators.emptyIterator();
            } else {
                firstComplementRangeLowerBound = positiveItr.next().upperBound;
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>(firstComplementRangeLowerBound, positiveItr) {
                Cut<C> nextComplementRangeLowerBound;
                final /* synthetic */ Cut val$firstComplementRangeLowerBound;
                final /* synthetic */ PeekingIterator val$positiveItr;

                {
                    this.val$firstComplementRangeLowerBound = r2;
                    this.val$positiveItr = r3;
                    this.nextComplementRangeLowerBound = r2;
                }

                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    Range<C> negativeRange;
                    if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.upperBound.isLessThan(this.nextComplementRangeLowerBound) || this.nextComplementRangeLowerBound == Cut.aboveAll()) {
                        return (Map.Entry) endOfData();
                    }
                    if (this.val$positiveItr.hasNext()) {
                        Range<C> positiveRange = (Range) this.val$positiveItr.next();
                        negativeRange = Range.create(this.nextComplementRangeLowerBound, positiveRange.lowerBound);
                        this.nextComplementRangeLowerBound = positiveRange.upperBound;
                    } else {
                        negativeRange = Range.create(this.nextComplementRangeLowerBound, Cut.aboveAll());
                        this.nextComplementRangeLowerBound = Cut.aboveAll();
                    }
                    return Maps.immutableEntry(negativeRange.lowerBound, negativeRange);
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            Cut cut;
            Cut<C> cut2;
            if (this.complementLowerBoundWindow.hasUpperBound()) {
                cut = this.complementLowerBoundWindow.upperEndpoint();
            } else {
                cut = Cut.aboveAll();
            }
            PeekingIterator<Range<C>> positiveItr = Iterators.peekingIterator(this.positiveRangesByUpperBound.headMap(cut, this.complementLowerBoundWindow.hasUpperBound() && this.complementLowerBoundWindow.upperBoundType() == BoundType.CLOSED).descendingMap().values().iterator());
            if (positiveItr.hasNext()) {
                if (positiveItr.peek().upperBound == Cut.aboveAll()) {
                    cut2 = positiveItr.next().lowerBound;
                } else {
                    cut2 = this.positiveRangesByLowerBound.higherKey(positiveItr.peek().upperBound);
                }
            } else if (!this.complementLowerBoundWindow.contains(Cut.belowAll()) || this.positiveRangesByLowerBound.containsKey(Cut.belowAll())) {
                return Iterators.emptyIterator();
            } else {
                cut2 = this.positiveRangesByLowerBound.higherKey(Cut.belowAll());
            }
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>((Cut) MoreObjects.firstNonNull(cut2, Cut.aboveAll()), positiveItr) {
                Cut<C> nextComplementRangeUpperBound;
                final /* synthetic */ Cut val$firstComplementRangeUpperBound;
                final /* synthetic */ PeekingIterator val$positiveItr;

                {
                    this.val$firstComplementRangeUpperBound = r2;
                    this.val$positiveItr = r3;
                    this.nextComplementRangeUpperBound = r2;
                }

                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (this.nextComplementRangeUpperBound == Cut.belowAll()) {
                        return (Map.Entry) endOfData();
                    }
                    if (this.val$positiveItr.hasNext()) {
                        Range<C> positiveRange = (Range) this.val$positiveItr.next();
                        Range<C> negativeRange = Range.create(positiveRange.upperBound, this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = positiveRange.lowerBound;
                        if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.lowerBound.isLessThan(negativeRange.lowerBound)) {
                            return Maps.immutableEntry(negativeRange.lowerBound, negativeRange);
                        }
                    } else if (ComplementRangesByLowerBound.this.complementLowerBoundWindow.lowerBound.isLessThan(Cut.belowAll())) {
                        Range<C> negativeRange2 = Range.create(Cut.belowAll(), this.nextComplementRangeUpperBound);
                        this.nextComplementRangeUpperBound = Cut.belowAll();
                        return Maps.immutableEntry(Cut.belowAll(), negativeRange2);
                    }
                    return (Map.Entry) endOfData();
                }
            };
        }

        public int size() {
            return Iterators.size(entryIterator());
        }

        @CheckForNull
        public Range<C> get(@CheckForNull Object key) {
            if (key instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) key;
                    Map.Entry<Cut<C>, Range<C>> firstEntry = tailMap(cut, true).firstEntry();
                    if (firstEntry != null && firstEntry.getKey().equals(cut)) {
                        return firstEntry.getValue();
                    }
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        public boolean containsKey(@CheckForNull Object key) {
            return get(key) != null;
        }
    }

    private final class Complement extends TreeRangeSet<C> {
        Complement() {
            super(new ComplementRangesByLowerBound(TreeRangeSet.this.rangesByLowerBound));
        }

        public void add(Range<C> rangeToAdd) {
            TreeRangeSet.this.remove(rangeToAdd);
        }

        public void remove(Range<C> rangeToRemove) {
            TreeRangeSet.this.add(rangeToRemove);
        }

        public boolean contains(C value) {
            return !TreeRangeSet.this.contains(value);
        }

        public RangeSet<C> complement() {
            return TreeRangeSet.this;
        }
    }

    private static final class SubRangeSetRangesByLowerBound<C extends Comparable<?>> extends AbstractNavigableMap<Cut<C>, Range<C>> {
        /* access modifiers changed from: private */
        public final Range<Cut<C>> lowerBoundWindow;
        private final NavigableMap<Cut<C>, Range<C>> rangesByLowerBound;
        private final NavigableMap<Cut<C>, Range<C>> rangesByUpperBound;
        /* access modifiers changed from: private */
        public final Range<C> restriction;

        private SubRangeSetRangesByLowerBound(Range<Cut<C>> lowerBoundWindow2, Range<C> restriction2, NavigableMap<Cut<C>, Range<C>> rangesByLowerBound2) {
            this.lowerBoundWindow = (Range) Preconditions.checkNotNull(lowerBoundWindow2);
            this.restriction = (Range) Preconditions.checkNotNull(restriction2);
            this.rangesByLowerBound = (NavigableMap) Preconditions.checkNotNull(rangesByLowerBound2);
            this.rangesByUpperBound = new RangesByUpperBound(rangesByLowerBound2);
        }

        private NavigableMap<Cut<C>, Range<C>> subMap(Range<Cut<C>> window) {
            if (!window.isConnected(this.lowerBoundWindow)) {
                return ImmutableSortedMap.of();
            }
            return new SubRangeSetRangesByLowerBound(this.lowerBoundWindow.intersection(window), this.restriction, this.rangesByLowerBound);
        }

        public NavigableMap<Cut<C>, Range<C>> subMap(Cut<C> fromKey, boolean fromInclusive, Cut<C> toKey, boolean toInclusive) {
            return subMap(Range.range(fromKey, BoundType.forBoolean(fromInclusive), toKey, BoundType.forBoolean(toInclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> headMap(Cut<C> toKey, boolean inclusive) {
            return subMap(Range.upTo(toKey, BoundType.forBoolean(inclusive)));
        }

        public NavigableMap<Cut<C>, Range<C>> tailMap(Cut<C> fromKey, boolean inclusive) {
            return subMap(Range.downTo(fromKey, BoundType.forBoolean(inclusive)));
        }

        public Comparator<? super Cut<C>> comparator() {
            return Ordering.natural();
        }

        public boolean containsKey(@CheckForNull Object key) {
            return get(key) != null;
        }

        @CheckForNull
        public Range<C> get(@CheckForNull Object key) {
            if (key instanceof Cut) {
                try {
                    Cut<C> cut = (Cut) key;
                    if (this.lowerBoundWindow.contains(cut) && cut.compareTo(this.restriction.lowerBound) >= 0) {
                        if (cut.compareTo(this.restriction.upperBound) < 0) {
                            if (cut.equals(this.restriction.lowerBound)) {
                                Range<C> candidate = (Range) Maps.valueOrNull(this.rangesByLowerBound.floorEntry(cut));
                                if (candidate != null && candidate.upperBound.compareTo(this.restriction.lowerBound) > 0) {
                                    return candidate.intersection(this.restriction);
                                }
                            } else {
                                Range<C> result = (Range) this.rangesByLowerBound.get(cut);
                                if (result != null) {
                                    return result.intersection(this.restriction);
                                }
                            }
                        }
                    }
                    return null;
                } catch (ClassCastException e) {
                    return null;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> entryIterator() {
            final Iterator<Range<C>> completeRangeItr;
            if (this.restriction.isEmpty()) {
                return Iterators.emptyIterator();
            }
            if (this.lowerBoundWindow.upperBound.isLessThan(this.restriction.lowerBound)) {
                return Iterators.emptyIterator();
            }
            boolean z = false;
            if (this.lowerBoundWindow.lowerBound.isLessThan(this.restriction.lowerBound)) {
                completeRangeItr = this.rangesByUpperBound.tailMap(this.restriction.lowerBound, false).values().iterator();
            } else {
                NavigableMap<Cut<C>, Range<C>> navigableMap = this.rangesByLowerBound;
                Cut cut = (Cut) this.lowerBoundWindow.lowerBound.endpoint();
                if (this.lowerBoundWindow.lowerBoundType() == BoundType.CLOSED) {
                    z = true;
                }
                completeRangeItr = navigableMap.tailMap(cut, z).values().iterator();
            }
            final Cut<Cut<C>> upperBoundOnLowerBounds = (Cut) Ordering.natural().min(this.lowerBoundWindow.upperBound, Cut.belowValue(this.restriction.upperBound));
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!completeRangeItr.hasNext()) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> nextRange = (Range) completeRangeItr.next();
                    if (upperBoundOnLowerBounds.isLessThan(nextRange.lowerBound)) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> nextRange2 = nextRange.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                    return Maps.immutableEntry(nextRange2.lowerBound, nextRange2);
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Cut<C>, Range<C>>> descendingEntryIterator() {
            if (this.restriction.isEmpty()) {
                return Iterators.emptyIterator();
            }
            Cut<Cut<C>> upperBoundOnLowerBounds = (Cut) Ordering.natural().min(this.lowerBoundWindow.upperBound, Cut.belowValue(this.restriction.upperBound));
            final Iterator<Range<C>> completeRangeItr = this.rangesByLowerBound.headMap(upperBoundOnLowerBounds.endpoint(), upperBoundOnLowerBounds.typeAsUpperBound() == BoundType.CLOSED).descendingMap().values().iterator();
            return new AbstractIterator<Map.Entry<Cut<C>, Range<C>>>() {
                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<Cut<C>, Range<C>> computeNext() {
                    if (!completeRangeItr.hasNext()) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> nextRange = (Range) completeRangeItr.next();
                    if (SubRangeSetRangesByLowerBound.this.restriction.lowerBound.compareTo(nextRange.upperBound) >= 0) {
                        return (Map.Entry) endOfData();
                    }
                    Range<C> nextRange2 = nextRange.intersection(SubRangeSetRangesByLowerBound.this.restriction);
                    if (SubRangeSetRangesByLowerBound.this.lowerBoundWindow.contains(nextRange2.lowerBound)) {
                        return Maps.immutableEntry(nextRange2.lowerBound, nextRange2);
                    }
                    return (Map.Entry) endOfData();
                }
            };
        }

        public int size() {
            return Iterators.size(entryIterator());
        }
    }

    public RangeSet<C> subRangeSet(Range<C> view) {
        return view.equals(Range.all()) ? this : new SubRangeSet(view);
    }

    private final class SubRangeSet extends TreeRangeSet<C> {
        private final Range<C> restriction;

        SubRangeSet(Range<C> restriction2) {
            super(new SubRangeSetRangesByLowerBound(Range.all(), restriction2, TreeRangeSet.this.rangesByLowerBound));
            this.restriction = restriction2;
        }

        public boolean encloses(Range<C> range) {
            Range<C> enclosing;
            if (this.restriction.isEmpty() || !this.restriction.encloses(range) || (enclosing = TreeRangeSet.this.rangeEnclosing(range)) == null || enclosing.intersection(this.restriction).isEmpty()) {
                return false;
            }
            return true;
        }

        @CheckForNull
        public Range<C> rangeContaining(C value) {
            Range<C> result;
            if (this.restriction.contains(value) && (result = TreeRangeSet.this.rangeContaining(value)) != null) {
                return result.intersection(this.restriction);
            }
            return null;
        }

        public void add(Range<C> rangeToAdd) {
            Preconditions.checkArgument(this.restriction.encloses(rangeToAdd), "Cannot add range %s to subRangeSet(%s)", (Object) rangeToAdd, (Object) this.restriction);
            TreeRangeSet.this.add(rangeToAdd);
        }

        public void remove(Range<C> rangeToRemove) {
            if (rangeToRemove.isConnected(this.restriction)) {
                TreeRangeSet.this.remove(rangeToRemove.intersection(this.restriction));
            }
        }

        public boolean contains(C value) {
            return this.restriction.contains(value) && TreeRangeSet.this.contains(value);
        }

        public void clear() {
            TreeRangeSet.this.remove(this.restriction);
        }

        public RangeSet<C> subRangeSet(Range<C> view) {
            if (view.encloses(this.restriction)) {
                return this;
            }
            if (view.isConnected(this.restriction)) {
                return new SubRangeSet(this.restriction.intersection(view));
            }
            return ImmutableRangeSet.of();
        }
    }
}
