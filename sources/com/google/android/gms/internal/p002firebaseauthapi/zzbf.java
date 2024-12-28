package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbf {
    private final zznx zza;

    private zzbf(zznx zznx) {
        this.zza = zznx;
    }

    public static zzbf zze(String str, byte[] bArr, int i) {
        zzoy zzoy;
        zznw zza2 = zznx.zza();
        zza2.zzb(str);
        zza2.zzc(zzacc.zzn(bArr));
        switch (i - 1) {
            case 0:
                zzoy = zzoy.TINK;
                break;
            case 1:
                zzoy = zzoy.LEGACY;
                break;
            case 2:
                zzoy = zzoy.RAW;
                break;
            default:
                zzoy = zzoy.CRUNCHY;
                break;
        }
        zza2.zza(zzoy);
        return new zzbf((zznx) zza2.zzi());
    }

    /* access modifiers changed from: package-private */
    public final zznx zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zza.zzf();
    }

    public final byte[] zzc() {
        return this.zza.zze().zzt();
    }

    public final int zzd() {
        zzoy zzd = this.zza.zzd();
        zzoy zzoy = zzoy.UNKNOWN_PREFIX;
        switch (zzd.ordinal()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                throw new IllegalArgumentException("Unknown output prefix type");
        }
    }
}
