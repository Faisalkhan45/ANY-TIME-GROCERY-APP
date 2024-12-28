package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class a$_G$ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ CircularProgressView a;

    a$_G$(CircularProgressView circularProgressView) {
        this.a = circularProgressView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.a.indeterminateRotateOffset = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }
}
