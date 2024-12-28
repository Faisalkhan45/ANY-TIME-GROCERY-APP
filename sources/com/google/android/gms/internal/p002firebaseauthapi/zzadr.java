package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzadr extends zzabn implements RandomAccess, zzads {
    public static final zzads zza;
    private static final zzadr zzb;
    private final List zzc;

    static {
        zzadr zzadr = new zzadr(10);
        zzb = zzadr;
        zzadr.zzb();
        zza = zzadr;
    }

    public zzadr() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzacc) {
            return ((zzacc) obj).zzr(zzadl.zzb);
        }
        return zzadl.zzg((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzads) {
            collection = ((zzads) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ zzadk zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzadr(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final zzads zze() {
        return zzc() ? new zzafs(this) : this;
    }

    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzacc) {
            zzacc zzacc = (zzacc) obj;
            String zzr = zzacc.zzr(zzadl.zzb);
            if (zzacc.zzk()) {
                this.zzc.set(i, zzr);
            }
            return zzr;
        }
        byte[] bArr = (byte[]) obj;
        String zzg = zzadl.zzg(bArr);
        if (zzadl.zzh(bArr)) {
            this.zzc.set(i, zzg);
        }
        return zzg;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final void zzi(zzacc zzacc) {
        zza();
        this.zzc.add(zzacc);
        this.modCount++;
    }

    public zzadr(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzadr(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
