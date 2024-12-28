package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public class PaymentsClient extends GoogleApi<Wallet.WalletOptions> {
    public static final /* synthetic */ int zza = 0;

    PaymentsClient(Activity activity, Wallet.WalletOptions walletOptions) {
        super(activity, Wallet.API, walletOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Boolean> isReadyToPay(IsReadyToPayRequest request) {
        return doRead(TaskApiCall.builder().setMethodKey(23705).run(new zzab(request)).build());
    }

    public Task<PaymentData> loadPaymentData(PaymentDataRequest request) {
        return doWrite(TaskApiCall.builder().run(new zzac(request)).setFeatures(zzj.zzc).setAutoResolveMissingFeatures(true).setMethodKey(23707).build());
    }

    PaymentsClient(Context context, Wallet.WalletOptions walletOptions) {
        super(context, Wallet.API, walletOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
