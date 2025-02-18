package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
class Subscriber {
    private EventBus bus;
    private final Executor executor;
    private final Method method;
    final Object target;

    static Subscriber create(EventBus bus2, Object listener, Method method2) {
        if (isDeclaredThreadSafe(method2)) {
            return new Subscriber(bus2, listener, method2);
        }
        return new SynchronizedSubscriber(bus2, listener, method2);
    }

    private Subscriber(EventBus bus2, Object target2, Method method2) {
        this.bus = bus2;
        this.target = Preconditions.checkNotNull(target2);
        this.method = method2;
        method2.setAccessible(true);
        this.executor = bus2.executor();
    }

    /* access modifiers changed from: package-private */
    public final void dispatchEvent(Object event) {
        this.executor.execute(new Subscriber$$ExternalSyntheticLambda0(this, event));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchEvent$0$com-google-common-eventbus-Subscriber  reason: not valid java name */
    public /* synthetic */ void m288lambda$dispatchEvent$0$comgooglecommoneventbusSubscriber(Object event) {
        try {
            invokeSubscriberMethod(event);
        } catch (InvocationTargetException e) {
            this.bus.handleSubscriberException(e.getCause(), context(event));
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
        try {
            this.method.invoke(this.target, new Object[]{Preconditions.checkNotNull(event)});
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(event);
            throw new Error(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Method rejected target/argument: ").append(valueOf).toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(event);
            throw new Error(new StringBuilder(String.valueOf(valueOf2).length() + 28).append("Method became inaccessible: ").append(valueOf2).toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    private SubscriberExceptionContext context(Object event) {
        return new SubscriberExceptionContext(this.bus, event, this.target, this.method);
    }

    public final int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber that = (Subscriber) obj;
        if (this.target != that.target || !this.method.equals(that.method)) {
            return false;
        }
        return true;
    }

    private static boolean isDeclaredThreadSafe(Method method2) {
        return method2.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus bus, Object target, Method method) {
            super(bus, target, method);
        }

        /* access modifiers changed from: package-private */
        public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
            synchronized (this) {
                Subscriber.super.invokeSubscriberMethod(event);
            }
        }
    }
}
