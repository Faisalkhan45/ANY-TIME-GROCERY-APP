package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;

public abstract class ComponentProvider {
    private ConnectivityMonitor connectivityMonitor;
    private EventManager eventManager;
    private Scheduler garbageCollectionScheduler;
    private IndexBackfiller indexBackfiller;
    private LocalStore localStore;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    /* access modifiers changed from: protected */
    public abstract ConnectivityMonitor createConnectivityMonitor(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract EventManager createEventManager(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract Scheduler createGarbageCollectionScheduler(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract IndexBackfiller createIndexBackfiller(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract LocalStore createLocalStore(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract Persistence createPersistence(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract RemoteStore createRemoteStore(Configuration configuration);

    /* access modifiers changed from: protected */
    public abstract SyncEngine createSyncEngine(Configuration configuration);

    public static class Configuration {
        private final AsyncQueue asyncQueue;
        private final Context context;
        private final DatabaseInfo databaseInfo;
        private final Datastore datastore;
        private final User initialUser;
        private final int maxConcurrentLimboResolutions;
        private final FirebaseFirestoreSettings settings;

        public Configuration(Context context2, AsyncQueue asyncQueue2, DatabaseInfo databaseInfo2, Datastore datastore2, User initialUser2, int maxConcurrentLimboResolutions2, FirebaseFirestoreSettings settings2) {
            this.context = context2;
            this.asyncQueue = asyncQueue2;
            this.databaseInfo = databaseInfo2;
            this.datastore = datastore2;
            this.initialUser = initialUser2;
            this.maxConcurrentLimboResolutions = maxConcurrentLimboResolutions2;
            this.settings = settings2;
        }

        /* access modifiers changed from: package-private */
        public FirebaseFirestoreSettings getSettings() {
            return this.settings;
        }

        /* access modifiers changed from: package-private */
        public AsyncQueue getAsyncQueue() {
            return this.asyncQueue;
        }

        /* access modifiers changed from: package-private */
        public DatabaseInfo getDatabaseInfo() {
            return this.databaseInfo;
        }

        /* access modifiers changed from: package-private */
        public Datastore getDatastore() {
            return this.datastore;
        }

        /* access modifiers changed from: package-private */
        public User getInitialUser() {
            return this.initialUser;
        }

        /* access modifiers changed from: package-private */
        public int getMaxConcurrentLimboResolutions() {
            return this.maxConcurrentLimboResolutions;
        }

        /* access modifiers changed from: package-private */
        public Context getContext() {
            return this.context;
        }
    }

    public Persistence getPersistence() {
        return (Persistence) Assert.hardAssertNonNull(this.persistence, "persistence not initialized yet", new Object[0]);
    }

    public Scheduler getGarbageCollectionScheduler() {
        return this.garbageCollectionScheduler;
    }

    public IndexBackfiller getIndexBackfiller() {
        return this.indexBackfiller;
    }

    public LocalStore getLocalStore() {
        return (LocalStore) Assert.hardAssertNonNull(this.localStore, "localStore not initialized yet", new Object[0]);
    }

    public SyncEngine getSyncEngine() {
        return (SyncEngine) Assert.hardAssertNonNull(this.syncEngine, "syncEngine not initialized yet", new Object[0]);
    }

    public RemoteStore getRemoteStore() {
        return (RemoteStore) Assert.hardAssertNonNull(this.remoteStore, "remoteStore not initialized yet", new Object[0]);
    }

    public EventManager getEventManager() {
        return (EventManager) Assert.hardAssertNonNull(this.eventManager, "eventManager not initialized yet", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public ConnectivityMonitor getConnectivityMonitor() {
        return (ConnectivityMonitor) Assert.hardAssertNonNull(this.connectivityMonitor, "connectivityMonitor not initialized yet", new Object[0]);
    }

    public void initialize(Configuration configuration) {
        Persistence createPersistence = createPersistence(configuration);
        this.persistence = createPersistence;
        createPersistence.start();
        this.localStore = createLocalStore(configuration);
        this.connectivityMonitor = createConnectivityMonitor(configuration);
        this.remoteStore = createRemoteStore(configuration);
        this.syncEngine = createSyncEngine(configuration);
        this.eventManager = createEventManager(configuration);
        this.localStore.start();
        this.remoteStore.start();
        this.garbageCollectionScheduler = createGarbageCollectionScheduler(configuration);
        this.indexBackfiller = createIndexBackfiller(configuration);
    }
}
