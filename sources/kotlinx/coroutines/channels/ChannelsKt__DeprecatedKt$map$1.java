package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {487, 333, 333}, m = "invokeSuspend", n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
/* compiled from: Deprecated.kt */
final class ChannelsKt__DeprecatedKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<E> $this_map;
    final /* synthetic */ Function2<E, Continuation<? super R>, Object> $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$map$1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super ChannelsKt__DeprecatedKt$map$1> continuation) {
        super(2, continuation);
        this.$this_map = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__DeprecatedKt$map$1 channelsKt__DeprecatedKt$map$1 = new ChannelsKt__DeprecatedKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__DeprecatedKt$map$1.L$0 = obj;
        return channelsKt__DeprecatedKt$map$1;
    }

    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__DeprecatedKt$map$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: kotlinx.coroutines.channels.ReceiveChannel<E>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: kotlinx.coroutines.channels.ProducerScope} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.L$0 = r11;
        r1.L$1 = r7;
        r1.L$2 = r9;
        r1.L$3 = r6;
        r1.label = 1;
        r10 = r6.hasNext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b9, code lost:
        if (r10 != r0) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bb, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bc, code lost:
        r15 = r3;
        r3 = r2;
        r2 = r10;
        r10 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r4;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cc, code lost:
        if (((java.lang.Boolean) r2).booleanValue() == false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ce, code lost:
        r2 = r7.next();
        r12 = 0;
        r1.L$0 = r11;
        r1.L$1 = r8;
        r1.L$2 = r10;
        r1.L$3 = r7;
        r1.L$4 = r11;
        r1.label = 2;
        r13 = r8.invoke(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e4, code lost:
        if (r13 != r0) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e6, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e7, code lost:
        r2 = r13;
        r13 = r11;
        r11 = r8;
        r8 = r7;
        r7 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r1.L$0 = r13;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r8;
        r1.L$4 = null;
        r1.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fe, code lost:
        if (r7.send(r2, r1) != r0) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0100, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0101, code lost:
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        r6 = r12;
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010b, code lost:
        r6 = r7;
        r7 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x010f, code lost:
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r8 = r9;
        r9 = r10;
        r11 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0119, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0120, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0121, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0122, code lost:
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r8 = r9;
        r9 = r10;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r1 = r16
            int r2 = r1.label
            switch(r2) {
                case 0: goto L_0x0088;
                case 1: goto L_0x005f;
                case 2: goto L_0x0031;
                case 3: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0013:
            r1 = r16
            r2 = r17
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.Object r7 = r1.L$3
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            r8 = 0
            java.lang.Object r9 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r1.L$1
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0085 }
            goto L_0x010b
        L_0x0031:
            r1 = r16
            r2 = r17
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.Object r7 = r1.L$4
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            java.lang.Object r8 = r1.L$3
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            r9 = 0
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0059 }
            r13 = r12
            r12 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x00ec
        L_0x0059:
            r0 = move-exception
            r8 = r9
            r9 = r10
            r11 = r12
            goto L_0x012b
        L_0x005f:
            r1 = r16
            r2 = r17
            r3 = 0
            r4 = 0
            r5 = 0
            java.lang.Object r6 = r1.L$3
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            r8 = 0
            java.lang.Object r7 = r1.L$2
            r9 = r7
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r7 = r1.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r10 = r1.L$0
            r11 = r10
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0085 }
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x00c6
        L_0x0085:
            r0 = move-exception
            goto L_0x012b
        L_0x0088:
            kotlin.ResultKt.throwOnFailure(r17)
            r1 = r16
            r2 = r17
            java.lang.Object r3 = r1.L$0
            r11 = r3
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlinx.coroutines.channels.ReceiveChannel<E> r3 = r1.$this_map
            kotlin.jvm.functions.Function2<E, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r4 = r1.$transform
            r5 = 0
            r9 = r3
            r3 = 0
            r8 = 0
            r6 = r9
            r7 = 0
            kotlinx.coroutines.channels.ChannelIterator r10 = r6.iterator()     // Catch:{ all -> 0x0128 }
            r6 = r10
            r15 = r4
            r4 = r3
            r3 = r5
            r5 = r7
            r7 = r15
        L_0x00aa:
            r1.L$0 = r11     // Catch:{ all -> 0x0085 }
            r1.L$1 = r7     // Catch:{ all -> 0x0085 }
            r1.L$2 = r9     // Catch:{ all -> 0x0085 }
            r1.L$3 = r6     // Catch:{ all -> 0x0085 }
            r10 = 1
            r1.label = r10     // Catch:{ all -> 0x0085 }
            java.lang.Object r10 = r6.hasNext(r1)     // Catch:{ all -> 0x0085 }
            if (r10 != r0) goto L_0x00bc
            return r0
        L_0x00bc:
            r15 = r3
            r3 = r2
            r2 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r15
        L_0x00c6:
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x0121 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0121 }
            if (r2 == 0) goto L_0x0116
            java.lang.Object r2 = r7.next()     // Catch:{ all -> 0x0121 }
            r12 = 0
            r1.L$0 = r11     // Catch:{ all -> 0x0121 }
            r1.L$1 = r8     // Catch:{ all -> 0x0121 }
            r1.L$2 = r10     // Catch:{ all -> 0x0121 }
            r1.L$3 = r7     // Catch:{ all -> 0x0121 }
            r1.L$4 = r11     // Catch:{ all -> 0x0121 }
            r13 = 2
            r1.label = r13     // Catch:{ all -> 0x0121 }
            java.lang.Object r13 = r8.invoke(r2, r1)     // Catch:{ all -> 0x0121 }
            if (r13 != r0) goto L_0x00e7
            return r0
        L_0x00e7:
            r2 = r13
            r13 = r11
            r11 = r8
            r8 = r7
            r7 = r13
        L_0x00ec:
            r1.L$0 = r13     // Catch:{ all -> 0x010e }
            r1.L$1 = r11     // Catch:{ all -> 0x010e }
            r1.L$2 = r10     // Catch:{ all -> 0x010e }
            r1.L$3 = r8     // Catch:{ all -> 0x010e }
            r14 = 0
            r1.L$4 = r14     // Catch:{ all -> 0x010e }
            r14 = 3
            r1.label = r14     // Catch:{ all -> 0x010e }
            java.lang.Object r2 = r7.send(r2, r1)     // Catch:{ all -> 0x010e }
            if (r2 != r0) goto L_0x0101
            return r0
        L_0x0101:
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r6 = r12
            r11 = r13
        L_0x010b:
            r6 = r7
            r7 = r10
            goto L_0x00aa
        L_0x010e:
            r0 = move-exception
            r2 = r3
            r3 = r4
            r4 = r5
            r8 = r9
            r9 = r10
            r11 = r13
            goto L_0x012b
        L_0x0116:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0121 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0121:
            r0 = move-exception
            r2 = r3
            r3 = r4
            r4 = r5
            r8 = r9
            r9 = r10
            goto L_0x012b
        L_0x0128:
            r0 = move-exception
            r4 = r3
            r3 = r5
        L_0x012b:
            r5 = r0
            throw r0     // Catch:{ all -> 0x012e }
        L_0x012e:
            r0 = move-exception
            r6 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r9, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
