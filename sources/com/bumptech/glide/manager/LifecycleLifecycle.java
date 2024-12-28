package com.bumptech.glide.manager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.bumptech.glide.util.Util;
import java.util.HashSet;
import java.util.Set;

final class LifecycleLifecycle implements Lifecycle, LifecycleObserver {
    private final Lifecycle lifecycle;
    private final Set<LifecycleListener> lifecycleListeners = new HashSet();

    LifecycleLifecycle(Lifecycle lifecycle2) {
        this.lifecycle = lifecycle2;
        lifecycle2.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner owner) {
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStart();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner owner) {
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onStop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        for (T lifecycleListener : Util.getSnapshot(this.lifecycleListeners)) {
            lifecycleListener.onDestroy();
        }
        owner.getLifecycle().removeObserver(this);
    }

    public void addListener(LifecycleListener listener) {
        this.lifecycleListeners.add(listener);
        if (this.lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            listener.onDestroy();
        } else if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            listener.onStart();
        } else {
            listener.onStop();
        }
    }

    public void removeListener(LifecycleListener listener) {
        this.lifecycleListeners.remove(listener);
    }
}
