package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final long CLEANUP_EXECUTOR_DELAY_SECS = 60;
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final WeakValueReference<Object, Object, DummyInternalEntry> UNSET_WEAK_VALUE_REFERENCE = new WeakValueReference<Object, Object, DummyInternalEntry>() {
        public DummyInternalEntry getEntry() {
            return null;
        }

        public void clear() {
        }

        public Object get() {
            return null;
        }

        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry entry) {
            return this;
        }
    };
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient InternalEntryHelper<K, V, E, S> entryHelper;
    @CheckForNull
    transient Set<Map.Entry<K, V>> entrySet;
    final Equivalence<Object> keyEquivalence;
    @CheckForNull
    transient Set<K> keySet;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V, E, S>[] segments;
    @CheckForNull
    transient Collection<V> values;

    interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s, E e, @CheckForNull E e2);

        Strength keyStrength();

        E newEntry(S s, K k, int i, @CheckForNull E e);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2);

        void setValue(S s, E e, V v);

        Strength valueStrength();
    }

    enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> defaultEquivalence();
    }

    interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        void clearValue();

        WeakValueReference<K, V, E> getValueReference();
    }

    interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e);

        @CheckForNull
        V get();

        E getEntry();
    }

    private MapMakerInternalMap(MapMaker builder, InternalEntryHelper<K, V, E, S> entryHelper2) {
        this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
        this.keyEquivalence = builder.getKeyEquivalence();
        this.entryHelper = entryHelper2;
        int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
        int segmentShift2 = 0;
        int segmentCount = 1;
        while (segmentCount < this.concurrencyLevel) {
            segmentShift2++;
            segmentCount <<= 1;
        }
        this.segmentShift = 32 - segmentShift2;
        this.segmentMask = segmentCount - 1;
        this.segments = newSegmentArray(segmentCount);
        int segmentCapacity = initialCapacity / segmentCount;
        int segmentSize = 1;
        while (segmentSize < (segmentCapacity * segmentCount < initialCapacity ? segmentCapacity + 1 : segmentCapacity)) {
            segmentSize <<= 1;
        }
        int i = 0;
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.segments;
            if (i < segmentArr.length) {
                segmentArr[i] = createSegment(segmentSize, -1);
                i++;
            } else {
                return;
            }
        }
    }

    static <K, V> MapMakerInternalMap<K, V, ? extends InternalEntry<K, V, ?>, ?> create(MapMaker builder) {
        if (builder.getKeyStrength() == Strength.STRONG && builder.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(builder, StrongKeyStrongValueEntry.Helper.instance());
        }
        if (builder.getKeyStrength() == Strength.STRONG && builder.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(builder, StrongKeyWeakValueEntry.Helper.instance());
        }
        if (builder.getKeyStrength() == Strength.WEAK && builder.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(builder, WeakKeyStrongValueEntry.Helper.instance());
        }
        if (builder.getKeyStrength() == Strength.WEAK && builder.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(builder, WeakKeyWeakValueEntry.Helper.instance());
        }
        throw new AssertionError();
    }

    static <K> MapMakerInternalMap<K, MapMaker.Dummy, ? extends InternalEntry<K, MapMaker.Dummy, ?>, ?> createWithDummyValues(MapMaker builder) {
        if (builder.getKeyStrength() == Strength.STRONG && builder.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(builder, StrongKeyDummyValueEntry.Helper.instance());
        }
        if (builder.getKeyStrength() == Strength.WEAK && builder.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(builder, WeakKeyDummyValueEntry.Helper.instance());
        }
        if (builder.getValueStrength() == Strength.WEAK) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {
        final int hash;
        final K key;
        @CheckForNull
        final E next;

        AbstractStrongKeyEntry(K key2, int hash2, @CheckForNull E next2) {
            this.key = key2;
            this.hash = hash2;
            this.next = next2;
        }

        public K getKey() {
            return this.key;
        }

        public int getHash() {
            return this.hash;
        }

        public E getNext() {
            return this.next;
        }
    }

    static <K, V, E extends InternalEntry<K, V, E>> WeakValueReference<K, V, E> unsetWeakValueReference() {
        return UNSET_WEAK_VALUE_REFERENCE;
    }

    static final class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        @CheckForNull
        private volatile V value = null;

        StrongKeyStrongValueEntry(K key, int hash, @CheckForNull StrongKeyStrongValueEntry<K, V> next) {
            super(key, hash, next);
        }

        @CheckForNull
        public V getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(V value2) {
            this.value = value2;
        }

        /* access modifiers changed from: package-private */
        public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueEntry<K, V> newNext) {
            StrongKeyStrongValueEntry<K, V> newEntry = new StrongKeyStrongValueEntry<>(this.key, this.hash, newNext);
            newEntry.value = this.value;
            return newEntry;
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
                return new StrongKeyStrongValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> entry, @CheckForNull StrongKeyStrongValueEntry<K, V> newNext) {
                return entry.copy(newNext);
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> entry, V value) {
                entry.setValue(value);
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K key, int hash, @CheckForNull StrongKeyStrongValueEntry<K, V> next) {
                return new StrongKeyStrongValueEntry<>(key, hash, next);
            }
        }
    }

    static final class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.unsetWeakValueReference();

        StrongKeyWeakValueEntry(K key, int hash, @CheckForNull StrongKeyWeakValueEntry<K, V> next) {
            super(key, hash, next);
        }

        public V getValue() {
            return this.valueReference.get();
        }

        public void clearValue() {
            this.valueReference.clear();
        }

        /* access modifiers changed from: package-private */
        public void setValue(V value, ReferenceQueue<V> queueForValues) {
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> previous = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(queueForValues, value, this);
            previous.clear();
        }

        /* access modifiers changed from: package-private */
        public StrongKeyWeakValueEntry<K, V> copy(ReferenceQueue<V> queueForValues, StrongKeyWeakValueEntry<K, V> newNext) {
            StrongKeyWeakValueEntry<K, V> newEntry = new StrongKeyWeakValueEntry<>(this.key, this.hash, newNext);
            newEntry.valueReference = this.valueReference.copyFor(queueForValues, newEntry);
            return newEntry;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
                return new StrongKeyWeakValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> segment, StrongKeyWeakValueEntry<K, V> entry, @CheckForNull StrongKeyWeakValueEntry<K, V> newNext) {
                if (Segment.isCollected(entry)) {
                    return null;
                }
                return entry.copy(segment.queueForValues, newNext);
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> segment, StrongKeyWeakValueEntry<K, V> entry, V value) {
                entry.setValue(value, segment.queueForValues);
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K key, int hash, @CheckForNull StrongKeyWeakValueEntry<K, V> next) {
                return new StrongKeyWeakValueEntry<>(key, hash, next);
            }
        }
    }

    static final class StrongKeyDummyValueEntry<K> extends AbstractStrongKeyEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> {
        StrongKeyDummyValueEntry(K key, int hash, @CheckForNull StrongKeyDummyValueEntry<K> next) {
            super(key, hash, next);
        }

        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }

        /* access modifiers changed from: package-private */
        public void setValue(MapMaker.Dummy value) {
        }

        /* access modifiers changed from: package-private */
        public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueEntry<K> newNext) {
            return new StrongKeyDummyValueEntry<>(this.key, this.hash, newNext);
        }

        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> map, int initialCapacity, int maxSegmentSize) {
                return new StrongKeyDummyValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> entry, @CheckForNull StrongKeyDummyValueEntry<K> newNext) {
                return entry.copy(newNext);
            }

            public void setValue(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, MapMaker.Dummy value) {
            }

            public StrongKeyDummyValueEntry<K> newEntry(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K key, int hash, @CheckForNull StrongKeyDummyValueEntry<K> next) {
                return new StrongKeyDummyValueEntry<>(key, hash, next);
            }
        }
    }

    static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {
        final int hash;
        @CheckForNull
        final E next;

        AbstractWeakKeyEntry(ReferenceQueue<K> queue, K key, int hash2, @CheckForNull E next2) {
            super(key, queue);
            this.hash = hash2;
            this.next = next2;
        }

        public K getKey() {
            return get();
        }

        public int getHash() {
            return this.hash;
        }

        public E getNext() {
            return this.next;
        }
    }

    static final class WeakKeyDummyValueEntry<K> extends AbstractWeakKeyEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> {
        WeakKeyDummyValueEntry(ReferenceQueue<K> queue, K key, int hash, @CheckForNull WeakKeyDummyValueEntry<K> next) {
            super(queue, key, hash, next);
        }

        public MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }

        /* access modifiers changed from: package-private */
        public void setValue(MapMaker.Dummy value) {
        }

        /* access modifiers changed from: package-private */
        public WeakKeyDummyValueEntry<K> copy(ReferenceQueue<K> queueForKeys, WeakKeyDummyValueEntry<K> newNext) {
            return new WeakKeyDummyValueEntry<>(queueForKeys, getKey(), this.hash, newNext);
        }

        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public WeakKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> map, int initialCapacity, int maxSegmentSize) {
                return new WeakKeyDummyValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public WeakKeyDummyValueEntry<K> copy(WeakKeyDummyValueSegment<K> segment, WeakKeyDummyValueEntry<K> entry, @CheckForNull WeakKeyDummyValueEntry<K> newNext) {
                if (entry.getKey() == null) {
                    return null;
                }
                return entry.copy(segment.queueForKeys, newNext);
            }

            public void setValue(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, MapMaker.Dummy value) {
            }

            public WeakKeyDummyValueEntry<K> newEntry(WeakKeyDummyValueSegment<K> segment, K key, int hash, @CheckForNull WeakKeyDummyValueEntry<K> next) {
                return new WeakKeyDummyValueEntry<>(segment.queueForKeys, key, hash, next);
            }
        }
    }

    static final class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        @CheckForNull
        private volatile V value = null;

        WeakKeyStrongValueEntry(ReferenceQueue<K> queue, K key, int hash, @CheckForNull WeakKeyStrongValueEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @CheckForNull
        public V getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(V value2) {
            this.value = value2;
        }

        /* access modifiers changed from: package-private */
        public WeakKeyStrongValueEntry<K, V> copy(ReferenceQueue<K> queueForKeys, WeakKeyStrongValueEntry<K, V> newNext) {
            WeakKeyStrongValueEntry<K, V> newEntry = new WeakKeyStrongValueEntry<>(queueForKeys, getKey(), this.hash, newNext);
            newEntry.setValue(this.value);
            return newEntry;
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
                return new WeakKeyStrongValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> segment, WeakKeyStrongValueEntry<K, V> entry, @CheckForNull WeakKeyStrongValueEntry<K, V> newNext) {
                if (entry.getKey() == null) {
                    return null;
                }
                return entry.copy(segment.queueForKeys, newNext);
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> entry, V value) {
                entry.setValue(value);
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> segment, K key, int hash, @CheckForNull WeakKeyStrongValueEntry<K, V> next) {
                return new WeakKeyStrongValueEntry<>(segment.queueForKeys, key, hash, next);
            }
        }
    }

    static final class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.unsetWeakValueReference();

        WeakKeyWeakValueEntry(ReferenceQueue<K> queue, K key, int hash, @CheckForNull WeakKeyWeakValueEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        public V getValue() {
            return this.valueReference.get();
        }

        /* access modifiers changed from: package-private */
        public WeakKeyWeakValueEntry<K, V> copy(ReferenceQueue<K> queueForKeys, ReferenceQueue<V> queueForValues, WeakKeyWeakValueEntry<K, V> newNext) {
            WeakKeyWeakValueEntry<K, V> newEntry = new WeakKeyWeakValueEntry<>(queueForKeys, getKey(), this.hash, newNext);
            newEntry.valueReference = this.valueReference.copyFor(queueForValues, newEntry);
            return newEntry;
        }

        public void clearValue() {
            this.valueReference.clear();
        }

        /* access modifiers changed from: package-private */
        public void setValue(V value, ReferenceQueue<V> queueForValues) {
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> previous = this.valueReference;
            this.valueReference = new WeakValueReferenceImpl(queueForValues, value, this);
            previous.clear();
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
                return new WeakKeyWeakValueSegment<>(map, initialCapacity, maxSegmentSize);
            }

            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> segment, WeakKeyWeakValueEntry<K, V> entry, @CheckForNull WeakKeyWeakValueEntry<K, V> newNext) {
                if (entry.getKey() != null && !Segment.isCollected(entry)) {
                    return entry.copy(segment.queueForKeys, segment.queueForValues, newNext);
                }
                return null;
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> segment, WeakKeyWeakValueEntry<K, V> entry, V value) {
                entry.setValue(value, segment.queueForValues);
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> segment, K key, int hash, @CheckForNull WeakKeyWeakValueEntry<K, V> next) {
                return new WeakKeyWeakValueEntry<>(segment.queueForKeys, key, hash, next);
            }
        }
    }

    static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }

        public int getHash() {
            throw new AssertionError();
        }

        public Object getKey() {
            throw new AssertionError();
        }

        public Object getValue() {
            throw new AssertionError();
        }
    }

    static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {
        final E entry;

        WeakValueReferenceImpl(ReferenceQueue<V> queue, V referent, E entry2) {
            super(referent, queue);
            this.entry = entry2;
        }

        public E getEntry() {
            return this.entry;
        }

        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> queue, E entry2) {
            return new WeakValueReferenceImpl(queue, get(), entry2);
        }
    }

    static int rehash(int h) {
        int h2 = h + ((h << 15) ^ -12931);
        int h3 = h2 ^ (h2 >>> 10);
        int h4 = h3 + (h3 << 3);
        int h5 = h4 ^ (h4 >>> 6);
        int h6 = h5 + (h5 << 2) + (h5 << 14);
        return (h6 >>> 16) ^ h6;
    }

    /* access modifiers changed from: package-private */
    public E copyEntry(E original, E newNext) {
        return segmentFor(original.getHash()).copyEntry(original, newNext);
    }

    /* access modifiers changed from: package-private */
    public int hash(Object key) {
        return rehash(this.keyEquivalence.hash(key));
    }

    /* access modifiers changed from: package-private */
    public void reclaimValue(WeakValueReference<K, V, E> valueReference) {
        E entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    /* access modifiers changed from: package-private */
    public void reclaimKey(E entry) {
        int hash = entry.getHash();
        segmentFor(hash).reclaimKey(entry, hash);
    }

    /* access modifiers changed from: package-private */
    public boolean isLiveForTesting(InternalEntry<K, V, ?> entry) {
        return segmentFor(entry.getHash()).getLiveValueForTesting(entry) != null;
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> segmentFor(int hash) {
        return this.segments[(hash >>> this.segmentShift) & this.segmentMask];
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> createSegment(int initialCapacity, int maxSegmentSize) {
        return this.entryHelper.newSegment(this, initialCapacity, maxSegmentSize);
    }

    /* access modifiers changed from: package-private */
    public V getLiveValue(E entry) {
        if (entry.getKey() == null) {
            return null;
        }
        return entry.getValue();
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V, E, S>[] newSegmentArray(int ssize) {
        return new Segment[ssize];
    }

    static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        volatile int count;
        final MapMakerInternalMap<K, V, E, S> map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        @CheckForNull
        volatile AtomicReferenceArray<E> table;
        int threshold;

        /* access modifiers changed from: package-private */
        public abstract E castForTesting(InternalEntry<K, V, ?> internalEntry);

        /* access modifiers changed from: package-private */
        public abstract S self();

        Segment(MapMakerInternalMap<K, V, E, S> map2, int initialCapacity, int maxSegmentSize2) {
            this.map = map2;
            this.maxSegmentSize = maxSegmentSize2;
            initTable(newEntryArray(initialCapacity));
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public void setValue(E entry, V value) {
            this.map.entryHelper.setValue(self(), entry, value);
        }

        /* access modifiers changed from: package-private */
        public E copyEntry(E original, E newNext) {
            return this.map.entryHelper.copy(self(), original, newNext);
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<E> newEntryArray(int size) {
            return new AtomicReferenceArray<>(size);
        }

        /* access modifiers changed from: package-private */
        public void initTable(AtomicReferenceArray<E> newTable) {
            int length = (newTable.length() * 3) / 4;
            this.threshold = length;
            if (length == this.maxSegmentSize) {
                this.threshold = length + 1;
            }
            this.table = newTable;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setTableEntryForTesting(int i, InternalEntry<K, V, ?> entry) {
            this.table.set(i, castForTesting(entry));
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E copyForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r5, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r6) {
            /*
                r4 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r4.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r4.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r4.castForTesting(r5)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r4.castForTesting(r6)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r0.copy(r1, r2, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.copyForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r4v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r4, V r5) {
            /*
                r3 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r3.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r3.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r3.castForTesting(r4)
                r0.setValue(r1, r2, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.setValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, java.lang.Object):void");
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E newEntryForTesting(K r4, int r5, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r6) {
            /*
                r3 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r3.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r3.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r3.castForTesting(r6)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r0.newEntry(r1, r4, r5, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.newEntryForTesting(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r2) {
            /*
                r1 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r1.castForTesting(r2)
                boolean r0 = r1.removeEntryForTesting(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):boolean");
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r3, com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r4) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r2.castForTesting(r3)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r2.castForTesting(r4)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r2.removeFromChain(r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r2) {
            /*
                r1 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r0 = r1.castForTesting(r2)
                java.lang.Object r0 = r1.getLiveValue(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainKeyReferenceQueue(ReferenceQueue<K> keyReferenceQueue) {
            int i = 0;
            do {
                E poll = keyReferenceQueue.poll();
                E ref = poll;
                if (poll != null) {
                    this.map.reclaimKey((InternalEntry) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void drainValueReferenceQueue(ReferenceQueue<V> valueReferenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = valueReferenceQueue.poll();
                Reference<? extends V> ref = poll;
                if (poll != null) {
                    this.map.reclaimValue((WeakValueReference) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public E getFirst(int hash) {
            AtomicReferenceArray<E> table2 = this.table;
            return (InternalEntry) table2.get((table2.length() - 1) & hash);
        }

        /* access modifiers changed from: package-private */
        public E getEntry(Object key, int hash) {
            if (this.count == 0) {
                return null;
            }
            for (E e = getFirst(hash); e != null; e = e.getNext()) {
                if (e.getHash() == hash) {
                    K entryKey = e.getKey();
                    if (entryKey == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(key, entryKey)) {
                        return e;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public E getLiveEntry(Object key, int hash) {
            return getEntry(key, hash);
        }

        /* access modifiers changed from: package-private */
        public V get(Object key, int hash) {
            try {
                E e = getLiveEntry(key, hash);
                if (e == null) {
                    return null;
                }
                V value = e.getValue();
                if (value == null) {
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return value;
            } finally {
                postReadCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean containsKey(Object key, int hash) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    E e = getLiveEntry(key, hash);
                    if (!(e == null || e.getValue() == null)) {
                        z = true;
                    }
                    return z;
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public boolean containsValue(Object value) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> table2 = this.table;
                    int length = table2.length();
                    for (int i = 0; i < length; i++) {
                        for (E e = (InternalEntry) table2.get(i); e != null; e = e.getNext()) {
                            V entryValue = getLiveValue(e);
                            if (entryValue != null) {
                                if (this.map.valueEquivalence().equivalent(value, entryValue)) {
                                    postReadCleanup();
                                    return true;
                                }
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public V put(K key, int hash, V value, boolean onlyIfAbsent) {
            lock();
            try {
                preWriteCleanup();
                int newCount = this.count + 1;
                if (newCount > this.threshold) {
                    expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else {
                        V entryValue = e.getValue();
                        if (entryValue == null) {
                            this.modCount++;
                            setValue(e, value);
                            this.count = this.count;
                            return null;
                        } else if (onlyIfAbsent) {
                            unlock();
                            return entryValue;
                        } else {
                            this.modCount++;
                            setValue(e, value);
                            unlock();
                            return entryValue;
                        }
                    }
                }
                this.modCount++;
                E newEntry = this.map.entryHelper.newEntry(self(), key, hash, first);
                setValue(newEntry, value);
                table2.set(index, newEntry);
                this.count = newCount;
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void expand() {
            AtomicReferenceArray<E> oldTable = this.table;
            int oldCapacity = oldTable.length();
            if (oldCapacity < 1073741824) {
                int newCount = this.count;
                AtomicReferenceArray<E> newTable = newEntryArray(oldCapacity << 1);
                this.threshold = (newTable.length() * 3) / 4;
                int newMask = newTable.length() - 1;
                for (int oldIndex = 0; oldIndex < oldCapacity; oldIndex++) {
                    E head = (InternalEntry) oldTable.get(oldIndex);
                    if (head != null) {
                        E next = head.getNext();
                        int headIndex = head.getHash() & newMask;
                        if (next == null) {
                            newTable.set(headIndex, head);
                        } else {
                            E tail = head;
                            int tailIndex = headIndex;
                            for (E e = next; e != null; e = e.getNext()) {
                                int newIndex = e.getHash() & newMask;
                                if (newIndex != tailIndex) {
                                    tailIndex = newIndex;
                                    tail = e;
                                }
                            }
                            newTable.set(tailIndex, tail);
                            for (E e2 = head; e2 != tail; e2 = e2.getNext()) {
                                int newIndex2 = e2.getHash() & newMask;
                                E newFirst = copyEntry(e2, (InternalEntry) newTable.get(newIndex2));
                                if (newFirst != null) {
                                    newTable.set(newIndex2, newFirst);
                                } else {
                                    newCount--;
                                }
                            }
                        }
                    }
                }
                this.table = newTable;
                this.count = newCount;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean replace(K key, int hash, V oldValue, V newValue) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else {
                        V entryValue = e.getValue();
                        if (entryValue == null) {
                            if (isCollected(e)) {
                                int i = this.count - 1;
                                this.modCount++;
                                table2.set(index, removeFromChain(first, e));
                                this.count--;
                            }
                            return false;
                        } else if (this.map.valueEquivalence().equivalent(oldValue, entryValue)) {
                            this.modCount++;
                            setValue(e, newValue);
                            unlock();
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public V replace(K key, int hash, V newValue) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else {
                        V entryValue = e.getValue();
                        if (entryValue == null) {
                            if (isCollected(e)) {
                                int i = this.count - 1;
                                this.modCount++;
                                table2.set(index, removeFromChain(first, e));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(e, newValue);
                        unlock();
                        return entryValue;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public V remove(Object key, int hash) {
            lock();
            try {
                preWriteCleanup();
                int i = this.count - 1;
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else {
                        V entryValue = e.getValue();
                        if (entryValue == null) {
                            if (!isCollected(e)) {
                                unlock();
                                return null;
                            }
                        }
                        this.modCount++;
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return entryValue;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(Object key, int hash, Object value) {
            lock();
            try {
                preWriteCleanup();
                int i = this.count - 1;
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else {
                        boolean explicitRemoval = false;
                        if (this.map.valueEquivalence().equivalent(value, e.getValue())) {
                            explicitRemoval = true;
                        } else if (!isCollected(e)) {
                            unlock();
                            return false;
                        }
                        this.modCount++;
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return explicitRemoval;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> table2 = this.table;
                    for (int i = 0; i < table2.length(); i++) {
                        table2.set(i, (Object) null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public E removeFromChain(E first, E entry) {
            int newCount = this.count;
            E newFirst = entry.getNext();
            for (E e = first; e != entry; e = e.getNext()) {
                E next = copyEntry(e, newFirst);
                if (next != null) {
                    newFirst = next;
                } else {
                    newCount--;
                }
            }
            this.count = newCount;
            return newFirst;
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimKey(E entry, int hash) {
            lock();
            try {
                int i = this.count - 1;
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                for (E e = first; e != null; e = e.getNext()) {
                    if (e == entry) {
                        this.modCount++;
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimValue(K key, int hash, WeakValueReference<K, V, E> valueReference) {
            lock();
            try {
                int i = this.count - 1;
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else if (((WeakValueEntry) e).getValueReference() == valueReference) {
                        this.modCount++;
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean clearValueForTesting(K key, int hash, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> valueReference) {
            lock();
            try {
                AtomicReferenceArray<E> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                E first = (InternalEntry) table2.get(index);
                E e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else if (((WeakValueEntry) e).getValueReference() == valueReference) {
                        table2.set(index, removeFromChain(first, e));
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean removeEntryForTesting(E entry) {
            int hash = entry.getHash();
            int i = this.count - 1;
            AtomicReferenceArray<E> table2 = this.table;
            int index = (table2.length() - 1) & hash;
            E first = (InternalEntry) table2.get(index);
            for (E e = first; e != null; e = e.getNext()) {
                if (e == entry) {
                    this.modCount++;
                    table2.set(index, removeFromChain(first, e));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        static <K, V, E extends InternalEntry<K, V, E>> boolean isCollected(E entry) {
            return entry.getValue() == null;
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V getLiveValue(E entry) {
            if (entry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V value = entry.getValue();
            if (value != null) {
                return value;
            }
            tryDrainReferenceQueues();
            return null;
        }

        /* access modifiers changed from: package-private */
        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }
    }

    static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public StrongKeyStrongValueSegment<K, V> self() {
            return this;
        }

        public StrongKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> entry) {
            return (StrongKeyStrongValueEntry) entry;
        }
    }

    static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public StrongKeyWeakValueSegment<K, V> self() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        public StrongKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> entry) {
            return (StrongKeyWeakValueEntry) entry;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> e) {
            return castForTesting((InternalEntry) e).getValueReference();
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> e, V value) {
            return new WeakValueReferenceImpl(this.queueForValues, value, castForTesting((InternalEntry) e));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> e, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> valueReference) {
            StrongKeyWeakValueEntry<K, V> entry = castForTesting((InternalEntry) e);
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> previous = entry.valueReference;
            WeakValueReference unused = entry.valueReference = valueReference;
            previous.clear();
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForValues);
        }
    }

    static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
        StrongKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public StrongKeyDummyValueSegment<K> self() {
            return this;
        }

        public StrongKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> entry) {
            return (StrongKeyDummyValueEntry) entry;
        }
    }

    static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public WeakKeyStrongValueSegment<K, V> self() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> entry) {
            return (WeakKeyStrongValueEntry) entry;
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public WeakKeyWeakValueSegment<K, V> self() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        public WeakKeyWeakValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> entry) {
            return (WeakKeyWeakValueEntry) entry;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> e) {
            return castForTesting((InternalEntry) e).getValueReference();
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> e, V value) {
            return new WeakValueReferenceImpl(this.queueForValues, value, castForTesting((InternalEntry) e));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> e, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> valueReference) {
            WeakKeyWeakValueEntry<K, V> entry = castForTesting((InternalEntry) e);
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> previous = entry.valueReference;
            WeakValueReference unused = entry.valueReference = valueReference;
            previous.clear();
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        WeakKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public WeakKeyDummyValueSegment<K> self() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public WeakKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> entry) {
            return (WeakKeyDummyValueEntry) entry;
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?, ?, ?>> mapReference;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> map) {
            this.mapReference = new WeakReference<>(map);
        }

        public void run() {
            MapMakerInternalMap<?, ?, ?, ?> map = (MapMakerInternalMap) this.mapReference.get();
            if (map != null) {
                for (Segment<K, V, E, S> runCleanup : map.segments) {
                    runCleanup.runCleanup();
                }
                return;
            }
            throw new CancellationException();
        }
    }

    /* access modifiers changed from: package-private */
    public Strength keyStrength() {
        return this.entryHelper.keyStrength();
    }

    /* access modifiers changed from: package-private */
    public Strength valueStrength() {
        return this.entryHelper.valueStrength();
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.valueStrength().defaultEquivalence();
    }

    public boolean isEmpty() {
        long sum = 0;
        Segment<K, V, E, S>[] segments2 = this.segments;
        for (int i = 0; i < segments2.length; i++) {
            if (segments2[i].count != 0) {
                return false;
            }
            sum += (long) segments2[i].modCount;
        }
        if (sum == 0) {
            return true;
        }
        for (int i2 = 0; i2 < segments2.length; i2++) {
            if (segments2[i2].count != 0) {
                return false;
            }
            sum -= (long) segments2[i2].modCount;
        }
        if (sum == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        Segment<K, V, E, S>[] segments2 = this.segments;
        long sum = 0;
        for (Segment<K, V, E, S> segment : segments2) {
            sum += (long) segment.count;
        }
        return Ints.saturatedCast(sum);
    }

    public V get(@CheckForNull Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).get(key, hash);
    }

    /* access modifiers changed from: package-private */
    public E getEntry(@CheckForNull Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).getEntry(key, hash);
    }

    public boolean containsKey(@CheckForNull Object key) {
        if (key == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).containsKey(key, hash);
    }

    public boolean containsValue(@CheckForNull Object value) {
        Object obj = value;
        int i = 0;
        if (obj == null) {
            return false;
        }
        Segment<K, V, E, S>[] segments2 = this.segments;
        long last = -1;
        int i2 = 0;
        while (i2 < 3) {
            long sum = 0;
            int length = segments2.length;
            int i3 = i;
            while (i3 < length) {
                Segment<K, V, E, S> segment = segments2[i3];
                int i4 = segment.count;
                AtomicReferenceArray<E> table = segment.table;
                int j = 0;
                while (j < table.length()) {
                    E e = (InternalEntry) table.get(j);
                    while (e != null) {
                        V v = segment.getLiveValue(e);
                        if (v != null && valueEquivalence().equivalent(obj, v)) {
                            return true;
                        }
                        e = e.getNext();
                    }
                    j++;
                }
                sum += (long) segment.modCount;
                i3++;
            }
            if (sum == last) {
                return false;
            }
            last = sum;
            i2++;
            i = 0;
        }
        return false;
    }

    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, false);
    }

    public V putIfAbsent(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, true);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    public V remove(@CheckForNull Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash);
    }

    public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
        if (key == null || value == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash, value);
    }

    public boolean replace(K key, @CheckForNull V oldValue, V newValue) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        if (oldValue == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    public V replace(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, value);
    }

    public void clear() {
        for (Segment<K, V, E, S> segment : this.segments) {
            segment.clear();
        }
    }

    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Values values2 = new Values();
        this.values = values2;
        return values2;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    abstract class HashIterator<T> implements Iterator<T> {
        @CheckForNull
        Segment<K, V, E, S> currentSegment;
        @CheckForNull
        AtomicReferenceArray<E> currentTable;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry lastReturned;
        @CheckForNull
        E nextEntry;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public abstract T next();

        HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            advance();
        }

        /* access modifiers changed from: package-private */
        public final void advance() {
            this.nextExternal = null;
            if (!nextInChain() && !nextInTable()) {
                while (this.nextSegmentIndex >= 0) {
                    Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.segments;
                    int i = this.nextSegmentIndex;
                    this.nextSegmentIndex = i - 1;
                    Segment<K, V, E, S> segment = segmentArr[i];
                    this.currentSegment = segment;
                    if (segment.count != 0) {
                        AtomicReferenceArray<E> atomicReferenceArray = this.currentSegment.table;
                        this.currentTable = atomicReferenceArray;
                        this.nextTableIndex = atomicReferenceArray.length() - 1;
                        if (nextInTable()) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInChain() {
            E e = this.nextEntry;
            if (e == null) {
                return false;
            }
            while (true) {
                this.nextEntry = e.getNext();
                E e2 = this.nextEntry;
                if (e2 == null) {
                    return false;
                }
                if (advanceTo(e2)) {
                    return true;
                }
                e = this.nextEntry;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInTable() {
            while (true) {
                int i = this.nextTableIndex;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i - 1;
                E e = (InternalEntry) atomicReferenceArray.get(i);
                this.nextEntry = e;
                if (e != null && (advanceTo(e) || nextInChain())) {
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean advanceTo(E entry) {
            try {
                K key = entry.getKey();
                V value = MapMakerInternalMap.this.getLiveValue(entry);
                if (value != null) {
                    this.nextExternal = new WriteThroughEntry(key, value);
                    return true;
                }
                this.currentSegment.postReadCleanup();
                return false;
            } finally {
                this.currentSegment.postReadCleanup();
            }
        }

        public boolean hasNext() {
            return this.nextExternal != null;
        }

        /* access modifiers changed from: package-private */
        public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextEntry() {
            MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry != null) {
                this.lastReturned = writeThroughEntry;
                advance();
                return this.lastReturned;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.lastReturned != null);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        KeyIterator(MapMakerInternalMap this$0) {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        ValueIterator(MapMakerInternalMap this$0) {
            super();
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    final class WriteThroughEntry extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K key2, V value2) {
            this.key = key2;
            this.value = value2;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public boolean equals(@CheckForNull Object object) {
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> that = (Map.Entry) object;
            if (!this.key.equals(that.getKey()) || !this.value.equals(that.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        public V setValue(V newValue) {
            V oldValue = MapMakerInternalMap.this.put(this.key, newValue);
            this.value = newValue;
            return oldValue;
        }
    }

    final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        EntryIterator(MapMakerInternalMap this$0) {
            super();
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    final class KeySet extends SafeToArraySet<K> {
        KeySet() {
            super();
        }

        public Iterator<K> iterator() {
            return new KeyIterator(MapMakerInternalMap.this);
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object o) {
            return MapMakerInternalMap.this.containsKey(o);
        }

        public boolean remove(Object o) {
            return MapMakerInternalMap.this.remove(o) != null;
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class Values extends AbstractCollection<V> {
        Values() {
        }

        public Iterator<V> iterator() {
            return new ValueIterator(MapMakerInternalMap.this);
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object o) {
            return MapMakerInternalMap.this.containsValue(o);
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <T> T[] toArray(T[] a) {
            return MapMakerInternalMap.toArrayList(this).toArray(a);
        }
    }

    final class EntrySet extends SafeToArraySet<Map.Entry<K, V>> {
        EntrySet() {
            super();
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator(MapMakerInternalMap.this);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r0 = (java.util.Map.Entry) r7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r7) {
            /*
                r6 = this;
                boolean r0 = r7 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                r0 = r7
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r2 = r0.getKey()
                if (r2 != 0) goto L_0x0010
                return r1
            L_0x0010:
                com.google.common.collect.MapMakerInternalMap r3 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r3 = r3.get(r2)
                if (r3 == 0) goto L_0x0029
                com.google.common.collect.MapMakerInternalMap r4 = com.google.common.collect.MapMakerInternalMap.this
                com.google.common.base.Equivalence r4 = r4.valueEquivalence()
                java.lang.Object r5 = r0.getValue()
                boolean r4 = r4.equivalent(r5, r3)
                if (r4 == 0) goto L_0x0029
                r1 = 1
            L_0x0029:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.contains(java.lang.Object):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r0 = (java.util.Map.Entry) r6;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r6) {
            /*
                r5 = this;
                boolean r0 = r6 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                r0 = r6
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r2 = r0.getKey()
                if (r2 == 0) goto L_0x001c
                com.google.common.collect.MapMakerInternalMap r3 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r4 = r0.getValue()
                boolean r3 = r3.remove(r2, r4)
                if (r3 == 0) goto L_0x001c
                r1 = 1
            L_0x001c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.remove(java.lang.Object):boolean");
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    private static abstract class SafeToArraySet<E> extends AbstractSet<E> {
        private SafeToArraySet() {
        }

        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        public <T> T[] toArray(T[] a) {
            return MapMakerInternalMap.toArrayList(this).toArray(a);
        }
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> c) {
        ArrayList<E> result = new ArrayList<>(c.size());
        Iterators.addAll(result, c.iterator());
        return result;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.entryHelper.keyStrength(), this.entryHelper.valueStrength(), this.keyEquivalence, this.entryHelper.valueStrength().defaultEquivalence(), this.concurrencyLevel, this);
    }

    static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength keyStrength2, Strength valueStrength2, Equivalence<Object> keyEquivalence2, Equivalence<Object> valueEquivalence2, int concurrencyLevel2, ConcurrentMap<K, V> delegate2) {
            this.keyStrength = keyStrength2;
            this.valueStrength = valueStrength2;
            this.keyEquivalence = keyEquivalence2;
            this.valueEquivalence = valueEquivalence2;
            this.concurrencyLevel = concurrencyLevel2;
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void writeMapTo(ObjectOutputStream out) throws IOException {
            out.writeInt(this.delegate.size());
            for (Map.Entry<K, V> entry : this.delegate.entrySet()) {
                out.writeObject(entry.getKey());
                out.writeObject(entry.getValue());
            }
            out.writeObject((Object) null);
        }

        /* access modifiers changed from: package-private */
        public MapMaker readMapMaker(ObjectInputStream in) throws IOException {
            return new MapMaker().initialCapacity(in.readInt()).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
        }

        /* access modifiers changed from: package-private */
        public void readEntries(ObjectInputStream in) throws IOException, ClassNotFoundException {
            while (true) {
                K key = in.readObject();
                if (key != null) {
                    this.delegate.put(key, in.readObject());
                } else {
                    return;
                }
            }
        }
    }

    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, int concurrencyLevel, ConcurrentMap<K, V> delegate) {
            super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, concurrencyLevel, delegate);
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            writeMapTo(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.delegate = readMapMaker(in).makeMap();
            readEntries(in);
        }

        private Object readResolve() {
            return this.delegate;
        }
    }
}
