package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T>[] comparators;

    CompoundOrdering(Comparator<? super T> primary, Comparator<? super T> secondary) {
        this.comparators = new Comparator[]{primary, secondary};
    }

    CompoundOrdering(Iterable<? extends Comparator<? super T>> comparators2) {
        this.comparators = (Comparator[]) Iterables.toArray(comparators2, (T[]) new Comparator[0]);
    }

    public int compare(@ParametricNullness T left, @ParametricNullness T right) {
        int i = 0;
        while (true) {
            Comparator<? super T>[] comparatorArr = this.comparators;
            if (i >= comparatorArr.length) {
                return 0;
            }
            int result = comparatorArr[i].compare(left, right);
            if (result != 0) {
                return result;
            }
            i++;
        }
    }

    public boolean equals(@CheckForNull Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof CompoundOrdering) {
            return Arrays.equals(this.comparators, ((CompoundOrdering) object).comparators);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.comparators);
    }

    public String toString() {
        String arrays = Arrays.toString(this.comparators);
        return new StringBuilder(String.valueOf(arrays).length() + 19).append("Ordering.compound(").append(arrays).append(")").toString();
    }
}
