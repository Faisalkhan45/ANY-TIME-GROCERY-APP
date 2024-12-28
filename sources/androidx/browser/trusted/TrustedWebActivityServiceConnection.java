package androidx.browser.trusted;

import android.app.Notification;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;
import android.support.customtabs.trusted.ITrustedWebActivityService;

public final class TrustedWebActivityServiceConnection {
    private static final String KEY_ACTIVE_NOTIFICATIONS = "android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS";
    private static final String KEY_CHANNEL_NAME = "android.support.customtabs.trusted.CHANNEL_NAME";
    private static final String KEY_NOTIFICATION = "android.support.customtabs.trusted.NOTIFICATION";
    private static final String KEY_NOTIFICATION_SUCCESS = "android.support.customtabs.trusted.NOTIFICATION_SUCCESS";
    private static final String KEY_PLATFORM_ID = "android.support.customtabs.trusted.PLATFORM_ID";
    private static final String KEY_PLATFORM_TAG = "android.support.customtabs.trusted.PLATFORM_TAG";
    private final ComponentName mComponentName;
    private final ITrustedWebActivityService mService;

    TrustedWebActivityServiceConnection(ITrustedWebActivityService service, ComponentName componentName) {
        this.mService = service;
        this.mComponentName = componentName;
    }

    public boolean areNotificationsEnabled(String channelName) throws RemoteException {
        return ResultArgs.fromBundle(this.mService.areNotificationsEnabled(new NotificationsEnabledArgs(channelName).toBundle())).success;
    }

    public boolean notify(String platformTag, int platformId, Notification notification, String channel) throws RemoteException {
        return ResultArgs.fromBundle(this.mService.notifyNotificationWithChannel(new NotifyNotificationArgs(platformTag, platformId, notification, channel).toBundle())).success;
    }

    public void cancel(String platformTag, int platformId) throws RemoteException {
        this.mService.cancelNotification(new CancelNotificationArgs(platformTag, platformId).toBundle());
    }

    public Parcelable[] getActiveNotifications() throws RemoteException {
        return ActiveNotificationsArgs.fromBundle(this.mService.getActiveNotifications()).notifications;
    }

    public int getSmallIconId() throws RemoteException {
        return this.mService.getSmallIconId();
    }

    public Bitmap getSmallIconBitmap() throws RemoteException {
        return (Bitmap) this.mService.getSmallIconBitmap().getParcelable(TrustedWebActivityService.KEY_SMALL_ICON_BITMAP);
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public Bundle sendExtraCommand(String commandName, Bundle args, TrustedWebActivityCallback callback) throws RemoteException {
        ITrustedWebActivityCallback callbackBinder = wrapCallback(callback);
        return this.mService.extraCommand(commandName, args, callbackBinder == null ? null : callbackBinder.asBinder());
    }

    static class NotifyNotificationArgs {
        public final String channelName;
        public final Notification notification;
        public final int platformId;
        public final String platformTag;

        NotifyNotificationArgs(String platformTag2, int platformId2, Notification notification2, String channelName2) {
            this.platformTag = platformTag2;
            this.platformId = platformId2;
            this.notification = notification2;
            this.channelName = channelName2;
        }

        public static NotifyNotificationArgs fromBundle(Bundle bundle) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG);
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_PLATFORM_ID);
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_NOTIFICATION);
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME);
            return new NotifyNotificationArgs(bundle.getString(TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG), bundle.getInt(TrustedWebActivityServiceConnection.KEY_PLATFORM_ID), (Notification) bundle.getParcelable(TrustedWebActivityServiceConnection.KEY_NOTIFICATION), bundle.getString(TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME));
        }

        public Bundle toBundle() {
            Bundle args = new Bundle();
            args.putString(TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG, this.platformTag);
            args.putInt(TrustedWebActivityServiceConnection.KEY_PLATFORM_ID, this.platformId);
            args.putParcelable(TrustedWebActivityServiceConnection.KEY_NOTIFICATION, this.notification);
            args.putString(TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME, this.channelName);
            return args;
        }
    }

    static class CancelNotificationArgs {
        public final int platformId;
        public final String platformTag;

        CancelNotificationArgs(String platformTag2, int platformId2) {
            this.platformTag = platformTag2;
            this.platformId = platformId2;
        }

        public static CancelNotificationArgs fromBundle(Bundle bundle) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG);
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_PLATFORM_ID);
            return new CancelNotificationArgs(bundle.getString(TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG), bundle.getInt(TrustedWebActivityServiceConnection.KEY_PLATFORM_ID));
        }

        public Bundle toBundle() {
            Bundle args = new Bundle();
            args.putString(TrustedWebActivityServiceConnection.KEY_PLATFORM_TAG, this.platformTag);
            args.putInt(TrustedWebActivityServiceConnection.KEY_PLATFORM_ID, this.platformId);
            return args;
        }
    }

    static class ResultArgs {
        public final boolean success;

        ResultArgs(boolean success2) {
            this.success = success2;
        }

        public static ResultArgs fromBundle(Bundle bundle) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_NOTIFICATION_SUCCESS);
            return new ResultArgs(bundle.getBoolean(TrustedWebActivityServiceConnection.KEY_NOTIFICATION_SUCCESS));
        }

        public Bundle toBundle() {
            Bundle args = new Bundle();
            args.putBoolean(TrustedWebActivityServiceConnection.KEY_NOTIFICATION_SUCCESS, this.success);
            return args;
        }
    }

    static class ActiveNotificationsArgs {
        public final Parcelable[] notifications;

        ActiveNotificationsArgs(Parcelable[] notifications2) {
            this.notifications = notifications2;
        }

        public static ActiveNotificationsArgs fromBundle(Bundle bundle) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_ACTIVE_NOTIFICATIONS);
            return new ActiveNotificationsArgs(bundle.getParcelableArray(TrustedWebActivityServiceConnection.KEY_ACTIVE_NOTIFICATIONS));
        }

        public Bundle toBundle() {
            Bundle args = new Bundle();
            args.putParcelableArray(TrustedWebActivityServiceConnection.KEY_ACTIVE_NOTIFICATIONS, this.notifications);
            return args;
        }
    }

    static class NotificationsEnabledArgs {
        public final String channelName;

        NotificationsEnabledArgs(String channelName2) {
            this.channelName = channelName2;
        }

        public static NotificationsEnabledArgs fromBundle(Bundle bundle) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle, TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME);
            return new NotificationsEnabledArgs(bundle.getString(TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME));
        }

        public Bundle toBundle() {
            Bundle args = new Bundle();
            args.putString(TrustedWebActivityServiceConnection.KEY_CHANNEL_NAME, this.channelName);
            return args;
        }
    }

    static void ensureBundleContains(Bundle args, String key) {
        if (!args.containsKey(key)) {
            throw new IllegalArgumentException("Bundle must contain " + key);
        }
    }

    private static ITrustedWebActivityCallback wrapCallback(final TrustedWebActivityCallback callback) {
        if (callback == null) {
            return null;
        }
        return new ITrustedWebActivityCallback.Stub() {
            public void onExtraCallback(String callbackName, Bundle args) throws RemoteException {
                TrustedWebActivityCallback.this.onExtraCallback(callbackName, args);
            }
        };
    }
}
