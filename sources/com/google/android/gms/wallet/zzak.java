package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.wallet.zzab;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public abstract class zzak<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzab> {
    public zzak(GoogleApiClient googleApiClient) {
        super((Api<?>) Wallet.API, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public abstract void doExecute(zzab zzab) throws RemoteException;
}
