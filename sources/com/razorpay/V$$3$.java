package com.razorpay;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BU\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0006j\b\u0012\u0004\u0012\u00020\u0002`\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0006j\b\u0012\u0004\u0012\u00020\u0002`\u0007\u0012\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0006j\b\u0012\u0004\u0012\u00020\n`\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0006j\b\u0012\u0004\u0012\u00020\u0002`\u0007X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0006j\b\u0012\u0004\u0012\u00020\u0002`\u0007X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0006j\b\u0012\u0004\u0012\u00020\n`\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/razorpay/OpinionatedSoln$MyListAdapter;", "Landroid/widget/ArrayAdapter;", "", "context", "Landroid/app/Activity;", "itemTitles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "itemDescs", "status", "", "(Landroid/app/Activity;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getCount", "", "getView", "Landroid/view/View;", "position", "view", "parent", "Landroid/view/ViewGroup;", "checkout_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: OpinionatedSoln.kt */
public final class V$$3$ extends ArrayAdapter<String> {
    private final Activity a;
    private final ArrayList<String> b;
    private final ArrayList<String> c;
    private final ArrayList<Boolean> d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public V$$3$(Activity activity, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Boolean> arrayList3) {
        super(activity, R.layout.single_item);
        Intrinsics.checkNotNullParameter(activity, "context");
        Intrinsics.checkNotNullParameter(arrayList, "itemTitles");
        Intrinsics.checkNotNullParameter(arrayList2, "itemDescs");
        Intrinsics.checkNotNullParameter(arrayList3, NotificationCompat.CATEGORY_STATUS);
        this.a = activity;
        this.b = arrayList;
        this.c = arrayList2;
        this.d = arrayList3;
    }

    public final int getCount() {
        return this.b.size();
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater layoutInflater = this.a.getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "context.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.single_item, (ViewGroup) null, true);
        View findViewById = inflate.findViewById(R.id.tv_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        View findViewById2 = inflate.findViewById(R.id.iv_check_mark);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.tv_sub_item);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById3;
        ((TextView) findViewById).setText(this.b.get(i));
        Boolean bool = this.d.get(i);
        Intrinsics.checkNotNullExpressionValue(bool, "status[position]");
        if (bool.booleanValue()) {
            imageView.setImageResource(R.drawable.ic_tick_mark);
        } else {
            imageView.setImageResource(R.drawable.ic_alert);
        }
        textView.setText(this.c.get(i));
        Intrinsics.checkNotNullExpressionValue(inflate, "rowView");
        return inflate;
    }
}
