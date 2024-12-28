package com.google.android.gms;

import com.google.android.gms.dependencies.DependencyInspector;
import org.gradle.api.Action;
import org.gradle.api.artifacts.ResolvableDependencies;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StrictVersionMatcherPlugin$$ExternalSyntheticLambda0 implements Action {
    public final /* synthetic */ DependencyInspector f$0;

    public /* synthetic */ StrictVersionMatcherPlugin$$ExternalSyntheticLambda0(DependencyInspector dependencyInspector) {
        this.f$0 = dependencyInspector;
    }

    public final void execute(Object obj) {
        this.f$0.afterResolve((ResolvableDependencies) obj);
    }
}
