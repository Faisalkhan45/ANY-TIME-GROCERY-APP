package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzx implements Parcelable.Creator<PaymentDataRequest> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        CardRequirements cardRequirements = null;
        ShippingAddressRequirements shippingAddressRequirements = null;
        ArrayList<Integer> arrayList = null;
        PaymentMethodTokenizationParameters paymentMethodTokenizationParameters = null;
        TransactionInfo transactionInfo = null;
        String str = null;
        Bundle bundle = null;
        boolean z4 = true;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 2:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 3:
                    cardRequirements = (CardRequirements) SafeParcelReader.createParcelable(parcel2, readHeader, CardRequirements.CREATOR);
                    break;
                case 4:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 5:
                    shippingAddressRequirements = (ShippingAddressRequirements) SafeParcelReader.createParcelable(parcel2, readHeader, ShippingAddressRequirements.CREATOR);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createIntegerList(parcel2, readHeader);
                    break;
                case 7:
                    paymentMethodTokenizationParameters = (PaymentMethodTokenizationParameters) SafeParcelReader.createParcelable(parcel2, readHeader, PaymentMethodTokenizationParameters.CREATOR);
                    break;
                case 8:
                    transactionInfo = (TransactionInfo) SafeParcelReader.createParcelable(parcel2, readHeader, TransactionInfo.CREATOR);
                    break;
                case 9:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 11:
                    bundle = SafeParcelReader.createBundle(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new PaymentDataRequest(z, z2, cardRequirements, z3, shippingAddressRequirements, arrayList, paymentMethodTokenizationParameters, transactionInfo, z4, str, bundle);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PaymentDataRequest[i];
    }
}
