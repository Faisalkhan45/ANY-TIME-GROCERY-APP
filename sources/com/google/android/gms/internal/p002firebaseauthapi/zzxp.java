package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzxp extends zzyh implements zzyy {
    zzxq zza;
    private zzxj zzb;
    private zzxk zzc;
    private zzym zzd;
    private final zzxo zze;
    private final FirebaseApp zzf;
    private final String zzg;

    zzxp(FirebaseApp firebaseApp, zzxo zzxo, zzym zzym, zzxj zzxj, zzxk zzxk) {
        this.zzf = firebaseApp;
        String apiKey = firebaseApp.getOptions().getApiKey();
        this.zzg = apiKey;
        this.zze = (zzxo) Preconditions.checkNotNull(zzxo);
        zzw((zzym) null, (zzxj) null, (zzxk) null);
        zzyz.zze(apiKey, this);
    }

    private final zzxq zzv() {
        if (this.zza == null) {
            FirebaseApp firebaseApp = this.zzf;
            this.zza = new zzxq(firebaseApp.getApplicationContext(), firebaseApp, this.zze.zzb());
        }
        return this.zza;
    }

    private final void zzw(zzym zzym, zzxj zzxj, zzxk zzxk) {
        this.zzd = null;
        this.zzb = null;
        this.zzc = null;
        String zza2 = zzyw.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzyz.zzd(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for secureToken URL: ".concat(String.valueOf(zza2)));
        }
        if (this.zzd == null) {
            this.zzd = new zzym(zza2, zzv());
        }
        String zza3 = zzyw.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzyz.zzb(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for identityToolkit URL: ".concat(String.valueOf(zza3)));
        }
        if (this.zzb == null) {
            this.zzb = new zzxj(zza3, zzv());
        }
        String zza4 = zzyw.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza4)) {
            zza4 = zzyz.zzc(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for identityToolkitV2 URL: ".concat(String.valueOf(zza4)));
        }
        if (this.zzc == null) {
            this.zzc = new zzxk(zza4, zzv());
        }
    }

    public final void zza(zzzc zzzc, zzyg zzyg) {
        Preconditions.checkNotNull(zzzc);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/createAuthUri", this.zzg), zzzc, zzyg, zzzd.class, zzxj.zzb);
    }

    public final void zzb(zzzf zzzf, zzyg zzyg) {
        Preconditions.checkNotNull(zzzf);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/deleteAccount", this.zzg), zzzf, zzyg, Void.class, zzxj.zzb);
    }

    public final void zzc(zzzg zzzg, zzyg zzyg) {
        Preconditions.checkNotNull(zzzg);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/emailLinkSignin", this.zzg), zzzg, zzyg, zzzh.class, zzxj.zzb);
    }

    public final void zzd(zzzi zzzi, zzyg zzyg) {
        Preconditions.checkNotNull(zzzi);
        Preconditions.checkNotNull(zzyg);
        zzxk zzxk = this.zzc;
        zzyj.zza(zzxk.zza("/accounts/mfaEnrollment:finalize", this.zzg), zzzi, zzyg, zzzj.class, zzxk.zzb);
    }

    public final void zze(zzzk zzzk, zzyg zzyg) {
        Preconditions.checkNotNull(zzzk);
        Preconditions.checkNotNull(zzyg);
        zzxk zzxk = this.zzc;
        zzyj.zza(zzxk.zza("/accounts/mfaSignIn:finalize", this.zzg), zzzk, zzyg, zzzl.class, zzxk.zzb);
    }

    public final void zzf(zzzn zzzn, zzyg zzyg) {
        Preconditions.checkNotNull(zzzn);
        Preconditions.checkNotNull(zzyg);
        zzym zzym = this.zzd;
        zzyj.zza(zzym.zza("/token", this.zzg), zzzn, zzyg, zzzy.class, zzym.zzb);
    }

    public final void zzg(zzzo zzzo, zzyg zzyg) {
        Preconditions.checkNotNull(zzzo);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/getAccountInfo", this.zzg), zzzo, zzyg, zzzp.class, zzxj.zzb);
    }

    public final void zzh(zzzv zzzv, zzyg zzyg) {
        Preconditions.checkNotNull(zzzv);
        Preconditions.checkNotNull(zzyg);
        if (zzzv.zzb() != null) {
            zzv().zzc(zzzv.zzb().zze());
        }
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/getOobConfirmationCode", this.zzg), zzzv, zzyg, zzzw.class, zzxj.zzb);
    }

    public final void zzi() {
        zzw((zzym) null, (zzxj) null, (zzxk) null);
    }

    public final void zzj(zzaai zzaai, zzyg zzyg) {
        Preconditions.checkNotNull(zzaai);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/resetPassword", this.zzg), zzaai, zzyg, zzaaj.class, zzxj.zzb);
    }

    public final void zzk(zzaal zzaal, zzyg zzyg) {
        Preconditions.checkNotNull(zzaal);
        Preconditions.checkNotNull(zzyg);
        if (!TextUtils.isEmpty(zzaal.zzc())) {
            zzv().zzc(zzaal.zzc());
        }
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/sendVerificationCode", this.zzg), zzaal, zzyg, zzaan.class, zzxj.zzb);
    }

    public final void zzl(zzaao zzaao, zzyg zzyg) {
        Preconditions.checkNotNull(zzaao);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/setAccountInfo", this.zzg), zzaao, zzyg, zzaap.class, zzxj.zzb);
    }

    public final void zzm(String str, zzyg zzyg) {
        Preconditions.checkNotNull(zzyg);
        zzv().zzb(str);
        ((zzuw) zzyg).zza.zzm();
    }

    public final void zzn(zzaaq zzaaq, zzyg zzyg) {
        Preconditions.checkNotNull(zzaaq);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/signupNewUser", this.zzg), zzaaq, zzyg, zzaar.class, zzxj.zzb);
    }

    public final void zzo(zzaas zzaas, zzyg zzyg) {
        Preconditions.checkNotNull(zzaas);
        Preconditions.checkNotNull(zzyg);
        if (!TextUtils.isEmpty(zzaas.zzc())) {
            zzv().zzc(zzaas.zzc());
        }
        zzxk zzxk = this.zzc;
        zzyj.zza(zzxk.zza("/accounts/mfaEnrollment:start", this.zzg), zzaas, zzyg, zzaat.class, zzxk.zzb);
    }

    public final void zzp(zzaau zzaau, zzyg zzyg) {
        Preconditions.checkNotNull(zzaau);
        Preconditions.checkNotNull(zzyg);
        if (!TextUtils.isEmpty(zzaau.zzc())) {
            zzv().zzc(zzaau.zzc());
        }
        zzxk zzxk = this.zzc;
        zzyj.zza(zzxk.zza("/accounts/mfaSignIn:start", this.zzg), zzaau, zzyg, zzaav.class, zzxk.zzb);
    }

    public final void zzq(zzaay zzaay, zzyg zzyg) {
        Preconditions.checkNotNull(zzaay);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/verifyAssertion", this.zzg), zzaay, zzyg, zzaba.class, zzxj.zzb);
    }

    public final void zzr(zzabb zzabb, zzyg zzyg) {
        Preconditions.checkNotNull(zzabb);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/verifyCustomToken", this.zzg), zzabb, zzyg, zzabc.class, zzxj.zzb);
    }

    public final void zzs(zzabe zzabe, zzyg zzyg) {
        Preconditions.checkNotNull(zzabe);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/verifyPassword", this.zzg), zzabe, zzyg, zzabf.class, zzxj.zzb);
    }

    public final void zzt(zzabg zzabg, zzyg zzyg) {
        Preconditions.checkNotNull(zzabg);
        Preconditions.checkNotNull(zzyg);
        zzxj zzxj = this.zzb;
        zzyj.zza(zzxj.zza("/verifyPhoneNumber", this.zzg), zzabg, zzyg, zzabh.class, zzxj.zzb);
    }

    public final void zzu(zzabi zzabi, zzyg zzyg) {
        Preconditions.checkNotNull(zzabi);
        Preconditions.checkNotNull(zzyg);
        zzxk zzxk = this.zzc;
        zzyj.zza(zzxk.zza("/accounts/mfaEnrollment:withdraw", this.zzg), zzabi, zzyg, zzabj.class, zzxk.zzb);
    }
}
