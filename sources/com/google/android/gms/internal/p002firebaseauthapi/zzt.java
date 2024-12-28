package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.Serializable;
import java.util.regex.Pattern;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzt extends zzq implements Serializable {
    private final Pattern zza;

    zzt(Pattern pattern) {
        if (pattern != null) {
            this.zza = pattern;
            return;
        }
        throw null;
    }

    public final String toString() {
        return this.zza.toString();
    }

    public final zzp zza(CharSequence charSequence) {
        return new zzs(this.zza.matcher(charSequence));
    }
}
