package com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class PairwiseEquivalence<E, T extends E> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    final Equivalence<E> elementEquivalence;

    PairwiseEquivalence(Equivalence<E> elementEquivalence2) {
        this.elementEquivalence = (Equivalence) Preconditions.checkNotNull(elementEquivalence2);
    }

    /* access modifiers changed from: protected */
    public boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
        Iterator<T> iteratorA = iterableA.iterator();
        Iterator<T> iteratorB = iterableB.iterator();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (!this.elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) {
                return false;
            }
        }
        if (iteratorA.hasNext() || iteratorB.hasNext()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int doHash(Iterable<T> iterable) {
        int hash = 78721;
        for (T element : iterable) {
            hash = (hash * 24943) + this.elementEquivalence.hash(element);
        }
        return hash;
    }

    public boolean equals(@CheckForNull Object object) {
        if (object instanceof PairwiseEquivalence) {
            return this.elementEquivalence.equals(((PairwiseEquivalence) object).elementEquivalence);
        }
        return false;
    }

    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 1185147655;
    }

    public String toString() {
        String valueOf = String.valueOf(this.elementEquivalence);
        return new StringBuilder(String.valueOf(valueOf).length() + 11).append(valueOf).append(".pairwise()").toString();
    }
}
