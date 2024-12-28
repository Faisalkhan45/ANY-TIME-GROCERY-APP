package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {57, 79, 82}, m = "invokeSuspend", n = {"latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1"})
/* compiled from: Combine.kt */
final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<T[]> $arrayFactory;
    final /* synthetic */ Flow<T>[] $flows;
    final /* synthetic */ FlowCollector<R> $this_combineInternal;
    final /* synthetic */ Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> $transform;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$combineInternal$2(Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, FlowCollector<? super R> flowCollector, Continuation<? super CombineKt$combineInternal$2> continuation) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
        this.$this_combineInternal = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, continuation);
        combineKt$combineInternal$2.L$0 = obj;
        return combineKt$combineInternal$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00e6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0166  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            r22 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r22
            int r2 = r1.label
            switch(r2) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0053;
                case 2: goto L_0x0033;
                case 3: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0013:
            r1 = r22
            r2 = r23
            int r3 = r1.I$1
            int r4 = r1.I$0
            java.lang.Object r5 = r1.L$2
            byte[] r5 = (byte[]) r5
            java.lang.Object r6 = r1.L$1
            kotlinx.coroutines.channels.Channel r6 = (kotlinx.coroutines.channels.Channel) r6
            java.lang.Object r7 = r1.L$0
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            kotlin.ResultKt.throwOnFailure(r2)
            r11 = r7
            r21 = r4
            r4 = r3
            r3 = r5
            r5 = r21
            goto L_0x0164
        L_0x0033:
            r1 = r22
            r2 = r23
            int r3 = r1.I$1
            int r4 = r1.I$0
            java.lang.Object r5 = r1.L$2
            byte[] r5 = (byte[]) r5
            java.lang.Object r6 = r1.L$1
            kotlinx.coroutines.channels.Channel r6 = (kotlinx.coroutines.channels.Channel) r6
            java.lang.Object r7 = r1.L$0
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            kotlin.ResultKt.throwOnFailure(r2)
            r11 = r7
            r21 = r4
            r4 = r3
            r3 = r5
            r5 = r21
            goto L_0x013f
        L_0x0053:
            r1 = r22
            r2 = r23
            int r3 = r1.I$1
            int r4 = r1.I$0
            java.lang.Object r5 = r1.L$2
            byte[] r5 = (byte[]) r5
            java.lang.Object r6 = r1.L$1
            kotlinx.coroutines.channels.Channel r6 = (kotlinx.coroutines.channels.Channel) r6
            java.lang.Object r7 = r1.L$0
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            kotlin.ResultKt.throwOnFailure(r2)
            r8 = r2
            kotlinx.coroutines.channels.ChannelResult r8 = (kotlinx.coroutines.channels.ChannelResult) r8
            java.lang.Object r8 = r8.m241unboximpl()
            r21 = r4
            r4 = r3
            r3 = r5
            r5 = r21
            goto L_0x00e8
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r23)
            r1 = r22
            r2 = r23
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlinx.coroutines.flow.Flow<T>[] r4 = r1.$flows
            int r10 = r4.length
            if (r10 != 0) goto L_0x008c
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x008c:
            java.lang.Object[] r11 = new java.lang.Object[r10]
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.UNINITIALIZED
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r11
            kotlin.collections.ArraysKt.fill$default((java.lang.Object[]) r4, (java.lang.Object) r5, (int) r6, (int) r7, (int) r8, (java.lang.Object) r9)
            r4 = 6
            r5 = 0
            kotlinx.coroutines.channels.Channel r18 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r10, r5, r5, r4, r5)
            java.util.concurrent.atomic.AtomicInteger r15 = new java.util.concurrent.atomic.AtomicInteger
            r15.<init>(r10)
            r19 = r10
            r4 = 0
        L_0x00a6:
            if (r4 >= r10) goto L_0x00c5
            r14 = r4
            int r20 = r4 + 1
            r5 = 0
            r6 = 0
            kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1 r4 = new kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1
            kotlinx.coroutines.flow.Flow<T>[] r13 = r1.$flows
            r17 = 0
            r12 = r4
            r16 = r18
            r12.<init>(r13, r14, r15, r16, r17)
            r7 = r4
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r4 = r3
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
            r4 = r20
            goto L_0x00a6
        L_0x00c5:
            byte[] r3 = new byte[r10]
            r4 = 0
            r6 = r18
            r5 = r19
        L_0x00cc:
            int r7 = r4 + 1
            byte r4 = (byte) r7
            r7 = r1
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r1.L$0 = r11
            r1.L$1 = r6
            r1.L$2 = r3
            r1.I$0 = r5
            r1.I$1 = r4
            r8 = 1
            r1.label = r8
            java.lang.Object r8 = r6.m248receiveCatchingJP2dKIU(r7)
            if (r8 != r0) goto L_0x00e7
            return r0
        L_0x00e7:
            r7 = r11
        L_0x00e8:
            java.lang.Object r8 = kotlinx.coroutines.channels.ChannelResult.m234getOrNullimpl(r8)
            kotlin.collections.IndexedValue r8 = (kotlin.collections.IndexedValue) r8
            if (r8 != 0) goto L_0x00f3
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00f3:
            int r9 = r8.getIndex()
            r10 = r7[r9]
            java.lang.Object r11 = r8.getValue()
            r7[r9] = r11
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.UNINITIALIZED
            if (r10 != r8) goto L_0x0106
            int r5 = r5 + -1
        L_0x0106:
            byte r8 = r3[r9]
            if (r8 != r4) goto L_0x010b
            goto L_0x011a
        L_0x010b:
            byte r8 = (byte) r4
            r3[r9] = r8
            java.lang.Object r8 = r6.m249tryReceivePtdJZtk()
            java.lang.Object r8 = kotlinx.coroutines.channels.ChannelResult.m234getOrNullimpl(r8)
            kotlin.collections.IndexedValue r8 = (kotlin.collections.IndexedValue) r8
            if (r8 != 0) goto L_0x0169
        L_0x011a:
            if (r5 != 0) goto L_0x0166
            kotlin.jvm.functions.Function0<T[]> r8 = r1.$arrayFactory
            java.lang.Object r8 = r8.invoke()
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            if (r8 != 0) goto L_0x0140
            kotlin.jvm.functions.Function3<kotlinx.coroutines.flow.FlowCollector<? super R>, T[], kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r8 = r1.$transform
            kotlinx.coroutines.flow.FlowCollector<R> r9 = r1.$this_combineInternal
            r1.L$0 = r7
            r1.L$1 = r6
            r1.L$2 = r3
            r1.I$0 = r5
            r1.I$1 = r4
            r10 = 2
            r1.label = r10
            java.lang.Object r8 = r8.invoke(r9, r7, r1)
            if (r8 != r0) goto L_0x013e
            return r0
        L_0x013e:
            r11 = r7
        L_0x013f:
            goto L_0x00cc
        L_0x0140:
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 14
            r15 = 0
            r9 = r7
            r10 = r8
            kotlin.collections.ArraysKt.copyInto$default((java.lang.Object[]) r9, (java.lang.Object[]) r10, (int) r11, (int) r12, (int) r13, (int) r14, (java.lang.Object) r15)
            kotlin.jvm.functions.Function3<kotlinx.coroutines.flow.FlowCollector<? super R>, T[], kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r9 = r1.$transform
            kotlinx.coroutines.flow.FlowCollector<R> r10 = r1.$this_combineInternal
            r1.L$0 = r7
            r1.L$1 = r6
            r1.L$2 = r3
            r1.I$0 = r5
            r1.I$1 = r4
            r11 = 3
            r1.label = r11
            java.lang.Object r8 = r9.invoke(r10, r8, r1)
            if (r8 != r0) goto L_0x0163
            return r0
        L_0x0163:
            r11 = r7
        L_0x0164:
            goto L_0x00cc
        L_0x0166:
            r11 = r7
            goto L_0x00cc
        L_0x0169:
            goto L_0x00f3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
