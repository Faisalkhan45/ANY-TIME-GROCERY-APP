package com.google.common.util.concurrent;

import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class Platform {
    static boolean isInstanceOfThrowableClass(@CheckForNull Throwable t, Class<? extends Throwable> expectedClass) {
        return expectedClass.isInstance(t);
    }

    private Platform() {
    }
}
