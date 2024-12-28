package com.google.android.gms.internal.p002firebaseauthapi;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzen  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzen {
    public static final zznx zza;
    public static final zznx zzb;
    public static final zznx zzc;
    private static final byte[] zzd;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        byte[] bArr2 = bArr;
        zza = zza(4, 5, 3, zzcd.zza, zzoy.TINK, bArr2);
        zzb = zza(4, 5, 4, zzcd.zza, zzoy.RAW, bArr2);
        zzc = zza(4, 5, 3, zzcd.zze, zzoy.TINK, bArr2);
    }

    public static zznx zza(int i, int i2, int i3, zznx zznx, zzoy zzoy, byte[] bArr) {
        zzlw zza2 = zzlx.zza();
        zzmi zza3 = zzmj.zza();
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzacc.zzn(bArr));
        zzlt zza4 = zzlu.zza();
        zza4.zza(zznx);
        zzlz zzb2 = zzma.zzb();
        zzb2.zzb((zzmj) zza3.zzi());
        zzb2.zza((zzlu) zza4.zzi());
        zzb2.zzc(i3);
        zza2.zza((zzma) zzb2.zzi());
        zznw zza5 = zznx.zza();
        new zzef();
        zza5.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza5.zza(zzoy);
        zza5.zzc(((zzlx) zza2.zzi()).zzo());
        return (zznx) zza5.zzi();
    }
}
