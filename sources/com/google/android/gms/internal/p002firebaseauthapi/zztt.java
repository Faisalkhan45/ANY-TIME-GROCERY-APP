package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zztt implements zzyg {
    final /* synthetic */ zzaao zza;
    final /* synthetic */ zzzr zzb;
    final /* synthetic */ zzxa zzc;
    final /* synthetic */ zzzy zzd;
    final /* synthetic */ zzyf zze;
    final /* synthetic */ zzvf zzf;

    zztt(zzvf zzvf, zzaao zzaao, zzzr zzzr, zzxa zzxa, zzzy zzzy, zzyf zzyf) {
        this.zzf = zzvf;
        this.zza = zzaao;
        this.zzb = zzzr;
        this.zzc = zzxa;
        this.zzd = zzzy;
        this.zze = zzyf;
    }

    public final void zza(String str) {
        this.zze.zza(str);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaap zzaap = (zzaap) obj;
        if (this.zza.zzn("EMAIL")) {
            this.zzb.zzg((String) null);
        } else {
            zzaao zzaao = this.zza;
            if (zzaao.zzk() != null) {
                this.zzb.zzg(zzaao.zzk());
            }
        }
        if (this.zza.zzn("DISPLAY_NAME")) {
            this.zzb.zzf((String) null);
        } else {
            zzaao zzaao2 = this.zza;
            if (zzaao2.zzj() != null) {
                this.zzb.zzf(zzaao2.zzj());
            }
        }
        if (this.zza.zzn("PHOTO_URL")) {
            this.zzb.zzj((String) null);
        } else {
            zzaao zzaao3 = this.zza;
            if (zzaao3.zzm() != null) {
                this.zzb.zzj(zzaao3.zzm());
            }
        }
        if (!TextUtils.isEmpty(this.zza.zzl())) {
            this.zzb.zzi(Base64Utils.encode("redacted".getBytes()));
        }
        List zzf2 = zzaap.zzf();
        if (zzf2 == null) {
            zzf2 = new ArrayList();
        }
        this.zzb.zzk(zzf2);
        zzxa zzxa = this.zzc;
        zzzy zzzy = this.zzd;
        Preconditions.checkNotNull(zzzy);
        Preconditions.checkNotNull(zzaap);
        String zzd2 = zzaap.zzd();
        String zze2 = zzaap.zze();
        if (!TextUtils.isEmpty(zzd2) && !TextUtils.isEmpty(zze2)) {
            zzzy = new zzzy(zze2, zzd2, Long.valueOf(zzaap.zzb()), zzzy.zzg());
        }
        zzxa.zzi(zzzy, this.zzb);
    }
}
