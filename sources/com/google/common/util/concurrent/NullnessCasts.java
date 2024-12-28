package com.google.common.util.concurrent;

import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class NullnessCasts {
    @ParametricNullness
    static <T> T uncheckedCastNullableTToT(@CheckForNull T t) {
        return t;
    }

    @ParametricNullness
    static <T> T uncheckedNull() {
        return null;
    }

    private NullnessCasts() {
    }
}
