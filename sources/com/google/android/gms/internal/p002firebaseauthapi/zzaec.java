package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaec  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzaec implements zzaex {
    private static final zzaei zza = new zzaea();
    private final zzaei zzb;

    public zzaec() {
        zzaei zzaei;
        zzaei[] zzaeiArr = new zzaei[2];
        zzaeiArr[0] = zzada.zza();
        try {
            zzaei = (zzaei) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            zzaei = zza;
        }
        zzaeiArr[1] = zzaei;
        zzaeb zzaeb = new zzaeb(zzaeiArr);
        zzadl.zzf(zzaeb, "messageInfoFactory");
        this.zzb = zzaeb;
    }

    private static boolean zzb(zzaeh zzaeh) {
        return zzaeh.zzc() == 1;
    }

    public final zzaew zza(Class cls) {
        zzaey.zzG(cls);
        zzaeh zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzadf.class.isAssignableFrom(cls)) {
                return zzaeo.zzc(zzaey.zzB(), zzacv.zzb(), zzb2.zza());
            }
            return zzaeo.zzc(zzaey.zzz(), zzacv.zza(), zzb2.zza());
        } else if (zzadf.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzaen.zzl(cls, zzb2, zzaeq.zzb(), zzady.zze(), zzaey.zzB(), zzacv.zzb(), zzaeg.zzb());
            }
            return zzaen.zzl(cls, zzb2, zzaeq.zzb(), zzady.zze(), zzaey.zzB(), (zzact) null, zzaeg.zzb());
        } else if (zzb(zzb2)) {
            return zzaen.zzl(cls, zzb2, zzaeq.zza(), zzady.zzd(), zzaey.zzz(), zzacv.zza(), zzaeg.zza());
        } else {
            return zzaen.zzl(cls, zzb2, zzaeq.zza(), zzady.zzd(), zzaey.zzA(), (zzact) null, zzaeg.zza());
        }
    }
}
