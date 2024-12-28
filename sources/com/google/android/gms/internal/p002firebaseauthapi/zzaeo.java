package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaeo implements zzaew {
    private final zzaek zza;
    private final zzafn zzb;
    private final boolean zzc;
    private final zzact zzd;

    private zzaeo(zzafn zzafn, zzact zzact, zzaek zzaek) {
        this.zzb = zzafn;
        this.zzc = zzact.zzh(zzaek);
        this.zzd = zzact;
        this.zza = zzaek;
    }

    static zzaeo zzc(zzafn zzafn, zzact zzact, zzaek zzaek) {
        return new zzaeo(zzafn, zzact, zzaek);
    }

    public final int zza(Object obj) {
        zzafn zzafn = this.zzb;
        int zzb2 = zzafn.zzb(zzafn.zzd(obj));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final Object zze() {
        zzaek zzaek = this.zza;
        if (zzaek instanceof zzadf) {
            return ((zzadf) zzaek).zzw();
        }
        return zzaek.zzB().zzk();
    }

    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zze(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzaey.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzaey.zzE(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, zzaev zzaev, zzacs zzacs) throws IOException {
        boolean z;
        zzafn zzafn = this.zzb;
        zzact zzact = this.zzd;
        Object zzc2 = zzafn.zzc(obj);
        zzacx zzb2 = zzact.zzb(obj);
        while (zzaev.zzc() != Integer.MAX_VALUE) {
            int zzd2 = zzaev.zzd();
            if (zzd2 != 11) {
                if ((zzd2 & 7) == 2) {
                    Object zzc3 = zzact.zzc(zzacs, this.zza, zzd2 >>> 3);
                    if (zzc3 != null) {
                        zzact.zzf(zzaev, zzc3, zzacs, zzb2);
                    } else {
                        z = zzafn.zzp(zzc2, zzaev);
                    }
                } else {
                    z = zzaev.zzO();
                }
                if (!z) {
                    break;
                }
            } else {
                int i = 0;
                Object obj2 = null;
                zzacc zzacc = null;
                while (true) {
                    try {
                        if (zzaev.zzc() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzd3 = zzaev.zzd();
                        if (zzd3 == 16) {
                            i = zzaev.zzj();
                            obj2 = zzact.zzc(zzacs, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj2 != null) {
                                zzact.zzf(zzaev, obj2, zzacs, zzb2);
                            } else {
                                zzacc = zzaev.zzp();
                            }
                        } else if (!zzaev.zzO()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzafn.zzn(obj, zzc2);
                        throw th;
                    }
                }
                if (zzaev.zzd() != 12) {
                    throw zzadn.zzb();
                } else if (zzacc != null) {
                    if (obj2 != null) {
                        zzact.zzg(zzacc, obj2, zzacs, zzb2);
                    } else {
                        zzafn.zzk(zzc2, i, zzacc);
                    }
                }
            }
        }
        zzafn.zzn(obj, zzc2);
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabp zzabp) throws IOException {
        zzadf zzadf = (zzadf) obj;
        if (zzadf.zzc == zzafo.zzc()) {
            zzadf.zzc = zzafo.zzf();
        }
        zzadc zzadc = (zzadc) obj;
        throw null;
    }

    public final boolean zzj(Object obj, Object obj2) {
        if (!this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    public final boolean zzk(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    public final void zzn(Object obj, zzaco zzaco) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
