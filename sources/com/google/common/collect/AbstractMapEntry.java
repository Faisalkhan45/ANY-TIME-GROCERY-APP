package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    @ParametricNullness
    public abstract K getKey();

    @ParametricNullness
    public abstract V getValue();

    AbstractMapEntry() {
    }

    @ParametricNullness
    public V setValue(@ParametricNullness V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(@CheckForNull Object object) {
        if (!(object instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<?, ?> that = (Map.Entry) object;
        if (!Objects.equal(getKey(), that.getKey()) || !Objects.equal(getValue(), that.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        K k = getKey();
        V v = getValue();
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        if (v != null) {
            i = v.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        String valueOf = String.valueOf(getKey());
        String valueOf2 = String.valueOf(getValue());
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
