package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzi implements Parcelable.Creator<CreateWalletObjectsRequest> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LoyaltyWalletObject loyaltyWalletObject = null;
        GiftCardWalletObject giftCardWalletObject = null;
        int i = 0;
        OfferWalletObject offerWalletObject = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    loyaltyWalletObject = (LoyaltyWalletObject) SafeParcelReader.createParcelable(parcel, readHeader, LoyaltyWalletObject.CREATOR);
                    break;
                case 3:
                    offerWalletObject = (OfferWalletObject) SafeParcelReader.createParcelable(parcel, readHeader, OfferWalletObject.CREATOR);
                    break;
                case 4:
                    giftCardWalletObject = (GiftCardWalletObject) SafeParcelReader.createParcelable(parcel, readHeader, GiftCardWalletObject.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new CreateWalletObjectsRequest(loyaltyWalletObject, offerWalletObject, giftCardWalletObject, i);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new CreateWalletObjectsRequest[i];
    }
}
