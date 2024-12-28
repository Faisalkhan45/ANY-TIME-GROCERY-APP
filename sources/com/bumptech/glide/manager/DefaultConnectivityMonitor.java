package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.ConnectivityMonitor;

final class DefaultConnectivityMonitor implements ConnectivityMonitor {
    private final Context context;
    final ConnectivityMonitor.ConnectivityListener listener;

    DefaultConnectivityMonitor(Context context2, ConnectivityMonitor.ConnectivityListener listener2) {
        this.context = context2.getApplicationContext();
        this.listener = listener2;
    }

    private void register() {
        SingletonConnectivityReceiver.get(this.context).register(this.listener);
    }

    private void unregister() {
        SingletonConnectivityReceiver.get(this.context).unregister(this.listener);
    }

    public void onStart() {
        register();
    }

    public void onStop() {
        unregister();
    }

    public void onDestroy() {
    }
}
