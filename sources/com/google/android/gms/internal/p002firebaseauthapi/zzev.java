package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzev implements zzau {
    private static final byte[] zza = new byte[0];
    private final zzey zzb;
    private final zzex zzc;
    private final zzet zzd;
    private final int zze;
    private final zzes zzf;

    private zzev(zzey zzey, zzex zzex, zzes zzes, zzet zzet, int i, byte[] bArr) {
        this.zzb = zzey;
        this.zzc = zzex;
        this.zzf = zzes;
        this.zzd = zzet;
        this.zze = i;
    }

    static zzev zzb(zznk zznk) throws GeneralSecurityException {
        int i;
        zzfh zzfh;
        int i2;
        if (!zznk.zzk()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        } else if (!zznk.zze().zzl()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        } else if (!zznk.zzf().zzs()) {
            zznh zzb2 = zznk.zze().zzb();
            zzex zzb3 = zzez.zzb(zzb2);
            zzes zzc2 = zzez.zzc(zzb2);
            zzet zza2 = zzez.zza(zzb2);
            int zzf2 = zzb2.zzf();
            switch (zzf2 - 2) {
                case 1:
                    i = 32;
                    break;
                case 2:
                    i = 65;
                    break;
                case 3:
                    i2 = 97;
                    break;
                case 4:
                    i2 = 133;
                    break;
                default:
                    throw new IllegalArgumentException("Unable to determine KEM-encoding length for ".concat(zznb.zza(zzf2)));
            }
            i = i2;
            switch (zznk.zze().zzb().zzf() - 2) {
                case 1:
                    zzfh = zzfj.zzc(zznk.zzf().zzt());
                    break;
                case 2:
                case 3:
                case 4:
                    zzfh = zzfh.zzc(zznk.zzf().zzt(), zznk.zze().zzg().zzt(), zzff.zzg(zznk.zze().zzb().zzf()));
                    break;
                default:
                    throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
            }
            return new zzev(zzfh, zzb3, zzc2, zza2, i, (byte[]) null);
        } else {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zze;
        if (length >= i) {
            byte[] copyOf = Arrays.copyOf(bArr, i);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, this.zze, length);
            zzey zzey = this.zzb;
            zzex zzex = this.zzc;
            zzes zzes = this.zzf;
            zzet zzet = this.zzd;
            return zzeu.zzb(copyOf, zzex.zza(copyOf, zzey), zzex, zzes, zzet, new byte[0]).zza(copyOfRange, zza);
        }
        throw new GeneralSecurityException("Ciphertext is too short.");
    }
}
