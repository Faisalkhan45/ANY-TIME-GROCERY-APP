package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
class zzaz implements zzax {
    private final zzgc zza;
    private final Class zzb;

    public zzaz(zzgc zzgc, Class cls) {
        if (zzgc.zzm().contains(cls) || Void.class.equals(cls)) {
            this.zza = zzgc;
            this.zzb = cls;
            return;
        }
        throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", new Object[]{zzgc.toString(), cls.getName()}));
    }

    private final zzay zzf() {
        return new zzay(this.zza.zza());
    }

    private final Object zzg(zzaek zzaek) throws GeneralSecurityException {
        if (!Void.class.equals(this.zzb)) {
            this.zza.zze(zzaek);
            return this.zza.zzl(zzaek, this.zzb);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    public final zzns zza(zzacc zzacc) throws GeneralSecurityException {
        try {
            zzaek zza2 = zzf().zza(zzacc);
            zznp zza3 = zzns.zza();
            zza3.zzb(this.zza.zzd());
            zza3.zzc(zza2.zzo());
            zza3.zza(this.zza.zzb());
            return (zzns) zza3.zzi();
        } catch (zzadn e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    public final zzaek zzb(zzacc zzacc) throws GeneralSecurityException {
        try {
            return zzf().zza(zzacc);
        } catch (zzadn e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zza().zzg().getName())), e);
        }
    }

    public final Object zzc(zzacc zzacc) throws GeneralSecurityException {
        try {
            return zzg(this.zza.zzc(zzacc));
        } catch (zzadn e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zzk().getName())), e);
        }
    }

    public final Object zzd(zzaek zzaek) throws GeneralSecurityException {
        String concat = "Expected proto of type ".concat(String.valueOf(this.zza.zzk().getName()));
        if (this.zza.zzk().isInstance(zzaek)) {
            return zzg(zzaek);
        }
        throw new GeneralSecurityException(concat);
    }

    public final String zze() {
        return this.zza.zzd();
    }
}
