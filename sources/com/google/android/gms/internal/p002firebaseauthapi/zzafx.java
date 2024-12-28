package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzafx {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = zzabo.zza();
    private static final boolean zze;
    private static final zzafw zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    static {
        boolean z;
        boolean z2;
        zzafw zzafw;
        Class<double[]> cls = double[].class;
        Class<float[]> cls2 = float[].class;
        Class<long[]> cls3 = long[].class;
        Class<int[]> cls4 = int[].class;
        Class<boolean[]> cls5 = boolean[].class;
        Unsafe zzg2 = zzg();
        zzc = zzg2;
        boolean zzv = zzv(Long.TYPE);
        zze = zzv;
        boolean zzv2 = zzv(Integer.TYPE);
        zzafw zzafw2 = null;
        if (zzg2 != null) {
            if (zzv) {
                zzafw2 = new zzafv(zzg2);
            } else if (zzv2) {
                zzafw2 = new zzafu(zzg2);
            }
        }
        zzf = zzafw2;
        boolean z3 = true;
        if (zzafw2 == null) {
            z = false;
        } else {
            try {
                Class<?> cls6 = zzafw2.zza.getClass();
                cls6.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls6.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                z = zzB() != null;
            } catch (Throwable th) {
                Logger.getLogger(zzafx.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
                z = false;
            }
        }
        zzg = z;
        zzafw zzafw3 = zzf;
        if (zzafw3 == null) {
            z2 = false;
        } else {
            try {
                Class<?> cls7 = zzafw3.zza.getClass();
                cls7.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls7.getMethod("arrayBaseOffset", new Class[]{Class.class});
                cls7.getMethod("arrayIndexScale", new Class[]{Class.class});
                cls7.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
                cls7.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
                cls7.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                cls7.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
                cls7.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
                cls7.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
                z2 = true;
            } catch (Throwable th2) {
                Logger.getLogger(zzafx.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th2.toString()));
                z2 = false;
            }
        }
        zzh = z2;
        zzz(cls5);
        zzA(cls5);
        zzz(cls4);
        zzA(cls4);
        zzz(cls3);
        zzA(cls3);
        zzz(cls2);
        zzA(cls2);
        zzz(cls);
        zzA(cls);
        zzz(Object[].class);
        zzA(Object[].class);
        Field zzB = zzB();
        if (!(zzB == null || (zzafw = zzf) == null)) {
            zzafw.zzl(zzB);
        }
        if (ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN) {
            z3 = false;
        }
        zzb = z3;
    }

    private zzafx() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zzi(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zzabo.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(Buffer.class, "address");
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzafw zzafw = zzf;
        int zzj = zzafw.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzafw.zzn(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzafw zzafw = zzf;
        int i = (((int) j) & 3) << 3;
        zzafw.zzn(obj, j2, ((255 & b) << i) | (zzafw.zzj(obj, j2) & (~(255 << i))));
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzf.zzj(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zzk(obj, j);
    }

    static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzf.zzm(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzaft());
        } catch (Throwable th) {
            return null;
        }
    }

    static void zzm(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    static void zzn(byte[] bArr, long j, byte b) {
        zzf.zzd(bArr, zza + j, b);
    }

    static void zzo(Object obj, long j, double d) {
        zzf.zze(obj, j, d);
    }

    static void zzp(Object obj, long j, float f) {
        zzf.zzf(obj, j, f);
    }

    static void zzq(Object obj, long j, int i) {
        zzf.zzn(obj, j, i);
    }

    static void zzr(Object obj, long j, long j2) {
        zzf.zzo(obj, j, j2);
    }

    static void zzs(Object obj, long j, Object obj2) {
        zzf.zzp(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzv(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzabo.zza;
        try {
            Class cls3 = zzd;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static boolean zzw(Object obj, long j) {
        return zzf.zzg(obj, j);
    }

    static boolean zzx() {
        return zzh;
    }

    static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zzh(cls);
        }
        return -1;
    }
}
