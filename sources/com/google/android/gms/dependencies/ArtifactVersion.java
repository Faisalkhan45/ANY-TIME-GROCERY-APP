package com.google.android.gms.dependencies;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/google/android/gms/dependencies/ArtifactVersion;", "", "groupId", "", "artifactId", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtifactId", "()Ljava/lang/String;", "getGroupId", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "getArtifact", "Lcom/google/android/gms/dependencies/Artifact;", "getGradleRef", "hashCode", "", "toString", "Companion", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: DataObjects.kt */
public final class ArtifactVersion {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String artifactId;
    private final String groupId;
    private final String version;

    public static /* synthetic */ ArtifactVersion copy$default(ArtifactVersion artifactVersion, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artifactVersion.groupId;
        }
        if ((i & 2) != 0) {
            str2 = artifactVersion.artifactId;
        }
        if ((i & 4) != 0) {
            str3 = artifactVersion.version;
        }
        return artifactVersion.copy(str, str2, str3);
    }

    public final String component1() {
        return this.groupId;
    }

    public final String component2() {
        return this.artifactId;
    }

    public final String component3() {
        return this.version;
    }

    public final ArtifactVersion copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "groupId");
        Intrinsics.checkNotNullParameter(str2, "artifactId");
        Intrinsics.checkNotNullParameter(str3, "version");
        return new ArtifactVersion(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArtifactVersion)) {
            return false;
        }
        ArtifactVersion artifactVersion = (ArtifactVersion) obj;
        return Intrinsics.areEqual((Object) this.groupId, (Object) artifactVersion.groupId) && Intrinsics.areEqual((Object) this.artifactId, (Object) artifactVersion.artifactId) && Intrinsics.areEqual((Object) this.version, (Object) artifactVersion.version);
    }

    public int hashCode() {
        String str = this.groupId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.artifactId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.version;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "ArtifactVersion(groupId=" + this.groupId + ", artifactId=" + this.artifactId + ", version=" + this.version + ")";
    }

    public ArtifactVersion(String groupId2, String artifactId2, String version2) {
        Intrinsics.checkNotNullParameter(groupId2, "groupId");
        Intrinsics.checkNotNullParameter(artifactId2, "artifactId");
        Intrinsics.checkNotNullParameter(version2, "version");
        this.groupId = groupId2;
        this.artifactId = artifactId2;
        this.version = version2;
    }

    public final String getArtifactId() {
        return this.artifactId;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final String getVersion() {
        return this.version;
    }

    public final Artifact getArtifact() {
        return new Artifact(this.groupId, this.artifactId);
    }

    public final String getGradleRef() {
        return this.groupId + ':' + this.artifactId + ':' + this.version;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/google/android/gms/dependencies/ArtifactVersion$Companion;", "", "()V", "fromGradleRef", "Lcom/google/android/gms/dependencies/ArtifactVersion;", "referenceString", "", "fromGradleRefOrNull", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: DataObjects.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final ArtifactVersion fromGradleRef(String referenceString) {
            Intrinsics.checkNotNullParameter(referenceString, "referenceString");
            List stringSplit = StringsKt.split$default((CharSequence) referenceString, new String[]{":"}, false, 0, 6, (Object) null);
            if (stringSplit.size() >= 3) {
                return new ArtifactVersion((String) stringSplit.get(0), (String) stringSplit.get(1), (String) stringSplit.get(2));
            }
            throw new IllegalArgumentException("Invalid Gradle reference string: " + referenceString);
        }

        public final ArtifactVersion fromGradleRefOrNull(String referenceString) {
            Intrinsics.checkNotNullParameter(referenceString, "referenceString");
            List stringSplit = StringsKt.split$default((CharSequence) referenceString, new String[]{":"}, false, 0, 6, (Object) null);
            if (stringSplit.size() < 3) {
                return null;
            }
            return new ArtifactVersion((String) stringSplit.get(0), (String) stringSplit.get(1), (String) stringSplit.get(2));
        }
    }
}
