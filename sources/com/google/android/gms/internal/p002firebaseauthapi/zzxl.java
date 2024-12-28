package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.reflect.Type;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxl {
    private static final String zza = zzxl.class.getName();

    private zzxl() {
    }

    public static Object zza(String str, Type type) throws zzvg {
        if (type == String.class) {
            try {
                zzzb zzzb = new zzzb();
                zzzb.zzb(str);
                if (zzzb.zzd()) {
                    return zzzb.zzc();
                }
                throw new zzvg("No error message: " + str);
            } catch (Exception e) {
                throw new zzvg("Json conversion failed! ".concat(String.valueOf(e.getMessage())), e);
            }
        } else if (type == Void.class) {
            return null;
        } else {
            try {
                zzxn zzxn = (zzxn) ((Class) type).getConstructor(new Class[0]).newInstance(new Object[0]);
                try {
                    zzxn.zza(str);
                    return zzxn;
                } catch (Exception e2) {
                    throw new zzvg("Json conversion failed! ".concat(String.valueOf(e2.getMessage())), e2);
                }
            } catch (Exception e3) {
                throw new zzvg("Instantiation of JsonResponse failed! ".concat(type.toString()), e3);
            }
        }
    }
}
