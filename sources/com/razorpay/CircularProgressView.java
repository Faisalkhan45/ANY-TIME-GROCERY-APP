package com.razorpay;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.google.logging.type.LogSeverity;

class CircularProgressView extends View {
    private static final float INDETERMINANT_MIN_SWEEP = 15.0f;
    /* access modifiers changed from: private */
    public float actualProgress;
    private int animDuration;
    private int animSteps;
    private int animSwoopDuration;
    private int animSyncDuration;
    private boolean autostartAnimation;
    private RectF bounds;
    private int color;
    private Context context;
    private float currentProgress;
    private AnimatorSet indeterminateAnimator;
    /* access modifiers changed from: private */
    public float indeterminateRotateOffset;
    /* access modifiers changed from: private */
    public float indeterminateSweep;
    private float initialStartAngle;
    private boolean isIndeterminate;
    private float maxProgress;
    private Paint paint;
    private ValueAnimator progressAnimator;
    private int size = 0;
    /* access modifiers changed from: private */
    public float startAngle;
    private ValueAnimator startAngleRotate;
    private int thickness;

    public CircularProgressView(Context context2) {
        super(context2);
        init((AttributeSet) null, 0, context2);
    }

    public CircularProgressView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(attributeSet, 0, context2);
    }

    public CircularProgressView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(attributeSet, i, context2);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet, int i, Context context2) {
        initAttributes(attributeSet, i, context2);
        this.paint = new Paint(1);
        updatePaint();
        this.bounds = new RectF();
    }

    private void initAttributes(AttributeSet attributeSet, int i, Context context2) {
        getResources();
        this.currentProgress = 0.0f;
        this.maxProgress = 100.0f;
        this.thickness = convertDPtoInt(context2, 3);
        this.isIndeterminate = true;
        this.autostartAnimation = true;
        this.initialStartAngle = -90.0f;
        this.startAngle = -90.0f;
        this.color = Color.parseColor("#4aa3df");
        this.animDuration = 4000;
        this.animSwoopDuration = 5000;
        this.animSyncDuration = LogSeverity.ERROR_VALUE;
        this.animSteps = 3;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int measuredWidth = getMeasuredWidth() - paddingLeft;
        int measuredHeight = getMeasuredHeight() - paddingTop;
        if (measuredWidth >= measuredHeight) {
            measuredWidth = measuredHeight;
        }
        this.size = measuredWidth;
        setMeasuredDimension(paddingLeft + measuredWidth, measuredWidth + paddingTop);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i >= i2) {
            i = i2;
        }
        this.size = i;
        updateBounds();
    }

    private void updateBounds() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        RectF rectF = this.bounds;
        int i = this.thickness;
        int i2 = this.size;
        rectF.set((float) (paddingLeft + i), (float) (paddingTop + i), (float) ((i2 - paddingLeft) - i), (float) ((i2 - paddingTop) - i));
    }

    private void updatePaint() {
        this.paint.setColor(this.color);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth((float) this.thickness);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = ((isInEditMode() ? this.currentProgress : this.actualProgress) / this.maxProgress) * 360.0f;
        if (!this.isIndeterminate) {
            canvas.drawArc(this.bounds, this.startAngle, f, false, this.paint);
        } else {
            canvas.drawArc(this.bounds, this.startAngle + this.indeterminateRotateOffset, this.indeterminateSweep, false, this.paint);
        }
    }

    public boolean isIndeterminate() {
        return this.isIndeterminate;
    }

    public void setIndeterminate(boolean z) {
        boolean z2 = this.isIndeterminate == z;
        this.isIndeterminate = z;
        if (z2) {
            resetAnimation();
        }
    }

    public int getThickness() {
        return this.thickness;
    }

    public void setThickness(int i) {
        this.thickness = i;
        updatePaint();
        updateBounds();
        invalidate();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        updatePaint();
        invalidate();
    }

    public float getMaxProgress() {
        return this.maxProgress;
    }

    public void setMaxProgress(float f) {
        this.maxProgress = f;
        invalidate();
    }

    public float getProgress() {
        return this.currentProgress;
    }

    public void setProgress(float f) {
        this.currentProgress = f;
        if (!this.isIndeterminate) {
            ValueAnimator valueAnimator = this.progressAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.progressAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.actualProgress, f});
            this.progressAnimator = ofFloat;
            ofFloat.setDuration((long) this.animSyncDuration);
            this.progressAnimator.setInterpolator(new LinearInterpolator());
            this.progressAnimator.addUpdateListener(new B$$Z_(this));
            this.progressAnimator.start();
        }
        invalidate();
    }

    public void startAnimation() {
        resetAnimation();
    }

    public void resetAnimation() {
        ValueAnimator valueAnimator = this.startAngleRotate;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.startAngleRotate.cancel();
        }
        ValueAnimator valueAnimator2 = this.progressAnimator;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            this.progressAnimator.cancel();
        }
        AnimatorSet animatorSet = this.indeterminateAnimator;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.indeterminateAnimator.cancel();
        }
        int i = 0;
        if (!this.isIndeterminate) {
            float f = this.initialStartAngle;
            this.startAngle = f;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f + 360.0f});
            this.startAngleRotate = ofFloat;
            ofFloat.setDuration((long) this.animSwoopDuration);
            this.startAngleRotate.setInterpolator(new DecelerateInterpolator(2.0f));
            this.startAngleRotate.addUpdateListener(new q$_Y$(this));
            this.startAngleRotate.start();
            this.actualProgress = 0.0f;
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, this.currentProgress});
            this.progressAnimator = ofFloat2;
            ofFloat2.setDuration((long) this.animSyncDuration);
            this.progressAnimator.setInterpolator(new LinearInterpolator());
            this.progressAnimator.addUpdateListener(new q_$J$(this));
            this.progressAnimator.start();
            return;
        }
        this.indeterminateSweep = INDETERMINANT_MIN_SWEEP;
        this.indeterminateAnimator = new AnimatorSet();
        AnimatorSet animatorSet2 = null;
        while (i < this.animSteps) {
            AnimatorSet createIndeterminateAnimator = createIndeterminateAnimator((float) i);
            AnimatorSet.Builder play = this.indeterminateAnimator.play(createIndeterminateAnimator);
            if (animatorSet2 != null) {
                play.after(animatorSet2);
            }
            i++;
            animatorSet2 = createIndeterminateAnimator;
        }
        this.indeterminateAnimator.addListener(new v__i_(this));
        this.indeterminateAnimator.start();
    }

    public void stopAnimation() {
        ValueAnimator valueAnimator = this.startAngleRotate;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.startAngleRotate = null;
        }
        ValueAnimator valueAnimator2 = this.progressAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
            this.progressAnimator = null;
        }
        AnimatorSet animatorSet = this.indeterminateAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.indeterminateAnimator = null;
        }
    }

    private AnimatorSet createIndeterminateAnimator(float f) {
        int i = this.animSteps;
        float f2 = ((((float) (i - 1)) * 360.0f) / ((float) i)) + INDETERMINANT_MIN_SWEEP;
        float f3 = ((f2 - INDETERMINANT_MIN_SWEEP) * f) - 0.049804688f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{15.0f, f2});
        ofFloat.setDuration((long) ((this.animDuration / this.animSteps) / 2));
        ofFloat.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat.addUpdateListener(new r__Q_(this));
        int i2 = this.animSteps;
        float f4 = (0.5f + f) * 720.0f;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(f * 720.0f) / ((float) i2), f4 / ((float) i2)});
        ofFloat2.setDuration((long) ((this.animDuration / this.animSteps) / 2));
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.addUpdateListener(new a$_G$(this));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{f3, (f3 + f2) - INDETERMINANT_MIN_SWEEP});
        ofFloat3.setDuration((long) ((this.animDuration / this.animSteps) / 2));
        ofFloat3.setInterpolator(new DecelerateInterpolator(1.0f));
        ofFloat3.addUpdateListener(new Q__8_(this, f2, f3));
        int i3 = this.animSteps;
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(new float[]{f4 / ((float) i3), ((f + 1.0f) * 720.0f) / ((float) i3)});
        ofFloat4.setDuration((long) ((this.animDuration / this.animSteps) / 2));
        ofFloat4.setInterpolator(new LinearInterpolator());
        ofFloat4.addUpdateListener(new Y_$H_(this));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.play(ofFloat3).with(ofFloat4).after(ofFloat2);
        return animatorSet;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.autostartAnimation) {
            startAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (i == visibility) {
            return;
        }
        if (i == 0) {
            resetAnimation();
        } else if (i == 8 || i == 4) {
            stopAnimation();
        }
    }

    private int convertDPtoInt(Context context2, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context2.getResources().getDisplayMetrics());
    }
}
