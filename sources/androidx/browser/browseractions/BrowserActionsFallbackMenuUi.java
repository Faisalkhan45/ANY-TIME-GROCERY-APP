package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.browser.R;
import androidx.core.widget.TextViewCompat;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;

@Deprecated
class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    private static final String TAG = "BrowserActionskMenuUi";
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    final Context mContext;
    private final List<BrowserActionItem> mMenuItems;
    BrowserActionsFallMenuUiListener mMenuUiListener;
    final Uri mUri;

    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View view);
    }

    BrowserActionsFallbackMenuUi(Context context, Uri uri, List<BrowserActionItem> customItems) {
        this.mContext = context;
        this.mUri = uri;
        this.mMenuItems = buildFallbackMenuItemList(customItems);
    }

    /* access modifiers changed from: package-private */
    public void setMenuUiListener(BrowserActionsFallMenuUiListener menuUiListener) {
        this.mMenuUiListener = menuUiListener;
    }

    private List<BrowserActionItem> buildFallbackMenuItemList(List<BrowserActionItem> customItems) {
        List<BrowserActionItem> fallbackMenuItems = new ArrayList<>();
        fallbackMenuItems.add(new BrowserActionItem(this.mContext.getString(R.string.fallback_menu_item_open_in_browser), buildOpenInBrowserAction()));
        fallbackMenuItems.add(new BrowserActionItem(this.mContext.getString(R.string.fallback_menu_item_copy_link), buildCopyAction()));
        fallbackMenuItems.add(new BrowserActionItem(this.mContext.getString(R.string.fallback_menu_item_share_link), buildShareAction()));
        fallbackMenuItems.addAll(customItems);
        return fallbackMenuItems;
    }

    private PendingIntent buildOpenInBrowserAction() {
        return PendingIntent.getActivity(this.mContext, 0, new Intent("android.intent.action.VIEW", this.mUri), 67108864);
    }

    private PendingIntent buildShareAction() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", this.mUri.toString());
        intent.setType("text/plain");
        return PendingIntent.getActivity(this.mContext, 0, intent, 67108864);
    }

    private Runnable buildCopyAction() {
        return new Runnable() {
            public void run() {
                ((ClipboardManager) BrowserActionsFallbackMenuUi.this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(ImagesContract.URL, BrowserActionsFallbackMenuUi.this.mUri.toString()));
                Toast.makeText(BrowserActionsFallbackMenuUi.this.mContext, BrowserActionsFallbackMenuUi.this.mContext.getString(R.string.copy_toast_msg), 0).show();
            }
        };
    }

    public void displayMenu() {
        final View view = LayoutInflater.from(this.mContext).inflate(R.layout.browser_actions_context_menu_page, (ViewGroup) null);
        BrowserActionsFallbackMenuDialog browserActionsFallbackMenuDialog = new BrowserActionsFallbackMenuDialog(this.mContext, initMenuView(view));
        this.mBrowserActionsDialog = browserActionsFallbackMenuDialog;
        browserActionsFallbackMenuDialog.setContentView(view);
        if (this.mMenuUiListener != null) {
            this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    if (BrowserActionsFallbackMenuUi.this.mMenuUiListener == null) {
                        Log.e(BrowserActionsFallbackMenuUi.TAG, "Cannot trigger menu item listener, it is null");
                    } else {
                        BrowserActionsFallbackMenuUi.this.mMenuUiListener.onMenuShown(view);
                    }
                }
            });
        }
        this.mBrowserActionsDialog.show();
    }

    private BrowserActionsFallbackMenuView initMenuView(View view) {
        BrowserActionsFallbackMenuView menuView = (BrowserActionsFallbackMenuView) view.findViewById(R.id.browser_actions_menu_view);
        final TextView urlTextView = (TextView) view.findViewById(R.id.browser_actions_header_text);
        urlTextView.setText(this.mUri.toString());
        urlTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextViewCompat.getMaxLines(urlTextView) == Integer.MAX_VALUE) {
                    urlTextView.setMaxLines(1);
                    urlTextView.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                urlTextView.setMaxLines(Integer.MAX_VALUE);
                urlTextView.setEllipsize((TextUtils.TruncateAt) null);
            }
        });
        ListView menuListView = (ListView) view.findViewById(R.id.browser_actions_menu_items);
        menuListView.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
        menuListView.setOnItemClickListener(this);
        return menuView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BrowserActionItem menuItem = this.mMenuItems.get(position);
        if (menuItem.getAction() != null) {
            try {
                menuItem.getAction().send();
            } catch (PendingIntent.CanceledException e) {
                Log.e(TAG, "Failed to send custom item action", e);
            }
        } else if (menuItem.getRunnableAction() != null) {
            menuItem.getRunnableAction().run();
        }
        BrowserActionsFallbackMenuDialog browserActionsFallbackMenuDialog = this.mBrowserActionsDialog;
        if (browserActionsFallbackMenuDialog == null) {
            Log.e(TAG, "Cannot dismiss dialog, it has already been dismissed.");
        } else {
            browserActionsFallbackMenuDialog.dismiss();
        }
    }
}
