package androidx.browser.browseractions;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

@Deprecated
class BrowserActionsFallbackMenuAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<BrowserActionItem> mMenuItems;

    BrowserActionsFallbackMenuAdapter(List<BrowserActionItem> menuItems, Context context) {
        this.mMenuItems = menuItems;
        this.mContext = context;
    }

    public int getCount() {
        return this.mMenuItems.size();
    }

    public Object getItem(int position) {
        return this.mMenuItems.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$ViewHolderItem} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            java.util.List<androidx.browser.browseractions.BrowserActionItem> r0 = r6.mMenuItems
            java.lang.Object r0 = r0.get(r7)
            androidx.browser.browseractions.BrowserActionItem r0 = (androidx.browser.browseractions.BrowserActionItem) r0
            r1 = 0
            if (r8 != 0) goto L_0x003c
            android.content.Context r2 = r6.mContext
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)
            int r3 = androidx.browser.R.layout.browser_actions_context_menu_row
            android.view.View r8 = r2.inflate(r3, r1)
            int r2 = androidx.browser.R.id.browser_actions_menu_item_icon
            android.view.View r2 = r8.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            int r3 = androidx.browser.R.id.browser_actions_menu_item_text
            android.view.View r3 = r8.findViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            if (r2 == 0) goto L_0x0034
            if (r3 == 0) goto L_0x0034
            androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$ViewHolderItem r4 = new androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$ViewHolderItem
            r4.<init>(r2, r3)
            r8.setTag(r4)
            goto L_0x0043
        L_0x0034:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r4 = "Browser Actions fallback UI does not contain necessary Views."
            r1.<init>(r4)
            throw r1
        L_0x003c:
            java.lang.Object r2 = r8.getTag()
            r4 = r2
            androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$ViewHolderItem r4 = (androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter.ViewHolderItem) r4
        L_0x0043:
            java.lang.String r2 = r0.getTitle()
            android.widget.TextView r3 = r4.mText
            r3.setText(r2)
            int r3 = r0.getIconId()
            if (r3 == 0) goto L_0x0066
            android.content.Context r3 = r6.mContext
            android.content.res.Resources r3 = r3.getResources()
            int r5 = r0.getIconId()
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r3, r5, r1)
            android.widget.ImageView r3 = r4.mIcon
            r3.setImageDrawable(r1)
            goto L_0x0093
        L_0x0066:
            android.net.Uri r3 = r0.getIconUri()
            if (r3 == 0) goto L_0x0088
            android.content.Context r1 = r6.mContext
            android.content.ContentResolver r1 = r1.getContentResolver()
            android.net.Uri r3 = r0.getIconUri()
            com.google.common.util.concurrent.ListenableFuture r1 = androidx.browser.browseractions.BrowserServiceFileProvider.loadBitmap(r1, r3)
            androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$1 r3 = new androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$1
            r3.<init>(r2, r4, r1)
            androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$2 r5 = new androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter$2
            r5.<init>()
            r1.addListener(r3, r5)
            goto L_0x0093
        L_0x0088:
            android.widget.ImageView r3 = r4.mIcon
            r3.setImageBitmap(r1)
            android.widget.ImageView r1 = r4.mIcon
            r3 = 4
            r1.setVisibility(r3)
        L_0x0093:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.browser.browseractions.BrowserActionsFallbackMenuAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    static class ViewHolderItem {
        final ImageView mIcon;
        final TextView mText;

        ViewHolderItem(ImageView icon, TextView text) {
            this.mIcon = icon;
            this.mText = text;
        }
    }
}
