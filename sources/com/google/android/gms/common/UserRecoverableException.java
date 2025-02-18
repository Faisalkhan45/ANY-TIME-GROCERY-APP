package com.google.android.gms.common;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public class UserRecoverableException extends Exception {
    private final Intent zza;

    public UserRecoverableException(String msg, Intent intent) {
        super(msg);
        this.zza = intent;
    }

    public Intent getIntent() {
        return new Intent(this.zza);
    }
}
