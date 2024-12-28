package androidx.browser.trusted;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.customtabs.trusted.ITrustedWebActivityService;
import androidx.browser.trusted.TrustedWebActivityServiceConnection;
import androidx.core.app.NotificationManagerCompat;
import java.util.Locale;

public abstract class TrustedWebActivityService extends Service {
    public static final String ACTION_TRUSTED_WEB_ACTIVITY_SERVICE = "android.support.customtabs.trusted.TRUSTED_WEB_ACTIVITY_SERVICE";
    public static final String KEY_SMALL_ICON_BITMAP = "android.support.customtabs.trusted.SMALL_ICON_BITMAP";
    public static final String KEY_SUCCESS = "androidx.browser.trusted.SUCCESS";
    public static final String META_DATA_NAME_SMALL_ICON = "android.support.customtabs.trusted.SMALL_ICON";
    public static final int SMALL_ICON_NOT_SET = -1;
    private final ITrustedWebActivityService.Stub mBinder = new ITrustedWebActivityService.Stub() {
        public Bundle areNotificationsEnabled(Bundle bundle) {
            checkCaller();
            return new TrustedWebActivityServiceConnection.ResultArgs(TrustedWebActivityService.this.onAreNotificationsEnabled(TrustedWebActivityServiceConnection.NotificationsEnabledArgs.fromBundle(bundle).channelName)).toBundle();
        }

        public Bundle notifyNotificationWithChannel(Bundle bundle) {
            checkCaller();
            TrustedWebActivityServiceConnection.NotifyNotificationArgs args = TrustedWebActivityServiceConnection.NotifyNotificationArgs.fromBundle(bundle);
            return new TrustedWebActivityServiceConnection.ResultArgs(TrustedWebActivityService.this.onNotifyNotificationWithChannel(args.platformTag, args.platformId, args.notification, args.channelName)).toBundle();
        }

        public void cancelNotification(Bundle bundle) {
            checkCaller();
            TrustedWebActivityServiceConnection.CancelNotificationArgs args = TrustedWebActivityServiceConnection.CancelNotificationArgs.fromBundle(bundle);
            TrustedWebActivityService.this.onCancelNotification(args.platformTag, args.platformId);
        }

        public Bundle getActiveNotifications() {
            checkCaller();
            return new TrustedWebActivityServiceConnection.ActiveNotificationsArgs(TrustedWebActivityService.this.onGetActiveNotifications()).toBundle();
        }

        public int getSmallIconId() {
            checkCaller();
            return TrustedWebActivityService.this.onGetSmallIconId();
        }

        public Bundle getSmallIconBitmap() {
            checkCaller();
            return TrustedWebActivityService.this.onGetSmallIconBitmap();
        }

        public Bundle extraCommand(String commandName, Bundle args, IBinder callback) {
            checkCaller();
            return TrustedWebActivityService.this.onExtraCommand(commandName, args, TrustedWebActivityCallbackRemote.fromBinder(callback));
        }

        private void checkCaller() {
            if (TrustedWebActivityService.this.mVerifiedUid == -1) {
                String[] packages = TrustedWebActivityService.this.getPackageManager().getPackagesForUid(getCallingUid());
                int i = 0;
                if (packages == null) {
                    packages = new String[0];
                }
                Token verifiedProvider = TrustedWebActivityService.this.getTokenStore().load();
                PackageManager pm = TrustedWebActivityService.this.getPackageManager();
                if (verifiedProvider != null) {
                    int length = packages.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        } else if (verifiedProvider.matches(packages[i], pm)) {
                            TrustedWebActivityService.this.mVerifiedUid = getCallingUid();
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            if (TrustedWebActivityService.this.mVerifiedUid != getCallingUid()) {
                throw new SecurityException("Caller is not verified as Trusted Web Activity provider.");
            }
        }
    };
    private NotificationManager mNotificationManager;
    int mVerifiedUid = -1;

    public abstract TokenStore getTokenStore();

    public void onCreate() {
        super.onCreate();
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
    }

    public boolean onAreNotificationsEnabled(String channelName) {
        ensureOnCreateCalled();
        if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return true;
        }
        return NotificationApiHelperForO.isChannelEnabled(this.mNotificationManager, channelNameToId(channelName));
    }

    public boolean onNotifyNotificationWithChannel(String platformTag, int platformId, Notification notification, String channelName) {
        ensureOnCreateCalled();
        if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            String channelId = channelNameToId(channelName);
            notification = NotificationApiHelperForO.copyNotificationOntoChannel(this, this.mNotificationManager, notification, channelId, channelName);
            if (!NotificationApiHelperForO.isChannelEnabled(this.mNotificationManager, channelId)) {
                return false;
            }
        }
        this.mNotificationManager.notify(platformTag, platformId, notification);
        return true;
    }

    public void onCancelNotification(String platformTag, int platformId) {
        ensureOnCreateCalled();
        this.mNotificationManager.cancel(platformTag, platformId);
    }

    public Parcelable[] onGetActiveNotifications() {
        ensureOnCreateCalled();
        if (Build.VERSION.SDK_INT >= 23) {
            return NotificationApiHelperForM.getActiveNotifications(this.mNotificationManager);
        }
        throw new IllegalStateException("onGetActiveNotifications cannot be called pre-M.");
    }

    public Bundle onGetSmallIconBitmap() {
        int id = onGetSmallIconId();
        Bundle bundle = new Bundle();
        if (id == -1) {
            return bundle;
        }
        bundle.putParcelable(KEY_SMALL_ICON_BITMAP, BitmapFactory.decodeResource(getResources(), id));
        return bundle;
    }

    public int onGetSmallIconId() {
        try {
            ServiceInfo info = getPackageManager().getServiceInfo(new ComponentName(this, getClass()), 128);
            if (info.metaData == null) {
                return -1;
            }
            return info.metaData.getInt(META_DATA_NAME_SMALL_ICON, -1);
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public final boolean onUnbind(Intent intent) {
        this.mVerifiedUid = -1;
        return super.onUnbind(intent);
    }

    public Bundle onExtraCommand(String commandName, Bundle args, TrustedWebActivityCallbackRemote callbackRemote) {
        return null;
    }

    private static String channelNameToId(String name) {
        return name.toLowerCase(Locale.ROOT).replace(' ', '_') + "_channel_id";
    }

    private void ensureOnCreateCalled() {
        if (this.mNotificationManager == null) {
            throw new IllegalStateException("TrustedWebActivityService has not been properly initialized. Did onCreate() call super.onCreate()?");
        }
    }
}
