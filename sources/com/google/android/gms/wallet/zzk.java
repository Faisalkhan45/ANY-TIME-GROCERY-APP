package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzk implements Parcelable.Creator<FullWallet> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        zzad zzad = null;
        String str3 = null;
        zza zza = null;
        zza zza2 = null;
        String[] strArr = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] instrumentInfoArr = null;
        PaymentMethodToken paymentMethodToken = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzad = (zzad) SafeParcelReader.createParcelable(parcel, readHeader, zzad.CREATOR);
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    break;
                case 7:
                    zza2 = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    break;
                case 8:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 9:
                    userAddress = (UserAddress) SafeParcelReader.createParcelable(parcel, readHeader, UserAddress.CREATOR);
                    break;
                case 10:
                    userAddress2 = (UserAddress) SafeParcelReader.createParcelable(parcel, readHeader, UserAddress.CREATOR);
                    break;
                case 11:
                    instrumentInfoArr = (InstrumentInfo[]) SafeParcelReader.createTypedArray(parcel, readHeader, InstrumentInfo.CREATOR);
                    break;
                case 12:
                    paymentMethodToken = (PaymentMethodToken) SafeParcelReader.createParcelable(parcel, readHeader, PaymentMethodToken.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FullWallet(str, str2, zzad, str3, zza, zza2, strArr, userAddress, userAddress2, instrumentInfoArr, paymentMethodToken);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new FullWallet[i];
    }
}
