package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaControllerCompatApi21;
import android.support.v4.media.session.MediaControllerCompatApi23;
import android.support.v4.media.session.MediaControllerCompatApi24;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.core.app.BundleCompat;
import androidx.core.app.ComponentActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class MediaControllerCompat {
    public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final HashSet<Callback> mRegisteredCallbacks = new HashSet<>();
    private final MediaSessionCompat.Token mToken;

    interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i);

        void adjustVolume(int i, int i2);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List<MediaSessionCompat.QueueItem> getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i, int i2);

        void unregisterCallback(Callback callback);
    }

    private static class MediaControllerExtraData extends ComponentActivity.ExtraData {
        private final MediaControllerCompat mMediaController;

        MediaControllerExtraData(MediaControllerCompat mediaController) {
            this.mMediaController = mediaController;
        }

        /* access modifiers changed from: package-private */
        public MediaControllerCompat getMediaController() {
            return this.mMediaController;
        }
    }

    public static void setMediaController(Activity activity, MediaControllerCompat mediaController) {
        if (activity instanceof ComponentActivity) {
            ((ComponentActivity) activity).putExtraData(new MediaControllerExtraData(mediaController));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Object controllerObj = null;
            if (mediaController != null) {
                controllerObj = MediaControllerCompatApi21.fromToken(activity, mediaController.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity, controllerObj);
        }
    }

    public static MediaControllerCompat getMediaController(Activity activity) {
        Object controllerObj;
        if (activity instanceof ComponentActivity) {
            MediaControllerExtraData extraData = (MediaControllerExtraData) ((ComponentActivity) activity).getExtraData(MediaControllerExtraData.class);
            if (extraData != null) {
                return extraData.getMediaController();
            }
            return null;
        } else if (Build.VERSION.SDK_INT < 21 || (controllerObj = MediaControllerCompatApi21.getMediaController(activity)) == null) {
            return null;
        } else {
            try {
                return new MediaControllerCompat((Context) activity, MediaSessionCompat.Token.fromToken(MediaControllerCompatApi21.getSessionToken(controllerObj)));
            } catch (RemoteException e) {
                Log.e(TAG, "Dead object in getMediaController.", e);
                return null;
            }
        }
    }

    static void validateCustomAction(String action, Bundle args) {
        if (action != null) {
            char c = 65535;
            switch (action.hashCode()) {
                case -1348483723:
                    if (action.equals(MediaSessionCompat.ACTION_FOLLOW)) {
                        c = 0;
                        break;
                    }
                    break;
                case 503011406:
                    if (action.equals(MediaSessionCompat.ACTION_UNFOLLOW)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    if (args == null || !args.containsKey(MediaSessionCompat.ARGUMENT_MEDIA_ATTRIBUTE)) {
                        throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + action + ".");
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat session) {
        if (session != null) {
            MediaSessionCompat.Token sessionToken = session.getSessionToken();
            this.mToken = sessionToken;
            MediaControllerImpl impl = null;
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    impl = new MediaControllerImplApi24(context, sessionToken);
                } else if (Build.VERSION.SDK_INT >= 23) {
                    impl = new MediaControllerImplApi23(context, sessionToken);
                } else if (Build.VERSION.SDK_INT >= 21) {
                    impl = new MediaControllerImplApi21(context, sessionToken);
                } else {
                    impl = new MediaControllerImplBase(sessionToken);
                }
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to create MediaControllerImpl.", e);
            }
            this.mImpl = impl;
            return;
        }
        throw new IllegalArgumentException("session must not be null");
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
        if (sessionToken != null) {
            this.mToken = sessionToken;
            if (Build.VERSION.SDK_INT >= 24) {
                this.mImpl = new MediaControllerImplApi24(context, sessionToken);
            } else if (Build.VERSION.SDK_INT >= 23) {
                this.mImpl = new MediaControllerImplApi23(context, sessionToken);
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.mImpl = new MediaControllerImplApi21(context, sessionToken);
            } else {
                this.mImpl = new MediaControllerImplBase(sessionToken);
            }
        } else {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.mImpl.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.mImpl.getQueue();
    }

    public void addQueueItem(MediaDescriptionCompat description) {
        this.mImpl.addQueueItem(description);
    }

    public void addQueueItem(MediaDescriptionCompat description, int index) {
        this.mImpl.addQueueItem(description, index);
    }

    public void removeQueueItem(MediaDescriptionCompat description) {
        this.mImpl.removeQueueItem(description);
    }

    @Deprecated
    public void removeQueueItemAt(int index) {
        MediaSessionCompat.QueueItem item;
        List<MediaSessionCompat.QueueItem> queue = getQueue();
        if (queue != null && index >= 0 && index < queue.size() && (item = queue.get(index)) != null) {
            removeQueueItem(item.getDescription());
        }
    }

    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public boolean isCaptioningEnabled() {
        return this.mImpl.isCaptioningEnabled();
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public long getFlags() {
        return this.mImpl.getFlags();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    public Bundle getSessionToken2Bundle() {
        return this.mToken.getSessionToken2Bundle();
    }

    public void setVolumeTo(int value, int flags) {
        this.mImpl.setVolumeTo(value, flags);
    }

    public void adjustVolume(int direction, int flags) {
        this.mImpl.adjustVolume(direction, flags);
    }

    public void registerCallback(Callback callback) {
        registerCallback(callback, (Handler) null);
    }

    public void registerCallback(Callback callback, Handler handler) {
        if (callback != null) {
            if (handler == null) {
                handler = new Handler();
            }
            callback.setHandler(handler);
            this.mImpl.registerCallback(callback, handler);
            this.mRegisteredCallbacks.add(callback);
            return;
        }
        throw new IllegalArgumentException("callback must not be null");
    }

    public void unregisterCallback(Callback callback) {
        if (callback != null) {
            try {
                this.mRegisteredCallbacks.remove(callback);
                this.mImpl.unregisterCallback(callback);
            } finally {
                callback.setHandler((Handler) null);
            }
        } else {
            throw new IllegalArgumentException("callback must not be null");
        }
    }

    public void sendCommand(String command, Bundle params, ResultReceiver cb) {
        if (!TextUtils.isEmpty(command)) {
            this.mImpl.sendCommand(command, params, cb);
            return;
        }
        throw new IllegalArgumentException("command must neither be null nor empty");
    }

    public boolean isSessionReady() {
        return this.mImpl.isSessionReady();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }

    public static abstract class Callback implements IBinder.DeathRecipient {
        final Object mCallbackObj;
        MessageHandler mHandler;
        IMediaControllerCallback mIControllerCallback;

        public Callback() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(this));
                return;
            }
            StubCompat stubCompat = new StubCompat(this);
            this.mIControllerCallback = stubCompat;
            this.mCallbackObj = stubCompat;
        }

        public void onSessionReady() {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String event, Bundle extras) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat state) {
        }

        public void onMetadataChanged(MediaMetadataCompat metadata) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence title) {
        }

        public void onExtrasChanged(Bundle extras) {
        }

        public void onAudioInfoChanged(PlaybackInfo info) {
        }

        public void onCaptioningEnabledChanged(boolean enabled) {
        }

        public void onRepeatModeChanged(int repeatMode) {
        }

        public void onShuffleModeChanged(int shuffleMode) {
        }

        public IMediaControllerCallback getIControllerCallback() {
            return this.mIControllerCallback;
        }

        public void binderDied() {
            postToHandler(8, (Object) null, (Bundle) null);
        }

        /* access modifiers changed from: package-private */
        public void setHandler(Handler handler) {
            if (handler == null) {
                MessageHandler messageHandler = this.mHandler;
                if (messageHandler != null) {
                    messageHandler.mRegistered = false;
                    this.mHandler.removeCallbacksAndMessages((Object) null);
                    this.mHandler = null;
                    return;
                }
                return;
            }
            MessageHandler messageHandler2 = new MessageHandler(handler.getLooper());
            this.mHandler = messageHandler2;
            messageHandler2.mRegistered = true;
        }

        /* access modifiers changed from: package-private */
        public void postToHandler(int what, Object obj, Bundle data) {
            MessageHandler messageHandler = this.mHandler;
            if (messageHandler != null) {
                Message msg = messageHandler.obtainMessage(what, obj);
                msg.setData(data);
                msg.sendToTarget();
            }
        }

        private static class StubApi21 implements MediaControllerCompatApi21.Callback {
            private final WeakReference<Callback> mCallback;

            StubApi21(Callback callback) {
                this.mCallback = new WeakReference<>(callback);
            }

            public void onSessionDestroyed() {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            public void onSessionEvent(String event, Bundle extras) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback == null) {
                    return;
                }
                if (callback.mIControllerCallback == null || Build.VERSION.SDK_INT >= 23) {
                    callback.onSessionEvent(event, extras);
                }
            }

            public void onPlaybackStateChanged(Object stateObj) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null && callback.mIControllerCallback == null) {
                    callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(stateObj));
                }
            }

            public void onMetadataChanged(Object metadataObj) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(metadataObj));
                }
            }

            public void onQueueChanged(List<?> queue) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(queue));
                }
            }

            public void onQueueTitleChanged(CharSequence title) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(title);
                }
            }

            public void onExtrasChanged(Bundle extras) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onExtrasChanged(extras);
                }
            }

            public void onAudioInfoChanged(int type, int stream, int control, int max, int current) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onAudioInfoChanged(new PlaybackInfo(type, stream, control, max, current));
                }
            }
        }

        private static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> mCallback;

            StubCompat(Callback callback) {
                this.mCallback = new WeakReference<>(callback);
            }

            public void onEvent(String event, Bundle extras) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(1, event, extras);
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(8, (Object) null, (Bundle) null);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(2, state, (Bundle) null);
                }
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(3, metadata, (Bundle) null);
                }
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(5, queue, (Bundle) null);
                }
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(6, title, (Bundle) null);
                }
            }

            public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(enabled), (Bundle) null);
                }
            }

            public void onRepeatModeChanged(int repeatMode) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(repeatMode), (Bundle) null);
                }
            }

            public void onShuffleModeChangedRemoved(boolean enabled) throws RemoteException {
            }

            public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(shuffleMode), (Bundle) null);
                }
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(7, extras, (Bundle) null);
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    PlaybackInfo pi = null;
                    if (info != null) {
                        pi = new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
                    }
                    callback.postToHandler(4, pi, (Bundle) null);
                }
            }

            public void onSessionReady() throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(13, (Object) null, (Bundle) null);
                }
            }
        }

        private class MessageHandler extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_SESSION_READY = 13;
            private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_REPEAT_MODE = 9;
            private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
            private static final int MSG_UPDATE_VOLUME = 4;
            boolean mRegistered = false;

            MessageHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message msg) {
                if (this.mRegistered) {
                    switch (msg.what) {
                        case 1:
                            Bundle extras = msg.getData();
                            MediaSessionCompat.ensureClassLoader(extras);
                            Callback.this.onSessionEvent((String) msg.obj, extras);
                            return;
                        case 2:
                            Callback.this.onPlaybackStateChanged((PlaybackStateCompat) msg.obj);
                            return;
                        case 3:
                            Callback.this.onMetadataChanged((MediaMetadataCompat) msg.obj);
                            return;
                        case 4:
                            Callback.this.onAudioInfoChanged((PlaybackInfo) msg.obj);
                            return;
                        case 5:
                            Callback.this.onQueueChanged((List) msg.obj);
                            return;
                        case 6:
                            Callback.this.onQueueTitleChanged((CharSequence) msg.obj);
                            return;
                        case 7:
                            Bundle extras2 = (Bundle) msg.obj;
                            MediaSessionCompat.ensureClassLoader(extras2);
                            Callback.this.onExtrasChanged(extras2);
                            return;
                        case 8:
                            Callback.this.onSessionDestroyed();
                            return;
                        case 9:
                            Callback.this.onRepeatModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 11:
                            Callback.this.onCaptioningEnabledChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        case 12:
                            Callback.this.onShuffleModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 13:
                            Callback.this.onSessionReady();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    public static abstract class TransportControls {
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public abstract void setCaptioningEnabled(boolean z);

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j);

        public abstract void stop();

        TransportControls() {
        }
    }

    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int type, int stream, int control, int max, int current) {
            this.mPlaybackType = type;
            this.mAudioStream = stream;
            this.mVolumeControl = control;
            this.mMaxVolume = max;
            this.mCurrentVolume = current;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getAudioStream() {
            return this.mAudioStream;
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
    }

    static class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;
        private TransportControls mTransportControls;

        public MediaControllerImplBase(MediaSessionCompat.Token token) {
            this.mBinder = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }

        public void registerCallback(Callback callback, Handler handler) {
            if (callback != null) {
                try {
                    this.mBinder.asBinder().linkToDeath(callback, 0);
                    this.mBinder.registerCallbackListener((IMediaControllerCallback) callback.mCallbackObj);
                    callback.postToHandler(13, (Object) null, (Bundle) null);
                } catch (RemoteException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                    callback.postToHandler(8, (Object) null, (Bundle) null);
                }
            } else {
                throw new IllegalArgumentException("callback may not be null.");
            }
        }

        public void unregisterCallback(Callback callback) {
            if (callback != null) {
                try {
                    this.mBinder.unregisterCallbackListener((IMediaControllerCallback) callback.mCallbackObj);
                    this.mBinder.asBinder().unlinkToDeath(callback, 0);
                } catch (RemoteException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e);
                }
            } else {
                throw new IllegalArgumentException("callback may not be null.");
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent event) {
            if (event != null) {
                try {
                    this.mBinder.sendMediaButton(event);
                    return false;
                } catch (RemoteException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in dispatchMediaButtonEvent.", e);
                    return false;
                }
            } else {
                throw new IllegalArgumentException("event may not be null.");
            }
        }

        public TransportControls getTransportControls() {
            if (this.mTransportControls == null) {
                this.mTransportControls = new TransportControlsBase(this.mBinder);
            }
            return this.mTransportControls;
        }

        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.mBinder.getPlaybackState();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        public MediaMetadataCompat getMetadata() {
            try {
                return this.mBinder.getMetadata();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getMetadata.", e);
                return null;
            }
        }

        public List<MediaSessionCompat.QueueItem> getQueue() {
            try {
                return this.mBinder.getQueue();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getQueue.", e);
                return null;
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 6 */
        public void addQueueItem(MediaDescriptionCompat description) {
            try {
                if ((4 & this.mBinder.getFlags()) != 0) {
                    this.mBinder.addQueueItem(description);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in addQueueItem.", e);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 6 */
        public void addQueueItem(MediaDescriptionCompat description, int index) {
            try {
                if ((4 & this.mBinder.getFlags()) != 0) {
                    this.mBinder.addQueueItemAt(description, index);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in addQueueItemAt.", e);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 6 */
        public void removeQueueItem(MediaDescriptionCompat description) {
            try {
                if ((4 & this.mBinder.getFlags()) != 0) {
                    this.mBinder.removeQueueItem(description);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in removeQueueItem.", e);
            }
        }

        public CharSequence getQueueTitle() {
            try {
                return this.mBinder.getQueueTitle();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getQueueTitle.", e);
                return null;
            }
        }

        public Bundle getExtras() {
            try {
                return this.mBinder.getExtras();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getExtras.", e);
                return null;
            }
        }

        public int getRatingType() {
            try {
                return this.mBinder.getRatingType();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getRatingType.", e);
                return 0;
            }
        }

        public boolean isCaptioningEnabled() {
            try {
                return this.mBinder.isCaptioningEnabled();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            try {
                return this.mBinder.getRepeatMode();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            try {
                return this.mBinder.getShuffleMode();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            try {
                return this.mBinder.getFlags();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getFlags.", e);
                return 0;
            }
        }

        public PlaybackInfo getPlaybackInfo() {
            try {
                ParcelableVolumeInfo info = this.mBinder.getVolumeAttributes();
                return new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackInfo.", e);
                return null;
            }
        }

        public PendingIntent getSessionActivity() {
            try {
                return this.mBinder.getLaunchPendingIntent();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        public void setVolumeTo(int value, int flags) {
            try {
                this.mBinder.setVolumeTo(value, flags, (String) null);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setVolumeTo.", e);
            }
        }

        public void adjustVolume(int direction, int flags) {
            try {
                this.mBinder.adjustVolume(direction, flags, (String) null);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in adjustVolume.", e);
            }
        }

        public void sendCommand(String command, Bundle params, ResultReceiver cb) {
            try {
                this.mBinder.sendCommand(command, params, new MediaSessionCompat.ResultReceiverWrapper(cb));
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in sendCommand.", e);
            }
        }

        public boolean isSessionReady() {
            return true;
        }

        public String getPackageName() {
            try {
                return this.mBinder.getPackageName();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getPackageName.", e);
                return null;
            }
        }

        public Object getMediaController() {
            return null;
        }
    }

    static class TransportControlsBase extends TransportControls {
        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession binder) {
            this.mBinder = binder;
        }

        public void prepare() {
            try {
                this.mBinder.prepare();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in prepare.", e);
            }
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            try {
                this.mBinder.prepareFromMediaId(mediaId, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromMediaId.", e);
            }
        }

        public void prepareFromSearch(String query, Bundle extras) {
            try {
                this.mBinder.prepareFromSearch(query, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromSearch.", e);
            }
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            try {
                this.mBinder.prepareFromUri(uri, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromUri.", e);
            }
        }

        public void play() {
            try {
                this.mBinder.play();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in play.", e);
            }
        }

        public void playFromMediaId(String mediaId, Bundle extras) {
            try {
                this.mBinder.playFromMediaId(mediaId, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in playFromMediaId.", e);
            }
        }

        public void playFromSearch(String query, Bundle extras) {
            try {
                this.mBinder.playFromSearch(query, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in playFromSearch.", e);
            }
        }

        public void playFromUri(Uri uri, Bundle extras) {
            try {
                this.mBinder.playFromUri(uri, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in playFromUri.", e);
            }
        }

        public void skipToQueueItem(long id) {
            try {
                this.mBinder.skipToQueueItem(id);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in skipToQueueItem.", e);
            }
        }

        public void pause() {
            try {
                this.mBinder.pause();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in pause.", e);
            }
        }

        public void stop() {
            try {
                this.mBinder.stop();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in stop.", e);
            }
        }

        public void seekTo(long pos) {
            try {
                this.mBinder.seekTo(pos);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in seekTo.", e);
            }
        }

        public void fastForward() {
            try {
                this.mBinder.fastForward();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in fastForward.", e);
            }
        }

        public void skipToNext() {
            try {
                this.mBinder.next();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in skipToNext.", e);
            }
        }

        public void rewind() {
            try {
                this.mBinder.rewind();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in rewind.", e);
            }
        }

        public void skipToPrevious() {
            try {
                this.mBinder.previous();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in skipToPrevious.", e);
            }
        }

        public void setRating(RatingCompat rating) {
            try {
                this.mBinder.rate(rating);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setRating.", e);
            }
        }

        public void setRating(RatingCompat rating, Bundle extras) {
            try {
                this.mBinder.rateWithExtras(rating, extras);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setRating.", e);
            }
        }

        public void setCaptioningEnabled(boolean enabled) {
            try {
                this.mBinder.setCaptioningEnabled(enabled);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setCaptioningEnabled.", e);
            }
        }

        public void setRepeatMode(int repeatMode) {
            try {
                this.mBinder.setRepeatMode(repeatMode);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setRepeatMode.", e);
            }
        }

        public void setShuffleMode(int shuffleMode) {
            try {
                this.mBinder.setShuffleMode(shuffleMode);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in setShuffleMode.", e);
            }
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle args) {
            sendCustomAction(customAction.getAction(), args);
        }

        public void sendCustomAction(String action, Bundle args) {
            MediaControllerCompat.validateCustomAction(action, args);
            try {
                this.mBinder.sendCustomAction(action, args);
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in sendCustomAction.", e);
            }
        }
    }

    static class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap<Callback, ExtraCallback> mCallbackMap = new HashMap<>();
        protected final Object mControllerObj;
        final Object mLock = new Object();
        private final List<Callback> mPendingCallbacks = new ArrayList();
        final MediaSessionCompat.Token mSessionToken;

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            this.mSessionToken = sessionToken;
            Object fromToken = MediaControllerCompatApi21.fromToken(context, sessionToken.getToken());
            this.mControllerObj = fromToken;
            if (fromToken == null) {
                throw new RemoteException();
            } else if (sessionToken.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        public final void registerCallback(Callback callback, Handler handler) {
            MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.mCallbackMap.put(callback, extraCallback);
                    callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        callback.postToHandler(13, (Object) null, (Bundle) null);
                    } catch (RemoteException e) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                    }
                } else {
                    callback.mIControllerCallback = null;
                    this.mPendingCallbacks.add(callback);
                }
            }
        }

        public final void unregisterCallback(Callback callback) {
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    try {
                        ExtraCallback extraCallback = this.mCallbackMap.remove(callback);
                        if (extraCallback != null) {
                            callback.mIControllerCallback = null;
                            this.mSessionToken.getExtraBinder().unregisterCallbackListener(extraCallback);
                        }
                    } catch (RemoteException e) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e);
                    }
                } else {
                    this.mPendingCallbacks.remove(callback);
                }
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent event) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, event);
        }

        public TransportControls getTransportControls() {
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                return new TransportControlsApi21(controlsObj);
            }
            return null;
        }

        public PlaybackStateCompat getPlaybackState() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getPlaybackState();
                } catch (RemoteException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e);
                }
            }
            Object stateObj = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            if (stateObj != null) {
                return PlaybackStateCompat.fromPlaybackState(stateObj);
            }
            return null;
        }

        public MediaMetadataCompat getMetadata() {
            Object metadataObj = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
            if (metadataObj != null) {
                return MediaMetadataCompat.fromMediaMetadata(metadataObj);
            }
            return null;
        }

        public List<MediaSessionCompat.QueueItem> getQueue() {
            List<Object> queueObjs = MediaControllerCompatApi21.getQueue(this.mControllerObj);
            if (queueObjs != null) {
                return MediaSessionCompat.QueueItem.fromQueueItemList(queueObjs);
            }
            return null;
        }

        public void addQueueItem(MediaDescriptionCompat description) {
            if ((4 & getFlags()) != 0) {
                Bundle params = new Bundle();
                params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
                sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM, params, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public void addQueueItem(MediaDescriptionCompat description, int index) {
            if ((4 & getFlags()) != 0) {
                Bundle params = new Bundle();
                params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
                params.putInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, index);
                sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT, params, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public void removeQueueItem(MediaDescriptionCompat description) {
            if ((4 & getFlags()) != 0) {
                Bundle params = new Bundle();
                params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
                sendCommand(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM, params, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
        }

        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.mControllerObj);
        }

        public int getRatingType() {
            if (Build.VERSION.SDK_INT < 22 && this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getRatingType();
                } catch (RemoteException e) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getRatingType.", e);
                }
            }
            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }

        public boolean isCaptioningEnabled() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return false;
            }
            try {
                return this.mSessionToken.getExtraBinder().isCaptioningEnabled();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getRepeatMode();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getShuffleMode();
            } catch (RemoteException e) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.mControllerObj);
        }

        public PlaybackInfo getPlaybackInfo() {
            Object volumeInfoObj = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            if (volumeInfoObj != null) {
                return new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(volumeInfoObj));
            }
            return null;
        }

        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
        }

        public void setVolumeTo(int value, int flags) {
            MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, value, flags);
        }

        public void adjustVolume(int direction, int flags) {
            MediaControllerCompatApi21.adjustVolume(this.mControllerObj, direction, flags);
        }

        public void sendCommand(String command, Bundle params, ResultReceiver cb) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, command, params, cb);
        }

        public boolean isSessionReady() {
            return this.mSessionToken.getExtraBinder() != null;
        }

        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
        }

        public Object getMediaController() {
            return this.mControllerObj;
        }

        private void requestExtraBinder() {
            sendCommand(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER, (Bundle) null, new ExtraBinderRequestResultReceiver(this));
        }

        /* access modifiers changed from: package-private */
        public void processPendingCallbacksLocked() {
            if (this.mSessionToken.getExtraBinder() != null) {
                for (Callback callback : this.mPendingCallbacks) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.mCallbackMap.put(callback, extraCallback);
                    callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        callback.postToHandler(13, (Object) null, (Bundle) null);
                    } catch (RemoteException e) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                    }
                }
                this.mPendingCallbacks.clear();
            }
        }

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImpl) {
                super((Handler) null);
                this.mMediaControllerImpl = new WeakReference<>(mediaControllerImpl);
            }

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                MediaControllerImplApi21 mediaControllerImpl = (MediaControllerImplApi21) this.mMediaControllerImpl.get();
                if (mediaControllerImpl != null && resultData != null) {
                    synchronized (mediaControllerImpl.mLock) {
                        mediaControllerImpl.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(resultData, MediaSessionCompat.KEY_EXTRA_BINDER)));
                        mediaControllerImpl.mSessionToken.setSessionToken2Bundle(resultData.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE));
                        mediaControllerImpl.processPendingCallbacksLocked();
                    }
                }
            }
        }

        private static class ExtraCallback extends Callback.StubCompat {
            ExtraCallback(Callback callback) {
                super(callback);
            }

            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                throw new AssertionError();
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                throw new AssertionError();
            }
        }
    }

    static class TransportControlsApi21 extends TransportControls {
        protected final Object mControlsObj;

        public TransportControlsApi21(Object controlsObj) {
            this.mControlsObj = controlsObj;
        }

        public void prepare() {
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE, (Bundle) null);
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID, mediaId);
            bundle.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID, bundle);
        }

        public void prepareFromSearch(String query, Bundle extras) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaSessionCompat.ACTION_ARGUMENT_QUERY, query);
            bundle.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI, uri);
            bundle.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_URI, bundle);
        }

        public void play() {
            MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
        }

        public void pause() {
            MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
        }

        public void stop() {
            MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
        }

        public void seekTo(long pos) {
            MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, pos);
        }

        public void fastForward() {
            MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
        }

        public void rewind() {
            MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
        }

        public void skipToNext() {
            MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
        }

        public void skipToPrevious() {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
        }

        public void setRating(RatingCompat rating) {
            MediaControllerCompatApi21.TransportControls.setRating(this.mControlsObj, rating != null ? rating.getRating() : null);
        }

        public void setRating(RatingCompat rating, Bundle extras) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING, rating);
            bundle.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_SET_RATING, bundle);
        }

        public void setCaptioningEnabled(boolean enabled) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED, enabled);
            sendCustomAction(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED, bundle);
        }

        public void setRepeatMode(int repeatMode) {
            Bundle bundle = new Bundle();
            bundle.putInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE, repeatMode);
            sendCustomAction(MediaSessionCompat.ACTION_SET_REPEAT_MODE, bundle);
        }

        public void setShuffleMode(int shuffleMode) {
            Bundle bundle = new Bundle();
            bundle.putInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE, shuffleMode);
            sendCustomAction(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE, bundle);
        }

        public void playFromMediaId(String mediaId, Bundle extras) {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, mediaId, extras);
        }

        public void playFromSearch(String query, Bundle extras) {
            MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, query, extras);
        }

        public void playFromUri(Uri uri, Bundle extras) {
            if (uri == null || Uri.EMPTY.equals(uri)) {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI, uri);
            bundle.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PLAY_FROM_URI, bundle);
        }

        public void skipToQueueItem(long id) {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, id);
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle args) {
            MediaControllerCompat.validateCustomAction(customAction.getAction(), args);
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, customAction.getAction(), args);
        }

        public void sendCustomAction(String action, Bundle args) {
            MediaControllerCompat.validateCustomAction(action, args);
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, action, args);
        }
    }

    static class MediaControllerImplApi23 extends MediaControllerImplApi21 {
        public MediaControllerImplApi23(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }

        public TransportControls getTransportControls() {
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                return new TransportControlsApi23(controlsObj);
            }
            return null;
        }
    }

    static class TransportControlsApi23 extends TransportControlsApi21 {
        public TransportControlsApi23(Object controlsObj) {
            super(controlsObj);
        }

        public void playFromUri(Uri uri, Bundle extras) {
            MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, uri, extras);
        }
    }

    static class MediaControllerImplApi24 extends MediaControllerImplApi23 {
        public MediaControllerImplApi24(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }

        public TransportControls getTransportControls() {
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                return new TransportControlsApi24(controlsObj);
            }
            return null;
        }
    }

    static class TransportControlsApi24 extends TransportControlsApi23 {
        public TransportControlsApi24(Object controlsObj) {
            super(controlsObj);
        }

        public void prepare() {
            MediaControllerCompatApi24.TransportControls.prepare(this.mControlsObj);
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.mControlsObj, mediaId, extras);
        }

        public void prepareFromSearch(String query, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.mControlsObj, query, extras);
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromUri(this.mControlsObj, uri, extras);
        }
    }
}
