package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzvf {
    /* access modifiers changed from: private */
    public final zzyh zza;

    public zzvf(zzyh zzyh) {
        this.zza = (zzyh) Preconditions.checkNotNull(zzyh);
    }

    private final void zzM(String str, zzyg zzyg) {
        Preconditions.checkNotNull(zzyg);
        Preconditions.checkNotEmpty(str);
        zzzy zzd = zzzy.zzd(str);
        if (zzd.zzj()) {
            zzyg.zzb(zzd);
            return;
        }
        this.zza.zzf(new zzzn(zzd.zzf()), new zzve(this, zzyg));
    }

    /* access modifiers changed from: private */
    public final void zzN(zzzg zzzg, zzxa zzxa) {
        Preconditions.checkNotNull(zzzg);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzc(zzzg, new zztr(this, zzxa));
    }

    /* access modifiers changed from: private */
    public final void zzO(zzzy zzzy, String str, String str2, Boolean bool, zze zze, zzxa zzxa, zzyf zzyf) {
        Preconditions.checkNotNull(zzzy);
        Preconditions.checkNotNull(zzyf);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzg(new zzzo(zzzy.zze()), new zztu(this, zzyf, str2, str, bool, zze, zzxa, zzzy));
    }

    private final void zzP(zzzv zzzv, zzxa zzxa) {
        Preconditions.checkNotNull(zzzv);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzh(zzzv, new zzux(this, zzxa));
    }

    static /* bridge */ /* synthetic */ void zzd(zzvf zzvf, zzaba zzaba, zzxa zzxa, zzyf zzyf) {
        Status status;
        if (zzaba.zzp()) {
            zze zzc = zzaba.zzc();
            String zzd = zzaba.zzd();
            String zzk = zzaba.zzk();
            if (zzaba.zzn()) {
                status = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                status = zzai.zza(zzaba.zze());
            }
            zzxa.zze(new zztk(status, zzc, zzd, zzk));
            return;
        }
        zzvf.zzO(new zzzy(zzaba.zzj(), zzaba.zzf(), Long.valueOf(zzaba.zzb()), "Bearer"), zzaba.zzi(), zzaba.zzh(), Boolean.valueOf(zzaba.zzo()), zzaba.zzc(), zzxa, zzyf);
    }

    static /* bridge */ /* synthetic */ void zze(zzvf zzvf, zzxa zzxa, zzzy zzzy, zzaao zzaao, zzyf zzyf) {
        Preconditions.checkNotNull(zzxa);
        Preconditions.checkNotNull(zzzy);
        Preconditions.checkNotNull(zzaao);
        Preconditions.checkNotNull(zzyf);
        zzvf.zza.zzg(new zzzo(zzzy.zze()), new zzts(zzvf, zzyf, zzxa, zzzy, zzaao));
    }

    static /* bridge */ /* synthetic */ void zzf(zzvf zzvf, zzxa zzxa, zzzy zzzy, zzzr zzzr, zzaao zzaao, zzyf zzyf) {
        Preconditions.checkNotNull(zzxa);
        Preconditions.checkNotNull(zzzy);
        Preconditions.checkNotNull(zzzr);
        Preconditions.checkNotNull(zzaao);
        Preconditions.checkNotNull(zzyf);
        zzvf.zza.zzl(zzaao, new zztt(zzvf, zzaao, zzzr, zzxa, zzzy, zzyf));
    }

    public final void zzA(zzaay zzaay, zzxa zzxa) {
        Preconditions.checkNotNull(zzaay);
        Preconditions.checkNotNull(zzxa);
        zzaay.zzd(true);
        this.zza.zzq(zzaay, new zzuy(this, zzxa));
    }

    public final void zzB(zzabb zzabb, zzxa zzxa) {
        Preconditions.checkNotNull(zzabb);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzr(zzabb, new zzun(this, zzxa));
    }

    public final void zzC(String str, String str2, String str3, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzs(new zzabe(str, str2, str3), new zztp(this, zzxa));
    }

    public final void zzD(EmailAuthCredential emailAuthCredential, zzxa zzxa) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzxa);
        if (emailAuthCredential.zzh()) {
            zzM(emailAuthCredential.zzc(), new zztq(this, emailAuthCredential, zzxa));
        } else {
            zzN(new zzzg(emailAuthCredential, (String) null), zzxa);
        }
    }

    public final void zzE(zzabg zzabg, zzxa zzxa) {
        Preconditions.checkNotNull(zzabg);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzt(zzabg, new zzub(this, zzxa));
    }

    public final void zzF(zzaas zzaas, zzxa zzxa) {
        Preconditions.checkNotNull(zzaas);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzo(zzaas, new zzum(this, zzxa));
    }

    public final void zzG(zzaau zzaau, zzxa zzxa) {
        Preconditions.checkNotNull(zzaau);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzp(zzaau, new zzur(this, zzxa));
    }

    public final void zzH(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzul(this, str2, zzxa));
    }

    public final void zzI(String str, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzuh(this, zzxa));
    }

    public final void zzJ(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        zzM(str2, new zzuj(this, str, zzxa));
    }

    public final void zzK(String str, UserProfileChangeRequest userProfileChangeRequest, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzva(this, userProfileChangeRequest, zzxa));
    }

    public final void zzL(zzzv zzzv, zzxa zzxa) {
        zzP(zzzv, zzxa);
    }

    public final void zzg(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzaao zzaao = new zzaao();
        zzaao.zzf(str);
        zzaao.zzi(str2);
        this.zza.zzl(zzaao, new zzvd(this, zzxa));
    }

    public final void zzh(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzvb(this, str2, zzxa));
    }

    public final void zzi(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzvc(this, str2, zzxa));
    }

    public final void zzj(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzj(new zzaai(str, (String) null, str2), new zztx(this, zzxa));
    }

    public final void zzk(String str, String str2, String str3, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzj(new zzaai(str, str2, str3), new zztz(this, zzxa));
    }

    public final void zzl(String str, String str2, String str3, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzn(new zzaaq(str, str2, (String) null, str3), new zzto(this, zzxa));
    }

    public final void zzm(String str, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzuv(this, zzxa));
    }

    public final void zzn(zzzi zzzi, String str, zzxa zzxa) {
        Preconditions.checkNotNull(zzzi);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzup(this, zzzi, zzxa));
    }

    public final void zzo(zzzk zzzk, zzxa zzxa) {
        Preconditions.checkNotNull(zzzk);
        Preconditions.checkNotNull(zzxa);
        this.zza.zze(zzzk, new zzuq(this, zzxa));
    }

    public final void zzp(String str, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        this.zza.zzf(new zzzn(str), new zzty(this, zzxa));
    }

    public final void zzq(String str, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        this.zza.zza(new zzzc(str, str2), new zztv(this, zzxa));
    }

    public final void zzr(String str, String str2, String str3, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzxa);
        zzM(str3, new zzuc(this, str, str2, zzxa));
    }

    public final void zzs(String str, zzaay zzaay, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaay);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzug(this, zzaay, zzxa));
    }

    public final void zzt(String str, zzabg zzabg, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzabg);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzue(this, zzabg, zzxa));
    }

    public final void zzu(String str, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzM(str, new zzut(this, zzxa));
    }

    public final void zzv(String str, ActionCodeSettings actionCodeSettings, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzzv zzzv = new zzzv(4);
        zzzv.zzg(str);
        if (actionCodeSettings != null) {
            zzzv.zzd(actionCodeSettings);
        }
        zzP(zzzv, zzxa);
    }

    public final void zzw(String str, ActionCodeSettings actionCodeSettings, String str2, zzxa zzxa) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzxa);
        zzzv zzzv = new zzzv(actionCodeSettings.zza());
        zzzv.zze(str);
        zzzv.zzd(actionCodeSettings);
        zzzv.zzf(str2);
        this.zza.zzh(zzzv, new zztw(this, zzxa));
    }

    public final void zzx(zzaal zzaal, zzxa zzxa) {
        Preconditions.checkNotEmpty(zzaal.zzd());
        Preconditions.checkNotNull(zzxa);
        this.zza.zzk(zzaal, new zzua(this, zzxa));
    }

    public final void zzy(String str, zzxa zzxa) {
        Preconditions.checkNotNull(zzxa);
        this.zza.zzm(str, new zzuw(this, zzxa));
    }

    public final void zzz(String str, zzxa zzxa) {
        Preconditions.checkNotNull(zzxa);
        this.zza.zzn(new zzaaq(str), new zzuz(this, zzxa));
    }
}
