package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zabe;
import com.google.android.gms.common.api.internal.zada;
import com.google.android.gms.common.api.internal.zak;
import com.google.android.gms.common.api.internal.zat;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.signin.zae;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public abstract class GoogleApiClient {
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    public static final Set<GoogleApiClient> zaa = Collections.newSetFromMap(new WeakHashMap());

    @Deprecated
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    }

    @Deprecated
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public static void dumpAll(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
            String concat = String.valueOf(prefix).concat("  ");
            int i = 0;
            for (GoogleApiClient dump : set) {
                writer.append(prefix).append("GoogleApiClient#").println(i);
                dump.dump(concat, fd, writer, args);
                i++;
            }
        }
    }

    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new UnsupportedOperationException();
    }

    public <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    public abstract ConnectionResult getConnectionResult(Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public boolean hasApi(Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public <L> ListenerHolder<L> registerListener(L l) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public void zao(zada zada) {
        throw new UnsupportedOperationException();
    }

    public void zap(zada zada) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    /* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
    public static final class Builder {
        private Account zaa;
        private final Set<Scope> zab;
        private final Set<Scope> zac;
        private int zad;
        private View zae;
        private String zaf;
        private String zag;
        private final Map<Api<?>, zab> zah;
        private final Context zai;
        private final Map<Api<?>, Api.ApiOptions> zaj;
        private LifecycleActivity zak;
        private int zal;
        private OnConnectionFailedListener zam;
        private Looper zan;
        private GoogleApiAvailability zao;
        private Api.AbstractClientBuilder<? extends zae, SignInOptions> zap;
        private final ArrayList<ConnectionCallbacks> zaq;
        private final ArrayList<OnConnectionFailedListener> zar;

        public Builder(Context context) {
            this.zab = new HashSet();
            this.zac = new HashSet();
            this.zah = new ArrayMap();
            this.zaj = new ArrayMap();
            this.zal = -1;
            this.zao = GoogleApiAvailability.getInstance();
            this.zap = zad.zac;
            this.zaq = new ArrayList<>();
            this.zar = new ArrayList<>();
            this.zai = context;
            this.zan = context.getMainLooper();
            this.zaf = context.getPackageName();
            this.zag = context.getClass().getName();
        }

        private final <O extends Api.ApiOptions> void zab(Api<O> api, O o, Scope... scopeArr) {
            HashSet hashSet = new HashSet(((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.zah.put(api, new zab(hashSet));
        }

        public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaj.put(api, (Object) null);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(null);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(Api<O> api, O options, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(options, "Null options are not permitted for this Api");
            this.zaj.put(api, options);
            zab(api, options, scopes);
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks listener) {
            Preconditions.checkNotNull(listener, "Listener must not be null");
            this.zaq.add(listener);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener listener) {
            Preconditions.checkNotNull(listener, "Listener must not be null");
            this.zar.add(listener);
            return this;
        }

        public Builder addScope(Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.zab.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            boolean z;
            Preconditions.checkArgument(!this.zaj.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings zaa2 = zaa();
            Map<Api<?>, zab> zad2 = zaa2.zad();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Api api = null;
            boolean z2 = false;
            for (Api next : this.zaj.keySet()) {
                Api.ApiOptions apiOptions = this.zaj.get(next);
                if (zad2.get(next) != null) {
                    z = true;
                } else {
                    z = false;
                }
                arrayMap.put(next, Boolean.valueOf(z));
                zat zat = new zat(next, z);
                arrayList.add(zat);
                Api.AbstractClientBuilder abstractClientBuilder = (Api.AbstractClientBuilder) Preconditions.checkNotNull(next.zaa());
                Api api2 = next;
                Api.Client buildClient = abstractClientBuilder.buildClient(this.zai, this.zan, zaa2, apiOptions, (ConnectionCallbacks) zat, (OnConnectionFailedListener) zat);
                arrayMap2.put(api2.zab(), buildClient);
                if (abstractClientBuilder.getPriority() == 1) {
                    if (apiOptions != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (buildClient.providesSignIn()) {
                    if (api == null) {
                        api = api2;
                    } else {
                        String zad3 = api2.zad();
                        String zad4 = api.zad();
                        StringBuilder sb = new StringBuilder(String.valueOf(zad3).length() + 21 + String.valueOf(zad4).length());
                        sb.append(zad3);
                        sb.append(" cannot be used with ");
                        sb.append(zad4);
                        throw new IllegalStateException(sb.toString());
                    }
                }
            }
            if (api != null) {
                if (!z2) {
                    Preconditions.checkState(this.zaa == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.zad());
                    Preconditions.checkState(this.zab.equals(this.zac), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.zad());
                } else {
                    String zad5 = api.zad();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(zad5).length() + 82);
                    sb2.append("With using ");
                    sb2.append(zad5);
                    sb2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
                    throw new IllegalStateException(sb2.toString());
                }
            }
            zabe zabe = new zabe(this.zai, new ReentrantLock(), this.zan, zaa2, this.zao, this.zap, arrayMap, this.zaq, this.zar, arrayMap2, this.zal, zabe.zad(arrayMap2.values(), true), arrayList);
            synchronized (GoogleApiClient.zaa) {
                GoogleApiClient.zaa.add(zabe);
            }
            if (this.zal >= 0) {
                zak.zaa(this.zak).zad(this.zal, zabe, this.zam);
            }
            return zabe;
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, int clientId, OnConnectionFailedListener unresolvedConnectionFailedListener) {
            boolean z;
            LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
            if (clientId >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "clientId must be non-negative");
            this.zal = clientId;
            this.zam = unresolvedConnectionFailedListener;
            this.zak = lifecycleActivity;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.zaa = accountName == null ? null : new Account(accountName, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int i) {
            this.zad = i;
            return this;
        }

        public Builder setHandler(Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zan = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(View viewForPopups) {
            Preconditions.checkNotNull(viewForPopups, "View must not be null");
            this.zae = viewForPopups;
            return this;
        }

        public Builder useDefaultAccount() {
            setAccountName("<<default account>>");
            return this;
        }

        public final ClientSettings zaa() {
            SignInOptions signInOptions;
            SignInOptions signInOptions2 = SignInOptions.zaa;
            if (this.zaj.containsKey(zad.zag)) {
                signInOptions = (SignInOptions) this.zaj.get(zad.zag);
            } else {
                signInOptions = signInOptions2;
            }
            return new ClientSettings(this.zaa, this.zab, this.zah, this.zad, this.zae, this.zaf, this.zag, signInOptions, false);
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, OnConnectionFailedListener unresolvedConnectionFailedListener) {
            enableAutoManage(fragmentActivity, 0, unresolvedConnectionFailedListener);
            return this;
        }

        public <T extends Api.ApiOptions.NotRequiredOptions> Builder addApiIfAvailable(Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaj.put(api, (Object) null);
            zab(api, (Api.ApiOptions) null, scopes);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> api, O options) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(options, "Null options are not permitted for this Api");
            this.zaj.put(api, options);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(options);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        public Builder(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectedListener, "Must provide a connected listener");
            this.zaq.add(connectedListener);
            Preconditions.checkNotNull(connectionFailedListener, "Must provide a connection failed listener");
            this.zar.add(connectionFailedListener);
        }
    }
}
