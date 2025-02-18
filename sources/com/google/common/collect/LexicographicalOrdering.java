package com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T> elementOrder;

    LexicographicalOrdering(Comparator<? super T> elementOrder2) {
        this.elementOrder = elementOrder2;
    }

    public int compare(Iterable<T> leftIterable, Iterable<T> rightIterable) {
        Iterator<T> right = rightIterable.iterator();
        for (T compare : leftIterable) {
            if (!right.hasNext()) {
                return 1;
            }
            int result = this.elementOrder.compare(compare, right.next());
            if (result != 0) {
                return result;
            }
        }
        if (right.hasNext()) {
            return -1;
        }
        return 0;
    }

    public boolean equals(@CheckForNull Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof LexicographicalOrdering) {
            return this.elementOrder.equals(((LexicographicalOrdering) object).elementOrder);
        }
        return false;
    }

    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    public String toString() {
        String valueOf = String.valueOf(this.elementOrder);
        return new StringBuilder(String.valueOf(valueOf).length() + 18).append(valueOf).append(".lexicographical()").toString();
    }
}
