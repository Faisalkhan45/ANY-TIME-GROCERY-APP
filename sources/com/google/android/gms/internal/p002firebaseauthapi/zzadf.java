package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzadb;
import com.google.android.gms.internal.p002firebaseauthapi.zzadf;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzadf<MessageType extends zzadf<MessageType, BuilderType>, BuilderType extends zzadb<MessageType, BuilderType>> extends zzabm<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzafo zzc = zzafo.zzc();
    private int zzd = -1;

    protected static zzadk zzA(zzadk zzadk) {
        int i;
        int size = zzadk.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        return zzadk.zzd(i);
    }

    static Object zzC(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzD(zzaek zzaek, String str, Object[] objArr) {
        return new zzaeu(zzaek, str, objArr);
    }

    protected static void zzG(Class cls, zzadf zzadf) {
        zzb.put(cls, zzadf);
        zzadf.zzE();
    }

    private final int zza(zzaew zzaew) {
        if (zzaew == null) {
            return zzaes.zza().zzb(getClass()).zza(this);
        }
        return zzaew.zza(this);
    }

    private static zzadf zzb(zzadf zzadf) throws zzadn {
        if (zzadf == null || zzadf.zzJ()) {
            return zzadf;
        }
        zzadn zza = new zzafm(zzadf).zza();
        zza.zzh(zzadf);
        throw zza;
    }

    private static zzadf zzc(zzadf zzadf, byte[] bArr, int i, int i2, zzacs zzacs) throws zzadn {
        zzadf zzw = zzadf.zzw();
        try {
            zzaew zzb2 = zzaes.zza().zzb(zzw.getClass());
            zzb2.zzi(zzw, bArr, 0, i2, new zzabp(zzacs));
            zzb2.zzf(zzw);
            return zzw;
        } catch (zzadn e) {
            e.zzh(zzw);
            throw e;
        } catch (zzafm e2) {
            zzadn zza = e2.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzadn) {
                throw ((zzadn) e3.getCause());
            }
            zzadn zzadn = new zzadn(e3);
            zzadn.zzh(zzw);
            throw zzadn;
        } catch (IndexOutOfBoundsException e4) {
            zzadn zzi = zzadn.zzi();
            zzi.zzh(zzw);
            throw zzi;
        }
    }

    static zzadf zzv(Class cls) {
        Map map = zzb;
        zzadf zzadf = (zzadf) map.get(cls);
        if (zzadf == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzadf = (zzadf) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzadf == null) {
            zzadf = (zzadf) ((zzadf) zzafx.zze(cls)).zzj(6, (Object) null, (Object) null);
            if (zzadf != null) {
                map.put(cls, zzadf);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzadf;
    }

    protected static zzadf zzx(zzadf zzadf, zzacc zzacc, zzacs zzacs) throws zzadn {
        zzacg zzh = zzacc.zzh();
        zzadf zzw = zzadf.zzw();
        try {
            zzaew zzb2 = zzaes.zza().zzb(zzw.getClass());
            zzb2.zzh(zzw, zzach.zzq(zzh), zzacs);
            zzb2.zzf(zzw);
            try {
                zzh.zzm(0);
                zzb(zzw);
                return zzw;
            } catch (zzadn e) {
                e.zzh(zzw);
                throw e;
            }
        } catch (zzadn e2) {
            e2.zzh(zzw);
            throw e2;
        } catch (zzafm e3) {
            zzadn zza = e3.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzadn) {
                throw ((zzadn) e4.getCause());
            }
            zzadn zzadn = new zzadn(e4);
            zzadn.zzh(zzw);
            throw zzadn;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzadn) {
                throw ((zzadn) e5.getCause());
            }
            throw e5;
        }
    }

    protected static zzadf zzy(zzadf zzadf, byte[] bArr, zzacs zzacs) throws zzadn {
        zzadf zzc2 = zzc(zzadf, bArr, 0, bArr.length, zzacs);
        zzb(zzc2);
        return zzc2;
    }

    protected static zzadk zzz() {
        return zzaet.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaes.zza().zzb(getClass()).zzj(this, (zzadf) obj);
        }
        return false;
    }

    public final int hashCode() {
        if (zzK()) {
            return zzr();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzr = zzr();
        this.zza = zzr;
        return zzr;
    }

    public final String toString() {
        return zzaem.zza(this, super.toString());
    }

    public final /* synthetic */ zzaej zzB() {
        return (zzadb) zzj(5, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzE() {
        zzaes.zza().zzb(getClass()).zzf(this);
        zzF();
    }

    /* access modifiers changed from: package-private */
    public final void zzF() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzH(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final void zzI(zzacn zzacn) throws IOException {
        zzaes.zza().zzb(getClass()).zzn(this, zzaco.zza(zzacn));
    }

    public final boolean zzJ() {
        zzadf zzadf;
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zzj(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzaes.zza().zzb(getClass()).zzk(this);
        if (!booleanValue) {
            return zzk;
        }
        if (true != zzk) {
            zzadf = null;
        } else {
            zzadf = this;
        }
        zzj(2, zzadf, (Object) null);
        return zzk;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzK() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final /* synthetic */ zzaek zzL() {
        return (zzadf) zzj(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public abstract Object zzj(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final int zzr() {
        return zzaes.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: protected */
    public final zzadb zzt() {
        return (zzadb) zzj(5, (Object) null, (Object) null);
    }

    public final zzadb zzu() {
        zzadb zzadb = (zzadb) zzj(5, (Object) null, (Object) null);
        zzadb.zzh(this);
        return zzadb;
    }

    /* access modifiers changed from: package-private */
    public final zzadf zzw() {
        return (zzadf) zzj(4, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final int zzn(zzaew zzaew) {
        if (zzK()) {
            int zza = zza(zzaew);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zza(zzaew);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    public final int zzs() {
        int i;
        if (zzK()) {
            i = zza((zzaew) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zza((zzaew) null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
