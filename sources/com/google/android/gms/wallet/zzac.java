package com.google.android.gms.wallet;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.wallet.zzab;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final /* synthetic */ class zzac implements RemoteCall {
    public final /* synthetic */ PaymentDataRequest zza;

    public /* synthetic */ zzac(PaymentDataRequest paymentDataRequest) {
        this.zza = paymentDataRequest;
    }

    public final void accept(Object obj, Object obj2) {
        int i = PaymentsClient.zza;
        ((zzab) obj).zzs(this.zza, (TaskCompletionSource) obj2);
    }
}
