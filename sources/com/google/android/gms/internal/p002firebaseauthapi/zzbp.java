package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbp {
    private final Class zza;
    private ConcurrentMap zzb = new ConcurrentHashMap();
    private zzbq zzc;
    private zzjc zzd;

    /* synthetic */ zzbp(Class cls, zzbo zzbo) {
        this.zza = cls;
        this.zzd = zzjc.zza;
    }

    private final zzbp zze(Object obj, zzoe zzoe, boolean z) throws GeneralSecurityException {
        byte[] bArr;
        if (this.zzb == null) {
            throw new IllegalStateException("addPrimitive cannot be called after build");
        } else if (zzoe.zzk() == 3) {
            ConcurrentMap concurrentMap = this.zzb;
            Integer valueOf = Integer.valueOf(zzoe.zza());
            if (zzoe.zze() == zzoy.RAW) {
                valueOf = null;
            }
            zzaw zza2 = zzgn.zzb().zza(zzgy.zza(zzoe.zzb().zzf(), zzoe.zzb().zze(), zzoe.zzb().zzb(), zzoe.zze(), valueOf), zzca.zza());
            switch (zzoe.zze().ordinal()) {
                case 1:
                    bArr = ByteBuffer.allocate(5).put((byte) 1).putInt(zzoe.zza()).array();
                    break;
                case 2:
                case 4:
                    bArr = ByteBuffer.allocate(5).put((byte) 0).putInt(zzoe.zza()).array();
                    break;
                case 3:
                    bArr = zzas.zza;
                    break;
                default:
                    throw new GeneralSecurityException("unknown output prefix type");
            }
            zzbq zzbq = new zzbq(obj, bArr, zzoe.zzk(), zzoe.zze(), zzoe.zza(), zza2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzbq);
            zzbs zzbs = new zzbs(zzbq.zzf(), (zzbr) null);
            List list = (List) concurrentMap.put(zzbs, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzbq);
                concurrentMap.put(zzbs, Collections.unmodifiableList(arrayList2));
            }
            if (z) {
                if (this.zzc == null) {
                    this.zzc = zzbq;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        } else {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
    }

    public final zzbp zza(Object obj, zzoe zzoe) throws GeneralSecurityException {
        zze(obj, zzoe, true);
        return this;
    }

    public final zzbp zzb(Object obj, zzoe zzoe) throws GeneralSecurityException {
        zze(obj, zzoe, false);
        return this;
    }

    public final zzbp zzc(zzjc zzjc) {
        if (this.zzb != null) {
            this.zzd = zzjc;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzbu zzd() throws GeneralSecurityException {
        ConcurrentMap concurrentMap = this.zzb;
        if (concurrentMap != null) {
            zzbu zzbu = new zzbu(concurrentMap, this.zzc, this.zzd, this.zza, (zzbt) null);
            this.zzb = null;
            return zzbu;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
