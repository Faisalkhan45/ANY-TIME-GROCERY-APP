package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ElementTypesAreNonnullByDefault
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;

    public static /* synthetic */ ReentrantReadWriteLock $r8$lambda$DPHKQ3dY4ROYnkMBoW1EqdOwBu8() {
        return new ReentrantReadWriteLock();
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i);

    /* access modifiers changed from: package-private */
    public abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }

    public Iterable<L> bulkGet(Iterable<? extends Object> keys) {
        List<L> asStripes = Lists.newArrayList(keys);
        if (asStripes.isEmpty()) {
            return ImmutableList.of();
        }
        int[] stripes = new int[asStripes.size()];
        for (int i = 0; i < asStripes.size(); i++) {
            stripes[i] = indexFor(asStripes.get(i));
        }
        Arrays.sort(stripes);
        int previousStripe = stripes[0];
        asStripes.set(0, getAt(previousStripe));
        for (int i2 = 1; i2 < asStripes.size(); i2++) {
            int currentStripe = stripes[i2];
            if (currentStripe == previousStripe) {
                asStripes.set(i2, asStripes.get(i2 - 1));
            } else {
                asStripes.set(i2, getAt(currentStripe));
                previousStripe = currentStripe;
            }
        }
        return Collections.unmodifiableList(asStripes);
    }

    static <L> Striped<L> custom(int stripes, Supplier<L> supplier) {
        return new CompactStriped(stripes, supplier);
    }

    public static Striped<Lock> lock(int stripes) {
        return custom(stripes, new Striped$$ExternalSyntheticLambda5());
    }

    static /* synthetic */ Lock lambda$lazyWeakLock$0() {
        return new ReentrantLock(false);
    }

    public static Striped<Lock> lazyWeakLock(int stripes) {
        return lazy(stripes, new Striped$$ExternalSyntheticLambda4());
    }

    private static <L> Striped<L> lazy(int stripes, Supplier<L> supplier) {
        if (stripes < 1024) {
            return new SmallLazyStriped(stripes, supplier);
        }
        return new LargeLazyStriped(stripes, supplier);
    }

    static /* synthetic */ Semaphore lambda$semaphore$1(int permits) {
        return new PaddedSemaphore(permits);
    }

    public static Striped<Semaphore> semaphore(int stripes, int permits) {
        return custom(stripes, new Striped$$ExternalSyntheticLambda0(permits));
    }

    static /* synthetic */ Semaphore lambda$lazyWeakSemaphore$2(int permits) {
        return new Semaphore(permits, false);
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int stripes, int permits) {
        return lazy(stripes, new Striped$$ExternalSyntheticLambda1(permits));
    }

    public static Striped<ReadWriteLock> readWriteLock(int stripes) {
        return custom(stripes, new Striped$$ExternalSyntheticLambda3());
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int stripes) {
        return lazy(stripes, new Striped$$ExternalSyntheticLambda2());
    }

    private static final class WeakSafeReadWriteLock implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        WeakSafeReadWriteLock() {
        }

        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }

    private static final class WeakSafeLock extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeLock(Lock delegate2, WeakSafeReadWriteLock strongReference2) {
            this.delegate = delegate2;
            this.strongReference = strongReference2;
        }

        /* access modifiers changed from: package-private */
        public Lock delegate() {
            return this.delegate;
        }

        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    private static final class WeakSafeCondition extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeCondition(Condition delegate2, WeakSafeReadWriteLock strongReference2) {
            this.delegate = delegate2;
            this.strongReference = strongReference2;
        }

        /* access modifiers changed from: package-private */
        public Condition delegate() {
            return this.delegate;
        }
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int stripes) {
            super();
            Preconditions.checkArgument(stripes > 0, "Stripes must be positive");
            this.mask = stripes > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(stripes) - 1;
        }

        /* access modifiers changed from: package-private */
        public final int indexFor(Object key) {
            return this.mask & Striped.smear(key.hashCode());
        }

        public final L get(Object key) {
            return getAt(indexFor(key));
        }
    }

    private static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int stripes, Supplier<L> supplier) {
            super(stripes);
            Preconditions.checkArgument(stripes <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[(this.mask + 1)];
            int i = 0;
            while (true) {
                Object[] objArr = this.array;
                if (i < objArr.length) {
                    objArr[i] = supplier.get();
                    i++;
                } else {
                    return;
                }
            }
        }

        public L getAt(int index) {
            return this.array[index];
        }

        public int size() {
            return this.array.length;
        }
    }

    static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue = new ReferenceQueue<>();
        final int size;
        final Supplier<L> supplier;

        SmallLazyStriped(int stripes, Supplier<L> supplier2) {
            super(stripes);
            int i = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.size = i;
            this.locks = new AtomicReferenceArray<>(i);
            this.supplier = supplier2;
        }

        public L getAt(int index) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, size());
            }
            ArrayReference<? extends L> existingRef = this.locks.get(index);
            L existing = existingRef == null ? null : existingRef.get();
            if (existing != null) {
                return existing;
            }
            L created = this.supplier.get();
            ArrayReference<L> newRef = new ArrayReference<>(created, index, this.queue);
            while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.locks, index, existingRef, newRef)) {
                existingRef = this.locks.get(index);
                L existing2 = existingRef == null ? null : existingRef.get();
                if (existing2 != null) {
                    return existing2;
                }
            }
            drainQueue();
            return created;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> poll = this.queue.poll();
                Reference<? extends L> ref = poll;
                if (poll != null) {
                    ArrayReference<? extends L> arrayRef = (ArrayReference) ref;
                    Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.locks, arrayRef.index, arrayRef, (Object) null);
                } else {
                    return;
                }
            }
        }

        public int size() {
            return this.size;
        }

        private static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            ArrayReference(L referent, int index2, ReferenceQueue<L> queue) {
                super(referent, queue);
                this.index = index2;
            }
        }
    }

    static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        LargeLazyStriped(int stripes, Supplier<L> supplier2) {
            super(stripes);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.supplier = supplier2;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        public L getAt(int index) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(index, size());
            }
            L existing = this.locks.get(Integer.valueOf(index));
            if (existing != null) {
                return existing;
            }
            L created = this.supplier.get();
            return MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(index), created), created);
        }

        public int size() {
            return this.size;
        }
    }

    /* access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int x) {
        return 1 << IntMath.log2(x, RoundingMode.CEILING);
    }

    /* access modifiers changed from: private */
    public static int smear(int hashCode) {
        int hashCode2 = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return ((hashCode2 >>> 7) ^ hashCode2) ^ (hashCode2 >>> 4);
    }

    private static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        PaddedLock() {
            super(false);
        }
    }

    private static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        PaddedSemaphore(int permits) {
            super(permits, false);
        }
    }
}
