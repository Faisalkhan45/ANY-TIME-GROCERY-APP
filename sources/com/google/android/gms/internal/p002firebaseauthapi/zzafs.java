package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzafs extends AbstractList implements RandomAccess, zzads {
    /* access modifiers changed from: private */
    public final zzads zza;

    public zzafs(zzads zzads) {
        this.zza = zzads;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzadr) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzafr(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzafq(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzads zze() {
        return this;
    }

    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    public final List zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzacc zzacc) {
        throw new UnsupportedOperationException();
    }
}
