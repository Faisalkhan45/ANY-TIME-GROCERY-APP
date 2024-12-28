package com.denzcoskun.imageslider.adapters;

import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, k = 3, mv = {1, 1, 16})
/* compiled from: ViewPagerAdapter.kt */
final class ViewPagerAdapter$instantiateItem$2 implements View.OnTouchListener {
    final /* synthetic */ ViewPagerAdapter this$0;

    ViewPagerAdapter$instantiateItem$2(ViewPagerAdapter viewPagerAdapter) {
        this.this$0 = viewPagerAdapter;
    }

    public final boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkExpressionValueIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        switch (event.getAction()) {
            case 0:
                TouchListener access$getTouchListener$p = this.this$0.touchListener;
                if (access$getTouchListener$p == null) {
                    Intrinsics.throwNpe();
                }
                access$getTouchListener$p.onTouched(ActionTypes.DOWN);
                return false;
            case 1:
                TouchListener access$getTouchListener$p2 = this.this$0.touchListener;
                if (access$getTouchListener$p2 == null) {
                    Intrinsics.throwNpe();
                }
                access$getTouchListener$p2.onTouched(ActionTypes.UP);
                return false;
            case 2:
                TouchListener access$getTouchListener$p3 = this.this$0.touchListener;
                if (access$getTouchListener$p3 == null) {
                    Intrinsics.throwNpe();
                }
                access$getTouchListener$p3.onTouched(ActionTypes.MOVE);
                return false;
            default:
                return false;
        }
    }
}
