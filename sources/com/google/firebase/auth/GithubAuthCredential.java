package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p002firebaseauthapi.zzaay;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class GithubAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GithubAuthCredential> CREATOR = new zzab();
    private String zza;

    GithubAuthCredential(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public static zzaay zzb(GithubAuthCredential githubAuthCredential, String str) {
        Preconditions.checkNotNull(githubAuthCredential);
        return new zzaay((String) null, githubAuthCredential.zza, githubAuthCredential.getProvider(), (String) null, (String) null, (String) null, str, (String) null, (String) null);
    }

    public String getProvider() {
        return "github.com";
    }

    public String getSignInMethod() {
        return "github.com";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AuthCredential zza() {
        return new GithubAuthCredential(this.zza);
    }
}
