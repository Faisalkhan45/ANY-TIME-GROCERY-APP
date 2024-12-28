package com.razorpay;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

/* compiled from: StorageBridge */
final class n {
    private SharedPreferences a;
    private SharedPreferences.Editor b;

    n(Context context) {
        this.a = context.getSharedPreferences("rzp_preferences_storage_bridge", 0);
        this.b = context.getSharedPreferences("rzp_preferences_storage_bridge", 0).edit();
    }

    @JavascriptInterface
    public final void setString(String str, String str2) {
        try {
            this.b.putString(str, str2);
            this.b.commit();
        } catch (Exception e) {
            d__1_.a("Error saving string", e);
        }
    }

    @JavascriptInterface
    public final void setBoolean(String str, boolean z) {
        try {
            this.b.putBoolean(str, z);
            this.b.commit();
        } catch (Exception e) {
            d__1_.a("Error saving boolean", e);
        }
    }

    @JavascriptInterface
    public final void setInt(String str, int i) {
        try {
            this.b.putInt(str, i);
            this.b.commit();
        } catch (Exception e) {
            d__1_.a("Error saving integer", e);
        }
    }

    @JavascriptInterface
    public final void setFloat(String str, float f) {
        try {
            this.b.putFloat(str, f);
            this.b.commit();
        } catch (Exception e) {
            d__1_.a("Error saving float", e);
        }
    }

    @JavascriptInterface
    public final String getString(String str) {
        try {
            return this.a.getString(str, (String) null);
        } catch (Exception e) {
            return null;
        }
    }

    @JavascriptInterface
    public final boolean getBoolean(String str) {
        try {
            return this.a.getBoolean(str, false);
        } catch (Exception e) {
            return false;
        }
    }

    @JavascriptInterface
    public final float getFloat(String str) {
        try {
            return this.a.getFloat(str, 0.0f);
        } catch (Exception e) {
            return 0.0f;
        }
    }

    @JavascriptInterface
    public final int getInt(String str) {
        try {
            return this.a.getInt(str, 0);
        } catch (Exception e) {
            return 0;
        }
    }
}
