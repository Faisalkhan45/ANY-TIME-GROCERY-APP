package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.util.Base64OutputStream;
import androidx.core.os.UserManagerCompat;
import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.load.Key;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

public class DefaultHeartBeatController implements HeartBeatController, HeartBeatInfo {
    private static final ThreadFactory THREAD_FACTORY = new DefaultHeartBeatController$$ExternalSyntheticLambda0();
    private final Context applicationContext;
    private final Executor backgroundExecutor;
    private final Set<HeartBeatConsumer> consumers;
    private final Provider<HeartBeatInfoStorage> storageProvider;
    private final Provider<UserAgentPublisher> userAgentProvider;

    static /* synthetic */ Thread lambda$static$0(Runnable r) {
        return new Thread(r, "heartbeat-information-executor");
    }

    public Task<Void> registerHeartBeat() {
        if (this.consumers.size() <= 0) {
            return Tasks.forResult(null);
        }
        if (!UserManagerCompat.isUserUnlocked(this.applicationContext)) {
            return Tasks.forResult(null);
        }
        return Tasks.call(this.backgroundExecutor, new DefaultHeartBeatController$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerHeartBeat$1$com-google-firebase-heartbeatinfo-DefaultHeartBeatController  reason: not valid java name */
    public /* synthetic */ Void m433lambda$registerHeartBeat$1$comgooglefirebaseheartbeatinfoDefaultHeartBeatController() throws Exception {
        synchronized (this) {
            this.storageProvider.get().storeHeartBeat(System.currentTimeMillis(), this.userAgentProvider.get().getUserAgent());
        }
        return null;
    }

    public Task<String> getHeartBeatsHeader() {
        if (!UserManagerCompat.isUserUnlocked(this.applicationContext)) {
            return Tasks.forResult("");
        }
        return Tasks.call(this.backgroundExecutor, new DefaultHeartBeatController$$ExternalSyntheticLambda1(this));
    }

    /* Debug info: failed to restart local var, previous not found, register: 9 */
    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getHeartBeatsHeader$2$com-google-firebase-heartbeatinfo-DefaultHeartBeatController  reason: not valid java name */
    public /* synthetic */ String m432lambda$getHeartBeatsHeader$2$comgooglefirebaseheartbeatinfoDefaultHeartBeatController() throws Exception {
        GZIPOutputStream gzip;
        String byteArrayOutputStream;
        synchronized (this) {
            HeartBeatInfoStorage storage = this.storageProvider.get();
            List<HeartBeatResult> allHeartBeats = storage.getAllHeartBeats();
            storage.deleteAllHeartBeats();
            JSONArray array = new JSONArray();
            for (int i = 0; i < allHeartBeats.size(); i++) {
                HeartBeatResult result = allHeartBeats.get(i);
                JSONObject obj = new JSONObject();
                obj.put("agent", result.getUserAgent());
                obj.put("dates", new JSONArray(result.getUsedDates()));
                array.put(obj);
            }
            JSONObject output = new JSONObject();
            output.put("heartbeats", array);
            output.put("version", ExifInterface.GPS_MEASUREMENT_2D);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Base64OutputStream b64os = new Base64OutputStream(out, 11);
            try {
                gzip = new GZIPOutputStream(b64os);
                gzip.write(output.toString().getBytes(Key.STRING_CHARSET_NAME));
                gzip.close();
                b64os.close();
                byteArrayOutputStream = out.toString(Key.STRING_CHARSET_NAME);
            } catch (Throwable th) {
                try {
                    b64os.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        return byteArrayOutputStream;
        throw th;
    }

    private DefaultHeartBeatController(Context context, String persistenceKey, Set<HeartBeatConsumer> consumers2, Provider<UserAgentPublisher> userAgentProvider2) {
        this(new DefaultHeartBeatController$$ExternalSyntheticLambda4(context, persistenceKey), consumers2, new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY), userAgentProvider2, context);
    }

    static /* synthetic */ HeartBeatInfoStorage lambda$new$3(Context context, String persistenceKey) {
        return new HeartBeatInfoStorage(context, persistenceKey);
    }

    DefaultHeartBeatController(Provider<HeartBeatInfoStorage> testStorage, Set<HeartBeatConsumer> consumers2, Executor executor, Provider<UserAgentPublisher> userAgentProvider2, Context context) {
        this.storageProvider = testStorage;
        this.consumers = consumers2;
        this.backgroundExecutor = executor;
        this.userAgentProvider = userAgentProvider2;
        this.applicationContext = context;
    }

    public static Component<DefaultHeartBeatController> component() {
        return Component.builder(DefaultHeartBeatController.class, HeartBeatController.class, HeartBeatInfo.class).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseApp.class)).add(Dependency.setOf(HeartBeatConsumer.class)).add(Dependency.requiredProvider(UserAgentPublisher.class)).factory(new DefaultHeartBeatController$$ExternalSyntheticLambda3()).build();
    }

    static /* synthetic */ DefaultHeartBeatController lambda$component$4(ComponentContainer c) {
        return new DefaultHeartBeatController((Context) c.get(Context.class), ((FirebaseApp) c.get(FirebaseApp.class)).getPersistenceKey(), c.setOf(HeartBeatConsumer.class), c.getProvider(UserAgentPublisher.class));
    }

    public synchronized HeartBeatInfo.HeartBeat getHeartBeatCode(String heartBeatTag) {
        long presentTime = System.currentTimeMillis();
        HeartBeatInfoStorage storage = this.storageProvider.get();
        if (storage.shouldSendGlobalHeartBeat(presentTime)) {
            storage.postHeartBeatCleanUp();
            return HeartBeatInfo.HeartBeat.GLOBAL;
        }
        return HeartBeatInfo.HeartBeat.NONE;
    }
}
