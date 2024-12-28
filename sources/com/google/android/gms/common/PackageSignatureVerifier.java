package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;

@RestrictedInheritance(allowedOnPath = ".*javatests.*/com/google/android/gms/common/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public class PackageSignatureVerifier {
    private static zzad zza;
    private volatile zzac zzb;

    private static zzad zza() {
        zzad zzad;
        synchronized (zzad.class) {
            if (zza == null) {
                zza = new zzad();
            }
            zzad = zza;
        }
        return zzad;
    }

    public PackageVerificationResult queryPackageSignatureVerified(Context context, String callingPackage) {
        String str;
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        zza();
        if (zzn.zzf()) {
            if (true != honorsDebugCertificates) {
                str = "-0";
            } else {
                str = "-1";
            }
            String concat = String.valueOf(callingPackage).concat(str);
            if (this.zzb != null && this.zzb.zza.equals(concat)) {
                return this.zzb.zzb;
            }
            zza();
            zzx zzc = zzn.zzc(callingPackage, honorsDebugCertificates, false, false);
            if (zzc.zza) {
                this.zzb = new zzac(concat, PackageVerificationResult.zzd(callingPackage, zzc.zzd));
                return this.zzb.zzb;
            }
            Preconditions.checkNotNull(zzc.zzb);
            return PackageVerificationResult.zza(callingPackage, zzc.zzb, zzc.zzc);
        }
        throw new zzae();
    }

    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(Context context, String callingPackage) {
        try {
            PackageVerificationResult queryPackageSignatureVerified = queryPackageSignatureVerified(context, callingPackage);
            queryPackageSignatureVerified.zzb();
            return queryPackageSignatureVerified;
        } catch (SecurityException e) {
            PackageVerificationResult queryPackageSignatureVerified2 = queryPackageSignatureVerified(context, callingPackage);
            if (!queryPackageSignatureVerified2.zzc()) {
                return queryPackageSignatureVerified2;
            }
            Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e);
            return queryPackageSignatureVerified2;
        }
    }
}
