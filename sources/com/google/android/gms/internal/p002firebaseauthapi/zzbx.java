package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzbx extends zzaz implements zzbw {
    private final zzgx zza;
    private final zzgc zzb;

    public zzbx(zzgx zzgx, zzgc zzgc, Class cls) {
        super(zzgx, cls);
        this.zza = zzgx;
        this.zzb = zzgc;
    }

    public final zzns zzf(zzacc zzacc) throws GeneralSecurityException {
        try {
            zzaek zzc = this.zza.zzc(zzacc);
            this.zza.zze(zzc);
            zzaek zzg = this.zza.zzg(zzc);
            this.zzb.zze(zzg);
            zznp zza2 = zzns.zza();
            zza2.zzb(this.zzb.zzd());
            zza2.zzc(zzg.zzo());
            zza2.zza(this.zzb.zzb());
            return (zzns) zza2.zzi();
        } catch (zzadn e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
