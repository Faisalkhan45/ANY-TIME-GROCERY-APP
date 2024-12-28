package com.google.android.gms.dependencies;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\f"}, d2 = {"Lcom/google/android/gms/dependencies/VersionEvaluators;", "", "()V", "getEvaluator", "Lcom/google/android/gms/dependencies/VersionEvaluator;", "versionString", "", "enableStrictMatching", "", "AlwaysCompatibleEvaluator", "ExactVersionEvaluator", "SemVerVersionEvaluator", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: VersionEvaluation.kt */
public final class VersionEvaluators {
    public static final VersionEvaluators INSTANCE = new VersionEvaluators();

    private VersionEvaluators() {
    }

    public final VersionEvaluator getEvaluator(String versionString, boolean enableStrictMatching) {
        AlwaysCompatibleEvaluator alwaysCompatibleEvaluator;
        Intrinsics.checkNotNullParameter(versionString, "versionString");
        boolean hasVersionRange = StringsKt.indexOf$default((CharSequence) versionString, ",", 0, false, 6, (Object) null) > 0 || StringsKt.indexOf$default((CharSequence) versionString, ")", 0, false, 6, (Object) null) > 0 || StringsKt.indexOf$default((CharSequence) versionString, "(", 0, false, 6, (Object) null) > 0;
        if (!enableStrictMatching || !StringsKt.startsWith$default(versionString, "[", false, 2, (Object) null) || !StringsKt.endsWith$default(versionString, "]", false, 2, (Object) null)) {
            if (!enableStrictMatching || hasVersionRange) {
                alwaysCompatibleEvaluator = new AlwaysCompatibleEvaluator();
            } else {
                alwaysCompatibleEvaluator = new AlwaysCompatibleEvaluator();
            }
            return alwaysCompatibleEvaluator;
        }
        String substring = versionString.substring(1, versionString.length() - 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return new ExactVersionEvaluator(substring);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/google/android/gms/dependencies/VersionEvaluators$AlwaysCompatibleEvaluator;", "Lcom/google/android/gms/dependencies/VersionEvaluator;", "()V", "isCompatible", "", "version", "", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: VersionEvaluation.kt */
    public static final class AlwaysCompatibleEvaluator implements VersionEvaluator {
        public boolean isCompatible(String version) {
            Intrinsics.checkNotNullParameter(version, "version");
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/google/android/gms/dependencies/VersionEvaluators$ExactVersionEvaluator;", "Lcom/google/android/gms/dependencies/VersionEvaluator;", "versionString", "", "(Ljava/lang/String;)V", "getVersionString$strict_version_matcher_plugin", "()Ljava/lang/String;", "setVersionString$strict_version_matcher_plugin", "isCompatible", "", "version", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: VersionEvaluation.kt */
    public static final class ExactVersionEvaluator implements VersionEvaluator {
        private String versionString;

        public ExactVersionEvaluator(String versionString2) {
            Intrinsics.checkNotNullParameter(versionString2, "versionString");
            this.versionString = versionString2;
        }

        public final String getVersionString$strict_version_matcher_plugin() {
            return this.versionString;
        }

        public final void setVersionString$strict_version_matcher_plugin(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.versionString = str;
        }

        public boolean isCompatible(String version) {
            Intrinsics.checkNotNullParameter(version, "version");
            return Intrinsics.areEqual((Object) version, (Object) this.versionString);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/google/android/gms/dependencies/VersionEvaluators$SemVerVersionEvaluator;", "Lcom/google/android/gms/dependencies/VersionEvaluator;", "versionString", "", "(Ljava/lang/String;)V", "versionInfo", "Lcom/google/android/gms/dependencies/SemVerInfo;", "getVersionInfo$strict_version_matcher_plugin", "()Lcom/google/android/gms/dependencies/SemVerInfo;", "setVersionInfo$strict_version_matcher_plugin", "(Lcom/google/android/gms/dependencies/SemVerInfo;)V", "isCompatible", "", "version", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: VersionEvaluation.kt */
    public static final class SemVerVersionEvaluator implements VersionEvaluator {
        private SemVerInfo versionInfo;

        public SemVerVersionEvaluator(String versionString) {
            Intrinsics.checkNotNullParameter(versionString, "versionString");
            this.versionInfo = SemVerInfo.Companion.parseString(versionString);
        }

        public final SemVerInfo getVersionInfo$strict_version_matcher_plugin() {
            return this.versionInfo;
        }

        public final void setVersionInfo$strict_version_matcher_plugin(SemVerInfo semVerInfo) {
            Intrinsics.checkNotNullParameter(semVerInfo, "<set-?>");
            this.versionInfo = semVerInfo;
        }

        public boolean isCompatible(String version) {
            Intrinsics.checkNotNullParameter(version, "version");
            SemVerInfo parseString = SemVerInfo.Companion.parseString(version);
            return parseString.component1() == this.versionInfo.getMajor() && parseString.component2() >= this.versionInfo.getMinor();
        }
    }
}
