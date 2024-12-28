package com.razorpay;

import android.animation.ValueAnimator;

/* compiled from: CircularProgressView */
final class q$_Y$ implements ValueAnimator.AnimatorUpdateListener {
    private /* synthetic */ CircularProgressView a;

    q$_Y$(CircularProgressView circularProgressView) {
        this.a = circularProgressView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float unused = this.a.startAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.a.invalidate();
    }
}
