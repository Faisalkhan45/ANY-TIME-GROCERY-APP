package com.google.android.gms.dependencies;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.gradle.api.GradleException;
import org.gradle.api.artifacts.DependencyResolutionListener;
import org.gradle.api.artifacts.ResolvableDependencies;
import org.gradle.api.artifacts.component.ModuleComponentSelector;
import org.gradle.api.artifacts.result.DependencyResult;
import org.gradle.api.artifacts.result.ResolutionResult;
import org.gradle.api.artifacts.result.ResolvedComponentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependencyInspector implements DependencyResolutionListener {
    private static final String GRADLE_PROJECT = "gradle.project";
    private static Logger logger = LoggerFactory.getLogger(DependencyInspector.class);
    private final DependencyAnalyzer dependencyAnalyzer;
    private final String exceptionMessageAddendum;
    private final String projectName;

    public DependencyInspector(@Nonnull DependencyAnalyzer dependencyAnalyzer2, @Nonnull String projectName2, @Nullable String exceptionMessageAddendum2) {
        this.dependencyAnalyzer = dependencyAnalyzer2;
        this.exceptionMessageAddendum = exceptionMessageAddendum2;
        this.projectName = projectName2;
    }

    private static String simplifyKnownGroupIds(@Nonnull String inputString) {
        return inputString.replace("com.google.android.gms", "c.g.a.g").replace("com.google.firebase", "c.g.f");
    }

    private static void printNode(int depth, @Nonnull Node n) {
        StringBuilder prefix = new StringBuilder();
        for (int z = 0; z < depth; z++) {
            prefix.append("--");
        }
        prefix.append(" ");
        Dependency dep = n.getDependency();
        if (GRADLE_PROJECT.equals(n.getDependency().getFromArtifactVersion().getGroupId())) {
            String fromRef = dep.getFromArtifactVersion().getGradleRef().replace(GRADLE_PROJECT, "");
            logger.info(prefix.toString() + fromRef + " task/module dep -> " + simplifyKnownGroupIds(dep.getToArtifact().getGradleRef()) + "@" + dep.getToArtifactVersionString());
        } else {
            String fromRef2 = simplifyKnownGroupIds(dep.getFromArtifactVersion().getGradleRef());
            logger.info(prefix.toString() + fromRef2 + " library depends -> " + simplifyKnownGroupIds(dep.getToArtifact().getGradleRef()) + "@" + dep.getToArtifactVersionString());
        }
        if (n.getChild() != null) {
            printNode(depth + 1, n.getChild());
        }
    }

    private void registerDependencies(@Nonnull ResolvableDependencies resolvableDependencies, @Nonnull String projectName2, @Nonnull String taskName) {
        String depFromString;
        ArtifactVersion fromDep;
        String toDepString;
        String str = projectName2;
        String str2 = taskName;
        String depFromString2 = "";
        for (DependencyResult depResult : resolvableDependencies.getResolutionResult().getAllDependencies()) {
            if (depResult.getFrom() == null || "".equals(depResult.getFrom().getId().getDisplayName()) || "project :".equals(depResult.getFrom().getId().getDisplayName())) {
                fromDep = ArtifactVersion.Companion.fromGradleRef("gradle.project:" + str + "-" + str2 + ":0.0.0");
                depFromString = depFromString2;
            } else {
                String depFromString3 = "" + depResult.getFrom().getId().getDisplayName();
                if (depFromString3.startsWith("project ")) {
                    String[] splitDepName = depFromString3.split(":");
                    depFromString = depFromString3;
                    fromDep = ArtifactVersion.Companion.fromGradleRef("gradle.project:" + str + "-" + str2 + "-" + (splitDepName.length > 1 ? splitDepName[1] : "module") + ":0.0.0");
                } else {
                    try {
                        depFromString = depFromString3;
                        fromDep = ArtifactVersion.Companion.fromGradleRef(depFromString3);
                    } catch (IllegalArgumentException e) {
                        logger.info("Skipping misunderstood FROM dep string: " + depFromString3);
                        depFromString2 = depFromString3;
                    }
                }
            }
            if (depResult.getRequested() != null) {
                String toDepString2 = depResult.getRequested().toString();
                if (depResult.getRequested() instanceof ModuleComponentSelector) {
                    ModuleComponentSelector selector = depResult.getRequested();
                    if (!"".equals(selector.getVersionConstraint().getStrictVersion())) {
                        toDepString = selector.getGroup() + ":" + selector.getModule() + ":[" + selector.getVersionConstraint().getStrictVersion() + "]";
                        this.dependencyAnalyzer.registerDependency(Dependency.Companion.fromArtifactVersions(fromDep, ArtifactVersion.Companion.fromGradleRef(toDepString)));
                    }
                }
                toDepString = toDepString2;
                try {
                    this.dependencyAnalyzer.registerDependency(Dependency.Companion.fromArtifactVersions(fromDep, ArtifactVersion.Companion.fromGradleRef(toDepString)));
                } catch (IllegalArgumentException e2) {
                    logger.info("Skipping misunderstood TO dep string: " + toDepString);
                }
            }
            depFromString2 = depFromString;
        }
    }

    public void beforeResolve(ResolvableDependencies resolvableDependencies) {
    }

    public void afterResolve(ResolvableDependencies resolvableDependencies) {
        String taskName = resolvableDependencies.getName();
        logger.info("Registered task dependencies: " + this.projectName + ":" + taskName);
        if (!(resolvableDependencies.getResolutionResult() == null || resolvableDependencies.getResolutionResult().getAllDependencies() == null)) {
            registerDependencies(resolvableDependencies, this.projectName, taskName);
        }
        logger.info("Starting dependency analysis");
        ResolutionResult resolutionResult = resolvableDependencies.getResolutionResult();
        HashMap<Artifact, ArtifactVersion> resolvedVersions = new HashMap<>();
        for (ResolvedComponentResult resolvedComponentResult : resolutionResult.getAllComponents()) {
            ArtifactVersion version = ArtifactVersion.Companion.fromGradleRefOrNull(resolvedComponentResult.getId().toString());
            if (version != null) {
                resolvedVersions.put(version.getArtifact(), version);
            }
        }
        if (resolvedVersions.size() >= 1) {
            for (Dependency dep : this.dependencyAnalyzer.getActiveDependencies(resolvedVersions.values())) {
                ArtifactVersion resolvedVersion = resolvedVersions.get(dep.getToArtifact());
                if (!dep.isVersionCompatible(resolvedVersion.getVersion())) {
                    logger.warn("Dependency resolved to an incompatible version: " + dep);
                    Collection<Node> depsPaths = this.dependencyAnalyzer.getPaths(resolvedVersion.getArtifact());
                    logger.info("Dependency Resolution Help: Displaying all currently known paths to any version of the dependency: " + dep.getToArtifact());
                    logger.info("NOTE: com.google.android.gms translated to c.g.a.g for brevity. Same for com.google.firebase -> c.g.f");
                    for (Node n : depsPaths) {
                        printNode(1, n);
                    }
                    throw new GradleException(getErrorMessage(dep, resolvedVersion, depsPaths));
                }
            }
        }
    }

    private String getErrorMessage(@Nonnull Dependency dep, @Nonnull ArtifactVersion resolvedVersion, @Nonnull Collection<Node> depPaths) {
        StringBuilder errorMessage = new StringBuilder("In project '").append(this.projectName).append("' a resolved Google Play services library dependency depends on another at an exact version (e.g. \"").append(dep.getToArtifactVersionString()).append("\", but isn't being resolved to that version. Behavior exhibited by the library will be unknown.").append(System.lineSeparator()).append(System.lineSeparator()).append("Dependency failing: ").append(dep.getDisplayString()).append(", but ").append(dep.getToArtifact().getArtifactId()).append(" version was ").append(resolvedVersion.getVersion()).append(".").append(System.lineSeparator()).append(System.lineSeparator()).append("The following dependencies are project dependencies that are direct or have transitive dependencies that lead to the artifact with the issue.");
        HashSet<String> directDependencyStrings = new HashSet<>();
        StringBuilder currentString = new StringBuilder();
        for (Node node : depPaths) {
            String[] projectNameParts = node.getDependency().getFromArtifactVersion().getArtifactId().split("-");
            if (projectNameParts[0].equals(projectNameParts[2])) {
                currentString.append("-- Project '").append(projectNameParts[0]).append("' depends onto ");
            } else {
                currentString.append("-- Project '").append(projectNameParts[0]).append("' depends on project '").append(projectNameParts[2]).append("' which depends onto ");
            }
            currentString.append(node.getDependency().getToArtifact().getGroupId()).append(":").append(node.getDependency().getToArtifact().getArtifactId()).append("@").append(node.getDependency().getToArtifactVersionString());
            directDependencyStrings.add(currentString.toString());
            currentString.delete(0, currentString.length());
        }
        Iterator<String> it = directDependencyStrings.iterator();
        while (it.hasNext()) {
            errorMessage.append(System.lineSeparator()).append(it.next());
        }
        errorMessage.append(System.lineSeparator()).append(System.lineSeparator()).append("For extended debugging info execute Gradle from the command line with ").append("./gradlew --info :").append(this.projectName).append(":assembleDebug to see the dependency paths to the artifact. ");
        String str = this.exceptionMessageAddendum;
        if (str != null && !"".equals(str.trim())) {
            errorMessage.append(this.exceptionMessageAddendum);
        }
        return errorMessage.toString().replaceAll(".{120}(?=.)", "$0" + System.lineSeparator());
    }
}
