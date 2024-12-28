package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class Y_$H_ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ CircularProgressView a;

    Y_$H_(CircularProgressView circularProgressView) {
        this.a = circularProgressView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.a.indeterminateRotateOffset = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }
}
