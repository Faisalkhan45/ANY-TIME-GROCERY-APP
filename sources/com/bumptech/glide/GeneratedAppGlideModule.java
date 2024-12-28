package com.bumptech.glide;

import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.HashSet;
import java.util.Set;

abstract class GeneratedAppGlideModule extends AppGlideModule {
    GeneratedAppGlideModule() {
    }

    /* access modifiers changed from: package-private */
    public Set<Class<?>> getExcludedModuleClasses() {
        return new HashSet();
    }

    /* access modifiers changed from: package-private */
    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
