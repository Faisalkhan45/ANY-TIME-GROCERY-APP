package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.internal.zzao;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzxy implements zzwz {
    final /* synthetic */ zzyb zza;

    zzxy(zzyb zzyb) {
        this.zza = zzyb;
    }

    private final void zzp(zzxz zzxz) {
        this.zza.zzi.execute(new zzxx(this, zzxz));
    }

    private final void zzq(Status status, AuthCredential authCredential, String str, String str2) {
        zzyb.zzk(this.zza, status);
        zzyb zzyb = this.zza;
        zzyb.zzp = authCredential;
        zzyb.zzq = str;
        zzyb.zzr = str2;
        zzao zzao = zzyb.zzg;
        if (zzao != null) {
            zzao.zzb(status);
        }
        this.zza.zzl(status);
    }

    public final void zza(String str) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 8) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb zzyb = this.zza;
        zzyb.zzo = str;
        zzyb.zza = true;
        zzp(new zzxv(this, str));
    }

    public final void zzb(String str) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 8) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        this.zza.zzo = str;
        zzp(new zzxt(this, str));
    }

    public final void zzc(zzzd zzzd) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 3) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb zzyb = this.zza;
        zzyb.zzl = zzzd;
        zzyb.zzj(zzyb);
    }

    public final void zzd() throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 5) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb.zzj(this.zza);
    }

    public final void zze(zztk zztk) {
        zzq(zztk.zza(), zztk.zzb(), zztk.zzc(), zztk.zzd());
    }

    public final void zzf(zztm zztm) {
        zzyb zzyb = this.zza;
        zzyb.zzs = zztm;
        zzyb.zzl(zzai.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    public final void zzg(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzq(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zzh(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        zzyb zzyb = this.zza;
        if (zzyb.zzb == 8) {
            zzyb.zza = true;
            zzp(new zzxw(this, status));
            return;
        }
        zzyb.zzk(zzyb, status);
        this.zza.zzl(status);
    }

    public final void zzi(zzzy zzzy, zzzr zzzr) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type: " + i);
        zzyb zzyb = this.zza;
        zzyb.zzj = zzzy;
        zzyb.zzk = zzzr;
        zzyb.zzj(zzyb);
    }

    public final void zzj(zzaaj zzaaj) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 4) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb zzyb = this.zza;
        zzyb.zzm = zzaaj;
        zzyb.zzj(zzyb);
    }

    public final void zzk() throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 6) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb.zzj(this.zza);
    }

    public final void zzl(String str) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 7) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb zzyb = this.zza;
        zzyb.zzn = str;
        zzyb.zzj(zzyb);
    }

    public final void zzm() throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 9) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        zzyb.zzj(this.zza);
    }

    public final void zzn(zzzy zzzy) throws RemoteException {
        int i = this.zza.zzb;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type: " + i);
        zzyb zzyb = this.zza;
        zzyb.zzj = zzzy;
        zzyb.zzj(zzyb);
    }

    public final void zzo(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z;
        int i = this.zza.zzb;
        if (i == 8) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type " + i);
        this.zza.zza = true;
        zzp(new zzxu(this, phoneAuthCredential));
    }
}
