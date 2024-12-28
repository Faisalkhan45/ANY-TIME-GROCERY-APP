package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.GlideSuppliers;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

final class SingletonConnectivityReceiver {
    private static final String TAG = "ConnectivityMonitor";
    private static volatile SingletonConnectivityReceiver instance;
    private final FrameworkConnectivityMonitor frameworkConnectivityMonitor;
    private boolean isRegistered;
    final Set<ConnectivityMonitor.ConnectivityListener> listeners = new HashSet();

    private interface FrameworkConnectivityMonitor {
        boolean register();

        void unregister();
    }

    static SingletonConnectivityReceiver get(Context context) {
        if (instance == null) {
            synchronized (SingletonConnectivityReceiver.class) {
                if (instance == null) {
                    instance = new SingletonConnectivityReceiver(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    static void reset() {
        instance = null;
    }

    private SingletonConnectivityReceiver(final Context context) {
        FrameworkConnectivityMonitor frameworkConnectivityMonitor2;
        GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager = GlideSuppliers.memorize(new GlideSuppliers.GlideSupplier<ConnectivityManager>() {
            public ConnectivityManager get() {
                return (ConnectivityManager) context.getSystemService("connectivity");
            }
        });
        ConnectivityMonitor.ConnectivityListener connectivityListener = new ConnectivityMonitor.ConnectivityListener() {
            public void onConnectivityChanged(boolean isConnected) {
                List<ConnectivityMonitor.ConnectivityListener> toNotify;
                Util.assertMainThread();
                synchronized (SingletonConnectivityReceiver.this) {
                    toNotify = new ArrayList<>(SingletonConnectivityReceiver.this.listeners);
                }
                for (ConnectivityMonitor.ConnectivityListener listener : toNotify) {
                    listener.onConnectivityChanged(isConnected);
                }
            }
        };
        if (Build.VERSION.SDK_INT >= 24) {
            frameworkConnectivityMonitor2 = new FrameworkConnectivityMonitorPostApi24(connectivityManager, connectivityListener);
        } else {
            frameworkConnectivityMonitor2 = new FrameworkConnectivityMonitorPreApi24(context, connectivityManager, connectivityListener);
        }
        this.frameworkConnectivityMonitor = frameworkConnectivityMonitor2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void register(ConnectivityMonitor.ConnectivityListener listener) {
        this.listeners.add(listener);
        maybeRegisterReceiver();
    }

    /* access modifiers changed from: package-private */
    public synchronized void unregister(ConnectivityMonitor.ConnectivityListener listener) {
        this.listeners.remove(listener);
        maybeUnregisterReceiver();
    }

    private void maybeRegisterReceiver() {
        if (!this.isRegistered && !this.listeners.isEmpty()) {
            this.isRegistered = this.frameworkConnectivityMonitor.register();
        }
    }

    private void maybeUnregisterReceiver() {
        if (this.isRegistered && this.listeners.isEmpty()) {
            this.frameworkConnectivityMonitor.unregister();
            this.isRegistered = false;
        }
    }

    private static final class FrameworkConnectivityMonitorPostApi24 implements FrameworkConnectivityMonitor {
        private final GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager;
        boolean isConnected;
        final ConnectivityMonitor.ConnectivityListener listener;
        private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            public void onAvailable(Network network) {
                postOnConnectivityChange(true);
            }

            public void onLost(Network network) {
                postOnConnectivityChange(false);
            }

            private void postOnConnectivityChange(final boolean newState) {
                Util.postOnUiThread(new Runnable() {
                    public void run() {
                        AnonymousClass1.this.onConnectivityChange(newState);
                    }
                });
            }

            /* access modifiers changed from: package-private */
            public void onConnectivityChange(boolean newState) {
                Util.assertMainThread();
                boolean wasConnected = FrameworkConnectivityMonitorPostApi24.this.isConnected;
                FrameworkConnectivityMonitorPostApi24.this.isConnected = newState;
                if (wasConnected != newState) {
                    FrameworkConnectivityMonitorPostApi24.this.listener.onConnectivityChanged(newState);
                }
            }
        };

        FrameworkConnectivityMonitorPostApi24(GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager2, ConnectivityMonitor.ConnectivityListener listener2) {
            this.connectivityManager = connectivityManager2;
            this.listener = listener2;
        }

        public boolean register() {
            this.isConnected = this.connectivityManager.get().getActiveNetwork() != null;
            try {
                this.connectivityManager.get().registerDefaultNetworkCallback(this.networkCallback);
                return true;
            } catch (RuntimeException e) {
                if (Log.isLoggable(SingletonConnectivityReceiver.TAG, 5)) {
                    Log.w(SingletonConnectivityReceiver.TAG, "Failed to register callback", e);
                }
                return false;
            }
        }

        public void unregister() {
            this.connectivityManager.get().unregisterNetworkCallback(this.networkCallback);
        }
    }

    private static final class FrameworkConnectivityMonitorPreApi24 implements FrameworkConnectivityMonitor {
        static final Executor EXECUTOR = AsyncTask.SERIAL_EXECUTOR;
        private final GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager;
        final BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                FrameworkConnectivityMonitorPreApi24.this.onConnectivityChange();
            }
        };
        final Context context;
        volatile boolean isConnected;
        volatile boolean isRegistered;
        final ConnectivityMonitor.ConnectivityListener listener;

        FrameworkConnectivityMonitorPreApi24(Context context2, GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager2, ConnectivityMonitor.ConnectivityListener listener2) {
            this.context = context2.getApplicationContext();
            this.connectivityManager = connectivityManager2;
            this.listener = listener2;
        }

        public boolean register() {
            EXECUTOR.execute(new Runnable() {
                public void run() {
                    FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                    frameworkConnectivityMonitorPreApi24.isConnected = frameworkConnectivityMonitorPreApi24.isConnected();
                    try {
                        FrameworkConnectivityMonitorPreApi24.this.context.registerReceiver(FrameworkConnectivityMonitorPreApi24.this.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = true;
                    } catch (SecurityException e) {
                        if (Log.isLoggable(SingletonConnectivityReceiver.TAG, 5)) {
                            Log.w(SingletonConnectivityReceiver.TAG, "Failed to register", e);
                        }
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = false;
                    }
                }
            });
            return true;
        }

        public void unregister() {
            EXECUTOR.execute(new Runnable() {
                public void run() {
                    if (FrameworkConnectivityMonitorPreApi24.this.isRegistered) {
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = false;
                        FrameworkConnectivityMonitorPreApi24.this.context.unregisterReceiver(FrameworkConnectivityMonitorPreApi24.this.connectivityReceiver);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void onConnectivityChange() {
            EXECUTOR.execute(new Runnable() {
                public void run() {
                    boolean wasConnected = FrameworkConnectivityMonitorPreApi24.this.isConnected;
                    FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                    frameworkConnectivityMonitorPreApi24.isConnected = frameworkConnectivityMonitorPreApi24.isConnected();
                    if (wasConnected != FrameworkConnectivityMonitorPreApi24.this.isConnected) {
                        if (Log.isLoggable(SingletonConnectivityReceiver.TAG, 3)) {
                            Log.d(SingletonConnectivityReceiver.TAG, "connectivity changed, isConnected: " + FrameworkConnectivityMonitorPreApi24.this.isConnected);
                        }
                        FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi242 = FrameworkConnectivityMonitorPreApi24.this;
                        frameworkConnectivityMonitorPreApi242.notifyChangeOnUiThread(frameworkConnectivityMonitorPreApi242.isConnected);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void notifyChangeOnUiThread(final boolean isConnected2) {
            Util.postOnUiThread(new Runnable() {
                public void run() {
                    FrameworkConnectivityMonitorPreApi24.this.listener.onConnectivityChanged(isConnected2);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public boolean isConnected() {
            try {
                NetworkInfo networkInfo = this.connectivityManager.get().getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected()) {
                    return false;
                }
                return true;
            } catch (RuntimeException e) {
                if (Log.isLoggable(SingletonConnectivityReceiver.TAG, 5)) {
                    Log.w(SingletonConnectivityReceiver.TAG, "Failed to determine connectivity status when connectivity changed", e);
                }
                return true;
            }
        }
    }
}
