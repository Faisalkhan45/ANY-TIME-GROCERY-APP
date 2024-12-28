package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzafr implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzafs zzb;

    zzafr(zzafs zzafs) {
        this.zzb = zzafs;
        this.zza = zzafs.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
