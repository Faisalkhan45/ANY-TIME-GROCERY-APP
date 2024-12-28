package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzacx {
    private static final zzacx zzb = new zzacx(true);
    final zzafj zza = new zzaez(16);
    private boolean zzc;
    private boolean zzd;

    private zzacx() {
    }

    public static zzacx zza() {
        throw null;
    }

    private static final void zzd(zzacw zzacw, Object obj) {
        boolean z;
        zzagd zzb2 = zzacw.zzb();
        zzadl.zze(obj);
        zzagd zzagd = zzagd.DOUBLE;
        zzage zzage = zzage.INT;
        switch (zzb2.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzacc) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzadh)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzaek) || (obj instanceof zzadp)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(zzacw.zza()), zzacw.zzb().zza(), obj.getClass().getName()}));
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzacx zzacx = new zzacx();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzacx.zzc((zzacw) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzacx.zzc((zzacw) entry.getKey(), entry.getValue());
        }
        zzacx.zzd = this.zzd;
        return zzacx;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzacx)) {
            return false;
        }
        return this.zza.equals(((zzacx) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzadf) {
                    ((zzadf) zzg.getValue()).zzE();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzc(zzacw zzacw, Object obj) {
        if (!zzacw.zzc()) {
            zzd(zzacw, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzacw, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzadp) {
            this.zzd = true;
        }
        this.zza.put(zzacw, obj);
    }

    private zzacx(boolean z) {
        zzb();
        zzb();
    }
}
