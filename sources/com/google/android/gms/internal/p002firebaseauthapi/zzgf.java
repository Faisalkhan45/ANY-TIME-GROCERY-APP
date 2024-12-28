package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzgf extends zzbn {
    private final String zza;
    private final zzoy zzb;

    /* synthetic */ zzgf(String str, zzoy zzoy, zzge zzge) {
        this.zza = str;
        this.zzb = zzoy;
    }

    public final String toString() {
        String str;
        Object[] objArr = new Object[2];
        objArr[0] = this.zza;
        zzoy zzoy = this.zzb;
        zznr zznr = zznr.UNKNOWN_KEYMATERIAL;
        zzoy zzoy2 = zzoy.UNKNOWN_PREFIX;
        switch (zzoy.ordinal()) {
            case 1:
                str = "TINK";
                break;
            case 2:
                str = "LEGACY";
                break;
            case 3:
                str = "RAW";
                break;
            case 4:
                str = "CRUNCHY";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        objArr[1] = str;
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }
}
