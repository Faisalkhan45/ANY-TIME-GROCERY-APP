package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcelable;

public final class zzs implements Parcelable.Creator<zzr> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r1 = 0
            r3 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x0014:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x0073
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r8) {
                case 1: goto L_0x0065;
                case 2: goto L_0x0053;
                case 3: goto L_0x0045;
                case 4: goto L_0x0037;
                case 5: goto L_0x0029;
                default: goto L_0x0025;
            }
        L_0x0025:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r1)
            goto L_0x0014
        L_0x0029:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            r1 = 5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0014
        L_0x0037:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            r1 = 4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0014
        L_0x0045:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r10, r1)
            r1 = 3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0014
        L_0x0053:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.accounttransfer.zzt> r4 = com.google.android.gms.auth.api.accounttransfer.zzt.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r4)
            r4 = r1
            com.google.android.gms.auth.api.accounttransfer.zzt r4 = (com.google.android.gms.auth.api.accounttransfer.zzt) r4
            r1 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0014
        L_0x0065:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r1)
            r1 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.add(r1)
            goto L_0x0014
        L_0x0073:
            int r1 = r10.dataPosition()
            if (r1 != r0) goto L_0x0080
            com.google.android.gms.auth.api.accounttransfer.zzr r10 = new com.google.android.gms.auth.api.accounttransfer.zzr
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r10
        L_0x0080:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r1 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r2 = 37
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Overread allowed size end="
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0, r10)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.accounttransfer.zzs.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
