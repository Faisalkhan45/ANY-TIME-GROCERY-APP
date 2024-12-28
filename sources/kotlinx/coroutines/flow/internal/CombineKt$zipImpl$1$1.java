package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
/* compiled from: Combine.kt */
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T1> $flow;
    final /* synthetic */ Flow<T2> $flow2;
    final /* synthetic */ FlowCollector<R> $this_unsafeFlow;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$zipImpl$1$1(FlowCollector<? super R> flowCollector, Flow<? extends T2> flow, Flow<? extends T1> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: kotlinx.coroutines.channels.ReceiveChannel} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            r23 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r23
            int r2 = r1.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L_0x0029;
                case 1: goto L_0x0015;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0015:
            r1 = r23
            r2 = r24
            java.lang.Object r0 = r1.L$0
            r5 = r0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ AbortFlowException -> 0x0026 }
            goto L_0x00a3
        L_0x0023:
            r0 = move-exception
            goto L_0x00b6
        L_0x0026:
            r0 = move-exception
            goto L_0x00ad
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r24)
            r1 = r23
            r2 = r24
            java.lang.Object r5 = r1.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            r7 = 0
            r8 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1 r6 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$second$1
            kotlinx.coroutines.flow.Flow<T2> r9 = r1.$flow2
            r6.<init>(r9, r4)
            r9 = r6
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r10 = 3
            r11 = 0
            r6 = r5
            kotlinx.coroutines.channels.ReceiveChannel r6 = kotlinx.coroutines.channels.ProduceKt.produce$default(r6, r7, r8, r9, r10, r11)
            kotlinx.coroutines.CompletableJob r7 = kotlinx.coroutines.JobKt.Job$default((kotlinx.coroutines.Job) r4, (int) r3, (java.lang.Object) r4)
            r8 = r6
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1 r9 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$1
            kotlinx.coroutines.flow.FlowCollector<R> r10 = r1.$this_unsafeFlow
            r9.<init>(r7, r10)
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            r8.invokeOnClose(r9)
            kotlin.coroutines.CoroutineContext r14 = r5.getCoroutineContext()     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            java.lang.Object r15 = kotlinx.coroutines.internal.ThreadContextKt.threadContextElements(r14)     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlin.coroutines.CoroutineContext r8 = r5.getCoroutineContext()     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r9 = r7
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlin.coroutines.CoroutineContext r8 = r8.plus(r9)     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r10 = 0
            kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2 r11 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlinx.coroutines.flow.Flow<T1> r13 = r1.$flow     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlinx.coroutines.flow.FlowCollector<R> r12 = r1.$this_unsafeFlow     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r4 = r1.$transform     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r19 = 0
            r17 = r12
            r12 = r11
            r16 = r6
            r18 = r4
            r12.<init>(r13, r14, r15, r16, r17, r18, r19)     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r19 = r11
            kotlin.jvm.functions.Function2 r19 = (kotlin.jvm.functions.Function2) r19     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r20 = r1
            kotlin.coroutines.Continuation r20 = (kotlin.coroutines.Continuation) r20     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r21 = 4
            r22 = 0
            r1.L$0 = r6     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r1.label = r3     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            r16 = r8
            r17 = r9
            r18 = r10
            java.lang.Object r4 = kotlinx.coroutines.flow.internal.ChannelFlowKt.withContextUndispatched$default(r16, r17, r18, r19, r20, r21, r22)     // Catch:{ AbortFlowException -> 0x00ab, all -> 0x00a8 }
            if (r4 != r0) goto L_0x00a2
            return r0
        L_0x00a2:
            r5 = r6
        L_0x00a3:
            r4 = 0
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r5, (java.util.concurrent.CancellationException) r4, (int) r3, (java.lang.Object) r4)
            goto L_0x00b3
        L_0x00a8:
            r0 = move-exception
            r5 = r6
            goto L_0x00b6
        L_0x00ab:
            r0 = move-exception
            r5 = r6
        L_0x00ad:
            kotlinx.coroutines.flow.FlowCollector<R> r4 = r1.$this_unsafeFlow     // Catch:{ all -> 0x0023 }
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r4)     // Catch:{ all -> 0x0023 }
            goto L_0x00a3
        L_0x00b3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00b6:
            r4 = 0
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel$default((kotlinx.coroutines.channels.ReceiveChannel) r5, (java.util.concurrent.CancellationException) r4, (int) r3, (java.lang.Object) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
