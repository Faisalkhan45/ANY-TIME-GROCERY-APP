package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzxb {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzvf zzb;
    private final zzyv zzc;

    zzxb(FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzvf(new zzxp(firebaseApp, zzxo.zza(), (zzym) null, (zzxj) null, (zzxk) null));
        this.zzc = new zzyv(applicationContext);
    }

    private static boolean zzG(long j, boolean z) {
        if (j > 0 && z) {
            return true;
        }
        zza.w("App hash will not be appended to the request.", new Object[0]);
        return false;
    }

    public final void zzA(zzsy zzsy, zzwz zzwz) {
        Preconditions.checkNotNull(zzsy);
        Preconditions.checkNotNull(zzwz);
        String phoneNumber = zzsy.zzb().getPhoneNumber();
        zzxa zzxa = new zzxa(zzwz, zza);
        if (this.zzc.zzl(phoneNumber)) {
            if (zzsy.zzg()) {
                this.zzc.zzj(phoneNumber);
            } else {
                this.zzc.zzi(zzxa, phoneNumber);
                return;
            }
        }
        long zza2 = zzsy.zza();
        boolean zzh = zzsy.zzh();
        zzaau zzb2 = zzaau.zzb(zzsy.zzd(), zzsy.zzb().getUid(), zzsy.zzb().getPhoneNumber(), zzsy.zzc(), zzsy.zze(), zzsy.zzf());
        if (zzG(zza2, zzh)) {
            zzb2.zzd(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(phoneNumber, zzxa, zza2, zzh);
        this.zzb.zzG(zzb2, new zzys(this.zzc, zzxa, phoneNumber));
    }

    public final void zzB(zzta zzta, zzwz zzwz) {
        Preconditions.checkNotNull(zzta);
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzH(zzta.zza(), zzta.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzC(zztc zztc, zzwz zzwz) {
        Preconditions.checkNotNull(zztc);
        Preconditions.checkNotEmpty(zztc.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzI(zztc.zza(), new zzxa(zzwz, zza));
    }

    public final void zzD(zzte zzte, zzwz zzwz) {
        Preconditions.checkNotNull(zzte);
        Preconditions.checkNotEmpty(zzte.zzb());
        Preconditions.checkNotEmpty(zzte.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzJ(zzte.zzb(), zzte.zza(), new zzxa(zzwz, zza));
    }

    public final void zzE(zztg zztg, zzwz zzwz) {
        Preconditions.checkNotNull(zztg);
        Preconditions.checkNotEmpty(zztg.zzb());
        Preconditions.checkNotNull(zztg.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzK(zztg.zzb(), zztg.zza(), new zzxa(zzwz, zza));
    }

    public final void zzF(zzti zzti, zzwz zzwz) {
        Preconditions.checkNotNull(zzti);
        this.zzb.zzL(zzzv.zzc(zzti.zza(), zzti.zzb(), zzti.zzc()), new zzxa(zzwz, zza));
    }

    public final void zza(zzqy zzqy, zzwz zzwz) {
        Preconditions.checkNotNull(zzqy);
        Preconditions.checkNotEmpty(zzqy.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzg(zzqy.zza(), zzqy.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzb(zzra zzra, zzwz zzwz) {
        Preconditions.checkNotNull(zzra);
        Preconditions.checkNotEmpty(zzra.zza());
        Preconditions.checkNotEmpty(zzra.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzh(zzra.zza(), zzra.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzc(zzrc zzrc, zzwz zzwz) {
        Preconditions.checkNotNull(zzrc);
        Preconditions.checkNotEmpty(zzrc.zza());
        Preconditions.checkNotEmpty(zzrc.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzi(zzrc.zza(), zzrc.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzd(zzre zzre, zzwz zzwz) {
        Preconditions.checkNotNull(zzre);
        Preconditions.checkNotEmpty(zzre.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzj(zzre.zza(), zzre.zzb(), new zzxa(zzwz, zza));
    }

    public final void zze(zzrg zzrg, zzwz zzwz) {
        Preconditions.checkNotNull(zzrg);
        Preconditions.checkNotEmpty(zzrg.zza());
        Preconditions.checkNotEmpty(zzrg.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzk(zzrg.zza(), zzrg.zzb(), zzrg.zzc(), new zzxa(zzwz, zza));
    }

    public final void zzf(zzri zzri, zzwz zzwz) {
        Preconditions.checkNotNull(zzri);
        Preconditions.checkNotEmpty(zzri.zza());
        Preconditions.checkNotEmpty(zzri.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzl(zzri.zza(), zzri.zzb(), zzri.zzc(), new zzxa(zzwz, zza));
    }

    public final void zzg(zzrk zzrk, zzwz zzwz) {
        Preconditions.checkNotNull(zzrk);
        Preconditions.checkNotEmpty(zzrk.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzm(zzrk.zza(), new zzxa(zzwz, zza));
    }

    public final void zzh(zzrm zzrm, zzwz zzwz) {
        Preconditions.checkNotNull(zzrm);
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzn(zzzi.zzb(zzrm.zzb(), (String) Preconditions.checkNotNull(zzrm.zza().zzg()), (String) Preconditions.checkNotNull(zzrm.zza().getSmsCode()), zzrm.zzc()), zzrm.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzi(zzro zzro, zzwz zzwz) {
        Preconditions.checkNotNull(zzro);
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzo(zzzk.zzb(zzro.zzb(), (String) Preconditions.checkNotNull(zzro.zza().zzg()), (String) Preconditions.checkNotNull(zzro.zza().getSmsCode())), new zzxa(zzwz, zza));
    }

    public final void zzj(zzrq zzrq, zzwz zzwz) {
        Preconditions.checkNotNull(zzrq);
        Preconditions.checkNotNull(zzwz);
        Preconditions.checkNotEmpty(zzrq.zza());
        this.zzb.zzp(zzrq.zza(), new zzxa(zzwz, zza));
    }

    public final void zzk(zzrs zzrs, zzwz zzwz) {
        Preconditions.checkNotNull(zzrs);
        Preconditions.checkNotEmpty(zzrs.zza());
        this.zzb.zzq(zzrs.zza(), zzrs.zzb(), new zzxa(zzwz, zza));
    }

    public final void zzl(zzru zzru, zzwz zzwz) {
        Preconditions.checkNotNull(zzru);
        Preconditions.checkNotEmpty(zzru.zzb());
        Preconditions.checkNotEmpty(zzru.zzc());
        Preconditions.checkNotEmpty(zzru.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzr(zzru.zzb(), zzru.zzc(), zzru.zza(), new zzxa(zzwz, zza));
    }

    public final void zzm(zzrw zzrw, zzwz zzwz) {
        Preconditions.checkNotNull(zzrw);
        Preconditions.checkNotEmpty(zzrw.zzb());
        Preconditions.checkNotNull(zzrw.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzs(zzrw.zzb(), zzrw.zza(), new zzxa(zzwz, zza));
    }

    public final void zzn(zzry zzry, zzwz zzwz) {
        Preconditions.checkNotNull(zzwz);
        Preconditions.checkNotNull(zzry);
        this.zzb.zzt(Preconditions.checkNotEmpty(zzry.zzb()), zzyl.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzry.zza())), new zzxa(zzwz, zza));
    }

    public final void zzo(zzsa zzsa, zzwz zzwz) {
        Preconditions.checkNotNull(zzsa);
        Preconditions.checkNotEmpty(zzsa.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzu(zzsa.zza(), new zzxa(zzwz, zza));
    }

    public final void zzp(zzsc zzsc, zzwz zzwz) {
        Preconditions.checkNotNull(zzsc);
        Preconditions.checkNotEmpty(zzsc.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzv(zzsc.zzb(), zzsc.zza(), new zzxa(zzwz, zza));
    }

    public final void zzq(zzse zzse, zzwz zzwz) {
        Preconditions.checkNotNull(zzse);
        Preconditions.checkNotEmpty(zzse.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzw(zzse.zzb(), zzse.zza(), zzse.zzc(), new zzxa(zzwz, zza));
    }

    public final void zzr(zzsg zzsg, zzwz zzwz) {
        Preconditions.checkNotNull(zzwz);
        Preconditions.checkNotNull(zzsg);
        zzaal zzaal = (zzaal) Preconditions.checkNotNull(zzsg.zza());
        String zzd = zzaal.zzd();
        zzxa zzxa = new zzxa(zzwz, zza);
        if (this.zzc.zzl(zzd)) {
            if (zzaal.zzf()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zzxa, zzd);
                return;
            }
        }
        long zzb2 = zzaal.zzb();
        boolean zzg = zzaal.zzg();
        if (zzG(zzb2, zzg)) {
            zzaal.zze(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zzxa, zzb2, zzg);
        this.zzb.zzx(zzaal, new zzys(this.zzc, zzxa, zzd));
    }

    public final void zzs(zzsi zzsi, zzwz zzwz) {
        Preconditions.checkNotNull(zzsi);
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzy(zzsi.zza(), new zzxa(zzwz, zza));
    }

    public final void zzt(zzsk zzsk, zzwz zzwz) {
        Preconditions.checkNotNull(zzsk);
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzz(zzsk.zza(), new zzxa(zzwz, zza));
    }

    public final void zzu(zzsm zzsm, zzwz zzwz) {
        Preconditions.checkNotNull(zzsm);
        Preconditions.checkNotNull(zzsm.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzA(zzsm.zza(), new zzxa(zzwz, zza));
    }

    public final void zzv(zzso zzso, zzwz zzwz) {
        Preconditions.checkNotNull(zzso);
        Preconditions.checkNotEmpty(zzso.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzB(new zzabb(zzso.zzb(), zzso.zza()), new zzxa(zzwz, zza));
    }

    public final void zzw(zzsq zzsq, zzwz zzwz) {
        Preconditions.checkNotNull(zzsq);
        Preconditions.checkNotEmpty(zzsq.zza());
        Preconditions.checkNotEmpty(zzsq.zzb());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzC(zzsq.zza(), zzsq.zzb(), zzsq.zzc(), new zzxa(zzwz, zza));
    }

    public final void zzx(zzss zzss, zzwz zzwz) {
        Preconditions.checkNotNull(zzss);
        Preconditions.checkNotNull(zzss.zza());
        Preconditions.checkNotNull(zzwz);
        this.zzb.zzD(zzss.zza(), new zzxa(zzwz, zza));
    }

    public final void zzy(zzsu zzsu, zzwz zzwz) {
        Preconditions.checkNotNull(zzwz);
        Preconditions.checkNotNull(zzsu);
        this.zzb.zzE(zzyl.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzsu.zza())), new zzxa(zzwz, zza));
    }

    public final void zzz(zzsw zzsw, zzwz zzwz) {
        Preconditions.checkNotNull(zzsw);
        Preconditions.checkNotNull(zzwz);
        String zzd = zzsw.zzd();
        zzxa zzxa = new zzxa(zzwz, zza);
        if (this.zzc.zzl(zzd)) {
            if (zzsw.zzg()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zzxa, zzd);
                return;
            }
        }
        long zza2 = zzsw.zza();
        boolean zzh = zzsw.zzh();
        zzaas zzb2 = zzaas.zzb(zzsw.zzb(), zzsw.zzd(), zzsw.zzc(), zzsw.zze(), zzsw.zzf());
        if (zzG(zza2, zzh)) {
            zzb2.zzd(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zzxa, zza2, zzh);
        this.zzb.zzF(zzb2, new zzys(this.zzc, zzxa, zzd));
    }
}
