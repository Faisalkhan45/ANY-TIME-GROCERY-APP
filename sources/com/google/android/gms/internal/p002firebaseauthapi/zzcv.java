package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzcv extends zzgc {
    zzcv() {
        super(zzla.class, new zzct(zzap.class));
    }

    public static void zzg(boolean z) throws GeneralSecurityException {
        if (zzi()) {
            zzbz.zzn(new zzcv(), true);
        }
    }

    static /* bridge */ /* synthetic */ zzga zzh(int i, int i2) {
        zzlc zzb = zzld.zzb();
        zzb.zza(i);
        return new zzga((zzld) zzb.zzi(), i2);
    }

    private static boolean zzi() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            return false;
        }
    }

    public final zzgb zza() {
        return new zzcu(this, zzld.class);
    }

    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    public final /* synthetic */ zzaek zzc(zzacc zzacc) throws zzadn {
        return zzla.zzd(zzacc, zzacs.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaek zzaek) throws GeneralSecurityException {
        zzla zzla = (zzla) zzaek;
        zzqs.zzc(zzla.zza(), 0);
        zzqs.zzb(zzla.zze().zzd());
    }
}
