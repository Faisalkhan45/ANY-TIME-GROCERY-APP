package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.EventLoopImplBase;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\u0014\u0010 \u001a\u00020\u001e2\n\u0010!\u001a\u00060\u0002j\u0002`\u0003H\u0016J\r\u0010\"\u001a\u00020\u001eH\u0000¢\u0006\u0002\b#J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\b2\n\u0010'\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0015H\u0002J\u0018\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.H\u0014J\b\u0010/\u001a\u00020\u001eH\u0016J\b\u00100\u001a\u00020\u001eH\u0016J\b\u00101\u001a\u00020\u001eH\u0002J\u000e\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0004R\u000e\u0010\u0013\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00158@X\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00118TX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u00064"}, d2 = {"Lkotlinx/coroutines/DefaultExecutor;", "Lkotlinx/coroutines/EventLoopImplBase;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "()V", "ACTIVE", "", "DEFAULT_KEEP_ALIVE_MS", "", "FRESH", "KEEP_ALIVE_NANOS", "SHUTDOWN", "SHUTDOWN_ACK", "SHUTDOWN_REQ", "THREAD_NAME", "", "_thread", "Ljava/lang/Thread;", "get_thread$annotations", "debugStatus", "isShutDown", "", "()Z", "isShutdownRequested", "isThreadPresent", "isThreadPresent$kotlinx_coroutines_core", "thread", "getThread", "()Ljava/lang/Thread;", "acknowledgeShutdownIfNeeded", "", "createThreadSync", "enqueue", "task", "ensureStarted", "ensureStarted$kotlinx_coroutines_core", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "block", "context", "Lkotlin/coroutines/CoroutineContext;", "notifyStartup", "reschedule", "now", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "run", "shutdown", "shutdownError", "shutdownForTests", "timeout", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: DefaultExecutor.kt */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    private static final int ACTIVE = 1;
    private static final long DEFAULT_KEEP_ALIVE_MS = 1000;
    private static final int FRESH = 0;
    public static final DefaultExecutor INSTANCE;
    private static final long KEEP_ALIVE_NANOS;
    private static final int SHUTDOWN = 4;
    private static final int SHUTDOWN_ACK = 3;
    private static final int SHUTDOWN_REQ = 2;
    public static final String THREAD_NAME = "kotlinx.coroutines.DefaultExecutor";
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    private static /* synthetic */ void get_thread$annotations() {
    }

    private DefaultExecutor() {
    }

    static {
        Long l;
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        INSTANCE = defaultExecutor;
        EventLoop.incrementUseCount$default(defaultExecutor, false, 1, (Object) null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000);
        } catch (SecurityException e) {
            l = 1000L;
        }
        KEEP_ALIVE_NANOS = timeUnit.toNanos(l.longValue());
    }

    /* access modifiers changed from: protected */
    public Thread getThread() {
        Thread thread = _thread;
        return thread == null ? createThreadSync() : thread;
    }

    private final boolean isShutDown() {
        return debugStatus == 4;
    }

    private final boolean isShutdownRequested() {
        int debugStatus2 = debugStatus;
        return debugStatus2 == 2 || debugStatus2 == 3;
    }

    public void enqueue(Runnable task) {
        if (isShutDown()) {
            shutdownError();
        }
        super.enqueue(task);
    }

    /* access modifiers changed from: protected */
    public void reschedule(long now, EventLoopImplBase.DelayedTask delayedTask) {
        shutdownError();
    }

    private final void shutdownError() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    public void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    public DisposableHandle invokeOnTimeout(long timeMillis, Runnable block, CoroutineContext context) {
        return scheduleInvokeOnTimeout(timeMillis, block);
    }

    public void run() {
        Unit unit;
        ThreadLocalEventLoop.INSTANCE.setEventLoop$kotlinx_coroutines_core(this);
        AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
        if (timeSource != null) {
            timeSource.registerTimeLoopThread();
        }
        long shutdownNanos = Long.MAX_VALUE;
        try {
            if (notifyStartup()) {
                while (true) {
                    Thread.interrupted();
                    long parkNanos = processNextEvent();
                    if (parkNanos == Long.MAX_VALUE) {
                        AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
                        Long valueOf = timeSource2 == null ? null : Long.valueOf(timeSource2.nanoTime());
                        long now = valueOf == null ? System.nanoTime() : valueOf.longValue();
                        if (shutdownNanos == Long.MAX_VALUE) {
                            shutdownNanos = now + KEEP_ALIVE_NANOS;
                        }
                        long tillShutdown = shutdownNanos - now;
                        if (tillShutdown <= 0) {
                            _thread = null;
                            acknowledgeShutdownIfNeeded();
                            AbstractTimeSource timeSource3 = AbstractTimeSourceKt.getTimeSource();
                            if (timeSource3 != null) {
                                timeSource3.unregisterTimeLoopThread();
                            }
                            if (!isEmpty()) {
                                getThread();
                            }
                            long j = parkNanos;
                            long j2 = tillShutdown;
                            long j3 = now;
                            return;
                        }
                        parkNanos = RangesKt.coerceAtMost(parkNanos, tillShutdown);
                    } else {
                        shutdownNanos = Long.MAX_VALUE;
                    }
                    if (parkNanos > 0) {
                        if (isShutdownRequested()) {
                            _thread = null;
                            acknowledgeShutdownIfNeeded();
                            AbstractTimeSource timeSource4 = AbstractTimeSourceKt.getTimeSource();
                            if (timeSource4 != null) {
                                timeSource4.unregisterTimeLoopThread();
                            }
                            if (!isEmpty()) {
                                getThread();
                            }
                            long j4 = parkNanos;
                            return;
                        }
                        AbstractTimeSource timeSource5 = AbstractTimeSourceKt.getTimeSource();
                        if (timeSource5 == null) {
                            unit = null;
                        } else {
                            timeSource5.parkNanos(this, parkNanos);
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            LockSupport.parkNanos(this, parkNanos);
                        }
                    }
                }
            }
        } finally {
            _thread = null;
            acknowledgeShutdownIfNeeded();
            AbstractTimeSource timeSource6 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource6 != null) {
                timeSource6.unregisterTimeLoopThread();
            }
            if (!isEmpty()) {
                getThread();
            }
        }
    }

    private final synchronized Thread createThreadSync() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, THREAD_NAME);
            Thread $this$createThreadSync_u24lambda_u2d0 = thread;
            _thread = $this$createThreadSync_u24lambda_u2d0;
            $this$createThreadSync_u24lambda_u2d0.setDaemon(true);
            $this$createThreadSync_u24lambda_u2d0.start();
        }
        return thread;
    }

    public final synchronized void ensureStarted$kotlinx_coroutines_core() {
        boolean z = true;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((_thread == null ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (debugStatus != 0) {
                if (debugStatus != 3) {
                    z = false;
                }
            }
            if (!z) {
                throw new AssertionError();
            }
        }
        debugStatus = 0;
        createThreadSync();
        while (debugStatus == 0) {
            wait();
        }
    }

    private final synchronized boolean notifyStartup() {
        if (isShutdownRequested()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    public final synchronized void shutdownForTests(long timeout) {
        Unit unit;
        long deadline = System.currentTimeMillis() + timeout;
        if (!isShutdownRequested()) {
            debugStatus = 2;
        }
        while (true) {
            if (debugStatus == 3 || _thread == null) {
                break;
            }
            Thread it = _thread;
            if (it != null) {
                AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
                if (timeSource == null) {
                    unit = null;
                } else {
                    timeSource.unpark(it);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    LockSupport.unpark(it);
                }
            }
            if (deadline - System.currentTimeMillis() <= 0) {
                break;
            }
            wait(timeout);
        }
        debugStatus = 0;
    }

    private final synchronized void acknowledgeShutdownIfNeeded() {
        if (isShutdownRequested()) {
            debugStatus = 3;
            resetAll();
            notifyAll();
        }
    }

    public final boolean isThreadPresent$kotlinx_coroutines_core() {
        return _thread != null;
    }
}
