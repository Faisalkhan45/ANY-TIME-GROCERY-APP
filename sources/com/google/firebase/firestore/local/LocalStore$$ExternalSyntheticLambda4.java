package com.google.firebase.firestore.local;

import com.google.protobuf.ByteString;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocalStore$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ LocalStore f$0;
    public final /* synthetic */ ByteString f$1;

    public /* synthetic */ LocalStore$$ExternalSyntheticLambda4(LocalStore localStore, ByteString byteString) {
        this.f$0 = localStore;
        this.f$1 = byteString;
    }

    public final void run() {
        this.f$0.m359lambda$setLastStreamToken$5$comgooglefirebasefirestorelocalLocalStore(this.f$1);
    }
}
