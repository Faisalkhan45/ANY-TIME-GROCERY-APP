package com.denzcoskun.imageslider;

import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/denzcoskun/imageslider/ImageSlider$setupDots$1", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageSlider.kt */
public final class ImageSlider$setupDots$1 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ ImageSlider this$0;

    ImageSlider$setupDots$1(ImageSlider $outer) {
        this.this$0 = $outer;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.this$0.currentPage = position;
        ImageView[] access$getDots$p = this.this$0.dots;
        if (access$getDots$p == null) {
            Intrinsics.throwNpe();
        }
        for (ImageView dot : access$getDots$p) {
            if (dot == null) {
                Intrinsics.throwNpe();
            }
            dot.setImageDrawable(ContextCompat.getDrawable(this.this$0.getContext(), this.this$0.unselectedDot));
        }
        ImageView[] access$getDots$p2 = this.this$0.dots;
        if (access$getDots$p2 == null) {
            Intrinsics.throwNpe();
        }
        ImageView imageView = access$getDots$p2[position];
        if (imageView == null) {
            Intrinsics.throwNpe();
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(this.this$0.getContext(), this.this$0.selectedDot));
        if (this.this$0.itemChangeListener != null) {
            ItemChangeListener access$getItemChangeListener$p = this.this$0.itemChangeListener;
            if (access$getItemChangeListener$p == null) {
                Intrinsics.throwNpe();
            }
            access$getItemChangeListener$p.onItemChanged(position);
        }
    }

    public void onPageScrollStateChanged(int state) {
    }
}
