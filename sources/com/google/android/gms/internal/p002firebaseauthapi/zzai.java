package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzai  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzai extends AbstractCollection implements Serializable {
    private static final Object[] zza = new Object[0];

    zzai() {
    }

    @Deprecated
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(zza);
    }

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public int zzb() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public int zzc() {
        throw null;
    }

    /* renamed from: zzd */
    public abstract zzan iterator();

    /* access modifiers changed from: package-private */
    @CheckForNull
    public Object[] zze() {
        throw null;
    }

    public final Object[] toArray(Object[] objArr) {
        if (objArr != null) {
            int size = size();
            int length = objArr.length;
            if (length < size) {
                Object[] zze = zze();
                if (zze != null) {
                    return Arrays.copyOfRange(zze, zzc(), zzb(), objArr.getClass());
                }
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            } else if (length > size) {
                objArr[size] = null;
            }
            zza(objArr, 0);
            return objArr;
        }
        throw null;
    }
}
