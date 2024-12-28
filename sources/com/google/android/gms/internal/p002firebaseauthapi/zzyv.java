package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzyv {
    /* access modifiers changed from: private */
    public static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    /* access modifiers changed from: private */
    public final HashMap zzd = new HashMap();

    zzyv(Context context) {
        this.zzb = (Context) Preconditions.checkNotNull(context);
        zzf.zza();
        this.zzc = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
    }

    static String zzb(String str) {
        Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    static /* bridge */ /* synthetic */ void zze(zzyv zzyv, String str) {
        zzyu zzyu = (zzyu) zzyv.zzd.get(str);
        if (zzyu != null && !zzag.zzd(zzyu.zzd) && !zzag.zzd(zzyu.zze) && !zzyu.zzb.isEmpty()) {
            for (zzxa zzo : zzyu.zzb) {
                zzo.zzo(PhoneAuthCredential.zzc(zzyu.zzd, zzyu.zze));
            }
            zzyu.zzh = true;
        }
    }

    private static String zzm(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str3.getBytes(zzo.zzc));
            String substring = Base64.encodeToString(Arrays.copyOf(instance.digest(), 9), 3).substring(0, 11);
            zza.d("Package: " + str + " -- Hash: " + substring, new Object[0]);
            return substring;
        } catch (NoSuchAlgorithmException e) {
            zza.e("NoSuchAlgorithm: ".concat(String.valueOf(e.getMessage())), new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzn(String str) {
        zzyu zzyu = (zzyu) this.zzd.get(str);
        if (zzyu != null && !zzyu.zzh && !zzag.zzd(zzyu.zzd)) {
            zza.w("Timed out waiting for SMS.", new Object[0]);
            for (zzxa zza2 : zzyu.zzb) {
                zza2.zza(zzyu.zzd);
            }
            zzyu.zzi = true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzo */
    public final void zzh(String str) {
        zzyu zzyu = (zzyu) this.zzd.get(str);
        if (zzyu != null) {
            if (!zzyu.zzi) {
                zzn(str);
            }
            zzj(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzc() {
        Signature[] signatureArr;
        try {
            String packageName = this.zzb.getPackageName();
            if (Build.VERSION.SDK_INT < 28) {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 64).signatures;
            } else {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 134217728).signingInfo.getApkContentsSigners();
            }
            String zzm = zzm(packageName, signatureArr[0].toCharsString());
            if (zzm != null) {
                return zzm;
            }
            zza.e("Hash generation failed.", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zza.e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(zzxa zzxa, String str) {
        zzyu zzyu = (zzyu) this.zzd.get(str);
        if (zzyu != null) {
            zzyu.zzb.add(zzxa);
            if (zzyu.zzg) {
                zzxa.zzb(zzyu.zzd);
            }
            if (zzyu.zzh) {
                zzxa.zzo(PhoneAuthCredential.zzc(zzyu.zzd, zzyu.zze));
            }
            if (zzyu.zzi) {
                zzxa.zza(zzyu.zzd);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(String str) {
        zzyu zzyu = (zzyu) this.zzd.get(str);
        if (zzyu != null) {
            ScheduledFuture scheduledFuture = zzyu.zzf;
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                zzyu.zzf.cancel(false);
            }
            zzyu.zzb.clear();
            this.zzd.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzk(String str, zzxa zzxa, long j, boolean z) {
        this.zzd.put(str, new zzyu(j, z));
        zzi(zzxa, str);
        zzyu zzyu = (zzyu) this.zzd.get(str);
        long j2 = zzyu.zza;
        if (j2 <= 0) {
            zza.w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzyu.zzf = this.zzc.schedule(new zzyq(this, str), j2, TimeUnit.SECONDS);
        if (!zzyu.zzc) {
            zza.w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzyt zzyt = new zzyt(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        zzb.zza(this.zzb.getApplicationContext(), zzyt, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzyr(this));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl(String str) {
        return this.zzd.get(str) != null;
    }
}
