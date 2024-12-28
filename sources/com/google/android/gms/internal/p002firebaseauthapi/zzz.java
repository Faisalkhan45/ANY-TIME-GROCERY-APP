package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzz extends zzad {
    final /* synthetic */ zzaa zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzz(zzaa zzaa, zzaf zzaf, CharSequence charSequence) {
        super(zzaf, charSequence);
        this.zza = zzaa;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzy.zzb(i, length, "index");
        while (i < length) {
            if (charSequence.charAt(i) == '.') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
