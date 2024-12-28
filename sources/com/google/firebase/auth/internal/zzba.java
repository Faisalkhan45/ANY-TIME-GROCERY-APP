package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzaac;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzba {
    @Nullable
    public static MultiFactorInfo zza(zzaac zzaac) {
        if (zzaac != null && !TextUtils.isEmpty(zzaac.zze())) {
            return new PhoneMultiFactorInfo(zzaac.zzd(), zzaac.zzc(), zzaac.zza(), Preconditions.checkNotEmpty(zzaac.zze()));
        }
        return null;
    }

    public static List zzb(List list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MultiFactorInfo zza = zza((zzaac) it.next());
            if (zza != null) {
                arrayList.add(zza);
            }
        }
        return arrayList;
    }
}
