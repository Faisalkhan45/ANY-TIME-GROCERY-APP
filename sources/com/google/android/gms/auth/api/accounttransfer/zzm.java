package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

public final class zzm implements Parcelable.Creator<zzl> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzl[i];
    }

    /* JADX WARNING: type inference failed for: r1v12, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r9) {
        /*
            r8 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r9)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r1 = 0
            r3 = 0
            r4 = r1
            r6 = r4
            r5 = r3
        L_0x0012:
            int r1 = r9.dataPosition()
            if (r1 >= r0) goto L_0x0064
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r9)
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r7) {
                case 1: goto L_0x0056;
                case 2: goto L_0x0047;
                case 3: goto L_0x0039;
                case 4: goto L_0x0027;
                default: goto L_0x0023;
            }
        L_0x0023:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r9, r1)
            goto L_0x0012
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzo> r6 = com.google.android.gms.auth.api.accounttransfer.zzo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r9, r1, r6)
            r6 = r1
            com.google.android.gms.auth.api.accounttransfer.zzo r6 = (com.google.android.gms.auth.api.accounttransfer.zzo) r6
            r1 = 4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0012
        L_0x0039:
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r1)
            r1 = 3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0012
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzr> r4 = com.google.android.gms.auth.api.accounttransfer.zzr.CREATOR
            java.util.ArrayList r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r9, r1, r4)
            r1 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0012
        L_0x0056:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r9, r1)
            r1 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0012
        L_0x0064:
            int r1 = r9.dataPosition()
            if (r1 != r0) goto L_0x0071
            com.google.android.gms.auth.api.accounttransfer.zzl r9 = new com.google.android.gms.auth.api.accounttransfer.zzl
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return r9
        L_0x0071:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r1 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r2 = 37
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Overread allowed size end="
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0, r9)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzm.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
