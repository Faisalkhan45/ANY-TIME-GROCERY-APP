package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class zzadq {
    private static final zzacs zzb = zzacs.zza;
    protected volatile zzaek zza;
    private volatile zzacc zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzadq)) {
            return false;
        }
        zzadq zzadq = (zzadq) obj;
        zzaek zzaek = this.zza;
        zzaek zzaek2 = zzadq.zza;
        if (zzaek == null && zzaek2 == null) {
            return zzb().equals(zzadq.zzb());
        }
        if (zzaek != null && zzaek2 != null) {
            return zzaek.equals(zzaek2);
        }
        if (zzaek != null) {
            zzadq.zzc(zzaek.zzL());
            return zzaek.equals(zzadq.zza);
        }
        zzc(zzaek2.zzL());
        return this.zza.equals(zzaek2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzabz) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzs();
        }
        return 0;
    }

    public final zzacc zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzacc zzacc = this.zzc;
                return zzacc;
            }
            if (this.zza == null) {
                this.zzc = zzacc.zzb;
            } else {
                this.zzc = this.zza.zzo();
            }
            zzacc zzacc2 = this.zzc;
            return zzacc2;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzaek zzaek) {
        if (this.zza == null) {
            synchronized (this) {
                if (this.zza == null) {
                    try {
                        this.zza = zzaek;
                        this.zzc = zzacc.zzb;
                    } catch (zzadn e) {
                        this.zza = zzaek;
                        this.zzc = zzacc.zzb;
                    }
                }
            }
        }
    }
}
