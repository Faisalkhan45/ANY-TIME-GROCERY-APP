package com.google.common.base;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class Suppliers {

    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Function<? super F, T> function;
        final Supplier<F> supplier;

        SupplierComposition(Function<? super F, T> function2, Supplier<F> supplier2) {
            this.function = (Function) Preconditions.checkNotNull(function2);
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier2);
        }

        @ParametricNullness
        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof SupplierComposition)) {
                return false;
            }
            SupplierComposition<?, ?> that = (SupplierComposition) obj;
            if (!this.function.equals(that.function) || !this.supplier.equals(that.supplier)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.function, this.supplier);
        }

        public String toString() {
            String valueOf = String.valueOf(this.function);
            String valueOf2 = String.valueOf(this.supplier);
            return new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append("Suppliers.compose(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Supplier<T> memoize(Supplier<T> delegate) {
        if ((delegate instanceof NonSerializableMemoizingSupplier) || (delegate instanceof MemoizingSupplier)) {
            return delegate;
        }
        if (delegate instanceof Serializable) {
            return new MemoizingSupplier(delegate);
        }
        return new NonSerializableMemoizingSupplier(delegate);
    }

    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        @CheckForNull
        transient T value;

        MemoizingSupplier(Supplier<T> delegate2) {
            this.delegate = (Supplier) Preconditions.checkNotNull(delegate2);
        }

        @ParametricNullness
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj;
            if (this.initialized) {
                String valueOf = String.valueOf(this.value);
                obj = new StringBuilder(String.valueOf(valueOf).length() + 25).append("<supplier that returned ").append(valueOf).append(">").toString();
            } else {
                obj = this.delegate;
            }
            String valueOf2 = String.valueOf(obj);
            return new StringBuilder(String.valueOf(valueOf2).length() + 19).append("Suppliers.memoize(").append(valueOf2).append(")").toString();
        }
    }

    static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        @CheckForNull
        volatile Supplier<T> delegate;
        volatile boolean initialized;
        @CheckForNull
        T value;

        NonSerializableMemoizingSupplier(Supplier<T> delegate2) {
            this.delegate = (Supplier) Preconditions.checkNotNull(delegate2);
        }

        @ParametricNullness
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = ((Supplier) Objects.requireNonNull(this.delegate)).get();
                        this.value = t;
                        this.initialized = true;
                        this.delegate = null;
                        return t;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj;
            Supplier<T> delegate2 = this.delegate;
            if (delegate2 == null) {
                String valueOf = String.valueOf(this.value);
                obj = new StringBuilder(String.valueOf(valueOf).length() + 25).append("<supplier that returned ").append(valueOf).append(">").toString();
            } else {
                obj = delegate2;
            }
            String valueOf2 = String.valueOf(obj);
            return new StringBuilder(String.valueOf(valueOf2).length() + 19).append("Suppliers.memoize(").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> delegate, long duration, TimeUnit unit) {
        return new ExpiringMemoizingSupplier(delegate, duration, unit);
    }

    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        final long durationNanos;
        volatile transient long expirationNanos;
        @CheckForNull
        volatile transient T value;

        ExpiringMemoizingSupplier(Supplier<T> delegate2, long duration, TimeUnit unit) {
            this.delegate = (Supplier) Preconditions.checkNotNull(delegate2);
            this.durationNanos = unit.toNanos(duration);
            Preconditions.checkArgument(duration > 0, "duration (%s %s) must be > 0", duration, (Object) unit);
        }

        @ParametricNullness
        public T get() {
            long nanos = this.expirationNanos;
            long now = Platform.systemNanoTime();
            if (nanos == 0 || now - nanos >= 0) {
                synchronized (this) {
                    if (nanos == this.expirationNanos) {
                        T t = this.delegate.get();
                        this.value = t;
                        long nanos2 = now + this.durationNanos;
                        this.expirationNanos = nanos2 == 0 ? 1 : nanos2;
                        return t;
                    }
                }
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            return new StringBuilder(String.valueOf(valueOf).length() + 62).append("Suppliers.memoizeWithExpiration(").append(valueOf).append(", ").append(this.durationNanos).append(", NANOS)").toString();
        }
    }

    public static <T> Supplier<T> ofInstance(@ParametricNullness T instance) {
        return new SupplierOfInstance(instance);
    }

    private static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        @ParametricNullness
        final T instance;

        SupplierOfInstance(@ParametricNullness T instance2) {
            this.instance = instance2;
        }

        @ParametricNullness
        public T get() {
            return this.instance;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            String valueOf = String.valueOf(this.instance);
            return new StringBuilder(String.valueOf(valueOf).length() + 22).append("Suppliers.ofInstance(").append(valueOf).append(")").toString();
        }
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> delegate) {
        return new ThreadSafeSupplier(delegate);
    }

    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;

        ThreadSafeSupplier(Supplier<T> delegate2) {
            this.delegate = (Supplier) Preconditions.checkNotNull(delegate2);
        }

        @ParametricNullness
        public T get() {
            T t;
            synchronized (this.delegate) {
                t = this.delegate.get();
            }
            return t;
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            return new StringBuilder(String.valueOf(valueOf).length() + 32).append("Suppliers.synchronizedSupplier(").append(valueOf).append(")").toString();
        }
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @CheckForNull
        public Object apply(Supplier<Object> input) {
            return input.get();
        }

        public String toString() {
            return "Suppliers.supplierFunction()";
        }
    }
}
