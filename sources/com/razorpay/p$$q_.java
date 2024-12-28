package com.razorpay;

import android.app.Activity;

/* compiled from: MagicData */
final class p$$q_ {
    public static String b = "magic_version";
    Activity a;
    /* access modifiers changed from: private */
    public String c;

    p$$q_(Activity activity) {
        this.a = activity;
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        if (this.c == null) {
            if (BaseUtils.getLocalVersion(this.a, b).equals(BaseUtils.getVersionFromJsonString(g$_H$.getVersionJSON(), b))) {
                this.c = g$_H$.getMagicJs();
            } else {
                try {
                    this.c = BaseUtils.getFileFromInternal(this.a, g$_H$.a().getMagicJsFileName(), b);
                } catch (Exception e) {
                    this.c = g$_H$.getMagicJs();
                }
            }
        }
        return this.c;
    }
}
