package com.denzcoskun.imageslider;

import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: ImageSlider.kt */
final class ImageSlider$scheduleTimer$update$1 implements Runnable {
    final /* synthetic */ ImageSlider this$0;

    ImageSlider$scheduleTimer$update$1(ImageSlider imageSlider) {
        this.this$0 = imageSlider;
    }

    public final void run() {
        if (this.this$0.currentPage == this.this$0.imageCount) {
            this.this$0.currentPage = 0;
        }
        ViewPager access$getViewPager$p = this.this$0.viewPager;
        if (access$getViewPager$p == null) {
            Intrinsics.throwNpe();
        }
        ImageSlider imageSlider = this.this$0;
        int access$getCurrentPage$p = imageSlider.currentPage;
        imageSlider.currentPage = access$getCurrentPage$p + 1;
        access$getViewPager$p.setCurrentItem(access$getCurrentPage$p, true);
    }
}
