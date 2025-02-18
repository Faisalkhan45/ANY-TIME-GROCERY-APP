package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p002firebaseauthapi.zzaae;
import com.google.android.gms.internal.p002firebaseauthapi.zzqx;
import com.google.android.gms.internal.p002firebaseauthapi.zzzr;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzt extends AbstractSafeParcelable implements UserInfo {
    public static final Parcelable.Creator<zzt> CREATOR = new zzu();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private String zzd;
    private Uri zze;
    private final String zzf;
    private final String zzg;
    private final boolean zzh;
    private final String zzi;

    public zzt(zzzr zzzr, String str) {
        Preconditions.checkNotNull(zzzr);
        Preconditions.checkNotEmpty(FirebaseAuthProvider.PROVIDER_ID);
        this.zza = Preconditions.checkNotEmpty(zzzr.zzo());
        this.zzb = FirebaseAuthProvider.PROVIDER_ID;
        this.zzf = zzzr.zzn();
        this.zzc = zzzr.zzm();
        Uri zzc2 = zzzr.zzc();
        if (zzc2 != null) {
            this.zzd = zzc2.toString();
            this.zze = zzc2;
        }
        this.zzh = zzzr.zzs();
        this.zzi = null;
        this.zzg = zzzr.zzp();
    }

    public final String getDisplayName() {
        return this.zzc;
    }

    public final String getEmail() {
        return this.zzf;
    }

    public final String getPhoneNumber() {
        return this.zzg;
    }

    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzd) && this.zze == null) {
            this.zze = Uri.parse(this.zzd);
        }
        return this.zze;
    }

    public final String getProviderId() {
        return this.zzb;
    }

    public final String getUid() {
        return this.zza;
    }

    public final boolean isEmailVerified() {
        return this.zzh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzh);
        SafeParcelWriter.writeString(parcel, 8, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.zzi;
    }

    public final String zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("userId", this.zza);
            jSONObject.putOpt("providerId", this.zzb);
            jSONObject.putOpt("displayName", this.zzc);
            jSONObject.putOpt("photoUrl", this.zzd);
            jSONObject.putOpt("email", this.zzf);
            jSONObject.putOpt("phoneNumber", this.zzg);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzh));
            jSONObject.putOpt("rawUserInfo", this.zzi);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzqx(e);
        }
    }

    public zzt(zzaae zzaae) {
        Preconditions.checkNotNull(zzaae);
        this.zza = zzaae.zzd();
        this.zzb = Preconditions.checkNotEmpty(zzaae.zzf());
        this.zzc = zzaae.zzb();
        Uri zza2 = zzaae.zza();
        if (zza2 != null) {
            this.zzd = zza2.toString();
            this.zze = zza2;
        }
        this.zzf = zzaae.zzc();
        this.zzg = zzaae.zze();
        this.zzh = false;
        this.zzi = zzaae.zzg();
    }

    public zzt(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzc = str5;
        this.zzd = str6;
        if (!TextUtils.isEmpty(str6)) {
            this.zze = Uri.parse(this.zzd);
        }
        this.zzh = z;
        this.zzi = str7;
    }
}
