package androidx.browser.trusted;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.customtabs.trusted.ITrustedWebActivityService;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

class ConnectionHolder implements ServiceConnection {
    private static final int STATE_AWAITING_CONNECTION = 0;
    private static final int STATE_CANCELLED = 3;
    private static final int STATE_CONNECTED = 1;
    private static final int STATE_DISCONNECTED = 2;
    private Exception mCancellationException;
    private final Runnable mCloseRunnable;
    private List<CallbackToFutureAdapter.Completer<TrustedWebActivityServiceConnection>> mCompleters;
    private TrustedWebActivityServiceConnection mService;
    private int mState;
    private final WrapperFactory mWrapperFactory;

    static class WrapperFactory {
        WrapperFactory() {
        }

        /* access modifiers changed from: package-private */
        public TrustedWebActivityServiceConnection create(ComponentName name, IBinder iBinder) {
            return new TrustedWebActivityServiceConnection(ITrustedWebActivityService.Stub.asInterface(iBinder), name);
        }
    }

    ConnectionHolder(Runnable closeRunnable) {
        this(closeRunnable, new WrapperFactory());
    }

    ConnectionHolder(Runnable closeRunnable, WrapperFactory factory) {
        this.mState = 0;
        this.mCompleters = new ArrayList();
        this.mCloseRunnable = closeRunnable;
        this.mWrapperFactory = factory;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mService = this.mWrapperFactory.create(componentName, iBinder);
        for (CallbackToFutureAdapter.Completer<TrustedWebActivityServiceConnection> completer : this.mCompleters) {
            completer.set(this.mService);
        }
        this.mCompleters.clear();
        this.mState = 1;
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.mService = null;
        this.mCloseRunnable.run();
        this.mState = 2;
    }

    public void cancel(Exception exception) {
        for (CallbackToFutureAdapter.Completer<TrustedWebActivityServiceConnection> completer : this.mCompleters) {
            completer.setException(exception);
        }
        this.mCompleters.clear();
        this.mCloseRunnable.run();
        this.mState = 3;
        this.mCancellationException = exception;
    }

    public ListenableFuture<TrustedWebActivityServiceConnection> getServiceWrapper() {
        return CallbackToFutureAdapter.getFuture(new ConnectionHolder$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getServiceWrapper$0$androidx-browser-trusted-ConnectionHolder  reason: not valid java name */
    public /* synthetic */ Object m879lambda$getServiceWrapper$0$androidxbrowsertrustedConnectionHolder(CallbackToFutureAdapter.Completer completer) throws Exception {
        switch (this.mState) {
            case 0:
                this.mCompleters.add(completer);
                break;
            case 1:
                TrustedWebActivityServiceConnection trustedWebActivityServiceConnection = this.mService;
                if (trustedWebActivityServiceConnection != null) {
                    completer.set(trustedWebActivityServiceConnection);
                    break;
                } else {
                    throw new IllegalStateException("ConnectionHolder state is incorrect.");
                }
            case 2:
                throw new IllegalStateException("Service has been disconnected.");
            case 3:
                throw this.mCancellationException;
            default:
                throw new IllegalStateException("Connection state is invalid");
        }
        return "ConnectionHolder, state = " + this.mState;
    }
}
