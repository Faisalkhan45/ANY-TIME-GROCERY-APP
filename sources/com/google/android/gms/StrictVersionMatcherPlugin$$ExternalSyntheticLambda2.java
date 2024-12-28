package com.google.android.gms;

import com.google.android.gms.dependencies.DependencyInspector;
import org.gradle.api.Action;
import org.gradle.api.artifacts.Configuration;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StrictVersionMatcherPlugin$$ExternalSyntheticLambda2 implements Action {
    public final /* synthetic */ DependencyInspector f$0;

    public /* synthetic */ StrictVersionMatcherPlugin$$ExternalSyntheticLambda2(DependencyInspector dependencyInspector) {
        this.f$0 = dependencyInspector;
    }

    public final void execute(Object obj) {
        StrictVersionMatcherPlugin.lambda$apply$1(this.f$0, (Configuration) obj);
    }
}
