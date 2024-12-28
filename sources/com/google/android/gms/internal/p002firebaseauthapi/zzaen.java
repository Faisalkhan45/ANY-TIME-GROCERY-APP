package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaen  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaen<T> implements zzaew<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzafx.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaek zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzady zzn;
    private final zzafn zzo;
    private final zzact zzp;
    private final zzaep zzq;
    private final zzaef zzr;

    private zzaen(int[] iArr, Object[] objArr, int i, int i2, zzaek zzaek, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzaep zzaep, zzady zzady, zzafn zzafn, zzact zzact, zzaef zzaef, byte[] bArr) {
        zzaek zzaek2 = zzaek;
        zzact zzact2 = zzact;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzaek2 instanceof zzadf;
        this.zzj = z;
        boolean z3 = false;
        if (zzact2 != null && zzact2.zzh(zzaek)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzaep;
        this.zzn = zzady;
        this.zzo = zzafn;
        this.zzp = zzact2;
        this.zzg = zzaek2;
        this.zzr = zzaef;
    }

    private final int zzA(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzB(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzC(int i) {
        return this.zzc[i + 1];
    }

    private static long zzD(Object obj, long j) {
        return ((Long) zzafx.zzf(obj, j)).longValue();
    }

    private final zzadj zzE(int i) {
        int i2 = i / 3;
        return (zzadj) this.zzd[i2 + i2 + 1];
    }

    private final zzaew zzF(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzaew zzaew = (zzaew) this.zzd[i3];
        if (zzaew != null) {
            return zzaew;
        }
        zzaew zzb2 = zzaes.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzG(Object obj, int i, Object obj2, zzafn zzafn, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf2 = zzafx.zzf(obj, (long) (zzC(i) & 1048575));
        if (zzf2 == null || zzE(i) == null) {
            return obj2;
        }
        zzaee zzaee = (zzaee) zzf2;
        zzaed zzaed = (zzaed) zzH(i);
        throw null;
    }

    private final Object zzH(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzI(Object obj, int i) {
        zzaew zzF = zzF(i);
        long zzC = (long) (zzC(i) & 1048575);
        if (!zzV(obj, i)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, zzC);
        if (zzY(object)) {
            return object;
        }
        Object zze2 = zzF.zze();
        if (object != null) {
            zzF.zzg(zze2, object);
        }
        return zze2;
    }

    private final Object zzJ(Object obj, int i, int i2) {
        zzaew zzF = zzF(i2);
        if (!zzZ(obj, i, i2)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, (long) (zzC(i2) & 1048575));
        if (zzY(object)) {
            return object;
        }
        Object zze2 = zzF.zze();
        if (object != null) {
            zzF.zzg(zze2, object);
        }
        return zze2;
    }

    private static Field zzK(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzL(Object obj) {
        if (!zzY(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzM(Object obj, Object obj2, int i) {
        if (zzV(obj2, i)) {
            long zzC = (long) (zzC(i) & 1048575);
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object != null) {
                zzaew zzF = zzF(i);
                if (!zzV(obj, i)) {
                    if (!zzY(object)) {
                        unsafe.putObject(obj, zzC, object);
                    } else {
                        Object zze2 = zzF.zze();
                        zzF.zzg(zze2, object);
                        unsafe.putObject(obj, zzC, zze2);
                    }
                    zzP(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzC);
                if (!zzY(object2)) {
                    Object zze3 = zzF.zze();
                    zzF.zzg(zze3, object2);
                    unsafe.putObject(obj, zzC, zze3);
                    object2 = zze3;
                }
                zzF.zzg(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzN(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzZ(obj2, i2, i)) {
            long zzC = (long) (zzC(i) & 1048575);
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object != null) {
                zzaew zzF = zzF(i);
                if (!zzZ(obj, i2, i)) {
                    if (!zzY(object)) {
                        unsafe.putObject(obj, zzC, object);
                    } else {
                        Object zze2 = zzF.zze();
                        zzF.zzg(zze2, object);
                        unsafe.putObject(obj, zzC, zze2);
                    }
                    zzQ(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzC);
                if (!zzY(object2)) {
                    Object zze3 = zzF.zze();
                    zzF.zzg(zze3, object2);
                    unsafe.putObject(obj, zzC, zze3);
                    object2 = zze3;
                }
                zzF.zzg(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzO(Object obj, int i, zzaev zzaev) throws IOException {
        if (zzU(i)) {
            zzafx.zzs(obj, (long) (i & 1048575), zzaev.zzs());
        } else if (this.zzi) {
            zzafx.zzs(obj, (long) (i & 1048575), zzaev.zzr());
        } else {
            zzafx.zzs(obj, (long) (i & 1048575), zzaev.zzp());
        }
    }

    private final void zzP(Object obj, int i) {
        int zzz = zzz(i);
        long j = (long) (1048575 & zzz);
        if (j != 1048575) {
            zzafx.zzq(obj, j, (1 << (zzz >>> 20)) | zzafx.zzc(obj, j));
        }
    }

    private final void zzQ(Object obj, int i, int i2) {
        zzafx.zzq(obj, (long) (zzz(i2) & 1048575), i);
    }

    private final void zzR(Object obj, int i, Object obj2) {
        zzb.putObject(obj, (long) (zzC(i) & 1048575), obj2);
        zzP(obj, i);
    }

    private final void zzS(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, (long) (zzC(i2) & 1048575), obj2);
        zzQ(obj, i, i2);
    }

    private final boolean zzT(Object obj, Object obj2, int i) {
        return zzV(obj, i) == zzV(obj2, i);
    }

    private static boolean zzU(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzV(Object obj, int i) {
        int zzz = zzz(i);
        long j = (long) (zzz & 1048575);
        if (j != 1048575) {
            return (zzafx.zzc(obj, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(i);
        long j2 = (long) (zzC & 1048575);
        switch (zzB(zzC)) {
            case 0:
                return Double.doubleToRawLongBits(zzafx.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzafx.zzb(obj, j2)) != 0;
            case 2:
                return zzafx.zzd(obj, j2) != 0;
            case 3:
                return zzafx.zzd(obj, j2) != 0;
            case 4:
                return zzafx.zzc(obj, j2) != 0;
            case 5:
                return zzafx.zzd(obj, j2) != 0;
            case 6:
                return zzafx.zzc(obj, j2) != 0;
            case 7:
                return zzafx.zzw(obj, j2);
            case 8:
                Object zzf2 = zzafx.zzf(obj, j2);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zzacc) {
                    return !zzacc.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzafx.zzf(obj, j2) != null;
            case 10:
                return !zzacc.zzb.equals(zzafx.zzf(obj, j2));
            case 11:
                return zzafx.zzc(obj, j2) != 0;
            case 12:
                return zzafx.zzc(obj, j2) != 0;
            case 13:
                return zzafx.zzc(obj, j2) != 0;
            case 14:
                return zzafx.zzd(obj, j2) != 0;
            case 15:
                return zzafx.zzc(obj, j2) != 0;
            case 16:
                return zzafx.zzd(obj, j2) != 0;
            case 17:
                return zzafx.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzW(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzV(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzX(Object obj, int i, zzaew zzaew) {
        return zzaew.zzk(zzafx.zzf(obj, (long) (i & 1048575)));
    }

    private static boolean zzY(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzadf) {
            return ((zzadf) obj).zzK();
        }
        return true;
    }

    private final boolean zzZ(Object obj, int i, int i2) {
        return zzafx.zzc(obj, (long) (zzz(i2) & 1048575)) == i;
    }

    private static boolean zzaa(Object obj, long j) {
        return ((Boolean) zzafx.zzf(obj, j)).booleanValue();
    }

    private final void zzab(Object obj, zzaco zzaco) throws IOException {
        int i;
        Object obj2 = obj;
        zzaco zzaco2 = zzaco;
        if (!this.zzh) {
            int length = this.zzc.length;
            Unsafe unsafe = zzb;
            int i2 = 1048575;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int zzC = zzC(i4);
                int[] iArr = this.zzc;
                int i6 = iArr[i4];
                int zzB = zzB(zzC);
                if (zzB <= 17) {
                    int i7 = iArr[i4 + 2];
                    int i8 = i7 & i2;
                    if (i8 != i3) {
                        i5 = unsafe.getInt(obj2, (long) i8);
                        i3 = i8;
                    }
                    i = 1 << (i7 >>> 20);
                } else {
                    i = 0;
                }
                long j = (long) (zzC & i2);
                switch (zzB) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzf(i6, zzafx.zza(obj2, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzo(i6, zzafx.zzb(obj2, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzt(i6, unsafe.getLong(obj2, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzJ(i6, unsafe.getLong(obj2, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzr(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzm(i6, unsafe.getLong(obj2, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzk(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzb(i6, zzafx.zzw(obj2, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzad(i6, unsafe.getObject(obj2, j), zzaco2);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzv(i6, unsafe.getObject(obj2, j), zzF(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzd(i6, (zzacc) unsafe.getObject(obj2, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzH(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzi(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzw(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzy(i6, unsafe.getLong(obj2, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzA(i6, unsafe.getInt(obj2, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzC(i6, unsafe.getLong(obj2, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            zzaco2.zzq(i6, unsafe.getObject(obj2, j), zzF(i4));
                            break;
                        }
                    case 18:
                        zzaey.zzL(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 19:
                        zzaey.zzP(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 20:
                        zzaey.zzS(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 21:
                        zzaey.zzaa(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 22:
                        zzaey.zzR(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 23:
                        zzaey.zzO(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 24:
                        zzaey.zzN(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 25:
                        zzaey.zzJ(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 26:
                        zzaey.zzY(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2);
                        break;
                    case 27:
                        zzaey.zzT(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, zzF(i4));
                        break;
                    case 28:
                        zzaey.zzK(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2);
                        break;
                    case 29:
                        zzaey.zzZ(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 30:
                        zzaey.zzM(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 31:
                        zzaey.zzU(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 32:
                        zzaey.zzV(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 33:
                        zzaey.zzW(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 34:
                        zzaey.zzX(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, false);
                        break;
                    case 35:
                        zzaey.zzL(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 36:
                        zzaey.zzP(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 37:
                        zzaey.zzS(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 38:
                        zzaey.zzaa(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 39:
                        zzaey.zzR(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 40:
                        zzaey.zzO(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 41:
                        zzaey.zzN(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 42:
                        zzaey.zzJ(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 43:
                        zzaey.zzZ(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 44:
                        zzaey.zzM(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 45:
                        zzaey.zzU(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 46:
                        zzaey.zzV(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 47:
                        zzaey.zzW(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 48:
                        zzaey.zzX(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, true);
                        break;
                    case 49:
                        zzaey.zzQ(this.zzc[i4], (List) unsafe.getObject(obj2, j), zzaco2, zzF(i4));
                        break;
                    case 50:
                        zzac(zzaco2, i6, unsafe.getObject(obj2, j), i4);
                        break;
                    case 51:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzf(i6, zzo(obj2, j));
                            break;
                        }
                    case 52:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzo(i6, zzp(obj2, j));
                            break;
                        }
                    case 53:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzt(i6, zzD(obj2, j));
                            break;
                        }
                    case 54:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzJ(i6, zzD(obj2, j));
                            break;
                        }
                    case 55:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzr(i6, zzs(obj2, j));
                            break;
                        }
                    case 56:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzm(i6, zzD(obj2, j));
                            break;
                        }
                    case 57:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzk(i6, zzs(obj2, j));
                            break;
                        }
                    case 58:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzb(i6, zzaa(obj2, j));
                            break;
                        }
                    case 59:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzad(i6, unsafe.getObject(obj2, j), zzaco2);
                            break;
                        }
                    case 60:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzv(i6, unsafe.getObject(obj2, j), zzF(i4));
                            break;
                        }
                    case 61:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzd(i6, (zzacc) unsafe.getObject(obj2, j));
                            break;
                        }
                    case 62:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzH(i6, zzs(obj2, j));
                            break;
                        }
                    case 63:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzi(i6, zzs(obj2, j));
                            break;
                        }
                    case 64:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzw(i6, zzs(obj2, j));
                            break;
                        }
                    case 65:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzy(i6, zzD(obj2, j));
                            break;
                        }
                    case 66:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzA(i6, zzs(obj2, j));
                            break;
                        }
                    case 67:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzC(i6, zzD(obj2, j));
                            break;
                        }
                    case 68:
                        if (!zzZ(obj2, i6, i4)) {
                            break;
                        } else {
                            zzaco2.zzq(i6, unsafe.getObject(obj2, j), zzF(i4));
                            break;
                        }
                }
                i4 += 3;
                i2 = 1048575;
            }
            zzafn zzafn = this.zzo;
            zzafn.zzr(zzafn.zzd(obj2), zzaco2);
            return;
        }
        this.zzp.zza(obj2);
        throw null;
    }

    private final void zzac(zzaco zzaco, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzaed zzaed = (zzaed) zzH(i2);
            throw null;
        }
    }

    private static final void zzad(int i, Object obj, zzaco zzaco) throws IOException {
        if (obj instanceof String) {
            zzaco.zzF(i, (String) obj);
        } else {
            zzaco.zzd(i, (zzacc) obj);
        }
    }

    static zzafo zzd(Object obj) {
        zzadf zzadf = (zzadf) obj;
        zzafo zzafo = zzadf.zzc;
        if (zzafo != zzafo.zzc()) {
            return zzafo;
        }
        zzafo zzf2 = zzafo.zzf();
        zzadf.zzc = zzf2;
        return zzf2;
    }

    static zzaen zzl(Class cls, zzaeh zzaeh, zzaep zzaep, zzady zzady, zzafn zzafn, zzact zzact, zzaef zzaef) {
        if (zzaeh instanceof zzaeu) {
            return zzm((zzaeu) zzaeh, zzaep, zzady, zzafn, zzact, zzaef);
        }
        zzafk zzafk = (zzafk) zzaeh;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:158:0x034b  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x03ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.p002firebaseauthapi.zzaen zzm(com.google.android.gms.internal.p002firebaseauthapi.zzaeu r34, com.google.android.gms.internal.p002firebaseauthapi.zzaep r35, com.google.android.gms.internal.p002firebaseauthapi.zzady r36, com.google.android.gms.internal.p002firebaseauthapi.zzafn r37, com.google.android.gms.internal.p002firebaseauthapi.zzact r38, com.google.android.gms.internal.p002firebaseauthapi.zzaef r39) {
        /*
            int r0 = r34.zzc()
            r1 = 0
            r3 = 2
            if (r0 != r3) goto L_0x000a
            r10 = 1
            goto L_0x000b
        L_0x000a:
            r10 = r1
        L_0x000b:
            java.lang.String r0 = r34.zzd()
            int r3 = r0.length()
            char r4 = r0.charAt(r1)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0027
            r4 = 1
        L_0x001d:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0028
            r4 = r6
            goto L_0x001d
        L_0x0027:
            r6 = 1
        L_0x0028:
            int r4 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0048
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0034:
            int r9 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0044
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r8
            r6 = r6 | r4
            int r8 = r8 + 13
            r4 = r9
            goto L_0x0034
        L_0x0044:
            int r4 = r4 << r8
            r6 = r6 | r4
            r4 = r9
            goto L_0x0049
        L_0x0048:
        L_0x0049:
            if (r6 != 0) goto L_0x0059
            int[] r6 = zza
            r8 = r1
            r9 = r8
            r11 = r9
            r12 = r11
            r14 = r12
            r16 = r14
            r13 = r6
            r6 = r16
            goto L_0x0178
        L_0x0059:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0079
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0065:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0075
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r4 = r4 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x0065
        L_0x0075:
            int r6 = r6 << r8
            r4 = r4 | r6
            r6 = r9
            goto L_0x007a
        L_0x0079:
        L_0x007a:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x009a
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0086:
            int r11 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0096
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r11
            goto L_0x0086
        L_0x0096:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r11
            goto L_0x009b
        L_0x009a:
        L_0x009b:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00bb
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a7:
            int r12 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00b7
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r11
            r8 = r8 | r9
            int r11 = r11 + 13
            r9 = r12
            goto L_0x00a7
        L_0x00b7:
            int r9 = r9 << r11
            r8 = r8 | r9
            r9 = r12
            goto L_0x00bc
        L_0x00bb:
        L_0x00bc:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00dc
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c8:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00d8
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r9 = r9 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c8
        L_0x00d8:
            int r11 = r11 << r12
            r9 = r9 | r11
            r11 = r13
            goto L_0x00dd
        L_0x00dc:
        L_0x00dd:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00fd
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00e9:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x00f9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00e9
        L_0x00f9:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
            goto L_0x00fe
        L_0x00fd:
        L_0x00fe:
            int r13 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x011e
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x010a:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x011a
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x010a
        L_0x011a:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
            goto L_0x011f
        L_0x011e:
        L_0x011f:
            int r14 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0141
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x012b:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x013c
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x012b
        L_0x013c:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
            goto L_0x0142
        L_0x0141:
        L_0x0142:
            int r15 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0166
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x014e:
            int r17 = r15 + 1
            char r15 = r0.charAt(r15)
            if (r15 < r5) goto L_0x0160
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x014e
        L_0x0160:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
            goto L_0x0167
        L_0x0166:
        L_0x0167:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int[] r13 = new int[r13]
            int r16 = r4 + r4
            int r16 = r16 + r6
            r6 = r4
            r4 = r15
            r33 = r12
            r12 = r9
            r9 = r33
        L_0x0178:
            sun.misc.Unsafe r15 = zzb
            java.lang.Object[] r17 = r34.zze()
            com.google.android.gms.internal.firebase-auth-api.zzaek r18 = r34.zza()
            java.lang.Class r1 = r18.getClass()
            int r7 = r11 * 3
            int[] r7 = new int[r7]
            int r11 = r11 + r11
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r21 = r14 + r9
            r22 = r14
            r23 = r21
            r9 = 0
            r20 = 0
        L_0x0196:
            if (r4 >= r3) goto L_0x03e9
            int r24 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01be
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r2 = r24
            r24 = 13
        L_0x01a6:
            int r26 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01b8
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r24
            r4 = r4 | r2
            int r24 = r24 + 13
            r2 = r26
            goto L_0x01a6
        L_0x01b8:
            int r2 = r2 << r24
            r4 = r4 | r2
            r2 = r26
            goto L_0x01c0
        L_0x01be:
            r2 = r24
        L_0x01c0:
            int r24 = r2 + 1
            char r2 = r0.charAt(r2)
            if (r2 < r5) goto L_0x01ed
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r5 = r24
            r24 = 13
        L_0x01ce:
            int r27 = r5 + 1
            char r5 = r0.charAt(r5)
            r28 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r3) goto L_0x01e7
            r3 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r2 = r2 | r3
            int r24 = r24 + 13
            r5 = r27
            r3 = r28
            goto L_0x01ce
        L_0x01e7:
            int r3 = r5 << r24
            r2 = r2 | r3
            r3 = r27
            goto L_0x01f1
        L_0x01ed:
            r28 = r3
            r3 = r24
        L_0x01f1:
            r5 = r2 & 255(0xff, float:3.57E-43)
            r24 = r14
            r14 = r2 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L_0x01ff
            int r14 = r20 + 1
            r13[r20] = r9
            r20 = r14
        L_0x01ff:
            r14 = 51
            if (r5 < r14) goto L_0x02aa
            int r14 = r3 + 1
            char r3 = r0.charAt(r3)
            r27 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r14) goto L_0x0235
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r14 = r27
            r27 = 13
        L_0x0216:
            int r31 = r14 + 1
            char r14 = r0.charAt(r14)
            r32 = r12
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r12) goto L_0x022f
            r12 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r27
            r3 = r3 | r12
            int r27 = r27 + 13
            r14 = r31
            r12 = r32
            goto L_0x0216
        L_0x022f:
            int r12 = r14 << r27
            r3 = r3 | r12
            r14 = r31
            goto L_0x0239
        L_0x0235:
            r32 = r12
            r14 = r27
        L_0x0239:
            int r12 = r5 + -51
            r27 = r14
            r14 = 9
            if (r12 == r14) goto L_0x025c
            r14 = 17
            if (r12 != r14) goto L_0x0246
            goto L_0x025c
        L_0x0246:
            r14 = 12
            if (r12 != r14) goto L_0x026b
            if (r10 != 0) goto L_0x026b
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            r16 = r14
            goto L_0x026b
        L_0x025c:
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            r16 = r14
        L_0x026b:
            int r3 = r3 + r3
            r12 = r17[r3]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0275
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x027d
        L_0x0275:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zzK(r1, r12)
            r17[r3] = r12
        L_0x027d:
            r31 = r7
            r14 = r8
            long r7 = r15.objectFieldOffset(r12)
            int r7 = (int) r7
            int r3 = r3 + 1
            r8 = r17[r3]
            boolean r12 = r8 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x0290
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x0298
        L_0x0290:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzK(r1, r8)
            r17[r3] = r8
        L_0x0298:
            r3 = r7
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r30 = r0
            r8 = r1
            r0 = r7
            r29 = r11
            r25 = 1
            r7 = r3
            r3 = 0
            goto L_0x03b0
        L_0x02aa:
            r31 = r7
            r14 = r8
            r32 = r12
            int r7 = r16 + 1
            r8 = r17[r16]
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzK(r1, r8)
            r12 = 9
            if (r5 == r12) goto L_0x0329
            r12 = 17
            if (r5 != r12) goto L_0x02c5
            r25 = 1
            goto L_0x032b
        L_0x02c5:
            r12 = 27
            if (r5 == r12) goto L_0x0319
            r12 = 49
            if (r5 != r12) goto L_0x02ce
            goto L_0x0319
        L_0x02ce:
            r12 = 12
            if (r5 == r12) goto L_0x0304
            r12 = 30
            if (r5 == r12) goto L_0x0304
            r12 = 44
            if (r5 != r12) goto L_0x02db
            goto L_0x0304
        L_0x02db:
            r12 = 50
            if (r5 != r12) goto L_0x0303
            int r12 = r22 + 1
            r13[r22] = r9
            int r22 = r9 / 3
            int r22 = r22 + r22
            int r27 = r7 + 1
            r7 = r17[r7]
            r11[r22] = r7
            r7 = r2 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x02fc
            int r7 = r27 + 1
            int r22 = r22 + 1
            r27 = r17[r27]
            r11[r22] = r27
            r22 = r12
            goto L_0x0300
        L_0x02fc:
            r22 = r12
            r7 = r27
        L_0x0300:
            r25 = 1
            goto L_0x0336
        L_0x0303:
            goto L_0x0300
        L_0x0304:
            if (r10 != 0) goto L_0x0318
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            r7 = r27
        L_0x0315:
            r25 = 1
            goto L_0x0336
        L_0x0318:
            goto L_0x0315
        L_0x0319:
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            r7 = r27
            goto L_0x0336
        L_0x0329:
            r25 = 1
        L_0x032b:
            int r12 = r9 / 3
            int r12 = r12 + r12
            int r12 = r12 + 1
            java.lang.Class r27 = r8.getType()
            r11[r12] = r27
        L_0x0336:
            r12 = r7
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r8 = r2 & 4096(0x1000, float:5.74E-42)
            r27 = 1048575(0xfffff, float:1.469367E-39)
            r29 = r11
            r11 = 4096(0x1000, float:5.74E-42)
            if (r8 != r11) goto L_0x0394
            r8 = 17
            if (r5 > r8) goto L_0x0394
            int r8 = r3 + 1
            char r3 = r0.charAt(r3)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r11) goto L_0x0370
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x035a:
            int r27 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r11) goto L_0x036c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r26
            r3 = r3 | r8
            int r26 = r26 + 13
            r8 = r27
            goto L_0x035a
        L_0x036c:
            int r8 = r8 << r26
            r3 = r3 | r8
            goto L_0x0372
        L_0x0370:
            r27 = r8
        L_0x0372:
            int r8 = r6 + r6
            int r26 = r3 / 32
            int r8 = r8 + r26
            r11 = r17[r8]
            r30 = r0
            boolean r0 = r11 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x0383
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x038b
        L_0x0383:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = zzK(r1, r11)
            r17[r8] = r11
        L_0x038b:
            r8 = r1
            long r0 = r15.objectFieldOffset(r11)
            int r0 = (int) r0
            int r3 = r3 % 32
            goto L_0x039c
        L_0x0394:
            r30 = r0
            r8 = r1
            r0 = r27
            r27 = r3
            r3 = 0
        L_0x039c:
            r1 = 18
            if (r5 < r1) goto L_0x03ad
            r1 = 49
            if (r5 > r1) goto L_0x03ad
            int r1 = r23 + 1
            r13[r23] = r7
            r23 = r1
            r16 = r12
            goto L_0x03b0
        L_0x03ad:
            r16 = r12
        L_0x03b0:
            int r1 = r9 + 1
            r31[r9] = r4
            int r4 = r1 + 1
            r9 = r2 & 512(0x200, float:7.175E-43)
            if (r9 == 0) goto L_0x03bd
            r9 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03be
        L_0x03bd:
            r9 = 0
        L_0x03be:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x03c5
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03c6
        L_0x03c5:
            r2 = 0
        L_0x03c6:
            r2 = r2 | r9
            int r5 = r5 << 20
            r2 = r2 | r5
            r2 = r2 | r7
            r31[r1] = r2
            int r9 = r4 + 1
            int r1 = r3 << 20
            r0 = r0 | r1
            r31[r4] = r0
            r1 = r8
            r8 = r14
            r14 = r24
            r4 = r27
            r3 = r28
            r11 = r29
            r0 = r30
            r7 = r31
            r12 = r32
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0196
        L_0x03e9:
            r31 = r7
            r29 = r11
            r32 = r12
            r24 = r14
            r14 = r8
            com.google.android.gms.internal.firebase-auth-api.zzaen r0 = new com.google.android.gms.internal.firebase-auth-api.zzaen
            r4 = r0
            com.google.android.gms.internal.firebase-auth-api.zzaek r9 = r34.zza()
            r11 = 0
            r1 = r29
            r20 = 0
            r5 = r31
            r6 = r1
            r7 = r14
            r8 = r32
            r12 = r13
            r13 = r24
            r14 = r21
            r15 = r35
            r16 = r36
            r17 = r37
            r18 = r38
            r19 = r39
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzm(com.google.android.gms.internal.firebase-auth-api.zzaeu, com.google.android.gms.internal.firebase-auth-api.zzaep, com.google.android.gms.internal.firebase-auth-api.zzady, com.google.android.gms.internal.firebase-auth-api.zzafn, com.google.android.gms.internal.firebase-auth-api.zzact, com.google.android.gms.internal.firebase-auth-api.zzaef):com.google.android.gms.internal.firebase-auth-api.zzaen");
    }

    private static double zzo(Object obj, long j) {
        return ((Double) zzafx.zzf(obj, j)).doubleValue();
    }

    private static float zzp(Object obj, long j) {
        return ((Float) zzafx.zzf(obj, j)).floatValue();
    }

    private final int zzq(Object obj) {
        int i;
        Object obj2 = obj;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.zzc.length) {
            int zzC = zzC(i4);
            int[] iArr = this.zzc;
            int i7 = iArr[i4];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
                int i8 = iArr[i4 + 2];
                int i9 = i8 & i2;
                i = 1 << (i8 >>> 20);
                if (i9 != i3) {
                    i6 = unsafe.getInt(obj2, (long) i9);
                    i3 = i9;
                }
            } else {
                i = 0;
            }
            long j = (long) (zzC & i2);
            switch (zzB) {
                case 0:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 1:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 2:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF(unsafe.getLong(obj2, j));
                        break;
                    }
                case 3:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF(unsafe.getLong(obj2, j));
                        break;
                    }
                case 4:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzy(unsafe.getInt(obj2, j));
                        break;
                    }
                case 5:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 6:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 7:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 1;
                        break;
                    }
                case 8:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj2, j);
                        if (!(object instanceof zzacc)) {
                            i5 += zzacn.zzE(i7 << 3) + zzacn.zzC((String) object);
                            break;
                        } else {
                            int zzE = zzacn.zzE(i7 << 3);
                            int zzd2 = ((zzacc) object).zzd();
                            i5 += zzE + zzacn.zzE(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzaey.zzo(i7, unsafe.getObject(obj2, j), zzF(i4));
                        break;
                    }
                case 10:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        int zzE2 = zzacn.zzE(i7 << 3);
                        int zzd3 = ((zzacc) unsafe.getObject(obj2, j)).zzd();
                        i5 += zzE2 + zzacn.zzE(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzE(unsafe.getInt(obj2, j));
                        break;
                    }
                case 12:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzy(unsafe.getInt(obj2, j));
                        break;
                    }
                case 13:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 14:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 15:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        int i10 = unsafe.getInt(obj2, j);
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzE((i10 >> 31) ^ (i10 + i10));
                        break;
                    }
                case 16:
                    if ((i & i6) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj2, j);
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i6 & i) == 0) {
                        break;
                    } else {
                        i5 += zzacn.zzx(i7, (zzaek) unsafe.getObject(obj2, j), zzF(i4));
                        break;
                    }
                case 18:
                    i5 += zzaey.zzh(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 19:
                    i5 += zzaey.zzf(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 20:
                    i5 += zzaey.zzm(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 21:
                    i5 += zzaey.zzx(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 22:
                    i5 += zzaey.zzk(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 23:
                    i5 += zzaey.zzh(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 24:
                    i5 += zzaey.zzf(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 25:
                    i5 += zzaey.zza(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 26:
                    i5 += zzaey.zzu(i7, (List) unsafe.getObject(obj2, j));
                    break;
                case 27:
                    i5 += zzaey.zzp(i7, (List) unsafe.getObject(obj2, j), zzF(i4));
                    break;
                case 28:
                    i5 += zzaey.zzc(i7, (List) unsafe.getObject(obj2, j));
                    break;
                case 29:
                    i5 += zzaey.zzv(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 30:
                    i5 += zzaey.zzd(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 31:
                    i5 += zzaey.zzf(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 32:
                    i5 += zzaey.zzh(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 33:
                    i5 += zzaey.zzq(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 34:
                    i5 += zzaey.zzs(i7, (List) unsafe.getObject(obj2, j), false);
                    break;
                case 35:
                    int zzi2 = zzaey.zzi((List) unsafe.getObject(obj2, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzaey.zzg((List) unsafe.getObject(obj2, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzaey.zzn((List) unsafe.getObject(obj2, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzaey.zzy((List) unsafe.getObject(obj2, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzaey.zzl((List) unsafe.getObject(obj2, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzaey.zzi((List) unsafe.getObject(obj2, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzaey.zzg((List) unsafe.getObject(obj2, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzaey.zzb((List) unsafe.getObject(obj2, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzaey.zzw((List) unsafe.getObject(obj2, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzaey.zze((List) unsafe.getObject(obj2, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzaey.zzg((List) unsafe.getObject(obj2, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzaey.zzi((List) unsafe.getObject(obj2, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr2 = zzaey.zzr((List) unsafe.getObject(obj2, j));
                    if (zzr2 <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzr2) + zzr2;
                        break;
                    }
                case 48:
                    int zzt = zzaey.zzt((List) unsafe.getObject(obj2, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i5 += zzacn.zzD(i7) + zzacn.zzE(zzt) + zzt;
                        break;
                    }
                case 49:
                    i5 += zzaey.zzj(i7, (List) unsafe.getObject(obj2, j), zzF(i4));
                    break;
                case 50:
                    zzaef.zza(i7, unsafe.getObject(obj2, j), zzH(i4));
                    break;
                case 51:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF(zzD(obj2, j));
                        break;
                    }
                case 54:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF(zzD(obj2, j));
                        break;
                    }
                case 55:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzy(zzs(obj2, j));
                        break;
                    }
                case 56:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(obj2, j);
                        if (!(object2 instanceof zzacc)) {
                            i5 += zzacn.zzE(i7 << 3) + zzacn.zzC((String) object2);
                            break;
                        } else {
                            int zzE3 = zzacn.zzE(i7 << 3);
                            int zzd4 = ((zzacc) object2).zzd();
                            i5 += zzE3 + zzacn.zzE(zzd4) + zzd4;
                            break;
                        }
                    }
                case 60:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzaey.zzo(i7, unsafe.getObject(obj2, j), zzF(i4));
                        break;
                    }
                case 61:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        int zzE4 = zzacn.zzE(i7 << 3);
                        int zzd5 = ((zzacc) unsafe.getObject(obj2, j)).zzd();
                        i5 += zzE4 + zzacn.zzE(zzd5) + zzd5;
                        break;
                    }
                case 62:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzE(zzs(obj2, j));
                        break;
                    }
                case 63:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzy(zzs(obj2, j));
                        break;
                    }
                case 64:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzE(i7 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        int zzs = zzs(obj2, j);
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzE((zzs >> 31) ^ (zzs + zzs));
                        break;
                    }
                case 67:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        long zzD = zzD(obj2, j);
                        i5 += zzacn.zzE(i7 << 3) + zzacn.zzF((zzD >> 63) ^ (zzD + zzD));
                        break;
                    }
                case 68:
                    if (!zzZ(obj2, i7, i4)) {
                        break;
                    } else {
                        i5 += zzacn.zzx(i7, (zzaek) unsafe.getObject(obj2, j), zzF(i4));
                        break;
                    }
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzafn zzafn = this.zzo;
        int zza2 = i5 + zzafn.zza(zzafn.zzd(obj2));
        if (!this.zzh) {
            return zza2;
        }
        this.zzp.zza(obj2);
        throw null;
    }

    private final int zzr(Object obj) {
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzC = zzC(i2);
            int zzB = zzB(zzC);
            int i3 = this.zzc[i2];
            long j = (long) (zzC & 1048575);
            if (zzB >= zzacy.DOUBLE_LIST_PACKED.zza() && zzB <= zzacy.SINT64_LIST_PACKED.zza()) {
                int i4 = this.zzc[i2 + 2];
            }
            switch (zzB) {
                case 0:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 1:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 2:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF(zzafx.zzd(obj, j));
                        break;
                    }
                case 3:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF(zzafx.zzd(obj, j));
                        break;
                    }
                case 4:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzy(zzafx.zzc(obj, j));
                        break;
                    }
                case 5:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 6:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 7:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 1;
                        break;
                    }
                case 8:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        Object zzf2 = zzafx.zzf(obj, j);
                        if (!(zzf2 instanceof zzacc)) {
                            i += zzacn.zzE(i3 << 3) + zzacn.zzC((String) zzf2);
                            break;
                        } else {
                            int zzE = zzacn.zzE(i3 << 3);
                            int zzd2 = ((zzacc) zzf2).zzd();
                            i += zzE + zzacn.zzE(zzd2) + zzd2;
                            break;
                        }
                    }
                case 9:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzaey.zzo(i3, zzafx.zzf(obj, j), zzF(i2));
                        break;
                    }
                case 10:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        int zzE2 = zzacn.zzE(i3 << 3);
                        int zzd3 = ((zzacc) zzafx.zzf(obj, j)).zzd();
                        i += zzE2 + zzacn.zzE(zzd3) + zzd3;
                        break;
                    }
                case 11:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzE(zzafx.zzc(obj, j));
                        break;
                    }
                case 12:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzy(zzafx.zzc(obj, j));
                        break;
                    }
                case 13:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 14:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 15:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        int zzc2 = zzafx.zzc(obj, j);
                        i += zzacn.zzE(i3 << 3) + zzacn.zzE((zzc2 >> 31) ^ (zzc2 + zzc2));
                        break;
                    }
                case 16:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        long zzd4 = zzafx.zzd(obj, j);
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF((zzd4 >> 63) ^ (zzd4 + zzd4));
                        break;
                    }
                case 17:
                    if (!zzV(obj, i2)) {
                        break;
                    } else {
                        i += zzacn.zzx(i3, (zzaek) zzafx.zzf(obj, j), zzF(i2));
                        break;
                    }
                case 18:
                    i += zzaey.zzh(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 19:
                    i += zzaey.zzf(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 20:
                    i += zzaey.zzm(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 21:
                    i += zzaey.zzx(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 22:
                    i += zzaey.zzk(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 23:
                    i += zzaey.zzh(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 24:
                    i += zzaey.zzf(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 25:
                    i += zzaey.zza(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 26:
                    i += zzaey.zzu(i3, (List) zzafx.zzf(obj, j));
                    break;
                case 27:
                    i += zzaey.zzp(i3, (List) zzafx.zzf(obj, j), zzF(i2));
                    break;
                case 28:
                    i += zzaey.zzc(i3, (List) zzafx.zzf(obj, j));
                    break;
                case 29:
                    i += zzaey.zzv(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 30:
                    i += zzaey.zzd(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 31:
                    i += zzaey.zzf(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 32:
                    i += zzaey.zzh(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 33:
                    i += zzaey.zzq(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 34:
                    i += zzaey.zzs(i3, (List) zzafx.zzf(obj, j), false);
                    break;
                case 35:
                    int zzi2 = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzi2) + zzi2;
                        break;
                    }
                case 36:
                    int zzg2 = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzg2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzg2) + zzg2;
                        break;
                    }
                case 37:
                    int zzn2 = zzaey.zzn((List) unsafe.getObject(obj, j));
                    if (zzn2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzn2) + zzn2;
                        break;
                    }
                case 38:
                    int zzy = zzaey.zzy((List) unsafe.getObject(obj, j));
                    if (zzy <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzy) + zzy;
                        break;
                    }
                case 39:
                    int zzl2 = zzaey.zzl((List) unsafe.getObject(obj, j));
                    if (zzl2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzl2) + zzl2;
                        break;
                    }
                case 40:
                    int zzi3 = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi3 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzi3) + zzi3;
                        break;
                    }
                case 41:
                    int zzg3 = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzg3 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzg3) + zzg3;
                        break;
                    }
                case 42:
                    int zzb2 = zzaey.zzb((List) unsafe.getObject(obj, j));
                    if (zzb2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzb2) + zzb2;
                        break;
                    }
                case 43:
                    int zzw = zzaey.zzw((List) unsafe.getObject(obj, j));
                    if (zzw <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzw) + zzw;
                        break;
                    }
                case 44:
                    int zze2 = zzaey.zze((List) unsafe.getObject(obj, j));
                    if (zze2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zze2) + zze2;
                        break;
                    }
                case 45:
                    int zzg4 = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzg4 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzg4) + zzg4;
                        break;
                    }
                case 46:
                    int zzi4 = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi4 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzi4) + zzi4;
                        break;
                    }
                case 47:
                    int zzr2 = zzaey.zzr((List) unsafe.getObject(obj, j));
                    if (zzr2 <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzr2) + zzr2;
                        break;
                    }
                case 48:
                    int zzt = zzaey.zzt((List) unsafe.getObject(obj, j));
                    if (zzt <= 0) {
                        break;
                    } else {
                        i += zzacn.zzD(i3) + zzacn.zzE(zzt) + zzt;
                        break;
                    }
                case 49:
                    i += zzaey.zzj(i3, (List) zzafx.zzf(obj, j), zzF(i2));
                    break;
                case 50:
                    zzaef.zza(i3, zzafx.zzf(obj, j), zzH(i2));
                    break;
                case 51:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 52:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 53:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF(zzD(obj, j));
                        break;
                    }
                case 54:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF(zzD(obj, j));
                        break;
                    }
                case 55:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzy(zzs(obj, j));
                        break;
                    }
                case 56:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 57:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 58:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 1;
                        break;
                    }
                case 59:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        Object zzf3 = zzafx.zzf(obj, j);
                        if (!(zzf3 instanceof zzacc)) {
                            i += zzacn.zzE(i3 << 3) + zzacn.zzC((String) zzf3);
                            break;
                        } else {
                            int zzE3 = zzacn.zzE(i3 << 3);
                            int zzd5 = ((zzacc) zzf3).zzd();
                            i += zzE3 + zzacn.zzE(zzd5) + zzd5;
                            break;
                        }
                    }
                case 60:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzaey.zzo(i3, zzafx.zzf(obj, j), zzF(i2));
                        break;
                    }
                case 61:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        int zzE4 = zzacn.zzE(i3 << 3);
                        int zzd6 = ((zzacc) zzafx.zzf(obj, j)).zzd();
                        i += zzE4 + zzacn.zzE(zzd6) + zzd6;
                        break;
                    }
                case 62:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzE(zzs(obj, j));
                        break;
                    }
                case 63:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + zzacn.zzy(zzs(obj, j));
                        break;
                    }
                case 64:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 4;
                        break;
                    }
                case 65:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzE(i3 << 3) + 8;
                        break;
                    }
                case 66:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        int zzs = zzs(obj, j);
                        i += zzacn.zzE(i3 << 3) + zzacn.zzE((zzs >> 31) ^ (zzs + zzs));
                        break;
                    }
                case 67:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        long zzD = zzD(obj, j);
                        i += zzacn.zzE(i3 << 3) + zzacn.zzF((zzD >> 63) ^ (zzD + zzD));
                        break;
                    }
                case 68:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i += zzacn.zzx(i3, (zzaek) zzafx.zzf(obj, j), zzF(i2));
                        break;
                    }
            }
        }
        zzafn zzafn = this.zzo;
        return i + zzafn.zza(zzafn.zzd(obj));
    }

    private static int zzs(Object obj, long j) {
        return ((Integer) zzafx.zzf(obj, j)).intValue();
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzabp zzabp) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzaef.zzb(object)) {
            zzaee zzb2 = zzaee.zza().zzb();
            zzaef.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzaed zzaed = (zzaed) zzH;
        throw null;
    }

    private final int zzu(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzabp zzabp) throws IOException {
        boolean z;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        zzabp zzabp2 = zzabp;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Double.valueOf(Double.longBitsToDouble(zzabq.zzp(bArr, i))));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Float.valueOf(Float.intBitsToFloat(zzabq.zzb(bArr, i))));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzm2 = zzabq.zzm(bArr2, i9, zzabp2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzabp2.zzb));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm2;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int zzj2 = zzabq.zzj(bArr2, i9, zzabp2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzabp2.zza));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Long.valueOf(zzabq.zzp(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzabq.zzb(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int zzm3 = zzabq.zzm(bArr2, i9, zzabp2);
                    if (zzabp2.zzb != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    unsafe.putObject(obj2, j2, Boolean.valueOf(z));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm3;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int zzj3 = zzabq.zzj(bArr2, i9, zzabp2);
                    int i14 = zzabp2.zza;
                    if (i14 == 0) {
                        unsafe.putObject(obj2, j2, "");
                    } else if ((i6 & 536870912) == 0 || zzagc.zzf(bArr2, zzj3, zzj3 + i14)) {
                        unsafe.putObject(obj2, j2, new String(bArr2, zzj3, i14, zzadl.zzb));
                        zzj3 += i14;
                    } else {
                        throw zzadn.zzd();
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return zzj3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    Object zzJ = zzJ(obj2, i11, i13);
                    int zzo2 = zzabq.zzo(zzJ, zzF(i13), bArr, i, i2, zzabp);
                    zzS(obj2, i11, i13, zzJ);
                    return zzo2;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int zza2 = zzabq.zza(bArr2, i9, zzabp2);
                    unsafe.putObject(obj2, j2, zzabp2.zzc);
                    unsafe.putInt(obj2, j3, i11);
                    return zza2;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int zzj4 = zzabq.zzj(bArr2, i9, zzabp2);
                    int i15 = zzabp2.zza;
                    zzadj zzE = zzE(i13);
                    if (zzE == null || zzE.zza()) {
                        unsafe.putObject(obj2, j2, Integer.valueOf(i15));
                        unsafe.putInt(obj2, j3, i11);
                        return zzj4;
                    }
                    zzd(obj).zzj(i10, Long.valueOf((long) i15));
                    return zzj4;
                }
                break;
            case 66:
                if (i12 == 0) {
                    int zzj5 = zzabq.zzj(bArr2, i9, zzabp2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzacg.zzs(zzabp2.zza)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzm4 = zzabq.zzm(bArr2, i9, zzabp2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzacg.zzt(zzabp2.zzb)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    Object zzJ2 = zzJ(obj2, i11, i13);
                    int i16 = (i10 & -8) | 4;
                    int zzn2 = zzabq.zzn(zzJ2, zzF(i13), bArr, i, i2, i16, zzabp);
                    zzS(obj2, i11, i13, zzJ2);
                    return zzn2;
                }
                break;
        }
        return i9;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzv(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.p002firebaseauthapi.zzabp r35) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            zzL(r31)
            sun.misc.Unsafe r9 = zzb
            r10 = -1
            r16 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r33
            r7 = r8
            r1 = r10
            r2 = r16
            r6 = r2
        L_0x001c:
            if (r0 >= r13) goto L_0x047e
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002e
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzk(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x0031
        L_0x002e:
            r17 = r0
            r4 = r3
        L_0x0031:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x003f
            int r2 = r2 / 3
            int r0 = r15.zzy(r5, r2)
            r2 = r0
            goto L_0x0044
        L_0x003f:
            int r0 = r15.zzx(r5)
            r2 = r0
        L_0x0044:
            if (r2 != r10) goto L_0x0051
            r2 = r4
            r23 = r5
            r29 = r9
            r18 = r10
            r15 = r16
            goto L_0x0458
        L_0x0051:
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r13 = zzB(r1)
            r10 = r1 & r8
            r19 = r9
            long r8 = (long) r10
            r10 = 17
            r33 = r5
            if (r13 > r10) goto L_0x030d
            int r10 = r2 + 2
            r0 = r0[r10]
            int r10 = r0 >>> 20
            r5 = 1
            int r10 = r5 << r10
            r15 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r15
            if (r0 == r7) goto L_0x0094
            if (r7 == r15) goto L_0x0082
            r22 = r1
            r20 = r2
            long r1 = (long) r7
            r7 = r19
            r7.putInt(r14, r1, r6)
            goto L_0x0088
        L_0x0082:
            r22 = r1
            r20 = r2
            r7 = r19
        L_0x0088:
            if (r0 == r15) goto L_0x0090
            long r1 = (long) r0
            int r6 = r7.getInt(r14, r1)
            goto L_0x0091
        L_0x0090:
        L_0x0091:
            r2 = r7
            r7 = r0
            goto L_0x009a
        L_0x0094:
            r22 = r1
            r20 = r2
            r2 = r19
        L_0x009a:
            r0 = 5
            switch(r13) {
                case 0: goto L_0x02da;
                case 1: goto L_0x02b0;
                case 2: goto L_0x0284;
                case 3: goto L_0x0284;
                case 4: goto L_0x025d;
                case 5: goto L_0x022e;
                case 6: goto L_0x0207;
                case 7: goto L_0x01d7;
                case 8: goto L_0x01a4;
                case 9: goto L_0x0168;
                case 10: goto L_0x013d;
                case 11: goto L_0x025d;
                case 12: goto L_0x0113;
                case 13: goto L_0x0207;
                case 14: goto L_0x022e;
                case 15: goto L_0x00e5;
                case 16: goto L_0x00ab;
                default: goto L_0x009e;
            }
        L_0x009e:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            goto L_0x0304
        L_0x00ab:
            if (r3 != 0) goto L_0x00d7
            int r13 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r4, r11)
            long r0 = r11.zzb
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzacg.zzt(r0)
            r0 = r2
            r1 = r31
            r15 = r20
            r20 = r7
            r7 = r2
            r2 = r8
            r23 = r33
            r0.putLong(r1, r2, r4)
            r6 = r6 | r10
            r9 = r7
            r0 = r13
            r2 = r15
            r7 = r20
            r1 = r23
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r15 = r30
            r13 = r34
            goto L_0x001c
        L_0x00d7:
            r23 = r33
            r15 = r20
            r20 = r7
            r7 = r2
            r13 = r30
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0304
        L_0x00e5:
            r23 = r33
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x010c
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r4, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzacg.zzs(r1)
            r7.putInt(r14, r8, r1)
            r6 = r6 | r10
            r13 = r34
            r9 = r7
            r2 = r15
            r7 = r20
            r1 = r23
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r15 = r30
            goto L_0x001c
        L_0x010c:
            r13 = r30
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0304
        L_0x0113:
            r23 = r33
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x0136
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r4, r11)
            int r1 = r11.zza
            r7.putInt(r14, r8, r1)
            r6 = r6 | r10
            r13 = r34
            r9 = r7
            r2 = r15
            r7 = r20
            r1 = r23
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r15 = r30
            goto L_0x001c
        L_0x0136:
            r13 = r30
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0304
        L_0x013d:
            r23 = r33
            r15 = r20
            r20 = r7
            r7 = r2
            r0 = 2
            if (r3 != r0) goto L_0x0161
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zza(r12, r4, r11)
            java.lang.Object r1 = r11.zzc
            r7.putObject(r14, r8, r1)
            r6 = r6 | r10
            r13 = r34
            r9 = r7
            r2 = r15
            r7 = r20
            r1 = r23
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            r15 = r30
            goto L_0x001c
        L_0x0161:
            r13 = r30
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0304
        L_0x0168:
            r23 = r33
            r15 = r20
            r0 = 2
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x019d
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r30
            java.lang.Object r8 = r13.zzI(r14, r15)
            com.google.android.gms.internal.firebase-auth-api.zzaew r1 = r13.zzF(r15)
            r0 = r8
            r2 = r32
            r3 = r4
            r4 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzo(r0, r1, r2, r3, r4, r5)
            r13.zzR(r14, r15, r8)
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x019d:
            r13 = r30
            r19 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0304
        L_0x01a4:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            r0 = 2
            if (r3 != r0) goto L_0x01d5
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r22 & r0
            if (r0 != 0) goto L_0x01bd
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzg(r12, r4, r11)
            goto L_0x01c1
        L_0x01bd:
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzh(r12, r4, r11)
        L_0x01c1:
            java.lang.Object r1 = r11.zzc
            r7.putObject(r14, r8, r1)
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x01d5:
            goto L_0x0304
        L_0x01d7:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x0205
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r4, r11)
            long r1 = r11.zzb
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x01f1
            goto L_0x01f3
        L_0x01f1:
            r5 = r16
        L_0x01f3:
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzm(r14, r8, r5)
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x0205:
            goto L_0x0304
        L_0x0207:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x022c
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzb(r12, r4)
            r7.putInt(r14, r8, r0)
            int r0 = r4 + 4
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x022c:
            goto L_0x0304
        L_0x022e:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != r5) goto L_0x025a
            long r21 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzp(r12, r4)
            r0 = r7
            r1 = r31
            r2 = r8
            r8 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x025a:
            r8 = r4
            goto L_0x0304
        L_0x025d:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x0282
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r4, r11)
            int r1 = r11.zza
            r7.putInt(r14, r8, r1)
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x0282:
            goto L_0x0304
        L_0x0284:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x02af
            int r17 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r4, r11)
            long r4 = r11.zzb
            r0 = r7
            r1 = r31
            r2 = r8
            r0.putLong(r1, r2, r4)
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r0 = r17
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x02af:
            goto L_0x0304
        L_0x02b0:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x02d9
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzb(r12, r4)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzp(r14, r8, r0)
            int r0 = r4 + 4
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x02d9:
            goto L_0x0304
        L_0x02da:
            r13 = r30
            r23 = r33
            r19 = r15
            r15 = r20
            r20 = r7
            r7 = r2
            if (r3 != r5) goto L_0x0303
            long r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzp(r12, r4)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzo(r14, r8, r0)
            int r0 = r4 + 8
            r6 = r6 | r10
            r9 = r7
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r10 = -1
            r15 = r13
            r13 = r34
            goto L_0x001c
        L_0x0303:
        L_0x0304:
            r2 = r4
            r29 = r7
            r7 = r20
            r18 = -1
            goto L_0x0458
        L_0x030d:
            r23 = r33
            r22 = r1
            r20 = r7
            r10 = r15
            r7 = r19
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r2
            r0 = 27
            if (r13 != r0) goto L_0x036e
            r0 = 2
            if (r3 != r0) goto L_0x0362
            java.lang.Object r0 = r7.getObject(r14, r8)
            com.google.android.gms.internal.firebase-auth-api.zzadk r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzadk) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x0340
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0336
            r1 = 10
            goto L_0x0337
        L_0x0336:
            int r1 = r1 + r1
        L_0x0337:
            com.google.android.gms.internal.firebase-auth-api.zzadk r0 = r0.zzd(r1)
            r7.putObject(r14, r8, r0)
            r5 = r0
            goto L_0x0341
        L_0x0340:
            r5 = r0
        L_0x0341:
            com.google.android.gms.internal.firebase-auth-api.zzaew r0 = r10.zzF(r15)
            r1 = r17
            r2 = r32
            r3 = r4
            r4 = r34
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zze(r0, r1, r2, r3, r4, r5, r6)
            r13 = r34
            r9 = r7
            r6 = r8
            r2 = r15
            r8 = r19
            r7 = r20
            r1 = r23
            r15 = r10
            r10 = -1
            goto L_0x001c
        L_0x0362:
            r8 = r6
            r14 = r4
            r29 = r7
            r25 = r8
            r26 = r20
            r18 = -1
            goto L_0x0417
        L_0x036e:
            r0 = 49
            if (r13 > r0) goto L_0x03ca
            r1 = r22
            long r1 = (long) r1
            r0 = r30
            r21 = r1
            r1 = r31
            r2 = r32
            r5 = r3
            r3 = r4
            r24 = r4
            r4 = r34
            r33 = r5
            r5 = r17
            r25 = r6
            r6 = r23
            r26 = r20
            r20 = r7
            r7 = r33
            r27 = r8
            r9 = r19
            r8 = r15
            r29 = r20
            r18 = -1
            r9 = r21
            r11 = r13
            r12 = r27
            r14 = r35
            int r0 = r0.zzw(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            r14 = r24
            if (r0 == r14) goto L_0x03c3
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r15
            r10 = r18
            r1 = r23
            r6 = r25
            r7 = r26
            r9 = r29
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r30
            goto L_0x001c
        L_0x03c3:
            r2 = r0
            r6 = r25
            r7 = r26
            goto L_0x0458
        L_0x03ca:
            r33 = r3
            r14 = r4
            r25 = r6
            r29 = r7
            r27 = r8
            r26 = r20
            r1 = r22
            r18 = -1
            r0 = 50
            if (r13 != r0) goto L_0x041d
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x0416
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r14
            r4 = r34
            r5 = r15
            r6 = r27
            r8 = r35
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r14) goto L_0x0410
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r15
            r10 = r18
            r1 = r23
            r6 = r25
            r7 = r26
            r9 = r29
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r30
            goto L_0x001c
        L_0x0410:
            r2 = r0
            r6 = r25
            r7 = r26
            goto L_0x0458
        L_0x0416:
        L_0x0417:
            r2 = r14
            r6 = r25
            r7 = r26
            goto L_0x0458
        L_0x041d:
            r7 = r33
            r0 = r30
            r8 = r1
            r1 = r31
            r2 = r32
            r3 = r14
            r4 = r34
            r5 = r17
            r6 = r23
            r9 = r13
            r10 = r27
            r12 = r15
            r13 = r35
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r14) goto L_0x0453
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r15
            r10 = r18
            r1 = r23
            r6 = r25
            r7 = r26
            r9 = r29
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r30
            goto L_0x001c
        L_0x0453:
            r2 = r0
            r6 = r25
            r7 = r26
        L_0x0458:
            com.google.android.gms.internal.firebase-auth-api.zzafo r4 = zzd(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzi(r0, r1, r2, r3, r4, r5)
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r15
            r10 = r18
            r1 = r23
            r9 = r29
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r30
            goto L_0x001c
        L_0x047e:
            r25 = r6
            r26 = r7
            r29 = r9
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 == r1) goto L_0x0493
            long r1 = (long) r7
            r3 = r31
            r6 = r25
            r4 = r29
            r4.putInt(r3, r1, r6)
        L_0x0493:
            r1 = r34
            if (r0 != r1) goto L_0x0498
            return r0
        L_0x0498:
            com.google.android.gms.internal.firebase-auth-api.zzadn r0 = com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzv(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzabp):int");
    }

    private final int zzw(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzabp zzabp) throws IOException {
        boolean z;
        boolean z2;
        boolean z3;
        int i8;
        int i9;
        int i10;
        int i11;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i12 = i;
        int i13 = i2;
        int i14 = i3;
        int i15 = i5;
        int i16 = i6;
        long j3 = j2;
        zzabp zzabp2 = zzabp;
        Unsafe unsafe = zzb;
        zzadk zzadk = (zzadk) unsafe.getObject(obj2, j3);
        if (!zzadk.zzc()) {
            int size = zzadk.size();
            zzadk = zzadk.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj2, j3, zzadk);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i15 == 2) {
                    zzacp zzacp = (zzacp) zzadk;
                    int zzj2 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i17 = zzabp2.zza + zzj2;
                    while (zzj2 < i17) {
                        zzacp.zze(Double.longBitsToDouble(zzabq.zzp(bArr2, zzj2)));
                        zzj2 += 8;
                    }
                    if (zzj2 == i17) {
                        return zzj2;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 1) {
                    zzacp zzacp2 = (zzacp) zzadk;
                    zzacp2.zze(Double.longBitsToDouble(zzabq.zzp(bArr, i)));
                    int i18 = i12 + 8;
                    while (i18 < i13) {
                        int zzj3 = zzabq.zzj(bArr2, i18, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return i18;
                        }
                        zzacp2.zze(Double.longBitsToDouble(zzabq.zzp(bArr2, zzj3)));
                        i18 = zzj3 + 8;
                    }
                    return i18;
                }
                break;
            case 19:
            case 36:
                if (i15 == 2) {
                    zzacz zzacz = (zzacz) zzadk;
                    int zzj4 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i19 = zzabp2.zza + zzj4;
                    while (zzj4 < i19) {
                        zzacz.zze(Float.intBitsToFloat(zzabq.zzb(bArr2, zzj4)));
                        zzj4 += 4;
                    }
                    if (zzj4 == i19) {
                        return zzj4;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 5) {
                    zzacz zzacz2 = (zzacz) zzadk;
                    zzacz2.zze(Float.intBitsToFloat(zzabq.zzb(bArr, i)));
                    int i20 = i12 + 4;
                    while (i20 < i13) {
                        int zzj5 = zzabq.zzj(bArr2, i20, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return i20;
                        }
                        zzacz2.zze(Float.intBitsToFloat(zzabq.zzb(bArr2, zzj5)));
                        i20 = zzj5 + 4;
                    }
                    return i20;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i15 == 2) {
                    zzadz zzadz = (zzadz) zzadk;
                    int zzj6 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i21 = zzabp2.zza + zzj6;
                    while (zzj6 < i21) {
                        zzj6 = zzabq.zzm(bArr2, zzj6, zzabp2);
                        zzadz.zzf(zzabp2.zzb);
                    }
                    if (zzj6 == i21) {
                        return zzj6;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 0) {
                    zzadz zzadz2 = (zzadz) zzadk;
                    int zzm2 = zzabq.zzm(bArr2, i12, zzabp2);
                    zzadz2.zzf(zzabp2.zzb);
                    while (zzm2 < i13) {
                        int zzj7 = zzabq.zzj(bArr2, zzm2, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return zzm2;
                        }
                        zzm2 = zzabq.zzm(bArr2, zzj7, zzabp2);
                        zzadz2.zzf(zzabp2.zzb);
                    }
                    return zzm2;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i15 == 2) {
                    return zzabq.zzf(bArr2, i12, zzadk, zzabp2);
                }
                if (i15 == 0) {
                    return zzabq.zzl(i3, bArr, i, i2, zzadk, zzabp);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i15 == 2) {
                    zzadz zzadz3 = (zzadz) zzadk;
                    int zzj8 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i22 = zzabp2.zza + zzj8;
                    while (zzj8 < i22) {
                        zzadz3.zzf(zzabq.zzp(bArr2, zzj8));
                        zzj8 += 8;
                    }
                    if (zzj8 == i22) {
                        return zzj8;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 1) {
                    zzadz zzadz4 = (zzadz) zzadk;
                    zzadz4.zzf(zzabq.zzp(bArr, i));
                    int i23 = i12 + 8;
                    while (i23 < i13) {
                        int zzj9 = zzabq.zzj(bArr2, i23, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return i23;
                        }
                        zzadz4.zzf(zzabq.zzp(bArr2, zzj9));
                        i23 = zzj9 + 8;
                    }
                    return i23;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i15 == 2) {
                    zzadg zzadg = (zzadg) zzadk;
                    int zzj10 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i24 = zzabp2.zza + zzj10;
                    while (zzj10 < i24) {
                        zzadg.zzf(zzabq.zzb(bArr2, zzj10));
                        zzj10 += 4;
                    }
                    if (zzj10 == i24) {
                        return zzj10;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 5) {
                    zzadg zzadg2 = (zzadg) zzadk;
                    zzadg2.zzf(zzabq.zzb(bArr, i));
                    int i25 = i12 + 4;
                    while (i25 < i13) {
                        int zzj11 = zzabq.zzj(bArr2, i25, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return i25;
                        }
                        zzadg2.zzf(zzabq.zzb(bArr2, zzj11));
                        i25 = zzj11 + 4;
                    }
                    return i25;
                }
                break;
            case 25:
            case 42:
                if (i15 == 2) {
                    zzabr zzabr = (zzabr) zzadk;
                    int zzj12 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i26 = zzabp2.zza + zzj12;
                    while (zzj12 < i26) {
                        zzj12 = zzabq.zzm(bArr2, zzj12, zzabp2);
                        if (zzabp2.zzb != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        zzabr.zze(z3);
                    }
                    if (zzj12 == i26) {
                        return zzj12;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 0) {
                    zzabr zzabr2 = (zzabr) zzadk;
                    int zzm3 = zzabq.zzm(bArr2, i12, zzabp2);
                    if (zzabp2.zzb != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    zzabr2.zze(z);
                    while (zzm3 < i13) {
                        int zzj13 = zzabq.zzj(bArr2, zzm3, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return zzm3;
                        }
                        zzm3 = zzabq.zzm(bArr2, zzj13, zzabp2);
                        if (zzabp2.zzb != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zzabr2.zze(z2);
                    }
                    return zzm3;
                }
                break;
            case 26:
                if (i15 == 2) {
                    if ((j & 536870912) == 0) {
                        int zzj14 = zzabq.zzj(bArr2, i12, zzabp2);
                        int i27 = zzabp2.zza;
                        if (i27 >= 0) {
                            if (i27 == 0) {
                                zzadk.add("");
                            } else {
                                zzadk.add(new String(bArr2, zzj14, i27, zzadl.zzb));
                                zzj14 += i27;
                            }
                            while (i9 < i13) {
                                int zzj15 = zzabq.zzj(bArr2, i9, zzabp2);
                                if (i14 != zzabp2.zza) {
                                    return i9;
                                }
                                i9 = zzabq.zzj(bArr2, zzj15, zzabp2);
                                int i28 = zzabp2.zza;
                                if (i28 < 0) {
                                    throw zzadn.zzf();
                                } else if (i28 == 0) {
                                    zzadk.add("");
                                } else {
                                    zzadk.add(new String(bArr2, i9, i28, zzadl.zzb));
                                    i9 += i28;
                                }
                            }
                            return i9;
                        }
                        throw zzadn.zzf();
                    }
                    int zzj16 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i29 = zzabp2.zza;
                    if (i29 >= 0) {
                        if (i29 == 0) {
                            zzadk.add("");
                            i8 = zzj16;
                        } else {
                            i8 = zzj16 + i29;
                            if (zzagc.zzf(bArr2, zzj16, i8)) {
                                zzadk.add(new String(bArr2, zzj16, i29, zzadl.zzb));
                            } else {
                                throw zzadn.zzd();
                            }
                        }
                        while (i8 < i13) {
                            int zzj17 = zzabq.zzj(bArr2, i8, zzabp2);
                            if (i14 != zzabp2.zza) {
                                return i8;
                            }
                            int zzj18 = zzabq.zzj(bArr2, zzj17, zzabp2);
                            int i30 = zzabp2.zza;
                            if (i30 < 0) {
                                throw zzadn.zzf();
                            } else if (i30 == 0) {
                                zzadk.add("");
                            } else {
                                int i31 = zzj18 + i30;
                                if (zzagc.zzf(bArr2, zzj18, i31)) {
                                    zzadk.add(new String(bArr2, zzj18, i30, zzadl.zzb));
                                    zzj18 = i31;
                                } else {
                                    throw zzadn.zzd();
                                }
                            }
                        }
                        return i8;
                    }
                    throw zzadn.zzf();
                }
                break;
            case 27:
                if (i15 == 2) {
                    return zzabq.zze(zzF(i16), i3, bArr, i, i2, zzadk, zzabp);
                }
                break;
            case 28:
                if (i15 == 2) {
                    int zzj19 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i32 = zzabp2.zza;
                    if (i32 < 0) {
                        throw zzadn.zzf();
                    } else if (i32 <= bArr2.length - zzj19) {
                        if (i32 == 0) {
                            zzadk.add(zzacc.zzb);
                        } else {
                            zzadk.add(zzacc.zzo(bArr2, zzj19, i32));
                            zzj19 += i32;
                        }
                        while (i10 < i13) {
                            int zzj20 = zzabq.zzj(bArr2, i10, zzabp2);
                            if (i14 != zzabp2.zza) {
                                return i10;
                            }
                            i10 = zzabq.zzj(bArr2, zzj20, zzabp2);
                            int i33 = zzabp2.zza;
                            if (i33 < 0) {
                                throw zzadn.zzf();
                            } else if (i33 > bArr2.length - i10) {
                                throw zzadn.zzi();
                            } else if (i33 == 0) {
                                zzadk.add(zzacc.zzb);
                            } else {
                                zzadk.add(zzacc.zzo(bArr2, i10, i33));
                                i10 += i33;
                            }
                        }
                        return i10;
                    } else {
                        throw zzadn.zzi();
                    }
                }
                break;
            case 30:
            case 44:
                if (i15 == 2) {
                    i11 = zzabq.zzf(bArr2, i12, zzadk, zzabp2);
                } else if (i15 == 0) {
                    i11 = zzabq.zzl(i3, bArr, i, i2, zzadk, zzabp);
                }
                zzaey.zzC(obj, i4, zzadk, zzE(i16), (Object) null, this.zzo);
                return i11;
            case 33:
            case 47:
                if (i15 == 2) {
                    zzadg zzadg3 = (zzadg) zzadk;
                    int zzj21 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i34 = zzabp2.zza + zzj21;
                    while (zzj21 < i34) {
                        zzj21 = zzabq.zzj(bArr2, zzj21, zzabp2);
                        zzadg3.zzf(zzacg.zzs(zzabp2.zza));
                    }
                    if (zzj21 == i34) {
                        return zzj21;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 0) {
                    zzadg zzadg4 = (zzadg) zzadk;
                    int zzj22 = zzabq.zzj(bArr2, i12, zzabp2);
                    zzadg4.zzf(zzacg.zzs(zzabp2.zza));
                    while (zzj22 < i13) {
                        int zzj23 = zzabq.zzj(bArr2, zzj22, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return zzj22;
                        }
                        zzj22 = zzabq.zzj(bArr2, zzj23, zzabp2);
                        zzadg4.zzf(zzacg.zzs(zzabp2.zza));
                    }
                    return zzj22;
                }
                break;
            case 34:
            case 48:
                if (i15 == 2) {
                    zzadz zzadz5 = (zzadz) zzadk;
                    int zzj24 = zzabq.zzj(bArr2, i12, zzabp2);
                    int i35 = zzabp2.zza + zzj24;
                    while (zzj24 < i35) {
                        zzj24 = zzabq.zzm(bArr2, zzj24, zzabp2);
                        zzadz5.zzf(zzacg.zzt(zzabp2.zzb));
                    }
                    if (zzj24 == i35) {
                        return zzj24;
                    }
                    throw zzadn.zzi();
                } else if (i15 == 0) {
                    zzadz zzadz6 = (zzadz) zzadk;
                    int zzm4 = zzabq.zzm(bArr2, i12, zzabp2);
                    zzadz6.zzf(zzacg.zzt(zzabp2.zzb));
                    while (zzm4 < i13) {
                        int zzj25 = zzabq.zzj(bArr2, zzm4, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return zzm4;
                        }
                        zzm4 = zzabq.zzm(bArr2, zzj25, zzabp2);
                        zzadz6.zzf(zzacg.zzt(zzabp2.zzb));
                    }
                    return zzm4;
                }
                break;
            default:
                if (i15 == 3) {
                    zzaew zzF = zzF(i16);
                    int i36 = (i14 & -8) | 4;
                    int zzc2 = zzabq.zzc(zzF, bArr, i, i2, i36, zzabp);
                    zzadk.add(zzabp2.zzc);
                    while (zzc2 < i13) {
                        int zzj26 = zzabq.zzj(bArr2, zzc2, zzabp2);
                        if (i14 != zzabp2.zza) {
                            return zzc2;
                        }
                        zzc2 = zzabq.zzc(zzF, bArr, zzj26, i2, i36, zzabp);
                        zzadk.add(zzabp2.zzc);
                    }
                    return zzc2;
                }
                break;
        }
        return i12;
    }

    private final int zzx(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, 0);
    }

    private final int zzy(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, i2);
    }

    private final int zzz(int i) {
        return this.zzc[i + 2];
    }

    public final int zza(Object obj) {
        return this.zzj ? zzr(obj) : zzq(obj);
    }

    public final int zzb(Object obj) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzC = zzC(i2);
            int i3 = this.zzc[i2];
            long j = (long) (1048575 & zzC);
            int i4 = 37;
            switch (zzB(zzC)) {
                case 0:
                    i = (i * 53) + zzadl.zzc(Double.doubleToLongBits(zzafx.zza(obj, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzafx.zzb(obj, j));
                    break;
                case 2:
                    i = (i * 53) + zzadl.zzc(zzafx.zzd(obj, j));
                    break;
                case 3:
                    i = (i * 53) + zzadl.zzc(zzafx.zzd(obj, j));
                    break;
                case 4:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 5:
                    i = (i * 53) + zzadl.zzc(zzafx.zzd(obj, j));
                    break;
                case 6:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 7:
                    i = (i * 53) + zzadl.zza(zzafx.zzw(obj, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzafx.zzf(obj, j)).hashCode();
                    break;
                case 9:
                    Object zzf2 = zzafx.zzf(obj, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 12:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 13:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 14:
                    i = (i * 53) + zzadl.zzc(zzafx.zzd(obj, j));
                    break;
                case 15:
                    i = (i * 53) + zzafx.zzc(obj, j);
                    break;
                case 16:
                    i = (i * 53) + zzadl.zzc(zzafx.zzd(obj, j));
                    break;
                case 17:
                    Object zzf3 = zzafx.zzf(obj, j);
                    if (zzf3 != null) {
                        i4 = zzf3.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                    break;
                case 51:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(Double.doubleToLongBits(zzo(obj, j)));
                        break;
                    }
                case 52:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzp(obj, j));
                        break;
                    }
                case 53:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(zzD(obj, j));
                        break;
                    }
                case 54:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(zzD(obj, j));
                        break;
                    }
                case 55:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 56:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(zzD(obj, j));
                        break;
                    }
                case 57:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 58:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zza(zzaa(obj, j));
                        break;
                    }
                case 59:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzafx.zzf(obj, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 63:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 64:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 65:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(zzD(obj, j));
                        break;
                    }
                case 66:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzs(obj, j);
                        break;
                    }
                case 67:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzadl.zzc(zzD(obj, j));
                        break;
                    }
                case 68:
                    if (!zzZ(obj, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzafx.zzf(obj, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzo.zzd(obj).hashCode();
        if (!this.zzh) {
            return hashCode;
        }
        this.zzp.zza(obj);
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v46, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.p002firebaseauthapi.zzabp r34) throws java.io.IOException {
        /*
            r28 = this;
            r15 = r28
            r14 = r29
            r12 = r30
            r13 = r32
            r11 = r33
            r9 = r34
            zzL(r29)
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r31
            r1 = r16
            r3 = r1
            r5 = r3
            r2 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001d:
            if (r0 >= r13) goto L_0x0518
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002e
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzk(r0, r12, r1, r9)
            int r1 = r9.zza
            r4 = r1
            r1 = r0
            goto L_0x002f
        L_0x002e:
            r4 = r0
        L_0x002f:
            int r0 = r4 >>> 3
            r7 = r4 & 7
            r8 = 3
            if (r0 <= r2) goto L_0x003c
            int r3 = r3 / r8
            int r2 = r15.zzy(r0, r3)
            goto L_0x0040
        L_0x003c:
            int r2 = r15.zzx(r0)
        L_0x0040:
            r3 = -1
            if (r2 != r3) goto L_0x0052
            r31 = r0
            r2 = r1
            r18 = r3
            r17 = r4
            r25 = r5
            r27 = r10
            r19 = r16
            goto L_0x04a9
        L_0x0052:
            int[] r3 = r15.zzc
            int r19 = r2 + 1
            r8 = r3[r19]
            int r11 = zzB(r8)
            r19 = r0
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r8 & r17
            r20 = r1
            long r0 = (long) r0
            r21 = r0
            r0 = 17
            if (r11 > r0) goto L_0x0373
            int r0 = r2 + 2
            r0 = r3[r0]
            int r3 = r0 >>> 20
            r1 = 1
            int r23 = r1 << r3
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r3
            if (r0 == r6) goto L_0x0090
            if (r6 == r3) goto L_0x0084
            r17 = r4
            long r3 = (long) r6
            r10.putInt(r14, r3, r5)
            goto L_0x0086
        L_0x0084:
            r17 = r4
        L_0x0086:
            long r3 = (long) r0
            int r5 = r10.getInt(r14, r3)
            r26 = r0
            r25 = r5
            goto L_0x0096
        L_0x0090:
            r17 = r4
            r25 = r5
            r26 = r6
        L_0x0096:
            r0 = 5
            switch(r11) {
                case 0: goto L_0x033a;
                case 1: goto L_0x030e;
                case 2: goto L_0x02df;
                case 3: goto L_0x02df;
                case 4: goto L_0x02b6;
                case 5: goto L_0x0280;
                case 6: goto L_0x0256;
                case 7: goto L_0x0223;
                case 8: goto L_0x01ee;
                case 9: goto L_0x01b5;
                case 10: goto L_0x018b;
                case 11: goto L_0x02b6;
                case 12: goto L_0x013c;
                case 13: goto L_0x0256;
                case 14: goto L_0x0280;
                case 15: goto L_0x010f;
                case 16: goto L_0x00d1;
                default: goto L_0x009a;
            }
        L_0x009a:
            r13 = r2
            r11 = r19
            r6 = r20
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 3
            if (r7 != r0) goto L_0x0366
            java.lang.Object r7 = r15.zzI(r14, r13)
            com.google.android.gms.internal.firebase-auth-api.zzaew r1 = r15.zzF(r13)
            int r0 = r11 << 3
            r5 = r0 | 4
            r0 = r7
            r2 = r30
            r3 = r6
            r4 = r32
            r8 = r17
            r6 = r34
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzn(r0, r1, r2, r3, r4, r5, r6)
            r15.zzR(r14, r13, r7)
            r5 = r25 | r23
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x00d1:
            if (r7 != 0) goto L_0x0100
            r1 = r20
            int r6 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r1, r9)
            long r0 = r9.zzb
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzacg.zzt(r0)
            r11 = r19
            r7 = r21
            r0 = r10
            r1 = r29
            r13 = r2
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r7
            r8 = r17
            r0.putLong(r1, r2, r4)
            r5 = r25 | r23
            r0 = r6
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0100:
            r13 = r2
            r8 = r17
            r11 = r19
            r1 = r20
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r1
            goto L_0x0368
        L_0x010f:
            r13 = r2
            r8 = r17
            r11 = r19
            r1 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0139
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r1, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzacg.zzs(r1)
            r10.putInt(r14, r3, r1)
            r5 = r25 | r23
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0139:
            r6 = r1
            goto L_0x0368
        L_0x013c:
            r13 = r2
            r8 = r17
            r11 = r19
            r1 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0188
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r1, r9)
            int r1 = r9.zza
            com.google.android.gms.internal.firebase-auth-api.zzadj r2 = r15.zzE(r13)
            if (r2 == 0) goto L_0x0178
            boolean r2 = r2.zza()
            if (r2 == 0) goto L_0x015f
            goto L_0x0178
        L_0x015f:
            com.google.android.gms.internal.firebase-auth-api.zzafo r2 = zzd(r29)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzj(r8, r1)
            r1 = r8
            r2 = r11
            r3 = r13
            r5 = r25
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0178:
            r10.putInt(r14, r3, r1)
            r5 = r25 | r23
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0188:
            r6 = r1
            goto L_0x0368
        L_0x018b:
            r13 = r2
            r8 = r17
            r11 = r19
            r1 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 2
            if (r7 != r0) goto L_0x01b2
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zza(r12, r1, r9)
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r3, r1)
            r5 = r25 | r23
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x01b2:
            r6 = r1
            goto L_0x0368
        L_0x01b5:
            r13 = r2
            r8 = r17
            r11 = r19
            r1 = r20
            r0 = 2
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r0) goto L_0x01ea
            java.lang.Object r6 = r15.zzI(r14, r13)
            com.google.android.gms.internal.firebase-auth-api.zzaew r2 = r15.zzF(r13)
            r0 = r6
            r5 = r1
            r1 = r2
            r2 = r30
            r3 = r5
            r4 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzo(r0, r1, r2, r3, r4, r5)
            r15.zzR(r14, r13, r6)
            r5 = r25 | r23
            r1 = r8
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x01ea:
            r5 = r1
            r6 = r5
            goto L_0x0368
        L_0x01ee:
            r13 = r2
            r6 = r17
            r11 = r19
            r5 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 2
            if (r7 != r0) goto L_0x021f
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r8
            if (r0 != 0) goto L_0x0209
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzg(r12, r5, r9)
            goto L_0x020d
        L_0x0209:
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzh(r12, r5, r9)
        L_0x020d:
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r3, r1)
            r5 = r25 | r23
            r1 = r6
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x021f:
            r8 = r6
            r6 = r5
            goto L_0x0368
        L_0x0223:
            r13 = r2
            r6 = r17
            r11 = r19
            r5 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0252
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r5, r9)
            long r7 = r9.zzb
            r20 = 0
            int r2 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r2 == 0) goto L_0x0240
            goto L_0x0242
        L_0x0240:
            r1 = r16
        L_0x0242:
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzm(r14, r3, r1)
            r5 = r25 | r23
            r1 = r6
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0252:
            r8 = r6
            r6 = r5
            goto L_0x0368
        L_0x0256:
            r13 = r2
            r6 = r17
            r11 = r19
            r5 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r0) goto L_0x027c
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzb(r12, r5)
            r10.putInt(r14, r3, r0)
            int r0 = r5 + 4
            r5 = r25 | r23
            r1 = r6
            r2 = r11
            r3 = r13
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x027c:
            r8 = r6
            r6 = r5
            goto L_0x0368
        L_0x0280:
            r13 = r2
            r6 = r17
            r11 = r19
            r5 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r1) goto L_0x02af
            long r7 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzp(r12, r5)
            r0 = r10
            r1 = r29
            r2 = r3
            r17 = r6
            r6 = r5
            r4 = r7
            r0.putLong(r1, r2, r4)
            int r0 = r6 + 8
            r5 = r25 | r23
            r2 = r11
            r3 = r13
            r1 = r17
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x02af:
            r17 = r6
            r6 = r5
            r8 = r17
            goto L_0x0368
        L_0x02b6:
            r13 = r2
            r11 = r19
            r6 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x02db
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzj(r12, r6, r9)
            int r1 = r9.zza
            r10.putInt(r14, r3, r1)
            r5 = r25 | r23
            r2 = r11
            r3 = r13
            r1 = r17
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x02db:
            r8 = r17
            goto L_0x0368
        L_0x02df:
            r13 = r2
            r11 = r19
            r6 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x030a
            int r6 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzm(r12, r6, r9)
            long r7 = r9.zzb
            r0 = r10
            r1 = r29
            r2 = r3
            r4 = r7
            r0.putLong(r1, r2, r4)
            r5 = r25 | r23
            r0 = r6
            r2 = r11
            r3 = r13
            r1 = r17
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x030a:
            r8 = r17
            goto L_0x0368
        L_0x030e:
            r13 = r2
            r11 = r19
            r6 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r0) goto L_0x0337
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzb(r12, r6)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzp(r14, r3, r0)
            int r0 = r6 + 4
            r5 = r25 | r23
            r2 = r11
            r3 = r13
            r1 = r17
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0337:
            r8 = r17
            goto L_0x0368
        L_0x033a:
            r13 = r2
            r11 = r19
            r6 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r1) goto L_0x0363
            long r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzp(r12, r6)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzo(r14, r3, r0)
            int r0 = r6 + 8
            r5 = r25 | r23
            r2 = r11
            r3 = r13
            r1 = r17
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x0363:
            r8 = r17
            goto L_0x0368
        L_0x0366:
            r8 = r17
        L_0x0368:
            r2 = r6
            r17 = r8
            r27 = r10
            r31 = r11
            r19 = r13
            goto L_0x046f
        L_0x0373:
            r13 = r2
            r2 = r4
            r26 = r6
            r1 = r19
            r6 = r20
            r3 = r21
            r18 = -1
            r19 = 1048575(0xfffff, float:1.469367E-39)
            r0 = 27
            if (r11 != r0) goto L_0x03da
            r0 = 2
            if (r7 != r0) goto L_0x03cb
            java.lang.Object r0 = r10.getObject(r14, r3)
            com.google.android.gms.internal.firebase-auth-api.zzadk r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzadk) r0
            boolean r7 = r0.zzc()
            if (r7 != 0) goto L_0x03a8
            int r7 = r0.size()
            if (r7 != 0) goto L_0x039e
            r7 = 10
            goto L_0x039f
        L_0x039e:
            int r7 = r7 + r7
        L_0x039f:
            com.google.android.gms.internal.firebase-auth-api.zzadk r0 = r0.zzd(r7)
            r10.putObject(r14, r3, r0)
            r7 = r0
            goto L_0x03a9
        L_0x03a8:
            r7 = r0
        L_0x03a9:
            com.google.android.gms.internal.firebase-auth-api.zzaew r0 = r15.zzF(r13)
            r8 = r1
            r1 = r2
            r11 = r2
            r2 = r30
            r3 = r6
            r4 = r32
            r25 = r5
            r5 = r7
            r6 = r34
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zze(r0, r1, r2, r3, r4, r5, r6)
            r2 = r8
            r1 = r11
            r3 = r13
            r5 = r25
            r6 = r26
            r13 = r32
            r11 = r33
            goto L_0x001d
        L_0x03cb:
            r8 = r1
            r11 = r2
            r25 = r5
            r15 = r6
            r31 = r8
            r27 = r10
            r17 = r11
            r19 = r13
            goto L_0x046e
        L_0x03da:
            r25 = r5
            r5 = r1
            r0 = 49
            if (r11 > r0) goto L_0x0428
            long r0 = (long) r8
            r20 = r0
            r0 = r28
            r1 = r29
            r8 = r2
            r2 = r30
            r23 = r3
            r3 = r6
            r4 = r32
            r31 = r5
            r5 = r8
            r15 = r6
            r6 = r31
            r17 = r8
            r8 = r13
            r27 = r10
            r9 = r20
            r19 = r13
            r12 = r23
            r14 = r34
            int r0 = r0.zzw(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x0423
            r15 = r28
            r14 = r29
            r12 = r30
            r2 = r31
            r13 = r32
            r11 = r33
            r9 = r34
            r1 = r17
            r3 = r19
            r5 = r25
            r6 = r26
            r10 = r27
            goto L_0x001d
        L_0x0423:
            r2 = r0
            r6 = r26
            goto L_0x04a9
        L_0x0428:
            r17 = r2
            r23 = r3
            r31 = r5
            r15 = r6
            r27 = r10
            r19 = r13
            r0 = 50
            if (r11 != r0) goto L_0x0472
            r0 = 2
            if (r7 != r0) goto L_0x046d
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r15
            r4 = r32
            r5 = r19
            r6 = r23
            r8 = r34
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x0469
            r15 = r28
            r14 = r29
            r12 = r30
            r2 = r31
            r13 = r32
            r11 = r33
            r9 = r34
            r1 = r17
            r3 = r19
            r5 = r25
            r6 = r26
            r10 = r27
            goto L_0x001d
        L_0x0469:
            r2 = r0
            r6 = r26
            goto L_0x04a9
        L_0x046d:
        L_0x046e:
            r2 = r15
        L_0x046f:
            r6 = r26
            goto L_0x04a9
        L_0x0472:
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r15
            r4 = r32
            r5 = r17
            r6 = r31
            r9 = r11
            r10 = r23
            r12 = r19
            r13 = r34
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x04a6
            r15 = r28
            r14 = r29
            r12 = r30
            r2 = r31
            r13 = r32
            r11 = r33
            r9 = r34
            r1 = r17
            r3 = r19
            r5 = r25
            r6 = r26
            r10 = r27
            goto L_0x001d
        L_0x04a6:
            r2 = r0
            r6 = r26
        L_0x04a9:
            r7 = r33
            r8 = r17
            if (r8 != r7) goto L_0x04bb
            if (r7 == 0) goto L_0x04bb
            r9 = r28
            r12 = r29
            r0 = r6
            r5 = r25
            r6 = r2
            goto L_0x0525
        L_0x04bb:
            r9 = r28
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x04f1
            r10 = r34
            com.google.android.gms.internal.firebase-auth-api.zzacs r0 = r10.zzd
            com.google.android.gms.internal.firebase-auth-api.zzacs r1 = com.google.android.gms.internal.p002firebaseauthapi.zzacs.zza
            if (r0 == r1) goto L_0x04ec
            com.google.android.gms.internal.firebase-auth-api.zzaek r1 = r9.zzg
            r11 = r31
            com.google.android.gms.internal.firebase-auth-api.zzadd r0 = r0.zzb(r1, r11)
            if (r0 != 0) goto L_0x04e5
            com.google.android.gms.internal.firebase-auth-api.zzafo r4 = zzd(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzi(r0, r1, r2, r3, r4, r5)
            r12 = r29
            goto L_0x0506
        L_0x04e5:
            r12 = r29
            r0 = r12
            com.google.android.gms.internal.firebase-auth-api.zzadc r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzadc) r0
            r0 = 0
            throw r0
        L_0x04ec:
            r12 = r29
            r11 = r31
            goto L_0x04f7
        L_0x04f1:
            r12 = r29
            r11 = r31
            r10 = r34
        L_0x04f7:
            com.google.android.gms.internal.firebase-auth-api.zzafo r4 = zzd(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzabq.zzi(r0, r1, r2, r3, r4, r5)
        L_0x0506:
            r13 = r32
            r1 = r8
            r15 = r9
            r9 = r10
            r2 = r11
            r14 = r12
            r3 = r19
            r5 = r25
            r10 = r27
            r12 = r30
            r11 = r7
            goto L_0x001d
        L_0x0518:
            r25 = r5
            r26 = r6
            r27 = r10
            r7 = r11
            r12 = r14
            r9 = r15
            r6 = r0
            r8 = r1
            r0 = r26
        L_0x0525:
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 == r1) goto L_0x0530
            long r0 = (long) r0
            r2 = r27
            r2.putInt(r12, r0, r5)
        L_0x0530:
            int r0 = r9.zzl
            r10 = r0
        L_0x0533:
            int r0 = r9.zzm
            if (r10 >= r0) goto L_0x054a
            int[] r0 = r9.zzk
            r2 = r0[r10]
            r3 = 0
            com.google.android.gms.internal.firebase-auth-api.zzafn r4 = r9.zzo
            r0 = r28
            r1 = r29
            r5 = r29
            r0.zzG(r1, r2, r3, r4, r5)
            int r10 = r10 + 1
            goto L_0x0533
        L_0x054a:
            if (r7 != 0) goto L_0x0556
            r0 = r32
            if (r6 != r0) goto L_0x0551
            goto L_0x055c
        L_0x0551:
            com.google.android.gms.internal.firebase-auth-api.zzadn r0 = com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzg()
            throw r0
        L_0x0556:
            r0 = r32
            if (r6 > r0) goto L_0x055d
            if (r8 != r7) goto L_0x055d
        L_0x055c:
            return r6
        L_0x055d:
            com.google.android.gms.internal.firebase-auth-api.zzadn r0 = com.google.android.gms.internal.p002firebaseauthapi.zzadn.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzabp):int");
    }

    public final Object zze() {
        return ((zzadf) this.zzg).zzw();
    }

    public final void zzf(Object obj) {
        if (zzY(obj)) {
            if (obj instanceof zzadf) {
                zzadf zzadf = (zzadf) obj;
                zzadf.zzH(Integer.MAX_VALUE);
                zzadf.zza = 0;
                zzadf.zzF();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                long j = (long) (1048575 & zzC);
                switch (zzB(zzC)) {
                    case 9:
                    case 17:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzF(i).zzf(zzb.getObject(obj, j));
                            break;
                        }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzn.zzb(obj, j);
                        break;
                    case 50:
                        Unsafe unsafe = zzb;
                        Object object = unsafe.getObject(obj, j);
                        if (object == null) {
                            break;
                        } else {
                            ((zzaee) object).zzc();
                            unsafe.putObject(obj, j, object);
                            break;
                        }
                }
            }
            this.zzo.zzm(obj);
            if (this.zzh) {
                this.zzp.zze(obj);
            }
        }
    }

    public final void zzg(Object obj, Object obj2) {
        zzL(obj);
        if (obj2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzC = zzC(i);
                long j = (long) (1048575 & zzC);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzo(obj, j, zzafx.zza(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 1:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzp(obj, j, zzafx.zzb(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 2:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 3:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 4:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 5:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 6:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 7:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzm(obj, j, zzafx.zzw(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 8:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 9:
                        zzM(obj, obj2, i);
                        break;
                    case 10:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 11:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 12:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 13:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 14:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 15:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 16:
                        if (!zzV(obj2, i)) {
                            break;
                        } else {
                            zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                            zzP(obj, i);
                            break;
                        }
                    case 17:
                        zzM(obj, obj2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzn.zzc(obj, obj2, j);
                        break;
                    case 50:
                        zzaey.zzI(this.zzr, obj, obj2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zzZ(obj2, i2, i)) {
                            break;
                        } else {
                            zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                            zzQ(obj, i2, i);
                            break;
                        }
                    case 60:
                        zzN(obj, obj2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzZ(obj2, i2, i)) {
                            break;
                        } else {
                            zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                            zzQ(obj, i2, i);
                            break;
                        }
                    case 68:
                        zzN(obj, obj2, i);
                        break;
                }
            }
            zzaey.zzF(this.zzo, obj, obj2);
            if (this.zzh) {
                zzaey.zzE(this.zzp, obj, obj2);
                return;
            }
            return;
        }
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v12, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v13, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v16, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v18, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v19, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaev} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: com.google.android.gms.internal.firebase-auth-api.zzacs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v20, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v21, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v22, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v23, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v25, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v26, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v27, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v28, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v29, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v30, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v31, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v32, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v33, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v34, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v35, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v36, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v37, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v38, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v39, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v40, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v41, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v43, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v44, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v46, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v47, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v48, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v49, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v50, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v51, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v52, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v53, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v54, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v55, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v56, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v57, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v58, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v59, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v60, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v61, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v62, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v63, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v64, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v65, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v66, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v67, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v68, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v69, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v70, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v71, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v72, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v73, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v74, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v75, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v76, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v77, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v78, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v79, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v103, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaew} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v80, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v81, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v82, resolved type: com.google.android.gms.internal.firebase-auth-api.zzact} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v83, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v85, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v113, resolved type: com.google.android.gms.internal.firebase-auth-api.zzacs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v105, resolved type: com.google.android.gms.internal.firebase-auth-api.zzacs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v116, resolved type: com.google.android.gms.internal.firebase-auth-api.zzacs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v108, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaev} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v42, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v43, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v45, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v46, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v47, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v48, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v49, resolved type: com.google.android.gms.internal.firebase-auth-api.zzafn} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x072e, code lost:
        r14 = r9;
        r5 = r11;
        r6 = r13;
        r4 = r15;
        r15 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0016, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0747 A[Catch:{ all -> 0x077c }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0772  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0791 A[LOOP:5: B:226:0x078d->B:228:0x0791, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x07a5  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0752 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(java.lang.Object r18, com.google.android.gms.internal.p002firebaseauthapi.zzaev r19, com.google.android.gms.internal.p002firebaseauthapi.zzacs r20) throws java.io.IOException {
        /*
            r17 = this;
            r7 = r17
            r15 = r18
            r6 = r19
            r5 = r20
            r16 = 0
            if (r5 == 0) goto L_0x07a9
            zzL(r18)
            com.google.android.gms.internal.firebase-auth-api.zzafn r14 = r7.zzo
            com.google.android.gms.internal.firebase-auth-api.zzact r4 = r7.zzp
            r3 = r16
            r8 = r3
        L_0x0016:
            int r2 = r19.zzc()     // Catch:{ all -> 0x0782 }
            int r1 = r7.zzx(r2)     // Catch:{ all -> 0x0782 }
            if (r1 >= 0) goto L_0x00d1
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r1) goto L_0x0045
            int r1 = r7.zzl
            r8 = r1
            r4 = r3
        L_0x0029:
            int r1 = r7.zzm
            if (r8 >= r1) goto L_0x003f
            int[] r1 = r7.zzk
            r3 = r1[r8]
            r1 = r17
            r2 = r18
            r5 = r14
            r6 = r18
            java.lang.Object r4 = r1.zzG(r2, r3, r4, r5, r6)
            int r8 = r8 + 1
            goto L_0x0029
        L_0x003f:
            if (r4 == 0) goto L_0x0771
            r14.zzn(r15, r4)
            return
        L_0x0045:
            boolean r1 = r7.zzh     // Catch:{ all -> 0x00ca }
            if (r1 != 0) goto L_0x004c
            r11 = r16
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.firebase-auth-api.zzaek r1 = r7.zzg     // Catch:{ all -> 0x00ca }
            java.lang.Object r1 = r4.zzc(r5, r1, r2)     // Catch:{ all -> 0x00ca }
            r11 = r1
        L_0x0053:
            if (r11 == 0) goto L_0x0081
            if (r8 != 0) goto L_0x0063
            com.google.android.gms.internal.firebase-auth-api.zzacx r8 = r4.zzb(r15)     // Catch:{ all -> 0x005d }
            r1 = r8
            goto L_0x0064
        L_0x005d:
            r0 = move-exception
            r1 = r0
            r9 = r14
            r10 = r15
            goto L_0x00ce
        L_0x0063:
            r1 = r8
        L_0x0064:
            r8 = r4
            r9 = r18
            r10 = r19
            r12 = r20
            r13 = r1
            r2 = r14
            r14 = r3
            r5 = r15
            r15 = r2
            java.lang.Object r3 = r8.zzd(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x007a }
            r8 = r1
            r14 = r2
            r15 = r5
            r5 = r20
            goto L_0x0016
        L_0x007a:
            r0 = move-exception
            r1 = r0
            r9 = r2
            r14 = r3
            r10 = r5
            goto L_0x0787
        L_0x0081:
            r2 = r14
            r5 = r15
            r2.zzq(r6)     // Catch:{ all -> 0x00c6 }
            if (r3 != 0) goto L_0x008c
            java.lang.Object r3 = r2.zzc(r5)     // Catch:{ all -> 0x007a }
        L_0x008c:
            boolean r1 = r2.zzp(r3, r6)     // Catch:{ all -> 0x00c0 }
            if (r1 != 0) goto L_0x00b8
            int r1 = r7.zzl
            r8 = r1
            r4 = r3
        L_0x0096:
            int r1 = r7.zzm
            if (r8 >= r1) goto L_0x00b0
            int[] r1 = r7.zzk
            r3 = r1[r8]
            r1 = r17
            r9 = r2
            r2 = r18
            r10 = r5
            r5 = r9
            r6 = r18
            java.lang.Object r4 = r1.zzG(r2, r3, r4, r5, r6)
            int r8 = r8 + 1
            r2 = r9
            r5 = r10
            goto L_0x0096
        L_0x00b0:
            r9 = r2
            r10 = r5
            if (r4 == 0) goto L_0x0771
            r9.zzn(r10, r4)
            return
        L_0x00b8:
            r9 = r2
            r10 = r5
            r5 = r20
            r14 = r9
            r15 = r10
            goto L_0x0016
        L_0x00c0:
            r0 = move-exception
            r9 = r2
            r10 = r5
            r8 = r0
            goto L_0x0789
        L_0x00c6:
            r0 = move-exception
            r9 = r2
            r10 = r5
            goto L_0x00cd
        L_0x00ca:
            r0 = move-exception
            r9 = r14
            r10 = r15
        L_0x00cd:
            r1 = r0
        L_0x00ce:
            r14 = r3
            goto L_0x0787
        L_0x00d1:
            r9 = r14
            r10 = r15
            int r5 = r7.zzC(r1)     // Catch:{ all -> 0x0780 }
            int r11 = zzB(r5)     // Catch:{ zzadm -> 0x073b }
            r12 = 1048575(0xfffff, float:1.469367E-39)
            switch(r11) {
                case 0: goto L_0x06f2;
                case 1: goto L_0x06df;
                case 2: goto L_0x06cc;
                case 3: goto L_0x06b9;
                case 4: goto L_0x06a6;
                case 5: goto L_0x0692;
                case 6: goto L_0x067e;
                case 7: goto L_0x066a;
                case 8: goto L_0x0657;
                case 9: goto L_0x0640;
                case 10: goto L_0x062c;
                case 11: goto L_0x0618;
                case 12: goto L_0x05f1;
                case 13: goto L_0x05dd;
                case 14: goto L_0x05c9;
                case 15: goto L_0x05b5;
                case 16: goto L_0x05a1;
                case 17: goto L_0x058a;
                case 18: goto L_0x0577;
                case 19: goto L_0x0564;
                case 20: goto L_0x0551;
                case 21: goto L_0x053e;
                case 22: goto L_0x052b;
                case 23: goto L_0x0518;
                case 24: goto L_0x0505;
                case 25: goto L_0x04f2;
                case 26: goto L_0x04c3;
                case 27: goto L_0x04ac;
                case 28: goto L_0x0499;
                case 29: goto L_0x0486;
                case 30: goto L_0x0467;
                case 31: goto L_0x0454;
                case 32: goto L_0x0441;
                case 33: goto L_0x042e;
                case 34: goto L_0x041b;
                case 35: goto L_0x0408;
                case 36: goto L_0x03f5;
                case 37: goto L_0x03e2;
                case 38: goto L_0x03cf;
                case 39: goto L_0x03bc;
                case 40: goto L_0x03a9;
                case 41: goto L_0x0396;
                case 42: goto L_0x0383;
                case 43: goto L_0x0370;
                case 44: goto L_0x0349;
                case 45: goto L_0x0332;
                case 46: goto L_0x031e;
                case 47: goto L_0x030a;
                case 48: goto L_0x02f6;
                case 49: goto L_0x02de;
                case 50: goto L_0x02a6;
                case 51: goto L_0x028e;
                case 52: goto L_0x0276;
                case 53: goto L_0x025e;
                case 54: goto L_0x0246;
                case 55: goto L_0x022e;
                case 56: goto L_0x0216;
                case 57: goto L_0x01fe;
                case 58: goto L_0x01e6;
                case 59: goto L_0x01d8;
                case 60: goto L_0x01c0;
                case 61: goto L_0x01ac;
                case 62: goto L_0x0194;
                case 63: goto L_0x0166;
                case 64: goto L_0x014e;
                case 65: goto L_0x0136;
                case 66: goto L_0x011e;
                case 67: goto L_0x0106;
                case 68: goto L_0x00ee;
                default: goto L_0x00e1;
            }
        L_0x00e1:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            if (r14 != 0) goto L_0x0706
            java.lang.Object r3 = r9.zzc(r10)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0707
        L_0x00ee:
            java.lang.Object r5 = r7.zzJ(r10, r2, r1)     // Catch:{ all -> 0x0346 }
            com.google.android.gms.internal.firebase-auth-api.zzaek r5 = (com.google.android.gms.internal.p002firebaseauthapi.zzaek) r5     // Catch:{ all -> 0x0346 }
            com.google.android.gms.internal.firebase-auth-api.zzaew r11 = r7.zzF(r1)     // Catch:{ all -> 0x0346 }
            r13 = r20
            r6.zzt(r5, r11, r13)     // Catch:{ zzadm -> 0x036a }
            r7.zzS(r10, r2, r1, r5)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0106:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            long r14 = r19.zzn()     // Catch:{ zzadm -> 0x036a }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x011e:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            int r5 = r19.zzi()     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0136:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            long r14 = r19.zzm()     // Catch:{ zzadm -> 0x036a }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x014e:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            int r5 = r19.zzh()     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0166:
            r13 = r20
            int r11 = r19.zze()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzadj r14 = r7.zzE(r1)     // Catch:{ zzadm -> 0x036a }
            if (r14 == 0) goto L_0x0182
            boolean r14 = r14.zza()     // Catch:{ zzadm -> 0x036a }
            if (r14 == 0) goto L_0x0179
            goto L_0x0182
        L_0x0179:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzaey.zzD(r10, r2, r11, r3, r9)     // Catch:{ zzadm -> 0x036a }
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x072e
        L_0x0182:
            r5 = r5 & r12
            long r14 = (long) r5     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r11)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r14, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0194:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            int r5 = r19.zzj()     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x01ac:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzacc r5 = r19.zzp()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x01c0:
            r13 = r20
            java.lang.Object r5 = r7.zzJ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaek r5 = (com.google.android.gms.internal.p002firebaseauthapi.zzaek) r5     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaew r11 = r7.zzF(r1)     // Catch:{ zzadm -> 0x036a }
            r6.zzu(r5, r11, r13)     // Catch:{ zzadm -> 0x036a }
            r7.zzS(r10, r2, r1, r5)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x01d8:
            r13 = r20
            r7.zzO(r10, r5, r6)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x01e6:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            boolean r5 = r19.zzN()     // Catch:{ zzadm -> 0x036a }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x01fe:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            int r5 = r19.zzf()     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0216:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            long r14 = r19.zzk()     // Catch:{ zzadm -> 0x036a }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x022e:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            int r5 = r19.zzg()     // Catch:{ zzadm -> 0x036a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0246:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            long r14 = r19.zzo()     // Catch:{ zzadm -> 0x036a }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x025e:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            long r14 = r19.zzl()     // Catch:{ zzadm -> 0x036a }
            java.lang.Long r5 = java.lang.Long.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0276:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            float r5 = r19.zzb()     // Catch:{ zzadm -> 0x036a }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x028e:
            r13 = r20
            r5 = r5 & r12
            long r11 = (long) r5     // Catch:{ zzadm -> 0x036a }
            double r14 = r19.zza()     // Catch:{ zzadm -> 0x036a }
            java.lang.Double r5 = java.lang.Double.valueOf(r14)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r7.zzQ(r10, r2, r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x02a6:
            r13 = r20
            java.lang.Object r2 = r7.zzH(r1)     // Catch:{ zzadm -> 0x036a }
            int r1 = r7.zzC(r1)     // Catch:{ zzadm -> 0x036a }
            r1 = r1 & r12
            long r11 = (long) r1     // Catch:{ zzadm -> 0x036a }
            java.lang.Object r1 = com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzf(r10, r11)     // Catch:{ zzadm -> 0x036a }
            if (r1 == 0) goto L_0x02ce
            boolean r5 = com.google.android.gms.internal.p002firebaseauthapi.zzaef.zzb(r1)     // Catch:{ zzadm -> 0x036a }
            if (r5 == 0) goto L_0x02d9
            com.google.android.gms.internal.firebase-auth-api.zzaee r5 = com.google.android.gms.internal.p002firebaseauthapi.zzaee.zza()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaee r5 = r5.zzb()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzaef.zzc(r5, r1)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r5)     // Catch:{ zzadm -> 0x036a }
            r1 = r5
            goto L_0x02d9
        L_0x02ce:
            com.google.android.gms.internal.firebase-auth-api.zzaee r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaee.zza()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaee r1 = r1.zzb()     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r11, r1)     // Catch:{ zzadm -> 0x036a }
        L_0x02d9:
            com.google.android.gms.internal.firebase-auth-api.zzaee r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzaee) r1     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaed r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaed) r2     // Catch:{ zzadm -> 0x036a }
            throw r16     // Catch:{ zzadm -> 0x036a }
        L_0x02de:
            r13 = r20
            r2 = r5 & r12
            long r11 = (long) r2     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzaew r1 = r7.zzF(r1)     // Catch:{ zzadm -> 0x036a }
            com.google.android.gms.internal.firebase-auth-api.zzady r2 = r7.zzn     // Catch:{ zzadm -> 0x036a }
            java.util.List r2 = r2.zza(r10, r11)     // Catch:{ zzadm -> 0x036a }
            r6.zzC(r2, r1, r13)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x02f6:
            r13 = r20
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x036a }
            r2 = r5 & r12
            long r11 = (long) r2     // Catch:{ zzadm -> 0x036a }
            java.util.List r1 = r1.zza(r10, r11)     // Catch:{ zzadm -> 0x036a }
            r6.zzJ(r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x030a:
            r13 = r20
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x036a }
            r2 = r5 & r12
            long r11 = (long) r2     // Catch:{ zzadm -> 0x036a }
            java.util.List r1 = r1.zza(r10, r11)     // Catch:{ zzadm -> 0x036a }
            r6.zzI(r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x031e:
            r13 = r20
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x036a }
            r2 = r5 & r12
            long r11 = (long) r2     // Catch:{ zzadm -> 0x036a }
            java.util.List r1 = r1.zza(r10, r11)     // Catch:{ zzadm -> 0x036a }
            r6.zzH(r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0332:
            r13 = r20
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x036a }
            r2 = r5 & r12
            long r11 = (long) r2     // Catch:{ zzadm -> 0x036a }
            java.util.List r1 = r1.zza(r10, r11)     // Catch:{ zzadm -> 0x036a }
            r6.zzG(r1)     // Catch:{ zzadm -> 0x036a }
            r14 = r3
            r15 = r4
            r11 = r13
            r13 = r6
            goto L_0x0704
        L_0x0346:
            r0 = move-exception
            goto L_0x00cd
        L_0x0349:
            r13 = r20
            com.google.android.gms.internal.firebase-auth-api.zzady r11 = r7.zzn     // Catch:{  }
            r5 = r5 & r12
            long r14 = (long) r5     // Catch:{  }
            java.util.List r5 = r11.zza(r10, r14)     // Catch:{  }
            r6.zzy(r5)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzadj r11 = r7.zzE(r1)     // Catch:{  }
            r1 = r18
            r14 = r3
            r3 = r5
            r15 = r4
            r4 = r11
            r11 = r13
            r5 = r14
            r13 = r6
            r6 = r9
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzaey.zzC(r1, r2, r3, r4, r5, r6)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x072e
        L_0x036a:
            r0 = move-exception
            r14 = r3
            r15 = r4
            r11 = r13
            goto L_0x0740
        L_0x0370:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzL(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0383:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzv(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0396:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzz(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x03a9:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzA(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x03bc:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzD(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x03cf:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzM(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x03e2:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzE(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x03f5:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzB(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0408:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzx(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x041b:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzJ(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x042e:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzI(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0441:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzH(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0454:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzG(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0467:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r3 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r4 = r5 & r12
            long r4 = (long) r4     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r3 = r3.zza(r10, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzy(r3)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzadj r4 = r7.zzE(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r1 = r18
            r5 = r14
            r6 = r9
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzaey.zzC(r1, r2, r3, r4, r5, r6)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x072e
        L_0x0486:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzL(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0499:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzw(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x04ac:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaew r1 = r7.zzF(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzady r4 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r2 = r4.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzF(r2, r1, r11)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x04c3:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            boolean r1 = zzU(r5)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            if (r1 == 0) goto L_0x04e0
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r13
            com.google.android.gms.internal.firebase-auth-api.zzach r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzach) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r3 = 1
            r2.zzK(r1, r3)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x04e0:
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r13
            com.google.android.gms.internal.firebase-auth-api.zzach r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzach) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r3 = 0
            r2.zzK(r1, r3)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x04f2:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzv(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0505:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzz(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0518:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzA(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x052b:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzD(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x053e:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzM(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0551:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzE(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0564:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzB(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0577:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            com.google.android.gms.internal.firebase-auth-api.zzady r1 = r7.zzn     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            java.util.List r1 = r1.zza(r10, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzx(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x058a:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            java.lang.Object r2 = r7.zzI(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzaek r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaek) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzaew r3 = r7.zzF(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzt(r2, r3, r11)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzR(r10, r1, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x05a1:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            long r4 = r19.zzn()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzr(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x05b5:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            int r4 = r19.zzi()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x05c9:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            long r4 = r19.zzm()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzr(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x05dd:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            int r4 = r19.zzh()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x05f1:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            int r3 = r19.zze()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzadj r4 = r7.zzE(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            if (r4 == 0) goto L_0x060d
            boolean r4 = r4.zza()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            if (r4 == 0) goto L_0x0607
            goto L_0x060d
        L_0x0607:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzaey.zzD(r10, r2, r3, r14, r9)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x072e
        L_0x060d:
            r2 = r5 & r12
            long r4 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r4, r3)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0618:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            int r4 = r19.zzj()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x062c:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzacc r4 = r19.zzp()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzs(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0640:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            java.lang.Object r2 = r7.zzI(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzaek r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaek) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.firebase-auth-api.zzaew r3 = r7.zzF(r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r13.zzu(r2, r3, r11)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzR(r10, r1, r2)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0657:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r7.zzO(r10, r5, r13)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0664:
            r0 = move-exception
            goto L_0x0786
        L_0x0667:
            r0 = move-exception
            goto L_0x0741
        L_0x066a:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            boolean r4 = r19.zzN()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzm(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x067e:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            int r4 = r19.zzf()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x0692:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            long r4 = r19.zzk()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzr(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x06a6:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            int r4 = r19.zzg()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzq(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x06b9:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            long r4 = r19.zzo()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzr(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x06cc:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            long r4 = r19.zzl()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzr(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x06df:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            float r4 = r19.zzb()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzp(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            goto L_0x0704
        L_0x06f2:
            r11 = r20
            r14 = r3
            r15 = r4
            r13 = r6
            r2 = r5 & r12
            long r2 = (long) r2     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            double r4 = r19.zza()     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            com.google.android.gms.internal.p002firebaseauthapi.zzafx.zzo(r10, r2, r4)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
            r7.zzP(r10, r1)     // Catch:{ zzadm -> 0x0667, all -> 0x0664 }
        L_0x0704:
            r3 = r14
            goto L_0x072e
        L_0x0706:
            r3 = r14
        L_0x0707:
            boolean r1 = r9.zzp(r3, r13)     // Catch:{ zzadm -> 0x0739, all -> 0x0735 }
            if (r1 != 0) goto L_0x072d
            int r1 = r7.zzl
            r8 = r1
            r4 = r3
        L_0x0711:
            int r1 = r7.zzm
            if (r8 >= r1) goto L_0x0727
            int[] r1 = r7.zzk
            r3 = r1[r8]
            r1 = r17
            r2 = r18
            r5 = r9
            r6 = r18
            java.lang.Object r4 = r1.zzG(r2, r3, r4, r5, r6)
            int r8 = r8 + 1
            goto L_0x0711
        L_0x0727:
            if (r4 == 0) goto L_0x0771
            r9.zzn(r10, r4)
            return
        L_0x072d:
        L_0x072e:
            r14 = r9
            r5 = r11
            r6 = r13
            r4 = r15
            r15 = r10
            goto L_0x0016
        L_0x0735:
            r0 = move-exception
            r8 = r0
            goto L_0x0789
        L_0x0739:
            r0 = move-exception
            goto L_0x0742
        L_0x073b:
            r0 = move-exception
            r11 = r20
            r14 = r3
            r15 = r4
        L_0x0740:
            r13 = r6
        L_0x0741:
            r3 = r14
        L_0x0742:
            r9.zzq(r13)     // Catch:{ all -> 0x077c }
            if (r3 != 0) goto L_0x074c
            java.lang.Object r1 = r9.zzc(r10)     // Catch:{ all -> 0x077c }
            r3 = r1
        L_0x074c:
            boolean r1 = r9.zzp(r3, r13)     // Catch:{ all -> 0x0779 }
            if (r1 != 0) goto L_0x0772
            int r1 = r7.zzl
            r8 = r1
            r4 = r3
        L_0x0756:
            int r1 = r7.zzm
            if (r8 >= r1) goto L_0x076c
            int[] r1 = r7.zzk
            r3 = r1[r8]
            r1 = r17
            r2 = r18
            r5 = r9
            r6 = r18
            java.lang.Object r4 = r1.zzG(r2, r3, r4, r5, r6)
            int r8 = r8 + 1
            goto L_0x0756
        L_0x076c:
            if (r4 == 0) goto L_0x0771
            r9.zzn(r10, r4)
        L_0x0771:
            return
        L_0x0772:
            r14 = r9
            r5 = r11
            r6 = r13
            r4 = r15
            r15 = r10
            goto L_0x0016
        L_0x0779:
            r0 = move-exception
            r8 = r0
            goto L_0x0789
        L_0x077c:
            r0 = move-exception
            r1 = r0
            r8 = r1
            goto L_0x0789
        L_0x0780:
            r0 = move-exception
            goto L_0x0785
        L_0x0782:
            r0 = move-exception
            r9 = r14
            r10 = r15
        L_0x0785:
            r14 = r3
        L_0x0786:
            r1 = r0
        L_0x0787:
            r8 = r1
            r3 = r14
        L_0x0789:
            int r1 = r7.zzl
            r11 = r1
            r4 = r3
        L_0x078d:
            int r1 = r7.zzm
            if (r11 >= r1) goto L_0x07a3
            int[] r1 = r7.zzk
            r3 = r1[r11]
            r1 = r17
            r2 = r18
            r5 = r9
            r6 = r18
            java.lang.Object r4 = r1.zzG(r2, r3, r4, r5, r6)
            int r11 = r11 + 1
            goto L_0x078d
        L_0x07a3:
            if (r4 == 0) goto L_0x07a8
            r9.zzn(r10, r4)
        L_0x07a8:
            throw r8
        L_0x07a9:
            throw r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaen.zzh(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzaev, com.google.android.gms.internal.firebase-auth-api.zzacs):void");
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabp zzabp) throws IOException {
        if (this.zzj) {
            zzv(obj, bArr, i, i2, zzabp);
        } else {
            zzc(obj, bArr, i, i2, 0, zzabp);
        }
    }

    public final boolean zzj(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzC = zzC(i);
            long j = (long) (zzC & 1048575);
            switch (zzB(zzC)) {
                case 0:
                    if (zzT(obj, obj2, i) && Double.doubleToLongBits(zzafx.zza(obj, j)) == Double.doubleToLongBits(zzafx.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzT(obj, obj2, i) && Float.floatToIntBits(zzafx.zzb(obj, j)) == Float.floatToIntBits(zzafx.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzT(obj, obj2, i) && zzafx.zzw(obj, j) == zzafx.zzw(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j));
                    break;
                case 50:
                    z = zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzz = (long) (zzz(i) & 1048575);
                    if (zzafx.zzc(obj, zzz) == zzafx.zzc(obj2, zzz) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(obj);
        this.zzp.zza(obj2);
        throw null;
    }

    public final boolean zzk(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzC = zzC(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 == i3) {
                i2 = i3;
                i = i4;
            } else if (i9 != 1048575) {
                i = zzb.getInt(obj2, (long) i9);
                i2 = i9;
            } else {
                i = i4;
                i2 = i9;
            }
            if ((268435456 & zzC) != 0 && !zzW(obj, i6, i2, i, i10)) {
                return false;
            }
            switch (zzB(zzC)) {
                case 9:
                case 17:
                    if (zzW(obj, i6, i2, i, i10) && !zzX(obj2, zzC, zzF(i6))) {
                        return false;
                    }
                case 27:
                case 49:
                    List list = (List) zzafx.zzf(obj2, (long) (zzC & 1048575));
                    if (!list.isEmpty()) {
                        zzaew zzF = zzF(i6);
                        for (int i11 = 0; i11 < list.size(); i11++) {
                            if (!zzF.zzk(list.get(i11))) {
                                return false;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                case 50:
                    if (((zzaee) zzafx.zzf(obj2, (long) (zzC & 1048575))).isEmpty()) {
                        break;
                    } else {
                        zzaed zzaed = (zzaed) zzH(i6);
                        throw null;
                    }
                case 60:
                case 68:
                    if (zzZ(obj2, i7, i6) && !zzX(obj2, zzC, zzF(i6))) {
                        return false;
                    }
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(obj2);
        throw null;
    }

    public final void zzn(Object obj, zzaco zzaco) throws IOException {
        if (!this.zzj) {
            zzab(obj, zzaco);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzf(i2, zzafx.zza(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzo(i2, zzafx.zzb(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzt(i2, zzafx.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzJ(i2, zzafx.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzr(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzm(i2, zzafx.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzk(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzb(i2, zzafx.zzw(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzad(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco);
                            break;
                        }
                    case 9:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzv(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 10:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzd(i2, (zzacc) zzafx.zzf(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzH(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzi(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzw(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzy(i2, zzafx.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzA(i2, zzafx.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzC(i2, zzafx.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzV(obj, i)) {
                            break;
                        } else {
                            zzaco.zzq(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 18:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 19:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 20:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 21:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 22:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 23:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 24:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 25:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 26:
                        zzaey.zzY(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco);
                        break;
                    case 27:
                        zzaey.zzT(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, zzF(i));
                        break;
                    case 28:
                        zzaey.zzK(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco);
                        break;
                    case 29:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 30:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 31:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 32:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 33:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 34:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, false);
                        break;
                    case 35:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 36:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 37:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 38:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 39:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 40:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 41:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 42:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 43:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 44:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 45:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 46:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 47:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 48:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, true);
                        break;
                    case 49:
                        zzaey.zzQ(i2, (List) zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco, zzF(i));
                        break;
                    case 50:
                        zzac(zzaco, i2, zzafx.zzf(obj, (long) (zzC & 1048575)), i);
                        break;
                    case 51:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzf(i2, zzo(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzo(i2, zzp(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzt(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzJ(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzr(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzm(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzk(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzb(i2, zzaa(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzad(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzaco);
                            break;
                        }
                    case 60:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzv(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 61:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzd(i2, (zzacc) zzafx.zzf(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzH(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzi(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzw(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzy(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzA(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzC(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzZ(obj, i2, i)) {
                            break;
                        } else {
                            zzaco.zzq(i2, zzafx.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                }
            }
            zzafn zzafn = this.zzo;
            zzafn.zzr(zzafn.zzd(obj), zzaco);
        } else {
            this.zzp.zza(obj);
            throw null;
        }
    }
}
