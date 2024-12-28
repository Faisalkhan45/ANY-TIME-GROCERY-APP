package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzabl;
import com.google.android.gms.internal.p002firebaseauthapi.zzabm;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public abstract class zzabm<MessageType extends zzabm<MessageType, BuilderType>, BuilderType extends zzabl<MessageType, BuilderType>> implements zzaek {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zzn(zzaew zzaew) {
        throw null;
    }

    public final zzacc zzo() {
        try {
            int zzs = zzs();
            zzacc zzacc = zzacc.zzb;
            byte[] bArr = new byte[zzs];
            zzacn zzG = zzacn.zzG(bArr);
            zzI(zzG);
            zzG.zzI();
            return new zzabz(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final void zzp(OutputStream outputStream) throws IOException {
        zzacn zzH = zzacn.zzH(outputStream, zzacn.zzB(zzs()));
        zzI(zzH);
        zzH.zzN();
    }

    public final byte[] zzq() {
        try {
            byte[] bArr = new byte[zzs()];
            zzacn zzG = zzacn.zzG(bArr);
            zzI(zzG);
            zzG.zzI();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
