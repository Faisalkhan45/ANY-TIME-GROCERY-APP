package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    /* access modifiers changed from: private */
    public final ImmutableMap<K, V> map;

    ImmutableMapValues(ImmutableMap<K, V> map2) {
        this.map = map2;
    }

    public int size() {
        return this.map.size();
    }

    public UnmodifiableIterator<V> iterator() {
        return new UnmodifiableIterator<V>() {
            final UnmodifiableIterator<Map.Entry<K, V>> entryItr;

            {
                this.entryItr = ImmutableMapValues.this.map.entrySet().iterator();
            }

            public boolean hasNext() {
                return this.entryItr.hasNext();
            }

            public V next() {
                return ((Map.Entry) this.entryItr.next()).getValue();
            }
        };
    }

    public boolean contains(@CheckForNull Object object) {
        return object != null && Iterators.contains(iterator(), object);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return true;
    }

    public ImmutableList<V> asList() {
        final ImmutableList<Map.Entry<K, V>> entryList = this.map.entrySet().asList();
        return new ImmutableList<V>(this) {
            public V get(int index) {
                return ((Map.Entry) entryList.get(index)).getValue();
            }

            /* access modifiers changed from: package-private */
            public boolean isPartialView() {
                return true;
            }

            public int size() {
                return entryList.size();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }

    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> map;

        SerializedForm(ImmutableMap<?, V> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.values();
        }
    }
}
