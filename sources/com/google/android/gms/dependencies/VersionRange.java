package com.google.android.gms.dependencies;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/google/android/gms/dependencies/VersionRange;", "", "closedStart", "", "closedEnd", "rangeStart", "Lcom/google/android/gms/dependencies/Version;", "rangeEnd", "(ZZLcom/google/android/gms/dependencies/Version;Lcom/google/android/gms/dependencies/Version;)V", "getClosedEnd", "()Z", "getClosedStart", "getRangeEnd", "()Lcom/google/android/gms/dependencies/Version;", "getRangeStart", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "toVersionString", "versionInRange", "compareTo", "Companion", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
/* compiled from: Versions.kt */
public final class VersionRange {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern VERSION_RANGE_PATTERN = Pattern.compile("\\[(\\d+\\.)*(\\d+)+(-\\w)*\\]");
    private final boolean closedEnd;
    private final boolean closedStart;
    private final Version rangeEnd;
    private final Version rangeStart;

    public static /* synthetic */ VersionRange copy$default(VersionRange versionRange, boolean z, boolean z2, Version version, Version version2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = versionRange.closedStart;
        }
        if ((i & 2) != 0) {
            z2 = versionRange.closedEnd;
        }
        if ((i & 4) != 0) {
            version = versionRange.rangeStart;
        }
        if ((i & 8) != 0) {
            version2 = versionRange.rangeEnd;
        }
        return versionRange.copy(z, z2, version, version2);
    }

    public final boolean component1() {
        return this.closedStart;
    }

    public final boolean component2() {
        return this.closedEnd;
    }

    public final Version component3() {
        return this.rangeStart;
    }

    public final Version component4() {
        return this.rangeEnd;
    }

    public final VersionRange copy(boolean z, boolean z2, Version version, Version version2) {
        Intrinsics.checkNotNullParameter(version, "rangeStart");
        Intrinsics.checkNotNullParameter(version2, "rangeEnd");
        return new VersionRange(z, z2, version, version2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VersionRange)) {
            return false;
        }
        VersionRange versionRange = (VersionRange) obj;
        return this.closedStart == versionRange.closedStart && this.closedEnd == versionRange.closedEnd && Intrinsics.areEqual((Object) this.rangeStart, (Object) versionRange.rangeStart) && Intrinsics.areEqual((Object) this.rangeEnd, (Object) versionRange.rangeEnd);
    }

    public int hashCode() {
        boolean z = this.closedStart;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        boolean z3 = this.closedEnd;
        if (!z3) {
            z2 = z3;
        }
        int i2 = (i + (z2 ? 1 : 0)) * 31;
        Version version = this.rangeStart;
        int i3 = 0;
        int hashCode = (i2 + (version != null ? version.hashCode() : 0)) * 31;
        Version version2 = this.rangeEnd;
        if (version2 != null) {
            i3 = version2.hashCode();
        }
        return hashCode + i3;
    }

    public String toString() {
        return "VersionRange(closedStart=" + this.closedStart + ", closedEnd=" + this.closedEnd + ", rangeStart=" + this.rangeStart + ", rangeEnd=" + this.rangeEnd + ")";
    }

    public VersionRange(boolean closedStart2, boolean closedEnd2, Version rangeStart2, Version rangeEnd2) {
        Intrinsics.checkNotNullParameter(rangeStart2, "rangeStart");
        Intrinsics.checkNotNullParameter(rangeEnd2, "rangeEnd");
        this.closedStart = closedStart2;
        this.closedEnd = closedEnd2;
        this.rangeStart = rangeStart2;
        this.rangeEnd = rangeEnd2;
    }

    public final boolean getClosedEnd() {
        return this.closedEnd;
    }

    public final boolean getClosedStart() {
        return this.closedStart;
    }

    public final Version getRangeEnd() {
        return this.rangeEnd;
    }

    public final Version getRangeStart() {
        return this.rangeStart;
    }

    public final String toVersionString() {
        return (this.closedStart ? "[" : "(") + this.rangeStart.getTrimmedString() + "," + this.rangeEnd.getTrimmedString() + (this.closedEnd ? "]" : ")");
    }

    public final boolean versionInRange(Version compareTo) {
        Intrinsics.checkNotNullParameter(compareTo, "compareTo");
        if (this.closedStart) {
            if (Companion.versionCompare(this.rangeStart.getTrimmedString(), compareTo.getTrimmedString()) > 0) {
                return false;
            }
        } else if (Companion.versionCompare(this.rangeStart.getTrimmedString(), compareTo.getTrimmedString()) >= 0) {
            return false;
        }
        if (this.closedEnd) {
            if (Companion.versionCompare(this.rangeEnd.getTrimmedString(), compareTo.getTrimmedString()) < 0) {
                return false;
            }
            return true;
        } else if (Companion.versionCompare(this.rangeEnd.getTrimmedString(), compareTo.getTrimmedString()) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/google/android/gms/dependencies/VersionRange$Companion;", "", "()V", "VERSION_RANGE_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "getVERSION_RANGE_PATTERN", "()Ljava/util/regex/Pattern;", "fromString", "Lcom/google/android/gms/dependencies/VersionRange;", "versionRange", "", "versionCompare", "", "str1", "str2", "strict-version-matcher-plugin"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Versions.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final int versionCompare(String str1, String str2) {
            Intrinsics.checkNotNullParameter(str1, "str1");
            Intrinsics.checkNotNullParameter(str2, "str2");
            List vals1 = StringsKt.split$default((CharSequence) str1, new String[]{"\\."}, false, 0, 6, (Object) null);
            List vals2 = StringsKt.split$default((CharSequence) str2, new String[]{"\\."}, false, 0, 6, (Object) null);
            int i = 0;
            while (i < vals1.size() && i < vals2.size() && Intrinsics.areEqual((Object) (String) vals1.get(i), (Object) (String) vals2.get(i))) {
                i++;
            }
            if (i >= vals1.size() || i >= vals2.size()) {
                return Integer.signum(vals1.size() - vals2.size());
            }
            Integer valueOf = Integer.valueOf((String) vals1.get(i));
            Intrinsics.checkNotNullExpressionValue(valueOf, "Integer.valueOf(vals1[i])");
            int intValue = valueOf.intValue();
            Integer valueOf2 = Integer.valueOf((String) vals2.get(i));
            Intrinsics.checkNotNullExpressionValue(valueOf2, "Integer.valueOf(vals2[i])");
            return Integer.signum(Integer.compare(intValue, valueOf2.intValue()));
        }

        public final Pattern getVERSION_RANGE_PATTERN() {
            return VersionRange.VERSION_RANGE_PATTERN;
        }

        public final VersionRange fromString(String versionRange) {
            Version v;
            Intrinsics.checkNotNullParameter(versionRange, "versionRange");
            Matcher versionRangeMatcher = getVERSION_RANGE_PATTERN().matcher(versionRange);
            if (!versionRangeMatcher.matches() || (v = Version.Companion.fromString(versionRangeMatcher.group(1))) == null) {
                return null;
            }
            return new VersionRange(true, true, v, v);
        }
    }
}
