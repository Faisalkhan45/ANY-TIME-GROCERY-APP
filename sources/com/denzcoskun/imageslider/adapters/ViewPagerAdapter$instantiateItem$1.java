package com.denzcoskun.imageslider.adapters;

import android.view.View;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: ViewPagerAdapter.kt */
final class ViewPagerAdapter$instantiateItem$1 implements View.OnClickListener {
    final /* synthetic */ int $position;
    final /* synthetic */ ViewPagerAdapter this$0;

    ViewPagerAdapter$instantiateItem$1(ViewPagerAdapter viewPagerAdapter, int i) {
        this.this$0 = viewPagerAdapter;
        this.$position = i;
    }

    public final void onClick(View it) {
        ItemClickListener access$getItemClickListener$p = this.this$0.itemClickListener;
        if (access$getItemClickListener$p != null) {
            access$getItemClickListener$p.onItemSelected(this.$position);
        }
    }
}
