package com.razorpay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: CircularProgressView */
final class v__i_ extends AnimatorListenerAdapter {
    private boolean a = false;
    private /* synthetic */ CircularProgressView b;

    v__i_(CircularProgressView circularProgressView) {
        this.b = circularProgressView;
    }

    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    public final void onAnimationEnd(Animator animator) {
        if (!this.a) {
            this.b.resetAnimation();
        }
    }
}
