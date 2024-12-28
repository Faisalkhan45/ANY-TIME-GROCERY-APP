package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.zzab;
import com.google.android.gms.wallet.Wallet;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzaj extends Api.AbstractClientBuilder<zzab, Wallet.WalletOptions> {
    zzaj() {
    }

    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Wallet.WalletOptions walletOptions = (Wallet.WalletOptions) obj;
        if (walletOptions == null) {
            walletOptions = new Wallet.WalletOptions((zzaj) null);
        }
        return new zzab(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, walletOptions.environment, walletOptions.theme, walletOptions.zzb);
    }
}
