package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzafl {
    static String zza(zzacc zzacc) {
        StringBuilder sb = new StringBuilder(zzacc.zzd());
        for (int i = 0; i < zzacc.zzd(); i++) {
            byte zza = zzacc.zza(i);
            switch (zza) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case 34:
                    sb.append("\\\"");
                    break;
                case 39:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (zza >= 32 && zza <= 126) {
                        sb.append((char) zza);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zza >>> 6) & 3) + 48));
                        sb.append((char) (((zza >>> 3) & 7) + 48));
                        sb.append((char) ((zza & 7) + 48));
                        break;
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
