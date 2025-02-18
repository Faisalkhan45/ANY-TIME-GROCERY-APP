package com.google.common.base;

import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @CheckForNull
    @LazyInit
    private transient Converter<B, A> reverse;

    /* access modifiers changed from: protected */
    public abstract A doBackward(B b);

    /* access modifiers changed from: protected */
    public abstract B doForward(A a);

    protected Converter() {
        this(true);
    }

    Converter(boolean handleNullAutomatically2) {
        this.handleNullAutomatically = handleNullAutomatically2;
    }

    @CheckForNull
    public final B convert(@CheckForNull A a) {
        return correctedDoForward(a);
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public B correctedDoForward(@CheckForNull A a) {
        if (!this.handleNullAutomatically) {
            return unsafeDoForward(a);
        }
        if (a == null) {
            return null;
        }
        return Preconditions.checkNotNull(doForward(a));
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public A correctedDoBackward(@CheckForNull B b) {
        if (!this.handleNullAutomatically) {
            return unsafeDoBackward(b);
        }
        if (b == null) {
            return null;
        }
        return Preconditions.checkNotNull(doBackward(b));
    }

    @CheckForNull
    private B unsafeDoForward(@CheckForNull A a) {
        return doForward(NullnessCasts.uncheckedCastNullableTToT(a));
    }

    @CheckForNull
    private A unsafeDoBackward(@CheckForNull B b) {
        return doBackward(NullnessCasts.uncheckedCastNullableTToT(b));
    }

    public Iterable<B> convertAll(final Iterable<? extends A> fromIterable) {
        Preconditions.checkNotNull(fromIterable, "fromIterable");
        return new Iterable<B>() {
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    private final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = fromIterable.iterator();
                    }

                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @CheckForNull
                    public B next() {
                        return Converter.this.convert(this.fromIterator.next());
                    }

                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    @CheckReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> result = this.reverse;
        if (result != null) {
            return result;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> original2) {
            this.original = original2;
        }

        /* access modifiers changed from: protected */
        public A doForward(B b) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public B doBackward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public A correctedDoForward(@CheckForNull B b) {
            return this.original.correctedDoBackward(b);
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public B correctedDoBackward(@CheckForNull A a) {
            return this.original.correctedDoForward(a);
        }

        public Converter<A, B> reverse() {
            return this.original;
        }

        public boolean equals(@CheckForNull Object object) {
            if (object instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) object).original);
            }
            return false;
        }

        public int hashCode() {
            return ~this.original.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.original);
            return new StringBuilder(String.valueOf(valueOf).length() + 10).append(valueOf).append(".reverse()").toString();
        }
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> secondConverter) {
        return doAndThen(secondConverter);
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> doAndThen(Converter<B, C> secondConverter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(secondConverter));
    }

    private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> first2, Converter<B, C> second2) {
            this.first = first2;
            this.second = second2;
        }

        /* access modifiers changed from: protected */
        public C doForward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public A doBackward(C c) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public C correctedDoForward(@CheckForNull A a) {
            return this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public A correctedDoBackward(@CheckForNull C c) {
            return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        public boolean equals(@CheckForNull Object object) {
            if (!(object instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition<?, ?, ?> that = (ConverterComposition) object;
            if (!this.first.equals(that.first) || !this.second.equals(that.second)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.first);
            String valueOf2 = String.valueOf(this.second);
            return new StringBuilder(String.valueOf(valueOf).length() + 10 + String.valueOf(valueOf2).length()).append(valueOf).append(".andThen(").append(valueOf2).append(")").toString();
        }
    }

    @CheckForNull
    @Deprecated
    public final B apply(@CheckForNull A a) {
        return convert(a);
    }

    public boolean equals(@CheckForNull Object object) {
        return super.equals(object);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
        return new FunctionBasedConverter(forwardFunction, backwardFunction);
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> forwardFunction2, Function<? super B, ? extends A> backwardFunction2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(forwardFunction2);
            this.backwardFunction = (Function) Preconditions.checkNotNull(backwardFunction2);
        }

        /* access modifiers changed from: protected */
        public B doForward(A a) {
            return this.forwardFunction.apply(a);
        }

        /* access modifiers changed from: protected */
        public A doBackward(B b) {
            return this.backwardFunction.apply(b);
        }

        public boolean equals(@CheckForNull Object object) {
            if (!(object instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter<?, ?> that = (FunctionBasedConverter) object;
            if (!this.forwardFunction.equals(that.forwardFunction) || !this.backwardFunction.equals(that.backwardFunction)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.forwardFunction);
            String valueOf2 = String.valueOf(this.backwardFunction);
            return new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Converter.from(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter<?> INSTANCE = new IdentityConverter<>();
        private static final long serialVersionUID = 0;

        private IdentityConverter() {
        }

        /* access modifiers changed from: protected */
        public T doForward(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        public T doBackward(T t) {
            return t;
        }

        public IdentityConverter<T> reverse() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public <S> Converter<T, S> doAndThen(Converter<T, S> otherConverter) {
            return (Converter) Preconditions.checkNotNull(otherConverter, "otherConverter");
        }

        public String toString() {
            return "Converter.identity()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
