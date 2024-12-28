package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@"}, d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {487, 469, 471}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<R> $other;
    final /* synthetic */ ReceiveChannel<E> $this_zip;
    final /* synthetic */ Function2<E, R, V> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$zip$2(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super ChannelsKt__DeprecatedKt$zip$2> continuation) {
        super(2, continuation);
        this.$other = receiveChannel;
        this.$this_zip = receiveChannel2;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$zip$2 channelsKt__DeprecatedKt$zip$2 = new ChannelsKt__DeprecatedKt$zip$2(this.$other, this.$this_zip, this.$transform, continuation);
        channelsKt__DeprecatedKt$zip$2.L$0 = obj;
        return channelsKt__DeprecatedKt$zip$2;
    }

    public final Object invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$zip$2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: kotlinx.coroutines.channels.ChannelIterator<R>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.L$0 = r13;
        r1.L$1 = r12;
        r1.L$2 = r8;
        r1.L$3 = r10;
        r1.L$4 = r7;
        r1.L$5 = r3;
        r1.label = 1;
        r11 = r7.hasNext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d6, code lost:
        if (r11 != r0) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00d9, code lost:
        r16 = r4;
        r4 = r2;
        r2 = r11;
        r11 = r10;
        r10 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00eb, code lost:
        if (((java.lang.Boolean) r2).booleanValue() == false) goto L_0x0175;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ed, code lost:
        r2 = r8.next();
        r14 = 0;
        r1.L$0 = r13;
        r1.L$1 = r12;
        r1.L$2 = r9;
        r1.L$3 = r11;
        r1.L$4 = r8;
        r1.L$5 = r2;
        r1.label = 2;
        r15 = r12.hasNext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0105, code lost:
        if (r15 != r0) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0107, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0108, code lost:
        r16 = r9;
        r9 = r2;
        r2 = r15;
        r15 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0117, code lost:
        if (((java.lang.Boolean) r2).booleanValue() != false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0119, code lost:
        r9 = r10;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r11;
        r10 = r12;
        r12 = r13;
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0124, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r3 = r11.invoke(r9, r13.next());
        r1.L$0 = r15;
        r1.L$1 = r13;
        r1.L$2 = r11;
        r1.L$3 = r12;
        r1.L$4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0137, code lost:
        r17 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r1.L$5 = null;
        r1.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0143, code lost:
        if (r15.send(r3, r1) != r0) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0145, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0146, code lost:
        r9 = r17;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r10 = r12;
        r12 = r13;
        r7 = r14;
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0150, code lost:
        r7 = r8;
        r8 = r11;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0155, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0156, code lost:
        r9 = r17;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r10 = r12;
        r12 = r13;
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x015f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0160, code lost:
        r9 = r2;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r10 = r12;
        r12 = r13;
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x016b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x016c, code lost:
        r9 = r10;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r10 = r12;
        r12 = r13;
        r13 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0177, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r11, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x017e, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x017f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0180, code lost:
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r9 = r10;
        r10 = r11;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r17
            int r2 = r1.label
            r3 = 0
            switch(r2) {
                case 0: goto L_0x0099;
                case 1: goto L_0x006b;
                case 2: goto L_0x0036;
                case 3: goto L_0x0014;
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
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            r9 = 0
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0096 }
            goto L_0x0150
        L_0x0036:
            r1 = r17
            r2 = r18
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.Object r8 = r1.L$5
            java.lang.Object r9 = r1.L$4
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r10 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0067 }
            r14 = r7
            r15 = r13
            r7 = r6
            r13 = r12
            r6 = r5
            r12 = r10
            r10 = r3
            r5 = r4
            r4 = r2
            r16 = r9
            r9 = r8
            r8 = r16
            goto L_0x0111
        L_0x0067:
            r0 = move-exception
            r9 = r3
            goto L_0x0189
        L_0x006b:
            r1 = r17
            r2 = r18
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.Object r7 = r1.L$4
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            r9 = 0
            java.lang.Object r8 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            r10 = r8
            java.lang.Object r8 = r1.L$2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r11 = r1.L$1
            r12 = r11
            kotlinx.coroutines.channels.ChannelIterator r12 = (kotlinx.coroutines.channels.ChannelIterator) r12
            java.lang.Object r11 = r1.L$0
            r13 = r11
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0096 }
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r2
            goto L_0x00e5
        L_0x0096:
            r0 = move-exception
            goto L_0x0189
        L_0x0099:
            kotlin.ResultKt.throwOnFailure(r18)
            r1 = r17
            r2 = r18
            java.lang.Object r4 = r1.L$0
            r13 = r4
            kotlinx.coroutines.channels.ProducerScope r13 = (kotlinx.coroutines.channels.ProducerScope) r13
            kotlinx.coroutines.channels.ReceiveChannel<R> r4 = r1.$other
            kotlinx.coroutines.channels.ChannelIterator r12 = r4.iterator()
            kotlinx.coroutines.channels.ReceiveChannel<E> r4 = r1.$this_zip
            kotlin.jvm.functions.Function2<E, R, V> r5 = r1.$transform
            r6 = 0
            r10 = r4
            r4 = 0
            r9 = 0
            r7 = r10
            r8 = 0
            kotlinx.coroutines.channels.ChannelIterator r11 = r7.iterator()     // Catch:{ all -> 0x0186 }
            r7 = r11
            r16 = r5
            r5 = r4
            r4 = r6
            r6 = r8
            r8 = r16
        L_0x00c3:
            r1.L$0 = r13     // Catch:{ all -> 0x0096 }
            r1.L$1 = r12     // Catch:{ all -> 0x0096 }
            r1.L$2 = r8     // Catch:{ all -> 0x0096 }
            r1.L$3 = r10     // Catch:{ all -> 0x0096 }
            r1.L$4 = r7     // Catch:{ all -> 0x0096 }
            r1.L$5 = r3     // Catch:{ all -> 0x0096 }
            r11 = 1
            r1.label = r11     // Catch:{ all -> 0x0096 }
            java.lang.Object r11 = r7.hasNext(r1)     // Catch:{ all -> 0x0096 }
            if (r11 != r0) goto L_0x00d9
            return r0
        L_0x00d9:
            r16 = r4
            r4 = r2
            r2 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r16
        L_0x00e5:
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x017f }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x017f }
            if (r2 == 0) goto L_0x0174
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x017f }
            r14 = 0
            r1.L$0 = r13     // Catch:{ all -> 0x017f }
            r1.L$1 = r12     // Catch:{ all -> 0x017f }
            r1.L$2 = r9     // Catch:{ all -> 0x017f }
            r1.L$3 = r11     // Catch:{ all -> 0x017f }
            r1.L$4 = r8     // Catch:{ all -> 0x017f }
            r1.L$5 = r2     // Catch:{ all -> 0x017f }
            r15 = 2
            r1.label = r15     // Catch:{ all -> 0x017f }
            java.lang.Object r15 = r12.hasNext(r1)     // Catch:{ all -> 0x017f }
            if (r15 != r0) goto L_0x0108
            return r0
        L_0x0108:
            r16 = r9
            r9 = r2
            r2 = r15
            r15 = r13
            r13 = r12
            r12 = r11
            r11 = r16
        L_0x0111:
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x016b }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x016b }
            if (r2 != 0) goto L_0x0124
            r9 = r10
            r2 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r11
            r10 = r12
            r12 = r13
            r13 = r15
            goto L_0x00c3
        L_0x0124:
            r2 = r10
            java.lang.Object r10 = r13.next()     // Catch:{ all -> 0x015f }
            java.lang.Object r3 = r11.invoke(r9, r10)     // Catch:{ all -> 0x015f }
            r1.L$0 = r15     // Catch:{ all -> 0x015f }
            r1.L$1 = r13     // Catch:{ all -> 0x015f }
            r1.L$2 = r11     // Catch:{ all -> 0x015f }
            r1.L$3 = r12     // Catch:{ all -> 0x015f }
            r1.L$4 = r8     // Catch:{ all -> 0x015f }
            r17 = r2
            r2 = 0
            r1.L$5 = r2     // Catch:{ all -> 0x0155 }
            r2 = 3
            r1.label = r2     // Catch:{ all -> 0x0155 }
            java.lang.Object r2 = r15.send(r3, r1)     // Catch:{ all -> 0x0155 }
            if (r2 != r0) goto L_0x0146
            return r0
        L_0x0146:
            r9 = r17
            r2 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r10 = r12
            r12 = r13
            r7 = r14
            r13 = r15
        L_0x0150:
            r7 = r8
            r8 = r11
            r3 = 0
            goto L_0x00c3
        L_0x0155:
            r0 = move-exception
            r9 = r17
            r2 = r4
            r4 = r5
            r5 = r6
            r10 = r12
            r12 = r13
            r13 = r15
            goto L_0x0189
        L_0x015f:
            r0 = move-exception
            r17 = r2
            r9 = r17
            r2 = r4
            r4 = r5
            r5 = r6
            r10 = r12
            r12 = r13
            r13 = r15
            goto L_0x0189
        L_0x016b:
            r0 = move-exception
            r9 = r10
            r2 = r4
            r4 = r5
            r5 = r6
            r10 = r12
            r12 = r13
            r13 = r15
            goto L_0x0189
        L_0x0174:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x017f }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r11, r10)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x017f:
            r0 = move-exception
            r2 = r4
            r4 = r5
            r5 = r6
            r9 = r10
            r10 = r11
            goto L_0x0189
        L_0x0186:
            r0 = move-exception
            r5 = r4
            r4 = r6
        L_0x0189:
            r3 = r0
            throw r0     // Catch:{ all -> 0x018c }
        L_0x018c:
            r0 = move-exception
            r6 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
