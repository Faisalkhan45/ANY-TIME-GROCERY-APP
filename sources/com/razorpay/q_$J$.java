package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class q_$J$ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ CircularProgressView a;

    q_$J$(CircularProgressView circularProgressView) {
        this.a = circularProgressView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.a.actualProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.a.invalidate();
    }
}
