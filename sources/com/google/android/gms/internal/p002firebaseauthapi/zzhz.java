package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final /* synthetic */ class zzhz implements zzft {
    public static final /* synthetic */ zzhz zza = new zzhz();

    private /* synthetic */ zzhz() {
    }

    public final zzaw zza(zzha zzha, zzca zzca) {
        zzht zzht;
        int i = zzia.zza;
        if (((zzgy) zzha).zzg().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzjn zzd = zzjn.zzd(((zzgy) zzha).zze(), zzacs.zza());
                if (zzd.zza() == 0) {
                    zzhs zzhs = new zzhs((zzhr) null);
                    zzhs.zza(zzd.zzf().zzd());
                    zzhs.zzb(zzd.zze().zza());
                    zzoy zzc = ((zzgy) zzha).zzc();
                    zzoy zzoy = zzoy.UNKNOWN_PREFIX;
                    switch (zzc.ordinal()) {
                        case 1:
                            zzht = zzht.zza;
                            break;
                        case 2:
                            zzht = zzht.zzc;
                            break;
                        case 3:
                            zzht = zzht.zzd;
                            break;
                        case 4:
                            zzht = zzht.zzb;
                            break;
                        default:
                            int zza2 = zzc.zza();
                            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                    }
                    zzhs.zzc(zzht);
                    zzhv zzd2 = zzhs.zzd();
                    zzhl zzhl = new zzhl((zzhk) null);
                    zzhl.zzc(zzd2);
                    zzhl.zza(zzqw.zzb(zzd.zzf().zzt(), zzca));
                    zzhl.zzb(((zzgy) zzha).zzf());
                    return zzhl.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzadn | IllegalArgumentException e) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacParameters.parseParameters");
        }
    }
}
