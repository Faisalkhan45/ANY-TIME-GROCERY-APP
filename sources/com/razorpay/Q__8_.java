package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class Q__8_ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ float a;
    private /* synthetic */ float b;
    private /* synthetic */ CircularProgressView c;

    Q__8_(CircularProgressView circularProgressView, float f, float f2) {
        this.c = circularProgressView;
        this.a = f;
        this.b = f2;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.c.startAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        CircularProgressView circularProgressView = this.c;
        float unused2 = circularProgressView.indeterminateSweep = (this.a - circularProgressView.startAngle) + this.b;
        this.c.invalidate();
    }
}
