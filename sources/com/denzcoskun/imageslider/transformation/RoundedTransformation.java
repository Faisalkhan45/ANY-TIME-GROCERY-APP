package com.denzcoskun.imageslider.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001*B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\nH\u0002J(\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J(\u0010$\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/denzcoskun/imageslider/transformation/RoundedTransformation;", "Lcom/squareup/picasso/Transformation;", "radius", "", "margin", "cornerType", "Lcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;", "(IILcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;)V", "mCornerType", "mDiameter", "", "mMargin", "mRadius", "drawBottomLeftRoundRect", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "right", "bottom", "drawBottomRightRoundRect", "drawBottomRoundRect", "drawDiagonalFromTopLeftRoundRect", "drawDiagonalFromTopRightRoundRect", "drawLeftRoundRect", "drawOtherBottomLeftRoundRect", "drawOtherBottomRightRoundRect", "drawOtherTopLeftRoundRect", "drawOtherTopRightRoundRect", "drawRightRoundRect", "drawRoundRect", "width", "height", "drawTopLeftRoundRect", "drawTopRightRoundRect", "drawTopRoundRect", "key", "", "transform", "Landroid/graphics/Bitmap;", "source", "CornerType", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RoundedTransformation.kt */
public final class RoundedTransformation implements Transformation {
    private CornerType mCornerType;
    private float mDiameter;
    private float mMargin;
    private float mRadius;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/denzcoskun/imageslider/transformation/RoundedTransformation$CornerType;", "", "(Ljava/lang/String;I)V", "ALL", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT", "TOP", "BOTTOM", "LEFT", "RIGHT", "OTHER_TOP_LEFT", "OTHER_TOP_RIGHT", "OTHER_BOTTOM_LEFT", "OTHER_BOTTOM_RIGHT", "DIAGONAL_FROM_TOP_LEFT", "DIAGONAL_FROM_TOP_RIGHT", "imageslider_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RoundedTransformation.kt */
    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CornerType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[CornerType.ALL.ordinal()] = 1;
            iArr[CornerType.TOP_LEFT.ordinal()] = 2;
            iArr[CornerType.TOP_RIGHT.ordinal()] = 3;
            iArr[CornerType.BOTTOM_LEFT.ordinal()] = 4;
            iArr[CornerType.BOTTOM_RIGHT.ordinal()] = 5;
            iArr[CornerType.TOP.ordinal()] = 6;
            iArr[CornerType.BOTTOM.ordinal()] = 7;
            iArr[CornerType.LEFT.ordinal()] = 8;
            iArr[CornerType.RIGHT.ordinal()] = 9;
            iArr[CornerType.OTHER_TOP_LEFT.ordinal()] = 10;
            iArr[CornerType.OTHER_TOP_RIGHT.ordinal()] = 11;
            iArr[CornerType.OTHER_BOTTOM_LEFT.ordinal()] = 12;
            iArr[CornerType.OTHER_BOTTOM_RIGHT.ordinal()] = 13;
            iArr[CornerType.DIAGONAL_FROM_TOP_LEFT.ordinal()] = 14;
            iArr[CornerType.DIAGONAL_FROM_TOP_RIGHT.ordinal()] = 15;
        }
    }

    public RoundedTransformation(int i, int i2) {
        this(i, i2, (CornerType) null, 4, (DefaultConstructorMarker) null);
    }

    public RoundedTransformation(int radius, int margin, CornerType cornerType) {
        Intrinsics.checkParameterIsNotNull(cornerType, "cornerType");
        this.mRadius = (float) radius;
        this.mDiameter = (float) (radius * 2);
        this.mMargin = (float) margin;
        this.mCornerType = cornerType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoundedTransformation(int i, int i2, CornerType cornerType, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? CornerType.ALL : cornerType);
    }

    public Bitmap transform(Bitmap source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, (float) width, (float) height);
        source.recycle();
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return bitmap;
    }

    private final void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float f = this.mMargin;
        float right = width - f;
        float bottom = height - f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.mCornerType.ordinal()]) {
            case 1:
                float f2 = this.mMargin;
                RectF rectF = new RectF(f2, f2, right, bottom);
                float f3 = this.mRadius;
                canvas.drawRoundRect(rectF, f3, f3, paint);
                return;
            case 2:
                drawTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 3:
                drawTopRightRoundRect(canvas, paint, right, bottom);
                return;
            case 4:
                drawBottomLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 5:
                drawBottomRightRoundRect(canvas, paint, right, bottom);
                return;
            case 6:
                drawTopRoundRect(canvas, paint, right, bottom);
                return;
            case 7:
                drawBottomRoundRect(canvas, paint, right, bottom);
                return;
            case 8:
                drawLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 9:
                drawRightRoundRect(canvas, paint, right, bottom);
                return;
            case 10:
                drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 11:
                drawOtherTopRightRoundRect(canvas, paint, right, bottom);
                return;
            case 12:
                drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 13:
                drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
                return;
            case 14:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case 15:
                drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
                return;
            default:
                float f4 = this.mMargin;
                RectF rectF2 = new RectF(f4, f4, right, bottom);
                float f5 = this.mRadius;
                canvas.drawRoundRect(rectF2, f5, f5, paint);
                return;
        }
    }

    private final void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        float f2 = this.mDiameter;
        RectF rectF = new RectF(f, f, f + f2, f2 + f);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.mMargin;
        float f5 = this.mRadius;
        canvas.drawRect(new RectF(f4, f4 + f5, f5 + f4, bottom), paint);
        float f6 = this.mMargin;
        canvas.drawRect(new RectF(this.mRadius + f6, f6, right, bottom), paint);
    }

    private final void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mDiameter;
        float f2 = this.mMargin;
        RectF rectF = new RectF(right - f, f2, right, f + f2);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.mMargin;
        canvas.drawRect(new RectF(f4, f4, right - this.mRadius, bottom), paint);
        float f5 = this.mRadius;
        canvas.drawRect(new RectF(right - f5, this.mMargin + f5, right, bottom), paint);
    }

    private final void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        float f2 = this.mDiameter;
        RectF rectF = new RectF(f, bottom - f2, f2 + f, bottom);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.mMargin;
        canvas.drawRect(new RectF(f4, f4, this.mDiameter + f4, bottom - this.mRadius), paint);
        float f5 = this.mMargin;
        canvas.drawRect(new RectF(this.mRadius + f5, f5, right, bottom), paint);
    }

    private final void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mDiameter;
        RectF rectF = new RectF(right - f, bottom - f, right, bottom);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        float f3 = this.mMargin;
        canvas.drawRect(new RectF(f3, f3, right - this.mRadius, bottom), paint);
        float f4 = this.mRadius;
        canvas.drawRect(new RectF(right - f4, this.mMargin, right, bottom - f4), paint);
    }

    private final void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        RectF rectF = new RectF(f, f, right, this.mDiameter + f);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        float f3 = this.mMargin;
        canvas.drawRect(new RectF(f3, this.mRadius + f3, right, bottom), paint);
    }

    private final void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        RectF rectF = new RectF(this.mMargin, bottom - this.mDiameter, right, bottom);
        float f = this.mRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
        float f2 = this.mMargin;
        canvas.drawRect(new RectF(f2, f2, right, bottom - this.mRadius), paint);
    }

    private final void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        RectF rectF = new RectF(f, f, this.mDiameter + f, bottom);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        float f3 = this.mMargin;
        canvas.drawRect(new RectF(this.mRadius + f3, f3, right, bottom), paint);
    }

    private final void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        RectF rectF = new RectF(right - this.mDiameter, this.mMargin, right, bottom);
        float f = this.mRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
        float f2 = this.mMargin;
        canvas.drawRect(new RectF(f2, f2, right - this.mRadius, bottom), paint);
    }

    private final void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        RectF rectF = new RectF(this.mMargin, bottom - this.mDiameter, right, bottom);
        float f = this.mRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
        RectF rectF2 = new RectF(right - this.mDiameter, this.mMargin, right, bottom);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF2, f2, f2, paint);
        float f3 = this.mMargin;
        float f4 = this.mRadius;
        canvas.drawRect(new RectF(f3, f3, right - f4, bottom - f4), paint);
    }

    private final void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        RectF rectF = new RectF(f, f, this.mDiameter + f, bottom);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        RectF rectF2 = new RectF(this.mMargin, bottom - this.mDiameter, right, bottom);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF2, f3, f3, paint);
        float f4 = this.mMargin;
        float f5 = this.mRadius;
        canvas.drawRect(new RectF(f4 + f5, f4, right, bottom - f5), paint);
    }

    private final void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        RectF rectF = new RectF(f, f, right, this.mDiameter + f);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        RectF rectF2 = new RectF(right - this.mDiameter, this.mMargin, right, bottom);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF2, f3, f3, paint);
        float f4 = this.mMargin;
        float f5 = this.mRadius;
        canvas.drawRect(new RectF(f4, f4 + f5, right - f5, bottom), paint);
    }

    private final void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        RectF rectF = new RectF(f, f, right, this.mDiameter + f);
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        float f3 = this.mMargin;
        RectF rectF2 = new RectF(f3, f3, this.mDiameter + f3, bottom);
        float f4 = this.mRadius;
        canvas.drawRoundRect(rectF2, f4, f4, paint);
        float f5 = this.mMargin;
        float f6 = this.mRadius;
        canvas.drawRect(new RectF(f5 + f6, f5 + f6, right, bottom), paint);
    }

    private final void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mMargin;
        float f2 = this.mDiameter;
        RectF rectF = new RectF(f, f, f + f2, f2 + f);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.mDiameter;
        RectF rectF2 = new RectF(right - f4, bottom - f4, right, bottom);
        float f5 = this.mRadius;
        canvas.drawRoundRect(rectF2, f5, f5, paint);
        float f6 = this.mMargin;
        canvas.drawRect(new RectF(f6, this.mRadius + f6, right - this.mDiameter, bottom), paint);
        float f7 = this.mMargin;
        canvas.drawRect(new RectF(this.mDiameter + f7, f7, right, bottom - this.mRadius), paint);
    }

    private final void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        float f = this.mDiameter;
        float f2 = this.mMargin;
        RectF rectF = new RectF(right - f, f2, right, f + f2);
        float f3 = this.mRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.mMargin;
        float f5 = this.mDiameter;
        RectF rectF2 = new RectF(f4, bottom - f5, f5 + f4, bottom);
        float f6 = this.mRadius;
        canvas.drawRoundRect(rectF2, f6, f6, paint);
        float f7 = this.mMargin;
        float f8 = this.mRadius;
        canvas.drawRect(new RectF(f7, f7, right - f8, bottom - f8), paint);
        float f9 = this.mMargin;
        float f10 = this.mRadius;
        canvas.drawRect(new RectF(f9 + f10, f9 + f10, right, bottom), paint);
    }

    public String key() {
        return "RoundedTransformation(radius=" + this.mRadius + ", margin=" + this.mMargin + ", diameter=" + this.mDiameter + ", cornerType=" + this.mCornerType.name() + ")";
    }
}
