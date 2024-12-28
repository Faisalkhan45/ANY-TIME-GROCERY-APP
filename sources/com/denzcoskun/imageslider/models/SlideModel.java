package com.denzcoskun.imageslider.models;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\u0018\u00002\u00020\u0001B)\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B)\b\u0016\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u000bB\u001b\b\u0016\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\fR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015¨\u0006\u001c"}, d2 = {"Lcom/denzcoskun/imageslider/models/SlideModel;", "", "imageUrl", "", "title", "scaleType", "Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "(Ljava/lang/String;Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "imagePath", "", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "(Ljava/lang/String;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "(Ljava/lang/Integer;Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "getImagePath", "()Ljava/lang/Integer;", "setImagePath", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getImageUrl", "()Ljava/lang/String;", "setImageUrl", "(Ljava/lang/String;)V", "getScaleType", "()Lcom/denzcoskun/imageslider/constants/ScaleTypes;", "setScaleType", "(Lcom/denzcoskun/imageslider/constants/ScaleTypes;)V", "getTitle", "setTitle", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SlideModel.kt */
public final class SlideModel {
    private Integer imagePath;
    private String imageUrl;
    private ScaleTypes scaleType;
    private String title;

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public final Integer getImagePath() {
        return this.imagePath;
    }

    public final void setImagePath(Integer num) {
        this.imagePath = num;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final ScaleTypes getScaleType() {
        return this.scaleType;
    }

    public final void setScaleType(ScaleTypes scaleTypes) {
        this.scaleType = scaleTypes;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SlideModel(java.lang.String r2, java.lang.String r3, com.denzcoskun.imageslider.constants.ScaleTypes r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r1 = this;
            r6 = r5 & 2
            r0 = 0
            if (r6 == 0) goto L_0x0009
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            r3 = r0
        L_0x0009:
            r5 = r5 & 4
            if (r5 == 0) goto L_0x0011
            r4 = r0
            com.denzcoskun.imageslider.constants.ScaleTypes r4 = (com.denzcoskun.imageslider.constants.ScaleTypes) r4
            r4 = r0
        L_0x0011:
            r1.<init>((java.lang.String) r2, (java.lang.String) r3, (com.denzcoskun.imageslider.constants.ScaleTypes) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.denzcoskun.imageslider.models.SlideModel.<init>(java.lang.String, java.lang.String, com.denzcoskun.imageslider.constants.ScaleTypes, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public SlideModel(String imageUrl2, String title2, ScaleTypes scaleType2) {
        this.imagePath = 0;
        this.imageUrl = imageUrl2;
        this.title = title2;
        this.scaleType = scaleType2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SlideModel(java.lang.Integer r2, java.lang.String r3, com.denzcoskun.imageslider.constants.ScaleTypes r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r1 = this;
            r6 = r5 & 2
            r0 = 0
            if (r6 == 0) goto L_0x0009
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            r3 = r0
        L_0x0009:
            r5 = r5 & 4
            if (r5 == 0) goto L_0x0011
            r4 = r0
            com.denzcoskun.imageslider.constants.ScaleTypes r4 = (com.denzcoskun.imageslider.constants.ScaleTypes) r4
            r4 = r0
        L_0x0011:
            r1.<init>((java.lang.Integer) r2, (java.lang.String) r3, (com.denzcoskun.imageslider.constants.ScaleTypes) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.denzcoskun.imageslider.models.SlideModel.<init>(java.lang.Integer, java.lang.String, com.denzcoskun.imageslider.constants.ScaleTypes, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public SlideModel(Integer imagePath2, String title2, ScaleTypes scaleType2) {
        this.imagePath = 0;
        this.imagePath = imagePath2;
        this.title = title2;
        this.scaleType = scaleType2;
    }

    public SlideModel(String imageUrl2, ScaleTypes scaleType2) {
        this.imagePath = 0;
        this.imageUrl = imageUrl2;
        this.scaleType = scaleType2;
    }

    public SlideModel(Integer imagePath2, ScaleTypes scaleType2) {
        this.imagePath = 0;
        this.imagePath = imagePath2;
        this.scaleType = scaleType2;
    }
}
