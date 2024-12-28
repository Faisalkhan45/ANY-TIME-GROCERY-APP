package com.razorpay;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;

/* compiled from: CheckoutUtils */
final class Q_$2$ {
    private View a;
    private int b;
    private FrameLayout.LayoutParams c;
    private int d;

    static /* synthetic */ void a(Q_$2$ q_$2$) {
        Rect rect = new Rect();
        q_$2$.a.getWindowVisibleDisplayFrame(rect);
        int i = rect.bottom - rect.top;
        if (i != q_$2$.b) {
            int height = q_$2$.a.getRootView().getHeight();
            if (height - i > height / 4) {
                q_$2$.c.height = i;
            } else {
                q_$2$.c.height = q_$2$.d;
            }
            q_$2$.a.requestLayout();
            q_$2$.b = i;
        }
    }

    static void a(Activity activity) {
        new Q_$2$(activity);
    }

    private Q_$2$(Activity activity) {
        View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.a = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new D$_X_(this));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.a.getLayoutParams();
        this.c = layoutParams;
        this.d = layoutParams.height;
    }
}
