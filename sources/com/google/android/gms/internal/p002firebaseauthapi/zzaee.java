package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaee  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaee extends LinkedHashMap {
    private static final zzaee zza;
    private boolean zzb = true;

    static {
        zzaee zzaee = new zzaee();
        zza = zzaee;
        zzaee.zzb = false;
    }

    private zzaee() {
    }

    public static zzaee zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzadl.zzb((byte[]) obj);
        }
        if (!(obj instanceof zzadh)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzg();
        super.clear();
    }

    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i;
    }

    public final Object put(Object obj, Object obj2) {
        zzg();
        zzadl.zze(obj);
        zzadl.zze(obj2);
        return super.put(obj, obj2);
    }

    public final void putAll(Map map) {
        zzg();
        for (Object next : map.keySet()) {
            zzadl.zze(next);
            zzadl.zze(map.get(next));
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzaee zzb() {
        return isEmpty() ? new zzaee() : new zzaee(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzaee zzaee) {
        zzg();
        if (!zzaee.isEmpty()) {
            putAll(zzaee);
        }
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzaee(Map map) {
        super(map);
    }
}
