package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

@JvmInline
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u000fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0012J\u001b\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u000fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0012J\u001b\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u000bJ\u001b\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\b9\u0010\u0012J\u001b\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u001fJ\u001b\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u000fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0012J\u001b\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0012J\u001b\u0010H\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0012J\u001b\u0010M\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020\u0003H\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\rH\b¢\u0006\u0004\b]\u0010-J\u0010\u0010^\u001a\u00020_H\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\b¢\u0006\u0004\bd\u0010eJ\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u0000H\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010\u0005J\u0016\u0010l\u001a\u00020\u0010H\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0013H\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010aJ\u0016\u0010p\u001a\u00020\u0016H\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010eJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0001\u0002\u0001\u00020\u0003ø\u0001\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m473boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m479constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m485equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m528unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m486equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m491hashCodeimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m485equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m491hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m528unboximpl() {
        return this.data;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object other) {
        return Intrinsics.compare((int) m528unboximpl() & 255, (int) ((UByte) other).m528unboximpl() & 255);
    }

    private /* synthetic */ UByte(byte data2) {
        this.data = data2;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: UByte.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private int m474compareTo7apg3OU(byte other) {
        return Intrinsics.compare((int) m528unboximpl() & 255, (int) other & 255);
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static int m475compareTo7apg3OU(byte arg0, byte other) {
        return Intrinsics.compare((int) arg0 & 255, (int) other & 255);
    }

    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m478compareToxj2QHRw(byte arg0, short other) {
        return Intrinsics.compare((int) arg0 & 255, (int) 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m477compareToWZ4Q5Ns(byte arg0, int other) {
        return UnsignedKt.uintCompare(UInt.m555constructorimpl(arg0 & 255), other);
    }

    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m476compareToVKZWuLQ(byte arg0, long other) {
        return UnsignedKt.ulongCompare(ULong.m633constructorimpl(((long) arg0) & 255), other);
    }

    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m503plus7apg3OU(byte arg0, byte other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) + UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m506plusxj2QHRw(byte arg0, short other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) + UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m505plusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) + other);
    }

    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m504plusVKZWuLQ(byte arg0, long other) {
        return ULong.m633constructorimpl(ULong.m633constructorimpl(((long) arg0) & 255) + other);
    }

    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m494minus7apg3OU(byte arg0, byte other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) - UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m497minusxj2QHRw(byte arg0, short other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) - UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m496minusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) - other);
    }

    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m495minusVKZWuLQ(byte arg0, long other) {
        return ULong.m633constructorimpl(ULong.m633constructorimpl(((long) arg0) & 255) - other);
    }

    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m512times7apg3OU(byte arg0, byte other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) * UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m515timesxj2QHRw(byte arg0, short other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) * UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m514timesWZ4Q5Ns(byte arg0, int other) {
        return UInt.m555constructorimpl(UInt.m555constructorimpl(arg0 & 255) * other);
    }

    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m513timesVKZWuLQ(byte arg0, long other) {
        return ULong.m633constructorimpl(ULong.m633constructorimpl(((long) arg0) & 255) * other);
    }

    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m481div7apg3OU(byte arg0, byte other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m484divxj2QHRw(byte arg0, short other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m483divWZ4Q5Ns(byte arg0, int other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), other);
    }

    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m482divVKZWuLQ(byte arg0, long other) {
        return UnsignedKt.m810ulongDivideeb3DHEI(ULong.m633constructorimpl(((long) arg0) & 255), other);
    }

    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m508rem7apg3OU(byte arg0, byte other) {
        return UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m511remxj2QHRw(byte arg0, short other) {
        return UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m510remWZ4Q5Ns(byte arg0, int other) {
        return UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), other);
    }

    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m509remVKZWuLQ(byte arg0, long other) {
        return UnsignedKt.m811ulongRemaindereb3DHEI(ULong.m633constructorimpl(((long) arg0) & 255), other);
    }

    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m487floorDiv7apg3OU(byte arg0, byte other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(other & 255));
    }

    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m490floorDivxj2QHRw(byte arg0, short other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(65535 & other));
    }

    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m489floorDivWZ4Q5Ns(byte arg0, int other) {
        return UnsignedKt.m808uintDivideJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), other);
    }

    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m488floorDivVKZWuLQ(byte arg0, long other) {
        return UnsignedKt.m810ulongDivideeb3DHEI(ULong.m633constructorimpl(((long) arg0) & 255), other);
    }

    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m498mod7apg3OU(byte arg0, byte other) {
        return m479constructorimpl((byte) UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(other & 255)));
    }

    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m501modxj2QHRw(byte arg0, short other) {
        return UShort.m739constructorimpl((short) UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(65535 & other)));
    }

    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m500modWZ4Q5Ns(byte arg0, int other) {
        return UnsignedKt.m809uintRemainderJ1ME1BU(UInt.m555constructorimpl(arg0 & 255), other);
    }

    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m499modVKZWuLQ(byte arg0, long other) {
        return UnsignedKt.m811ulongRemaindereb3DHEI(ULong.m633constructorimpl(((long) arg0) & 255), other);
    }

    /* renamed from: inc-w2LRezQ  reason: not valid java name */
    private static final byte m492incw2LRezQ(byte arg0) {
        return m479constructorimpl((byte) (arg0 + 1));
    }

    /* renamed from: dec-w2LRezQ  reason: not valid java name */
    private static final byte m480decw2LRezQ(byte arg0) {
        return m479constructorimpl((byte) (arg0 - 1));
    }

    /* renamed from: rangeTo-7apg3OU  reason: not valid java name */
    private static final UIntRange m507rangeTo7apg3OU(byte arg0, byte other) {
        return new UIntRange(UInt.m555constructorimpl(arg0 & 255), UInt.m555constructorimpl(other & 255), (DefaultConstructorMarker) null);
    }

    /* renamed from: and-7apg3OU  reason: not valid java name */
    private static final byte m472and7apg3OU(byte arg0, byte other) {
        return m479constructorimpl((byte) (arg0 & other));
    }

    /* renamed from: or-7apg3OU  reason: not valid java name */
    private static final byte m502or7apg3OU(byte arg0, byte other) {
        return m479constructorimpl((byte) (arg0 | other));
    }

    /* renamed from: xor-7apg3OU  reason: not valid java name */
    private static final byte m527xor7apg3OU(byte arg0, byte other) {
        return m479constructorimpl((byte) (arg0 ^ other));
    }

    /* renamed from: inv-w2LRezQ  reason: not valid java name */
    private static final byte m493invw2LRezQ(byte arg0) {
        return m479constructorimpl((byte) (~arg0));
    }

    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m516toByteimpl(byte arg0) {
        return arg0;
    }

    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m521toShortimpl(byte arg0) {
        return (short) (((short) arg0) & 255);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m519toIntimpl(byte arg0) {
        return arg0 & 255;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m520toLongimpl(byte arg0) {
        return ((long) arg0) & 255;
    }

    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m523toUBytew2LRezQ(byte arg0) {
        return arg0;
    }

    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m526toUShortMh2AYeg(byte arg0) {
        return UShort.m739constructorimpl((short) (((short) arg0) & 255));
    }

    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m524toUIntpVg5ArA(byte arg0) {
        return UInt.m555constructorimpl(arg0 & 255);
    }

    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m525toULongsVKNKU(byte arg0) {
        return ULong.m633constructorimpl(((long) arg0) & 255);
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m518toFloatimpl(byte arg0) {
        return (float) (arg0 & 255);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m517toDoubleimpl(byte arg0) {
        return (double) (arg0 & 255);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m522toStringimpl(byte arg0) {
        return String.valueOf(arg0 & 255);
    }

    public String toString() {
        return m522toStringimpl(this.data);
    }
}
