package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import androidx.collection.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String CATEGORY_COLOR_SCHEME_CUSTOMIZATION = "androidx.browser.customtabs.category.ColorSchemeCustomization";
    public static final String CATEGORY_NAVBAR_COLOR_CUSTOMIZATION = "androidx.browser.customtabs.category.NavBarColorCustomization";
    public static final String CATEGORY_TRUSTED_WEB_ACTIVITY_IMMERSIVE_MODE = "androidx.browser.trusted.category.ImmersiveMode";
    public static final String CATEGORY_WEB_SHARE_TARGET_V2 = "androidx.browser.trusted.category.WebShareTargetV2";
    public static final int FILE_PURPOSE_TRUSTED_WEB_ACTIVITY_SPLASH_IMAGE = 1;
    public static final String KEY_SUCCESS = "androidx.browser.customtabs.SUCCESS";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RELATION_HANDLE_ALL_URLS = 2;
    public static final int RELATION_USE_AS_ORIGIN = 1;
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS = 0;
    public static final String TRUSTED_WEB_ACTIVITY_CATEGORY = "androidx.browser.trusted.category.TrustedWebActivities";
    private ICustomTabsService.Stub mBinder = new ICustomTabsService.Stub() {
        public boolean warmup(long flags) {
            return CustomTabsService.this.warmup(flags);
        }

        public boolean newSession(ICustomTabsCallback callback) {
            return newSessionInternal(callback, (PendingIntent) null);
        }

        public boolean newSessionWithExtras(ICustomTabsCallback callback, Bundle extras) {
            return newSessionInternal(callback, getSessionIdFromBundle(extras));
        }

        /* Debug info: failed to restart local var, previous not found, register: 6 */
        private boolean newSessionInternal(ICustomTabsCallback callback, PendingIntent sessionId) {
            CustomTabsSessionToken sessionToken = new CustomTabsSessionToken(callback, sessionId);
            try {
                IBinder.DeathRecipient deathRecipient = new CustomTabsService$1$$ExternalSyntheticLambda0(this, sessionToken);
                synchronized (CustomTabsService.this.mDeathRecipientMap) {
                    callback.asBinder().linkToDeath(deathRecipient, 0);
                    CustomTabsService.this.mDeathRecipientMap.put(callback.asBinder(), deathRecipient);
                }
                return CustomTabsService.this.newSession(sessionToken);
            } catch (RemoteException e) {
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$newSessionInternal$0$androidx-browser-customtabs-CustomTabsService$1  reason: not valid java name */
        public /* synthetic */ void m878lambda$newSessionInternal$0$androidxbrowsercustomtabsCustomTabsService$1(CustomTabsSessionToken sessionToken) {
            CustomTabsService.this.cleanUpSession(sessionToken);
        }

        public boolean mayLaunchUrl(ICustomTabsCallback callback, Uri url, Bundle extras, List<Bundle> otherLikelyBundles) {
            return CustomTabsService.this.mayLaunchUrl(new CustomTabsSessionToken(callback, getSessionIdFromBundle(extras)), url, extras, otherLikelyBundles);
        }

        public Bundle extraCommand(String commandName, Bundle args) {
            return CustomTabsService.this.extraCommand(commandName, args);
        }

        public boolean updateVisuals(ICustomTabsCallback callback, Bundle bundle) {
            return CustomTabsService.this.updateVisuals(new CustomTabsSessionToken(callback, getSessionIdFromBundle(bundle)), bundle);
        }

        public boolean requestPostMessageChannel(ICustomTabsCallback callback, Uri postMessageOrigin) {
            return CustomTabsService.this.requestPostMessageChannel(new CustomTabsSessionToken(callback, (PendingIntent) null), postMessageOrigin);
        }

        public boolean requestPostMessageChannelWithExtras(ICustomTabsCallback callback, Uri postMessageOrigin, Bundle extras) {
            return CustomTabsService.this.requestPostMessageChannel(new CustomTabsSessionToken(callback, getSessionIdFromBundle(extras)), postMessageOrigin);
        }

        public int postMessage(ICustomTabsCallback callback, String message, Bundle extras) {
            return CustomTabsService.this.postMessage(new CustomTabsSessionToken(callback, getSessionIdFromBundle(extras)), message, extras);
        }

        public boolean validateRelationship(ICustomTabsCallback callback, int relation, Uri origin, Bundle extras) {
            return CustomTabsService.this.validateRelationship(new CustomTabsSessionToken(callback, getSessionIdFromBundle(extras)), relation, origin, extras);
        }

        public boolean receiveFile(ICustomTabsCallback callback, Uri uri, int purpose, Bundle extras) {
            return CustomTabsService.this.receiveFile(new CustomTabsSessionToken(callback, getSessionIdFromBundle(extras)), uri, purpose, extras);
        }

        private PendingIntent getSessionIdFromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            PendingIntent sessionId = (PendingIntent) bundle.getParcelable(CustomTabsIntent.EXTRA_SESSION_ID);
            bundle.remove(CustomTabsIntent.EXTRA_SESSION_ID);
            return sessionId;
        }
    };
    final SimpleArrayMap<IBinder, IBinder.DeathRecipient> mDeathRecipientMap = new SimpleArrayMap<>();

    @Retention(RetentionPolicy.SOURCE)
    public @interface FilePurpose {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Relation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    /* access modifiers changed from: protected */
    public abstract Bundle extraCommand(String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean mayLaunchUrl(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list);

    /* access modifiers changed from: protected */
    public abstract boolean newSession(CustomTabsSessionToken customTabsSessionToken);

    /* access modifiers changed from: protected */
    public abstract int postMessage(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean receiveFile(CustomTabsSessionToken customTabsSessionToken, Uri uri, int i, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean requestPostMessageChannel(CustomTabsSessionToken customTabsSessionToken, Uri uri);

    /* access modifiers changed from: protected */
    public abstract boolean updateVisuals(CustomTabsSessionToken customTabsSessionToken, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean validateRelationship(CustomTabsSessionToken customTabsSessionToken, int i, Uri uri, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean warmup(long j);

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    /* access modifiers changed from: protected */
    public boolean cleanUpSession(CustomTabsSessionToken sessionToken) {
        try {
            synchronized (this.mDeathRecipientMap) {
                IBinder binder = sessionToken.getCallbackBinder();
                if (binder == null) {
                    return false;
                }
                binder.unlinkToDeath(this.mDeathRecipientMap.get(binder), 0);
                this.mDeathRecipientMap.remove(binder);
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
