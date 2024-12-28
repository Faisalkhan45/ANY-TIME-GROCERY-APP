package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaey  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaey {
    private static final Class zza;
    private static final zzafn zzb = zzab(false);
    private static final zzafn zzc = zzab(true);
    private static final zzafn zzd = new zzafp();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zza = cls;
    }

    public static zzafn zzA() {
        return zzc;
    }

    public static zzafn zzB() {
        return zzd;
    }

    static Object zzC(Object obj, int i, List list, zzadj zzadj, Object obj2, zzafn zzafn) {
        if (zzadj == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzadj.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzD(obj, i, intValue, obj2, zzafn);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzadj.zza()) {
                    obj2 = zzD(obj, i, intValue2, obj2, zzafn);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzD(Object obj, int i, int i2, Object obj2, zzafn zzafn) {
        if (obj2 == null) {
            obj2 = zzafn.zzc(obj);
        }
        zzafn.zzl(obj2, i, (long) i2);
        return obj2;
    }

    static void zzE(zzact zzact, Object obj, Object obj2) {
        zzact.zza(obj2);
        throw null;
    }

    static void zzF(zzafn zzafn, Object obj, Object obj2) {
        zzafn.zzo(obj, zzafn.zze(zzafn.zzd(obj), zzafn.zzd(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzadf.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static void zzI(zzaef zzaef, Object obj, Object obj2, long j) {
        zzafx.zzs(obj, j, zzaef.zzc(zzafx.zzf(obj, j), zzafx.zzf(obj2, j)));
    }

    public static void zzJ(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzc(i, list, z);
        }
    }

    public static void zzK(int i, List list, zzaco zzaco) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zze(i, list);
        }
    }

    public static void zzL(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzg(i, list, z);
        }
    }

    public static void zzM(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzj(i, list, z);
        }
    }

    public static void zzN(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzl(i, list, z);
        }
    }

    public static void zzO(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzn(i, list, z);
        }
    }

    public static void zzP(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzp(i, list, z);
        }
    }

    public static void zzQ(int i, List list, zzaco zzaco, zzaew zzaew) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzaco.zzq(i, list.get(i2), zzaew);
            }
        }
    }

    public static void zzR(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzs(i, list, z);
        }
    }

    public static void zzS(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzu(i, list, z);
        }
    }

    public static void zzT(int i, List list, zzaco zzaco, zzaew zzaew) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzaco.zzv(i, list.get(i2), zzaew);
            }
        }
    }

    public static void zzU(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzx(i, list, z);
        }
    }

    public static void zzV(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzz(i, list, z);
        }
    }

    public static void zzW(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzB(i, list, z);
        }
    }

    public static void zzX(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzD(i, list, z);
        }
    }

    public static void zzY(int i, List list, zzaco zzaco) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzG(i, list);
        }
    }

    public static void zzZ(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzI(i, list, z);
        }
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 1);
    }

    public static void zzaa(int i, List list, zzaco zzaco, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaco.zzK(i, list, z);
        }
    }

    private static zzafn zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzafn) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable th2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = size * zzacn.zzD(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzD += zzacn.zzw((zzacc) list.get(i2));
        }
        return zzD;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzacn.zzD(i));
    }

    static int zze(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(zzadg.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzacn.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzaew zzaew) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzacn.zzx(i, (zzaek) list.get(i3), zzaew);
        }
        return i2;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzacn.zzD(i));
    }

    static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(zzadg.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzacn.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzacn.zzD(i));
    }

    static int zzn(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(zzadz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzacn.zzF(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, Object obj, zzaew zzaew) {
        if (!(obj instanceof zzadq)) {
            return zzacn.zzE(i << 3) + zzacn.zzA((zzaek) obj, zzaew);
        }
        int zzE = zzacn.zzE(i << 3);
        int zza2 = ((zzadq) obj).zza();
        return zzE + zzacn.zzE(zza2) + zza2;
    }

    static int zzp(int i, List list, zzaew zzaew) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = zzacn.zzD(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzadq) {
                i2 = zzacn.zzz((zzadq) obj);
            } else {
                i2 = zzacn.zzA((zzaek) obj, zzaew);
            }
            zzD += i2;
        }
        return zzD;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzacn.zzD(i));
    }

    static int zzr(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            i = 0;
            while (i2 < size) {
                int zze = zzadg.zze(i2);
                i += zzacn.zzE((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzacn.zzE((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzacn.zzD(i));
    }

    static int zzt(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                long zze = zzadz.zze(i2);
                i += zzacn.zzF((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzacn.zzF((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzD = zzacn.zzD(i) * size;
        if (list instanceof zzads) {
            zzads zzads = (zzads) list;
            while (i4 < size) {
                Object zzf = zzads.zzf(i4);
                if (zzf instanceof zzacc) {
                    i3 = zzacn.zzw((zzacc) zzf);
                } else {
                    i3 = zzacn.zzC((String) zzf);
                }
                zzD += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzacc) {
                    i2 = zzacn.zzw((zzacc) obj);
                } else {
                    i2 = zzacn.zzC((String) obj);
                }
                zzD += i2;
                i4++;
            }
        }
        return zzD;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzacn.zzD(i));
    }

    static int zzw(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadg = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzE(zzadg.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzacn.zzE(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzacn.zzD(i));
    }

    static int zzy(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(zzadz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzacn.zzF(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzafn zzz() {
        return zzb;
    }
}
