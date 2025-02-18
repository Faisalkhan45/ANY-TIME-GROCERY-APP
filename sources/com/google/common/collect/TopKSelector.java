package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;
    private final int k;
    @CheckForNull
    private T threshold;

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int k2) {
        return least(k2, Ordering.natural());
    }

    public static <T> TopKSelector<T> least(int k2, Comparator<? super T> comparator2) {
        return new TopKSelector<>(comparator2, k2);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int k2) {
        return greatest(k2, Ordering.natural());
    }

    public static <T> TopKSelector<T> greatest(int k2, Comparator<? super T> comparator2) {
        return new TopKSelector<>(Ordering.from(comparator2).reverse(), k2);
    }

    private TopKSelector(Comparator<? super T> comparator2, int k2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2, "comparator");
        this.k = k2;
        boolean z = true;
        Preconditions.checkArgument(k2 >= 0, "k (%s) must be >= 0", k2);
        Preconditions.checkArgument(k2 > 1073741823 ? false : z, "k (%s) must be <= Integer.MAX_VALUE / 2", k2);
        this.buffer = new Object[IntMath.checkedMultiply(k2, 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public void offer(@ParametricNullness T elem) {
        int i = this.k;
        if (i != 0) {
            int i2 = this.bufferSize;
            if (i2 == 0) {
                this.buffer[0] = elem;
                this.threshold = elem;
                this.bufferSize = 1;
            } else if (i2 < i) {
                T[] tArr = this.buffer;
                this.bufferSize = i2 + 1;
                tArr[i2] = elem;
                if (this.comparator.compare(elem, NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                    this.threshold = elem;
                }
            } else if (this.comparator.compare(elem, NullnessCasts.uncheckedCastNullableTToT(this.threshold)) < 0) {
                T[] tArr2 = this.buffer;
                int i3 = this.bufferSize;
                int i4 = i3 + 1;
                this.bufferSize = i4;
                tArr2[i3] = elem;
                if (i4 == this.k * 2) {
                    trim();
                }
            }
        }
    }

    private void trim() {
        int left = 0;
        int right = (this.k * 2) - 1;
        int minThresholdPosition = 0;
        int iterations = 0;
        int maxIterations = IntMath.log2(right - 0, RoundingMode.CEILING) * 3;
        while (true) {
            if (left < right) {
                int pivotNewIndex = partition(left, right, ((left + right) + 1) >>> 1);
                int i = this.k;
                if (pivotNewIndex <= i) {
                    if (pivotNewIndex >= i) {
                        break;
                    }
                    left = Math.max(pivotNewIndex, left + 1);
                    minThresholdPosition = pivotNewIndex;
                } else {
                    right = pivotNewIndex - 1;
                }
                iterations++;
                if (iterations >= maxIterations) {
                    Arrays.sort(this.buffer, left, right + 1, this.comparator);
                    break;
                }
            } else {
                break;
            }
        }
        this.bufferSize = this.k;
        this.threshold = NullnessCasts.uncheckedCastNullableTToT(this.buffer[minThresholdPosition]);
        for (int i2 = minThresholdPosition + 1; i2 < this.k; i2++) {
            if (this.comparator.compare(NullnessCasts.uncheckedCastNullableTToT(this.buffer[i2]), NullnessCasts.uncheckedCastNullableTToT(this.threshold)) > 0) {
                this.threshold = this.buffer[i2];
            }
        }
    }

    private int partition(int left, int right, int pivotIndex) {
        T pivotValue = NullnessCasts.uncheckedCastNullableTToT(this.buffer[pivotIndex]);
        T[] tArr = this.buffer;
        tArr[pivotIndex] = tArr[right];
        int pivotNewIndex = left;
        for (int i = left; i < right; i++) {
            if (this.comparator.compare(NullnessCasts.uncheckedCastNullableTToT(this.buffer[i]), pivotValue) < 0) {
                swap(pivotNewIndex, i);
                pivotNewIndex++;
            }
        }
        T[] tArr2 = this.buffer;
        tArr2[right] = tArr2[pivotNewIndex];
        tArr2[pivotNewIndex] = pivotValue;
        return pivotNewIndex;
    }

    private void swap(int i, int j) {
        T[] tArr = this.buffer;
        T tmp = tArr[i];
        tArr[i] = tArr[j];
        tArr[j] = tmp;
    }

    public void offerAll(Iterable<? extends T> elements) {
        offerAll(elements.iterator());
    }

    public void offerAll(Iterator<? extends T> elements) {
        while (elements.hasNext()) {
            offer(elements.next());
        }
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        int i = this.bufferSize;
        int i2 = this.k;
        if (i > i2) {
            T[] tArr = this.buffer;
            Arrays.fill(tArr, i2, tArr.length, (Object) null);
            int i3 = this.k;
            this.bufferSize = i3;
            this.threshold = this.buffer[i3 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }
}
