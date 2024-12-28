package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcp extends zzgc {
    zzcp() {
        super(zzkl.class, new zzcn(zzap.class));
    }

    static /* bridge */ /* synthetic */ zzga zzg(int i, int i2, int i3) {
        zzkn zzb = zzko.zzb();
        zzb.zza(i);
        zzkq zzb2 = zzkr.zzb();
        zzb2.zza(16);
        zzb.zzb((zzkr) zzb2.zzi());
        return new zzga((zzko) zzb.zzi(), i3);
    }

    public final zzgb zza() {
        return new zzco(this, zzko.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzkl.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzkl zzkl = (zzkl) zzaek;
        zzqs.zzc(zzkl.zza(), 0);
        zzqs.zzb(zzkl.zzf().zzd());
        if (zzkl.zze().zza() != 12 && zzkl.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
