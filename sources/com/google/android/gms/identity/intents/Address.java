package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.identity.zze;

public final class Address {
    public static final Api<AddressOptions> API;
    private static final Api.AbstractClientBuilder<zze, AddressOptions> CLIENT_BUILDER;
    private static final Api.ClientKey<zze> CLIENT_KEY;

    public static final class AddressOptions implements Api.ApiOptions.HasOptions {
        public final int theme;

        public AddressOptions() {
            this.theme = 0;
        }

        public AddressOptions(int i) {
            this.theme = i;
        }
    }

    private static abstract class zza extends BaseImplementation.ApiMethodImpl<Status, zze> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) Address.API, googleApiClient);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    static {
        Api.ClientKey<zze> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zza zza2 = new zza();
        CLIENT_BUILDER = zza2;
        API = new Api<>("Address.API", zza2, clientKey);
    }

    public static void requestUserAddress(GoogleApiClient googleApiClient, UserAddressRequest userAddressRequest, int i) {
        googleApiClient.enqueue(new zzb(googleApiClient, userAddressRequest, i));
    }
}
