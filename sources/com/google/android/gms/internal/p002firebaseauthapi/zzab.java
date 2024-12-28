package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzab  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzab extends zzad {
    final /* synthetic */ zzp zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzac zzac, zzaf zzaf, CharSequence charSequence, zzp zzp) {
        super(zzaf, charSequence);
        this.zza = zzp;
    }

    public final int zzc(int i) {
        return ((zzs) this.zza).zza.end();
    }

    public final int zzd(int i) {
        if (((zzs) this.zza).zza.find(i)) {
            return ((zzs) this.zza).zza.start();
        }
        return -1;
    }
}
