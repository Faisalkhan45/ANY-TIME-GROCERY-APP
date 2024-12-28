package com.google.android.gms.wallet;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.wallet.zzab;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final /* synthetic */ class zzam implements RemoteCall {
    public final /* synthetic */ CreateWalletObjectsRequest zza;

    public /* synthetic */ zzam(CreateWalletObjectsRequest createWalletObjectsRequest) {
        this.zza = createWalletObjectsRequest;
    }

    public final void accept(Object obj, Object obj2) {
        int i = WalletObjectsClient.zza;
        ((zzab) obj).zzq(this.zza, (TaskCompletionSource) obj2);
    }
}
