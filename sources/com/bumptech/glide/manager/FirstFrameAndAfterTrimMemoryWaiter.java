package com.bumptech.glide.manager;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class FirstFrameAndAfterTrimMemoryWaiter implements FrameWaiter, ComponentCallbacks2 {
    FirstFrameAndAfterTrimMemoryWaiter() {
    }

    public void registerSelf(Activity activity) {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
        onTrimMemory(20);
    }
}
