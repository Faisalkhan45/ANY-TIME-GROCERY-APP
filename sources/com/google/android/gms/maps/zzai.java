package com.google.android.gms.maps;

import android.os.Parcelable;

public final class zzai implements Parcelable.Creator<StreetViewPanoramaOptions> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r2 = 0
            r8 = r1
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r13 = r7
        L_0x001a:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0077
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x006d;
                case 3: goto L_0x0067;
                case 4: goto L_0x005d;
                case 5: goto L_0x0057;
                case 6: goto L_0x0051;
                case 7: goto L_0x004b;
                case 8: goto L_0x0045;
                case 9: goto L_0x003f;
                case 10: goto L_0x0039;
                case 11: goto L_0x002f;
                default: goto L_0x002b;
            }
        L_0x002b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x001a
        L_0x002f:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StreetViewSource> r2 = com.google.android.gms.maps.model.StreetViewSource.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r13 = r1
            com.google.android.gms.maps.model.StreetViewSource r13 = (com.google.android.gms.maps.model.StreetViewSource) r13
            goto L_0x001a
        L_0x0039:
            byte r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r15, r1)
            goto L_0x001a
        L_0x003f:
            byte r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r15, r1)
            goto L_0x001a
        L_0x0045:
            byte r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r15, r1)
            goto L_0x001a
        L_0x004b:
            byte r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r15, r1)
            goto L_0x001a
        L_0x0051:
            byte r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readByte(r15, r1)
            goto L_0x001a
        L_0x0057:
            java.lang.Integer r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r15, r1)
            goto L_0x001a
        L_0x005d:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r2 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r6 = r1
            com.google.android.gms.maps.model.LatLng r6 = (com.google.android.gms.maps.model.LatLng) r6
            goto L_0x001a
        L_0x0067:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x001a
        L_0x006d:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.StreetViewPanoramaCamera> r2 = com.google.android.gms.maps.model.StreetViewPanoramaCamera.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r4 = r1
            com.google.android.gms.maps.model.StreetViewPanoramaCamera r4 = (com.google.android.gms.maps.model.StreetViewPanoramaCamera) r4
            goto L_0x001a
        L_0x0077:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.maps.StreetViewPanoramaOptions r15 = new com.google.android.gms.maps.StreetViewPanoramaOptions
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.zzai.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
