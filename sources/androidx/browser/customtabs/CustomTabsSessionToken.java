package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.util.Log;
import androidx.core.app.BundleCompat;

public class CustomTabsSessionToken {
    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    final ICustomTabsCallback mCallbackBinder;
    private final PendingIntent mSessionId;

    static class MockCallback extends ICustomTabsCallback.Stub {
        MockCallback() {
        }

        public void onNavigationEvent(int navigationEvent, Bundle extras) {
        }

        public void extraCallback(String callbackName, Bundle args) {
        }

        public Bundle extraCallbackWithResult(String callbackName, Bundle args) {
            return null;
        }

        public void onMessageChannelReady(Bundle extras) {
        }

        public void onPostMessage(String message, Bundle extras) {
        }

        public void onRelationshipValidationResult(int relation, Uri requestedOrigin, boolean result, Bundle extras) {
        }

        public IBinder asBinder() {
            return this;
        }
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent) {
        Bundle b = intent.getExtras();
        ICustomTabsCallback callback = null;
        if (b == null) {
            return null;
        }
        IBinder binder = BundleCompat.getBinder(b, CustomTabsIntent.EXTRA_SESSION);
        PendingIntent sessionId = (PendingIntent) intent.getParcelableExtra(CustomTabsIntent.EXTRA_SESSION_ID);
        if (binder == null && sessionId == null) {
            return null;
        }
        if (binder != null) {
            callback = ICustomTabsCallback.Stub.asInterface(binder);
        }
        return new CustomTabsSessionToken(callback, sessionId);
    }

    public static CustomTabsSessionToken createMockSessionTokenForTesting() {
        return new CustomTabsSessionToken(new MockCallback(), (PendingIntent) null);
    }

    CustomTabsSessionToken(ICustomTabsCallback callbackBinder, PendingIntent sessionId) {
        if (callbackBinder == null && sessionId == null) {
            throw new IllegalStateException("CustomTabsSessionToken must have either a session id or a callback (or both).");
        }
        this.mCallbackBinder = callbackBinder;
        this.mSessionId = sessionId;
        this.mCallback = callbackBinder == null ? null : new CustomTabsCallback() {
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(navigationEvent, extras);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void extraCallback(String callbackName, Bundle args) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.extraCallback(callbackName, args);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public Bundle extraCallbackWithResult(String callbackName, Bundle args) {
                try {
                    return CustomTabsSessionToken.this.mCallbackBinder.extraCallbackWithResult(callbackName, args);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    return null;
                }
            }

            public void onMessageChannelReady(Bundle extras) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(extras);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onPostMessage(String message, Bundle extras) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(message, extras);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onRelationshipValidationResult(int relation, Uri origin, boolean result, Bundle extras) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(relation, origin, result, extras);
                } catch (RemoteException e) {
                    Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    public IBinder getCallbackBinder() {
        ICustomTabsCallback iCustomTabsCallback = this.mCallbackBinder;
        if (iCustomTabsCallback == null) {
            return null;
        }
        return iCustomTabsCallback.asBinder();
    }

    private IBinder getCallbackBinderAssertNotNull() {
        ICustomTabsCallback iCustomTabsCallback = this.mCallbackBinder;
        if (iCustomTabsCallback != null) {
            return iCustomTabsCallback.asBinder();
        }
        throw new IllegalStateException("CustomTabSessionToken must have valid binder or pending session");
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getId() {
        return this.mSessionId;
    }

    public boolean hasCallback() {
        return this.mCallbackBinder != null;
    }

    public boolean hasId() {
        return this.mSessionId != null;
    }

    public int hashCode() {
        PendingIntent pendingIntent = this.mSessionId;
        if (pendingIntent != null) {
            return pendingIntent.hashCode();
        }
        return getCallbackBinderAssertNotNull().hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof CustomTabsSessionToken)) {
            return false;
        }
        CustomTabsSessionToken other = (CustomTabsSessionToken) o;
        PendingIntent otherSessionId = other.getId();
        PendingIntent pendingIntent = this.mSessionId;
        boolean z = true;
        boolean z2 = pendingIntent == null;
        if (otherSessionId != null) {
            z = false;
        }
        if (z2 != z) {
            return false;
        }
        if (pendingIntent != null) {
            return pendingIntent.equals(otherSessionId);
        }
        return getCallbackBinderAssertNotNull().equals(other.getCallbackBinderAssertNotNull());
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    public boolean isAssociatedWith(CustomTabsSession session) {
        return session.getBinder().equals(this.mCallbackBinder);
    }
}
