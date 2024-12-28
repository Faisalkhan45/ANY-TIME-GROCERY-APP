package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.internal.zzbk;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.auth.internal.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzwy extends zzye {
    public zzwy(FirebaseApp firebaseApp) {
        this.zza = new zzxb(firebaseApp);
        this.zzb = Executors.newCachedThreadPool();
    }

    static zzx zzN(FirebaseApp firebaseApp, zzzr zzzr) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzzr);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzt(zzzr, FirebaseAuthProvider.PROVIDER_ID));
        List zzr = zzzr.zzr();
        if (zzr != null && !zzr.isEmpty()) {
            for (int i = 0; i < zzr.size(); i++) {
                arrayList.add(new zzt((zzaae) zzr.get(i)));
            }
        }
        zzx zzx = new zzx(firebaseApp, arrayList);
        zzx.zzr(new zzz(zzzr.zzb(), zzzr.zza()));
        zzx.zzq(zzzr.zzt());
        zzx.zzp(zzzr.zzd());
        zzx.zzi(zzba.zzb(zzzr.zzq()));
        return zzx;
    }

    public final Task zzA(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzwj zzwj = new zzwj(str, str2, str3);
        zzwj.zzf(firebaseApp);
        zzwj.zzd(zzg);
        return zzP(zzwj);
    }

    public final Task zzB(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zzg zzg) {
        zzwk zzwk = new zzwk(emailAuthCredential);
        zzwk.zzf(firebaseApp);
        zzwk.zzd(zzg);
        return zzP(zzwk);
    }

    public final Task zzC(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zzg zzg) {
        zzyp.zzc();
        zzwl zzwl = new zzwl(phoneAuthCredential, str);
        zzwl.zzf(firebaseApp);
        zzwl.zzd(zzg);
        return zzP(zzwl);
    }

    public final Task zzD(zzag zzag, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzwm zzwm = new zzwm(zzag, str, str2, j, z, z2, str3, str4, z3);
        String str5 = str;
        zzwm.zzh(onVerificationStateChangedCallbacks, activity, executor, str);
        return zzP(zzwm);
    }

    public final Task zzE(zzag zzag, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, String str2, String str3, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzwn zzwn = new zzwn(phoneMultiFactorInfo, Preconditions.checkNotEmpty(zzag.zzd()), str, j, z, z2, str2, str3, z3);
        Activity activity2 = activity;
        zzwn.zzh(onVerificationStateChangedCallbacks, activity2, executor, phoneMultiFactorInfo.getUid());
        return zzP(zzwn);
    }

    public final Task zzF(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzwo zzwo = new zzwo(firebaseUser.zzf(), str);
        zzwo.zzf(firebaseApp);
        zzwo.zzg(firebaseUser);
        zzwo.zzd(zzbk);
        zzwo.zze(zzbk);
        return zzP(zzwo);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task zzG(com.google.firebase.FirebaseApp r2, com.google.firebase.auth.FirebaseUser r3, java.lang.String r4, com.google.firebase.auth.internal.zzbk r5) {
        /*
            r1 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.util.List r0 = r3.zzg()
            if (r0 == 0) goto L_0x0018
            boolean r0 = r0.contains(r4)
            if (r0 == 0) goto L_0x001e
        L_0x0018:
            boolean r0 = r3.isAnonymous()
            if (r0 == 0) goto L_0x002e
        L_0x001e:
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r3 = 17016(0x4278, float:2.3844E-41)
            r2.<init>((int) r3, (java.lang.String) r4)
            com.google.firebase.FirebaseException r2 = com.google.android.gms.internal.p002firebaseauthapi.zzxc.zza(r2)
            com.google.android.gms.tasks.Task r2 = com.google.android.gms.tasks.Tasks.forException(r2)
            return r2
        L_0x002e:
            int r0 = r4.hashCode()
            switch(r0) {
                case 1216985755: goto L_0x0036;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0041
        L_0x0036:
            java.lang.String r0 = "password"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0035
            r0 = 0
            goto L_0x0042
        L_0x0041:
            r0 = -1
        L_0x0042:
            switch(r0) {
                case 0: goto L_0x005b;
                default: goto L_0x0045;
            }
        L_0x0045:
            com.google.android.gms.internal.firebase-auth-api.zzwq r0 = new com.google.android.gms.internal.firebase-auth-api.zzwq
            r0.<init>(r4)
            r0.zzf(r2)
            r0.zzg(r3)
            r0.zzd(r5)
            r0.zze(r5)
            com.google.android.gms.tasks.Task r2 = r1.zzP(r0)
            return r2
        L_0x005b:
            com.google.android.gms.internal.firebase-auth-api.zzwp r4 = new com.google.android.gms.internal.firebase-auth-api.zzwp
            r4.<init>()
            r4.zzf(r2)
            r4.zzg(r3)
            r4.zzd(r5)
            r4.zze(r5)
            com.google.android.gms.tasks.Task r2 = r1.zzP(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzwy.zzG(com.google.firebase.FirebaseApp, com.google.firebase.auth.FirebaseUser, java.lang.String, com.google.firebase.auth.internal.zzbk):com.google.android.gms.tasks.Task");
    }

    public final Task zzH(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzwr zzwr = new zzwr(str);
        zzwr.zzf(firebaseApp);
        zzwr.zzg(firebaseUser);
        zzwr.zzd(zzbk);
        zzwr.zze(zzbk);
        return zzP(zzwr);
    }

    public final Task zzI(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzws zzws = new zzws(str);
        zzws.zzf(firebaseApp);
        zzws.zzg(firebaseUser);
        zzws.zzd(zzbk);
        zzws.zze(zzbk);
        return zzP(zzws);
    }

    public final Task zzJ(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzbk zzbk) {
        zzyp.zzc();
        zzwt zzwt = new zzwt(phoneAuthCredential);
        zzwt.zzf(firebaseApp);
        zzwt.zzg(firebaseUser);
        zzwt.zzd(zzbk);
        zzwt.zze(zzbk);
        return zzP(zzwt);
    }

    public final Task zzK(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzbk zzbk) {
        zzwu zzwu = new zzwu(userProfileChangeRequest);
        zzwu.zzf(firebaseApp);
        zzwu.zzg(firebaseUser);
        zzwu.zzd(zzbk);
        zzwu.zze(zzbk);
        return zzP(zzwu);
    }

    public final Task zzL(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zzg(7);
        return zzP(new zzwv(str, str2, actionCodeSettings));
    }

    public final Task zzM(FirebaseApp firebaseApp, String str, String str2) {
        zzww zzww = new zzww(str, str2);
        zzww.zzf(firebaseApp);
        return zzP(zzww);
    }

    public final void zzO(FirebaseApp firebaseApp, zzaal zzaal, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzwx zzwx = new zzwx(zzaal);
        zzwx.zzf(firebaseApp);
        zzwx.zzh(onVerificationStateChangedCallbacks, activity, executor, zzaal.zzd());
        zzP(zzwx);
    }

    public final Task zza(FirebaseApp firebaseApp, String str, String str2) {
        zzvh zzvh = new zzvh(str, str2);
        zzvh.zzf(firebaseApp);
        return zzP(zzvh);
    }

    public final Task zzb(FirebaseApp firebaseApp, String str, String str2) {
        zzvi zzvi = new zzvi(str, str2);
        zzvi.zzf(firebaseApp);
        return zzP(zzvi);
    }

    public final Task zzc(FirebaseApp firebaseApp, String str, String str2, String str3) {
        zzvj zzvj = new zzvj(str, str2, str3);
        zzvj.zzf(firebaseApp);
        return zzP(zzvj);
    }

    public final Task zzd(FirebaseApp firebaseApp, String str, String str2, String str3, zzg zzg) {
        zzvk zzvk = new zzvk(str, str2, str3);
        zzvk.zzf(firebaseApp);
        zzvk.zzd(zzg);
        return zzP(zzvk);
    }

    public final Task zze(FirebaseUser firebaseUser, zzan zzan) {
        zzvl zzvl = new zzvl();
        zzvl.zzg(firebaseUser);
        zzvl.zzd(zzan);
        zzvl.zze(zzan);
        return zzP(zzvl);
    }

    public final Task zzf(FirebaseApp firebaseApp, String str, String str2) {
        zzvm zzvm = new zzvm(str, str2);
        zzvm.zzf(firebaseApp);
        return zzP(zzvm);
    }

    public final Task zzg(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zzg zzg) {
        zzyp.zzc();
        zzvn zzvn = new zzvn(phoneMultiFactorAssertion, firebaseUser.zzf(), str);
        zzvn.zzf(firebaseApp);
        zzvn.zzd(zzg);
        return zzP(zzvn);
    }

    public final Task zzh(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzg zzg) {
        zzyp.zzc();
        zzvo zzvo = new zzvo(phoneMultiFactorAssertion, str);
        zzvo.zzf(firebaseApp);
        zzvo.zzd(zzg);
        if (firebaseUser != null) {
            zzvo.zzg(firebaseUser);
        }
        return zzP(zzvo);
    }

    public final Task zzi(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbk zzbk) {
        zzvp zzvp = new zzvp(str);
        zzvp.zzf(firebaseApp);
        zzvp.zzg(firebaseUser);
        zzvp.zzd(zzbk);
        zzvp.zze(zzbk);
        return zzP(zzvp);
    }

    public final Task zzj(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbk);
        List zzg = firebaseUser.zzg();
        if (zzg != null && zzg.contains(authCredential.getProvider())) {
            return Tasks.forException(zzxc.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzg()) {
                zzvq zzvq = new zzvq(emailAuthCredential);
                zzvq.zzf(firebaseApp);
                zzvq.zzg(firebaseUser);
                zzvq.zzd(zzbk);
                zzvq.zze(zzbk);
                return zzP(zzvq);
            }
            zzvt zzvt = new zzvt(emailAuthCredential);
            zzvt.zzf(firebaseApp);
            zzvt.zzg(firebaseUser);
            zzvt.zzd(zzbk);
            zzvt.zze(zzbk);
            return zzP(zzvt);
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzyp.zzc();
            zzvs zzvs = new zzvs((PhoneAuthCredential) authCredential);
            zzvs.zzf(firebaseApp);
            zzvs.zzg(firebaseUser);
            zzvs.zzd(zzbk);
            zzvs.zze(zzbk);
            return zzP(zzvs);
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzbk);
            zzvr zzvr = new zzvr(authCredential);
            zzvr.zzf(firebaseApp);
            zzvr.zzg(firebaseUser);
            zzvr.zzd(zzbk);
            zzvr.zze(zzbk);
            return zzP(zzvr);
        }
    }

    public final Task zzk(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzvu zzvu = new zzvu(authCredential, str);
        zzvu.zzf(firebaseApp);
        zzvu.zzg(firebaseUser);
        zzvu.zzd(zzbk);
        zzvu.zze(zzbk);
        return zzP(zzvu);
    }

    public final Task zzl(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbk zzbk) {
        zzvv zzvv = new zzvv(authCredential, str);
        zzvv.zzf(firebaseApp);
        zzvv.zzg(firebaseUser);
        zzvv.zzd(zzbk);
        zzvv.zze(zzbk);
        return zzP(zzvv);
    }

    public final Task zzm(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzvw zzvw = new zzvw(emailAuthCredential);
        zzvw.zzf(firebaseApp);
        zzvw.zzg(firebaseUser);
        zzvw.zzd(zzbk);
        zzvw.zze(zzbk);
        return zzP(zzvw);
    }

    public final Task zzn(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzbk zzbk) {
        zzvx zzvx = new zzvx(emailAuthCredential);
        zzvx.zzf(firebaseApp);
        zzvx.zzg(firebaseUser);
        zzvx.zzd(zzbk);
        zzvx.zze(zzbk);
        return zzP(zzvx);
    }

    public final Task zzo(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzvy zzvy = new zzvy(str, str2, str3);
        zzvy.zzf(firebaseApp);
        zzvy.zzg(firebaseUser);
        zzvy.zzd(zzbk);
        zzvy.zze(zzbk);
        return zzP(zzvy);
    }

    public final Task zzp(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzbk zzbk) {
        zzvz zzvz = new zzvz(str, str2, str3);
        zzvz.zzf(firebaseApp);
        zzvz.zzg(firebaseUser);
        zzvz.zzd(zzbk);
        zzvz.zze(zzbk);
        return zzP(zzvz);
    }

    public final Task zzq(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzyp.zzc();
        zzwa zzwa = new zzwa(phoneAuthCredential, str);
        zzwa.zzf(firebaseApp);
        zzwa.zzg(firebaseUser);
        zzwa.zzd(zzbk);
        zzwa.zze(zzbk);
        return zzP(zzwa);
    }

    public final Task zzr(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbk zzbk) {
        zzyp.zzc();
        zzwb zzwb = new zzwb(phoneAuthCredential, str);
        zzwb.zzf(firebaseApp);
        zzwb.zzg(firebaseUser);
        zzwb.zzd(zzbk);
        zzwb.zze(zzbk);
        return zzP(zzwb);
    }

    public final Task zzs(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzbk zzbk) {
        zzwc zzwc = new zzwc();
        zzwc.zzf(firebaseApp);
        zzwc.zzg(firebaseUser);
        zzwc.zzd(zzbk);
        zzwc.zze(zzbk);
        return zzP(zzwc);
    }

    public final Task zzt(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        zzwd zzwd = new zzwd(str, actionCodeSettings);
        zzwd.zzf(firebaseApp);
        return zzP(zzwd);
    }

    public final Task zzu(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zzg(1);
        zzwe zzwe = new zzwe(str, actionCodeSettings, str2, "sendPasswordResetEmail");
        zzwe.zzf(firebaseApp);
        return zzP(zzwe);
    }

    public final Task zzv(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zzg(6);
        zzwe zzwe = new zzwe(str, actionCodeSettings, str2, "sendSignInLinkToEmail");
        zzwe.zzf(firebaseApp);
        return zzP(zzwe);
    }

    public final Task zzw(String str) {
        return zzP(new zzwf(str));
    }

    public final Task zzx(FirebaseApp firebaseApp, zzg zzg, String str) {
        zzwg zzwg = new zzwg(str);
        zzwg.zzf(firebaseApp);
        zzwg.zzd(zzg);
        return zzP(zzwg);
    }

    public final Task zzy(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zzg zzg) {
        zzwh zzwh = new zzwh(authCredential, str);
        zzwh.zzf(firebaseApp);
        zzwh.zzd(zzg);
        return zzP(zzwh);
    }

    public final Task zzz(FirebaseApp firebaseApp, String str, String str2, zzg zzg) {
        zzwi zzwi = new zzwi(str, str2);
        zzwi.zzf(firebaseApp);
        zzwi.zzd(zzg);
        return zzP(zzwi);
    }
}
