package com.google.android.gms.dependencies;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/google/android/gms/dependencies/Version;", "", "rawString", "", "trimmedString", "(Ljava/lang/String;Ljava/lang/String;)V", "getRawString", "()Ljava/lang/String;", "getTrimmedString", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: Versions.kt */
public final class Version {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String rawString;
    private final String trimmedString;

    public static /* synthetic */ Version copy$default(Version version, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = version.rawString;
        }
        if ((i & 2) != 0) {
            str2 = version.trimmedString;
        }
        return version.copy(str, str2);
    }

    public final String component1() {
        return this.rawString;
    }

    public final String component2() {
        return this.trimmedString;
    }

    public final Version copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "rawString");
        Intrinsics.checkNotNullParameter(str2, "trimmedString");
        return new Version(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Version)) {
            return false;
        }
        Version version = (Version) obj;
        return Intrinsics.areEqual((Object) this.rawString, (Object) version.rawString) && Intrinsics.areEqual((Object) this.trimmedString, (Object) version.trimmedString);
    }

    public int hashCode() {
        String str = this.rawString;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.trimmedString;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Version(rawString=" + this.rawString + ", trimmedString=" + this.trimmedString + ")";
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/android/gms/dependencies/Version$Companion;", "", "()V", "fromString", "Lcom/google/android/gms/dependencies/Version;", "version", "", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Versions.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final Version fromString(String version) {
            if (version == null) {
                return null;
            }
            return new Version(version, (String) StringsKt.split$default((CharSequence) version, new String[]{"-"}, false, 0, 6, (Object) null).get(0));
        }
    }

    public Version(String rawString2, String trimmedString2) {
        Intrinsics.checkNotNullParameter(rawString2, "rawString");
        Intrinsics.checkNotNullParameter(trimmedString2, "trimmedString");
        this.rawString = rawString2;
        this.trimmedString = trimmedString2;
    }

    public final String getRawString() {
        return this.rawString;
    }

    public final String getTrimmedString() {
        return this.trimmedString;
    }
}
