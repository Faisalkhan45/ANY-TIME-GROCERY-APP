package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> forwardOrder;

    ReverseOrdering(Ordering<? super T> forwardOrder2) {
        this.forwardOrder = (Ordering) Preconditions.checkNotNull(forwardOrder2);
    }

    public int compare(@ParametricNullness T a, @ParametricNullness T b) {
        return this.forwardOrder.compare(b, a);
    }

    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    public <E extends T> E min(@ParametricNullness E a, @ParametricNullness E b) {
        return this.forwardOrder.max(a, b);
    }

    public <E extends T> E min(@ParametricNullness E a, @ParametricNullness E b, @ParametricNullness E c, E... rest) {
        return this.forwardOrder.max(a, b, c, rest);
    }

    public <E extends T> E min(Iterator<E> iterator) {
        return this.forwardOrder.max(iterator);
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return this.forwardOrder.max(iterable);
    }

    public <E extends T> E max(@ParametricNullness E a, @ParametricNullness E b) {
        return this.forwardOrder.min(a, b);
    }

    public <E extends T> E max(@ParametricNullness E a, @ParametricNullness E b, @ParametricNullness E c, E... rest) {
        return this.forwardOrder.min(a, b, c, rest);
    }

    public <E extends T> E max(Iterator<E> iterator) {
        return this.forwardOrder.min(iterator);
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return this.forwardOrder.min(iterable);
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    public boolean equals(@CheckForNull Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) object).forwardOrder);
        }
        return false;
    }

    public String toString() {
        String valueOf = String.valueOf(this.forwardOrder);
        return new StringBuilder(String.valueOf(valueOf).length() + 10).append(valueOf).append(".reverse()").toString();
    }
}
