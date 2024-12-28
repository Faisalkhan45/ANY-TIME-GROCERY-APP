package com.razorpay;

import android.view.animation.Animation;

/* compiled from: RZPProgressBar */
final class G_$8_ implements Animation.AnimationListener {
    private /* synthetic */ o$_b$ a;

    G_$8_(o$_b$ o__b_) {
        this.a = o__b_;
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.a(0, 10);
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
