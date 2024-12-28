package com.razorpay;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* compiled from: RZPProgressBar */
final class a extends Animation {
    private int a;
    private int b;
    private View c;

    a(View view, int i) {
        this.c = view;
        this.a = i;
        this.b = view.getWidth();
    }

    /* access modifiers changed from: protected */
    public final void applyTransformation(float f, Transformation transformation) {
        int i = this.b;
        this.c.getLayoutParams().width = i + ((int) (((float) (this.a - i)) * f));
        this.c.requestLayout();
    }

    public final void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
    }

    public final boolean willChangeBounds() {
        return true;
    }
}
