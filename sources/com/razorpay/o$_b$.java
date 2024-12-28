package com.razorpay;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.logging.type.LogSeverity;

/* compiled from: RZPProgressBar */
final class o$_b$ {
    private Context a;
    private ViewGroup b;
    private View c;
    private float d;
    private int e;
    private String f;

    public o$_b$(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, (String) null);
    }

    public o$_b$(Context context, ViewGroup viewGroup, String str) {
        int i;
        this.f = str;
        this.a = context;
        this.b = viewGroup;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.d = ((float) displayMetrics.widthPixels) / displayMetrics.density;
        this.e = b(4);
        this.c = new View(this.a);
        this.c.setLayoutParams(new RelativeLayout.LayoutParams(0, this.e));
        if (TextUtils.isEmpty(this.f)) {
            i = b();
        } else {
            try {
                i = Color.parseColor(this.f);
            } catch (IllegalArgumentException e2) {
                i = b();
            }
        }
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * 0.8f;
        int HSVToColor = Color.HSVToColor(fArr);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, HSVToColor});
        gradientDrawable.setCornerRadius(0.0f);
        this.c.setBackgroundDrawable(gradientDrawable);
        this.b.addView(this.c);
    }

    private int b() {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            i = 16843829;
        } else {
            i = this.a.getResources().getIdentifier("colorAccent", "attr", this.a.getPackageName());
        }
        TypedValue typedValue = new TypedValue();
        if (this.a.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.data;
        }
        return Color.parseColor("#4aa3df");
    }

    private int b(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.a.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        if (i == 100) {
            c(200);
        } else {
            a(i, LogSeverity.ERROR_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        c(200);
    }

    /* access modifiers changed from: private */
    public void a(int i, int i2) {
        a aVar = new a(this.c, b((int) ((this.d * ((float) i)) / 100.0f)));
        aVar.setDuration((long) i2);
        this.c.startAnimation(aVar);
        aVar.setAnimationListener(new X$_8_(this));
    }

    private void c(int i) {
        a aVar = new a(this.c, b((int) this.d));
        aVar.setDuration(200);
        this.c.startAnimation(aVar);
        aVar.setAnimationListener(new G_$8_(this));
    }
}
