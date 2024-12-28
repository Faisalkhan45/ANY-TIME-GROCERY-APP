package com.google.android.gms.dependencies;

import com.google.common.annotations.VisibleForTesting;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u0012\u001a\u00020\u0005R.\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00048\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/google/android/gms/dependencies/ArtifactDependencyManager;", "", "()V", "dependencies", "Ljava/util/HashMap;", "Lcom/google/android/gms/dependencies/Artifact;", "Ljava/util/HashSet;", "Lcom/google/android/gms/dependencies/Dependency;", "getDependencies$strict_version_matcher_plugin$annotations", "getDependencies$strict_version_matcher_plugin", "()Ljava/util/HashMap;", "dependencyLock", "Ljava/lang/Object;", "addDependency", "", "dependency", "getDependencies", "", "artifact", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: DataObjects.kt */
public final class ArtifactDependencyManager {
    private final HashMap<Artifact, HashSet<Dependency>> dependencies = new HashMap<>();
    private final Object dependencyLock = new Object();

    @VisibleForTesting
    public static /* synthetic */ void getDependencies$strict_version_matcher_plugin$annotations() {
    }

    public final HashMap<Artifact, HashSet<Dependency>> getDependencies$strict_version_matcher_plugin() {
        return this.dependencies;
    }

    public final void addDependency(Dependency dependency) {
        Intrinsics.checkNotNullParameter(dependency, "dependency");
        synchronized (this.dependencyLock) {
            HashSet depSetForArtifact = this.dependencies.get(dependency.getToArtifact());
            if (depSetForArtifact == null) {
                depSetForArtifact = new HashSet();
                this.dependencies.put(dependency.getToArtifact(), depSetForArtifact);
            }
            depSetForArtifact.add(dependency);
        }
    }

    public final Collection<Dependency> getDependencies(Artifact artifact) {
        Intrinsics.checkNotNullParameter(artifact, "artifact");
        synchronized (this.dependencyLock) {
            if (this.dependencies.get(artifact) == null) {
                Collection<Dependency> hashSet = new HashSet<>();
                return hashSet;
            }
            Collection<Dependency> hashSet2 = new HashSet<>(this.dependencies.get(artifact));
            return hashSet2;
        }
    }
}
