package com.google.android.gms.internal.wallet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.zzal;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzad extends zzal {
    final /* synthetic */ CreateWalletObjectsRequest zza;
    final /* synthetic */ int zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(zzae zzae, GoogleApiClient googleApiClient, CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        super(googleApiClient);
        this.zza = createWalletObjectsRequest;
        this.zzb = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final void doExecute(zzab zzab) {
        zzab.zzp(this.zza, this.zzb);
        setResult(Status.RESULT_SUCCESS);
    }
}
