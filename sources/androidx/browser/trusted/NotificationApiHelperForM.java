package androidx.browser.trusted;

import android.app.NotificationManager;
import android.os.Parcelable;

public class NotificationApiHelperForM {
    static Parcelable[] getActiveNotifications(NotificationManager manager) {
        return manager.getActiveNotifications();
    }

    private NotificationApiHelperForM() {
    }
}
