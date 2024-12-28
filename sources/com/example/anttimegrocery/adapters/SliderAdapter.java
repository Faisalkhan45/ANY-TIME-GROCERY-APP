package com.example.anttimegrocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.example.anttimegrocery.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    int[] descriptionArray = {R.string.descriptions, R.string.descriptions, R.string.descriptions};
    int[] headingArray = {R.string.first_slide1, R.string.second_slide, R.string.third_slide};
    int[] imageArray = {R.drawable.onboardscreen1, R.drawable.onboardscreen2, R.drawable.onboardscreen3};
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context2) {
        this.context = context2;
    }

    public int getCount() {
        return this.headingArray.length;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isViewFromObject(android.view.View r2, java.lang.Object r3) {
        /*
            r1 = this;
            r0 = r3
            androidx.constraintlayout.widget.ConstraintLayout r0 = (androidx.constraintlayout.widget.ConstraintLayout) r0
            if (r2 != r0) goto L_0x0007
            r0 = 1
            goto L_0x0008
        L_0x0007:
            r0 = 0
        L_0x0008:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.anttimegrocery.adapters.SliderAdapter.isViewFromObject(android.view.View, java.lang.Object):boolean");
    }

    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater2 = (LayoutInflater) this.context.getSystemService("layout_inflater");
        this.layoutInflater = layoutInflater2;
        View view = layoutInflater2.inflate(R.layout.sliding_layout, container, false);
        ((ImageView) view.findViewById(R.id.slider_img)).setImageResource(this.imageArray[position]);
        ((TextView) view.findViewById(R.id.heading)).setText(this.headingArray[position]);
        ((TextView) view.findViewById(R.id.description)).setText(this.descriptionArray[position]);
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
