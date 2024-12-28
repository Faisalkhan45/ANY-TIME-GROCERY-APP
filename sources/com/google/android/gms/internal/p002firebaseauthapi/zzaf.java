package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzaf {
    /* access modifiers changed from: private */
    public final zzn zza;
    private final zzae zzb;

    private zzaf(zzae zzae) {
        zzm zzm = zzm.zza;
        this.zzb = zzae;
        this.zza = zzm;
    }

    public static zzaf zzb(char c) {
        return new zzaf(new zzaa(new zzk('.')));
    }

    public static zzaf zzc(String str) {
        zzq zza2 = zzx.zza("[.-]");
        if (!((zzs) zza2.zza("")).zza.matches()) {
            return new zzaf(new zzac(zza2));
        }
        throw new IllegalArgumentException(zzag.zzb("The pattern may not match the empty string: %s", zza2));
    }

    public final List zzd(CharSequence charSequence) {
        if (charSequence != null) {
            Iterator zza2 = this.zzb.zza(this, charSequence);
            ArrayList arrayList = new ArrayList();
            while (zza2.hasNext()) {
                arrayList.add((String) zza2.next());
            }
            return Collections.unmodifiableList(arrayList);
        }
        throw null;
    }
}
