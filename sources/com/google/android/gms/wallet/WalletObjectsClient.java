package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public class WalletObjectsClient extends GoogleApi<Wallet.WalletOptions> {
    public static final /* synthetic */ int zza = 0;

    WalletObjectsClient(Activity activity, Wallet.WalletOptions walletOptions) {
        super(activity, Wallet.API, walletOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<AutoResolvableVoidResult> createWalletObjects(CreateWalletObjectsRequest request) {
        return doWrite(TaskApiCall.builder().setMethodKey(23708).run(new zzam(request)).build());
    }
}
