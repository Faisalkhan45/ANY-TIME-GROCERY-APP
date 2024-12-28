package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzadu extends zzady {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzadu() {
        super((zzadx) null);
    }

    /* synthetic */ zzadu(zzadt zzadt) {
        super((zzadx) null);
    }

    private static List zzf(Object obj, long j, int i) {
        List list;
        List list2 = (List) zzafx.zzf(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof zzads) {
                list = new zzadr(i);
            } else if (!(list2 instanceof zzaer) || !(list2 instanceof zzadk)) {
                list = new ArrayList(i);
            } else {
                list = ((zzadk) list2).zzd(i);
            }
            zzafx.zzs(obj, j, list);
            return list;
        } else if (zza.isAssignableFrom(list2.getClass())) {
            ArrayList arrayList = new ArrayList(list2.size() + i);
            arrayList.addAll(list2);
            zzafx.zzs(obj, j, arrayList);
            return arrayList;
        } else if (list2 instanceof zzafs) {
            zzadr zzadr = new zzadr(list2.size() + i);
            zzadr.addAll(zzadr.size(), (zzafs) list2);
            zzafx.zzs(obj, j, zzadr);
            return zzadr;
        } else if (!(list2 instanceof zzaer) || !(list2 instanceof zzadk)) {
            return list2;
        } else {
            zzadk zzadk = (zzadk) list2;
            if (zzadk.zzc()) {
                return list2;
            }
            zzadk zzd = zzadk.zzd(list2.size() + i);
            zzafx.zzs(obj, j, zzd);
            return zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzafx.zzf(obj, j);
        if (list instanceof zzads) {
            obj2 = ((zzads) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzaer) || !(list instanceof zzadk)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzadk zzadk = (zzadk) list;
                if (zzadk.zzc()) {
                    zzadk.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzafx.zzs(obj, j, obj2);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzafx.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzafx.zzs(obj, j, list);
    }
}
