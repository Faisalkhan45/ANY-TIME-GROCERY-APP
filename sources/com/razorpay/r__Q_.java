package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class r__Q_ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ CircularProgressView a;

    r__Q_(CircularProgressView circularProgressView) {
        this.a = circularProgressView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.a.indeterminateSweep = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.a.invalidate();
    }
}
