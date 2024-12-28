package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {352}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "ticker"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$sample$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $periodMillis;
    final /* synthetic */ Flow<T> $this_sample;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2(long j, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$sample$2> continuation) {
        super(3, continuation);
        this.$periodMillis = j;
        this.$this_sample = flow;
    }

    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$sample$2 flowKt__DelayKt$sample$2 = new FlowKt__DelayKt$sample$2(this.$periodMillis, this.$this_sample, continuation);
        flowKt__DelayKt$sample$2.L$0 = coroutineScope;
        flowKt__DelayKt$sample$2.L$1 = flowCollector;
        return flowKt__DelayKt$sample$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r17
            int r2 = r1.label
            r3 = 0
            switch(r2) {
                case 0: goto L_0x0034;
                case 1: goto L_0x0014;
                default: goto L_0x000c;
            }
        L_0x000c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0014:
            r1 = r17
            r2 = r18
            r4 = 0
            java.lang.Object r5 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r2)
            r16 = r1
            r1 = r0
            r0 = r2
            r2 = r16
            goto L_0x00c7
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r18)
            r1 = r17
            r2 = r18
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            java.lang.Object r5 = r1.L$1
            r12 = r5
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            r6 = 0
            r7 = -1
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1 r5 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1
            kotlinx.coroutines.flow.Flow<T> r8 = r1.$this_sample
            r5.<init>(r8, r3)
            r8 = r5
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 1
            r10 = 0
            r5 = r4
            kotlinx.coroutines.channels.ReceiveChannel r13 = kotlinx.coroutines.channels.ProduceKt.produce$default(r5, r6, r7, r8, r9, r10)
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            r14 = r5
            long r6 = r1.$periodMillis
            r8 = 0
            r10 = 2
            r11 = 0
            r5 = r4
            kotlinx.coroutines.channels.ReceiveChannel r5 = kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker$default(r5, r6, r8, r10, r11)
            r4 = r2
            r8 = r12
            r7 = r13
            r6 = r14
            r2 = r1
            r1 = r0
        L_0x006e:
            T r0 = r6.element
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.flow.internal.NullSurrogateKt.DONE
            if (r0 == r9) goto L_0x00c9
            r9 = 0
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r6
            r2.L$3 = r5
            r0 = 1
            r2.label = r0
            r10 = r2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r11 = 0
            kotlinx.coroutines.selects.SelectBuilderImpl r0 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r0.<init>(r10)
            r12 = r0
            r0 = r12
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ all -> 0x00ae }
            r13 = 0
            kotlinx.coroutines.selects.SelectClause1 r14 = r7.getOnReceiveCatching()     // Catch:{ all -> 0x00ae }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$1 r15 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$1     // Catch:{ all -> 0x00ae }
            r15.<init>(r6, r5, r3)     // Catch:{ all -> 0x00ae }
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15     // Catch:{ all -> 0x00ae }
            r0.invoke(r14, r15)     // Catch:{ all -> 0x00ae }
            kotlinx.coroutines.selects.SelectClause1 r14 = r5.getOnReceive()     // Catch:{ all -> 0x00ae }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$2 r15 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$2     // Catch:{ all -> 0x00ae }
            r15.<init>(r6, r8, r3)     // Catch:{ all -> 0x00ae }
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15     // Catch:{ all -> 0x00ae }
            r0.invoke(r14, r15)     // Catch:{ all -> 0x00ae }
            goto L_0x00b2
        L_0x00ae:
            r0 = move-exception
            r12.handleBuilderException(r0)
        L_0x00b2:
            java.lang.Object r0 = r12.getResult()
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r10) goto L_0x00c2
            r10 = r2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r10)
        L_0x00c2:
            if (r0 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r0 = r4
            r4 = r9
        L_0x00c7:
            r4 = r0
            goto L_0x006e
        L_0x00c9:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
