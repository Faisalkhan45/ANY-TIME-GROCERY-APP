package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzadb;
import com.google.android.gms.internal.p002firebaseauthapi.zzadf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class zzadb<MessageType extends zzadf<MessageType, BuilderType>, BuilderType extends zzadb<MessageType, BuilderType>> extends zzabl<MessageType, BuilderType> {
    protected zzadf zza;
    private final zzadf zzb;

    protected zzadb(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzK()) {
            this.zza = messagetype.zzw();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzaes.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* synthetic */ zzaek zzL() {
        throw null;
    }

    /* renamed from: zzg */
    public final zzadb zzf() {
        zzadb zzadb = (zzadb) this.zzb.zzj(5, (Object) null, (Object) null);
        zzadb.zza = zzk();
        return zzadb;
    }

    public final zzadb zzh(zzadf zzadf) {
        if (!this.zzb.equals(zzadf)) {
            if (!this.zza.zzK()) {
                zzn();
            }
            zza(this.zza, zzadf);
        }
        return this;
    }

    public final MessageType zzi() {
        MessageType zzj = zzk();
        if (zzj.zzJ()) {
            return zzj;
        }
        throw new zzafm(zzj);
    }

    /* renamed from: zzj */
    public MessageType zzk() {
        if (!this.zza.zzK()) {
            return this.zza;
        }
        this.zza.zzE();
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzm() {
        if (!this.zza.zzK()) {
            zzn();
        }
    }

    /* access modifiers changed from: protected */
    public void zzn() {
        zzadf zzw = this.zzb.zzw();
        zza(zzw, this.zza);
        this.zza = zzw;
    }
}
