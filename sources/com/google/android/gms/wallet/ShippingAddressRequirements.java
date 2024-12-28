package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ShippingAddressRequirements> CREATOR = new zzag();
    ArrayList<String> zza;

    /* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
    public final class Builder {
        /* synthetic */ Builder(zzaf zzaf) {
        }

        public Builder addAllowedCountryCode(String allowedCountryCode) {
            Preconditions.checkNotEmpty(allowedCountryCode, "allowedCountryCode can't be null or empty! If you don't have restrictions, just leave it unset.");
            ShippingAddressRequirements shippingAddressRequirements = ShippingAddressRequirements.this;
            if (shippingAddressRequirements.zza == null) {
                shippingAddressRequirements.zza = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zza.add(allowedCountryCode);
            return this;
        }

        public Builder addAllowedCountryCodes(Collection<String> allowedCountryCodes) {
            if (allowedCountryCodes == null || allowedCountryCodes.isEmpty()) {
                throw new IllegalArgumentException("allowedCountryCodes can't be null or empty! If you don't have restrictions, just leave it unset.");
            }
            ShippingAddressRequirements shippingAddressRequirements = ShippingAddressRequirements.this;
            if (shippingAddressRequirements.zza == null) {
                shippingAddressRequirements.zza = new ArrayList<>();
            }
            ShippingAddressRequirements.this.zza.addAll(allowedCountryCodes);
            return this;
        }

        public ShippingAddressRequirements build() {
            return ShippingAddressRequirements.this;
        }
    }

    private ShippingAddressRequirements() {
    }

    public static Builder newBuilder() {
        return new Builder((zzaf) null);
    }

    public ArrayList<String> getAllowedCountryCodes() {
        return this.zza;
    }

    public void writeToParcel(Parcel out, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeStringList(out, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    ShippingAddressRequirements(ArrayList<String> arrayList) {
        this.zza = arrayList;
    }
}
