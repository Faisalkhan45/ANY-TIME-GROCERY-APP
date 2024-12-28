package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
abstract class zzh implements Iterator {
    @CheckForNull
    private Object zza;
    private int zzb = 2;

    protected zzh() {
    }

    public final Object next() {
        if (hasNext()) {
            this.zzb = 2;
            Object obj = this.zza;
            this.zza = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public abstract Object zza();

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Object zzb() {
        this.zzb = 3;
        return null;
    }

    public final boolean hasNext() {
        int i = this.zzb;
        if (i != 4) {
            int i2 = i - 1;
            if (i != 0) {
                switch (i2) {
                    case 0:
                        return true;
                    case 2:
                        return false;
                    default:
                        this.zzb = 4;
                        this.zza = zza();
                        if (this.zzb == 3) {
                            return false;
                        }
                        this.zzb = 1;
                        return true;
                }
            } else {
                throw null;
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
