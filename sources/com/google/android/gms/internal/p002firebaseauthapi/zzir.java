package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzir  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final /* synthetic */ class zzir implements zzft {
    public static final /* synthetic */ zzir zza = new zzir();

    private /* synthetic */ zzir() {
    }

    public final zzaw zza(zzha zzha, zzca zzca) {
        zzik zzik;
        zzil zzil;
        int i = zzis.zza;
        if (((zzgy) zzha).zzg().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzmt zze = zzmt.zze(((zzgy) zzha).zze(), zzacs.zza());
                if (zze.zza() == 0) {
                    zzij zzij = new zzij((zzii) null);
                    zzij.zzb(zze.zzg().zzd());
                    zzij.zzc(zze.zzf().zza());
                    int zzf = zze.zzf().zzf();
                    zzoy zzoy = zzoy.UNKNOWN_PREFIX;
                    switch (zzf - 2) {
                        case 1:
                            zzik = zzik.zza;
                            break;
                        case 2:
                            zzik = zzik.zzd;
                            break;
                        case 3:
                            zzik = zzik.zzc;
                            break;
                        case 4:
                            zzik = zzik.zze;
                            break;
                        case 5:
                            zzik = zzik.zzb;
                            break;
                        default:
                            int zza2 = zzmq.zza(zzf);
                            throw new GeneralSecurityException("Unable to parse HashType: " + zza2);
                    }
                    zzij.zza(zzik);
                    zzoy zzc = ((zzgy) zzha).zzc();
                    switch (zzc.ordinal()) {
                        case 1:
                            zzil = zzil.zza;
                            break;
                        case 2:
                            zzil = zzil.zzc;
                            break;
                        case 3:
                            zzil = zzil.zzd;
                            break;
                        case 4:
                            zzil = zzil.zzb;
                            break;
                        default:
                            int zza3 = zzc.zza();
                            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza3);
                    }
                    zzij.zzd(zzil);
                    zzin zze2 = zzij.zze();
                    zzic zzic = new zzic((zzib) null);
                    zzic.zzc(zze2);
                    zzic.zzb(zzqw.zzb(zze.zzg().zzt(), zzca));
                    zzic.zza(((zzgy) zzha).zzf());
                    return zzic.zzd();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzadn | IllegalArgumentException e) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacParameters.parseParameters");
        }
    }
}
