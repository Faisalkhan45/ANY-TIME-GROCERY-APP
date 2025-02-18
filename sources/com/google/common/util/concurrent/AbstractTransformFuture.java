package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {
    @CheckForNull
    F function;
    @CheckForNull
    ListenableFuture<? extends I> inputFuture;

    /* access modifiers changed from: package-private */
    @ParametricNullness
    public abstract T doTransform(F f, @ParametricNullness I i) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void setResult(@ParametricNullness T t);

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function2, Executor executor) {
        Preconditions.checkNotNull(executor);
        AsyncTransformFuture<I, O> output = new AsyncTransformFuture<>(input, function2);
        input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
        return output;
    }

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, Function<? super I, ? extends O> function2, Executor executor) {
        Preconditions.checkNotNull(function2);
        TransformFuture<I, O> output = new TransformFuture<>(input, function2);
        input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
        return output;
    }

    AbstractTransformFuture(ListenableFuture<? extends I> inputFuture2, F function2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        this.function = Preconditions.checkNotNull(function2);
    }

    public final void run() {
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        boolean z = true;
        boolean isCancelled = isCancelled() | (localInputFuture == null);
        if (localFunction != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.inputFuture = null;
            if (localInputFuture.isCancelled()) {
                boolean future = setFuture(localInputFuture);
                return;
            }
            try {
                try {
                    T transformResult = doTransform(localFunction, Futures.getDone(localInputFuture));
                    this.function = null;
                    setResult(transformResult);
                } catch (Throwable t) {
                    this.function = null;
                    throw t;
                }
            } catch (CancellationException e) {
                cancel(false);
            } catch (ExecutionException e2) {
                setException(e2.getCause());
            } catch (RuntimeException e3) {
                setException(e3);
            } catch (Error e4) {
                setException(e4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public String pendingToString() {
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            String valueOf = String.valueOf(localInputFuture);
            resultString = new StringBuilder(String.valueOf(valueOf).length() + 16).append("inputFuture=[").append(valueOf).append("], ").toString();
        }
        if (localFunction != null) {
            String valueOf2 = String.valueOf(localFunction);
            return new StringBuilder(String.valueOf(resultString).length() + 11 + String.valueOf(valueOf2).length()).append(resultString).append("function=[").append(valueOf2).append("]").toString();
        } else if (superString == null) {
            return null;
        } else {
            String valueOf3 = String.valueOf(resultString);
            String valueOf4 = String.valueOf(superString);
            return valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        }
    }

    private static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        AsyncTransformFuture(ListenableFuture<? extends I> inputFuture, AsyncFunction<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> function, @ParametricNullness I input) throws Exception {
            ListenableFuture<? extends O> outputFuture = function.apply(input);
            Preconditions.checkNotNull(outputFuture, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) function);
            return outputFuture;
        }

        /* access modifiers changed from: package-private */
        public void setResult(ListenableFuture<? extends O> result) {
            setFuture(result);
        }
    }

    private static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        TransformFuture(ListenableFuture<? extends I> inputFuture, Function<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* access modifiers changed from: package-private */
        @ParametricNullness
        public O doTransform(Function<? super I, ? extends O> function, @ParametricNullness I input) {
            return function.apply(input);
        }

        /* access modifiers changed from: package-private */
        public void setResult(@ParametricNullness O result) {
            set(result);
        }
    }
}
